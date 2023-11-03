package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import Data.Vector2D;
import Data.spriteInfo;
import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static stopWatchX timer = new stopWatchX(info.hz);
	public static boolean isImageDrawn = false;
	
	//Direction and Animations
	public static String direction = "walkdown";
	public static int currentSpriteIndex1, currentSpriteIndex2, currentSpriteIndex3, currentSpriteIndex4 = 0;
	
	//Images
	public static spriteInfo floor;
	public static ArrayList<spriteInfo> wall = new ArrayList();
	public static spriteInfo player;
	public static spriteInfo computer;
	public static spriteInfo usb;
	
	//Text
	public static HashMap<String, String> text = new HashMap<>();
	public static String currentText = "";
	public static String inBound;
	
			
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		player = new spriteInfo(new Vector2D(900,400),info.playerWidth,info.playerHeight,"walkdown1");
		floor = new spriteInfo(new Vector2D(0,0), info.SCREENX, info.SCREENY, "map");
		usb = new spriteInfo(new Vector2D(330,735),info.usbWidth,info.usbHeight,"usb");
		computer = new spriteInfo(new Vector2D(1320,240),info.computerWidth,info.computerHeight,"computer");
		
		wall.add(new spriteInfo(new Vector2D(0,0),info.wallleftWidth,info.wallleftHeight,"wallleft"));
		wall.add(new spriteInfo(new Vector2D(info.SCREENX-120,0),info.wallrightWidth,info.wallrightHeight,"wallright"));
		wall.add(new spriteInfo(new Vector2D(0,0),info.walltopWidth,info.walltopHeight,"walltop"));
		wall.add(new spriteInfo(new Vector2D(0,info.SCREENY-120),info.wallbottomWidth,info.wallbottomHeight,"wallbottom"));
		loadText();
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		
			//Adding all the art to the screen
			ctrl.addSpriteToFrontBuffer(0, 0, floor.getTag());
			ctrl.addSpriteToFrontBuffer(usb.getCoords().getX(), usb.getCoords().getY(), usb.getTag());
			ctrl.addSpriteToFrontBuffer(computer.getCoords().getX(), computer.getCoords().getY(), computer.getTag());
			for(spriteInfo border:wall) {
				 ctrl.addSpriteToFrontBuffer(border.getCoords().getX(),border.getCoords().getY(),border.getTag());
			}
			ctrl.addSpriteToFrontBuffer(player.getCoords().getX(),player.getCoords().getY(), player.getTag());
				
			//Executes text when player press space and in bound of the items
			if (inBound != null) {
	            ctrl.drawString(750, 850, currentText, Color.WHITE);
			}
			
			//Movements with WASD
			if(timer.isTimeUp()) {
				if(isImageDrawn) {
					switch(direction) {
					case "walkup":
						player.setTag(direction+currentSpriteIndex1++);
						if(currentSpriteIndex1==4) {
							currentSpriteIndex1=1;
						}
						break;
					case "walkleft":
						player.setTag(direction+currentSpriteIndex2++);
						if(currentSpriteIndex2==4) {
							currentSpriteIndex2=1;
						}
						break;
					case "walkdown":
						player.setTag(direction+currentSpriteIndex3++);
						if(currentSpriteIndex3==4) {
							currentSpriteIndex3=1;
						}
						break;
					case "walkright":
						player.setTag(direction+currentSpriteIndex4++);
						if(currentSpriteIndex4==4) {
							currentSpriteIndex4=1;
						}
						break;
					}
					isImageDrawn=false;
				}
				else {
					player.setTag(direction+1);
				}
				timer.resetWatch();
			}	
			     
	}
	
	//Method to load text from file.
	public static void loadText() {
		EZFileRead ezr = new EZFileRead("Dialogue.txt");
		int i=0;
		while(i<ezr.getNumLines()) {
			String raw = ezr.getNextLine();
			StringTokenizer st = new StringTokenizer(raw,"*");
			String Key = st.nextToken();
			String Value = st.nextToken();
			text.put(Key, Value);
			i++;
		}
	}
	
	//Method to set currentText
	public static void OpenText(){
		if (inBound != null) {
            if(inBound=="computer") {
            currentText = text.get("computer");
            }
            else if(inBound=="usb"){
            	currentText = text.get("usb");
            }
        }	
	}

	//Returns an integer to see if the player collide with item or wall
	public static int isCollision() {
		if (player.willCollide(computer)) {
            inBound = "computer";
            return 0;
		}
        if (player.willCollide(usb)) {
            inBound = "usb";
            return 0;
        }
        if(!player.willCollide(computer) && !player.willCollide(usb)) {
        	inBound = null;
        	currentText = "";
        }
        for (spriteInfo x : wall)
            if (player.willCollide(x)) {
            	return 0;
            }
        return -1;
	}

}
