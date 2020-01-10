//Sephira is another playable character in the game. This class keeps track of the characters hp, abilities, attacks, and graphics. 
// It is also an extension of the Player class.

package GameCore.heroes;

import GameCore.*;
import GameCore.States.GameState;
import GameCore.entities.*;
import GameCore.gfx.*;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

public class Sephira extends Player{

  private boolean blink = false, blinkEnd = false, daggerThrow = false, daggerHit = false, songStart = false, song = false, songStrike = false, dsStrike = false, counter = false, slice = false;
  private int blinkRange = 400, dashCount = 0, hits = 0;
  private Rectangle pb = new Rectangle ();
  private Animation [] combos = new Animation [4];
  
  public Animation neutral, neutral2, left, right, down, up, attackLight, attackLight2, attackLightCoS, attackLightCoS2, counterStance, counterStance2, counterAttack, counterAttack2, blinkInit, blinkInit2, blinkFinal, blinkFinal2, blinkStrike, blinkStrike2, 
    decept, decept2, tdProj, tdProj2, dagdrop, dagdrop2, songCast, songCast2, rohar, rohar2, combo, flinch, flinch2, stunned, stunned2;
  
  private Projectile kunai;
  
  private ArrayList<Projectile> daggers;
  
  public Sephira(Handler handler, float x, float y) { 
    super (handler, x , y, Player.defaultWidth, Player.defaultHeight); 
    
    bounds.x = 64;
    bounds.y = 88;
    bounds.width = 60;
    bounds.height = 60;
    
    neutral = new Animation (1000, Assets.sephN); 
    neutral2 = new Animation (1000,Assets.sephN2);
    left = new Animation (100, Assets.sephML);
    right = new Animation (100, Assets.sephMR);
    down = new Animation (100, Assets.sephMD);
    up = new Animation (100, Assets.sephMU);
    
    attackLight = new Animation (90, Assets.sephLA);
    attackLight2 = new Animation (90, Assets.sephLA2);
    
    counterStance = new Animation(110, Assets.sephCS);    
    counterStance2 = new Animation(110, Assets.sephCS2);
    counterAttack = new Animation(100, Assets.sephCA);    
    counterAttack2 = new Animation(100, Assets.sephCA2);
    
    blinkInit = new Animation (75, Assets.sephBI);
    blinkInit2 = new Animation (75, Assets.sephBI2);
    blinkFinal = new Animation (75, Assets.sephBF);
    blinkFinal2 = new Animation (75, Assets.sephBF2);
    blinkStrike = new Animation(70, Assets.sephBS);
    blinkStrike2 = new Animation(70, Assets.sephBS2);
    
    decept = new Animation (120, Assets.sephDT);
    decept2 = new Animation (120, Assets.sephDT2);
    tdProj = new Animation (100, Assets.sephDag);
    tdProj2 = new Animation (100, Assets.sephDag2);
    dagdrop = new Animation (100, Assets.sephDA2);
    dagdrop2 = new Animation (100, Assets.sephDA);
    
    combo = new Animation(100, Assets.sephSnD);

    combos[0] = new Animation(80, Assets.sephSnD2);
    combos[1] = new Animation(80, Assets.sephSnD3);
    combos[2] = new Animation(80, Assets.sephSnD4);
    combos[3] = new Animation(80, Assets.sephSnD5);
    
    songCast = new Animation (80, Assets.sephSong);
    songCast2 = new Animation (80, Assets.sephSong2);
    
    rohar = new Animation (100, Assets.sephD2);
    rohar2 = new Animation (100, Assets.sephD);
    
    flinch = new Animation (100, Assets.sephF);
    flinch2 = new Animation (100, Assets.sephF2);
    stunned = new Animation (200, Assets.sephS);
    stunned2 = new Animation (200, Assets.sephS2);

    daggers = new ArrayList<Projectile>();
    kunai = new Projectile (20, 0, (int)(x), (int)(y), 40, 20, 96, 64, tdProj, 1);
    width = 192;
    height = 192;
    
    health = 430;
    maxHealth = 430;
    healthMod = health / 300;
    speed = 4.8f;
    maxSpeed = 4.8f;
    
    cooldowns[0] = 3; //3
    cooldowns[1] = 3; //3
    cooldowns[2] = 16; //23
    cooldowns[3] = 4;
    cooldowns[4] = 1; //1
    cooldowns[5] = 3; //3
  }
  
  @Override
  public void hurt(float amt){
    if(HA){
      counter = true;
      counterStance.index = 0;
      counterStance2.index = 0;
      if(dir == 1){
        x += 90;
      }else if(dir == 2){
        x -= 90;
      }
      parry();
    }else{
    health -= amt;
    hit = true;
    }
    if (health <= 0){
      alive = false;
      die();
    }
  }
  
    @Override 
  public void heal(float amt){
    if (health >= maxHealth){
     health = maxHealth;
    }else{
     health += amt; 
    }
    
    if (health >= maxHealth){
      health = maxHealth;
    }
  }

  @Override
  public void die(){
    alive = false;
    GameState.end = true;
  }
  
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
  
  @Override 
  public void tick () {
    inBounds(); 
    tickCooldowns();

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
    tdProj.tick();
    tdProj2.tick();
    effectCheck(timeStack, affectedBy); 
    
    for(int i = 0; i < daggers.size(); i++){
      daggers.get(i).tick();
      
      pb.x = daggers.get(i).projX + kunai.inX;
      pb.y = daggers.get(i).projY + kunai.inY;
      pb.width = daggers.get(i).projWidth;
      pb.height = daggers.get(i).projHeight;  
        
      for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this))
          continue;
        if(e.getHitbox(0, 0).intersects(pb) && !(e instanceof Player)){ 
          dagdrop.index = 0;
          dagdrop2.index = 0;
          e.hurt(65);
          if(daggers.get(i).speedX < 0){
            x = e.getX() - e.getWidth() * 0.5f;
            y = e.getY() - e.getHeight() * 0.75f; 
          }else if(daggers.get(i).speedX > 0){            
            x = e.getX();
            y = e.getY() - e.getHeight() * 0.75f; 
          }else if(daggers.get(i).projBounds()){
           daggers.remove(daggers.get(daggers.size() - 1));           
          }else{
            
          }
          decept.index = 0;
          daggerHit = true;
          daggerThrow = false;
          daggers.remove(daggers.get(daggers.size() - 1));
          return;
        }
      }
    }
    
    if (free && !stun){
    //Moving
      getInput();
      move();
      if (handler.getKeyManager().attackLight && !song && playerNum == 1 && checkCooldowns(4) && !dsStrike){
        LA = true;
        free = false;
        abilities[3] = 0;
      }
      if (handler.getKeyManager().attackHeavy && !song && playerNum == 1 && checkCooldowns(5)){
        HA = true;
        free = false;
        abilities[4] = 0;
      }
      if(handler.getKeyManager().attackLight && song && playerNum == 1 && checkCooldowns(4) && !dsStrike){
        songStrike = true;
      }
      if (handler.getKeyManager().a1 && checkCooldowns(0) && playerNum == 1){
        blink = true;
        free = false;
        abilities[0] = 0;
      }
      if (handler.getKeyManager().a2 && checkCooldowns(1) && playerNum == 1){
       daggerThrow = true;
       free = false;
       if (dir == 1){
         daggers.add(new Projectile (-18, 0, (int)(x), (int)(y + bounds.y * 0.25), 30, 15, 96, 64, tdProj, 1));
         daggers.add(new Projectile (-18, 7, (int)(x), (int)(y + bounds.y * 0.25), 30, 15, 96, 64, tdProj, 1));
         daggers.add(new Projectile (-18, -7, (int)(x), (int)(y + bounds.y * 0.25), 30, 15, 96, 64, tdProj, 1));
       }else if(dir == 2){
         daggers.add(new Projectile (18, 0, (int)(x + bounds.x), (int)(y + bounds.y * 0.25), 40, 20, 96, 64, tdProj2, 2)); 
         daggers.add(new Projectile (18, 7, (int)(x + bounds.x), (int)(y + bounds.y * 0.25), 40, 20, 96, 64, tdProj2, 2)); 
         daggers.add(new Projectile (18, -7, (int)(x + bounds.x), (int)(y + bounds.y * 0.25), 40, 20, 96, 64, tdProj2, 2)); 
       }
       abilities[1] = 0;
      }
      if (handler.getKeyManager().a3 && checkCooldowns(2) && playerNum == 1){
       songStart = true;
       free = false;
       abilities[2] = 0;
      }
      if(handler.getKeyManager().a4 && playerNum == 1 && checkCooldowns(3)){
        slice = true;
        abilities[3] = 0;
      }
    }else if (!free){
       
    }
    
    if (LA){
      LightAttack(); 
    }
    if (HA){
      HeavyAttack();
    }
    if(counter){
      parry();
    }
    if (blink){    
     A1(); 
    }else if (blinkEnd){
     if(dsStrike){
     blinkStrike.tick();
     blinkStrike2.tick();
     }else if(!dsStrike){
      blinkFinal.tick();
      blinkFinal2.tick();
     }
        if(dsStrike){
         if(blinkStrike.checkAnim()){
          hitCheck(90, 70, 50); 
                blinkRange = 400;
                free = true;
                blinkEnd = false;
                dsStrike = false;
                reShape(300, 210, 192, 192);
         }
        }else{ 
         if(blinkFinal.checkAnim()){
         blinkRange = 400;
         free = true;
         blinkEnd = false;
         dsStrike = false;
         reShape(300, 210, 192, 192);
         }
        }
    }   
    if (daggerThrow){
      A2(); 
    }else if(daggerHit){
      dagdrop.tick();
      dagdrop2.tick();
      adjust(270, 345);
      if(dagdrop.checkAnim()){
     y+= height * 0.30;
     if(dir == 1){
     x -= 40;
     }else if(dir == 2){
     x += 40; 
     }
        pb.x = 0;
        pb.y = 0;
        daggerHit = false;
        free = true;
        reShape(270, 345, 192, 192);       
      }
    }
    if (songStart){
     A4(); 
    }else if(songStrike){
      dash();
    }
    if(slice){
      A3();
    }
    if(hit && flinch.checkAnim()){
     hit = false; 
    }
  }
  
  @Override
  public void LightAttack(){ 
    attackLight.tick();  
    attackLight2.tick();
    adjust(210, 210);    
    free = false;
    
    if (attackLight.checkAnim()){
      LA = false;
      free = true;
      reShape(210, 210, 192, 192);
      hitCheck(40, 50, 35);
    }
  }
  
  @Override
  public void HeavyAttack(){
    counterStance.tick();
    counterStance2.tick(); 
    free = false;
    
    if (counterStance.checkAnim()){
     HA = false;
     free = true;
      }
    }
  
  private void parry(){
   free = false;
   HA = false;
   counterAttack.tick(); 
   counterAttack2.tick();
   adjust(600, 450);
   
   if(counterAttack.checkAnim()){
   rangedCheck((int)(x), (int)(y) + 100, 480, 200, 90, 2, 2);
   if(dir == 1){
     x -= 600;
   }else if(dir == 2){
     x += 600;
   }
   free = true;
   counter = false;
   reShape(600, 450, 192, 192);
   }
  }
  
  @Override
  public void A1(){
    free = false;
    casting = true;
    blinkInit.tick();
    blinkInit2.tick();
    if(handler.getKeyManager().attackLight && !song && playerNum == 1){
     dsStrike = true;
    }
    adjust(300, 210);
    
    if (blinkInit.checkAnim()){
      if (dir == 1){
        x -= blinkRange;
      }else if (dir == 2){
        x+= blinkRange;
      }
      heal(50);
      blink = false;
      blinkEnd = true;
    }
    if(stun || silenced){
      blink = false;
      blinkEnd = false;
      dsStrike = false;
      blinkInit.index = 0;
      blinkInit2.index = 0;
      adjust(192, 192);
      return;
    } 
  }
  
  @Override
  public void A2(){
    free = false;
    casting = true;
    decept.tick();
    decept2.tick();
    adjust(270, 270);
    
    if (decept.checkAnim()){
      daggerThrow = false;
      free = true;
      reShape(270, 270, 192, 192);
      daggers.clear();
    }
    
    if(stun || silenced){
      daggerThrow = false;
      daggerHit = false;
      decept.index = 0;
      decept2.index = 0;
      return;
    } 
  }
  
  public void A3(){
    free = false;
    combos[0].tick();

    adjust(216, 216);
    for(int i = 0; i <= 3; i++){
    if(combos[hits].checkAnim()){
      if(hitCheck(85, 80, 115) && hits < 3){ 
        hits++;
        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
          if(e instanceof LiveEntity){ 
            x = e.getX() - 20;
            y = e.getY();
            if(x < e.getX()){
              dir = 2;
            }else if(x <= e.getX()){
              dir = 1;
            }
            break;
          }
        }
      }else{
        free = true;
        hits = 0;
        slice = false;
        reShape(216, 216, 192, 192);   
        break;
      }
    }
    combos[hits].tick();
    }
    
    if(stun || silenced){
      slice = false;
      combo.index = 0;
      reShape(216, 216, 192, 192);
      return;
    } 
  }
  
  @Override
  public void A4(){
   free = false;
   songCast.tick();
   songCast2.tick();
   adjust(210, 225);
   dashCount = 0;
   if(songCast.checkAnim()){
    songStart = false;
    song = true;
    free = true;
    reShape(210, 225, 192, 192);
   }
   
   if(stun || silenced){
     songStart = false;
     song = false;
     songCast.index = 0;
     songCast2.index = 0;
     adjust(192, 192);
     return;
   } 
  }
  
  public void dash(){
    free = false;
    rohar.tick();
    rohar2.tick();
    adjust(600, 450);
    
    if(rohar.checkAnim()){
      rangedCheck((int)(x), (int)(y) + 100, 500, 150, 150, 1, 4);
      if(dir == 1){
        x -= 600;
      }else if(dir == 2){
        x += 600;
      }
      dashCount ++;
      if(dashCount >= 5){
        song = false;
      }
      reShape(600, 450, 192, 192);
      free = true;
      songStrike = false;
    }
  }
  
  @Override
  public void render (Graphics g){  
    
    if(abilities[0] == cooldowns[0]){
      g.drawImage(Assets.sephAbils[0], 368, 782, 32, 32, null);
    }else{
      g.drawImage(Assets.sephAbils[1], 368, 782, 32, 32, null);
    }
    if(abilities[1] == cooldowns[1]){
      g.drawImage(Assets.sephAbils[2], 425, 782, 32, 32, null);      
    }else{
      g.drawImage(Assets.sephAbils[3], 425, 782, 32, 32, null);
    }
    if(abilities[3] == cooldowns[3]){
      g.drawImage(Assets.sephAbils[6], 481, 782, 32, 32, null);      
    }else{
      g.drawImage(Assets.sephAbils[7], 481, 782, 32, 32, null);
    }
    if(abilities[2] == cooldowns[2]){
      g.drawImage(Assets.sephAbils[4], 537, 782, 32, 32, null);
    }else{
      g.drawImage(Assets.sephAbils[5], 537, 782, 32, 32, null);
    }
    //481
    if(silenced){
     g.drawImage(Assets.silenced, 368, 782, 32, 32, null);
     g.drawImage(Assets.silenced, 425, 782, 32, 32, null);
     g.drawImage(Assets.silenced, 537, 782, 32, 32, null);
    }else{
     
    }
    
    
    //g.setColor(Color.green);
    //g.fillRect((int) (x + bounds.x * 3/4), (int)(y + bounds.y / 2), bounds.width * 2, bounds.height * 2); 
    //g.setColor (Color.red);
    //g.fillRect ((int) (x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height); 
    
   g.drawImage (getCurrentAnimationFrame(),(int) (x),(int) (y) , width, height, null);  
    
    for(int i = 0; i < daggers.size(); i++){
      daggers.get(i).render(g);
    }
    
  }
  
  private BufferedImage getCurrentAnimationFrame(){
    
    if(stun && dir == 1){
      return stunned.getCurrentFrame();
    }else if(stun && dir == 2){
      return stunned2.getCurrentFrame();      
    }
    
    // attacks
    if (!attackLight.checkAnim() && LA && dir == 1){
      return attackLight.getCurrentFrame();
    }else if (!attackLight.checkAnim() && LA && dir == 2){
      return attackLight2.getCurrentFrame();
    }else if (!counterStance2.checkAnim() && HA && dir == 1){
      return counterStance.getCurrentFrame();
    }else if (!counterStance.checkAnim() && HA && dir == 2){
      return counterStance.getCurrentFrame();
    }
    
    else if(!counterAttack.checkAnim() && counter && dir == 1){
      return counterAttack.getCurrentFrame();
    }else if(!counterAttack.checkAnim() && counter && dir == 2){
      return counterAttack2.getCurrentFrame();
    }
    
    //abilities
    
    // blink
    else if(!blinkInit.checkAnim() && blink && dir == 1){
      return blinkInit.getCurrentFrame();
    }else if(!blinkInit2.checkAnim() && blink && dir == 2){
      return blinkInit2.getCurrentFrame();
    }else if (blinkEnd  && !dsStrike && dir == 1){
     return blinkFinal.getCurrentFrame();
    }else if (blinkEnd && !dsStrike && dir == 2){
     return blinkFinal2.getCurrentFrame(); 
    }else if (blinkEnd &&  dsStrike && dir == 1){
     return blinkStrike.getCurrentFrame(); 
    }else if (blinkEnd && dsStrike && dir == 2){
     return blinkStrike2.getCurrentFrame();      
    }
    
    //twisting deception
    else if(!decept.checkAnim() && daggerThrow && dir == 1){
      return decept.getCurrentFrame();
    }else if (!decept.checkAnim() && daggerThrow && dir == 2){
      return decept2.getCurrentFrame();
    }else if (!dagdrop.checkAnim() && daggerHit && dir == 1){
      return dagdrop.getCurrentFrame();
    }else if (!dagdrop.checkAnim() && daggerHit && dir == 2){
      return dagdrop2.getCurrentFrame();
    }
    
    //slicendice
    else if(!combos[hits].checkAnim() && slice){
      return combos[hits].getCurrentFrame();
    }
    
    //song of roharen
    else if (!songCast.checkAnim() && songStart && dir == 1){
      return songCast.getCurrentFrame();
    }else if(!songCast.checkAnim() && songStart && dir == 2){
     return songCast2.getCurrentFrame(); 
    }
    
    else if (!rohar.checkAnim() && songStrike && dir == 1){
      return rohar.getCurrentFrame();
    }else if (!rohar.checkAnim() && songStrike && dir == 2){
      return rohar2.getCurrentFrame();
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

  
}
