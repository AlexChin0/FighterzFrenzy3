//This is the framework of the entire game. The game class loads assets, managers and the game timer. Together the game is able to
// run and render the correct game states.

package GameCore;
import GameCore.gfx.*;
import GameCore.States.*;
import GameCore.managers.*;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
  
public class Game implements Runnable {

 private Display display;
 public int width, height;
 public String title;
 
 private boolean running = false;
 private Thread thread;
 
 private BufferStrategy bs;
 private Graphics g;
 
 //states
 public State gameState;
 public State menuState;
 public State introState;
 public State selectionState;
 public State endState;
 public State stageState;
 
 //handler 
 private Handler handler;
 
 //managers
 private KeyManager keyManager;
 private MouseManager mouseManager;

 public Game(String title, int width, int height){
  this.width = width;
  this.height = height;
  this.title = title;
  keyManager = new KeyManager();
  mouseManager = new MouseManager();
 }
 
 private void init(){
   display = new Display (title, width, height);
   display.getFrame ().addKeyListener(keyManager);
   display.getFrame ().addMouseListener (mouseManager);
   display.getFrame ().addMouseMotionListener (mouseManager); //adds the mouse and keyboard inputs to the frame
   display.getCanvas ().addMouseListener (mouseManager);
   display.getCanvas ().addMouseMotionListener (mouseManager);
   
  Assets.init();
  
  handler = new Handler (this);

  menuState = new MenuState(handler);
  introState = new IntroState(handler); //declares the states
  selectionState = new SelectionState(handler);
  endState = new EndState(handler);
  stageState = new StageState(handler);
  State.setState(menuState);
 }
 
 private void tick(){
   keyManager.tick();
   
   if (State.getState() != null){ //ticks the current state
    State.getState().tick();
   }
 }
 
 public void gameInit(String stage){
   gameState = new GameState(handler, stage);
   State.setState(handler.getGame().gameState);
 }
 
 private void render(){
  bs = display.getCanvas().getBufferStrategy(); //buffer for the screen rendering
  if(bs == null){
   display.getCanvas().createBufferStrategy(3);
   return;
  }
  g = bs.getDrawGraphics();
  g.clearRect(0,0,width,height);
    //Drawing space
  
  if (State.getState() != null)
    State.getState().render(g);
  
    //
 bs.show();
 g.dispose();
 }
 
  public void run (){ 
    
    init ();  
    
    int fps = 60;
    double timePerTick = 1000000000 / fps; //runs the game at 60 frames per second
    double delta = 0;
    long now;
    long lastTime = System.nanoTime();
    long timer = 0;
    int ticks = 0;
    
    while (running){
      now = System.nanoTime ();
      delta += (now - lastTime) / timePerTick;
      timer += now - lastTime;
      lastTime = now;
      
      if (delta >= 1){
      tick();
      render();
      ticks ++;
      delta --;
      }
      
      if (timer >= 1000000000) {
//       System.out.println ("Fps: " + ticks); //This line of code prints the current fps of the game to the console. It should always be about 60.
       ticks = 0;
       timer = 0;
      }
    }  
    stop ();
  }
  
  public KeyManager getKeyManager () {
    return keyManager; 
  }
  
  public MouseManager getMouseManager () {
    return mouseManager; 
  }
  
  public State getGameState(){
   return gameState; 
  }
 
 public synchronized void start(){
  if(running) //starts the program
   return;
  running = true;
  thread = new Thread(this);
  thread.start();
 }
 
 public synchronized void stop(){
  if(!running) //ends the program
   return;
  running = false;
  try {
   thread.join();
  } catch (InterruptedException e) {
   e.printStackTrace();
  }
 }
 
}

