//this is an extension of the static entity class. It heals the player when they are closeby. 
package GameCore.entities;

import GameCore.*;
import java.awt.*;

import GameCore.gfx.Animation;
import GameCore.gfx.Assets;
import GameCore.heroes.*;

public class HealingCrystal extends StaticEntity{
 
 public Animation neutral, healEffect;
 
 public Rectangle area;

 public HealingCrystal(Handler handler, float x, float y, int width, int height) {
  super(handler, x, y, width, height);
  
  healEffect = new Animation (80, Assets.heals);    
  neutral = new Animation (200, Assets.hCrystal); //animations
  
  area = new Rectangle((int)(x - 150), (int)(y - 100), 400, 400);//heal effect radius
     
  bounds.x = 16;
  bounds.y = 80;
  bounds.width = 48;//hitbox
  bounds.height = 56;
  
  health = 1000;
  maxHealth = 1000;  
  
 }
 
 //inherited methods
 @Override
 public void die(){
  rangedCheck(area.x, area.y, area.width, area.height, 200, 1, 4);
  alive = false;
 }
 

 @Override
 public void tick(){
  neutral.tick();
     healEffect.tick();
 }
 
 public void render(Graphics g){
//     g.setColor(Color.green);
//     g.fillRect((int) (x + bounds.x * 3/4), (int)(y + bounds.y / 2), bounds.width * 2, bounds.height * 2); 
//     g.setColor (Color.red);
//     g.fillRect ((int) (x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height); 
  //g.fillRect(area.x, area.y, area.width, area.height);
  g.drawImage(neutral.getCurrentFrame(), (int)(x), (int)(y), width, height, null);
  
  //check through all entities
  for (Entity e : handler.getWorld().getEntityManager().getEntities()){
   if (e.equals(this))
    continue;
   if(e.getHitbox(0, 0).intersects(area) && e instanceof Player){
     e.heal(0.1f); //heals the player 
     g.drawImage(healEffect.getCurrentFrame(), (int)(e.getX()), (int)(e.getY()), null); 
   }
  }
 }

}