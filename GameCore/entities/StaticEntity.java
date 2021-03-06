//This is another child class of the entity class. It is for entities which do not move and are passive. 
package GameCore.entities;

import GameCore.*;
import java.awt.*;

public class StaticEntity extends Entity {
  
  //constructor
  public StaticEntity(Handler handler, float x, float y, int width, int height) { 
    super(handler, x, y, width, height);
    this.handler = handler;
    this.x = x;
    this.y = y;
    this.width = width; 
    this.height = height;
    health = defaultHP;
    
    bounds = new Rectangle (0, 0, width, height);//hitbox of the entity
  }
  
  
  //inherited methods
  public void tick(){
    
  }
  
  public void render(Graphics g){

  }
  
  public void stunned(){
   
  }
  
  public void slowed(float percent){
   
  }
  
  public void silenced(){
   
  }
  
  public void hurt(float amt){
   health -= amt;
   System.out.println(health);
   if(health <= 0){
    die();
   }
  }

  public void heal(float amt){
   health+= amt;
   if(health >= maxHealth){
    health = maxHealth;
   }
  }
  
}
