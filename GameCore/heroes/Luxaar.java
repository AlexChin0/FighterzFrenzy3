//Luxaar is another playable character in the game. It contains the same methods and follows the same procedures as the other hero classes
package GameCore.heroes;

import GameCore.*;
import GameCore.States.GameState;
import GameCore.entities.Entity;
import GameCore.entities.StaticEntity;
import GameCore.gfx.*;
import GameCore.Launcher;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

public class Luxaar extends Player{
  
  public Animation neutral, neutral2, left, right, down, up, attackLight, attackLight2, attackHeavy, attackHeavy2, flinch, flinch2, stunned, stunned2, lightCoil, lightCoil2, sumVolley, sumVolley2, volley, volley2, ultiCast, ultiCast2, vDet, arrowAttacks, arrowAttacks2, cleave, cleave2, cleaveCast, cleaveCast2;
  private ArrayList<Projectile> lArrows;
  private ArrayList<Projectile> hArrows;
  private ArrayList<Projectile> uArrows;
  private ArrayList<Projectile> chops;
  
  private boolean shove = false, summon = false, arrowFall = false, ultiFire = false, preDet = false, detonate = false, direct = false, boost = false, lightCast = false, lightFinal = false;
  private int volleyX, volleyY, vDir = 0, pullIn = 50, chopDmg = 0;
  private int posInit= 0, posDelta = 0, posX, posY, lightDmg = 40, heavyDmg = 90; 

  public Luxaar(Handler handler, float x, float y) { 
    super (handler, x , y, Player.defaultWidth, Player.defaultHeight); 
    
    bounds.x = 64;
    bounds.y = 96; // hit box variables
    bounds.width = 60;
    bounds.height = 58;
    
    neutral = new Animation (1000, Assets.luxN);
    neutral2 = new Animation (1000,Assets.luxN2);
    left = new Animation (100, Assets.luxML);
    right = new Animation (100, Assets.luxMR);
    down = new Animation (100, Assets.luxMD); //instances of the animation class
    up = new Animation (100, Assets.luxMU);
    
    attackLight = new Animation (80, Assets.luxLA);
    attackLight2 = new Animation (80, Assets.luxLA2);
    attackHeavy = new Animation (60, Assets.luxHA);
    attackHeavy2 = new Animation (60, Assets.luxHA2);   
    
    arrowAttacks = new Animation(0, Assets.luxAR);
    arrowAttacks2 = new Animation(0, Assets.luxAR2);
    
    cleaveCast = new Animation(90, Assets.luxRS);
    cleaveCast2 = new Animation(90, Assets.luxRS2);
    cleave = new Animation(25, Assets.luxFC);
    cleave2 = new Animation(25, Assets.luxFC2);
    
    sumVolley = new Animation (85, Assets.luxSV);
    sumVolley2 = new Animation (85, Assets.luxSV2);
    volley = new Animation (60, Assets.luxV);
    volley2 = new Animation (60, Assets.luxV2);
    
    ultiCast = new Animation (110, Assets.luxUC);
    ultiCast2 = new Animation (110, Assets.luxUC2);
    
    lightCoil = new Animation(80, Assets.luxLC);
    lightCoil2 = new Animation(80, Assets.luxLC2);
    
    vDet = new Animation (100, Assets.luxVD);
    
    flinch = new Animation (100, Assets.luxF);
    flinch2 = new Animation (100, Assets.luxF2);
    stunned = new Animation (200, Assets.luxS);
    stunned2 = new Animation (200, Assets.luxS2);
    
    lArrows = new ArrayList<Projectile>();       
    hArrows = new ArrayList<Projectile>();      
    uArrows = new ArrayList<Projectile>();  
    chops = new ArrayList<Projectile>();  
    
    width = 192;
    height = 192;
    
    health = 565; //hero values
    maxHealth = 565;
    healthMod = health / 300;
    speed = 4.0f;
    maxSpeed = 4.0f;
    
    cooldowns[0] = 4; //6
    cooldowns[1] = 4; //6
    cooldowns[2] = 16; //24
    cooldowns[3] = 6;
    cooldowns[4] = 1; //1
    cooldowns[5] = 2; //2
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
    alive = false;
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
}
  
  //ticks every value for the hero
  @Override
  public void tick(){
    tickCooldowns();   
    inBounds();   
    
    projCheck(lArrows, lightDmg, 0, 0);
    projCheck(hArrows, heavyDmg, 0, 0);

    effectCheck(timeStack, affectedBy); 

     for(int i = 0; i < uArrows.size(); i++){
      uArrows.get(i).tick();
    }
     
     for(int i = 0; i < chops.size(); i++){
       Rectangle pb = new Rectangle ();
       chops.get(i).tick();
       
       chops.get(i).inY -= 6;
       chops.get(i).projHeight += 12;       
       chopDmg = chops.get(i).projHeight/4;
       
       pb.x = chops.get(i).projX + chops.get(i).inX;
       pb.y = chops.get(i).projY + chops.get(i).inY;
       pb.width = chops.get(i).projWidth;
       pb.height = chops.get(i).projHeight;
       
       if(chops.get(i).animation.checkAnim()){
         chops.remove(chops.get(chops.size() - 1));     
         chopDmg = 0;
       }
       
       for (Entity e : handler.getWorld().getEntityManager().getEntities()){
         if (e.equals(this))
           continue;
         if(e.getHitbox(0, 0).intersects(pb)){
           e.hurt(chopDmg); 
           return;
         }
         }
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
        abilities[3] = 0;
      }
      if (handler.getKeyManager().attackHeavy && playerNum == 1  && checkCooldowns(5)){
        HA = true;
        free = false;
        abilities[4] = 0;
      }
      if (handler.getKeyManager().a1 && checkCooldowns(0) && playerNum == 1){
        free = false;
        shove = true;
        abilities[0] = 0;
      }
      if (handler.getKeyManager().a2 && checkCooldowns(1) && playerNum == 1){
        free = false;
        summon = true;
        abilities[1] = 0;
      }
      if (handler.getKeyManager().a3 && checkCooldowns(2) && playerNum == 1){
        free = false;
        ultiFire = true;
        abilities[2] = 0;
      }
      if (handler.getKeyManager().a4 && checkCooldowns(3) && playerNum == 1){
        free = false;
        lightCast = true;
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
    if(shove){
      A1();
    }
    if (summon){
     A2(); 
    }
    if(lightCast){
     A3();
    }else if(lightFinal){
      lightCoil2.tick();
      if(lightCoil2.checkAnim()){
          lightFinal = false;
          free = true;
          rangedCheck((int)(x) - 48, (int)(y) - 48, 288, 288, 50, 1, 2);
      }
    }
    if(ultiFire){
     A4(); 
    } 
    if(detonate){
     vDet.tick();
     posDelta = 0;
     if(vDet.checkAnim()){
       detonate = false;
       Rectangle pb = new Rectangle ();
       uArrows.clear();
      pb.x = (int)(posX - 720 * 0.25);
      pb.y = (int)(posY - 320 * 0.4);
      pb.width = 720;
      pb.height = 390;
      
      if(direct){
      for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this))
          continue;
        if(e.getHitbox(0, 0).intersects(pb)){
          e.hurt(80); 
          e.slowed(0.5f);
          e.timeStack += 5;
          e.affectedBy = 2;
        }
        continue;
      }
     }
    }  
    }
    
    if(hit && flinch.checkAnim()){
      hit = false; 
    }
    
    if(boost && abilities[1] == 4){
     speed -= 2.0;
     lightDmg -= 15;
     heavyDmg -= 15;
     boost = false;
    }

    if(preDet){
      
    for(int i = 0; i < uArrows.size(); i++){
      Rectangle pb = new Rectangle ();
      uArrows.get(i).tick();
      
      pb.x = uArrows.get(0).projX + uArrows.get(0).inX;
      pb.y = uArrows.get(0).projY + uArrows.get(0).inY;
      pb.width = uArrows.get(0).projWidth;
      pb.height = uArrows.get(0).projHeight;
      
      for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this))
          continue;
        if(e.getHitbox(0, 0).intersects(pb)){
          e.hurt(140);
          e.stunned();
          e.timeStack += 6;
          e.affectedBy = 1;
          detonate = true;
          if(!(e instanceof StaticEntity)){
          direct = true;
          }
          preDet = false;
          posX = uArrows.get(0).projX + uArrows.get(0).inX;
          posY = uArrows.get(0).projY + uArrows.get(0).inY;
          uArrows.get(0).speedX = 0;
          break;
        }
      }    
    }
    
      posDelta = Math.abs(posInit - uArrows.get(0).projX);    
      if(posDelta >= 500 || uArrows.get(0).projX > Launcher.runWidth - 70 || uArrows.get(0).projX < 0){
       detonate = true;
       preDet = false;
       posX = uArrows.get(0).projX + uArrows.get(0).inX;
       posY = uArrows.get(0).projY + uArrows.get(0).inY;
       uArrows.get(0).speedX = 0;
      }
    }
    
  }
  
  @Override
  public void LightAttack(){
    attackLight.tick();  
    attackLight2.tick();  
    free = false;
    
    if (attackLight.checkAnim()){
      LA = false;
      free = true;
      if(dir == 2){
      lArrows.add(new Projectile (32, 0, (int)(x + bounds.x), (int)(y + bounds.y / 2), 20, 10, 48, 32, arrowAttacks2, 2));
      }else if(dir == 1){
      lArrows.add(new Projectile (-32, 0, (int)(x), (int)(y + bounds.y / 2), 20, 10, 48, 32, arrowAttacks, 1));        
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
    attackHeavy.tick();  
    attackHeavy2.tick();
    free = false;
      
    if (attackHeavy.checkAnim()){
      HA = false;
      free = true;
      if(dir == 2){
        hArrows.add(new Projectile (40, 0, (int)(x + bounds.x), (int)(y + bounds.y / 2), 40, 40, 38, 21, arrowAttacks2, 2));
      }else if(dir == 1){
        hArrows.add(new Projectile (-40, 0, (int)(x), (int)(y + bounds.y / 2), 40, 40, 38, 21, arrowAttacks, 1));        
      }
    }
    if(stun || silenced){
      HA = false;
      attackHeavy.index = 0;
      attackHeavy2.index = 0;
      return;
    }
  }
  
  @Override
  public void A1(){
    cleaveCast.tick();
    cleaveCast2.tick();
    
    if(cleaveCast.checkAnim()){
      shove = false;
      free = true;
      if(dir == 2){
        chops.add(new Projectile (38, 0, (int)(x + bounds.x), (int)(y) - 20, 25, 25, 72, 124, cleave2, 2));
      }else if(dir == 1){
        chops.add(new Projectile (-38, 0, (int)(x), (int)(y) - 20, 25, 25, 72, 124, cleave, 1));        
      }
    }   
    
    if(stun || silenced){
     cleaveCast.index = 0;
     cleaveCast2.index = 0;
     shove = false;
     return;
    }
  }
  
  @Override
  public void A2(){
    sumVolley.tick();
    sumVolley2.tick();
    
    if(sumVolley.checkAnim()){
      summon = false;
      arrowFall = true;
      if(dir == 1){
       vDir = 1; 
       volleyX = (int)(x - bounds.x * 1.75);
       volleyY = (int)(y - bounds.y / 2);
      }
      if(dir == 2){
        vDir = 2; 
        volleyX = (int)(x + bounds.x * 0.25);
        volleyY = (int)(y - bounds.y / 2);
      }      
      free = true;
    }
    
    if(stun || silenced){
      sumVolley.index = 0;
      sumVolley2.index = 0;
      summon = false;
      arrowFall = false;
      return;
    }
  }
  
  @Override 
  public void A3(){
    lightCoil.tick();
    if(lightCoil.checkAnim()){
    if(handler.getKeyManager().up){
      y -= 250;
    }else if(handler.getKeyManager().down){
      y += 250;
    }else if(handler.getKeyManager().left){
      x -= 250;
    }else if(handler.getKeyManager().right){
      x += 250;
    }
    rangedCheck((int)(x) - 48, (int)(y) - 48, 288, 288, 50, 1, 2);
      lightCast = false;
      lightFinal = true;
    }
      
  }
  
  @Override
  public void A4(){
    ultiCast.tick();
    ultiCast2.tick();
    direct = false;
    if(ultiCast.checkAnim()){
      ultiFire = false;
      free = true;
      preDet = true;
      if(dir == 2){
        uArrows.add(new Projectile (20, 0, (int)(x + bounds.x), (int)(y + bounds.y / 2), 30, 20, 38, 22, arrowAttacks2, 2));
        posInit = (int)(x + bounds.x);
      }else if(dir == 1){
        uArrows.add(new Projectile (-20, 0, (int)(x), (int)(y + bounds.y / 2), 30, 20, 38, 22, arrowAttacks, 1));  
        posInit = (int)(x);
      }
    }
    
    if(stun || silenced){
      ultiCast.index = 0;
      ultiCast2.index = 0;
      ultiFire = false;
      preDet = false;
      return;
    }
  }
  
  //renders the hero and their abilities
  @Override
  public void render (Graphics g){
//    g.setColor(Color.green);
//    g.fillRect((int) (x + bounds.x * 3/4), (int)(y + bounds.y / 2), bounds.width * 2, bounds.height * 2); 
   //g.setColor (Color.red);
    //g.fillRect ((int) (x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height); 
    
    if(abilities[0] == cooldowns[0]){
      g.drawImage(Assets.luxAbils[0], 368, 782, 32, 32, null);
    }else{
      g.drawImage(Assets.luxAbils[1], 368, 782, 32, 32, null);
    }
    if(abilities[1] == cooldowns[1]){
      g.drawImage(Assets.luxAbils[4], 425, 782, 32, 32, null);      
    }else{
      g.drawImage(Assets.luxAbils[5], 425, 782, 32, 32, null);
    }
    if(abilities[3] == cooldowns[3]){
      g.drawImage(Assets.luxAbils[6], 481, 782, 32, 32, null);      
    }else{
      g.drawImage(Assets.luxAbils[7], 481, 782, 32, 32, null);
    }
    if(abilities[2] == cooldowns[2]){
      g.drawImage(Assets.luxAbils[2], 537, 782, 32, 32, null);
    }else{
      g.drawImage(Assets.luxAbils[3], 537, 782, 32, 32, null);
    } 
    
    if(silenced){
     g.drawImage(Assets.silenced, 368, 782, 32, 32, null);
     g.drawImage(Assets.silenced, 425, 782, 32, 32, null);
     g.drawImage(Assets.silenced, 537, 782, 32, 32, null);
    }else{
     
    }
    
    for(int i = 0; i < lArrows.size(); i++){
      lArrows.get(i).render(g);
    }
    for(int i = 0; i < hArrows.size(); i++){
      hArrows.get(i).render(g);
    }       
    for(int i = 0; i < uArrows.size(); i++){
      uArrows.get(i).render(g);
    }     
    for(int i = 0; i < chops.size(); i++){
      chops.get(i).render(g);
    }     
    
    if(detonate){
     g.drawImage(vDet.getCurrentFrame(), (int) (uArrows.get(0).projX - 720 * 0.25), (int) (uArrows.get(0).projY - 320 * 0.4), 720, 320,  null); 
    } 
    
    if(arrowFall){
     volley.tick();
     volley2.tick();
     
     if(vDir == 1 && !volley.checkAnim()){
      g.drawImage(volley.getCurrentFrame(), volleyX, volleyY, 300, 300, null); 
     }else if(vDir == 2 && !volley.checkAnim()){
      g.drawImage(volley2.getCurrentFrame(), volleyX, volleyY, 300, 300, null); 
     }
     
     if(volley.checkAnim()){
      arrowFall = false; 
      
      Rectangle hb = new Rectangle ();
      hb.width = 120;
      hb.height = 120;
      
      if(vDir == 2){
        hb.x = volleyX + 175;
        hb.y = volleyY + 100;
      }else if(vDir == 1){
        hb.x = volleyX;
        hb.y = volleyY + 100;
      }
      
      if(getCollisionBounds(0f, 0f).intersects(hb)){
       boost = true;
       lightDmg += 15;
       heavyDmg += 15;
       heal(40);
       speed += 2.0;
      }
      
      if(rangedCheck(hb.x, hb.y, 120, 120, 100, 0, 0) > 0){
       knockback(250, 250, 0, -pullIn);
      }
     }     
    }
    
    if(stun && dir == 1){
      g.drawImage (stunned.getCurrentFrame(),(int) (x),(int) (y) , width, height, null); 
    }else if(stun && dir == 2){      
      g.drawImage (stunned2.getCurrentFrame(),(int) (x),(int) (y) , width, height, null); 
    }else{
      g.drawImage (getCurrentAnimationFrame(),(int) (x),(int) (y) , width, height, null); 
    }
    
    if(lightCast){
      g.drawImage(lightCoil.getCurrentFrame(), (int)(x), (int)(y) - 70, 225, 345, null);
    }else if(lightFinal){
      g.drawImage(lightCoil2.getCurrentFrame(), (int)(x), (int)(y) - 70, 225, 345, null);
    }
  }
  
  private BufferedImage getCurrentAnimationFrame(){ //decides which animation to tick to the screen
    
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
    }else if (!attackHeavy.checkAnim() && HA && dir == 1){
      return attackHeavy.getCurrentFrame();
    }else if (!attackHeavy.checkAnim() && HA && dir == 2){
      return attackHeavy2.getCurrentFrame();
    }
    
    //abilities
    else if(!cleaveCast.checkAnim() && shove && dir == 1){
      return cleaveCast.getCurrentFrame();
    }else if(!cleaveCast2.checkAnim() && shove && dir == 2){
      return cleaveCast2.getCurrentFrame();
    }
    
    else if(!sumVolley.checkAnim() && summon && dir == 1){
     return sumVolley.getCurrentFrame(); 
    }else if(!sumVolley.checkAnim() && summon && dir == 2){
     return sumVolley2.getCurrentFrame(); 
    }
    
    else if(!ultiCast.checkAnim() && ultiFire && dir == 1){
     return ultiCast.getCurrentFrame(); 
    }else if(!ultiCast.checkAnim() && ultiFire && dir == 2){
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