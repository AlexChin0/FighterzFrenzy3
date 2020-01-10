//this is another monster which shares common methods with the others.
package GameCore.entities.monsters;

import java.awt.Graphics;

import GameCore.*;
import GameCore.gfx.*;
import GameCore.entities.*;

public class WingCharger extends LiveEntity {
  
  private Animation move;//movement sprite
  
  //constructor
  public WingCharger(Handler handler, float x, float y, int width, int height, float hpBonus){  
    super(handler, x, y, width, height, hpBonus);
    flying = true;
    health = 40 * hpBonus;
    maxHealth = 40 * hpBonus;
    healthMod = health / 120;
    
    bounds.x = 32;
    bounds.y = 40;
    bounds.width = 44;//instance variables
    bounds.height = 44;
    speed = 2.8f;
    maxSpeed = 2.8f;
    
    move = new Animation(200, Assets.wC);
  }
  
  @Override//ticks the creatures methods
  public void tick(){
    move.tick();
    effectCheck(timeStack, affectedBy);
    moveBehaviour(2);
    move();
    inBounds();
  }
  
  @Override 
  public void render(Graphics g){
    
    //g.setColor(Color.green);
    //g.fillRect((int) (x + bounds.x * 3/4), (int)(y + bounds.y / 2), bounds.width * 2, bounds.height * 2); 
    //g.setColor (Color.red);
    //g.fillRect ((int) (x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height); 
    
    g.drawImage(move.getCurrentFrame(), (int)(x), (int)(y), width, height, null);//draws the monster
  }
  
  @Override 
  public void attack(){//attack method for the monster
    hitCheck(60, 20, 5);
    x += 120;
  }
  
}
