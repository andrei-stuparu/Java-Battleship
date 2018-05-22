package fr.battleship;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestIA{
	public static char getCharForNumber(int i) {
	    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	    if (i > 26) {
	        return '?';
	    }
	    return alphabet[i];
	}
	
	public static void order(ArrayList <String> f) {
		if(f.size()==2) {
			 String s1=f.get(0);
			 String s2=f.get(1);
			 if(s1.compareTo(s2)<0) {
				 
			 }else {
				 f.set(0, s2);
				 f.set(1, s1);
			 }
		}else if(f.size()==3) {
			String s1=f.get(0);
		    String s2=f.get(1);
		    String s3=f.get(2);
		    if(s1.compareTo(s2)<0&&s2.compareTo(s3)<0) {
		    	f.remove(s2);
		    }
		    if(s1.compareTo(s3)<0&&s3.compareTo(s2)<0) {
		    	f.remove(s3);
		    }
		    if(s2.compareTo(s1)<0&&s1.compareTo(s3)<0) {
		    	f.remove(s1);
		    }
		    if(s3.compareTo(s1)<0&&s1.compareTo(s2)<0) {
		    	f.remove(s1);
		    	f.set(0, s3);
				f.set(1, s2);
		    }
		    if(s2.compareTo(s3)<0&&s3.compareTo(s1)<0) {
		    	f.remove(s3);
		    	 f.set(0, s2);
				 f.set(1, s1);
		    }
		    if(s3.compareTo(s2)<0&&s2.compareTo(s1)<0) {
		    	f.remove(s2);
		    	f.set(0, s3);
				f.set(1, s1);
		    }
		}else {
			System.out.println("CHAOS, CHAOS EVERYWHERE");
		}
	    
	   
	    
	}
	//compare 2 strings to find the head, the smaller one, and the end, the bigger one
	public static boolean compare(String c1, String c2) {
		String xc1 =c1.substring(0,1);
		String xc2 =c2.substring(0,1);
		String yc1 =c1.substring(1,c1.length());
		String yc2 =c2.substring(1,c2.length());
		if(xc1.equals(xc2)) {
			if(Integer.valueOf(yc2)<Integer.valueOf(yc1)) {
				return false;
			}
		}else {
			if(xc1.compareTo(xc2)>0){
				return false;
			}
		}
		return true;
	}
	//verify if the difference between 2 strings is the intended one
	public static boolean shipLenght(String c1, String c2, int len) {
		String xc1 =c1.substring(0,1);
		String xc2 =c2.substring(0,1);
		String yc1 =c1.substring(1,c1.length());
		String yc2 =c2.substring(1,c2.length());
		if(xc1.equals(xc2)) {
			if((Integer.valueOf(yc2)-Integer.valueOf(yc1))==len-1) {
				return true;
			}
		}else {
			if(xc2.compareTo(xc1)==len-1){
				return true;
			}
		}
		return false;
	}
	//verify if is possible to fit the coordinate on the board
	public static boolean coordVerify(String c, int x, int y) {
		String xc =c.substring(0,1);
		String yc =c.substring(1,c.length());
		char ver = 'A';
		for(int i=0;i<x;i++) {
			ver+=1;
		}
		if(String.valueOf(ver).compareTo(xc)>=0 && Integer.valueOf(yc)<=y) {
			return true;
		}
		return false;
	}
	//verify if a position is available
	public static boolean spacesAvail(String c1,String c2, ArrayList <String> f) {
		String xc1 =c1.substring(0,1);
		String xc2 =c2.substring(0,1);
		String yc1 =c1.substring(1,c1.length());
		String yc2 =c2.substring(1,c2.length());
		String c;
		if(xc1.equals(xc2)) {
			for(int i =Integer.valueOf(yc1);i<=Integer.valueOf(yc2);i++ ) {
				c= xc1 + String.valueOf(i);
				if(f.contains(c)){
					return false;
				}
			}
			
		}else{
			for(char j = xc1.charAt(0);j<=xc2.charAt(0);j+=1) {
				c = String.valueOf(j)+yc1;
				if(f.contains(c)){
					return false;
				}
			}
		}
		return true;
	}
	
	//check if there is a connection between the coordonates
	public static boolean check(String c1,String c2) {
		String xc1 =c1.substring(0,1);
		String xc2 =c2.substring(0,1);
		String yc1 =c1.substring(1,c1.length());
		String yc2 =c2.substring(1,c2.length());
		if(xc1.equals(xc2)||yc1.equals(yc2)) {
			return true;
		}
		return false;
	}
	//check if string is number
	public static boolean isNum(String strNum) {
	    boolean ret = true;
	    try {

	        Double.parseDouble(strNum);

	    }catch (NumberFormatException e) {
	        ret = false;
	    }
	    return ret;
	}
	private static Random rand = new Random();
	private static Scanner scanner = new Scanner( System.in );
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("GAME START");
		System.out.println("CHOSE YOUR GAMETYPE:");
		FileWriter f = new FileWriter("ai_proof.csv");
		
		int sizeBoardX=0;
		int sizeBoardY=0;
		CPU playerC1= new CPU();
		CPU playerC2= new CPU();
		int rounds=0;
		int scorePlayer1=0;
		int scorePlayer2=0;
		int playerTurn=1;
		int lastGame=0;
		ArrayList<Ship> sunkenFleet1 = new ArrayList<Ship>();
		ArrayList<Ship> sunkenFleet2 = new ArrayList<Ship>();
		ArrayList<String> lastHitsAI = new ArrayList<String>();
		ArrayList<String> hitsAI = new ArrayList<String>();
		ArrayList<String> lastHitsAI2 = new ArrayList<String>();
		ArrayList<String> hitsAI2 = new ArrayList<String>();
		int limitShip1 = 1;
		int limitShip2 = 1;
		int limitShip3 = 1;
		int limitShip4 = 1;
		int limitShip5 = 1;
		int limitFleet = limitShip1+limitShip2+limitShip3+limitShip4+limitShip5;
		int s1p1 = 0;
		int s2p1 = 0;
		int s3p1 = 0;
		int s4p1 = 0;
		int s5p1 = 0;
		int s1p2 = 0;
		int s2p2 = 0;
		int s3p2 = 0;
		int s4p2 = 0;
		int s5p2 = 0;
		String scan="NOPE";
		
		System.out.println("WE ADVISE THE USE OF A SIMETRICAL BOARD AND NO LONGER THAN 26");
		System.out.println("BOTH PLAYERS WILL HAVE SAME BOARD LENGHT AND HEIGHT SO PLEASE MAKE THIS DECISION TOGETHER");
		while(!isNum(scan)) {
			System.out.println("PLEASE INSERT YOUR BOARD LENGHT");
			scan=scanner.nextLine();
		}
		sizeBoardX = Integer.parseInt(scan);
		scan="NOPE";
		while(!isNum(scan)) {
			System.out.println("PLEASE INSERT YOUR BOARD HEIGHT");
			scan=scanner.nextLine();
		}
		sizeBoardY = Integer.parseInt(scan);
		scan="NOPE";
		playerC1.fill2("Computer1",1, sizeBoardX, sizeBoardY);
		playerC2.fill2("Computer2",1, sizeBoardX, sizeBoardY);
		playerC1.setEnemy(playerC2);
		playerC2.setEnemy(playerC1);
		//append columns name
		f.append("AI Name");
		f.append(',');
		f.append("score");
		f.append(',');
		f.append("AI Name2");
		f.append(',');
		f.append("score2");
		f.append(System.lineSeparator());
		f.flush();
		
		while(rounds<300) {
			s1p1 = 0;
			s2p1 = 0;
			s3p1 = 0;
			s4p1 = 0;
			s5p1 = 0;
			s1p2 = 0;
			s2p2 = 0;
			s3p2 = 0;
			s4p2 = 0;
			s5p2 = 0;
			playerC1.clear();
			playerC2.clear();
			while(s1p1 < limitShip1 || s2p1 < limitShip2 || s3p1 < limitShip3 || s4p1 < limitShip4 || s5p1 < limitShip5) {
				//create ships for AI
				int  pick = rand.nextInt(5) + 1;
				if(pick==1&&s1p1 < limitShip1) {//pick for carrier
					int  hX = rand.nextInt(sizeBoardX-5);
					int  hY = rand.nextInt(sizeBoardY-5) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+4;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+4);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC1.totalSpaces)==false){
						
					}else{
						Ship carrier1 = new Ship("Carrier", head, end, playerC1);
						playerC1.add(carrier1);
						playerC1.placeShip(carrier1);
						s1p1++;
					}
					
				}else if(pick == 2&&s2p1 < limitShip2) {//pick for battleship
					int  hX = rand.nextInt(sizeBoardX-4);
					int  hY = rand.nextInt(sizeBoardY-4) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+3;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+3);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC1.totalSpaces)==false){
						
					}else{
						Ship battleship1 = new Ship("Battleship", head, end, playerC1);
						playerC1.add(battleship1);
						playerC1.placeShip(battleship1);
						s2p1++;
					}
				}else if(pick == 3&&s3p1 < limitShip3) {//pick for cruiser
					int  hX = rand.nextInt(sizeBoardX-3);
					int  hY = rand.nextInt(sizeBoardY-3) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+2;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+2);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC1.totalSpaces)==false){
	
					}else{
						Ship cruiser1 = new Ship("Cruiser", head, end, playerC1);
						playerC1.add(cruiser1);
						playerC1.placeShip(cruiser1);
						s3p1++;
					}
				}else if(pick == 4&&s4p1 < limitShip4) {//pick for sub
					int  hX = rand.nextInt(sizeBoardX-3);
					int  hY = rand.nextInt(sizeBoardY-3) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+2;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+2);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC1.totalSpaces)==false){
	
					}else{
						Ship submarine1 = new Ship("Submarine", head, end, playerC1);
						playerC1.add(submarine1);
						playerC1.placeShip(submarine1);
						s4p1++;
					}
				}else if(pick == 5&&s5p1 < limitShip5) {//pick for destroyer
					int  hX = rand.nextInt(sizeBoardX-2);
					int  hY = rand.nextInt(sizeBoardY-2) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+1;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+1);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC1.totalSpaces)==false){
	
					}else{
						Ship destroyer1 = new Ship("Destroyer", head, end, playerC1);
						playerC1.add(destroyer1);
						playerC1.placeShip(destroyer1);
						s5p1++;
					}
				}
			}
			while(s1p2 < limitShip1 || s2p2 < limitShip2 || s3p2 < limitShip3 || s4p2 < limitShip4 || s5p2 < limitShip5) {
				//create ships for 2ND AI
				int  pick = rand.nextInt(5) + 1;
				if(pick==1&&s1p2 < limitShip1) {//pick for carrier
					int  hX = rand.nextInt(sizeBoardX-5);
					int  hY = rand.nextInt(sizeBoardY-5) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+4;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+4);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC2.totalSpaces)==false){
						
					}else{
						Ship carrier2 = new Ship("Carrier", head, end, playerC2);
						playerC2.add(carrier2);
						playerC2.placeShip(carrier2);
						s1p2++;
					}
					
				}else if(pick == 2&&s2p2 < limitShip2) {//pick for battleship
					int  hX = rand.nextInt(sizeBoardX-4);
					int  hY = rand.nextInt(sizeBoardY-4) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+3;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+3);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC2.totalSpaces)==false){
						
					}else{
						Ship battleship2 = new Ship("Battleship", head, end, playerC2);
						playerC2.add(battleship2);
						playerC2.placeShip(battleship2);
						s2p2++;
					}
				}else if(pick == 3&&s3p2 < limitShip3) {//pick for cruiser
					int  hX = rand.nextInt(sizeBoardX-3);
					int  hY = rand.nextInt(sizeBoardY-3) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+2;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+2);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC2.totalSpaces)==false){
	
					}else{
						Ship cruiser2 = new Ship("Cruiser", head, end, playerC2);
						playerC2.add(cruiser2);
						playerC2.placeShip(cruiser2);
						s3p2++;
					}
				}else if(pick == 4&&s4p2 < limitShip4) {//pick for sub
					int  hX = rand.nextInt(sizeBoardX-3);
					int  hY = rand.nextInt(sizeBoardY-3) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+2;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+2);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC2.totalSpaces)==false){
	
					}else{
						Ship submarine2 = new Ship("Submarine", head, end, playerC2);
						playerC2.add(submarine2);
						playerC2.placeShip(submarine2);
						s4p2++;
					}
				}else if(pick == 5&&s5p2 < limitShip5) {//pick for destroyer
					int  hX = rand.nextInt(sizeBoardX-2);
					int  hY = rand.nextInt(sizeBoardY-2) + 1;
					char headX= getCharForNumber(hX);
					String head = String.valueOf(headX)+ String.valueOf(hY);
					String end;
					int position = rand.nextInt(2);
					if (position==0) {//place vertically
						int eY = hY+1;
						end = String.valueOf(headX)+ String.valueOf(eY);
					}else {//place horizontally
						char endX = getCharForNumber(hX+1);
						end = String.valueOf(endX)+ String.valueOf(hY);
					}
					if(spacesAvail(head,end,playerC2.totalSpaces)==false){
	
					}else{
						Ship destroyer2 = new Ship("Destroyer", head, end, playerC2);
						playerC2.add(destroyer2);
						playerC2.placeShip(destroyer2);
						s5p2++;
					}
				}
			}//finish ships

			String winner = "NONE";
			if(rounds<100) {
				playerC1.difficulty=1;
				playerC2.difficulty=2;
			}else if(rounds<200) {
				playerC1.difficulty=1;
				playerC2.difficulty=3;
				
			}else {
				playerC1.difficulty=2;
				playerC2.difficulty=3;
			}
			if(rounds==100) {
				scorePlayer1=0;
				scorePlayer2=0;
			}else if(rounds==200) {
				scorePlayer1=0;
				scorePlayer2=0;
			}
			if(lastGame==1) {
				playerTurn=2;
			}
			if(lastGame==2) {
				playerTurn=1;
			}
			sunkenFleet1.clear();;
			sunkenFleet2.clear();
			lastHitsAI.clear();
			hitsAI.clear();
			lastHitsAI2.clear();
			hitsAI2.clear();
			lastGame=playerTurn;
			rounds++;
			int allHits=0;
			int endround1=0;
			int endround2=0;
			int resultplayer1=0;
			int resultplayer2=0;
			int shipsMissed=0;
			int loop=0;
			//general
			String positionShipAI="UNKNOWN";
			String lastHit;
			String firstHit;
			String xLast;
			String yLast;
			String xFirst;
			String yFirst;
			char xNew;
			int yNew;
			int hitUp=0;
			int hitDown=0;
			int hitRight=0;
			int hitLeft=0;
			//AI1
			String positionShipAI2= "UNKNOWN";
			String lastHitAI2;
			String firstHitAI2;
			String xLastAI2;
			String yLastAI2;
			String xFirstAI2;
			String yFirstAI2;
			char xNewAI2;
			int yNewAI2;
			int hitUpAI2=0;
			int hitDownAI2=0;
			int hitRightAI2=0;
			int hitLeftAI2=0;
			//AI2
			int rdy=0;
			//both AI
			String missile;
			while (winner.equals("NONE")) {
				if(playerTurn==1) {
					shipsMissed=0;
					if(playerC1.difficulty==1) {//EASY mode********************
						int  coordY = rand.nextInt(sizeBoardY) + 1;
						int  coordX = rand.nextInt(sizeBoardX);
						char mX =getCharForNumber(coordX);
						missile = String.valueOf(mX)+String.valueOf(coordY);
						hitsAI.add(missile);
						playerC1.hit(missile);
						playerC2.gotHit(missile);
						for (int i=0;i<playerC2.ships.size();i++) {
							if(playerC2.ships.get(i).isHit(missile)) {
								System.out.println(playerC1.name+" has hit "+missile+" and hit a ship");
								resultplayer1 = 1;
							}else {
								shipsMissed++;
							}
							playerC2.ships.get(i).hit(missile);
						}
						if(shipsMissed==limitFleet) {
							System.out.println(playerC1.name+" has hit "+missile+" and missed");
							resultplayer1=0;
						}
						for (int i=0;i<playerC2.ships.size();i++) {
							if(playerC2.ships.get(i).isDestroyed()) {
								System.out.println(playerC1.name+" has hit "+missile+" and sunk a ship");
								resultplayer1 = 2;
								sunkenFleet2.add(playerC2.ships.get(i));
								playerC2.ships.remove(i);
							}else {
								
							}
							//playerH1.ships.get(i).hit(missile);
							
						}
						if (sunkenFleet2.size()== limitFleet) {
							System.out.println("ALL SHIPS OF "+playerC2.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC1.name);
							winner = playerC1.name;
							System.out.println("The winner boards");
							playerC1.showBoards();
							System.out.println("The loser boards");
							playerC2.showBoards();
						}
					}else if(playerC1.difficulty==2) {//MEDIUM mode********************
						while(endround1 == 0) {
							int  coordY = rand.nextInt(sizeBoardY) + 1;
							int  coordX = rand.nextInt(sizeBoardX);
							char mX =getCharForNumber(coordX);
							missile = String.valueOf(mX)+String.valueOf(coordY);
							if(hitsAI.contains(missile)==false) {
								hitsAI.add(missile);
								playerC1.hit(missile);
								playerC2.gotHit(missile);
								
								for (int i=0;i<playerC2.ships.size();i++) {
									if(playerC2.ships.get(i).isHit(missile)) {
										System.out.println(playerC1.name+" has hit "+missile+" and hit a ship");
										resultplayer1 = 1;
									}else {
										shipsMissed++;
									}
									playerC2.ships.get(i).hit(missile);
								}
								if(shipsMissed==limitFleet) {
									System.out.println(playerC1.name+" has hit "+missile+" and missed");
									resultplayer1=0;
								}
								for (int i=0;i<playerC2.ships.size();i++) {
									if(playerC2.ships.get(i).isDestroyed()) {
										System.out.println(playerC1.name+" has hit "+missile+" and sunk a ship");
										resultplayer1 = 2;
										sunkenFleet2.add(playerC2.ships.get(i));
										playerC2.ships.remove(i);
									}else {
										
									}
									//playerH1.ships.get(i).hit(missile);
									
								}
								endround1=1;
							}
						}
						if (sunkenFleet2.size()== limitFleet) {
							System.out.println("ALL SHIPS OF "+playerC2.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC1.name);
							winner = playerC1.name;
							System.out.println("The winner boards");
							playerC1.showBoards();
							System.out.println("The loser boards");
							playerC2.showBoards();
						}
					}else if(playerC1.difficulty==3) {//HARD mode***************************
						while(endround1 == 0) {
							if(lastHitsAI.size()==0) {
								int  coordY = rand.nextInt(sizeBoardY) + 1;
								int  coordX = rand.nextInt(sizeBoardX);
								char mX =getCharForNumber(coordX);
								missile = String.valueOf(mX)+String.valueOf(coordY);
							}else {
								if(lastHitsAI.size()==1) {
									lastHit=lastHitsAI.get(0);
									xLast =lastHit.substring(0,1);
									yLast =lastHit.substring(1,lastHit.length());
									if(xLast.equals("A")) {
										if(Integer.parseInt(yLast)==1) {
											if(hitRight==0) {
												hitRight=1;
												xNew=xLast.charAt(0);
												xNew+=1;
												missile = String.valueOf(xNew)+String.valueOf(yLast);
											}else {
												hitDown=1;
												yNew=Integer.parseInt(yLast);
												yNew++;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}
										}else if(Integer.parseInt(yLast)==sizeBoardY) {
											if(hitRight==0) {
												hitRight=1;
												xNew=xLast.charAt(0);
												xNew+=1;
												missile = String.valueOf(xNew)+String.valueOf(yLast);
											}else {
												hitUp=1;
												yNew=Integer.parseInt(yLast);
												yNew=yNew-1;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}
										}else {
											if(hitRight==0) {
												hitRight=1;
												xNew=xLast.charAt(0);
												xNew+=1;
												missile = String.valueOf(xNew)+String.valueOf(yLast);
											}else if(hitDown==0){
												hitDown=1;
												yNew=Integer.parseInt(yLast);
												yNew++;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}else {
												hitUp=1;
												yNew=Integer.parseInt(yLast);
												yNew=yNew-1;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}
										}	
									}else if(xLast.equals(getCharForNumber(sizeBoardX-1))) {
										if(Integer.parseInt(yLast)==1) {
											if(hitLeft==0) {
												hitLeft=1;
												xNew=xLast.charAt(0);
												xNew-=1;
												missile = String.valueOf(xNew)+String.valueOf(yLast);
											}else {
												hitDown=1;
												yNew=Integer.parseInt(yLast);
												yNew++;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}
										}else if(Integer.parseInt(yLast)==sizeBoardY) {
											if(hitLeft==0) {
												hitLeft=1;
												xNew=xLast.charAt(0);
												xNew-=1;
												missile = String.valueOf(xNew)+String.valueOf(yLast);
											}else {
												hitUp=1;
												yNew=Integer.parseInt(yLast);
												yNew=yNew-1;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}
										}else {
											if(hitLeft==0) {
												hitLeft=1;
												xNew=xLast.charAt(0);
												xNew-=1;
												missile = String.valueOf(xNew)+String.valueOf(yLast);
											}else if(hitDown==0){
												hitDown=1;
												yNew=Integer.parseInt(yLast);
												yNew++;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}else {
												hitUp=1;
												yNew=Integer.parseInt(yLast);
												yNew=yNew-1;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}
										}	
									}else if(Integer.parseInt(yLast)==1){
										if(hitRight==0) {
											hitRight=1;
											xNew=xLast.charAt(0);
											xNew+=1;
											missile = String.valueOf(xNew)+String.valueOf(yLast);
										}else if(hitDown==0){
											hitDown=1;
											yNew=Integer.parseInt(yLast);
											yNew++;
											missile = String.valueOf(xLast)+String.valueOf(yNew);
										}else {
											hitLeft=1;
											xNew=xLast.charAt(0);
											xNew-=1;
											missile = String.valueOf(xNew)+String.valueOf(yLast);
										}
									}else if(Integer.parseInt(yLast)==sizeBoardY-1){
										if(hitRight==0) {
											hitRight=1;
											xNew=xLast.charAt(0);
											xNew+=1;
											missile = String.valueOf(xNew)+String.valueOf(yLast);
										}else if(hitUp==0){
											hitUp=1;
											yNew=Integer.parseInt(yLast);
											yNew--;
											missile = String.valueOf(xLast)+String.valueOf(yNew);
										}else {
											hitLeft=1;
											xNew=xLast.charAt(0);
											xNew-=1;
											missile = String.valueOf(xNew)+String.valueOf(yLast);
										}										
									}else {
										if(hitRight==0) {
											hitRight=1;
											xNew=xLast.charAt(0);
											xNew+=1;
											missile = String.valueOf(xNew)+String.valueOf(yLast);
										}else if(hitLeft==0) {
											hitLeft=1;
											xNew=xLast.charAt(0);
											xNew-=1;
											missile = String.valueOf(xNew)+String.valueOf(yLast);
										}else if(hitDown==0){
											hitDown=1;
											yNew=Integer.parseInt(yLast);
											yNew++;
											missile = String.valueOf(xLast)+String.valueOf(yNew);
										}else {
											hitUp=1;
											yNew=Integer.parseInt(yLast);
											yNew=yNew-1;
											missile = String.valueOf(xLast)+String.valueOf(yNew);
										}
									}
								}else {
									if(positionShipAI.equals("UNKNOWN")) {
										allHits=lastHitsAI.size();
										lastHit=lastHitsAI.get(allHits-1);
										firstHit =lastHitsAI.get(0);
										xLast =lastHit.substring(0,1);
										yLast =lastHit.substring(1,lastHit.length());
										xFirst =firstHit.substring(0,1);
										yFirst =firstHit.substring(1,firstHit.length());
										if(xLast.equals(xFirst)) {
											positionShipAI="V";
										}
										if(yLast.equals(yFirst)) {
											positionShipAI="H";
										}
									}
									allHits=lastHitsAI.size();
									lastHit=lastHitsAI.get(allHits-1);
									firstHit =lastHitsAI.get(0);
									xLast =lastHit.substring(0,1);
									yLast =lastHit.substring(1,lastHit.length());
									xFirst =firstHit.substring(0,1);
									yFirst =firstHit.substring(1,firstHit.length());
									if(positionShipAI.equals("V")) {
										if(Integer.parseInt(yLast)==sizeBoardY) {
											yNew=Integer.parseInt(yFirst);
											yNew=yNew-1;
											missile = String.valueOf(xLast)+String.valueOf(yNew);
										}else {
											yNew=Integer.parseInt(yLast);
											yNew=yNew+1;
											missile = String.valueOf(xLast)+String.valueOf(yNew);
											if(hitsAI.contains(missile)) {
												yNew=Integer.parseInt(yFirst);
												yNew=yNew-1;
												missile = String.valueOf(xLast)+String.valueOf(yNew);
											}
										}
									}else {
										if(xLast.equals(getCharForNumber(sizeBoardX-1))) {
											xNew=xFirst.charAt(0);
											xNew-=1;
											missile = String.valueOf(xNew)+String.valueOf(yLast);
										}else {
											xNew=xLast.charAt(0);
											xNew+=1;
											missile = String.valueOf(xNew)+String.valueOf(yLast);
											if(hitsAI.contains(missile)&&xLast.equals("A")==false) {
												xNew=xFirst.charAt(0);
												xNew-=1;
												missile = String.valueOf(xNew)+String.valueOf(yLast);
											}
										}
									}
								}
								//DO CODE TO HIT LEFT RIGHT FOR POSITION
							}
							if(hitsAI.contains(missile)==false) {
								//rundePlayer1++;
								hitsAI.add(missile);
								playerC1.hit(missile);
								playerC2.gotHit(missile);
								for (int i=0;i<playerC2.ships.size();i++) {
									if(playerC2.ships.get(i).isHit(missile)) {
										System.out.println(playerC1.name+" has hit "+missile+" and hit a ship");
										resultplayer1 = 1;
										lastHitsAI.add(missile);
										hitUp=0;
										hitDown=0;
										hitRight=0;
										hitLeft=0;
									}else {
										shipsMissed++;
									}
									playerC2.ships.get(i).hit(missile);
								}
								if(shipsMissed==limitFleet) {
									System.out.println(playerC1.name+" has hit "+missile+" and missed");
									resultplayer1=0;
								}
								for (int i=0;i<playerC2.ships.size();i++) {
									if(playerC2.ships.get(i).isDestroyed()) {
										System.out.println(playerC1.name+" has hit "+missile+" and sunk a ship");
										resultplayer1 = 2;
										sunkenFleet2.add(playerC2.ships.get(i));
										playerC2.ships.remove(i);
										lastHitsAI.clear();
									}else {
										
									}
									//playerH1.ships.get(i).hit(missile);
									
								}
								/*if(resultplayer1==2) {
									for(int i=0; i<sizeBoardX;i++) {
										for(int j=0; j<sizeBoardY;j++) {
											if (playerH1.myBoard.map[i][j]=='S') {
												char cJ =getCharForNumber(j);
												String smth = String.valueOf(cJ)+ String.valueOf(i);
												if(lastHitsAI.contains(smth)) {
													lastHitsAI.remove(smth);
												}
											}
										}
									}
								}*/
								endround1=1;
							}else {
								loop++;
							}
							if(loop==15) {
								hitUp=0;
								hitDown=0;
								hitRight=0;
								hitLeft=0;
								lastHitsAI.clear();
								loop=0;
							}
							if(lastHitsAI.size()>1) {
								order(lastHitsAI);
							}
						}
						if (sunkenFleet2.size()== limitFleet) {
							System.out.println("ALL SHIPS OF "+playerC2.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC1.name);
							winner = playerC1.name;
							System.out.println("The winner boards");
							playerC1.showBoards();
							System.out.println("The loser boards");
							playerC2.showBoards();
						}
					}
					playerTurn=2;
					endround2=0;
				}else {
					shipsMissed=0;
					if(playerC2.difficulty==1) {//EASY mode********************
						int  coordY = rand.nextInt(sizeBoardY) + 1;
						int  coordX = rand.nextInt(sizeBoardX);
						char mX =getCharForNumber(coordX);
						missile = String.valueOf(mX)+String.valueOf(coordY);
						hitsAI2.add(missile);
						playerC2.hit(missile);
						playerC1.gotHit(missile);
						for (int i=0;i<playerC1.ships.size();i++) {
							if(playerC1.ships.get(i).isHit(missile)) {
								System.out.println(playerC2.name+" has hit "+missile+" and hit a ship");
								resultplayer2 = 1;
							}else {
								shipsMissed++;
							}
							playerC1.ships.get(i).hit(missile);
						}
						if(shipsMissed==limitFleet) {
							System.out.println(playerC2.name+" has hit "+missile+" and missed");
							resultplayer2=0;
						}
						for (int i=0;i<playerC1.ships.size();i++) {
							if(playerC1.ships.get(i).isDestroyed()) {
								System.out.println(playerC2.name+" has hit "+missile+" and sunk a ship");
								resultplayer2 = 2;
								sunkenFleet1.add(playerC1.ships.get(i));
								playerC1.ships.remove(i);
							}else {
								
							}
							//playerH1.ships.get(i).hit(missile);
							
						}
						if (sunkenFleet1.size()== limitFleet) {
							System.out.println("ALL SHIPS OF "+playerC1.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC2.name);
							winner = playerC2.name;
							System.out.println("The winner boards");
							playerC2.showBoards();
							System.out.println("The loser boards");
							playerC1.showBoards();
						}
					}else if(playerC2.difficulty==2) {//MEDIUM mode********************
						while(endround2 == 0) {
							int  coordY = rand.nextInt(sizeBoardY) + 1;
							int  coordX = rand.nextInt(sizeBoardX);
							char mX =getCharForNumber(coordX);
							missile = String.valueOf(mX)+String.valueOf(coordY);
							if(hitsAI2.contains(missile)==false) {
								hitsAI2.add(missile);
								playerC2.hit(missile);
								playerC1.gotHit(missile);
								
								for (int i=0;i<playerC1.ships.size();i++) {
									if(playerC1.ships.get(i).isHit(missile)) {
										System.out.println(playerC2.name+" has hit "+missile+" and hit a ship");
										resultplayer2 = 1;
									}else {
										shipsMissed++;
									}
									playerC1.ships.get(i).hit(missile);
								}
								if(shipsMissed==limitFleet) {
									System.out.println(playerC2.name+" has hit "+missile+" and missed");
									resultplayer2=0;
								}
								for (int i=0;i<playerC1.ships.size();i++) {
									if(playerC1.ships.get(i).isDestroyed()) {
										System.out.println(playerC2.name+" has hit "+missile+" and sunk a ship");
										resultplayer2 = 2;
										sunkenFleet1.add(playerC1.ships.get(i));
										playerC1.ships.remove(i);
									}else {
										
									}
									
								}
								endround2=1;
							}
						}
						if (sunkenFleet1.size()== limitFleet) {
							System.out.println("ALL SHIPS OF "+playerC1.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC2.name);
							winner = playerC2.name;
							System.out.println("The winner boards");
							playerC2.showBoards();
							System.out.println("The loser boards");
							playerC1.showBoards();
						}
					}else if(playerC2.difficulty==3) {//HARD mode***************************
						while(endround2 == 0) {
							if(lastHitsAI2.size()==0) {
								int  coordY = rand.nextInt(sizeBoardY) + 1;
								int  coordX = rand.nextInt(sizeBoardX);
								char mX =getCharForNumber(coordX);
								missile = String.valueOf(mX)+String.valueOf(coordY);
							}else {
								if(lastHitsAI2.size()==1) {
									lastHitAI2=lastHitsAI2.get(0);
									xLastAI2 =lastHitAI2.substring(0,1);
									yLastAI2 =lastHitAI2.substring(1,lastHitAI2.length());
									if(xLastAI2.equals("A")) {
										if(Integer.parseInt(yLastAI2)==1) {
											if(hitRightAI2==0) {
												hitRightAI2=1;
												xNewAI2=xLastAI2.charAt(0);
												xNewAI2+=1;
												missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
											}else {
												hitDownAI2=1;
												yNewAI2=Integer.parseInt(yLastAI2);
												yNewAI2++;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}
										}else if(Integer.parseInt(yLastAI2)==sizeBoardY) {
											if(hitRightAI2==0) {
												hitRightAI2=1;
												xNewAI2=xLastAI2.charAt(0);
												xNewAI2+=1;
												missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
											}else {
												hitUpAI2=1;
												yNewAI2=Integer.parseInt(yLastAI2);
												yNewAI2=yNewAI2-1;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}
										}else {
											if(hitRightAI2==0) {
												hitRightAI2=1;
												xNewAI2=xLastAI2.charAt(0);
												xNewAI2+=1;
												missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
											}else if(hitDownAI2==0){
												hitDownAI2=1;
												yNewAI2=Integer.parseInt(yLastAI2);
												yNewAI2++;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}else {
												hitUpAI2=1;
												yNewAI2=Integer.parseInt(yLastAI2);
												yNewAI2=yNewAI2-1;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}
										}	
									}else if(xLastAI2.equals(getCharForNumber(sizeBoardX-1))) {
										if(Integer.parseInt(yLastAI2)==1) {
											if(hitLeftAI2==0) {
												hitLeftAI2=1;
												xNewAI2=xLastAI2.charAt(0);
												xNewAI2-=1;
												missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
											}else {
												hitDownAI2=1;
												yNewAI2=Integer.parseInt(yLastAI2);
												yNewAI2++;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}
										}else if(Integer.parseInt(yLastAI2)==sizeBoardY) {
											if(hitLeftAI2==0) {
												hitLeftAI2=1;
												xNewAI2=xLastAI2.charAt(0);
												xNewAI2-=1;
												missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
											}else {
												hitUpAI2=1;
												yNewAI2=Integer.parseInt(yLastAI2);
												yNewAI2=yNewAI2-1;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}
										}else {
											if(hitLeftAI2==0) {
												hitLeftAI2=1;
												xNewAI2=xLastAI2.charAt(0);
												xNewAI2-=1;
												missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
											}else if(hitDownAI2==0){
												hitDownAI2=1;
												yNewAI2=Integer.parseInt(yLastAI2);
												yNewAI2++;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}else {
												hitUpAI2=1;
												yNewAI2=Integer.parseInt(yLastAI2);
												yNewAI2=yNewAI2-1;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}
										}	
									}else if(Integer.parseInt(yLastAI2)==1){
										if(hitRightAI2==0) {
											hitRightAI2=1;
											xNewAI2=xLastAI2.charAt(0);
											xNewAI2+=1;
											missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
										}else if(hitDownAI2==0){
											hitDownAI2=1;
											yNewAI2=Integer.parseInt(yLastAI2);
											yNewAI2++;
											missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
										}else {
											hitLeftAI2=1;
											xNewAI2=xLastAI2.charAt(0);
											xNewAI2-=1;
											missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
										}
									}else if(Integer.parseInt(yLastAI2)==sizeBoardY-1){
										if(hitRightAI2==0) {
											hitRightAI2=1;
											xNewAI2=xLastAI2.charAt(0);
											xNewAI2+=1;
											missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
										}else if(hitUpAI2==0){
											hitUpAI2=1;
											yNewAI2=Integer.parseInt(yLastAI2);
											yNewAI2--;
											missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
										}else {
											hitLeftAI2=1;
											xNewAI2=xLastAI2.charAt(0);
											xNewAI2-=1;
											missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
										}										
									}else{
										if(hitRightAI2==0) {
											hitRightAI2=1;
											xNewAI2=xLastAI2.charAt(0);
											xNewAI2+=1;
											missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
										}else if(hitLeftAI2==0) {
											hitLeftAI2=1;
											xNewAI2=xLastAI2.charAt(0);
											xNewAI2-=1;
											missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
										}else if(hitDownAI2==0){
											hitDownAI2=1;
											yNewAI2=Integer.parseInt(yLastAI2);
											yNewAI2++;
											missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
										}else {
											hitUpAI2=1;
											yNewAI2=Integer.parseInt(yLastAI2);
											yNewAI2=yNewAI2-1;
											missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
										}
									}
								}else {
									if(positionShipAI2.equals("UNKNOWN")) {
										allHits=lastHitsAI2.size();
										lastHitAI2=lastHitsAI2.get(allHits-1);
										firstHitAI2 =lastHitsAI2.get(0);
										xLastAI2 =lastHitAI2.substring(0,1);
										yLastAI2 =lastHitAI2.substring(1,lastHitAI2.length());
										xFirstAI2 =firstHitAI2.substring(0,1);
										yFirstAI2 =firstHitAI2.substring(1,firstHitAI2.length());
										if(xLastAI2.equals(xFirstAI2)) {
											positionShipAI2="V";
										}
										if(yLastAI2.equals(yFirstAI2)) {
											positionShipAI2="H";
										}
									}
									allHits=lastHitsAI2.size();
									lastHitAI2=lastHitsAI2.get(allHits-1);
									firstHitAI2 =lastHitsAI2.get(0);
									xLastAI2 =lastHitAI2.substring(0,1);
									yLastAI2 =lastHitAI2.substring(1,lastHitAI2.length());
									xFirstAI2 =firstHitAI2.substring(0,1);
									yFirstAI2 =firstHitAI2.substring(1,firstHitAI2.length());
									if(positionShipAI2.equals("V")) {
										if(Integer.parseInt(yLastAI2)==sizeBoardY) {
											yNewAI2=Integer.parseInt(yFirstAI2);
											yNewAI2=yNewAI2-1;
											missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
										}else {
											yNewAI2=Integer.parseInt(yLastAI2);
											yNewAI2=yNewAI2+1;
											missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											if(hitsAI2.contains(missile)&&yLastAI2.equals("1")==false) {
												yNewAI2=Integer.parseInt(yFirstAI2);
												yNewAI2=yNewAI2-1;
												missile = String.valueOf(xLastAI2)+String.valueOf(yNewAI2);
											}
										}
									}else {
										if(xLastAI2.equals(getCharForNumber(sizeBoardX-1))) {
											xNewAI2=xFirstAI2.charAt(0);
											xNewAI2-=1;
											missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
										}else {
											xNewAI2=xLastAI2.charAt(0);
											xNewAI2+=1;
											missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
											if(hitsAI2.contains(missile)&&xLastAI2.equals("A")==false) {
												xNewAI2=xFirstAI2.charAt(0);
												xNewAI2-=1;
												missile = String.valueOf(xNewAI2)+String.valueOf(yLastAI2);
											}
										}
									}
								}
								//DO CODE TO HIT LEFT RIGHT FOR POSITION
							}
							if(hitsAI2.contains(missile)==false&&(missile.substring(1,missile.length()).equals("0")==false)) {
								//rundePlayer2++;
								hitsAI2.add(missile);
								playerC2.hit(missile);
								playerC1.gotHit(missile);
								for (int i=0;i<playerC1.ships.size();i++) {
									if(playerC1.ships.get(i).isHit(missile)) {
										System.out.println(playerC2.name+" has hit "+missile+" and hit a ship");
										resultplayer2 = 1;
										lastHitsAI2.add(missile);
										hitUpAI2=0;
										hitDownAI2=0;
										hitRightAI2=0;
										hitLeftAI2=0;
									}else {
										shipsMissed++;
									}
									playerC1.ships.get(i).hit(missile);
								}
								if(shipsMissed==limitFleet) {
									System.out.println(playerC2.name+" has hit "+missile+" and missed");
									resultplayer2=0;
								}
								for (int i=0;i<playerC1.ships.size();i++) {
									if(playerC1.ships.get(i).isDestroyed()) {
										System.out.println(playerC2.name+" has hit "+missile+" and sunk a ship");
										resultplayer2 = 2;
										sunkenFleet1.add(playerC1.ships.get(i));
										playerC1.ships.remove(i);
										lastHitsAI2.clear();
									}else {
										
									}
									//playerH1.ships.get(i).hit(missile);
									
								}
								
								
								endround2=1;
							}else {
								loop++;
							}
							if(loop==15) {
								hitUpAI2=0;
								hitDownAI2=0;
								hitRightAI2=0;
								hitLeftAI2=0;
								lastHitsAI2.clear();
								loop=0;
							}
							if(lastHitsAI2.size()==2||lastHitsAI2.size()==3) {
								order(lastHitsAI2);
							}
						}
						if (sunkenFleet1.size()== limitFleet) {
							System.out.println("ALL SHIPS OF "+playerC1.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC2.name);
							winner = playerC2.name;
							System.out.println("The winner boards");
							playerC2.showBoards();
							System.out.println("The loser boards");
							playerC1.showBoards();
						}
					}
					playerTurn=1;
					endround1=0;
				}
			}
			if(winner.equals(playerC1.name)) {
				scorePlayer1++;
			}
			if(winner.equals(playerC2.name)) {
				scorePlayer2++;
			}
			if(rounds==100) {
				f.append(playerC1.name+" Level Beginner");
				f.append(',');
				f.append(String.valueOf(scorePlayer1));
				f.append(',');
				f.append(playerC2.name+" Level Medium");
				f.append(',');
				f.append(String.valueOf(scorePlayer2));
				f.append(System.lineSeparator());
				f.flush();
			}else if(rounds==200) {
				f.append(playerC1.name+" Level Beginner");
				f.append(',');
				f.append(String.valueOf(scorePlayer1));
				f.append(',');
				f.append(playerC2.name+" Level Hard");
				f.append(',');
				f.append(String.valueOf(scorePlayer2));
				f.append(System.lineSeparator());
				f.flush();
				}else if(rounds==300) {
				f.append(playerC1.name+" Level Medium");
				f.append(',');
				f.append(String.valueOf(scorePlayer1));
				f.append(',');
				f.append(playerC2.name+" Level Hard");
				f.append(',');
				f.append(String.valueOf(scorePlayer2));
				f.append(System.lineSeparator());
				f.flush();
				f.close();
				}
			
		}//finish 300 rounds
	}

}
