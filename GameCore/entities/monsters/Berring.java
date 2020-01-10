//this is the most basic monster. 
package GameCore.entities.monsters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameCore.*;
import GameCore.gfx.*;
import GameCore.entities.*;

public class Berring extends LiveEntity{
  
  private Animation move, attack;
  private int initBehaviour;
  
  //constructor
  public Berring(Handler handler, float x, float y, int width, int height, float hpBonus) {
    super(handler, x, y, width, height, hpBonus);
    flying = false;
    health = 80 * hpBonus;
    maxHealth = 80 * hpBonus;
    healthMod = health / 120;//instance variables
    
    bounds.x = 24;
    bounds.y = 40;
    bounds.width = 72;
    bounds.height = 48;
    speed = 2.0f;
    maxSpeed = 2.0f;
    
    initBehaviour = (int)(Math.random() * 2) + 1;
            
    move = new Animation(200, Assets.berN);
    attack = new Animation(100, Assets.berA);
  }
  
  @Override
  public void tick(){
    effectCheck(timeStack, affectedBy);
    
    move.tick();
    
    moveBehaviour(initBehaviour);
    move();
    inBounds();
  }
  
  @Override 
  public void render(Graphics g){
    
    //g.setColor(Color.green);
    //g.fillRect((int) (x + bounds.x * 3/4), (int)(y + bounds.y / 2), bounds.width * 2, bounds.height * 2); 
    //g.setColor (Color.red);
    //g.fillRect ((int) (x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height); 
    
    if(dir == 1){
    g.drawImage(getCurrentAnimationFrame(), (int)(x), (int)(y), width, height, null);
    }else if(dir == 2){
       g.drawImage(getCurrentAnimationFrame(), (int)(x) + width, (int)(y), -width, height, null);   
    }
  }
  
  //makes the monster attack on contact
  @Override
  public void attack(){
    attack.tick();
    
    if(attack.checkAnim()){
      hitCheck(50, 30, 1);
      return;
    }
  }

//returns the proper animation to the screen
  public BufferedImage getCurrentAnimationFrame(){
    if(attacking && dir == 1){
      return attack.getCurrentFrame();
    }else if(attacking && dir == 2){
      return attack.getCurrentFrame();
    }else{
      attack.index = 0;
      return move.getCurrentFrame();
    }
    
  }
  
  //inherited hurt and heal methods
  @Override
  public void hurt(float amt){
    health -= amt;
    if(health <= 0){
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
