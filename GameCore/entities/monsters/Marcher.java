//this is a monster which shoots lasers. It has slightly different attributes than the other monsters.
package GameCore.entities.monsters;

import java.awt.Graphics;
import java.util.*;
import java.awt.image.BufferedImage;

import GameCore.*;
import GameCore.gfx.*;
import GameCore.entities.*;
import GameCore.heroes.Projectile;

public class Marcher extends LiveEntity {
  
  private Animation move, attack, laserProj, neutral;//animations
  private ArrayList<Projectile> beams;
  private int initX, walkDist;
  private boolean walking = true;
  
  //constructor
  public Marcher(Handler handler, float x, float y, int width, int height, float hpBonus){  
    super(handler, x, y, width, height, hpBonus);
    flying = true;
    health = 100 * hpBonus;
    maxHealth = 100 * hpBonus;
    healthMod = health / 120;
    
    bounds.x = 64;
    bounds.y = 96;
    bounds.width = 80;
    bounds.height = 70;//instance variables
    speed = 1.4f;
    maxSpeed = 1.4f;
    
    walkDist = (int)(Math.random() * 550) + 350;
    initX = (int)(x);
    
    beams = new ArrayList<Projectile>();      
    
    move = new Animation(300, Assets.maN);
    neutral = new Animation(200, Assets.maS);
    attack = new Animation(100, Assets.maA);
    laserProj = new Animation(100, Assets.laser1);
  }
  
  //ticks the monster
  @Override
  public void tick(){
    projCheck(beams, 2, 0, 0);
    move.tick();
    neutral.tick();
    effectCheck(timeStack, affectedBy);
    rangedBehaviour();
    inBounds();
  }
  
  @Override 
  public void render(Graphics g){
    
    for(int i = 0; i < beams.size(); i++){
      beams.get(i).render(g);
    }       
    
    //g.setColor(Color.green);
    //g.fillRect((int) (x + bounds.x * 3/4), (int)(y + bounds.y / 2), bounds.width * 2, bounds.height * 2); 
    //g.setColor (Color.red);
    //g.fillRect ((int) (x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height); 
    
    g.drawImage(getCurrentAnimationFrame(), (int)(x), (int)(y), width, height, null);
  }
  
  //attack method. shoots lasers.
  @Override 
  public void attack(){
    attacking = true;
    attack.tick();
    
    if(attack.checkAnim()){
      attacking = false;//shoots projectiles 
    beams.add(new Projectile (-28, 0, (int)(x), (int)(y + bounds.y) - 10, 10, 4, 0, 10, laserProj, 1));
    }
  }
  
  //returns the proper animation for the monster
  public BufferedImage getCurrentAnimationFrame(){
    if(attacking){
      return attack.getCurrentFrame();
    }else if(walking){
      return move.getCurrentFrame();
    }else{
      return neutral.getCurrentFrame();
    }  
  }
  
  //makes the monster walk on the screen
  private void rangedBehaviour(){
    if(x <= (initX - walkDist)){
      walking = false;
      attack();
    }else{
     walking = true; 
     x -= speed;
     move();
    }
  }
  
}