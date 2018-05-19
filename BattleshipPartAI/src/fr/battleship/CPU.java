package fr.battleship;
import java.util.ArrayList;

public class CPU implements Player {
	public String name;
	public String status;
	public Board myBoard = new Board();
	public Board enemyBoard = new Board();
	public int xCoord;
	public int yCoord;
	public Human enemyH;
	public CPU enemyC;
	public int difficulty;
	public int pickEnemyHuman=0;
	public int pickEnemyCPU=0;
	
// a list of coordonates where the AI has shot during the match and another with last hits he hit
//the fleet
	ArrayList<Ship> ships = new ArrayList<Ship>();
	ArrayList<String> totalSpaces = new ArrayList<String>();
	
	
	public CPU() {
		this.name = "Computer";
		this.status = "AI";
	}
	
	public void clear() {
		myBoard.clear();
		enemyBoard.clear();
		ships.clear();
		totalSpaces.clear();
		
	}
	
	public void fillNoDif(String name,int sizeBoardX,int sizeBoardY) {
		this.name = name;
		myBoard.sizeX = sizeBoardX;
		myBoard.sizeY = sizeBoardY;
		enemyBoard.sizeX = sizeBoardX;
		enemyBoard.sizeY = sizeBoardY;
		
	}


	public void fill(int dif,int sizeBoardX,int sizeBoardY) {
		this.difficulty = dif;
		myBoard.sizeX = sizeBoardX;
		myBoard.sizeY = sizeBoardY;
		enemyBoard.sizeX = sizeBoardX;
		enemyBoard.sizeY = sizeBoardY;
		myBoard.fillBoard();
		enemyBoard.fillBoard();
	}
	public void fill2(String name,int dif,int sizeBoardX,int sizeBoardY) {
		this.name=name;
		this.difficulty=dif;
		myBoard.sizeX = sizeBoardX;
		myBoard.sizeY = sizeBoardY;
		enemyBoard.sizeX = sizeBoardX;
		enemyBoard.sizeY = sizeBoardY;
		myBoard.fillBoard();
		enemyBoard.fillBoard();
	}
	
	public void setEnemy(Human enemy) {
		this.enemyH=enemy;
		pickEnemyHuman=1;
		
	}
	public void setEnemy(CPU enemy) {
		this.enemyC=enemy;
		pickEnemyCPU=1;
	}
	public void add(Ship s) {
		ships.add(s);
		for(int i = 0; i< s.spaces.size(); i++) {
			totalSpaces.add(s.spaces.get(i));
		}
	}
	
	public int charToInt(char s){
		int result=0;
		switch(s) {
		case 'A': result=0;
			break;
		case 'B': result=1;
			break;
		case 'C': result=2;
			break;
		case 'D': result=3;
			break;
		case 'E': result=4;
			break;
		case 'F': result=5;
			break;
		case 'G': result=6;
			break;
		case 'H': result=7;
			break;
		case 'I': result=8;
			break;
		case 'J': result=9;
			break;
		case 'K': result=10;
			break;
		case 'L': result=11;
			break;
		case 'M': result=12;
			break;
		case 'N': result=13;
			break;
		case 'O': result=14;
			break;
		case 'P': result=15;
			break;
		case 'Q': result=16;
			break;
		case 'R': result=17;
			break;
		case 'S': result=18;
			break;
		case 'T': result=19;
			break;
		case 'U': result=20;
			break;
		case 'V': result=21;
			break;
		case 'W': result=22;
			break;
		case 'X': result=23;
			break;
		case 'Y': result=24;
			break;
		case 'Z': result=25;
			break;
		}
		return result;
	}
	
	public int stringToInt(String s){
		int result=0;
		switch(s) {
		case "A": result=0;
			break;
		case "B": result=1;
			break;
		case "C": result=2;
			break;
		case "D": result=3;
			break;
		case "E": result=4;
			break;
		case "F": result=5;
			break;
		case "G": result=6;
			break;
		case "H": result=7;
			break;
		case "I": result=8;
			break;
		case "J": result=9;
			break;
		case "K": result=10;
			break;
		case "L": result=11;
			break;
		case "M": result=12;
			break;
		case "N": result=13;
			break;
		case "O": result=14;
			break;
		case "P": result=15;
			break;
		case "Q": result=16;
			break;
		case "R": result=17;
			break;
		case "S": result=18;
			break;
		case "T": result=19;
			break;
		case "U": result=20;
			break;
		case "V": result=21;
			break;
		case "W": result=22;
			break;
		case "X": result=23;
			break;
		case "Y": result=24;
			break;
		case "Z": result=25;
			break;
		}
		return result;
	}
	
	public void placeShip(Ship s) {
		xCoord=charToInt(s.xHInitial);
		yCoord=s.yHInitial;		
		if(s.position=="V") {
			for(int i=0;i<s.size;i++){
				myBoard.map[yCoord-1][xCoord]='#';
				yCoord+=1;
			}
		}else {
			for(int i=0;i<s.size;i++){
				myBoard.map[yCoord-1][xCoord]='#';
				xCoord+=1;
			}	
		}
		
	}
	
	public void showBoards() {
		System.out.println(this.name+"'s board of ships: ");
		myBoard.showBoard();
		System.out.println("");
		System.out.println(this.name+"'s board of atacks: ");
		enemyBoard.showBoard();
	}
	public void showEmptyBoards() {
		System.out.println(this.name+"'s board of ships: ");
		myBoard.showEmptyBoard();
		System.out.println("");
		System.out.println(this.name+"'s board of atacks: ");
		enemyBoard.showEmptyBoard();
	}
	
	public void hit(String missileCoord){
		String xMissile =missileCoord.substring(0,1);
		int xCoord=stringToInt(xMissile);
		int yCoord=Integer.valueOf(missileCoord.substring(1,missileCoord.length()));
		if(pickEnemyCPU==1) {
			if(enemyC.myBoard.map[yCoord-1][xCoord]=='#') {
				enemyBoard.map[yCoord-1][xCoord]='H';
			}else if(enemyC.myBoard.map[yCoord-1][xCoord]=='~') {
				enemyBoard.map[yCoord-1][xCoord]='M';
			}
		}
		if(pickEnemyHuman==1) {
			if(enemyH.myBoard.map[yCoord-1][xCoord]=='#') {
				enemyBoard.map[yCoord-1][xCoord]='H';
			}else if(enemyH.myBoard.map[yCoord-1][xCoord]=='~') {
				enemyBoard.map[yCoord-1][xCoord]='M';
			}
		}
	}
	
	public void gotHit(String missileCoord){
		String xMissile =missileCoord.substring(0,1);
		int xCoord=stringToInt(xMissile);
		int yCoord=Integer.valueOf(missileCoord.substring(1,missileCoord.length()));
		if(myBoard.map[yCoord-1][xCoord]=='#') {
			myBoard.map[yCoord-1][xCoord]='0';
		}
	}
	public void gotShipSunk(Ship s){
		String space;
		for (int i = 0; i < s.spaces2.size(); i++) {
            space=s.spaces2.get(i);
            int xCoord=stringToInt(space.substring(0,1));
    		int yCoord=Integer.valueOf(space.substring(1,space.length()));
    		myBoard.map[yCoord-1][xCoord]='W';
    		if(pickEnemyCPU==1) {
    			enemyC.enemyBoard.map[yCoord-1][xCoord]='S';
    		}
    		if(pickEnemyHuman==1) {
    			enemyH.enemyBoard.map[yCoord-1][xCoord]='S';
    		}
		}
	}
		
}
