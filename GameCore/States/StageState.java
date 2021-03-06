//this is the state where the player chooses which maps to play on. It incorporates buttons from the UIManager.

package GameCore.States;
import GameCore.Handler;
import GameCore.gfx.Assets;
import java.awt.*;
import GameCore.managers.UIManager;
import GameCore.ui.*;
import GameCore.entities.*;

import java.awt.Graphics;
  
public class StageState extends State {
  
  private UIManager uiManager;
  private boolean set = false;

 public StageState(Handler handler) {
  super(handler);
  
  uiManager = handler.getMouseManager().getUIManager();//set the UI Manager
  
 }
 
 //initializes the buttons
 public void setButtons(){
   uiManager.addObject(new UIImageButton(255, 570, 160, 80, Assets.btn_select, new ClickListener() {//These buttons initialize the game 
     @Override
     public void onClick(){ 
       handler.getMouseManager().pauseUIManager(true);
       handler.getGame().gameInit("res/worlds/world1.txt");
       set = false;
     }})); 
   uiManager.addObject(new UIImageButton(765, 570, 160, 80, Assets.btn_select, new ClickListener() {
     @Override
     public void onClick(){ 
       handler.getMouseManager().pauseUIManager(true);
       handler.getGame().gameInit("res/worlds/world2.txt");
       handler.getWorld().getEntityManager().addEntity(new HealingCrystal(handler, 180, 260, 90, 180), 0);   
       set = false;
     }})); 
   set = true;
 }

 @Override
 public void tick() {
   if(!set){
     setButtons();//set the buttons
   }
   
   uiManager.tick();
 }

 //draws the buttons and text to the screen
 @Override
 public void render(Graphics g) {
   g.setFont(new Font("Consolas", Font.PLAIN, 32)); 
   g.drawString("Map Selection", 420, 100);
   g.drawImage(Assets.world1, 110, 260, 486, 306, null);
   g.drawImage(Assets.world2, 620, 260, 486, 306, null);
   uiManager.render(g);
 }

}

