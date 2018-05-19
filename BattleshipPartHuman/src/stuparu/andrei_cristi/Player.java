package stuparu.andrei_cristi;

public interface Player {
	
	public void setEnemy(Human enemy);
	public void setEnemy(CPU enemy);
	public int charToInt(char s);
	public void add(Ship s);
	public int stringToInt(String s);
	public void placeShip(Ship s);
	public void showBoards();
	public void showEmptyBoards();
	public void hit(String missileCoord);
	public void gotHit(String missileCoord);
	public void gotShipSunk(Ship s);
}
