//This is the assets class. It loads all of the png images required for the game before it begins, and assigns them to arrays and variables.
// this way the images are all loaded at once, during launch. 

package GameCore.gfx;

import java.awt.image.*;


public class Assets {
  
  public static BufferedImage plain1, plain2, bridge1, bridge2, bridge3, bridge4, water, cliff1, cliff2, grass, path, hud1, silenced, world1, world2;
  
  public static BufferedImage [] btn_start, btn_intro, btn_select, btn_exit, btn_restart, btn_coop, heals, charCards, hCrystal; //image arrays
  
  public static BufferedImage introscreen, mainscreen, endscreen, selectscreen, blank;
  
  public static BufferedImage [] sephN, sephN2, sephMR, sephML, sephMD, sephMU, sephLA, sephLA2, sephHA, sephHA2, sephBI, sephBI2, sephBF, sephBF2, 
    sephDT, sephDT2, sephDA, sephDA2, sephDag, sephDag2, sephSong, sephSong2, sephUlt, sephUlt2, sephAbils, sephF, sephF2, sephS, sephS2, sephBS, sephBS2, sephCS, sephCS2, sephCA, sephCA2, sephD, sephD2, sephSnD, sephSnD2, sephSnD3, sephSnD4, sephSnD5;
  
  public static BufferedImage[] grayN, grayN2, grayMR, grayML, grayMD, grayMU, grayLA, grayLA2, grayHA, grayHA2, graySS, graySS2, graySC, graySC2, grayRS, grayRS2, grayUC, grayUC2, grayF, grayF2, grayS, grayS2, grayR, grayR2, grayE, arunLia, grayAbils;
  
  public static BufferedImage[] luxN, luxN2, luxMR, luxML, luxMD, luxMU, luxLA, luxLA2, luxHA, luxHA2, luxSV, luxSV2, luxV, luxV2, luxUC, luxUC2, luxVD, luxLC, luxLC2, luxAR, luxAR2, luxF, luxF2, luxS, luxS2, luxFC, luxFC2, luxRS, luxRS2,  luxAbils;

  public static BufferedImage[] taN, taN2, taMR, taML, taMD, taMU, taLA, taLA2, taHA, taHA2, taF, taF2, taS, taS2, taFB, taFB2, taFBH, taFBH2, taSP, taSP2, taSPP, taSPP2, taSB, taCC, taCC2, taFC, taUC, taUC2, tof, taFN, taFN2, taFLA, taFLA2, taFL, taFL2, taFG, 
    taFG2, taMFB, taMFB2, taPW, taFD, taFD2, taB, taB2, taAbils;
  
  public static BufferedImage[] ironN, ironN2, ironMR, ironML, ironMD, ironMU, ironLA, ironLA2, ironHA, ironHA2, ironF, ironF2, ironS, ironS2, ironSG1, ironSG2, ironI, ironRC, ironWR, ironWR2, ironRC2, sprintUp, sprintDown, sprintLeft, sprintRight, beat;

  public static BufferedImage[] berN, berA, wC, maN, maA, maS, laser1, laser2;
  
  public static void init () {
    
    SpriteSheet tileSheet = new SpriteSheet ("/textures/All Tiles.png");
    SpriteSheet tileSheet3 = new SpriteSheet ("/textures/bon.png");
    SpriteSheet tileSheet4 = new SpriteSheet ("/textures/background2.png");
    SpriteSheet texs = new SpriteSheet ("/textures/tex.png");
    SpriteSheet healing = new SpriteSheet ("/textures/healing.png");
    SpriteSheet hcrystal = new SpriteSheet ("/textures/Crystal.png");  
    SpriteSheet screens = new SpriteSheet ("/textures/intro.png");  
    
    hud1 = ImageLoader.loadImage("/textures/hud.png");
    silenced = ImageLoader.loadImage("/textures/silence.png");
    world1 = ImageLoader.loadImage("/worlds/bon.png");
    world2 = ImageLoader.loadImage("/worlds/remastered.png");
    
    SpriteSheet monsters = new SpriteSheet("/ents/Monster Sheet.png");
    SpriteSheet lasers = new SpriteSheet("/ents/Laser.png");
    
    // sephira stuff
    SpriteSheet sephi1 = new SpriteSheet ("/seph/Sephira Sheet1.png");
    SpriteSheet sephi2 = new SpriteSheet ("/seph/Sephira Sheet2.png");
    SpriteSheet sephi3 = new SpriteSheet ("/seph/Sephira Sheet3.png");
    SpriteSheet sephiLA = new SpriteSheet  ("/seph/Sephira Light Attack Left.png");
    SpriteSheet sephiLA2 = new SpriteSheet  ("/seph/Sephira Light Attack Right.png");
    SpriteSheet sephiHA = new SpriteSheet  ("/seph/Sephira Heavy Attack Left.png");
    SpriteSheet sephiHA2 = new SpriteSheet  ("/seph/Sephira Heavy Attack Right.png");
    SpriteSheet sephiBI = new SpriteSheet  ("/seph/Sephira Blink Init Left.png");
    SpriteSheet sephiBI2 = new SpriteSheet  ("/seph/Sephira Blink Init Right.png");
    SpriteSheet sephiBF = new SpriteSheet  ("/seph/Sephira Blink Final Left.png");
    SpriteSheet sephiBF2 = new SpriteSheet  ("/seph/Sephira Blink Final Right.png");
    SpriteSheet sephiDT = new SpriteSheet ("/seph/Sephira Dagger Throw Left.png");
    SpriteSheet sephiDT2 = new SpriteSheet ("/seph/Sephira Dagger Throw Right.png");
    SpriteSheet sephiDA = new SpriteSheet ("/seph/Dagger Attack Left.png");
    SpriteSheet sephiDA2 = new SpriteSheet ("/seph/Dagger Attack Right.png");
    SpriteSheet sephiDag = new SpriteSheet ("/seph/TD Projectile Left.png");
    SpriteSheet sephiDag2 = new SpriteSheet ("/seph/TD Projectile Right.png");
    SpriteSheet sephiSong = new SpriteSheet ("/seph/Sephira Ulti Cast Left.png");
    SpriteSheet sephiSong2 = new SpriteSheet ("/seph/Sephira Ulti Cast Right.png");
    SpriteSheet sephiAbils = new SpriteSheet ("/textures/sephira abilities.png");  
    SpriteSheet sephiBS = new SpriteSheet ("/seph/Sephira Blink Strike Left.png");
    SpriteSheet sephiBS2 = new SpriteSheet ("/seph/Sephira Blink Strike Right.png");
    SpriteSheet sephiSnD = new SpriteSheet("/seph/Sephira SnD.png");
    
    //grayson stuff
  SpriteSheet grays1 = new SpriteSheet ("/gray/Grayson Sheet1.png");
  SpriteSheet grays2 = new SpriteSheet ("/gray/Grayson Sheet2.png");
  SpriteSheet graysLA = new SpriteSheet  ("/gray/Grayson Light Attack Left.png");
  SpriteSheet graysLA2 = new SpriteSheet  ("/gray/Grayson Light Attack Right.png");
  SpriteSheet graysHA = new SpriteSheet  ("/gray/Grayson Heavy Attack Left.png");
  SpriteSheet graysHA2 = new SpriteSheet  ("/gray/Grayson Heavy Attack Right.png");
  SpriteSheet graysSS = new SpriteSheet  ("/gray/Grayson Shield Left.png");
  SpriteSheet graysSS2 = new SpriteSheet  ("/gray/Grayson Shield Right.png");
  SpriteSheet graysRS = new SpriteSheet  ("/gray/Grayson Runeshard Left.png");
  SpriteSheet graysRS2 = new SpriteSheet  ("/gray/Grayson Runeshard Right.png");
  SpriteSheet graysSC = new SpriteSheet ("/gray/Grayson Shield Charge Left.png");
  SpriteSheet graysSC2 = new SpriteSheet ("/gray/Grayson Shield Charge Right.png");
  SpriteSheet graysUC = new SpriteSheet ("/gray/Grayson Ulti Cast Left.png");
  SpriteSheet graysUC2 = new SpriteSheet ("/gray/Grayson Ulti Cast Right.png");
  SpriteSheet graysEN = new SpriteSheet("/gray/Grayson Enrune.png");
  SpriteSheet arunlia = new SpriteSheet ("/gray/Arun-Lia Effect.png");  
  SpriteSheet graysAbils = new SpriteSheet ("/textures/Grayson Abilities.png");
  
  //luxaar stuff
  SpriteSheet lux1 = new SpriteSheet ("/lux/Luxaar Sheet1.png");  
  SpriteSheet luxaLA = new SpriteSheet("/lux/Luxaar Light Attack Left.png");   
  SpriteSheet luxaLA2 = new SpriteSheet("/lux/Luxaar Light Attack Right.png");   
  SpriteSheet luxaHA = new SpriteSheet("/lux/Luxaar Heavy Attack Left.png");
  SpriteSheet luxaHA2 = new SpriteSheet("/lux/Luxaar Heavy Attack Right.png");  
  SpriteSheet luxaSV = new SpriteSheet("/lux/Luxaar Volley Cast Left.png");   
  SpriteSheet luxaSV2 = new SpriteSheet("/lux/Luxaar Volley Cast Right.png");
  SpriteSheet luxaV = new SpriteSheet("/lux/volley left.png");   
  SpriteSheet luxaV2 = new SpriteSheet("/lux/volley right.png"); 
  SpriteSheet luxaCF = new SpriteSheet("/lux/Luxaar Flash Cannon.png");
  SpriteSheet luxaUC = new SpriteSheet("/lux/Luxaar Ulti Cast left.png"); 
  SpriteSheet luxaUC2 = new SpriteSheet("/lux/Luxaar Ulti Cast Right.png");   
  SpriteSheet luxaVD = new SpriteSheet("/lux/valliant detonation.png"); 
  SpriteSheet luxaAR = new SpriteSheet("/lux/Luxaar Projectile Left.png"); 
  SpriteSheet luxaAR2 = new SpriteSheet("/lux/Luxaar Projectile Right.png"); 
  SpriteSheet luxaLC = new SpriteSheet("/lux/Lux Light Cast.png");
  SpriteSheet luxaFC = new SpriteSheet("/lux/Flash Cleave.png");
  SpriteSheet luxaAbils = new SpriteSheet("/textures/Luxaar Abilities.png"); 
  
    //ta fi rah stuff
  SpriteSheet ta1 = new SpriteSheet ("/tafirah/Ta-Fi-Rah Sheet1.png");
  SpriteSheet ta2 = new SpriteSheet ("/tafirah/Ta-Fi-Rah Sheet2.png"); 
  SpriteSheet ta3 = new SpriteSheet ("/tafirah/Ta-Fi-Rah Sheet3.png");
  SpriteSheet ta4 = new SpriteSheet ("/tafirah/TaFiRah Sheet4.png");
  SpriteSheet tafPW = new SpriteSheet ("/tafirah/Wings.png");
  SpriteSheet tafFB = new SpriteSheet("/tafirah/Fireball Left.png");
  SpriteSheet tafFB2 = new SpriteSheet("/tafirah/Fireball Right.png");   
  SpriteSheet tafFBH = new SpriteSheet("/tafirah/Fireball Heavy Left.png");
  SpriteSheet tafFBH2 = new SpriteSheet("/tafirah/Fireball Heavy Right.png");   
  SpriteSheet tafMFB = new SpriteSheet("/tafirah/MegaFireRight.png"); 
  SpriteSheet tafMFB2 = new SpriteSheet("/tafirah/MegaFire Left.png");    
  SpriteSheet tafSP = new SpriteSheet("/tafirah/Ta-Fi-Rah Spear of Flame Left.png");
  SpriteSheet tafSP2 = new SpriteSheet("/tafirah/Ta-Fi-Rah Spear of Flame Right.png");
  SpriteSheet tafSPP = new SpriteSheet("/tafirah/Flame Spear Right.png");
  SpriteSheet tafSPP2 = new SpriteSheet("/tafirah/Flame Spear Left.png");
  SpriteSheet tafSB = new SpriteSheet("/tafirah/Spear Bomb.png");
  SpriteSheet tafCC = new SpriteSheet("/tafirah/Ta-Fi-Rah Cone Cast Left.png"); 
  SpriteSheet tafCC2 = new SpriteSheet("/tafirah/Ta-Fi-Rah Cone Cast Right.png"); 
  SpriteSheet tafFC = new SpriteSheet("/tafirah/Flame Column.png");
  SpriteSheet tafFD = new SpriteSheet("/tafirah/TaFiRah Hot Wings.png");
  SpriteSheet taftof = new SpriteSheet("/tafirah/Tof effect.png");
  SpriteSheet tafF2 = new SpriteSheet("/tafirah/Fire2.png");
  SpriteSheet tafAbils = new SpriteSheet("/textures/TaFiRah Abilties.png");
  
  //iron fiend
  SpriteSheet iron1 = new SpriteSheet ("/ironfiend/Iron Fiend Sheet1.png");  
  SpriteSheet ironfHA = new SpriteSheet("/ironfiend/Iron Fiend Heavy Attack Left.png");
  SpriteSheet ironfHA2 = new SpriteSheet("/ironfiend/Iron Fiend Heavy Attack Right.png");     
  SpriteSheet sprints = new SpriteSheet("/ironfiend/sprint effect.png");
  SpriteSheet ironfWR = new SpriteSheet("/ironfiend/Iron Fiend Wild Rend Left.png");
  SpriteSheet ironfWR2 = new SpriteSheet("/ironfiend/Iron Fiend Wild Rend Right.png");
 
  //tiles
    plain1 = tileSheet.crop(0, 0, 64, 64);
    plain2 = tileSheet.crop(64, 64, 64, 64);
    bridge1 = tileSheet3.crop(0, 0, 64, 64);
    bridge2 = tileSheet3.crop(64, 64, 64, 64);
    bridge3 = tileSheet3.crop(64, 0, 64, 64);
    water = tileSheet3.crop(0, 64, 64, 64);
    bridge4 = tileSheet3.crop(0, 128, 64, 64);
    
    introscreen = screens.crop(0, 0, 1216, 832);
    mainscreen = screens.crop(1216, 0, 1216, 832);
    endscreen = screens.crop(1216, 832, 1216, 832);
    selectscreen = screens.crop(0, 832, 1216, 832);
    blank = screens.crop(2432, 832, 1216, 832);
    
    cliff1 = tileSheet4.crop(0, 0, 64, 64);
    cliff2 = tileSheet4.crop(64, 0, 64, 64);
    grass = tileSheet4.crop(0, 64, 64, 64);
    path = tileSheet4.crop(64, 64, 64, 64);
    
   btn_start = new BufferedImage [2];
   btn_start [0] = texs.crop (0, 0, 128, 64);
   btn_start [1] = texs.crop (128, 0, 128, 64);
         
   btn_intro = new BufferedImage [2];
   btn_intro [0] = texs.crop (0, 64, 128, 64);
   btn_intro [1] = texs.crop (128, 64, 128, 64);   //all image crops
   
   btn_select = new BufferedImage [2];
   btn_select [0] = texs.crop(0, 128, 128 ,64);
   btn_select [1] = texs.crop(128, 128, 128,64);
   
   btn_exit = new BufferedImage[2];
   btn_exit[0] = texs.crop(0, 192, 128, 64);
   btn_exit[1] = texs.crop(128, 192, 128, 64);
   
   btn_restart = new BufferedImage[2];
   btn_restart[0] = texs.crop(0, 251, 128, 64);
   btn_restart[1] = texs.crop(128, 251, 128, 64);
   
   btn_coop = new BufferedImage[2];
   btn_coop[0] = texs.crop(0, 320, 128, 64);
   btn_coop[1] = texs.crop(128, 320, 128, 64);
   
   heals = new BufferedImage[4];
   heals[0] = healing.crop(0, 0, 192, 192);
   heals[1] = healing.crop(192, 0, 192, 192);
   heals[2] = healing.crop(0, 192, 192, 192);
   heals[3] = healing.crop(192, 192, 192, 192);
   
   hCrystal = new BufferedImage[3];
   hCrystal[0] = hcrystal.crop(0, 0, 120, 240);
   hCrystal[1] = hcrystal.crop(120, 0, 120, 240);
   hCrystal[2] = hcrystal.crop(0, 240, 120, 240);
   
   //berring
   berN = new BufferedImage[4];
   berN [0] = monsters.crop(0, 0, 192, 192);
   berN [1] = monsters.crop(192, 0, 192, 192);
   berN [2] = monsters.crop(384, 0, 192, 192);
   berN [3] = monsters.crop(576, 0, 192, 192);
   
   berA = new BufferedImage[4];
   berA [0] = monsters.crop(768, 0, 192, 192);
   berA [1] = monsters.crop(0, 192, 192, 192);
   berA [2] = monsters.crop(192, 192, 192, 192);
   berA [3] = monsters.crop(384, 192, 192, 192);
   
   //wingcharager
   wC = new BufferedImage[3];
   wC[0] = monsters.crop(192, 384, 192, 192);
   wC[1] = monsters.crop(384, 384, 192, 192);
   wC[2] = monsters.crop(576, 384, 192, 192);
   
   //marcher 
   maN = new BufferedImage[5];
   maN[0] = monsters.crop(768, 384, 192, 192);
   maN[1] = monsters.crop(0, 576, 192, 192);
   maN[2] = monsters.crop(192, 576, 192, 192);
   maN[3] = monsters.crop(384, 576, 192, 192);
   maN[4] = monsters.crop(576, 576, 192, 192);
   
   maA = new BufferedImage[8];
   maA[0] = monsters.crop(768, 576, 192, 192);
   maA[1] = monsters.crop(0, 768, 192, 192);
   maA[2] = monsters.crop(192, 768, 192, 192);
   maA[3] = monsters.crop(384, 768, 192, 192);
   maA[4] = monsters.crop(576, 768, 192, 192);
   maA[5] = monsters.crop(768, 768, 192, 192);
   maA[6] = monsters.crop(0, 960, 192, 192);
   maA[7] = monsters.crop(192, 960, 192, 192);
   
   maS = new BufferedImage[2];
   maS[0] = monsters.crop(768, 576, 192, 192);
   maS[1] = monsters.crop(0, 768, 192, 192);
   
   laser1 = new BufferedImage[3];
   laser1 [0] = lasers.crop(0, 0, 32, 32);
   laser1 [1] = lasers.crop(32, 0, 32, 32);
   laser1 [2] = lasers.crop(0, 32, 32, 32);
   
   laser2 = new BufferedImage[3];
   laser2 [0] = lasers.crop(32, 32, 32, 32);
   laser2 [1] = lasers.crop(0, 64, 32, 32);
   laser2 [2] = lasers.crop(32, 64, 32, 32);
   
   //sephira 
   
  sephAbils = new BufferedImage[8];
  sephAbils[0] = sephiAbils.crop(0, 0, 64, 64);
  sephAbils[1] = sephiAbils.crop(64, 64, 64, 64);
  sephAbils[4] = sephiAbils.crop(64, 0, 64, 64);
  sephAbils[5] = sephiAbils.crop(0, 128, 64, 64);
  sephAbils[2] = sephiAbils.crop(0, 64, 64, 64);   
  sephAbils[3] = sephiAbils.crop(64, 128, 64, 64);
  sephAbils[6] = sephiAbils.crop(0, 192, 64, 64);
  sephAbils[7] = sephiAbils.crop(64, 192, 64, 64);
   
   sephN = new BufferedImage [2];
   sephN [0] = sephi1.crop (0, 0, 192, 192);
   sephN [1] = sephi1.crop (0, 192, 192, 192); 
   
   sephN2 = new BufferedImage [2];
   sephN2 [0] = sephi1.crop (192, 0, 192, 192);
   sephN2 [1] = sephi1.crop (192, 192, 192, 192);
   
  sephML = new BufferedImage [3];
  sephML [0] = sephi1.crop (768, 384, 192, 192);
  sephML [1] = sephi1.crop (960, 384, 192, 192);
  sephML [2] = sephi1.crop (768, 576, 192, 192);
  
  sephMR = new BufferedImage [3];
  sephMR [0] = sephi1.crop (0, 768, 192, 192);
  sephMR [1] = sephi1.crop (192, 768, 192, 192);
  sephMR [2] = sephi1.crop (0, 960, 192, 192);
  
  sephMD = new BufferedImage [3];
  sephMD [0] = sephi1.crop (768, 768, 192, 192);
  sephMD [1] = sephi1.crop (960, 768, 192, 192);
  sephMD [2] = sephi1.crop (768, 960, 192, 192);
  
  sephMU = new BufferedImage [3];
  sephMU [0] = sephi1.crop (384, 768, 192, 192);
  sephMU [1] = sephi1.crop (576, 768, 192, 192);
  sephMU [2] = sephi1.crop (384, 960, 192, 192);
  
  sephLA = new BufferedImage [8];
  sephLA [0] = sephiLA.crop (0, 0, 210, 210);
  sephLA [1] = sephiLA.crop (210, 0, 210, 210);
  sephLA [2] = sephiLA.crop (420, 0, 210, 210);
  sephLA [3] = sephiLA.crop (0, 210, 210, 210);
  sephLA [4] = sephiLA.crop (210, 210, 210, 210);
  sephLA [5] = sephiLA.crop (420, 210, 210, 210);
  sephLA [6] = sephiLA.crop (0, 420, 210, 210);
  sephLA [7] = sephiLA.crop (210, 420, 210, 210);
                                            
  sephLA2 = new BufferedImage [8];
  sephLA2 [0] = sephiLA2.crop (0, 0, 210, 210);
  sephLA2 [1] = sephiLA2.crop (210, 0, 210, 210);
  sephLA2 [2] = sephiLA2.crop (420, 0, 210, 210);
  sephLA2 [3] = sephiLA2.crop (0, 210, 210, 210);
  sephLA2 [4] = sephiLA2.crop (210, 210, 210, 210);
  sephLA2 [5] = sephiLA2.crop (420, 210, 210, 210);
  sephLA2 [6] = sephiLA2.crop (0, 420, 210, 210);
  sephLA2 [7] = sephiLA2.crop (210, 420, 210, 210);
  
  sephHA = new BufferedImage [12];
  sephHA [0] = sephiHA.crop (0, 0, 210, 210);
  sephHA [1] = sephiHA.crop (210, 0, 210, 210);
  sephHA [2] = sephiHA.crop (420, 0, 210, 210);
  sephHA [3] = sephiHA.crop (0, 210, 210, 210);
  sephHA [4] = sephiHA.crop (210, 210, 210, 210);
  sephHA [5] = sephiHA.crop (420, 210, 210, 210);
  sephHA [6] = sephiHA.crop (0, 420, 210, 210);
  sephHA [7] = sephiHA.crop (210, 420, 210, 210);
  sephHA [8] = sephiHA.crop (420, 420, 210, 210);
  sephHA [9] = sephiHA.crop (0, 630, 210, 210);
  sephHA [10] = sephiHA.crop (210, 630, 210, 210);
  sephHA [11] = sephiHA.crop (420, 630, 210, 210);
  
  sephHA2 = new BufferedImage [12];
  sephHA2 [0] = sephiHA2.crop (0, 0, 210, 210);
  sephHA2 [1] = sephiHA2.crop (210, 0, 210, 210);
  sephHA2 [2] = sephiHA2.crop (420, 0, 210, 210);
  sephHA2 [3] = sephiHA2.crop (0, 210, 210, 210);
  sephHA2 [4] = sephiHA2.crop (210, 210, 210, 210);
  sephHA2 [5] = sephiHA2.crop (420, 210, 210, 210);
  sephHA2 [6] = sephiHA2.crop (0, 420, 210, 210);
  sephHA2 [7] = sephiHA2.crop (210, 420, 210, 210);
  sephHA2 [8] = sephiHA2.crop (420, 420, 210, 210);
  sephHA2 [9] = sephiHA2.crop (0, 630, 210, 210);
  sephHA2 [10] = sephiHA2.crop (210, 630, 210, 210);
  sephHA2 [11] = sephiHA2.crop (420, 630, 210, 210);
  
  sephBI = new BufferedImage [5];
  sephBI [0] = sephiBI.crop (0, 0, 300, 210);
  sephBI [1] = sephiBI.crop (300, 0, 300, 210);
  sephBI [2] = sephiBI.crop (0, 210, 300, 210);
  sephBI [3] = sephiBI.crop (300, 210, 300, 210);
  sephBI [4] = sephiBI.crop (0, 420, 300, 210);
  
  sephBI2 = new BufferedImage [5];
  sephBI2 [0] = sephiBI2.crop (0, 0, 300, 210);
  sephBI2 [1] = sephiBI2.crop (300, 0, 300, 210);
  sephBI2 [2] = sephiBI2.crop (0, 210, 300, 210);
  sephBI2 [3] = sephiBI2.crop (300, 210, 300, 210);
  sephBI2 [4] = sephiBI2.crop (0, 420, 300, 210);
  
  sephBS = new BufferedImage [10];
  sephBS [0] = sephiBS.crop(0, 0, 300, 210);
  sephBS [1] = sephiBS.crop(300, 0, 300, 210);
  sephBS [2] = sephiBS.crop(600, 0, 300, 210);
  sephBS [3] = sephiBS.crop(0, 210, 300, 210);
  sephBS [4] = sephiBS.crop(300, 210, 300, 210);
  sephBS [5] = sephiBS.crop(600, 210, 300, 210);
  sephBS [6] = sephiBS.crop(0, 420, 300, 210);
  sephBS [7] = sephiBS.crop(300, 420, 300, 210);
  sephBS [8] = sephiBS.crop(600, 420, 300, 210);
  sephBS [9] = sephiBS.crop(0, 630, 300, 210);
  
  sephBS2 = new BufferedImage [10];
  sephBS2 [0] = sephiBS2.crop(0, 0, 300, 210);
  sephBS2 [1] = sephiBS2.crop(300, 0, 300, 210);
  sephBS2 [2] = sephiBS2.crop(600, 0, 300, 210);
  sephBS2 [3] = sephiBS2.crop(0, 210, 300, 210);
  sephBS2 [4] = sephiBS2.crop(300, 210, 300, 210);
  sephBS2 [5] = sephiBS2.crop(600, 210, 300, 210);
  sephBS2 [6] = sephiBS2.crop(0, 420, 300, 210);
  sephBS2 [7] = sephiBS2.crop(300, 420, 300, 210);
  sephBS2 [8] = sephiBS2.crop(600, 420, 300, 210);
  sephBS2 [9] = sephiBS2.crop(0, 630, 300, 210);
  
  sephBF = new BufferedImage [6];
  sephBF [0] = sephiBF.crop (0, 0, 300, 210);
  sephBF [1] = sephiBF.crop (300, 0, 300, 210);
  sephBF [2] = sephiBF.crop (0, 210, 300, 210);
  sephBF [3] = sephiBF.crop (300, 210, 300, 210);
  sephBF [4] = sephiBF.crop (0, 420, 300, 210);
  sephBF [5] = sephiBF.crop (300, 420, 300, 210);
  
  sephBF2 = new BufferedImage [6];
  sephBF2 [0] = sephiBF2.crop (0, 0, 300, 210);
  sephBF2 [1] = sephiBF2.crop (300, 0, 300, 210);
  sephBF2 [2] = sephiBF2.crop (0, 210, 300, 210);
  sephBF2 [3] = sephiBF2.crop (300, 210, 300, 210);
  sephBF2 [4] = sephiBF2.crop (0, 420, 300, 210);
  sephBF2 [5] = sephiBF2.crop (300, 420, 300, 210);
  
  sephDT = new BufferedImage [4];
  sephDT [0] = sephiDT.crop(0, 0, 270, 270);
  sephDT [1] = sephiDT.crop(270, 0, 270, 270);
  sephDT [2] = sephiDT.crop(0, 270, 270, 270);
  sephDT [3] = sephiDT.crop(270, 270, 270, 270);
  
  sephDT2 = new BufferedImage [4];
  sephDT2 [0] = sephiDT2.crop(0, 0, 270, 270);
  sephDT2 [1] = sephiDT2.crop(270, 0, 270, 270);
  sephDT2 [2] = sephiDT2.crop(0, 270, 270, 270);
  sephDT2 [3] = sephiDT2.crop(270, 270, 270, 270);
  
  sephDA = new BufferedImage[5];
  sephDA [0] = sephiDA.crop(0, 0, 270, 345);
  sephDA [1] = sephiDA.crop(270, 0, 270, 345);
  sephDA [2] = sephiDA.crop(540, 0, 270, 345);
  sephDA [3] = sephiDA.crop(0, 345, 270, 345);
  sephDA [4] = sephiDA.crop(270, 345, 270, 345);
  
  sephDA2 = new BufferedImage[5];
  sephDA2 [0] = sephiDA2.crop(0, 0, 270, 345);
  sephDA2 [1] = sephiDA2.crop(270, 0, 270, 345);
  sephDA2 [2] = sephiDA2.crop(540, 0, 270, 345);
  sephDA2 [3] = sephiDA2.crop(0, 345, 270, 345);
  sephDA2 [4] = sephiDA2.crop(270, 345, 270, 345);
  
  sephDag = new BufferedImage [4];
  sephDag [0] = sephiDag.crop(0, 0, 128, 128);
  sephDag [1] = sephiDag.crop(128, 0, 128, 128);
  sephDag [2] = sephiDag.crop(0, 128, 128, 128);
  sephDag [3] = sephiDag.crop(128, 128, 128, 128);
  
  sephDag2 = new BufferedImage [4];
  sephDag2 [0] = sephiDag2.crop(0, 0, 127, 128);
  sephDag2 [1] = sephiDag2.crop(128, 0, 127, 128);
  sephDag2 [2] = sephiDag2.crop(0, 128, 127, 128);
  sephDag2 [3] = sephiDag2.crop(128, 128, 127, 128); 
  
  sephSong = new BufferedImage [19];
  sephSong [0] = sephiSong.crop(0, 0, 210, 225);
  sephSong [1] = sephiSong.crop(210, 0, 210, 225);
  sephSong [2] = sephiSong.crop(420, 0, 210, 225);
  sephSong [3] = sephiSong.crop(630, 0, 210, 225);
  sephSong [4] = sephiSong.crop(840, 0, 210, 225);
  sephSong [5] = sephiSong.crop(0, 225, 210, 225);
  sephSong [6] = sephiSong.crop(210, 225, 210, 225);
  sephSong [7] = sephiSong.crop(420, 225, 210, 225);
  sephSong [8] = sephiSong.crop(630, 225, 210, 225);
  sephSong [9] = sephiSong.crop(840, 225, 210, 225);
  sephSong [10] = sephiSong.crop(0, 450, 210, 225);
  sephSong [11] = sephiSong.crop(210, 450, 210, 225);
  sephSong [12] = sephiSong.crop(420, 450, 210, 225);
  sephSong [13] = sephiSong.crop(630, 450, 210, 225);
  sephSong [14] = sephiSong.crop(840, 450, 210, 225);
  sephSong [15] = sephiSong.crop(0, 675, 210, 225);
  sephSong [16] = sephiSong.crop(210, 675, 210, 225);
  sephSong [17] = sephiSong.crop(420, 675, 210, 225);
  sephSong [18] = sephiSong.crop(630, 675, 210, 225);
  
  sephSong2 = new BufferedImage [19];
  sephSong2 [0] = sephiSong2.crop(0, 0, 210, 225);
  sephSong2 [1] = sephiSong2.crop(210, 0, 210, 225);
  sephSong2 [2] = sephiSong2.crop(420, 0, 210, 225);
  sephSong2 [3] = sephiSong2.crop(630, 0, 210, 225);
  sephSong2 [4] = sephiSong2.crop(840, 0, 210, 225);
  sephSong2 [5] = sephiSong2.crop(0, 225, 210, 225);
  sephSong2 [6] = sephiSong2.crop(210, 225, 210, 225);
  sephSong2 [7] = sephiSong2.crop(420, 225, 210, 225);
  sephSong2 [8] = sephiSong2.crop(630, 225, 210, 225);
  sephSong2 [9] = sephiSong2.crop(840, 225, 210, 225);
  sephSong2 [10] = sephiSong2.crop(0, 450, 210, 225);
  sephSong2 [11] = sephiSong2.crop(210, 450, 210, 225);
  sephSong2 [12] = sephiSong2.crop(420, 450, 210, 225);
  sephSong2 [13] = sephiSong2.crop(630, 450, 210, 225);
  sephSong2 [14] = sephiSong2.crop(840, 450, 210, 225);
  sephSong2 [15] = sephiSong2.crop(0, 675, 210, 225);
  sephSong2 [16] = sephiSong2.crop(210, 675, 210, 225);
  sephSong2 [17] = sephiSong2.crop(420, 675, 210, 225);
  sephSong2 [18] = sephiSong2.crop(630, 675, 210, 225);
  
  sephSnD = new BufferedImage[4];
  sephSnD [0] = sephiSnD.crop(0, 0, 216, 216);
  sephSnD [1] = sephiSnD.crop(0, 0, 216, 216);
  sephSnD [2] = sephiSnD.crop(216, 0, 216, 216);
  sephSnD [3] = sephiSnD.crop(216, 0, 216, 216);
  
  sephSnD2 = new BufferedImage[4];
  sephSnD2 [0] = sephiSnD.crop(432, 0, 216, 216);
  sephSnD2 [1] = sephiSnD.crop(432, 0, 216, 216);
  sephSnD2 [2] = sephiSnD.crop(648, 0, 216, 216);
  sephSnD2 [3] = sephiSnD.crop(648, 0, 216, 216);

  sephSnD3 = new BufferedImage[4];
  sephSnD3 [0] = sephiSnD.crop(0, 216, 216, 216);
  sephSnD3 [1] = sephiSnD.crop(0, 216, 216, 216);
  sephSnD3 [2] = sephiSnD.crop(216, 216, 216, 216);
  sephSnD3 [3] = sephiSnD.crop(216, 216, 216, 216);
  
  sephSnD4 = new BufferedImage[4];
  sephSnD4 [0] = sephiSnD.crop(432, 216, 216, 216);
  sephSnD4 [1] = sephiSnD.crop(648, 216, 216, 216);
  sephSnD4 [2] = sephiSnD.crop(0, 432, 216, 216);
  sephSnD4 [3] = sephiSnD.crop(0, 432, 216, 216);
  
  sephSnD5 = new BufferedImage[4];
  sephSnD5 [0] = sephiSnD.crop(216, 432, 216, 216);
  sephSnD5 [1] = sephiSnD.crop(432, 432, 216, 216);
  sephSnD5 [2] = sephiSnD.crop(648, 432, 216, 216);
  sephSnD5 [3] = sephiSnD.crop(0, 648, 216, 216);
  
  sephD = new BufferedImage[5];
  sephD [0] = sephi3.crop(0, 0, 600, 450);
  sephD [1] = sephi3.crop(600, 0, 600, 450);
  sephD [2] = sephi3.crop(1200, 0, 600, 450);
  sephD [3] = sephi3.crop(1800, 0, 600, 450);
  sephD [4] = sephi3.crop(2400, 0, 600, 450);
  
  sephD2 = new BufferedImage[5];
  sephD2 [0] = sephi3.crop(0, 450, 600, 450);
  sephD2 [1] = sephi3.crop(600, 450, 600, 450);
  sephD2 [2] = sephi3.crop(1200, 450, 600, 450);
  sephD2 [3] = sephi3.crop(1800, 450, 600, 450);
  sephD2 [4] = sephi3.crop(2400, 450, 600, 450);
  
  sephCA = new BufferedImage[9];
  sephCA [0] = sephi3.crop(0, 900, 600, 450);
  sephCA [1] = sephi3.crop(600, 900, 600, 450);
  sephCA [2] = sephi3.crop(1200, 900, 600, 450);
  sephCA [3] = sephi3.crop(1800, 900, 600, 450);
  sephCA [4] = sephi3.crop(2400, 900, 600, 450);
  sephCA [5] = sephi3.crop(0, 1350, 600, 450);
  sephCA [6] = sephi3.crop(600, 1350, 600, 450);
  sephCA [7] = sephi3.crop(1200, 1350, 600, 450);
  sephCA [8] = sephi3.crop(1800, 1350, 600, 450);
  
  sephCA2 = new BufferedImage[9];
  sephCA2 [0] = sephi3.crop(2400, 1350, 600, 450);
  sephCA2 [1] = sephi3.crop(0, 1800, 600, 450);
  sephCA2 [2] = sephi3.crop(600, 1800, 600, 450);
  sephCA2 [3] = sephi3.crop(1200, 1800, 600, 450);
  sephCA2 [4] = sephi3.crop(1800, 1800, 600, 450);
  sephCA2 [5] = sephi3.crop(2400, 1800, 600, 450);
  sephCA2 [6] = sephi3.crop(0, 2250, 600, 450);
  sephCA2 [7] = sephi3.crop(600, 2250, 600, 450);
  sephCA2 [8] = sephi3.crop(1200, 2250, 600, 450);
  
  sephCS = new BufferedImage[8];
  sephCS [0] = sephi2.crop(0, 0, 192, 192);
  sephCS [1] = sephi2.crop(192, 0, 192, 192);
  sephCS [2] = sephi2.crop(384, 0, 192, 192);
  sephCS [3] = sephi2.crop(576, 0, 192, 192);
  sephCS [4] = sephi2.crop(0, 192, 192, 192);
  sephCS [5] = sephi2.crop(192, 192, 192, 192);
  sephCS [6] = sephi2.crop(384, 192, 192, 192);
  sephCS [7] = sephi2.crop(576, 192, 192, 192);
  
  sephCS2 = new BufferedImage[8];
  sephCS2 [0] = sephi2.crop(0, 384, 192, 192);
  sephCS2 [1] = sephi2.crop(192, 384, 192, 192);
  sephCS2 [2] = sephi2.crop(384, 384, 192, 192);
  sephCS2 [3] = sephi2.crop(576, 384, 192, 192);
  sephCS2 [4] = sephi2.crop(0, 576, 192, 192);
  sephCS2 [5] = sephi2.crop(192, 576, 192, 192);
  sephCS2 [6] = sephi2.crop(384, 576, 192, 192);
  sephCS2 [7] = sephi2.crop(576, 576, 192, 192);
  
  sephF = new BufferedImage[4];
  sephF[0] = sephi1.crop(384, 0, 192, 192);
  sephF[1] = sephi1.crop(576, 0, 192, 192);
  sephF[2] = sephi1.crop(384, 192, 192, 192);
  sephF[3] = sephi1.crop(576, 192, 192, 192);
  
  sephF2 = new BufferedImage[4];
  sephF2[0] = sephi1.crop(768, 0, 192, 192);
  sephF2[1] = sephi1.crop(960, 0, 192, 192);
  sephF2[2] = sephi1.crop(768, 192, 192, 192);
  sephF2[3] = sephi1.crop(960, 192, 192, 192);
  
  sephS = new BufferedImage[3];
  sephS[0] = sephi1.crop(0, 384, 192, 192);
  sephS[1] = sephi1.crop(192, 384, 192, 192);
  sephS[2] = sephi1.crop(0, 576, 192, 192);
  
  sephS2 = new BufferedImage[3];
  sephS2[0] = sephi1.crop(384, 384, 192, 192);
  sephS2[1] = sephi1.crop(576, 384, 192, 192);
  sephS2[2] = sephi1.crop(576, 576, 192, 192);
  
  //grayson stuff 
  
  grayAbils = new BufferedImage[8];
  grayAbils[0] = graysAbils.crop(0, 0, 64, 64);
  grayAbils[1] = graysAbils.crop(64, 64, 64, 64);
  grayAbils[2] = graysAbils.crop(64, 0, 64, 64);
  grayAbils[3] = graysAbils.crop(0, 128, 64, 64);
  grayAbils[4] = graysAbils.crop(0, 64, 64, 64);   
  grayAbils[5] = graysAbils.crop(64, 128, 64, 64);
  grayAbils[6] = graysAbils.crop(0, 192, 64, 64);
  grayAbils[7] = graysAbils.crop(64, 192, 64, 64);
  
  grayN = new BufferedImage[2];
  grayN [0] = grays1.crop(0, 0, 192, 192);
  grayN [1] = grays1.crop(0, 192, 192, 192);
  
  grayN2 = new BufferedImage[2];
  grayN2 [0] = grays1.crop(192, 0, 192, 192);
  grayN2 [1] = grays1.crop(192, 192, 192, 192);
  
  grayML = new BufferedImage[3];
  grayML [0] = grays1.crop(384, 768, 192, 192);
  grayML [1] = grays1.crop(576, 768, 192, 192);
  grayML [2] = grays1.crop(384, 960, 192, 192);
  
  grayMR = new BufferedImage[3];
  grayMR [0] = grays1.crop(768, 768, 192, 192);
  grayMR [1] = grays1.crop(960, 768, 192, 192);
  grayMR [2] = grays1.crop(768, 960, 192, 192);
  
  grayMU = new BufferedImage[3];
  grayMU [0] = grays1.crop(768, 384, 192, 192);
  grayMU [1] = grays1.crop(960, 384, 192, 192);
  grayMU [2] = grays1.crop(768, 576, 192, 192);
  
  grayMD = new BufferedImage[3];
  grayMD [0] = grays1.crop(0, 768, 192, 192);
  grayMD [1] = grays1.crop(192, 768, 192, 192);
  grayMD [2] = grays1.crop(0, 960, 192, 192);
  
  grayLA = new BufferedImage[7];
  grayLA [0] = graysLA.crop(0, 0, 270, 150);
  grayLA [1] = graysLA.crop(270, 0, 270, 150);
  grayLA [2] = graysLA.crop(0, 150, 270, 150);
  grayLA [3] = graysLA.crop(270, 150, 270, 150);
  grayLA [4] = graysLA.crop(0, 300, 270, 150);
  grayLA [5] = graysLA.crop(270, 300, 270, 150);
  grayLA [6] = graysLA.crop (0, 450, 270, 150);
  
  grayLA2 = new BufferedImage[7];
  grayLA2 [0] = graysLA2.crop(0, 0, 270, 150);
  grayLA2 [1] = graysLA2.crop(270, 0, 270, 150);
  grayLA2 [2] = graysLA2.crop(0, 150, 270, 150);
  grayLA2 [3] = graysLA2.crop(270, 150, 270, 150);
  grayLA2 [4] = graysLA2.crop(0, 300, 270, 150);
  grayLA2 [5] = graysLA2.crop(270, 300, 270, 150);
  grayLA2 [6] = graysLA2.crop (0, 450, 270, 150);
  
  grayHA = new BufferedImage[7];
  grayHA [0] = graysHA.crop(0, 0, 270, 150);
  grayHA [1] = graysHA.crop(270, 0, 270, 150);
  grayHA [2] = graysHA.crop(0, 150, 270, 150);
  grayHA [3] = graysHA.crop(270, 150, 270, 150);
  grayHA [4] = graysHA.crop(0, 300, 270, 150);
  grayHA [5] = graysHA.crop(270, 300, 270, 150);
  grayHA [6] = graysHA.crop(0, 450, 270, 150);
  
  grayHA2 = new BufferedImage[7];
  grayHA2 [0] = graysHA2.crop(0, 0, 270, 150);
  grayHA2 [1] = graysHA2.crop(270, 0, 270, 150);
  grayHA2 [2] = graysHA2.crop(0, 150, 270, 150);
  grayHA2 [3] = graysHA2.crop(270, 150, 270, 150);
  grayHA2 [4] = graysHA2.crop(0, 300, 270, 150);
  grayHA2 [5] = graysHA2.crop(270, 300, 270, 150);
  grayHA2 [6] = graysHA2.crop(0, 450, 270, 150);
  
  graySS = new BufferedImage[11];
  graySS [0] = graysSS.crop(0, 0, 300, 210);
  graySS [1] = graysSS.crop(600, 0, 300, 210);
  graySS [2] = graysSS.crop(0, 210, 300, 210);
  graySS [3] = graysSS.crop(300, 210, 300, 210);
  graySS [4] = graysSS.crop(600, 210, 300, 210);
  graySS [5] = graysSS.crop(0, 420, 300, 210);
  graySS [6] = graysSS.crop(300, 420, 300, 210);
  graySS [7] = graysSS.crop(600, 420, 300, 210);
  graySS [8] = graysSS.crop(0, 630, 300, 210);
  graySS [9] = graysSS.crop(300, 630, 300, 210);
  graySS [10] = graysSS.crop(600, 630, 300, 210);
  
  graySS2 = new BufferedImage[11];
  graySS2 [0] = graysSS2.crop(0, 0, 300, 210);
  graySS2 [1] = graysSS2.crop(600, 0, 300, 210);
  graySS2 [2] = graysSS2.crop(0, 210, 300, 210);
  graySS2 [3] = graysSS2.crop(300, 210, 300, 210);
  graySS2 [4] = graysSS2.crop(600, 210, 300, 210);
  graySS2 [5] = graysSS2.crop(0, 420, 300, 210);
  graySS2 [6] = graysSS2.crop(300, 420, 300, 210);
  graySS2 [7] = graysSS2.crop(600, 420, 300, 210);
  graySS2 [8] = graysSS2.crop(0, 630, 300, 210);
  graySS2 [9] = graysSS2.crop(300, 630, 300, 210);
  graySS2 [10] = graysSS2.crop(600, 630, 300, 210);
  
  grayRS = new BufferedImage[12];
  grayRS [0] = graysRS.crop(0, 0, 390, 210);
  grayRS [1] = graysRS.crop(390, 0, 390, 210);
  grayRS [2] = graysRS.crop(780, 0, 390, 210);
  grayRS [3] = graysRS.crop(0, 210, 390, 210);
  grayRS [4] = graysRS.crop(390, 210, 390, 210);
  grayRS [5] = graysRS.crop(780, 210, 390, 210);
  grayRS [6] = graysRS.crop(0, 420, 390, 210);
  grayRS [7] = graysRS.crop(390, 420, 390, 210);
  grayRS [8] = graysRS.crop(780, 420, 390, 210);
  grayRS [9] = graysRS.crop(0, 630, 390, 210);
  grayRS [10] = graysRS.crop(390, 630, 390, 210);
  grayRS [11] = graysRS.crop(780, 630, 390, 210);
    
  grayRS2 = new BufferedImage[12];
  grayRS2 [0] = graysRS2.crop(0, 0, 390, 210);
  grayRS2 [1] = graysRS2.crop(390, 0, 390, 210);
  grayRS2 [2] = graysRS2.crop(780, 0, 390, 210);
  grayRS2 [3] = graysRS2.crop(0, 210, 390, 210);
  grayRS2 [4] = graysRS2.crop(390, 210, 390, 210);
  grayRS2 [5] = graysRS2.crop(780, 210, 390, 210);
  grayRS2 [6] = graysRS2.crop(0, 420, 390, 210);
  grayRS2 [7] = graysRS2.crop(390, 420, 390, 210);
  grayRS2 [8] = graysRS2.crop(780, 420, 390, 210);
  grayRS2 [9] = graysRS2.crop(0, 630, 390, 210);
  grayRS2 [10] = graysRS2.crop(390, 630, 390, 210);
  grayRS2 [11] = graysRS2.crop(780, 630, 390, 210);
  
  grayR = new BufferedImage[4];
  grayR [0] = grays2.crop(768, 384, 192, 192);
  grayR [1] = grays2.crop(0, 576, 192, 192);
  grayR [2] = grays2.crop(192, 576, 192, 192);
  grayR [3] = grays2.crop(384, 576, 192, 192);
  
  grayR2 = new BufferedImage[4];
  grayR2 [0] = grays2.crop(576, 576, 192, 192);
  grayR2 [1] = grays2.crop(768, 576, 192, 192);
  grayR2 [2] = grays2.crop(0, 768, 192, 192);
  grayR2 [3] = grays2.crop(192, 768, 192, 192);
  
  graySC = new BufferedImage[7];
  graySC [0] = graysSC.crop(0, 0, 330, 210);
  graySC [1] = graysSC.crop(330, 0, 330, 210);
  graySC [2] = graysSC.crop(0, 210, 330, 210);
  graySC [3] = graysSC.crop(330, 210, 330, 210);
  graySC [4] = graysSC.crop(0, 420, 330, 210);
  graySC [5] = graysSC.crop(330, 420, 330, 210);
  graySC [6] = graysSC.crop(0, 630, 330, 210);
  
  graySC2 = new BufferedImage[7];
  graySC2 [0] = graysSC2.crop(0, 0, 330, 210);
  graySC2 [1] = graysSC2.crop(330, 0, 330, 210);
  graySC2 [2] = graysSC2.crop(0, 210, 330, 210);
  graySC2 [3] = graysSC2.crop(330, 210, 330, 210);
  graySC2 [4] = graysSC2.crop(0, 420, 330, 210);
  graySC2 [5] = graysSC2.crop(330, 420, 330, 210);
  graySC2 [6] = graysSC2.crop(0, 630, 330, 210);
  
  grayUC = new BufferedImage[10];
  grayUC [0] = graysUC.crop(0, 0, 240, 240);
  grayUC [1] = graysUC.crop(240, 0, 240, 240);
  grayUC [2] = graysUC.crop(480, 0, 240, 240);
  grayUC [3] = graysUC.crop(0, 240, 240, 240);
  grayUC [4] = graysUC.crop(240, 240, 240, 240);
  grayUC [5] = graysUC.crop(480, 240, 240, 240);
  grayUC [6] = graysUC.crop(0, 480, 240, 240);
  grayUC [7] = graysUC.crop(240, 480, 240, 240);
  grayUC [8] = graysUC.crop(480, 480, 240, 240);
  grayUC [9] = graysUC.crop(0, 720, 240, 240);
  
  grayUC2 = new BufferedImage[10];
  grayUC2 [0] = graysUC2.crop(0, 0, 240, 240);
  grayUC2 [1] = graysUC2.crop(240, 0, 240, 240);
  grayUC2 [2] = graysUC2.crop(480, 0, 240, 240);
  grayUC2 [3] = graysUC2.crop(0, 240, 240, 240);
  grayUC2 [4] = graysUC2.crop(240, 240, 240, 240);
  grayUC2 [5] = graysUC2.crop(480, 240, 240, 240);
  grayUC2 [6] = graysUC2.crop(0, 480, 240, 240);
  grayUC2 [7] = graysUC2.crop(240, 480, 240, 240);
  grayUC2 [8] = graysUC2.crop(480, 480, 240, 240);
  grayUC2 [9] = graysUC2.crop(0, 720, 240, 240);
  
  grayE = new BufferedImage[6];
  grayE [0] = graysEN.crop(0, 0, 240, 240);
  grayE [1] = graysEN.crop(240, 0, 240, 240);
  grayE [2] = graysEN.crop(0, 240, 240, 240);
  grayE [3] = graysEN.crop(240, 240, 240, 240);
  grayE [4] = graysEN.crop(0, 480, 240, 240);
  grayE [5] = graysEN.crop(240, 480, 240, 240);
  
  grayF = new BufferedImage[3];
  grayF[0] = grays1.crop(384, 0, 192, 192);
  grayF[1] = grays1.crop(576, 0, 192, 192);
  grayF[2] = grays1.crop(384, 192, 192, 192);
  
  grayF2 = new BufferedImage[3];
  grayF2[0] = grays1.crop(768, 0, 192, 192);
  grayF2[1] = grays1.crop(960, 0, 192, 192);
  grayF2[2] = grays1.crop(768, 192, 192, 192);
  
  grayS = new BufferedImage[3];
  grayS[0] = grays1.crop(0, 384, 192, 192);
  grayS[1] = grays1.crop(192, 384, 192, 192);
  grayS[2] = grays1.crop(0, 576, 192, 192); 
  
  grayS2 = new BufferedImage[3];
  grayS2[0] = grays1.crop(384, 384, 192, 192);
  grayS2[1] = grays1.crop(576, 384, 192, 192);
  grayS2[2] = grays1.crop(384, 576, 192, 192); 
  
  arunLia = new BufferedImage[4];
  arunLia [0] = arunlia.crop(0, 0, 600, 600);
  arunLia [1] = arunlia.crop(600, 0, 600, 600);
  arunLia [2] = arunlia.crop(0, 600, 600, 600);
  arunLia [3] = arunlia.crop(600, 600, 600, 600);
  
  //Luxaar Stuff
  luxN = new BufferedImage[2];
  luxN [0] = lux1.crop(0, 0, 192, 192);
  luxN [1] = lux1.crop(0, 192, 192, 192);
  
  luxN2 = new BufferedImage[2];
  luxN2 [0] = lux1.crop(192, 0, 192, 192);
  luxN2 [1] = lux1.crop(192, 192, 192, 192);
  
  luxML = new BufferedImage[3];
  luxML [0] = lux1.crop(384, 384, 192, 192);
  luxML [1] = lux1.crop(576, 384, 192, 192);
  luxML [2] = lux1.crop(384, 576, 192, 192);
  
  luxMR = new BufferedImage[3];
  luxMR [0] = lux1.crop(0, 384, 192, 192);
  luxMR [1] = lux1.crop(192, 384, 192, 192);
  luxMR [2] = lux1.crop(0, 576, 192, 192);
  
  luxMU = new BufferedImage[3];
  luxMU [0] = lux1.crop(768, 0, 192, 192);
  luxMU [1] = lux1.crop(960, 0, 192, 192);
  luxMU [2] = lux1.crop(768, 192, 192, 192);
  
  luxMD = new BufferedImage[3];
  luxMD [0] = lux1.crop(384, 0, 192, 192);
  luxMD [1] = lux1.crop(576, 0, 192, 192);
  luxMD [2] = lux1.crop(384, 192, 192, 192);
  
  luxF = new BufferedImage[3];
  luxF [0] = lux1.crop(384, 768, 192, 192);
  luxF [1] = lux1.crop(576, 768, 192, 192);
  luxF [2] = lux1.crop(384, 960, 192, 192);
    
  luxF2 = new BufferedImage[3];
  luxF2 [0] = lux1.crop(960, 768, 192, 192);
  luxF2 [1] = lux1.crop(768, 960, 192, 192);
  luxF2 [2] = lux1.crop(960, 768, 192, 192);
  
  luxS = new BufferedImage[3];
  luxS[0] = lux1.crop(0, 768, 192, 192);
  luxS[1] = lux1.crop(192, 768, 192, 192);
  luxS[2] = lux1.crop(0, 960, 192, 192); 
  
  luxS2 = new BufferedImage[3];
  luxS2[0] = lux1.crop(768, 384, 192, 192);
  luxS2[1] = lux1.crop(960, 384, 192, 192);
  luxS2[2] = lux1.crop(768, 576, 192, 192); 
  
  luxLA = new BufferedImage[8];
  luxLA [0] = luxaLA.crop(0, 0, 192, 192);
  luxLA [1] = luxaLA.crop(192, 0, 192, 192);
  luxLA [2] = luxaLA.crop(384, 0, 192, 192);
  luxLA [3] = luxaLA.crop(0, 192, 192, 192);
  luxLA [4] = luxaLA.crop(192, 192, 192, 192);
  luxLA [5] = luxaLA.crop(384, 192, 192, 192);
  luxLA [6] = luxaLA.crop(0, 384, 192, 192);
  luxLA [7] = luxaLA.crop(192, 384, 192, 192);
  
  luxLA2 = new BufferedImage[8];
  luxLA2 [0] = luxaLA2.crop(0, 0, 192, 192);
  luxLA2 [1] = luxaLA2.crop(192, 0, 192, 192);
  luxLA2 [2] = luxaLA2.crop(384, 0, 192, 192);
  luxLA2 [3] = luxaLA2.crop(0, 192, 192, 192);
  luxLA2 [4] = luxaLA2.crop(192, 192, 192, 192);
  luxLA2 [5] = luxaLA2.crop(384, 192, 192, 192);
  luxLA2 [6] = luxaLA2.crop(0, 384, 192, 192);
  luxLA2 [7] = luxaLA2.crop(192, 384, 192, 192);
  
  luxHA = new BufferedImage[15];
  luxHA [0] = luxaHA.crop(0, 0, 192, 192);
  luxHA [1] = luxaHA.crop(192, 0, 192, 192);
  luxHA [2] = luxaHA.crop(384, 0, 192, 192);
  luxHA [3] = luxaHA.crop(576, 0, 192, 192);
  luxHA [4] = luxaHA.crop(0, 192, 192, 192);
  luxHA [5] = luxaHA.crop(192, 192, 192, 192);
  luxHA [6] = luxaHA.crop(384, 192, 192, 192);
  luxHA [7] = luxaHA.crop(576, 192, 192, 192);
  luxHA [8] = luxaHA.crop(0, 384, 192, 192);
  luxHA [9] = luxaHA.crop(192, 384, 192, 192);
  luxHA [10] = luxaHA.crop(384, 384, 192, 192);
  luxHA [11] = luxaHA.crop(576, 384, 192, 192);
  luxHA [12] = luxaHA.crop(0, 576, 192, 192);
  luxHA [13] = luxaHA.crop(192, 576, 192, 192);
  luxHA [14] = luxaHA.crop(384, 576, 192, 192);
  
  luxHA2 = new BufferedImage[15];
  luxHA2 [0] = luxaHA2.crop(0, 0, 192, 192);
  luxHA2 [1] = luxaHA2.crop(192, 0, 192, 192);
  luxHA2 [2] = luxaHA2.crop(384, 0, 192, 192);
  luxHA2 [3] = luxaHA2.crop(576, 0, 192, 192);
  luxHA2 [4] = luxaHA2.crop(0, 192, 192, 192);
  luxHA2 [5] = luxaHA2.crop(192, 192, 192, 192);
  luxHA2 [6] = luxaHA2.crop(384, 192, 192, 192);
  luxHA2 [7] = luxaHA2.crop(576, 192, 192, 192);
  luxHA2 [8] = luxaHA2.crop(0, 384, 192, 192);
  luxHA2 [9] = luxaHA2.crop(192, 384, 192, 192);
  luxHA2 [10] = luxaHA2.crop(384, 384, 192, 192);
  luxHA2 [11] = luxaHA2.crop(576, 384, 192, 192);
  luxHA2 [12] = luxaHA2.crop(0, 576, 192, 192);
  luxHA2 [13] = luxaHA2.crop(192, 576, 192, 192);
  luxHA2 [14] = luxaHA2.crop(384, 576, 192, 192);
  
  luxLC = new BufferedImage[5];
  luxLC [0] = luxaLC.crop(0, 0, 225, 345);
  luxLC [1] = luxaLC.crop(225, 0, 225, 345);
  luxLC [2] = luxaLC.crop(450, 0, 225, 345);
  luxLC [3] = luxaLC.crop(675, 0, 225, 345);
  luxLC [4] = luxaLC.crop(0, 345, 225, 345);
  
  luxLC2 = new BufferedImage[5];
  luxLC2 [0] = luxaLC.crop(225, 345, 225, 345);
  luxLC2 [1] = luxaLC.crop(450, 345, 225, 345);
  luxLC2 [2] = luxaLC.crop(675, 345, 225, 345);
  luxLC2 [3] = luxaLC.crop(0, 690, 225, 345);
  luxLC2 [4] = luxaLC.crop(225, 690, 225, 345);
  
  luxRS = new BufferedImage[8];
  luxRS [0] = luxaCF.crop(0, 0, 192, 192);
  luxRS [1] = luxaCF.crop(192, 0, 192, 192);
  luxRS [2] = luxaCF.crop(384, 0, 192, 192);
  luxRS [3] = luxaCF.crop(576, 0, 192, 192);
  luxRS [4] = luxaCF.crop(0, 192, 192, 192);
  luxRS [5] = luxaCF.crop(192, 192, 192, 192);
  luxRS [6] = luxaCF.crop(384, 192, 192, 192);
  luxRS [7] = luxaCF.crop(576, 192, 192, 192);
  
  luxRS2 = new BufferedImage[8];
  luxRS2 [0] = luxaCF.crop(0, 384, 192, 192);
  luxRS2 [1] = luxaCF.crop(192, 384, 192, 192);
  luxRS2 [2] = luxaCF.crop(384, 384, 192, 192);
  luxRS2 [3] = luxaCF.crop(576, 384, 192, 192);
  luxRS2 [4] = luxaCF.crop(0, 576, 192, 192);
  luxRS2 [5] = luxaCF.crop(192, 576, 192, 192);
  luxRS2 [6] = luxaCF.crop(384, 576, 192, 192);
  luxRS2 [7] = luxaCF.crop(576, 576, 192, 192);
  
  luxFC = new BufferedImage[10];
  luxFC [0] = luxaFC.crop(0, 0, 200, 270);
  luxFC [1] = luxaFC.crop(200, 0, 200, 270);
  luxFC [2] = luxaFC.crop(400, 0, 200, 270);
  luxFC [3] = luxaFC.crop(600, 0, 200, 270);
  luxFC [4] = luxaFC.crop(800, 0, 200, 270);
  luxFC [5] = luxaFC.crop(0, 270, 200, 270);
  luxFC [6] = luxaFC.crop(200, 270, 200, 270);
  luxFC [7] = luxaFC.crop(400, 270, 200, 270);
  luxFC [8] = luxaFC.crop(600, 270, 200, 270);
  luxFC [9] = luxaFC.crop(800, 270, 200, 270);
  
  luxFC2 = new BufferedImage[10];
  luxFC2 [0] = luxaFC.crop(0, 540, 200, 270);
  luxFC2 [1] = luxaFC.crop(200, 540, 200, 270);
  luxFC2 [2] = luxaFC.crop(400, 540, 200, 270);
  luxFC2 [3] = luxaFC.crop(600, 540, 200, 270);
  luxFC2 [4] = luxaFC.crop(800, 540, 200, 270);
  luxFC2 [5] = luxaFC.crop(0, 810, 200, 270);
  luxFC2 [6] = luxaFC.crop(200, 810, 200, 270);
  luxFC2 [7] = luxaFC.crop(400, 810, 200, 270);
  luxFC2 [8] = luxaFC.crop(600, 810, 200, 270);
  luxFC2 [9] = luxaFC.crop(800, 810, 200, 270);
  
  luxSV = new BufferedImage [6];
  luxSV [0] = luxaSV.crop(0, 0, 192, 192);
  luxSV [1] = luxaSV.crop(192, 0, 192, 192);
  luxSV [2] = luxaSV.crop(0, 192, 192, 192);
  luxSV [3] = luxaSV.crop(192, 192, 192, 192);
  luxSV [4] = luxaSV.crop(0, 384, 192, 192);
  luxSV [5] = luxaSV.crop(0, 384, 192, 192);
  
  luxSV2 = new BufferedImage [6];
  luxSV2 [0] = luxaSV2.crop(0, 0, 192, 192);
  luxSV2 [1] = luxaSV2.crop(192, 0, 192, 192);
  luxSV2 [2] = luxaSV2.crop(0, 192, 192, 192);
  luxSV2 [3] = luxaSV2.crop(192, 192, 192, 192);
  luxSV2 [4] = luxaSV2.crop(0, 384, 192, 192);
  luxSV2 [5] = luxaSV2.crop(0, 384, 192, 192);
  
  luxV = new BufferedImage [13];
  luxV [0] = luxaV.crop(0, 0, 200, 200);
  luxV [1] = luxaV.crop(200, 0, 200, 200);
  luxV [2] = luxaV.crop(400, 0, 200, 200);
  luxV [3] = luxaV.crop(600, 0, 200, 200);
  luxV [4] = luxaV.crop(0, 200, 200, 200);
  luxV [5] = luxaV.crop(200, 200, 200, 200);
  luxV [6] = luxaV.crop(400, 200, 200, 200);
  luxV [7] = luxaV.crop(600, 200, 200, 200);
  luxV [8] = luxaV.crop(0, 400, 200, 200);
  luxV [9] = luxaV.crop(200, 400, 200, 200);
  luxV [10] = luxaV.crop(400, 400, 200, 200);
  luxV [11] = luxaV.crop(600, 400, 200, 200);
  luxV [12] = luxaV.crop(0, 600, 200, 200);
  
  luxV2 = new BufferedImage [13];
  luxV2 [0] = luxaV2.crop(0, 0, 200, 200);
  luxV2 [1] = luxaV2.crop(200, 0, 200, 200);
  luxV2 [2] = luxaV2.crop(400, 0, 200, 200);
  luxV2 [3] = luxaV2.crop(600, 0, 200, 200);
  luxV2 [4] = luxaV2.crop(0, 200, 200, 200);
  luxV2 [5] = luxaV2.crop(200, 200, 200, 200);
  luxV2 [6] = luxaV2.crop(400, 200, 200, 200);
  luxV2 [7] = luxaV2.crop(600, 200, 200, 200);
  luxV2 [8] = luxaV2.crop(0, 400, 200, 200);
  luxV2 [9] = luxaV2.crop(200, 400, 200, 200);
  luxV2 [10] = luxaV2.crop(400, 400, 200, 200);
  luxV2 [11] = luxaV2.crop(600, 400, 200, 200);
  luxV2 [12] = luxaV2.crop(0, 600, 200, 200);
  
  luxUC = new BufferedImage [17];
  luxUC [0] = luxaUC.crop(0, 0, 192, 192);
  luxUC [1] = luxaUC.crop(192, 0, 192, 192);
  luxUC [2] = luxaUC.crop(384, 0, 192, 192);
  luxUC [3] = luxaUC.crop(576, 0, 192, 192);  
  luxUC [4] = luxaUC.crop(0, 192, 192, 192);
  luxUC [5] = luxaUC.crop(192, 192, 192, 192);
  luxUC [6] = luxaUC.crop(384, 192, 192, 192);
  luxUC [7] = luxaUC.crop(576, 192, 192, 192);  
  luxUC [8] = luxaUC.crop(0, 384, 192, 192);
  luxUC [9] = luxaUC.crop(192, 384, 192, 192);
  luxUC [10] = luxaUC.crop(384, 384, 192, 192);
  luxUC [11] = luxaUC.crop(576, 384, 192, 192);  
  luxUC [12] = luxaUC.crop(0, 576, 192, 192);
  luxUC [13] = luxaUC.crop(192, 576, 192, 192);
  luxUC [14] = luxaUC.crop(384, 576, 192, 192);
  luxUC [15] = luxaUC.crop(576, 576, 192, 192); 
  luxUC [16] = luxaUC.crop(0, 768, 192, 192);  
  
  luxUC2 = new BufferedImage [17];
  luxUC2 [0] = luxaUC2.crop(0, 0, 192, 192);
  luxUC2 [1] = luxaUC2.crop(192, 0, 192, 192);
  luxUC2 [2] = luxaUC2.crop(384, 0, 192, 192);
  luxUC2 [3] = luxaUC2.crop(576, 0, 192, 192);
  luxUC2 [4] = luxaUC2.crop(0, 192, 192, 192);
  luxUC2 [5] = luxaUC2.crop(192, 192, 192, 192);
  luxUC2 [6] = luxaUC2.crop(384, 192, 192, 192);
  luxUC2 [7] = luxaUC2.crop(576, 192, 192, 192);
  luxUC2 [8] = luxaUC2.crop(0, 384, 192, 192);
  luxUC2 [9] = luxaUC2.crop(192, 384, 192, 192);
  luxUC2 [10] = luxaUC2.crop(384, 384, 192, 192);
  luxUC2 [11] = luxaUC2.crop(576, 384, 192, 192);
  luxUC2 [12] = luxaUC2.crop(0, 576, 192, 192);
  luxUC2 [13] = luxaUC2.crop(192, 576, 192, 192);
  luxUC2 [14] = luxaUC2.crop(384, 576, 192, 192);
  luxUC2 [15] = luxaUC2.crop(576, 576, 192, 192);
  luxUC2 [16] = luxaUC2.crop(0, 768, 192, 192);  
  
  luxVD = new BufferedImage [6];
  luxVD [0] = luxaVD.crop(0, 0, 720, 390);
  luxVD [1] = luxaVD.crop(720, 0, 720, 390);
  luxVD [2] = luxaVD.crop(0, 390, 720, 390);
  luxVD [3] = luxaVD.crop(720, 390, 720, 390);
  luxVD [4] = luxaVD.crop(0, 780, 720, 390);
  luxVD [5] = luxaVD.crop(720, 780, 720, 390);
          
  luxAR = new BufferedImage[2];
  luxAR[0] = luxaAR.crop(0, 0, 96, 96);
  luxAR[1] = luxaAR.crop(0, 96, 96, 96);
  
  luxAR2 = new BufferedImage[2];
  luxAR2[0] = luxaAR2.crop(0, 0, 96, 96);
  luxAR2[1] = luxaAR2.crop(0, 96, 96, 96);
  
  luxAbils = new BufferedImage[8];
  luxAbils[0] = luxaAbils.crop(0, 0, 64, 64);
  luxAbils[1] = luxaAbils.crop(64, 64, 64, 64);
  luxAbils[4] = luxaAbils.crop(64, 0, 64, 64);
  luxAbils[5] = luxaAbils.crop(0, 128, 64, 64);
  luxAbils[2] = luxaAbils.crop(0, 62, 64, 64);   
  luxAbils[3] = luxaAbils.crop(64, 126, 64, 64);
  luxAbils[6] = luxaAbils.crop(0, 192, 64, 64);
  luxAbils[7] = luxaAbils.crop(64, 192, 64, 64);
  
  taN = new BufferedImage[2];
  taN[0] = ta1.crop(0, 0, 192, 192);
  taN[1] = ta1.crop(0, 192, 192, 192);
  
  taN2 = new BufferedImage[2];
  taN2[0] = ta1.crop(192, 0, 192, 192);
  taN2[1] = ta1.crop(192, 192, 192, 192); 
  
  taML = new BufferedImage[3];
  taML [0] = ta1.crop(0, 384, 192, 192);
  taML [1] = ta1.crop(192, 384, 192, 192);
  taML [2] = ta1.crop(0, 576, 192, 192);
  
  taMR = new BufferedImage[3];
  taMR [0] = ta1.crop(384, 384, 192, 192);
  taMR [1] = ta1.crop(576, 384, 192, 192);
  taMR [2] = ta1.crop(384, 576, 192, 192);
  
  taMU = new BufferedImage[3];
  taMU [0] = ta1.crop(768, 384, 192, 192);
  taMU [1] = ta1.crop(960, 384, 192, 192);
  taMU [2] = ta1.crop(768, 576, 192, 192);
  
  taMD = new BufferedImage[3];
  taMD [0] = ta1.crop(0, 768, 192, 192);
  taMD [1] = ta1.crop(192, 768, 192, 192);
  taMD [2] = ta1.crop(0, 960, 192, 192);
  
  taS = new BufferedImage[3];
  taS[0] = ta1.crop(384, 768, 192, 192);
  taS[1] = ta1.crop(576, 768, 192, 192);
  taS[2] = ta1.crop(384, 960, 192, 192); 
  
  taS2 = new BufferedImage[3];
  taS2[0] = ta1.crop(768, 768, 192, 192);
  taS2[1] = ta1.crop(960, 768, 192, 192);
  taS2[2] = ta1.crop(768, 960, 192, 192);   
  
  taF = new BufferedImage[3];
  taF[0] = ta1.crop(384, 0, 192, 192);
  taF[1] = ta1.crop(576, 0, 192, 192);
  taF[2] = ta1.crop(384, 192, 192, 192); 
  
  taF2 = new BufferedImage[3];
  taF2[0] = ta1.crop(768, 0, 192, 192);
  taF2[1] = ta1.crop(960, 0, 192, 192);
  taF2[2] = ta1.crop(768, 192, 192, 192);  
  
  taLA = new BufferedImage[6];
  taLA[0] = ta2.crop(0, 0, 192, 192);
  taLA[1] = ta2.crop(192, 0, 192, 192);
  taLA[2] = ta2.crop(0, 192, 192, 192);
  taLA[3] = ta2.crop(192, 192, 192, 192);
  taLA[4] = ta2.crop(0, 384, 192, 192);
  taLA[5] = ta2.crop(192, 384, 192, 192);
  
  taLA2 = new BufferedImage[6];
  taLA2[0] = ta2.crop(384, 0, 192, 192);
  taLA2[1] = ta2.crop(576, 0, 192, 192);
  taLA2[2] = ta2.crop(384, 192, 192, 192);
  taLA2[3] = ta2.crop(576, 192, 192, 192);
  taLA2[4] = ta2.crop(384, 384, 192, 192);
  taLA2[5] = ta2.crop(576, 384, 192, 192);
  
  taHA = new BufferedImage[6];
  taHA[0] = ta2.crop(0, 576, 192, 192);
  taHA[1] = ta2.crop(192, 576, 192, 192);
  taHA[2] = ta2.crop(0, 768, 192, 192);
  taHA[3] = ta2.crop(192, 768, 192, 192);
  taHA[4] = ta2.crop(0, 960, 192, 192);
  taHA[5] = ta2.crop(192, 960, 192, 192);
  
  taHA2 = new BufferedImage[6];
  taHA2[0] = ta2.crop(384, 576, 192, 192);
  taHA2[1] = ta2.crop(576, 576, 192, 192);
  taHA2[2] = ta2.crop(384, 768, 192, 192);
  taHA2[3] = ta2.crop(576, 768, 192, 192);
  taHA2[4] = ta2.crop(384, 960, 192, 192);
  taHA2[5] = ta2.crop(576, 960, 192, 192);
  
  taFB = new BufferedImage[3];
  taFB[0] = tafFB.crop(0, 0, 192, 192);
  taFB[1] = tafFB.crop(192, 0, 192, 192);
  taFB[2] = tafFB.crop(0, 192, 192, 192);
  
  taFB2 = new BufferedImage[3];
  taFB2[0] = tafFB2.crop(0, 0, 192, 192);
  taFB2[1] = tafFB2.crop(192, 0, 192, 192);
  taFB2[2] = tafFB2.crop(0, 192, 192, 192);
  
  taFBH = new BufferedImage[3];
  taFBH[0] = tafFBH.crop(0, 0, 192, 192);
  taFBH[1] = tafFBH.crop(192, 0, 192, 192);
  taFBH[2] = tafFBH.crop(0, 192, 192, 192);
  
  taFBH2 = new BufferedImage[3];
  taFBH2[0] = tafFBH2.crop(0, 0, 192, 192);
  taFBH2[1] = tafFBH2.crop(192, 0, 192, 192);
  taFBH2[2] = tafFBH2.crop(0, 192, 192, 192);
  
  taSP = new BufferedImage[7];
  taSP[0] = tafSP.crop(0, 0, 192, 192);
  taSP[1] = tafSP.crop(192, 0, 192, 192);
  taSP[2] = tafSP.crop(384, 0, 192, 192);
  taSP[3] = tafSP.crop(0, 192, 192, 192);
  taSP[4] = tafSP.crop(192, 192, 192, 192);
  taSP[5] = tafSP.crop(384, 192, 192, 192);
  taSP[6] = tafSP.crop(0, 384, 192, 192);
  
  taSP2 = new BufferedImage[7];
  taSP2[0] = tafSP2.crop(0, 0, 192, 192);
  taSP2[1] = tafSP2.crop(192, 0, 192, 192);
  taSP2[2] = tafSP2.crop(384, 0, 192, 192);
  taSP2[3] = tafSP2.crop(0, 192, 192, 192);
  taSP2[4] = tafSP2.crop(192, 192, 192, 192);
  taSP2[5] = tafSP2.crop(384, 192, 192, 192);
  taSP2[6] = tafSP2.crop(0, 384, 192, 192);
  
  taFD = new BufferedImage[7];
  taFD [0] = tafFD.crop(0, 0, 384, 192);
  taFD [1] = tafFD.crop(384, 0, 384, 192);
  taFD [2] = tafFD.crop(768, 0, 384, 192);
  taFD [3] = tafFD.crop(0, 192, 384, 192);
  taFD [4] = tafFD.crop(384, 192, 384, 192);
  taFD [5] = tafFD.crop(768, 192, 384, 192);
  taFD [6] = tafFD.crop(0, 384, 384, 192);
  
  taFD2 = new BufferedImage[7];
  taFD2 [0] = tafFD.crop(384, 384, 384, 192);
  taFD2 [1] = tafFD.crop(768, 384, 384, 192);
  taFD2 [2] = tafFD.crop(0, 576, 384, 192);
  taFD2 [3] = tafFD.crop(384, 576, 384, 192);
  taFD2 [4] = tafFD.crop(768, 576, 384, 192);
  taFD2 [5] = tafFD.crop(0, 768, 384, 192);
  taFD2 [6] = tafFD.crop(384, 768, 384, 192);
  
  taSPP = new BufferedImage[3];
  taSPP[0] = tafSPP.crop(0, 0, 192, 192);
  taSPP[1] = tafSPP.crop(192, 0, 192, 192);
  taSPP[2] = tafSPP.crop(0, 192, 192, 192);
  
  taSPP2 = new BufferedImage[3];
  taSPP2[0] = tafSPP2.crop(0, 0, 192, 192);
  taSPP2[1] = tafSPP2.crop(192, 0, 192, 192);
  taSPP2[2] = tafSPP2.crop(0, 192, 192, 192);
  
  taSB = new BufferedImage[5];
  taSB[0] = tafSB.crop(0, 0, 192, 192);
  taSB[1] = tafSB.crop(192, 0, 192, 192);
  taSB[2] = tafSB.crop(0, 192, 192, 192);
  taSB[3] = tafSB.crop(192, 192, 192, 192);
  taSB[4] = tafSB.crop(0, 384, 192, 192);       
  
  taCC = new BufferedImage[6];
  taCC[0] = tafCC.crop(0, 0, 300, 192);
  taCC[1] = tafCC.crop(0, 0, 300, 192);
  taCC[2] = tafCC.crop(300, 0, 300, 192);
  taCC[3] = tafCC.crop(0, 192, 300, 192);
  taCC[4] = tafCC.crop(300, 192, 300, 192);
  taCC[5] = tafCC.crop(0, 384, 300, 192);
  
  taCC2 = new BufferedImage[6];
  taCC2[0] = tafCC2.crop(0, 0, 300, 192);
  taCC2[1] = tafCC2.crop(0, 0, 300, 192);
  taCC2[2] = tafCC2.crop(300, 0, 300, 192);
  taCC2[3] = tafCC2.crop(0, 192, 300, 192);
  taCC2[4] = tafCC2.crop(300, 192, 300, 192);
  taCC2[5] = tafCC2.crop(0, 384, 300, 192);
  
  taFC = new BufferedImage[7];
  taFC[0] = tafFC.crop(0, 0, 576, 576);
  taFC[1] = tafFC.crop(576, 0, 576, 576);
  taFC[2] = tafFC.crop(0, 0, 576, 576);
  taFC[3] = tafFC.crop(576, 576, 576, 576);
  taFC[4] = tafFC.crop(0, 576, 576, 576);
  taFC[5] = tafFC.crop(576, 576, 576, 576);
  taFC[6] = tafFC.crop(0, 1152, 576, 576);

  taFN = new BufferedImage[6];
  taFN[0] = ta4.crop(384, 192, 192, 192);
  taFN[1] = ta4.crop(576, 192, 192, 192);
  taFN[2] = ta4.crop(0, 384, 192, 192);
  taFN[3] = ta4.crop(192, 384, 192, 192);
  taFN[4] = ta4.crop(384, 384, 192, 192);
  taFN[5] = ta4.crop(576, 384, 192, 192);
  
  taFN2 = new BufferedImage[6];
  taFN2[0] = ta4.crop(0, 0, 192, 192);
  taFN2[1] = ta4.crop(192, 0, 192, 192);
  taFN2[2] = ta4.crop(384, 0, 192, 192);
  taFN2[3] = ta4.crop(576, 0, 192, 192);
  taFN2[4] = ta4.crop(0, 192, 192, 192);
  taFN2[5] = ta4.crop(192, 192, 192, 192);
  
  taPW = new BufferedImage[7];
  taPW[0] = tafPW.crop(0, 0, 192, 384);
  taPW[1] = tafPW.crop(192, 0, 192, 384);
  taPW[2] = tafPW.crop(384, 0, 192, 384);
  taPW[3] = tafPW.crop(576, 0, 192, 384);
  taPW[4] = tafPW.crop(0, 384, 192, 384);
  taPW[5] = tafPW.crop(192, 384, 192, 384);
  taPW[6] = tafPW.crop(384, 384, 192, 384);
  
  taMFB = new BufferedImage[8];
  taMFB[0] = tafMFB.crop(0, 0, 192, 192);
  taMFB[1] = tafMFB.crop(192, 0, 192, 192);
  taMFB[2] = tafMFB.crop(384, 0, 192, 192);
  taMFB[3] = tafMFB.crop(0, 192, 192, 192);
  taMFB[4] = tafMFB.crop(192, 192, 192, 192);
  taMFB[5] = tafMFB.crop(384, 192, 192, 192);
  taMFB[6] = tafMFB.crop(0, 384, 192, 192);
  taMFB[7] = tafMFB.crop(192, 384, 192, 192);
  
  taMFB2 = new BufferedImage[8];
  taMFB2[0] = tafMFB2.crop(0, 0, 192, 192);
  taMFB2[1] = tafMFB2.crop(192, 0, 192, 192);
  taMFB2[2] = tafMFB2.crop(384, 0, 192, 192);
  taMFB2[3] = tafMFB2.crop(0, 192, 192, 192);
  taMFB2[4] = tafMFB2.crop(192, 192, 192, 192);
  taMFB2[5] = tafMFB2.crop(384, 192, 192, 192);
  taMFB2[6] = tafMFB2.crop(0, 384, 192, 192);
  taMFB2[7] = tafMFB2.crop(192, 384, 192, 192);
  
  taB = new BufferedImage[6];
  taB [0] = tafF2.crop(0, 0, 192, 192);
  taB [1] = tafF2.crop(192, 0, 192, 192);
  taB [2] = tafF2.crop(384, 0, 192, 192);
  taB [3] = tafF2.crop(0, 192, 192, 192);
  taB [4] = tafF2.crop(192, 192, 192, 192);
  taB [5] = tafF2.crop(384, 192, 192, 192);
  
  taB2 = new BufferedImage[6];
  taB2 [0] = tafF2.crop(0, 384, 192, 192);
  taB2 [1] = tafF2.crop(192, 384, 192, 192);
  taB2 [2] = tafF2.crop(384, 384, 192, 192);
  taB2 [3] = tafF2.crop(0, 576, 192, 192);
  taB2 [4] = tafF2.crop(192, 576, 192, 192);
  taB2 [5] = tafF2.crop(384, 576, 192, 192);
   
  taFLA2 = new BufferedImage[4];
  taFLA2[0] = ta4.crop(0, 576, 192, 192);
  taFLA2[1] = ta4.crop(192, 576, 192, 192);
  taFLA2[2] = ta4.crop(384, 576, 192, 192);
  taFLA2[3] = ta4.crop(576, 576, 192, 192);
  
  taFLA = new BufferedImage[4];
  taFLA[0] = ta4.crop(0, 768, 192, 192);
  taFLA[1] = ta4.crop(192, 768, 192, 192);
  taFLA[2] = ta4.crop(384, 768, 192, 192);
  taFLA[3] = ta4.crop(576, 768, 192, 192);
  
  taFL = new BufferedImage[7];
  taFL[0] = ta3.crop(0, 0, 330, 240);
  taFL[1] = ta3.crop(330, 0, 330, 240);
  taFL[2] = ta3.crop(660, 0, 330, 240);
  taFL[3] = ta3.crop(990, 0, 330, 240);
  taFL[4] = ta3.crop(0, 240, 330, 240);
  taFL[5] = ta3.crop(330, 0, 330, 240);
  taFL[6] = ta3.crop(660, 0, 330, 240);
  
  taFL2 = new BufferedImage[7];
  taFL2[0] = ta3.crop(990, 240, 330, 240);
  taFL2[1] = ta3.crop(0, 480, 330, 240);
  taFL2[2] = ta3.crop(330, 480, 330, 240);
  taFL2[3] = ta3.crop(660, 480, 330, 240);
  taFL2[4] = ta3.crop(990, 480, 330, 240);
  taFL2[5] = ta3.crop(0, 480, 330, 240);
  taFL2[6] = ta3.crop(330, 480, 330, 240);
  
  taFG = new BufferedImage[6];
  taFG[0] = ta3.crop(660, 720, 330, 240);
  taFG[1] = ta3.crop(990, 720, 330, 240);
  taFG[2] = ta3.crop(0, 960, 330, 240);
  taFG[3] = ta3.crop(330, 960, 330, 240);
  taFG[4] = ta3.crop(660, 960, 330, 240);
  taFG[5] = ta3.crop(990, 960, 330, 240);
  
  taFG2 = new BufferedImage[6];
  taFG2[0] = ta3.crop(0, 1200, 330, 240);
  taFG2[1] = ta3.crop(330, 1200, 330, 240);
  taFG2[2] = ta3.crop(660, 1200, 330, 240);
  taFG2[3] = ta3.crop(990, 1200, 330, 240);
  taFG2[4] = ta3.crop(0, 1440, 330, 240);
  taFG2[5] = ta3.crop(330, 1440, 330, 240);
  
  tof = new BufferedImage[3];
  tof[0] = taftof.crop(0, 0, 192, 192);
  tof[1] = taftof.crop(192, 0, 192, 192);
  tof[2] = taftof.crop(0, 192, 192, 192);
  
  taAbils = new BufferedImage[8];
  taAbils[0] = tafAbils.crop(0, 0, 64, 64);
  taAbils[1] = tafAbils.crop(64, 64, 64, 64);
  taAbils[4] = tafAbils.crop(64, 0, 64, 64);
  taAbils[5] = tafAbils.crop(0, 128, 64, 64);
  taAbils[2] = tafAbils.crop(0, 64, 64, 64);   
  taAbils[3] = tafAbils.crop(64, 128, 64, 64);
  taAbils[6] = tafAbils.crop(0, 192, 64, 64);
  taAbils[7] = tafAbils.crop(64, 192, 64, 64);
  
  //iron fiend sprites
  ironN = new BufferedImage[2];
  ironN[0] = iron1.crop(0, 0, 192, 192);
  ironN[1] = iron1.crop(0, 192, 192, 192);
  
  ironN2 = new BufferedImage[2];
  ironN2[0] = iron1.crop(192, 0, 192, 192);
  ironN2[1] = iron1.crop(192, 192, 192, 192); 
  
  ironML = new BufferedImage[3];
  ironML [0] = iron1.crop(384, 0, 192, 192);
  ironML [1] = iron1.crop(576, 0, 192, 192);
  ironML [2] = iron1.crop(384, 192, 192, 192);
  
  ironMR = new BufferedImage[3];
  ironMR [0] = iron1.crop(768, 0, 192, 192);
  ironMR [1] = iron1.crop(960, 0, 192, 192);
  ironMR [2] = iron1.crop(768, 192, 192, 192);
  
  ironMU = new BufferedImage[3];
  ironMU [0] = iron1.crop(0, 384, 192, 192);
  ironMU [1] = iron1.crop(192, 384, 192, 192);
  ironMU [2] = iron1.crop(0, 576, 192, 192);
  
  ironMD = new BufferedImage[3];
  ironMD [0] = iron1.crop(384, 384, 192, 192);
  ironMD [1] = iron1.crop(576, 384, 192, 192);
  ironMD [2] = iron1.crop(384, 576, 192, 192);

  ironS = new BufferedImage[3];
  ironS[0] = iron1.crop(0, 768, 192, 192);
  ironS[1] = iron1.crop(192, 768, 192, 192);
  ironS[2] = iron1.crop(0, 960, 192, 192);   
  
  ironS2 = new BufferedImage[3];
  ironS2[0] = iron1.crop(768, 384, 192, 192);
  ironS2[1] = iron1.crop(960, 384, 192, 192);
  ironS2[2] = iron1.crop(768, 576, 192, 192); 
  
  ironHA = new BufferedImage[9];
  ironHA[0] = ironfHA.crop(0, 0, 216, 216);
  ironHA[1] = ironfHA.crop(216, 0, 216, 216);
  ironHA[2] = ironfHA.crop(432, 0, 216, 216);
  ironHA[3] = ironfHA.crop(0, 216, 216, 216);
  ironHA[4] = ironfHA.crop(216, 216, 216, 216);
  ironHA[5] = ironfHA.crop(432, 216, 216, 216);
  ironHA[6] = ironfHA.crop(0, 432, 216, 216);
  ironHA[7] = ironfHA.crop(216, 432, 216, 216);
  ironHA[8] = ironfHA.crop(384, 432, 216, 216);        
  
  ironHA2 = new BufferedImage[9];
  ironHA2[0] = ironfHA2.crop(0, 0, 216, 216);
  ironHA2[1] = ironfHA2.crop(216, 0, 216, 216);
  ironHA2[2] = ironfHA2.crop(432, 0, 216, 216);
  ironHA2[3] = ironfHA2.crop(0, 216, 216, 216);
  ironHA2[4] = ironfHA2.crop(216, 216, 216, 216);
  ironHA2[5] = ironfHA2.crop(432, 216, 216, 216);
  ironHA2[6] = ironfHA2.crop(0, 432, 216, 216);
  ironHA2[7] = ironfHA2.crop(216, 432, 216, 216);
  ironHA2[8] = ironfHA2.crop(432, 432, 216, 216);   
  
  ironWR = new BufferedImage[7];
  ironWR[0] = ironfWR.crop(0, 0, 300, 300);
  ironWR[1] = ironfWR.crop(300, 0, 300, 300);
  ironWR[2] = ironfWR.crop(600, 0, 300, 300);
  ironWR[3] = ironfWR.crop(0, 300, 300, 300);
  ironWR[4] = ironfWR.crop(300, 300, 300, 300);
  ironWR[5] = ironfWR.crop(600, 300, 300, 300);
  ironWR[6] = ironfWR.crop(0, 600, 300, 300);  
  
  ironWR2 = new BufferedImage[7];
  ironWR2[0] = ironfWR2.crop(0, 0, 300, 300);
  ironWR2[1] = ironfWR2.crop(300, 0, 300, 300);
  ironWR2[2] = ironfWR2.crop(600, 0, 300, 300);
  ironWR2[3] = ironfWR2.crop(0, 300, 300, 300);
  ironWR2[4] = ironfWR2.crop(300, 300, 300, 300);
  ironWR2[5] = ironfWR2.crop(600, 300, 300, 300);
  ironWR2[6] = ironfWR2.crop(0, 600, 300, 300);  
  
  sprintDown = new BufferedImage[3];
  sprintDown[0] = sprints.crop(0, 0, 192, 192);
  sprintDown[1] = sprints.crop(192, 0, 192, 192);
  sprintDown[2] = sprints.crop(384, 0, 192, 192);
  
  sprintUp = new BufferedImage[3];
  sprintUp[0] = sprints.crop(0, 192, 192, 192);
  sprintUp[1] = sprints.crop(192, 192, 192, 192);
  sprintUp[2] = sprints.crop(384, 192, 192, 192);
  
  sprintLeft = new BufferedImage[3];
  sprintLeft[0] = sprints.crop(0, 384, 192, 192);
  sprintLeft[1] = sprints.crop(192, 384, 192, 192);
  sprintLeft[2] = sprints.crop(384, 384, 192, 192);
  
  sprintRight = new BufferedImage[3];
  sprintRight[0] = sprints.crop(0, 576, 192, 192);
  sprintRight[1] = sprints.crop(192, 576, 192, 192);
  sprintRight[2] = sprints.crop(384, 576, 192, 192);
  }

}