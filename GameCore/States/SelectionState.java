//This is an extension of the State class. It is displayed when a match starts and allows the player to choose which character to play
package GameCore.States;
import GameCore.Handler;
import GameCore.gfx.Assets;
import GameCore.gfx.Animation;
import GameCore.managers.UIManager;
import GameCore.ui.*;
import java.awt.*;

import java.awt.Graphics;

public class SelectionState extends State {
  
  public static int [] heroes = new int [2];//array which saves which character the player chooses
  
  private UIManager uiManager;//instance of the UI Manager class
  
  //list of idle animations for the screen
  public Animation sephiraN, graysonN, sephiraN2, graysonN2, luxaarN, luxaarN2, altaN, altaN2, ironfN, ironfN2, tafirahN, tafirahN2, laudaN, laudaN2, allistairN, allistairN2;
  private boolean set = false;
  
 public SelectionState(final Handler handler) {
  super(handler);
  
  uiManager = handler.getMouseManager().getUIManager();//retreive the UI Manager
  
  sephiraN = new Animation (1000, Assets.sephN);
  graysonN = new Animation (1000, Assets.grayN);
  luxaarN = new Animation(1000, Assets.luxN);
  sephiraN2 = new Animation (1000, Assets.sephN2);//initialize animations
  graysonN2 = new Animation (1000, Assets.grayN2);
  luxaarN2 = new Animation(1000, Assets.luxN2);
  tafirahN = new Animation (1000, Assets.taN);
  tafirahN2 = new Animation (1000, Assets.taN2);
  
 }
 
 //initializes the clickable objects
 public void setButtons(){
   uiManager.addObject(new UIImageButton(255, 260, 160, 80, Assets.btn_select, new ClickListener() {
     @Override
     public void onClick(){ 
       heroes[0] = 1;
       State.setState(handler.getGame().stageState);
       set = false;
     }})); 
   uiManager.addObject(new UIImageButton(765, 260, 160, 80, Assets.btn_select, new ClickListener() {
     @Override
     public void onClick(){ 
       heroes[0] = 2;
       State.setState(handler.getGame().stageState);
       set = false;
     }})); 
   uiManager.addObject(new UIImageButton(255, 590, 170, 80, Assets.btn_select, new ClickListener() {
     @Override
     public void onClick(){ 
       heroes[0] = 3;
       State.setState(handler.getGame().stageState);
       set = false;
     }})); 
   uiManager.addObject(new UIImageButton(765, 590, 170, 80, Assets.btn_select, new ClickListener() {
     @Override
     public void onClick(){ 
       heroes[0] = 4;
       State.setState(handler.getGame().stageState);
       set = false;
     }})); 
   set = true;
 }

 @Override
 public void tick() {
   if(!set){
     setButtons();
   }
   
   sephiraN.tick();
   graysonN.tick();
   sephiraN2.tick();
   graysonN2.tick();
   luxaarN.tick();
   luxaarN2.tick();//ticks the idle images
   tafirahN.tick();
   tafirahN2.tick();

   uiManager.tick();
 }

 @Override
 public void render(Graphics g) {
   g.drawImage(Assets.selectscreen, 0, 0, null);
   
   g.drawImage(sephiraN2.getCurrentFrame(), 245, 110, null); //draws things to the screen
   g.drawImage(graysonN2.getCurrentFrame(), 765, 95, null);
   g.drawImage(luxaarN2.getCurrentFrame(), 245, 440, null);
   g.drawImage(tafirahN2.getCurrentFrame(), 770, 465, null);
   
   g.setFont(new Font("Consolas", Font.PLAIN, 20)); 
   g.drawString("Sephira", 300, 330);
   g.drawString("Mobile, Versatile", 270, 350);
   g.drawString("Grayson", 800, 320);
   g.drawString("Durable, Control", 760, 340);
   g.drawString("Luxaar", 300, 660);
   g.drawString("Ranged, Splash", 270, 680);
   g.drawString("TaFiRah", 800, 660);
   g.drawString("Caster, Nuker", 780, 680);
   
   uiManager.render(g);
}
}