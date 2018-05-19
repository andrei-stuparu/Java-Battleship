package fr.battleship;

public class Board {
	public int sizeX;
	public int sizeY;
	public char[][] map= new char [26][26] ;
	
	
	public Board() {
	}

	public Board(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		for(int i=0;i<sizeY;i++){
			for(int j=0;j<sizeX;j++){	
				map[i][j]='~';
			}
		}
	}
	
	public void clear() {
		for(int i=0;i<sizeY;i++){
			for(int j=0;j<sizeX;j++){	
				map[i][j]='~';
			}
		}
	}
	public void fillBoard() {
		for(int i=0;i<sizeY;i++){
			for(int j=0;j<sizeX;j++){	
				map[i][j]='~';
			}
		}
	}
	
	public char getCharForNumber(int i) {
	    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	    if (i > 26) {
	        return '?';
	    }
	    return alphabet[i];
	}
	

	public void showBoard(){
		for(int i=0;i<=sizeY;i++){
			for(int j=0;j<=sizeX+1;j++){
				if(i==0 && j==0){
					System.out.print(' ');
				}else if(j==0){
					System.out.print(i);
				}else if(j==1){
					if (i<10) {
						System.out.print(" ");
					}
				}else if(i==0){
					System.out.print(getCharForNumber(j-2));
				}else{
					System.out.print(map[i-1][j-2]);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void showEmptyBoard(){
		for(int i=0;i<=sizeY;i++){
			for(int j=0;j<=sizeX+1;j++){
				if(i==0 && j==0){
					System.out.print(' ');
				}else if(j==0){
					System.out.print(i);
				}else if(j==1){
					if (i<10) {
						System.out.print(" ");
					}
				}else if(i==0){
					System.out.print(getCharForNumber(j-1));
				}else{
					System.out.print("~");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
}
