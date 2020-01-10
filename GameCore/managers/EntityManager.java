//This class creates an array list of player and monster in the game. It controls the number of entities that are rendered to the screen and
// is repsonsible for proper handling of all interactable objects in game.

package GameCore.managers;
import GameCore.Handler;

import GameCore.gfx.Assets;
import GameCore.entities.*;
import GameCore.entities.monsters.*;
import GameCore.States.GameState;

import java.awt.*;
import java.util.*;

public class EntityManager {

  private Handler handler;
  private ArrayList<Entity> entities; //render priority check
  private Entity p1;
  
  public int lives = 10, waveNum = 0;//variables for the players lives and wave number
  private float hpBuff = 1.0f;
  
  private Comparator<Entity> renderSorter = new Comparator<Entity>(){//this method compares the heights of all entities on the screen and orders them by height so that they can be drawn in order
   @Override
   public int compare(Entity a, Entity b) {
    if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
     return -1;
    return 1;
   }
  };

  public EntityManager(Handler handler){ //class constructor
    this.handler = handler;
    entities = new ArrayList<Entity>();
  }
  
  public void tick(){
    for(int i = 0; i < entities.size(); i++){ //ticks every player in the game
     Entity e = entities.get(i);
     e.tick();
     
     //condition triggers if a monster crosses to the end. Lives are deducted
     if(e instanceof LiveEntity && !(e instanceof IronFiend) && (int)(e.getX()) <= 32){
       e.die();
       lives--;
     }
          
     if(!e.isAlive()){ //removes entities who are dead
       entities.remove(e);
     }
    }
    
    //monitors the monsters and triggers the next wave when needed
    if(checkWave()){
      waveNum++;//increment the wave number
      if((waveNum) % 10 == 0 && waveNum != 0){
        spawnBoss();//spawns a boss every 10 waves
      }else{
        spawn(); //spawn a normal wave
      }
    }else if(lives < 0){
      GameState.end = true;//ends the game if the player has no more lives
    }
    Collections.sort(entities, renderSorter);//implements the comparator 
  }
  
  public void render(Graphics g){ //renders all entities to the screen
    for(Entity e : entities){

      if(e.playerNum == 1){
        p1 = e;
      }
  
      e.render(g);
      //draws the monsters health bars
      if(e instanceof LiveEntity && e.getHealth() < e.getMaxHealth()){
        g.setColor(Color.red);
        g.fillRect((int)(e.getX()), (int)(e.getY()), (int)(e.getHealth() / e.getMod()), 10);
      }
    }
    g.setColor(Color.red);
    g.fillRect(44, 784, (int)(p1.getHealth() / p1.getMod()), 12); //renders player health bars     
    g.drawImage(Assets.hud1, 0, 768, 1216, 64, null); //draws the heads up display
    
    //draws the required text to the screen
    if((waveNum) % 10 == 0 && waveNum != 0){
      g.setFont(new Font("Consolas", Font.PLAIN, 25)); 
      g.drawString("Lives: " + lives, 800, 790);
      g.drawString("BOSS BATTLE: " + waveNum, 760, 820);  
    }else{
    g.setFont(new Font("Consolas", Font.PLAIN, 25)); 
    g.drawString("Lives: " + lives, 800, 790);
    g.drawString("Wave: " + waveNum, 800, 820);
    }
  }
  
  //this method is responsible for spawning the appropiate number of randomly generated monsters
  private void spawn(){
    int initY, initX, type;//varaibles for location
    
    if(waveNum % 2 == 0 && waveNum != 0){//increases the monsters health based on the wave number
      hpBuff += 0.05;
    }
    //spawns monsters based on how high the wave is
    for(int i = 0; i <= (int)(waveNum + 1); i++){
      type = (int)(Math.random() * 6) + 1;//generates the type of monster
      initY = (int)((Math.random() * 450) + 100); //y spawn
      initX = (int)((Math.random() * 1200) + 1000); //x spawn
      if(type == 1 || type == 2 || type == 3){
      addEntity(new Berring(handler, initX, initY, 128, 128, hpBuff), 0);  
      }else if(type == 4 || type == 5){
        addEntity(new WingCharger(handler, initX, initY, 128, 128, hpBuff), 0);     
      }else if(type == 6){
        addEntity(new Marcher(handler, initX, initY, 192, 192, hpBuff), 0); 
      }
    }
  }
  
  //special spawn method for boss battles
  private void spawnBoss(){
    int initY, initX;
    initY = (int)((Math.random() * 500) + 50); //yspawn
    initX = (int)((Math.random() * 1200) + 1000); //xspawn
    addEntity(new IronFiend(handler, initX, initY, 192, 192, hpBuff), 0); 
  }
  
  public boolean checkWave(){//this method checks the runs through the list of entities and determines when to begin the next wave
    for(Entity e : entities){
      if(e instanceof LiveEntity && e.playerNum == 0){
        return false;
      }else{
       continue; 
      }
    }
    return true;
  }
  
  public void addEntity (Entity e, int pick){ //adds entities to the array list
    entities.add(e);
    e.playerNum = pick; 
  }
 
  //getters and setters
  
  public Handler getHandler() {
    return handler;
  }
  
  public void setHandler(Handler handler) {
    this.handler = handler;
  }
  
  public ArrayList<Entity> getEntities() {
    return entities;
  }
  
  public void setEntities(ArrayList<Entity> entities) {
    this.entities = entities;
  }
  
  public Entity getPlayer(){
   return p1; 
  }
  
}
