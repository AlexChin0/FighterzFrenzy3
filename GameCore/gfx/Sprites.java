//This class is used to create subimages from images for the animations in the game
package GameCore.gfx;

import java.awt.image.BufferedImage;

public class Sprites {

  private BufferedImage sheet;
  
  public Sprites (BufferedImage sheet){
    this.sheet = sheet; 
  }
  
  public BufferedImage crop(int x, int y, int width, int height){
    return sheet.getSubimage(x, y, width, height);
  }
  
}
