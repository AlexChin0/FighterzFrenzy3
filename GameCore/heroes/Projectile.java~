//This is a class which creates instances of projectiles, which are used by players to attack at range. It takes integer parameters
// responsible for setting the spawn coordinates and speed of the projectile.

package GameCore.heroes;
import GameCore.worlds.Tiles.*;
import GameCore.Launcher;
import GameCore.gfx.Animation;

import java.awt.*;
import java.util.*;

public class Projectile {
  
  public Animation animation;
  public int projX, projY, projWidth, projHeight, inX, inY, dir;
  public float speedX, speedY;
  private Rectangle pb;
  public ArrayList<Projectile> list;

  public Projectile(float speedX, float speedY, int x, int y, int projWidth, int projHeight, int inX, int inY, Animation animation, int dir){
    
    this.speedX = speedX;
    this.speedY = speedY;
    projX = x;
    projY = y; 
    this.projWidth = projWidth;
    this.projHeight = projHeight;
    this.inX = inX;
    this.inY = inY;
    this.animation = animation;
    this.dir = dir;
    
    list = new ArrayList<Projectile>();
    pb = new Rectangle (projX + inX, projY + inY, projWidth, projHeight);
  }

  public void tick(){
 animation.tick();
    projX += speedX;
    projY += speedY;
    projBounds();
  }
  
  public void render(Graphics g){
    if (dir == 2){
      g.drawImage(animation.getCurrentFrame(), projX, projY, null);
//      g.setColor(Color.GREEN);
//      g.fillRect(pb.x, pb.y, pb.width, pb.height);
    }
    if(dir == 1){
      g.drawImage(animation.getCurrentFrame(), projX, projY, null);     
//      g.setColor(Color.GREEN);
//      g.fillRect(pb.x, pb.y, pb.width, pb.height);
    } 
  }

//  checks for map collisions
  public boolean projBounds(){
    if ((int)(projX) < 0){ // left
      return true;
    }else if ((int) (projX) > (Launcher.runWidth - Tile.TILEWIDTH * 2)){ // right
      return true;
    }else{
    return false; 
    }
     
  }

  public Rectangle getPb(){
   return pb; 
  }
  
} 
