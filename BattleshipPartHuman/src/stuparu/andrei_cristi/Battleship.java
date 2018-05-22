package stuparu.andrei_cristi;
import java.util.*;
public class Battleship {
	//create all the options for a given length and height of a board
	public static ArrayList<String> options(int l,int h) {
		ArrayList<String> result= new ArrayList<String>();
		String option;
		for(int i=0;i<l;i++) {
			for(int j=0;j<h;j++) {
				option=String.valueOf(getCharForNumber(i))+String.valueOf(j+1);
				result.add(option);
			}
		}
		
		return result;
		
	}
	
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

//main start
private static Random rand = new Random();
private static Scanner scanner = new Scanner( System.in );
	
	public static void main(String[] args) {
		ArrayList<String> options= new ArrayList<String>();
		int step1 = 0;
		System.out.println("GAME START");
		System.out.println("CHOSE YOUR GAMETYPE:");
		int number = 0;
		String scan="NOPE";
		int sizeBoardX=0;
		int sizeBoardY=0;
		Human playerH1= new Human();
		Human playerH2= new Human();
		CPU playerC1= new CPU();
		int playerTurn=1;
		int lastGame=0;
		int scorePlayer1=0;
		int scorePlayer2=0;
		int again=1;
		int change=1;
		int step2=0;
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
		int dif=0;

		ArrayList<Ship> sunkenFleet1 = new ArrayList<Ship>();
		ArrayList<Ship> sunkenFleet2 = new ArrayList<Ship>();
		ArrayList<String> lastHitsAI = new ArrayList<String>();
		ArrayList<String> hitsAI = new ArrayList<String>();
		//Creation of players
	
		while(change==1) {
		while(step1 == 0) {
			while(!isNum(scan)) {
				System.out.println("CHOOSE 1 FOR PLAYERvsCOMPUTER AND 2 FOR PLAYERvsPLAYER");
				scan = scanner.nextLine();
			}
			number=Integer.parseInt(scan);
			scan="NOPE";
			if(number == 1) {
				System.out.println("WE ADVISE THE USE OF A SIMETRICAL BOARD AND NO LONGER THAN 26");
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
				System.out.println("PLEASE INSERT YOUR USERNAME");
				String name = scanner.nextLine();
				playerH1.fill(name,"Human",sizeBoardX,sizeBoardY);
				while(!isNum(scan)||(!scan.equals("1")&&!scan.equals("2")&&!scan.equals("3"))) {
					System.out.println("PLEASE INSERT THE DEGREE OF DIFFICULTY IN A RANGE FROM 1 FOR EASY TO 3 FOR HARD");
					scan = scanner.nextLine();
				}
				dif = Integer.parseInt(scan);
				scan="NOPE";
				playerC1.fill(dif,sizeBoardX, sizeBoardY);
				playerH1.setEnemy(playerC1);
				playerC1.setEnemy(playerH1);
				step1=1;
				
			}else if (number == 2){
				System.out.println("WE ADVISE THE USE OF A SIMETRICAL BOARD AND NO LONGER THAN 26");
				System.out.println("BOTH PLAYERS WILL HAE SAME BOARD LENGHT AND HEIGHT SO PLEASE MAKE THIS DECISION TOGETHER");
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
				System.out.println("PLAYER1 PLEASE INSERT YOUR USERNAME");
				String name1 = scanner.nextLine();
				playerH1.fill(name1,"Human",sizeBoardX,sizeBoardY);
				System.out.println("PLAYER2 PLEASE INSERT YOUR USERNAME");
				String name2 = scanner.nextLine();
				playerH2.fill(name2,"Human",sizeBoardX,sizeBoardY);
				playerH1.setEnemy(playerH2);
				playerH2.setEnemy(playerH1);
				step1=1;
			}else {
				System.out.println("THE INPUT YOU INSERTED IS NOT VALID, PLEASE TRY AGAIN");
			}
		}

		options = options(sizeBoardX,sizeBoardY);
		//Type of game choosing part
		
		//Fleet fill step
				
				while(again==1) {
					if(lastGame==1) {
						playerTurn=2;
					}
					if(lastGame==2) {
						playerTurn=1;
					}
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
					lastGame=playerTurn;
					
					sunkenFleet1.clear();;
					sunkenFleet2.clear();
					lastHitsAI.clear();
					hitsAI.clear();
					playerH1.clear();
					playerH2.clear();
					playerC1.clear();
					while(step2 == 0) {
						if(number == 1) {//BOATS FOR PLAYER AND AI
							while(s1p1 < limitShip1 || s2p1 < limitShip2 || s3p1 < limitShip3 || s4p1 < limitShip4 || s5p1 < limitShip5) {
								while(!isNum(scan)) {
									System.out.println("PLEASE INSERT THE TIME OF BOAT YOU WANT TO ADD: ");
									if(s1p1 < limitShip1) {
										System.out.println("PRESS 1 FOR CARRIER WITH 5 SPACES");
									} if(s2p1 < limitShip2) {
										System.out.println("PRESS 2 FOR BATTLESHIP WITH 4 SPACES");
									} if(s3p1 < limitShip3) {
										System.out.println("PRESS 3 FOR CRUISER WITH 3 SPACES");
									} if(s4p1 < limitShip4) {
										System.out.println("PRESS 4 FOR SUBMARINE WITH 3 SPACES");
									} if(s5p1 < limitShip5) {
										System.out.println("PRESS 5 FOR DESTROYER WITH 2 SPACES");
									}
									scan=scanner.nextLine();
								}
								int pick = Integer.parseInt(scan);
								scan="NONE";
								if(pick == 1) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,5)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 5");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Carrier has successfully been created between "+head+" and  "+end);
											Ship carrier1 = new Ship("Carrier", head, end, playerH1);
											playerH1.add(carrier1);
											playerH1.placeShip(carrier1);
											s1p1++;
										}
									}
								}else if(pick == 2 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,4)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 4");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Battleship has successfully been created between "+head+" and  "+end);
											Ship battleship1 = new Ship("Battleship", head, end, playerH1);
											playerH1.add(battleship1);
											playerH1.placeShip(battleship1);
											s2p1++;
										}
									}
									
								}else if(pick == 3 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,3)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 3");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Cruiser has successfully been created between "+head+" and  "+end);
											Ship cruiser1 = new Ship("Cruiser", head, end, playerH1);
											playerH1.add(cruiser1);
											playerH1.placeShip(cruiser1);
											s3p1++;
										}
									}
								}else if(pick == 4 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,3)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 3");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Submarine has successfully been created between "+head+" and  "+end);
											Ship submarine1 = new Ship("Submarine", head, end, playerH1);
											playerH1.add(submarine1);
											playerH1.placeShip(submarine1);
											s4p1++;
										}
									}
								}else if(pick == 5 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,2)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 2");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Destroyer has successfully been created between "+head+" and  "+end);
											Ship destroyer1 = new Ship("Destroyer", head, end, playerH1);
											playerH1.add(destroyer1);
											playerH1.placeShip(destroyer1);
											s5p1++;
										}
									}
								}else {
									
								}
							}while(s1p2 < limitShip1 || s2p2 < limitShip2 || s3p2 < limitShip3 || s4p2 < limitShip4 || s5p2 < limitShip5) {
								//create ships for AI
								int  pick = rand.nextInt(5) + 1;
								if(pick==1&&s1p2<limitShip1) {//pick for carrier
									System.out.println("Carrier AI in creation");
									int  hX = rand.nextInt(sizeBoardX-5);
									int  hY = rand.nextInt(sizeBoardX-5) + 1;
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
										Ship carrier2 = new Ship("Carrier", head, end, playerC1);
										playerC1.add(carrier2);
										playerC1.placeShip(carrier2);
										s1p2++;
										System.out.println("Carrier AI ready");
									}
									
								}else if(pick == 2&&s2p2 < limitShip2) {//pick for battleship
									System.out.println("Battleship AI in creation");
									int  hX = rand.nextInt(sizeBoardX-4);
									int  hY = rand.nextInt(sizeBoardX-4) + 1;
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
										Ship battleship2 = new Ship("Battleship", head, end, playerC1);
										playerC1.add(battleship2);
										playerC1.placeShip(battleship2);
										s2p2++;
										System.out.println("Battleship AI ready");
									}
								}else if(pick == 3&&s3p2 < limitShip3) {//pick for cruiser
									System.out.println("Cruiser AI in creation");
									int  hX = rand.nextInt(sizeBoardX-3);
									int  hY = rand.nextInt(sizeBoardX-3) + 1;
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
										Ship cruiser2 = new Ship("Cruiser", head, end, playerC1);
										playerC1.add(cruiser2);
										playerC1.placeShip(cruiser2);
										s3p2++;
										System.out.println("Cruiser AI ready");
									}
								}else if(pick == 4&&s4p2 < limitShip4) {//pick for sub
									System.out.println("Submarine AI in creation");
									int  hX = rand.nextInt(sizeBoardX-3);
									int  hY = rand.nextInt(sizeBoardX-3) + 1;
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
										Ship submarine2 = new Ship("Submarine", head, end, playerC1);
										playerC1.add(submarine2);
										playerC1.placeShip(submarine2);
										s4p2++;
										System.out.println("Submarine AI ready");
									}
								}else if(pick == 5&&s5p2 < limitShip5) {//pick for destroyer
									System.out.println("Destroyer AI in creation");
									int  hX = rand.nextInt(sizeBoardX-2);
									int  hY = rand.nextInt(sizeBoardX-2) + 1;
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
										Ship destroyer2 = new Ship("Destroyer", head, end, playerC1);
										playerC1.add(destroyer2);
										playerC1.placeShip(destroyer2);
										s5p2++;
										System.out.println("Destroyer AI ready");
									}
								}
							}
						playerH1.myBoard.showBoard();
						step2=1;
						}else if(number == 2) {// BOATS FOR 2 PLAYERS******************************
							//create ships for both players
							System.out.println("Player "+playerH1.name+" will be the first to insert his fleet of ships");
							while(s1p1 < limitShip1 || s2p1 < limitShip2 || s3p1 < limitShip3 || s4p1 < limitShip4 || s5p1 < limitShip5) {
								while(!isNum(scan)) {
									System.out.println("PLEASE INSERT THE TIME OF BOAT YOU WANT TO ADD: ");
									if(s1p1 < limitShip1) {
										System.out.println("PRESS 1 FOR CARRIER WITH 5 SPACES");
									} if(s2p1 < limitShip2) {
										System.out.println("PRESS 2 FOR BATTLESHIP WITH 4 SPACES");
									} if(s3p1 < limitShip3) {
										System.out.println("PRESS 3 FOR CRUISER WITH 3 SPACES");
									} if(s4p1 < limitShip4) {
										System.out.println("PRESS 4 FOR SUBMARINE WITH 3 SPACES");
									} if(s5p1 < limitShip5) {
										System.out.println("PRESS 5 FOR DESTROYER WITH 2 SPACES");
									}
									scan=scanner.nextLine();
								}
								int pick = Integer.parseInt(scan);
								scan="NONE";
								if(pick == 1) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,5)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 5");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Carrier has successfully been created between "+head+" and  "+end);
											Ship carrier1 = new Ship("Carrier", head, end, playerH1);
											playerH1.add(carrier1);
											playerH1.placeShip(carrier1);
											s1p1++;
										}
									}
								}else if(pick == 2 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,4)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 4");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Battleship has successfully been created between "+head+" and  "+end);
											Ship battleship1 = new Ship("Battleship", head, end, playerH1);
											playerH1.add(battleship1);
											playerH1.placeShip(battleship1);
											s2p1++;
										}
									}
									
								}else if(pick == 3 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,3)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 3");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Cruiser has successfully been created between "+head+" and  "+end);
											Ship cruiser1 = new Ship("Cruiser", head, end, playerH1);
											playerH1.add(cruiser1);
											playerH1.placeShip(cruiser1);
											s3p1++;
										}
									}
								}else if(pick == 4 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,3)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 3");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Submarine has successfully been created between "+head+" and  "+end);
											Ship submarine1 = new Ship("Submarine", head, end, playerH1);
											playerH1.add(submarine1);
											playerH1.placeShip(submarine1);
											s4p1++;
										}
									}
								}else if(pick == 5 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,2)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 2");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH1.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Destroyer has successfully been created between "+head+" and  "+end);
											Ship destroyer1 = new Ship("Destroyer", head, end, playerH1);
											playerH1.add(destroyer1);
											playerH1.placeShip(destroyer1);
											s5p1++;
										}
									}
								}else {
									
								}
							}
							System.out.println("Player "+playerH1.name+" would you like to see your map?");
							while(!isNum(scan)) {
								System.out.println("PRESS 1 FOR YES, ANY OTHER NUMBER FOR NO");
								scan = scanner.nextLine();
							}
							int choice = Integer.parseInt(scan);
							if (choice==1) {
								playerH1.myBoard.showBoard();
								System.out.println("When you are done watching press any key to erase your traces!");
								scan = scanner.nextLine();
								playerH1.myBoard.showEmptyBoard();
							}
							scan="NONE";
							System.out.println("Player "+playerH2.name+" will be inserting his fleet of ships now");
							while(s1p2 < limitShip1 || s2p2 < limitShip2 || s3p2 < limitShip3 || s4p2 < limitShip4 || s5p2 < limitShip5) {
								while(!isNum(scan)) {
									System.out.println("PLEASE INSERT THE TIME OF BOAT YOU WANT TO ADD: ");
									if(s1p2 < limitShip1) {
										System.out.println("PRESS 1 FOR CARRIER WITH 5 SPACES");
									} if(s2p2 < limitShip2) {
										System.out.println("PRESS 2 FOR BATTLESHIP WITH 4 SPACES");
									} if(s3p2 < limitShip3) {
										System.out.println("PRESS 3 FOR CRUISER WITH 3 SPACES");
									} if(s4p2 < limitShip4) {
										System.out.println("PRESS 4 FOR SUBMARINE WITH 3 SPACES");
									} if(s5p2 < limitShip5) {
										System.out.println("PRESS 5 FOR DESTROYER WITH 2 SPACES");
									}
									scan=scanner.nextLine();
								}
								int pick = Integer.parseInt(scan);
								scan="NONE";
								if(pick == 1) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,5)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 5");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH2.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Carrier has successfully been created between "+head+" and  "+end);
											Ship carrier2 = new Ship("Carrier", head, end, playerH2);
											playerH2.add(carrier2);
											playerH2.placeShip(carrier2);
											s1p2++;
										}
									}
								}else if(pick == 2 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,4)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 4");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH2.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Battleship has successfully been created between "+head+" and  "+end);
											Ship battleship2 = new Ship("Battleship", head, end, playerH2);
											playerH2.add(battleship2);
											playerH2.placeShip(battleship2);
											s2p2++;
										}
									}
									
								}else if(pick == 3) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,3)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 3");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH2.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Cruiser has successfully been created between "+head+" and  "+end);
											Ship cruiser2 = new Ship("Cruiser", head, end, playerH2);
											playerH2.add(cruiser2);
											playerH2.placeShip(cruiser2);
											s3p2++;
										}
									}
								}else if(pick == 4 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,3)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 3");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH2.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Submarine has successfully been created between "+head+" and  "+end);
											Ship submarine2 = new Ship("Submarine", head, end, playerH2);
											playerH2.add(submarine2);
											playerH2.placeShip(submarine2);
											s4p2++;
										}
									}
								}else if(pick == 5 ) {
									System.out.println("COORDONATES SHOULD BE LIKE THE FOLLOWING EXAMPLE: A3 ");
									System.out.println("YOU HAVE CHOSEN A BOARD OF "+sizeBoardX+" LINES AND "+sizeBoardY+" COLUMNS");
									System.out.println("PLEASE INSERT THE COORDONATE OF THE HEAD OF THE SHIP");
									String head = scanner.nextLine();
									System.out.println("PLEASE INSERT THE COORDONATE OF THE END OF THE SHIP");
									String end = scanner.nextLine();
									if(options.contains(head)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(options.contains(end)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE END!");
									}else if (check(head,end)== false) {
										System.out.println("YOUR HEAD AND END OF THE SHIP MUCH BE BOTH ON EITHER THE SAME LINE OR THE SAME COLUMN");
									}else {
										if(compare(head,end)==false) {
											String var = head;
											head= end;
											end=var;
										}
										if(shipLenght(head,end,2)==false) {
											System.out.println("THE DIFFERENCE BETWEEN YOUR HEAD COORDONATE AND END COORDONATE IS DIFFERENT OF 2");
										}else if(coordVerify(head,sizeBoardX,sizeBoardY)==false|| coordVerify(end,sizeBoardX,sizeBoardY)==false) {
											System.out.println("ONE OR BOTH COORDONATES YOU HAVE INTRODUCED ARE NOT ACCEPTED BY THE LIMITS OF THE BOARD YOU HAVE CHOSEN");
										}else if(spacesAvail(head,end,playerH2.totalSpaces)==false){
											System.out.println("THE COORDINATES FOR THE SHIP YOU WISH TO CREATE OVERLAP WITH ANOTHER SHIP ALREADY PLACED");
										}else{
											System.out.println("Ship Destroyer has successfully been created between "+head+" and  "+end);
											Ship destroyer2 = new Ship("Destroyer", head, end, playerH2);
											playerH2.add(destroyer2);
											playerH2.placeShip(destroyer2);
											s5p2++;
										}
									}
								}
							}
							System.out.println("Player "+playerH2.name+" would you like to see your map?");
							System.out.println("PRESS 1 FOR YES, 0 FOR NO");
							while(!isNum(scan)) {
								System.out.println("PRESS 1 FOR YES, ANY OTHER NUMBER FOR NO");
								scan = scanner.nextLine();
							}
							int choice2 = Integer.parseInt(scan);
							if (choice2==1) {
								playerH2.myBoard.showBoard();
								System.out.println("When you are done watching press any key to erase your traces!");
								scan = scanner.nextLine();
								playerH2.myBoard.showEmptyBoard();
							}
							scan="NONE";
							step2=1;
						}
					}
					String winner = "None";
					int allHits=0;
					int endround1=0;
					int endround2=0;
					int resultplayer1=0;
					int resultplayer2=0;
					
					int shipsMissed=0;
					int firstRound = 0;
					int loop=0;
					String missile;
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
					//for AI HARD
					int rdy=0;
					sunkenFleet1.clear();;
					sunkenFleet2.clear();
					lastHitsAI.clear();
					hitsAI.clear();
					//game useful
			
					while(winner.equals("None")) {
						if(number == 2) {//human vs human**************************
							if(playerTurn==1) {
								System.out.println("It's "+playerH1.name+" turn to attack");
								shipsMissed=0;
								if(firstRound==1) {
									if(resultplayer2 == 1) {
										System.out.println("You have been hit last turn by your opponent, check your board for more information");
										playerH1.myBoard.showBoard();
										System.out.println("When you are ready to attack please insert any key");
										scan = scanner.nextLine();
										scan = "NONE";
									}else if(resultplayer2==2){
										System.out.println("You have been hit last turn by your opponent, check your board for more information");
										System.out.println("One of your ships have been sunk");
										playerH1.myBoard.showBoard();
										System.out.println("When you are ready to attack please insert any key");
										scan = scanner.nextLine();
										scan = "NONE";
									}else {
										System.out.println("Your opponent has not hit any of your ships last turn");
									}
									
								}
								firstRound=1;
								while(endround1 == 0) {
									System.out.println("Take a look at your map of hits so far and think where would you like your next hit to be");
									playerH1.enemyBoard.showBoard();
									System.out.println("Please insert your firing coordonate following the example: A3");
									missile = scanner.nextLine();
									if(options.contains(missile)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(coordVerify(missile,sizeBoardX,sizeBoardY)) {
										playerH1.hit(missile);
										playerH2.gotHit(missile);
										for (int i=0;i<playerH2.ships.size();i++) {
											if(playerH2.ships.get(i).isHit(missile)) {
												System.out.println("You have hit a boat of "+playerH2.name);
												resultplayer1 = 1;
											}else {
												shipsMissed++;
											}
											playerH2.ships.get(i).hit(missile);
										}
										if(shipsMissed==limitFleet) {
											System.out.println("You have failed to hit a boat of "+playerH2.name);
											resultplayer1=0;
										}
										for (int i=0;i<playerH2.ships.size();i++) {
											if(playerH2.ships.get(i).isDestroyed()) {
												System.out.println("You have sunk a boat of "+playerH2.name);
												resultplayer1 = 2;
												sunkenFleet2.add(playerH2.ships.get(i));
												playerH2.ships.remove(i);
											}else {
												
											}
											//playerH2.ships.get(i).hit(missile);
											
										}
										endround1=1;
									}else {
										System.out.println("The Coordonate you have introduced does not fit on the board you have chosen");
									}
								}
								if (sunkenFleet2.size()== limitFleet) {
									System.out.println("ALL SHIPS OF "+playerH2.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerH1.name);
									winner = playerH1.name;
								}
								playerH1.showBoards();
								if(winner.equals("None")) {
									System.out.println("When you are ready to end your turn please insert any key");
									scan = scanner.nextLine();
									scan="NONE";
									playerH1.showEmptyBoards();
									scanner.nextLine();
								}
								playerTurn=2;
								endround2=0;
							
							}else {
								System.out.println("It's "+playerH2.name+" turn to attack");
								shipsMissed=0;
								if(resultplayer1 == 1) {
									System.out.println("You have been hit last turn by your opponent, check your board for more information");
									playerH2.myBoard.showBoard();
									System.out.println("When you are ready to attack please insert any key");
									scan = scanner.nextLine();
									scan="NONE";
								}else if(resultplayer1==2){
									System.out.println("You have been hit last turn by your opponent, check your board for more information");
									System.out.println("One of your ships have been sunk");
									playerH2.myBoard.showBoard();
									System.out.println("When you are ready to attack please insert any key");
									scan = scanner.nextLine();
									scan="NONE";
								}else {
									System.out.println("Your opponent has not hit any of your ships last turn");
								}
								while(endround2 == 0) {
									System.out.println("Take a look at your map of hits so far and think where would you like your next hit to be");
									playerH2.enemyBoard.showBoard();
									System.out.println("Please insert your firing coordonate following the example: A3");
									missile = scanner.nextLine();
									if(options.contains(missile)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(coordVerify(missile,sizeBoardX,sizeBoardY)) {
										playerH2.hit(missile);
										playerH1.gotHit(missile);
										for (int i=0;i<playerH1.ships.size();i++) {
											if(playerH1.ships.get(i).isHit(missile)) {
												System.out.println("You have hit a boat of "+playerH1.name);
												resultplayer2 = 1;
											}else {
												shipsMissed++;
											}
											playerH1.ships.get(i).hit(missile);
										}
										if(shipsMissed==limitFleet) {
											System.out.println("You have failed to hit a boat of "+playerH1.name);
											resultplayer2=0;
										}
										for (int i=0;i<playerH1.ships.size();i++) {
											if(playerH1.ships.get(i).isDestroyed()) {
												System.out.println("You have sunk a boat of "+playerH1.name);
												resultplayer2 = 2;
												sunkenFleet1.add(playerH1.ships.get(i));
												playerH1.ships.remove(i);
											}else {
												
											}
											//playerH1.ships.get(i).hit(missile);
											
										}
										endround2=1;
									}else {
										System.out.println("The Coordonate you have introduced does not fit on the board you have chosen");
									}
								}
								if (sunkenFleet1.size()== limitFleet) {
									System.out.println("ALL SHIPS OF "+playerH1.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerH2.name);
									winner = playerH2.name;
								}
								playerH2.showBoards();
								if(winner.equals("None")) {
									System.out.println("When you are ready to end your turn please insert any key");
									scan = scanner.nextLine();
									scan="NONE";
									playerH2.showEmptyBoards();
									scanner.nextLine();
								}
								playerTurn=1;
								endround1=0;
							}
						}else if(number == 1) {//player vs AI****************************
							if(playerTurn==1) {
								System.out.println("It's "+playerH1.name+" turn to attack");
								shipsMissed=0;
								if(firstRound==1) {
									if(resultplayer2 == 1) {
										System.out.println("You have been hit last turn by your opponent, check your board for more information");
										playerH1.myBoard.showBoard();
										System.out.println("When you are ready to attack please insert any key");
										scan = scanner.nextLine();
										scan="NONE";
									}else if(resultplayer2==2){
										System.out.println("You have been hit last turn by your opponent, check your board for more information");
										System.out.println("One of your ships have been sunk");
										playerH1.myBoard.showBoard();
										System.out.println("When you are ready to attack please insert any key");
										scan = scanner.nextLine();
										scan="NONE";
									}else {
										System.out.println("Your opponent has not hit any of your ships last turn");
									}
									
								}
								firstRound=1;
								while(endround1 == 0) {
									System.out.println("Take a look at your map of hits so far and think where would you like your next hit to be");
									playerH1.enemyBoard.showBoard();
									System.out.println("Please insert your firing coordonate following the example: A3");
									missile = scanner.nextLine();
									if(options.contains(missile)==false) {
										System.out.println("YOU HAVE INTRODUCED A WRONG VALUE FOR THE HEAD!");
									}else if(coordVerify(missile,sizeBoardX,sizeBoardY)) {
										playerH1.hit(missile);
										playerC1.gotHit(missile);
										for (int i=0;i<playerC1.ships.size();i++) {
											if(playerC1.ships.get(i).isHit(missile)) {
												System.out.println("You have hit a boat of "+playerC1.name);
												resultplayer1 = 1;
											}else {
												shipsMissed++;
											}
											playerC1.ships.get(i).hit(missile);
										}
										if(shipsMissed==limitFleet) {
											System.out.println("You have failed to hit a boat of "+playerC1.name);
											resultplayer1=0;
										}
										for (int i=0;i<playerC1.ships.size();i++) {
											if(playerC1.ships.get(i).isDestroyed()) {
												System.out.println("You have sunk a boat of "+playerC1.name);
												resultplayer1 = 2;
												sunkenFleet2.add(playerC1.ships.get(i));
												playerC1.ships.remove(i);
											}else {
												
											}
											//playerH2.ships.get(i).hit(missile);
											
										}
										endround1=1;
									}else {
										System.out.println("The Coordonate you have introduced does not fit on the board you have chosen");
									}
								}
								if (sunkenFleet2.size()== limitFleet) {
									System.out.println("ALL SHIPS OF "+playerC1.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerH1.name);
									winner = playerH1.name;
								}
								playerH1.showBoards();
								if(winner.equals("None")) {
									System.out.println("When you are ready to end your turn please insert any key");
									scan = scanner.nextLine();
									scan="NONE";
								}
								playerTurn=2;
								endround2=0;
							
							}else {
								shipsMissed=0;
								if(playerC1.difficulty==1) {//EASY mode********************
									int  coordY = rand.nextInt(sizeBoardY) + 1;
									int  coordX = rand.nextInt(sizeBoardX) + 1;
									char mX =getCharForNumber(coordX);
									missile = String.valueOf(mX)+String.valueOf(coordY);
									hitsAI.add(missile);
									playerC1.hit(missile);
									playerH1.gotHit(missile);
									for (int i=0;i<playerH1.ships.size();i++) {
										if(playerH1.ships.get(i).isHit(missile)) {
											System.out.println(playerC1.name+" has hit "+missile+" and hit a ship");
											resultplayer2 = 1;
										}else {
											shipsMissed++;
										}
										playerH1.ships.get(i).hit(missile);
									}
									if(shipsMissed==limitFleet) {
										System.out.println(playerC1.name+" has hit "+missile+" and missed");
										resultplayer2=0;
									}
									for (int i=0;i<playerH1.ships.size();i++) {
										if(playerH1.ships.get(i).isDestroyed()) {
											System.out.println(playerC1.name+" has hit "+missile+" and sunk a ship");
											resultplayer2 = 2;
											sunkenFleet1.add(playerH1.ships.get(i));
											playerH1.ships.remove(i);
										}else {
											
										}
										//playerH1.ships.get(i).hit(missile);
										
									}
									if (sunkenFleet1.size()== limitFleet) {
										System.out.println("ALL SHIPS OF "+playerH1.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC1.name);
										winner = playerC1.name;
										playerC1.showBoards();
									}
								}else if(playerC1.difficulty==2) {//MEDIUM mode********************
									while(endround2 == 0) {
										int  coordY = rand.nextInt(sizeBoardY) + 1;
										int  coordX = rand.nextInt(sizeBoardX) + 1;
										char mX =getCharForNumber(coordX);
										missile = String.valueOf(mX)+String.valueOf(coordY);
										if(hitsAI.contains(missile)==false) {
											hitsAI.add(missile);
											playerC1.hit(missile);
											playerH1.gotHit(missile);
											
											for (int i=0;i<playerH1.ships.size();i++) {
												if(playerH1.ships.get(i).isHit(missile)) {
													System.out.println(playerC1.name+" has hit "+missile+" and hit a ship");
													resultplayer2 = 1;
												}else {
													shipsMissed++;
												}
												playerH1.ships.get(i).hit(missile);
											}
											if(shipsMissed==limitFleet) {
												System.out.println(playerC1.name+" has hit "+missile+" and missed");
												resultplayer2=0;
											}
											for (int i=0;i<playerH1.ships.size();i++) {
												if(playerH1.ships.get(i).isDestroyed()) {
													System.out.println(playerC1.name+" has hit "+missile+" and sunk a ship");
													resultplayer2 = 2;
													sunkenFleet1.add(playerH1.ships.get(i));
													playerH1.ships.remove(i);
												}else {
													
												}
												//playerH1.ships.get(i).hit(missile);
												
											}
											endround2=1;
										}
									}
									if (sunkenFleet1.size()== limitFleet) {
										System.out.println("ALL SHIPS OF "+playerH1.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC1.name);
										winner = playerC1.name;
										playerC1.showBoards();
									}
								}else {//HARD mode***************************
									while(endround2 == 0) {
										if(lastHitsAI.size()==0) {
											int  coordY = rand.nextInt(sizeBoardY) + 1;
											int  coordX = rand.nextInt(sizeBoardX) + 1;
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
													if(xLast==xFirst) {
														positionShipAI="V";
													}
													if(yLast==yFirst) {
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
														if(hitsAI.contains(missile)&& xLast.equals("A")==false) {
															xNew=xFirst.charAt(0);
															xNew-=1;
															missile = String.valueOf(xNew)+String.valueOf(yLast);
														}
													}
												}
											}
											//DO CODE TO HIT LEFT RIGHT FOR POSITION
										}
										if(hitsAI.contains(missile)==false&&(missile.substring(1,missile.length()).equals("0")==false)) {
											hitsAI.add(missile);
											playerC1.hit(missile);
											playerH1.gotHit(missile);
											for (int i=0;i<playerH1.ships.size();i++) {
												if(playerH1.ships.get(i).isHit(missile)) {
													System.out.println(playerC1.name+" has hit "+missile+" and hit a ship");
													resultplayer2 = 1;
													lastHitsAI.add(missile);
													hitUp=0;
													hitDown=0;
													hitRight=0;
													hitLeft=0;
												}else {
													shipsMissed++;
												}
												playerH1.ships.get(i).hit(missile);
											}
											if(shipsMissed==limitFleet) {
												System.out.println(playerC1.name+" has hit "+missile+" and missed");
												resultplayer2=0;
											}
											for (int i=0;i<playerH1.ships.size();i++) {
												if(playerH1.ships.get(i).isDestroyed()) {
													System.out.println(playerC1.name+" has hit "+missile+" and sunk a ship");
													resultplayer2 = 2;
													sunkenFleet1.add(playerH1.ships.get(i));
													playerH1.ships.remove(i);
													lastHitsAI.clear();
												}else {
													
												}
												//playerH1.ships.get(i).hit(missile);
												
											}
											/*if(resultplayer2==2) {
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
											endround2=1;
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
									if (sunkenFleet1.size()== limitFleet) {
										System.out.println("ALL SHIPS OF "+playerH1.name+ " HAVE BEEN SENT TO DAVY JONES' LOCKER! CONGRATULATION "+ playerC1.name);
										winner = playerC1.name;
										System.out.println("The winner boards");
										playerC1.showBoards();
										System.out.println("The loser boards");
										playerH1.showBoards();
									}
								}
								playerTurn=1;
								endround1=0;
							}	
						}
					}
					if(number==1) {//Player vs AI
						if(winner.equals(playerC1.name)) {
							scorePlayer2++;
						}
						if(winner.equals(playerH1.name)) {
							scorePlayer1++;
						}
					}else {//PvP
						if(winner.equals(playerH2.name)) {
							scorePlayer2++;
						}
						if(winner.equals(playerH1.name)) {
							scorePlayer1++;
						}
					}
					
					while(!isNum(scan)){
						System.out.println("Would You Like to play another round with the same players?");
						System.out.println("Insert 1 for YES or any other number for NO!");
						scan=scanner.nextLine();
					}
					again  = Integer.parseInt(scan);
					scan="NONE";
					if(again==1) {
						step2=0;
					}else {	
						again=0;
					}
					
					
			//end game 
				}//end partial game reset
				
				if(number==1) {//Player vs AI
					System.out.println("The score is: "+ scorePlayer1+" wins for "+ playerH1.name+" and "+ scorePlayer2+" wins for "+ playerC1.name);
				}else {//PvP
					System.out.println("The score is: "+ scorePlayer1+" wins for "+ playerH1.name+" and "+ scorePlayer2+" wins for "+ playerH2.name);
				}
				while(!isNum(scan)){
					System.out.println("Would You Like to play again with new players?");
					System.out.println("Insert 1 for YES or any other number for NO!");
					scan=scanner.nextLine();
				}
					
				change  = Integer.parseInt(scan);
				scan="NONE";
				if(change==1) {
					step1=0;
					step2=0;
				}else {
					change=0;
				}
		}//end full game reset
					
	}//end main

}//end class
