//This is an extension of the State class. It is displays the world when the game has begun. 

package GameCore.States;
import GameCore.Handler;


import GameCore.entities.*;
import GameCore.worlds.World;
import GameCore.States.SelectionState;
import GameCore.heroes.*;

import java.awt.Graphics;

public class GameState extends State{
  
  private World world; //world object
  
  public static boolean end = false;
  
  public GameState(Handler handler, String stage){
    super (handler);
    world = new World (handler, stage);//loads the txt file for the world
    handler.setWorld (world);

    //Adds the player character into the game
    if(SelectionState.heroes[0] == 1){
      handler.getWorld().getEntityManager().addEntity(new Sephira(handler, 100, 280), 1);
    }else if(SelectionState.heroes[0] == 2){
      handler.getWorld().getEntityManager().addEntity(new Grayson(handler, 100, 280), 1);
    }else if(SelectionState.heroes[0] == 3){
      handler.getWorld().getEntityManager().addEntity(new Luxaar(handler, 100, 280), 1);
    }else if(SelectionState.heroes[0] == 4){
      handler.getWorld().getEntityManager().addEntity(new TaFiRah(handler, 100, 280), 1);
    }
  }
  
  @Override
  public void tick() {
    world.tick();
    
    //failsafe key resets the player position in case they get stuck off the map
    if (handler.getKeyManager().reset){
      for(int i = 0; i < handler.getWorld().getEntityManager().getEntities().size(); i++){
       handler.getWorld().getEntityManager().getEntities().get(i).freedom();
       
        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
          if(e instanceof Player){
            e.setX(100f);
            e.setY(280f);
          }
        }
      }
      
    }
          
    if(end){
      State.setState(handler.getGame().endState); //keeps track of if the game is still running
    }
  }
  
  @Override
  public void render (Graphics g){
  world.render(g);
  }
}