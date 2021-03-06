//Grayson is a playable character in the game. This class keeps track of the characters hp, abilities, attacks, and graphics. 
// It is an extension of the Player class.

package GameCore.heroes;

import GameCore.*;
import GameCore.States.GameState;
import GameCore.gfx.*;
import GameCore.entities.*;
import GameCore.worlds.Tiles.Tile;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class Grayson extends Player{
  
  public Animation neutral, neutral2, left, right, down, up, attackLight, attackLight2, attackHeavy, attackHeavy2, shieldCast, shieldCast2, shieldCharge, shieldCharge2, runeShard, runeShard2, enrune, ultiCast, ultiCast2, runeBomb, flinch, flinch2, stunned, stunned2;
  
  private boolean protect = false, shard = false, unleash = false, detonate = false, charge = false, powerUp = false, boost = false;
  private int leapRange = 198, leapDmg = 20, shieldDmg = 30, initX;
  private int runeForce = 0, runeCharge = 0;
  private float runeFactor = 1.0f, baseFactor = 0.0f;
  private ArrayList<Long> runeTick = new ArrayList<Long>();
  
  public Grayson(Handler handler, float x, float y) { 
    super (handler, x , y, Player.defaultWidth, Player.defaultHeight); 
    
    bounds.x = 64;
    bounds.y = 96; // hit box variables
    bounds.width = 60;
    bounds.height = 58;
    
    neutral = new Animation (1000, Assets.grayN);
    neutral2 = new Animation (1000,Assets.grayN2);
    left = new Animation (100, Assets.grayML);
    right = new Animation (100, Assets.grayMR);
    down = new Animation (100, Assets.grayMD); //instances of the animation class
    up = new Animation (100, Assets.grayMU);
    
    attackLight = new Animation (100, Assets.grayLA);
    attackLight2 = new Animation (100, Assets.grayLA2);
    attackHeavy = new Animation (100, Assets.grayHA);
    attackHeavy2 = new Animation (100, Assets.grayHA2);   
    
    shieldCast = new Animation (140, Assets.grayR);
    shieldCast2 = new Animation (140, Assets.grayR2);
    shieldCharge = new Animation(100, Assets.graySC);
    shieldCharge2 = new Animation(100, Assets.graySC2);
    
    runeShard = new Animation (70, Assets.grayRS);
    runeShard2 = new Animation (70, Assets.grayRS2);
    
    enrune = new Animation(100, Assets.grayE);
    
    ultiCast = new Animation (130, Assets.grayUC);
    ultiCast2 = new Animation (130, Assets.grayUC2);
    
    flinch = new Animation (100, Assets.grayF);
    flinch2 = new Animation (100, Assets.grayF2);
    
    runeBomb = new Animation (75, Assets.arunLia);
    
    stunned = new Animation (200, Assets.grayS);
    stunned2 = new Animation (200, Assets.grayS2);
    
    width = 192;
    height = 192;
    
    health = 650; //hero values
    maxHealth = 650;
    healthMod = health / 300;
    speed = 4.4f;
    maxSpeed = 4.4f;
    
    cooldowns[0] = 5; //5
    cooldowns[1] = 4; //6
    cooldowns[2] = 18; //23
    cooldowns[3] = 8;
    cooldowns[4] = 1; //1
    cooldowns[5] = 1; //1
  }

  @Override //method responsible for incoming damage calculations
  public void hurt(float amt){
    if(protect){
      runeForce += amt;
      shieldDmg += amt;
    }else {
      if(!protect){
        health -= amt;
    }
    }
    hit = true;
    if (health <= 0){
      alive = false;
      die();
    }
  }
  
  //recovers the players health
    @Override 
  public void heal(float amt){
    if (health > maxHealth){
     health = maxHealth;
    }else{
     health += amt; 
    }
  }

  @Override
  public void die(){
    alive = false; //death method. Removes the entity from the game upon death. 
    GameState.end = true;
  }
  
  //player movement method
  public void getInput(){
  xMove = 0;
  yMove = 0;
  
  if(playerNum == 1){
    if (handler.getKeyManager().up)
      yMove = -speed;
    if (handler.getKeyManager().down)
      yMove = speed;
    if (handler.getKeyManager().left)
      xMove = -speed;
    if (handler.getKeyManager().right)
      xMove = speed;   
  } 
  if(playerNum == 2){
    if (handler.getKeyManager().up2)
      yMove = -speed;
    if (handler.getKeyManager().down2)
      yMove = speed;
    if (handler.getKeyManager().left2)
      xMove = -speed;
    if (handler.getKeyManager().right2)
      xMove = speed; 
  }
}
  
  //ticks every value for the hero
  @Override
  public void tick(){
    tickCooldowns();   
    inBounds();

    effectCheck(timeStack, affectedBy); //checks the player states
    
    long toSeconds = abTimer / 1000;
    if (!runeTick.contains(toSeconds)){
      runeTick.add(toSeconds);
      if (runeForce < 300){
        runeForce+= 5;
      }else if(runeForce >= 300){
        runeForce = 300;
      }
    }
    
    if(runeForce >= 100 && runeForce <= 200){
      baseFactor = 0.3f;
    }else if(runeForce > 200){
      baseFactor = 0.5f; 
    }
    
    if(boost && abilities[3] == 6){
      speed -= 0.8;
      runeFactor -= 0.3;
      boost = false;
    }
    
    //Animation
    neutral.tick();
    neutral2.tick();
    left.tick();
    right.tick();
    up.tick();
    down.tick();
    flinch.tick();
    flinch2.tick();
    stunned.tick();
    stunned2.tick();
    
    //movement and abilities
    if (free && !stun){
      getInput();
      move();    
      
      if (handler.getKeyManager().attackLight && playerNum == 1 && checkCooldowns(4)){
        LA = true;
        free = false;
        abilities[4] = 0;
      }
      if (handler.getKeyManager().attackHeavy && playerNum == 1  && checkCooldowns(5)){
        HA = true;
        free = false;
        abilities[5] = 0;
      }
      if (handler.getKeyManager().a1 && checkCooldowns(0) && playerNum == 1){
        free = false;
        protect = true;
        initX = (int)(x);
        abilities[0] = 0;
      }
      if (handler.getKeyManager().a2 && checkCooldowns(1) && playerNum == 1){
        leapDmg = 20;
        free = false;
        shard = true;
        abilities[1] = 0;
      }
      if (handler.getKeyManager().a3 && checkCooldowns(2) && playerNum == 1){
        free = false;
        unleash = true;
        if(runeForce > 20){
          }else if(runeForce <= 20){
          }
        abilities[2] = 0;
      }
      if (handler.getKeyManager().a4 && checkCooldowns(3) && playerNum == 1){
        free = false;
        powerUp = true;
        abilities[3] = 0;
      }
    }else if (!free){
      
    }
    
    //triggers the abilities based on the boolean conditions
    if (LA){
      LightAttack(); 
    }
    if (HA){
      HeavyAttack();
    }
    if(protect){
      A1();
    }
    if (shard){
     A2(); 
    }
    if(powerUp){
     A3(); 
    }
    if(unleash){
     A4(); 
    }else if(detonate){
     runeBomb.tick();
     
     if(runeBomb.checkAnim()){
       free = true;
       detonate = false;
       runeCharge = (int)(runeForce * 0.75);
                  
         if(runeForce > 20){
           rangedCheck((int)(x - 125),(int)(y - 200), 600, 600, 100 + runeCharge, 0, 0);
           runeForce = 0;
         }else if(runeForce <= 20){
           health -= health/5;
           runeForce = 0;
         }
         
     }
     
    }    
    if(hit && flinch.checkAnim()){
      hit = false; 
    }
  }
  
  //the method which triggers basic attacks
  @Override
  public void LightAttack(){
    attackLight.tick();  
    attackLight2.tick();
    adjust(270, 150);    
    free = false;
    
    if (attackLight.checkAnim()){
      LA = false;
      free = true;
      reShape(270, 150, 192, 192);
      hitCheck(70, 40, (int)(40 * (baseFactor + runeFactor)));
    }
    
    if(stun || silenced){
      LA = false;
      attackLight.index = 0;
      attackLight2.index = 0;
      return;
    }     
  }
  
  //the method which triggers another basic attacks
  @Override
  public void HeavyAttack(){
    attackHeavy.tick();  
    attackHeavy2.tick();
    adjust(270, 150);
    free = false;
    
    if (attackHeavy.checkAnim()){
      HA = false;
      free = true;
      reShape(270, 150, 192, 192);
      hitCheck(60, 50, 80);
    }
    
    if(stun || silenced){
      HA = false;
      attackHeavy.index = 0;
      attackHeavy2.index = 0;
      return;
    }     
  }
  
  //the method which triggers the characters first ability
  @Override
  public void A1(){
    shieldCast.tick();
    shieldCast2.tick();
    
    if (handler.getKeyManager().up)
      yMove = -speed;
    if (handler.getKeyManager().down)
      yMove = speed; 
    
    if(dir == 1){
     x -= 8.5;
        int tx = (int)(x + xMove + bounds.x) / Tile.TILEWIDTH;
        
        if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
           collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) || x < 0){
            protect = false;
            free = true;
        }
    }else if(dir == 2){
     x += 8.5;
        int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
        
        if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
           collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) || x + bounds.width > Launcher.runWidth - Tile.TILEWIDTH  * 2){
            protect = false;
            free = true;
            x -= 64;
        }
    }
    
    
    for (Entity e : handler.getWorld().getEntityManager().getEntities()){
      if (e.equals(this)){
        continue;
      }else if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xMove, 0)) || e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0, yMove))){
        free = true;
        if(e instanceof LiveEntity){
          shieldDmg += 30;
          knockback(100, 100, (int)(30 * (baseFactor + runeFactor)), 72);
          e.timeStack += 4;
          e.affectedBy = 1;
          e.stunned();
          if(e.getY() > y){
            e.setY(e.getY() - 64);
          }else{
           e.setY(e.getY() + 64); 
          }
        }
      }
    }
    
    if(Math.abs(x - initX) >= 650){
      protect = false;
      free = true;
    }else if (handler.getKeyManager().a1 && Math.abs(x - initX) >= 150){
     charge = true;
     protect = false;
     adjust(330, 210);
    }
    
  }
  
      //the method which triggers the characters second ability
  @Override
  public void A2(){
    runeShard.tick();
    runeShard2.tick();
    adjust(390, 210);
    if (handler.getKeyManager().a2 && playerNum == 1 && runeShard.index > 2 || handler.getKeyManager().a5 && playerNum == 2 && runeShard.index > 2){
      runeForce -= 1;
      leapDmg += 2;
      if(runeForce <= 0){
        shard = false;
        free = true;
        runeShard.index = 0;
        runeShard2.index = 0;
        reShape(390, 210, 192, 192);
        hurt(leapDmg / 2);
        return;
      }
    }
    
    if(runeShard.checkAnim()){
      shard = false;
      free = true;
      reShape(390, 210, 192, 192);
      if(dir == 1){
      x -= leapRange;
      }else if(dir == 2){
      x += leapRange;
      }
      
      areaCheck(100, 90, (int)(leapDmg * (baseFactor + runeFactor)));
    }
    
    if(stun || silenced){
      shard = false;
      runeShard.index = 0;
      runeShard2.index = 0;
      adjust(192, 192);
      return;
    } 
  }
  
    //the method which triggers the characters third ability
  @Override
  public void A3(){
    enrune.tick();
    adjust(240, 240);
    
    if(enrune.checkAnim()){
      runeForce += 80;
      powerUp = false;
      speed += 0.8;
      runeFactor += 0.3;
      boost = true;
      free = true;
      reShape(240, 240, 192, 192);
    }
    
    if(stun || silenced){
      powerUp = false;
      reShape(240, 240, 192, 192);
    }
  }
  
    //the method which triggers the characters fourth ability
  @Override
  public void A4(){
    ultiCast.tick();
    ultiCast2.tick();
    adjust(240, 240);
    
    if(ultiCast.checkAnim()){
      unleash = false;
      detonate = true;
      reShape(240, 240, 192, 192);
    }
    
    if(stun || silenced){
     ultiCast.index = 0;
     ultiCast2.index = 0;
     unleash = false;
     detonate = false;
     health -= health/5;
     adjust(192, 192);
     return;
    }
  }
  
  //renders the hero and their abilities
  @Override
  public void render (Graphics g){
    //g.setColor(Color.green);
    //g.fillRect((int) (x + bounds.x * 3/4), (int)(y + bounds.y / 2), bounds.width * 2, bounds.height * 2); 
    //g.setColor (Color.red);
    //g.fillRect ((int) (x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height); 
    
    if(abilities[0] == cooldowns[0]){
      g.drawImage(Assets.grayAbils[0], 368, 782, 32, 32, null);
    }else{
      g.drawImage(Assets.grayAbils[1], 368, 782, 32, 32, null);
    }
    if(abilities[1] == cooldowns[1]){
      g.drawImage(Assets.grayAbils[2], 425, 782, 32, 32, null);      
    }else{
      g.drawImage(Assets.grayAbils[3], 425, 782, 32, 32, null);
    }
    if(abilities[3] == cooldowns[3]){
      g.drawImage(Assets.grayAbils[6], 481, 782, 32, 32, null);      
    }else{
      g.drawImage(Assets.grayAbils[7], 481, 782, 32, 32, null);
    }
    if(abilities[2] == cooldowns[2]){
      g.drawImage(Assets.grayAbils[4], 537, 782, 32, 32, null);
    }else{
      g.drawImage(Assets.grayAbils[5], 537, 782, 32, 32, null);
    }
    
    if(silenced){
     g.drawImage(Assets.silenced, 368, 782, 32, 32, null);
     g.drawImage(Assets.silenced, 425, 782, 32, 32, null);
     g.drawImage(Assets.silenced, 537, 782, 32, 32, null);
    }else{
     
    }
 
    g.drawImage (getCurrentAnimationFrame(),(int) (x),(int) (y) , width, height, null); 
       
    if(detonate && runeForce >= 20){
     //g.fillRect((int)(x - 200),(int)(y - 125), 600, 600);
     g.drawImage(runeBomb.getCurrentFrame(), (int) (x - 300), (int) (y - 225), 800, 800,  null); 
    }    
    
    if(charge){
     shieldCharge.tick();
     shieldCharge2.tick();
        if (handler.getKeyManager().a1 && playerNum == 1 && shieldCharge.index >= 2|| handler.getKeyManager().a4 && playerNum == 2 && shieldCharge.index >= 2){
         shieldDmg += 1;
         runeForce --;
            if(runeForce <= 0){
                charge = false;
                free = true;
                shieldCharge.index = 0;
                shieldCharge2.index = 0;
                reShape(390, 210, 192, 192);
                hurt(shieldDmg / 2);
                return;
              }
        }
     if(shieldCharge.checkAnim()){
      knockback(100, 70, shieldDmg, 102);
      if(dir == 1){
       x -= 118;
      }else if(dir == 2){
       x += 118;
      }
      shieldDmg = 30;
      charge = false;
      adjust(192, 192);
      free = true;
     }
    }
    
    if(playerNum == 1){
      g.setColor(Color.blue);
      g.fillRect (44, 804, runeForce, 8); 
    }
    if(playerNum == 2){
      g.setColor(Color.blue);
      g.fillRect (652, 804, runeForce, 8); 
    }
  }
  
  private BufferedImage getCurrentAnimationFrame(){ //decides which animation to tick to the screen
      if(stun && dir == 1){
      return stunned.getCurrentFrame();
    }else if(stun && dir == 2){
      return stunned2.getCurrentFrame();      
    }
    
    // attacks
    else if (!attackLight.checkAnim() && LA && dir == 1){
      return attackLight.getCurrentFrame();
    }else if (!attackLight.checkAnim() && LA && dir == 2){
      return attackLight2.getCurrentFrame();
    }else if (!attackHeavy.checkAnim() && HA && dir == 1){
      return attackHeavy.getCurrentFrame();
    }else if (!attackHeavy.checkAnim() && HA && dir == 2){
      return attackHeavy2.getCurrentFrame();
    }
    
    //abilities
      
    else if(!shieldCharge.checkAnim() && charge && dir == 1){
     return shieldCharge2.getCurrentFrame();
    }else if(!shieldCharge.checkAnim() && charge && dir == 2){
     return shieldCharge.getCurrentFrame();
    }
    
    //shield
    else if(protect && !charge && dir == 1){
      return shieldCast.getCurrentFrame();
    }else if(protect && !charge && dir == 2){
      return shieldCast2.getCurrentFrame();
    }
    
    //runeshard
    else if(!runeShard.checkAnim() && shard && dir == 1){
      return runeShard.getCurrentFrame();
    }else if(!runeShard.checkAnim() && shard && dir == 2){
      return runeShard2.getCurrentFrame();
    }
    
    //power
    else if(!enrune.checkAnim() && powerUp){
      return enrune.getCurrentFrame();
    }
    
    //ulti
    else if(!ultiCast.checkAnim() && unleash && dir == 1){
      return ultiCast.getCurrentFrame();
    }else if(!ultiCast.checkAnim() && unleash && dir == 2){
     return ultiCast2.getCurrentFrame(); 
    }
    
    else if(hit && dir == 1){
      return flinch.getCurrentFrame();
    }else if(hit && dir == 2){
     return flinch2.getCurrentFrame(); 
    }
    
    // movement
    else if (handler.getKeyManager().left && playerNum == 1 || handler.getKeyManager().left2 && playerNum == 2){
      dir = 1;
      return left.getCurrentFrame(); 
    }else if (handler.getKeyManager().right && playerNum == 1 || handler.getKeyManager().right2 && playerNum == 2){
      dir = 2;
      return right.getCurrentFrame();
    }else if (handler.getKeyManager().down && playerNum == 1 || handler.getKeyManager().down2 && playerNum == 2){
      return down.getCurrentFrame();
    }else if (handler.getKeyManager().up && playerNum == 1 || handler.getKeyManager().up2 && playerNum == 2){
      return up.getCurrentFrame();
    }else if (dir == 2){
      return neutral2.getCurrentFrame(); 
    }
    else
      return neutral.getCurrentFrame();
  }
  
  public int getRunes(){
    return runeForce;
  }
  
}