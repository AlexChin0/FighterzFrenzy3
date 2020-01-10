//TaFiRah is another playable character in the game. It contains the same methods and follows the same procedures as the other hero classes

package GameCore.heroes;

import GameCore.*;
import GameCore.States.GameState;
import GameCore.entities.*;
import GameCore.gfx.*;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

public class TaFiRah extends Player{
  
  public Animation neutral, neutral2, left, right, down, up, attackLight, attackLight2, attackHeavy, attackHeavy2, flinch, flinch2, stunned, stunned2, fireballLight1, fireballLight2, fireballHeavy1, fireballHeavy2, spearLeft, spearRight, spearCast, spearCast2, explode, grasp, grasp2, flameCol, ultiCast, ultiCast2, 
  flightNeutral, flightNeutral2, flightAttack, flightAttack2, flightLance, flightLance2, flightCatch, flightCatch2, megafire, megafire2, dash, dash2, blast, blast2;
  private boolean lance = false, lanceStrike = false, lanceDet = false, burn = false, grapple = false, fireCast = false, fireDash = false, blasted = false;
  private int posInit= 0, posDelta = 0, posX, posY, spearRange = 350, onFire = 0;
  private float burnDmg = 35.0f;
  private ArrayList<Projectile> lFire;
  private ArrayList<Projectile> mFire;
  private ArrayList<Projectile> spears;
  private ArrayList<Projectile> cyclones;
  
  public TaFiRah(Handler handler, float x, float y) { 
    super (handler, x , y, Entity.defaultWidth, Entity.defaultHeight); 
    
    bounds.x = 64;
    bounds.y = 96; // hit box variables
    bounds.width = 60;
    bounds.height = 58;
    
    neutral = new Animation (1000, Assets.taN);
    neutral2 = new Animation (1000,Assets.taN2);
    left = new Animation (100, Assets.taML);
    right = new Animation (100, Assets.taMR);
    down = new Animation (100, Assets.taMD); //instances of the animation class
    up = new Animation (100, Assets.taMU);
    
    attackLight = new Animation (100, Assets.taLA);
    attackLight2 = new Animation (100, Assets.taLA2);
    attackHeavy = new Animation (140, Assets.taHA);
    attackHeavy2 = new Animation (140, Assets.taHA2);   
    
    blast = new Animation(100, Assets.taB);
    blast2 = new Animation(100, Assets.taB2);
 
    spearCast = new Animation(100, Assets.taSP);
    spearCast2 = new Animation(100, Assets.taSP2);   
    spearLeft = new Animation(100, Assets.taSPP);
    spearRight = new Animation(100, Assets.taSPP2);   
    
    dash = new Animation(100, Assets.taFD);
    dash2 = new Animation(100, Assets.taFD2);
       
    grasp = new Animation(110, Assets.taCC);
    grasp2 = new Animation(110, Assets.taCC2);
    flameCol = new Animation(100, Assets.taFC);
    
    ultiCast = new Animation(100, Assets.taPW);
    ultiCast2 = new Animation(100, Assets.taPW);
    flightNeutral = new Animation(100, Assets.taFN);
    flightNeutral2 = new Animation(100, Assets.taFN2);
    
    flightAttack = new Animation(100, Assets.taFLA);
    flightAttack2 = new Animation(100, Assets.taFLA2);
    
    flightLance = new Animation(100, Assets.taFL);
    flightLance2 = new Animation(100, Assets.taFL2);
    flightCatch = new Animation(100, Assets.taFG);
    flightCatch2 = new Animation(100, Assets.taFG2);
    
    flinch = new Animation (100, Assets.taF);
    flinch2 = new Animation (100, Assets.taF2);
    stunned = new Animation (200, Assets.taS);
    stunned2 = new Animation (200, Assets.taS2);
    
    fireballLight1 = new Animation(100, Assets.taFB);
    fireballLight2 = new Animation(100, Assets.taFB2);
    fireballHeavy1 = new Animation(100, Assets.taFBH);
    fireballHeavy2 = new Animation(100, Assets.taFBH2);
    megafire = new Animation(100, Assets.taMFB);
    megafire2 = new Animation(100, Assets.taMFB2);
       
    explode = new Animation(100, Assets.taSB);
    
    lFire = new ArrayList<Projectile>();            
    mFire = new ArrayList<Projectile>();     
    spears = new ArrayList<Projectile>();
    cyclones = new ArrayList<Projectile>();
    
    width = 192;
    height = 192;
    
    health = 480; //hero values
    maxHealth = 480;
    healthMod = health / 300;
    speed = 4.5f;
    maxSpeed = 4.5f;
    
    cooldowns[0] = 4; //6
    cooldowns[1] = 8; //11
    cooldowns[2] = 24; //30
    cooldowns[3] = 3; //4
    cooldowns[4] = 1;
    cooldowns[5] = 2;
  }

  @Override //method responsible for incoming damage calculations
  public void hurt(float amt){
    health -= amt;
    hit = true;
    if (health <= 0){
      alive = false;
      die();
    }
  }
  
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
    alive = false;//death method. Removes the entity from the game upon death. 
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
    
    projCheck(lFire, 35, 0, 0);
    projCheck(mFire, 80, 0, 0);
    
    for(int i = 0; i < cyclones.size(); i++){
      Rectangle pb = new Rectangle ();
      cyclones.get(i).tick();
      
      pb.x = cyclones.get(i).projX + cyclones.get(i).inX;
      pb.y = cyclones.get(i).projY + cyclones.get(i).inY;
      pb.width = cyclones.get(i).projWidth;
      pb.height = cyclones.get(i).projHeight;
      
      for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this))
          continue;
        if(e.getHitbox(0, 0).intersects(pb)){
          e.hurt(4); 
          if(cyclones.get(i).speedX > 0){
          e.setX(e.getX() + 25);  
          }else if(cyclones.get(i).speedX < 0){
            e.setX(e.getX() - 25);
          }
          if(cyclones.get(i).projBounds()){
            cyclones.remove(cyclones.get(cyclones.size() - 1));           
          }else{
            
          }
          return;
        }
      }   
    }
    
     effectCheck(timeStack, affectedBy); 
  
    //Animation
    neutral.tick();
    neutral2.tick();
    spearLeft.tick();
    spearRight.tick();
    left.tick();
    right.tick();
    up.tick();
    down.tick();
    flinch.tick();
    flinch2.tick();
    stunned.tick();
    stunned2.tick();
    flightNeutral.tick();
    flightNeutral2.tick();
    fireballLight1.tick();
    fireballLight2.tick();
    fireballHeavy1.tick();
    fireballHeavy2.tick();
    
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
        lance = true;
        free = false;
        abilities[0] = 0;
      }
      if (handler.getKeyManager().a2 && checkCooldowns(1) && playerNum == 1){
        free = false;
        grapple = true;
        abilities[1] = 0;
      }
      if (handler.getKeyManager().a4 && checkCooldowns(3) && playerNum == 1){
        free = false;
        fireDash = true;
        abilities[3] = 0;
      }
      if (handler.getKeyManager().a3 && checkCooldowns(2) && playerNum == 1){
        fireCast = true;
        rangedCheck((int)(x - 40), (int)(y + 30), 250, 110, 65, 0, 0);
        y-=96;
        free = false;
        abilities[2] = 0;
      }
    }else if (!free){
      
    }
    
    if (LA){
      LightAttack(); 
    }
    if (HA){
      HeavyAttack();
    }
    if(lance){
      A1();
    }
      
    if(lanceDet){
     explode.tick();   
     posDelta = 0;
     if(explode.index >= 2){
      Rectangle pb = new Rectangle ();
      pb.x = (int)(posX - 100 * 0.25);
      pb.y = (int)(posY - 100 * 0.40);
      pb.width = 150;
      pb.height = 150;
      
      for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this))
          continue;
        if(e.getHitbox(0, 0).intersects(pb) && !(e instanceof Player)){
          burnDmg = 0;
          burn = true;
          return;
        }
      }
    }  
     if(explode.checkAnim()){
       lanceDet = false; 
       spears.clear();
     }
    }
    
    if(burn && burnDmg <= 50){
      for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this)){
          continue;
        }else if(!e.equals(this)){
          e.hurt(0.5f);
          burnDmg += 0.5f;
          return;
        }
      }
    }else{
     burn = false; 
    }

    if (grapple){
     A2(); 
    }
    
    if(fireDash){
     A3(); 
    }
    
    if(fireCast){
     A4(); 
    } 

    if(hit && flinch.checkAnim()){
      hit = false; 
    }
    
    if(lanceStrike){
      
    for(int i = 0; i < spears.size(); i++){
      Rectangle pb = new Rectangle ();
      spears.get(i).tick();
       
      pb.x = spears.get(0).projX + spears.get(0).inX;
      pb.y = spears.get(0).projY + spears.get(0).inY;
      pb.width = spears.get(0).projWidth;
      pb.height = spears.get(0).projHeight;
      
      for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this))
          continue;
        if(e.getHitbox(0, 0).intersects(pb) && !(e instanceof Player)){
          e.hurt(95);
          lanceDet = true;
          lanceStrike = false;
          posX = spears.get(0).projX + spears.get(0).inX;
          posY = spears.get(0).projY + spears.get(0).inY;
          spears.get(0).speedX = 0;
          break;
        }
      }    
    }
    if(!flying){
      posDelta = Math.abs(posInit - spears.get(0).projX);    
    }
      if(posDelta >= spearRange && spearRange > 0){
       lanceDet = true;
       lanceStrike = false;
       posX = spears.get(0).projX + spears.get(0).inX;
       posY = spears.get(0).projY + spears.get(0).inY;
       spears.get(0).speedX = 0;
      }
    }
        
  }
  
  @Override
  public void LightAttack(){
   if(!flying){
    attackLight.tick();  
    attackLight2.tick();  
    free = false;

    if (attackLight.checkAnim()){
     LA = false;
     free = true;
     if(dir == 2){
      lFire.add(new Projectile (16, 0, (int)(x + bounds.x), (int)(y - 20), 25, 18, 128, 72, fireballLight2, 2));
     }else if(dir == 1){
      lFire.add(new Projectile (-16, 0, (int)(x), (int)(y - 20), 25, 18, 128, 72, fireballLight1, 1));        
     }
    }    
   }else if(flying){
    flightAttack.tick();  
    flightAttack2.tick();  
    free = false;

    if (flightAttack.checkAnim()){
     LA = false;
     free = true;
     if(dir == 2){
      mFire.add(new Projectile (18, 0, (int)(x + bounds.x), (int)(y - 20), 40, 40, 128, 72, megafire, 2));
     }else if(dir == 1){
      mFire.add(new Projectile (-18, 0, (int)(x), (int)(y - 20), 40, 40, 128, 72, megafire2, 1));        
     }
    }     
   }
   
     if(stun || silenced){
         LA = false;
         attackLight.index = 0;
         attackLight2.index = 0;
         return;
       }   
  }
  
  @Override
  public void HeavyAttack(){
    if(!flying){
      attackHeavy.tick();  
      attackHeavy2.tick();
      free = false;
      
      if (attackHeavy.checkAnim()){
        HA = false;
        if((knockback(50, 50, 50, 300) > 0)){
          blasted = true;
        }else{
          free = true; 
        }
      }
    }else if(flying){
      flightAttack.tick();  
      flightAttack2.tick();
      free = false;
      
      if (flightAttack.checkAnim()){
        HA = false;
        if((knockback(50, 50, 100, 450) > 0)){
          blasted = true;
        }else{
          free = true; 
        }
      }   
    }
    
    if(stun || silenced){
      HA = false;
      attackHeavy.index = 0;
      attackHeavy2.index = 0;
      flightAttack.index = 0;
      flightAttack2.index = 0;
      return;
    }
  }
  
  @Override
  public void A1(){
 if(!flying){
    spearCast.tick();
    spearCast2.tick(); 
    free = false;
    
    if (spearCast.checkAnim()){
      if(dir == 2){
        spears.add(new Projectile (20, 0, (int)(x + bounds.x), (int)(y -30), 25, 40, 128, 72, spearLeft, 2));
        posInit = (int)(x + bounds.x);
      }else if(dir == 1){
        spears.add(new Projectile (-20, 0, (int)(x), (int)(y - 30), 25, 40, 128, 72, spearRight, 1));        
        posInit = (int)(x);
      } 
      lanceStrike = true;
      lance = false;
      free = true;
    }
 }else if(flying){
  adjust(330, 240);
     flightLance.tick();
     flightLance2.tick(); 
     free = false;
     
     if (flightLance.checkAnim()){
       if(dir == 2){
         spears.add(new Projectile (24, 0, (int)(x + bounds.x), (int)(y + 10), 25, 40, 128, 72, spearLeft, 2));
       }else if(dir == 1){
         spears.add(new Projectile (-24, 0, (int)(x), (int)(y + 10), 25, 40, 128, 72, spearRight, 1));        
       }          
         if(proc(50)){
           for(int f = 0; f <= abilities.length - 1; f++){
             if(abilities[f] > 0 && abilities[f] < cooldowns[f]){
             abilities[f] += 2;
             }
             if(abilities[f] >= cooldowns[f]){
               abilities[f] = cooldowns[f];
             }
           }
           heal(50);
         }
       reShape(330, 240, 192, 192);
       lanceStrike = true;
       lance = false;
       free = true;
     } 
 }
     
    if(stun || silenced){
     spearCast.index = 0;
     spearCast2.index = 0;
     adjust(192, 192);
      lance = false;
      free = false;
      lanceDet = false;
     return;
    }
  }
    
  @Override
  public void A2(){
   if(!flying){
    grasp.tick();
    grasp2.tick();
    adjust(300, 192);

    if(grasp.checkAnim()){
      if(dir == 2){
        cyclones.add(new Projectile (12, 0, (int)(x + bounds.x), (int)(y -192), 75, 210, 80, 156, flameCol, 2));
      }else if(dir == 1){
        cyclones.add(new Projectile (-12, 0, (int)(x + bounds.x), (int)(y - 192), 75, 210, 80, 156, flameCol, 1));  
      }
     reShape(300, 192, 192, 192);
     grapple = false;
     free = true;
    }
   }else if(flying){
    flightCatch.tick();
    flightCatch2.tick();
    adjust(330, 240);

    if(flightCatch.checkAnim()){
      if(dir == 2){
        cyclones.add(new Projectile (12, 0, (int)(x + bounds.x), (int)(y -192), 75, 210, 80, 156, flameCol, 2));
      }else if(dir == 1){
        cyclones.add(new Projectile (-12, 0, (int)(x + bounds.x), (int)(y), 75, 210, 80, 156, flameCol, 1));  
      }
     reShape(330, 240, 192, 192);
     grapple = false;
     free = true;
    }  
   }
   
    if(stun || silenced){
      grasp.index = 0;
      grasp2.index = 0;
      grapple = false;
      if(!flying){
      reShape(300, 192, 192, 192);
      }else{
      reShape(330, 240, 192, 192);      
      }
      return;
    }
  }
  
  @Override
  public void A3(){
    dash.tick();
    dash2.tick();
    free = false;

    adjust(384, 192);
    
    if(flying){
    if(dir == 2 && dash.index >= 3){
      x += 10;
    }else if(dir == 1 && dash.index >= 3){
     x -= 10; 
    }
    }
    
    if(dash.checkAnim()){
      rangedCheck((int)(x), (int)(y) + 100, 240, 120, 80, 0, 0);
      if(dir == 1){
       x -= 192; 
      }else{
       x += 192; 
      }
      free = true;
      fireDash = false;
      reShape(384, 192, 192, 192);
    }
  }
  
  @Override
  public void A4(){
    ultiCast.tick();
    ultiCast2.tick();
    adjust(192, 384);
    if(ultiCast.checkAnim()){
     fireCast = false;
     flying = true;
     free = true;
     reShape(192, 300, 192, 192);
    }
    
    if(stun || silenced){
      ultiCast.index = 0;
      ultiCast2.index = 0;
      flying = true;
      fireCast = false;
      reShape(192, 192, 192, 384);
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
      g.drawImage(Assets.taAbils[0], 368, 782, 32, 32, null);
    }else{
      g.drawImage(Assets.taAbils[1], 368, 782, 32, 32, null);
    }
    if(abilities[1] == cooldowns[1]){
      g.drawImage(Assets.taAbils[4], 425, 782, 32, 32, null);      
    }else{
      g.drawImage(Assets.taAbils[5], 425, 782, 32, 32, null);
    }
    if(abilities[3] == cooldowns[3]){
      g.drawImage(Assets.taAbils[6], 481, 782, 32, 32, null);      
    }else{
      g.drawImage(Assets.taAbils[7], 481, 782, 32, 32, null);
    }
    if(abilities[2] == cooldowns[2]){
      g.drawImage(Assets.taAbils[2], 537, 782, 32, 32, null);
    }else{
      g.drawImage(Assets.taAbils[3], 537, 782, 32, 32, null);
    } 
    
    if(silenced){
     g.drawImage(Assets.silenced, 368, 782, 32, 32, null);
     g.drawImage(Assets.silenced, 425, 782, 32, 32, null);
     g.drawImage(Assets.silenced, 537, 782, 32, 32, null);
    }else{
     
    }
    
    for(int i = 0; i < lFire.size(); i++){
      lFire.get(i).render(g);
    }       
    for(int i = 0; i < spears.size(); i++){
      spears.get(i).render(g);
    }    
    for(int i = 0; i < mFire.size(); i++){
     mFire.get(i).render(g);
    }    
    for(int i = 0; i < cyclones.size(); i++){
      cyclones.get(i).render(g);
    }
        
    if(lanceDet){
      g.drawImage(explode.getCurrentFrame(), (int) (spears.get(0).projX - 105), (int) (spears.get(0).projY - 105), 420, 420,  null); 
    } 
    
    if(flying){
     spearRange = 0;
      if(abilities[2] >= 16){
     spearRange = 350;
        flying = false;
      }
    }
    
    if(blasted){
      blast.tick();
      blast2.tick();
      if(dir == 1){
      g.drawImage(blast2.getCurrentFrame(), (int)(x) - 104, (int)(y) - 20, 192, 192, null);
      }else if(dir == 2){
        g.drawImage(blast.getCurrentFrame(), (int)(x) + 104, (int)(y) - 20, 192, 192, null);     
      }
      if(blast.checkAnim()){
        blasted = false;
        free = true;
    }
    
    }
    if(stun && dir == 1){
      g.drawImage (stunned.getCurrentFrame(),(int) (x),(int) (y) , width, height, null); 
    }else if(stun && dir == 2){      
      g.drawImage (stunned2.getCurrentFrame(),(int) (x),(int) (y) , width, height, null); 
    }else{
      g.drawImage (getCurrentAnimationFrame(),(int) (x),(int) (y) , width, height, null); 
    }
    
    g.setColor(Color.red);
    g.fillRect (44, 804, onFire, 8);   
  }
  
  private BufferedImage getCurrentAnimationFrame(){ //decides which animation to tick to the screen
    
    if(stun && dir == 1){
      return stunned.getCurrentFrame();
    }else if(stun && dir == 2){
      return stunned2.getCurrentFrame();      
    }
    
    // attacks
    if (!attackLight.checkAnim() && LA && dir == 1 && !flying){
      return attackLight.getCurrentFrame();
    }else if (!attackLight.checkAnim() && LA && dir == 2 && !flying){
      return attackLight2.getCurrentFrame();
    }else if (!attackHeavy.checkAnim() && HA && dir == 1 && !flying){
      return attackHeavy.getCurrentFrame();
    }else if (!attackHeavy.checkAnim() && HA && dir == 2 && !flying){
      return attackHeavy2.getCurrentFrame();
    }
    
    if (!flightAttack2.checkAnim() && LA && dir == 1 && flying){
        return flightAttack2.getCurrentFrame();
      }else if (!flightAttack2.checkAnim() && LA && dir == 2 && flying){
        return flightAttack.getCurrentFrame();
      }else if (!flightAttack2.checkAnim() && HA && dir == 1 && flying){
        return flightAttack2.getCurrentFrame();
      }else if (!flightAttack2.checkAnim() && HA && dir == 2 && flying){
        return flightAttack.getCurrentFrame();
      }
    
    //abilities
    else if(lance && dir == 1 && !flying){
      return spearCast.getCurrentFrame();
    }else if(lance && dir == 2 && !flying){
      return spearCast2.getCurrentFrame();
    }
    
    else if(lance && dir == 1 && flying){
     return flightLance.getCurrentFrame();
    }else if(lance && dir == 2 && flying){
     return flightLance2.getCurrentFrame();
    }
    
    else if(grapple && dir == 1 && !flying){
      return grasp.getCurrentFrame();
    }else if(grapple && dir == 2 && !flying){
      return grasp2.getCurrentFrame();
    }
    
    else if(grapple && dir == 1 && flying){
     return flightCatch.getCurrentFrame();
    }else if(grapple && dir == 2 && flying){
     return flightCatch2.getCurrentFrame();
    }
    
    else if(fireDash && dir == 1){
      return dash2.getCurrentFrame();
    }else if(fireDash && dir == 2){
      return dash.getCurrentFrame();
    }
    
    else if(fireCast && dir == 1){
      return ultiCast.getCurrentFrame();
    }else if(fireCast && dir == 2){
      return ultiCast2.getCurrentFrame();
    }
    
    // movement    
    else if(hit && dir == 1){
      return flinch.getCurrentFrame();
    }else if(hit && dir == 2){
      return flinch2.getCurrentFrame(); 
    }
    
    else if (handler.getKeyManager().left && playerNum == 1 || handler.getKeyManager().left2 && playerNum == 2){
        dir = 1;
        if(!flying){
        return left.getCurrentFrame(); 
        }else{
        return flightNeutral2.getCurrentFrame();  
        }
      }else if (handler.getKeyManager().right && playerNum == 1|| handler.getKeyManager().right2 && playerNum == 2){
        dir = 2;
        if(!flying){
        return right.getCurrentFrame(); 
        }else{
        return flightNeutral.getCurrentFrame();  
        }
      }else if (handler.getKeyManager().down && playerNum == 1 && !flying || handler.getKeyManager().down2 && playerNum == 2 && !flying){
        return down.getCurrentFrame();
      }else if (handler.getKeyManager().up && playerNum == 1 && !flying || handler.getKeyManager().up2 && playerNum == 2 && !flying){
        return up.getCurrentFrame();
      }else{
       if(dir == 1 && flying){
        return flightNeutral2.getCurrentFrame(); 
       }else if(dir == 2 && flying){
        return flightNeutral.getCurrentFrame();  
       }else if (dir == 2 && !flying){
             return neutral2.getCurrentFrame(); 
       }else{
        return neutral.getCurrentFrame();
       }
      }
      
    }
  }
  