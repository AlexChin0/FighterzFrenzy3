//This is the boss monster. It has special behaviours and its own abilties which only it can use.
package GameCore.entities.monsters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

import GameCore.*;
import GameCore.gfx.*;
import GameCore.heroes.Player;
import GameCore.entities.*;
import GameCore.worlds.Tiles.Tile;

public class IronFiend extends LiveEntity{
  
  private Animation neutral, neutral2, left, right, attack, attack2, stunned, stunned2, sprintLeft, sprintRight, rend, rend2;//animations
  private int behaviour;
  private boolean stampede = false, rending = false;
  
  protected long abTimer, lastUse;
  protected long [] abilities = new long[2];//ability cooldowns
  protected long [] cooldowns = new long[2];
  
  ArrayList<Long> a1tick = new ArrayList<Long>();
  ArrayList<Long> a2tick = new ArrayList<Long>();
  
  //constructor
  public IronFiend(Handler handler, float x, float y, int width, int height, float hpBonus) {
    super(handler, x, y, width, height, hpBonus);
    flying = false;
    health = 1000;
    maxHealth = 1000;
    healthMod = health / 120;
    
    bounds.x = 24;
    bounds.y = 10;
    bounds.width = 72;
    bounds.height = 72;
    
    speed = 3.0f;
    maxSpeed = 3.0f;
    
    cooldowns[0] = 8; //5
    cooldowns[1] = 3; //6
    
    behaviour = 1;
    abTimer = 0;
    lastUse = System.currentTimeMillis();
            
    neutral = new Animation(100, Assets.ironN);
    neutral2 = new Animation(100, Assets.ironN2);
    left = new Animation (150, Assets.ironML);
    right = new Animation (150, Assets.ironMR);
    
    sprintLeft = new Animation(100, Assets.sprintLeft);
    sprintRight = new Animation(100, Assets.sprintRight);
    
    rend = new Animation(100, Assets.ironWR);
    rend2 = new Animation(100, Assets.ironWR2);
    
    attack = new Animation (85, Assets.ironHA);
    attack2 = new Animation (85, Assets.ironHA2);
    stunned = new Animation (200, Assets.ironS);
    stunned2 = new Animation (200, Assets.ironS2);
  }
  
  @Override
  public void tick(){
    effectCheck(timeStack, affectedBy);
    tickCooldowns();
    
    neutral.tick();
    neutral2.tick();
    left.tick();
    right.tick();
    stunned.tick();
    stunned2.tick();
    
    //triggers the boss abilties when the timer is ready
    if(stampede && !rending){
      A1();
    }else if(rending && !stampede){
      A2();
    }else{
      moveBehaviour(behaviour); 
    }
    
    if(!attacking){
      attack.index = 0;//resets the animation when interupted
      attack2.index = 0;
    }
    
    move();
    inBounds();
  }
  
  @Override
  public void render(Graphics g){    
    //g.setColor(Color.green);
    //g.fillRect((int) (x + bounds.x * 3/4), (int)(y + bounds.y / 2), bounds.width * 2, bounds.height * 2); 
    //g.setColor (Color.red);
    //g.fillRect ((int) (x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height); 
    
    g.drawImage (getCurrentAnimationFrame(),(int) (x),(int) (y) , width, height, null); //draws the monster
    
    if(stampede){//draws sprinting effects
      if(dir == 1){
        g.drawImage(sprintLeft.getCurrentFrame(),(int) (x),(int) (y) , width, height, null);  
      }else if(dir == 2){
        g.drawImage(sprintRight.getCurrentFrame(),(int) (x),(int) (y) , width, height, null); 
      }
    }
  }
  
  //tracks the cooldowns of the monster abilities
  public void tickCooldowns(){
    abTimer += System.currentTimeMillis() - lastUse;
    lastUse = System.currentTimeMillis(); //clock timer for abilities
    
    long toSeconds = (int)(abTimer / 1000);
    if (!a1tick.contains(toSeconds)){
      a1tick.add(toSeconds);
      if (abilities[0] < cooldowns[0]){
        abilities[0]++;
      }else if(abilities[0] == cooldowns[0]){
        if(!stun || !rending){
        stampede = true;
        }
        abilities[0] = 0;
        cooldowns[0] = (int)(Math.random() * 8) + 5;
      }
    }
    
    if (!a2tick.contains(toSeconds)){
      a2tick.add(toSeconds);
      if (abilities[1] < cooldowns[1]){
        abilities[1]++;
      }else if(abilities[1] == cooldowns[1]){
        if(!stun || !stampede){
          rending = true;
        }
        abilities[1] = 0;
        cooldowns[1] = (int)(Math.random() * 7) + 4;
      }
    }
    
  }
  
  public boolean checkCooldowns(int abNum){ //a method which returns true if the ability is usable
    if (abilities[abNum] == cooldowns[abNum] && !silenced){
      return true;
    }else{
      return false; 
    }
  }
  
  //attack method
  @Override
  public void attack(){
    attack.tick();
    attack2.tick();
    
    if(attack.checkAnim()){
      if(hitCheck(100, 100, 5)){
       heal(3); 
      }
      return;
    }
  }
  
  //boss charge ability
  private void A1(){
    sprintLeft.tick();
    sprintRight.tick();
    if(dir == 1){
     x -= 7;
     yMove = 0;
        int tx = (int)(x + xMove + bounds.x) / Tile.TILEWIDTH;
        
        if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
           collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) || x < 32){
          timeStack += 3;
          affectedBy = 1;
          stunned();
          x += 64;
          stampede = false;
        }
    }else if(dir == 2){
     x += 7;
     yMove = 0;
        int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
        
        if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
           collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) || x + bounds.width > Launcher.runWidth - Tile.TILEWIDTH  * 2){
          timeStack += 3;
          affectedBy = 1;
          stunned();
            x -= 64;
            stampede = false;
        }
    }

    for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this)){
         continue;
        }else if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xMove, 0)) || e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0, yMove))){
         if(e instanceof Player){
          e.timeStack += 3;
          e.affectedBy = 1;
          e.stunned();
          e.hurt(50);
          if(dir == 1){
            e.setX(e.getX() - 64);
            x += 64; 
          }else if(dir == 2){
            e.setX(e.getX() + 64);
            x -= 64;
          }
          stampede = false;
         }
        }
       }
    
    if(stun || silenced){
      stampede = false;
      return;
    } 
  }
  
  //boss sword ability
  private void A2(){
    xMove = 0;
    yMove = 0;
    adjust(300, 300);
    rend.tick();
    rend2.tick();
    
    if(rend.checkAnim()){
      reShape(300, 300, 192, 192);
      rending = false;
      hitCheck(120, 200, 100);
    }
  }
  
  //displays the proper animation to the screen
  public BufferedImage getCurrentAnimationFrame(){
    
    if(stun && dir == 1){
      return stunned.getCurrentFrame();
    }else if(stun && dir == 2){
      return stunned2.getCurrentFrame();      
    }
    else if(rending && dir == 1){
      return rend.getCurrentFrame();
    }else if(rending && dir == 2){
      return rend2.getCurrentFrame(); 
    }
    else if(attacking && dir == 1){
      return attack.getCurrentFrame();
    }else if(attacking && dir == 2){
      return attack2.getCurrentFrame();
    }else if (dir == 2){
      return right.getCurrentFrame(); 
    }
    else
      return left.getCurrentFrame();
  }
 
  
  //hurt and heal methods.
  @Override
  public void hurt(float amt){
    health -= amt*0.8;
    if(health <= 0){
      handler.getWorld().getEntityManager().getPlayer().heal(250);
      die();
    }
  }
  
  @Override
  public void heal(float amt){
    health+= amt;
    if(health >= maxHealth){
      health = maxHealth;
    }
  }
}
