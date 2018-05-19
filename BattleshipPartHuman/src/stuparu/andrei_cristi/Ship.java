package stuparu.andrei_cristi;
import java.util.ArrayList;
public class Ship {
	
	public String shipClass;
	public int size;
	public int hp;
	public String head;
	public String end;
	public Player owner;
	/*additional things*/
	String xHead;
	String xEnd;
	String yHead;
	String yEnd;
	char xH;
	int yH;
	char xHInitial;
	int yHInitial;
	/*down until here*/
	public String position;
	ArrayList<String> spaces = new ArrayList<String>();
	ArrayList<String> spaces2 = new ArrayList<String>();
	
	public Ship(String shipClass, String head, String end,Player owner) {
		this.shipClass = shipClass;
		this.head = head;
		this.end = end;
		this.owner=owner;
		/*set size and hp*/
		if (shipClass == "Carrier"){
			size=5;
			hp=5;
		}else if(shipClass=="Battleship"){
			size=4;
			hp=4;
		}else if(shipClass=="Cruiser"){
			size=3;
			hp=3;
		}else if(shipClass=="Submarine"){
			size=3;
			hp=3;
		}else{
			size=2;
			hp=2;
		}
		/*set position*/
		xHead =head.substring(0,1);
		xEnd =end.substring(0,1);
		if (xHead.equals(xEnd)){
			position = "V";
		}else{
			position = "H";
		}
		/*set spaces(squares)*/
		yHead =head.substring(1,head.length());
		yEnd =end.substring(1,end.length());
		spaces.add(head);
		spaces2.add(head);
		String pos;
		String nou;
		xH=xHead.charAt(0);
		yH = Integer.valueOf(yHead);
		xHInitial=xH;
		yHInitial=yH;
		if (position=="V"){
			for(int i=1;i<size;i++){
				yH+=1;
				nou = String.valueOf(yH);
				pos = xHead+nou;
				spaces.add(pos);
				spaces2.add(pos);
			} 
		}else{
			for(int i=1;i<size;i++){
				xH+=1;
				nou = String.valueOf(xH);
				pos = nou+yHead;
				spaces.add(pos);
				spaces2.add(pos);
			} 
		}
		
	}
	
	public boolean isHit(String missileCoord){
		boolean hit = spaces.contains(missileCoord);
		return hit;
	}
	
	public void hit(String missileCoord){
		boolean hit = spaces.contains(missileCoord);
		if (hit){
			hp=hp-1;
			spaces.remove(missileCoord);
		}
		this.isDestroyed();
	}

	public boolean isDestroyed(){
		if (hp==0){
			owner.gotShipSunk(this);
			return true;
		}else{
			return false;
		}
	}

}