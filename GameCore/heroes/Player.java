//This player class is an abstract class. It is an incomplete class which is the base for all playable characters. It ticks all
//cooldowns for abilties, get input from the keyboard, and checks for map boundary collisions.

package GameCore.heroes; 
import GameCore.*;                           
import GameCore.entities.*;

import java.util.*;
import GameCore.worlds.Tiles.*;
import GameCore.heroes.Projectile;

import java.awt.*;
  
public abstract class Player extends Entity{

protected Projectile projectile;

protected Boolean LA = false, HA = false, hit = false;
public Boolean free = true, casting = false, stun = false, slowed = false, silenced = false, falling = false, flying = false;//boolean states of player

protected float xMove, yMove;

protected long abTimer, lastUse;
protected long [] abilities = new long[6];//array of player ability of cooldowns
protected long [] cooldowns = new long[6];

ArrayList<Long> a1tick = new ArrayList<Long>();
ArrayList<Long> a2tick = new ArrayList<Long>();
ArrayList<Long> a3tick = new ArrayList<Long>();//array for player ability cooldowns
ArrayList<Long> a4tick = new ArrayList<Long>();
ArrayList<Long> lightTick = new ArrayList<Long>();
ArrayList<Long> heavyTick = new ArrayList<Long>();

ArrayList<Long> effectTimer = new ArrayList<Long>();
 
public Player (Handler handler, float x, float y, int width, int height){ //class constructor
  super(handler, x, y, width, height);
   
  this.handler = handler;
  this.x = x;
  this.y = y;
  this.width = width; //sets the characters values
  this.height = height;
  health = defaultHP;
  
  speed = defaultSpeed;
  maxSpeed = defaultSpeed;
  
  xMove = 0;
  yMove = 0;
  
  bounds = new Rectangle (0, 0, width, height);
  
  abTimer = 0;
  lastUse = System.currentTimeMillis();
}

//ticks the timer of all abilities
public void tickCooldowns(){
  abTimer += System.currentTimeMillis() - lastUse;
  lastUse = System.currentTimeMillis(); //clock timer for abilities
  
  long toSeconds = (int)(abTimer / 1000);
  if (!a1tick.contains(toSeconds)){
    a1tick.add(toSeconds);
    if (abilities[0] < cooldowns[0]){
    abilities[0] ++;
    }
  }
  
  if (!a2tick.contains(toSeconds)){
    a2tick.add(toSeconds);
    if (abilities[1] < cooldowns[1]){
      abilities[1] ++;
    }
  }
  
  if (!a3tick.contains(toSeconds)){
    a3tick.add(toSeconds);
    if (abilities[2] < cooldowns[2]){
      abilities[2] ++;
    }
  }
  
  if (!a4tick.contains(toSeconds)){
    a4tick.add(toSeconds);
    if (abilities[3] < cooldowns[3]){
      abilities[3] ++;
    }
  }
  
  if(!lightTick.contains(toSeconds)){
   lightTick.add(toSeconds);
   if (abilities[4] < cooldowns[4]){
    abilities[4] ++; 
   }
  }
  
  if(!heavyTick.contains(toSeconds)){
    heavyTick.add(toSeconds);
    if (abilities[5] < cooldowns[5]){
      abilities[5]++; 
    }
  }
}

//the following three methods are player states which are called when the are debuffed
@Override
public void slowed(float percent){
    affectedBy = 2;
 if(speed == maxSpeed){
  speed = speed * percent;
  slowed = true;
 }
}

@Override
public void stunned(){
    affectedBy = 1;
 free = false;
 stun = true;
 adjust(192, 192);
}

@Override
public void silenced(){
    affectedBy = 3;
    free = true;
 silenced = true;
}

//calls and ticks the above states when they are apllied to the player
public void effectCheck(long time, int type){   // effect index: 1 = stun, 2 = slowed, 3 = silenced
  
  abTimer += System.currentTimeMillis() - lastUse;
  lastUse = System.currentTimeMillis(); 

  if(type == 1){
  if(stun){
  long toSeconds = (int)(abTimer / 1000);
    
    if (!effectTimer.contains(toSeconds)){
      effectTimer.add(toSeconds); //ticks effects on player
      if (effectCount < timeStack){
        effectCount++;
      }
    }   
  
    if (effectCount == timeStack){
     freedom();
    }
  }
  }else if(type == 2){
    if(slowed){
      long toSeconds = (int)(abTimer / 1000);
      if (!effectTimer.contains(toSeconds)){
        effectTimer.add(toSeconds);
        if (effectCount < timeStack){
          effectCount++;
        }
      }   
      
      if (effectCount == timeStack){
       freedom();
      }
    }
  }else if(type == 3){
     if(silenced){
         long toSeconds = (int)(abTimer / 1000);
         if (!effectTimer.contains(toSeconds)){
           effectTimer.add(toSeconds);
           if (effectCount < timeStack){
             effectCount++;
           }
         }   
         
         if (effectCount == timeStack){
          freedom();
         }
       }
     }else{
    timeStack = 0;
    affectedBy = 0;
  }
}

public boolean checkCooldowns(int abNum){ //a method which returns true if the ability is usable
  if (abilities[abNum] == cooldowns[abNum] && !silenced){
    return true;
  }else{
   return false; 
  }
}

//move method which checks player collions before moving
public void move(){
  if(!checkEntityCollisions(xMove, 0))
  moveX();
  if(!checkEntityCollisions(0, yMove))
  moveY();
  //uncommenting the above lines will toggle player collision with other players. This is user preference and is not integral to the code.
  if(!flying){
  effectTiles();
  }
}

public void moveX(){
 if(!flying){
  if(xMove > 0){//Checks collisions moving right
    int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
    
    if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
       !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
      x += xMove;
    }
  }else if(xMove < 0){// Checks collisions moving left
    int tx = (int)(x + xMove + bounds.x) / Tile.TILEWIDTH;
    
    if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
       !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
      x += xMove;
    }
  }
 }else if(flying){
  if(xMove != 0){
  x += xMove;
  }
 }
}

public void moveY(){
 if(!flying){
  if(yMove < 0){//Checks collisions moving up
    int ty = (int)(y + yMove + bounds.y) / Tile.TILEHEIGHT;
    
    if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
       !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
      y += yMove;
    }
  }else if(yMove > 0){//Checks collisions moving down
    int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
    
    if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
       !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
      y += yMove;
    }
  }
  }else if(flying){
  if(yMove != 0){
  y += yMove;
  }
 }
}

//this method checks for tiles that have effects on the player such as falling
public void effectTiles(){ 
    if(collisionWithFall((int) (x + bounds.x + bounds.width * 1.5) / Tile.TILEWIDTH, (int) (y + bounds.y + bounds.height * 1.5) / Tile.TILEHEIGHT) && 
       collisionWithFall((int) (x + bounds.x) / Tile.TILEWIDTH, (int) (y + bounds.y * 1.5) / Tile.TILEHEIGHT)){//tile collision
      falling = true;
    }
    if(falling){
      fallInit();
    }
}

private void fallInit(){
  width -= 10;
  height -= 10; //falling animation
  x+=10;
  y+=10;
  if(width <= 96 && height <= 96){
    hurt(maxHealth * 0.15f);
    if(playerNum == 1){
      x = 100;
      y = 280;
    }else if(playerNum == 2){
      x = 1000;
      y = 280; 
    }
    height = 192;
    width = 192;
    falling = false;
    return;
  }
}

protected boolean collisionWithTile(int x, int y){
 return handler.getWorld().getTile(x, y).isSolid(); 
}

protected boolean collisionWithFall(int x, int y){
 return handler.getWorld().getTile(x, y).willFall(); 
}

//method which keeps the player within the map boundaries
public void inBounds(){
  if ((int)(x + xMove + bounds.x) < Tile.TILEWIDTH * 0.6){ // left
   x += Tile.TILEWIDTH; 
  }else if ((int) (x + xMove + bounds.x + bounds.width) > (Launcher.runWidth - Tile.TILEWIDTH * 0.6)){ // right
   x -= Tile.TILEWIDTH; 
  }else if ((int)(y + yMove + bounds.y) > Launcher.runHeight - Tile.TILEHEIGHT){ // up
   y -= Tile.TILEHEIGHT;
  }else if ((int) (y + yMove + bounds.y + bounds.height) < Tile.TILEHEIGHT){ // down
   y += Tile.TILEHEIGHT;
  }
  
  if(!flying){
  if (collisionWithTile((int) (x + bounds.x + bounds.width/2)/ Tile.TILEWIDTH,(int) (y + bounds.y) / Tile.TILEHEIGHT) &&
  collisionWithTile((int) (x + bounds.x + bounds.width/2)/ Tile.TILEWIDTH, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
    x -= Tile.TILEWIDTH; // right collision
  }
  
  if (collisionWithTile((int)(x + bounds.x + bounds.width/2)/ Tile.TILEWIDTH,(int) (y + bounds.y) / Tile.TILEHEIGHT) &&
      collisionWithTile((int)(x + bounds.x + bounds.width/2)/ Tile.TILEWIDTH, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
    x += Tile.TILEWIDTH; // left collision
  }
  
  if(collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, (int)(y + bounds.y) / Tile.TILEHEIGHT) &&
     collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, (int)(y + bounds.y) / Tile.TILEHEIGHT)){
    y -= Tile.TILEHEIGHT;//up collision
  }  
  
  if(collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, (int)(y + bounds.y + bounds.height/2) / Tile.TILEHEIGHT) &&
     collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, (int)(y + bounds.y + bounds.height/2) / Tile.TILEHEIGHT)){
    y += Tile.TILEHEIGHT; //down collision
  }
}
}

  public abstract void render (Graphics g); //incomplete methods
  
  public abstract void LightAttack();
  
  public abstract void HeavyAttack();
  
  public abstract void A1();
  
  public abstract void A2();
  
  public abstract void A3();
  
  public abstract void A4();
  
  public Boolean proc (int procChance){
    int rnJesus = (int)(Math.random() * 100) + 0;
    if (rnJesus <= procChance){
      return true;
    }else
     return false; 
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
  
  //a method which checks attack collisions and deals damage to the entity.
  public boolean hitCheck(int arWidth, int arHeight, int dmg){
    Rectangle hb = getHitbox(0, 0);
    Rectangle ar = new Rectangle ();
    ar.width = arWidth;
    ar.height = arHeight;
    
    if (dir == 1){
      ar.x = hb.x - arWidth; // left
      ar.y = hb.y + hb.height / 2 - arHeight / 2;
    }else if (dir == 2){
      ar.x = hb.x + hb.width; // right
      ar.y = hb.y + hb.height / 2 - arHeight / 2;
    }
    
    for (Entity e : handler.getWorld().getEntityManager().getEntities()){
      if (e.equals(this))
        continue;
      if(e.getHitbox(0, 0).intersects(ar) && !(e instanceof Player)){
        e.hurt(dmg); 
        return true;
      }
    }
    return false;
  }
  
  //a method which checks attacks that hits multiple targets
  public int areaCheck(int arWidth, int arHeight, int dmg){
    Rectangle hb = getHitbox(0, 0);
    Rectangle ar = new Rectangle ();
    int hits = 0;
    ar.width = arWidth;
    ar.height = arHeight;
    
    if (dir == 1){
      ar.x = hb.x - arWidth; // left
      ar.y = hb.y + hb.height / 2 - arHeight / 2;
    }else if (dir == 2){
      ar.x = hb.x + hb.width; // right
      ar.y = hb.y + hb.height / 2 - arHeight / 2;
    }
    
    for (Entity e : handler.getWorld().getEntityManager().getEntities()){
      if (e.equals(this))
        continue;
      if(e.getHitbox(0, 0).intersects(ar) && !(e instanceof Player)){
        e.hurt(dmg); 
        hits++;
        continue;
      }
    }
    return hits;
  }
  
  //a method which stuns the target in range
  public Boolean hitLock(int arWidth, int arHeight, int dmg, long time){
    Rectangle hb = getHitbox(0, 0);
    Rectangle ar = new Rectangle ();
    ar.width = arWidth;
    ar.height = arHeight;
    
    if (dir == 1){
      ar.x = hb.x - arWidth; // left
      ar.y = hb.y + hb.height / 2 - arHeight / 2;
    }else if (dir == 2){
      ar.x = hb.x + hb.width; // right
      ar.y = hb.y + hb.height / 2 - arHeight / 2;
    }
    
    for (Entity e : handler.getWorld().getEntityManager().getEntities()){
      if (e.equals(this))
        continue;
      if(e.getHitbox(0, 0).intersects(ar) && !(e instanceof Player)){
        e.timeStack += time;
        e.affectedBy = 1;
        e.stunned();
        e.hurt(dmg);
        dmg = 0;
        return true;
      }
    }
    return false;
  }
  
  //method which checks with collisions with player projectiles
  public void projCheck(ArrayList<Projectile> projectiles, int dmg, int eIndex, long time){
    
    for(int i = 0; i < projectiles.size(); i++){
      Rectangle pb = new Rectangle ();
      projectiles.get(i).tick();
      
      pb.x = projectiles.get(i).projX + projectiles.get(i).inX;
      pb.y = projectiles.get(i).projY + projectiles.get(i).inY;
      pb.width = projectiles.get(i).projWidth;
      pb.height = projectiles.get(i).projHeight;
      
      for (Entity e : handler.getWorld().getEntityManager().getEntities()){
        if (e.equals(this))
          continue;
        if(e.getHitbox(0, 0).intersects(pb)){
          e.hurt(dmg); 
          if(eIndex == 1){
              e.timeStack += time;
              e.affectedBy = 1;
              e.hurt(dmg);
              dmg = 0;
              e.stunned();
          }else if(eIndex == 2){
            e.timeStack += time;
            e.affectedBy = 2;
         e.slowed(0.5f);          
          }else if(projectiles.get(i).projBounds()){
           projectiles.remove(projectiles.get(projectiles.size() - 1));           
          }else{
            
          }
          projectiles.remove(projectiles.get(projectiles.size() - 1));
          return;
        }
      }   
    }

  }
  
  //a method which checks collisions at a distance. It takes in different parameters based on what the attack does.
  @Override
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
      if(e.getHitbox(0, 0).intersects(ar) && !(e instanceof Player)){
        if(eIndex == 1){
          e.affectedBy = 1;
          e.timeStack += time;
          e.stunned();
        }else if(eIndex == 2){
          e.slowed(0.5f);
          e.affectedBy = 2; 
          e.slowed = true;
          e.timeStack += time;
        }else if(eIndex == 3){
            e.affectedBy = 3; 
            e.silenced();
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
  
  //a method which pushes the target away on collision
  public int knockback(int width, int height, int dmg, int distance){
    int count = 0;
    Rectangle ar = new Rectangle();
    Rectangle hb = getHitbox(0, 0);
    ar.width = width;
    ar.height = height;
    
     if (dir == 1){
      ar.x = hb.x; // left
      ar.y = hb.y + hb.height / 2 - height / 2;
    }else if (dir == 2){
      ar.x = hb.x + hb.width; // right
      ar.y = hb.y + hb.height / 2 - height / 2;
    }  
    
    for(Entity e : handler.getWorld().getEntityManager().getEntities()){
      if(e.equals(this)){
       continue; 
      }else if(e instanceof LiveEntity){
      if(e.getHitbox(0, 0).intersects(ar)){
        if(dir == 1){
          e.hurt(dmg);
          e.setX(e.getX() - distance);
        }else if (dir == 2){
          e.hurt(dmg);
          e.setX(e.getX() + distance);
        }
        count++;
        continue;
      }
    }
    }
    return count;
  }
  
  //returns the player to their default state
  @Override
  public void freedom(){
    timeStack = 0;
    affectedBy = 0;
    effectCount = 0;
    speed = maxSpeed;
    stun = false;
    slowed = false;
    silenced = false;
    free = true;
  }
  

}
