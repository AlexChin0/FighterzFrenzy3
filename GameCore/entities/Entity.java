///This is the parent class for all entities in the game. It holds all methods responsible for making up the stats of the entity.
package GameCore.entities;

import GameCore.*;
import GameCore.heroes.Player;

import java.awt.*;

public abstract class Entity {
  
  protected Handler handler;
  protected int width, height, dir = 1;
  protected float x, y, health, healthMod, maxHealth;
  public static final float DEFAULT_HEALTH = 100;//all default instance variables
  protected boolean alive = true;
  public boolean slowed = false, free = false, stun = false, silenced = false;
  public float speed, maxSpeed;
  protected Rectangle bounds;
  public int playerNum;
  
  public long effectCount = 0;
  public int affectedBy = 0; 
  public long timeStack = 0;
  
  protected static final int defaultHP = 500;
  public static final int defaultWidth = 64;
  public static final int defaultHeight = 64;
  public static final float defaultSpeed = 5.0f;
  
  public Entity(Handler handler, float x, float y, int width, int height) {
    this.handler = handler;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    health = DEFAULT_HEALTH;

  }
  
  //returns the hitbox of the entity
  public Rectangle getHitbox (float xOffset, float yOffset){
    return new Rectangle ((int)(x + bounds.x + xOffset), (int)(y + bounds.y / 2 + yOffset), bounds.width * 2, bounds.height * 2); 
  }
  
  //returns the collision box of the entity
  public Rectangle getCollisionBounds (float xOffset, float yOffset){
   return new Rectangle ((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height); 
  }
  
  //returns the entity to default state
  public void freedom(){
 timeStack = 0;
 affectedBy = 0;
 speed = maxSpeed;
 stun = false;
 slowed = false;
 silenced = false;
 free = true;
  }
  
  //method which adjusts the shape of the player when they perform actions
  public void adjust(int widthAdj, int heightAdj){
    if (dir == 1){
      x = x - (int)((widthAdj - width));
      y = y - (int)((heightAdj - height) / 2);
      width = widthAdj;
      height = heightAdj;
    }else if (dir == 2){
      y = y - (int)((heightAdj - height) / 2);
      width = widthAdj;
      height = heightAdj;        
    }
  }
  
  //method which returns the shape of the player to normal when actions are complete
  public void reShape(int widthAdj, int heightAdj,int ogWidth,int ogHeight){
    if (dir == 1){
      width = ogWidth;
      height = ogHeight;
      x = x + (int)(widthAdj - width);
      y = y + (int)((heightAdj - height) / 2);
    }else if (dir == 2){     
      width = ogWidth;
      height = ogHeight;
      y = y + (int)((heightAdj - height) / 2);        
    }
  }

  //returns true when an entity collides with another
  public boolean checkEntityCollisions (float xOffset, float yOffset){
   for (Entity e : handler.getWorld().getEntityManager().getEntities()){
    if (e.equals(this)){
     continue;
    }else if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e instanceof Player && e.playerNum != 0)
      return true;
   }
   return false;
  }
  
  //a method which checkss an attack at a range
  public int rangedCheck(int x, int y, int width, int height, int dmg, int eIndex, long time){
     Rectangle ar = new Rectangle ();
     int count = 0;
     ar.width = width;
     ar.height = height;      
     
     ar.x = x; 
     ar.y = y;
     
     for (Entity e : handler.getWorld().getEntityManager().getEntities()){
       if (e.equals(this))
         continue;
       if(e.getHitbox(0, 0).intersects(ar)){
         if(eIndex == 1){
           e.affectedBy = 1;
           e.timeStack += time;
           e.stunned();
         }else if(eIndex == 2){
           e.speed = speed / 2;
           e.affectedBy = 2; 
           e.slowed = true;
           e.timeStack += time;
         }
         else{
           
         }
         e.hurt(dmg); 
         count++;
         continue;
       }
     }
     return count;
   }
  
  //abstract classes
  public abstract void tick();
  
  public abstract void render(Graphics g);
  
  public abstract void hurt(float amt);
  
  public abstract void heal(float amt);
  
  public abstract void stunned();
  
  public abstract void slowed(float percent);
  
  public abstract void silenced();
  
  
  public void die(){
    alive = false;
  }
  
  //getters and setters
  public float getX() {
   return x;
  }

  public void setX(float x) {
   this.x = x;
  }

  public float getY() {
   return y; 
  }

  public void setY (float y) {
   this.y = y;
  }

  public int getWidth() {
   return width;
  }

  public void setWidth (int width) {
   this.width = width;
  }

  public int getHeight() {
   return height;
  }

  public void setHeight (int height) {
   this.height = height;
  }

  public float getHealth() {
   return health;
  }
  
  public float getMaxHealth(){
   return maxHealth; 
  }

  public void setHealth (int health) {
   this.health = health;
  }
  
  public boolean isAlive(){
 return alive;
  }
  
  public float getMod(){
 return healthMod;  
  }
  
  public int getDir(){
    return 0;
  }
  
  public void setDir(int dir){
    this.dir = dir;
  }

}
