package jumpingalien.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jumpingalien.model.Tile;
import jumpingalien.model.GameObject;
import jumpingalien.util.ModelException;

//TODO: checken of we de arraylist kunnen behouden of dat we misschien een HashSet moeten gebruiken.

public class World {
	public World (int tileSize, int nbTilesX, int nbTilesY,int visibleWindowWidth, int visibleWindowHeight, 
			int targetTileX, int targetTileY){
		for (int i = 0; i<= nbTilesX*nbTilesY-1; i++){
			tiles.add(new Tile(((i)%nbTilesX)*tileSize, (((i)%nbTilesX)*tileSize)/nbTilesX, 0, this));
		}
		setxMax( nbTilesX*tileSize);
		setyMax( nbTilesY*tileSize);
		setVisibleWindowHeight(visibleWindowHeight);
		setVisibleWindowWidth(visibleWindowWidth);
		setTargetTileX(targetTileX);
		setTargetTileY(targetTileY);
		this.tileSize = tileSize;
		setNbTilesX(nbTilesX);
		setNbTilesY(nbTilesY);
	}
	
	
	public int getNbTilesX() {
		return nbTilesX;
	}

	public void setNbTilesX(int nbTilesX) {
		this.nbTilesX = nbTilesX;
	}
	
	private int nbTilesX;

	public int getNbTilesY() {
		return nbTilesY;
	}

	public void setNbTilesY(int nbTilesY) {
		this.nbTilesY = nbTilesY;
	}

	private int nbTilesY;
	
	public final int getTileSize() {
		return tileSize;
	}
	
	private final int tileSize;

	public int getTargetTileX() {
		return targetTileX;
	}

	public void setTargetTileX(int targetTileX) {
		this.targetTileX = targetTileX;
	}
	
	public int targetTileX;

	public int getTargetTileY() {
		return targetTileY;
	}

	public void setTargetTileY(int targetTileY) {
		this.targetTileY = targetTileY;
	}

	public int targetTileY;
	
	public int findTile(int pixelX, int pixelY){
		boolean a =true;
		int index = -1;
		while(a==true){
			for(int i=0 ; i<= tiles.size()-1 ; i++){
				if(tiles.get(i).getTileX() == pixelX && tiles.get(i).getTileY()==pixelY){
					index =i;
					a=false;
				}
			}
		}
		return index;
	}
	
	public int findTileForPixel(int pixelX, int pixelY){
		boolean a = true;
		int index = -1;
		while(a==true){
			for(int i=0; i<=tiles.size()-1; i ++){
				if( (tiles.get(i).getTileX() <= pixelX ) && (pixelX < (tiles.get(i).getTileX() + tiles.get(i).getLength())) 
				&& (tiles.get(i).getTileY() <= pixelY) && (pixelY < (tiles.get(i).getTileY() + tiles.get(i).getLength()))){
					index = i;
					a=false;
				}
			}
		}
		return index;
	}
	
	public boolean tileExistsOn(int pixelX, int pixelY){
		if(findTile(pixelX,pixelY) == -1) return false;
		else return true;
	}

	
	public int[] getBottomLeftPixelOfTile(int tileX, int tileY){
//		int i = findTileForPixel(tileX,tileY);
		int[] array = {tileX, tileY};
//		int[] array = {tiles.get(i).getTileX(), tiles.get(i).getTileY()};
		return array;
		//TODO: klopt het dat je hier gewoon de megegeven punten?
		// Zie facade.
	}
	
	public int[][] getTilePositions(int pixelLeft, int pixelBottom, int pixelRight, int pixelTop)throws IllegalArgumentException{ 
			if(!tileExistsOn(pixelLeft, pixelBottom))
				throw new IllegalArgumentException();
			int nbTilesXractangle= (pixelRight - pixelLeft)/getTileSize();
			int nbTilesYractangle= (pixelTop - pixelBottom)/getTileSize();
			int [][] array = new int [nbTilesXractangle*nbTilesYractangle][2];
			for (int i = 0; i<= nbTilesXractangle*nbTilesYractangle-1 ; i++){
				array[i][0] =  pixelLeft + ((i)%nbTilesXractangle)*getTileSize();
				array[i][1] =  pixelBottom + (((i)%nbTilesXractangle)*getTileSize())/nbTilesXractangle;
				}			
			return array;
	}
	
	public void setGeologicalFeature(int tileX, int tileY, int tileType){
		for(int i=0 ; i<= tiles.size()-1 ; i++)
			if(tiles.get(i).getTileX() == tileX && tiles.get(i).getTileY()==tileY){
				tiles.remove(i);
				tiles.add(i, new Tile(tileX, tileY, tileType, this));
			}
	}
	
	public int getGeologicalFeature(int pixelX, int pixelY)
			throws IllegalArgumentException{	
			if(!tileExistsOn(pixelX,pixelY))
				throw new IllegalArgumentException();
			else	
			return tiles.get(findTile(pixelX, pixelY)).getGeologicalFeature();
	}
	

	public boolean isPassableTerrain(Tile tile){
		if(tile.getGeologicalFeature() == 3)
			return false;
		return true;
	}
	
	public ArrayList<Tile> getTiles() {
		return (ArrayList<Tile>) tiles.clone();
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	private ArrayList<Tile> tiles= new ArrayList<Tile>();

	
	
	
	public int getVisibleWindowWidth() {
		return visibleWindowWidth;
	}

	private void setVisibleWindowWidth(int visibleWindowWidth) throws IllegalArgumentException{
//		if(visibleWindowWidth< 400+ mazub.getWidth() || visibleWindowWidth > getxMax())
//			throw new IllegalArgumentException(); TODO: waarom crasht tie hierop?
		this.visibleWindowWidth = visibleWindowWidth;
	}

	private int visibleWindowWidth;
	
	public int getVisibleWindowHeight() {
		return visibleWindowHeight;
	}

	private void setVisibleWindowHeight(int visibleWindowHeight) throws IllegalArgumentException{
//		if(visibleWindowHeight< 400+ mazub.getHeight() || visibleWindowHeight > getyMax())
//			throw new IllegalArgumentException(); //TODO zelfde vraag.
		this.visibleWindowHeight = visibleWindowHeight;
	}
	
	private int visibleWindowHeight;
	
	public int getVisibleWindowLeft()throws IllegalStateException{
		return (int) (mazub.getPositionX() - (getVisibleWindowWidth()-mazub.getWidth())/2);
	}
	
	public int getVisibleWindowBottom(){
		return (int) (mazub.getPositionY() - (getVisibleWindowHeight()-mazub.getHeight())/2);
	}
	
	public int getVisibleWindowRight(){
		return (int) (mazub.getPositionX() + mazub.getWidth()/2 + getVisibleWindowWidth()/2);
	}
	
	public int getVisibleWindowTop(){
		return (int) (mazub.getPositionY() + mazub.getHeight()/2 + getVisibleWindowHeight()/2);
	}
	
	public int[] getVisibleWindow()throws IllegalStateException{
		int[] array = {getVisibleWindowLeft(), getVisibleWindowBottom(), getVisibleWindowRight(), getVisibleWindowTop()};
		if(!isValidVisibleWindow(array))
			throw new IllegalStateException();
		else if(getVisibleWindowLeft()<0 && getVisibleWindowBottom() < 0){
			array[0] = 0;
			array[1] = 0;
			array[2] = getVisibleWindowRight() -getVisibleWindowLeft();
			array[3] = getVisibleWindowTop() - getVisibleWindowBottom();
			return array;
		}
		else if(getVisibleWindowLeft()<0 && getVisibleWindowTop() > getyMax()){
			array[0] = 0;
			array[1] = getVisibleWindowBottom() - (getVisibleWindowTop() - getyMax());
			array[2] = getVisibleWindowRight() - getVisibleWindowLeft();
			array[3] = getyMax();
			return array;
		}
		else if(getVisibleWindowRight()>getxMax() && getVisibleWindowTop() > getyMax()){
			array[0] = getVisibleWindowLeft() - (getVisibleWindowRight()-getxMax());
			array[1] = getVisibleWindowBottom() - (getVisibleWindowTop() - getyMax());
			array[2] = getxMax();
			array[3] = getyMax();
			return array;
		}
		else if(getVisibleWindowRight()>getxMax() && getVisibleWindowBottom() < 0){
			array[0] = getVisibleWindowLeft() - (getVisibleWindowRight()-getxMax());
			array[1] = 0;
			array[2] = getxMax();
			array[3] = getVisibleWindowTop() - getVisibleWindowBottom();
			return array;
		}
		else if( getVisibleWindowBottom() < 0){
			array[0] = getVisibleWindowLeft() ;
			array[1] = 0;
			array[2] = getVisibleWindowRight();
			array[3] = getVisibleWindowTop() - getVisibleWindowBottom();
			return array;
		}
		else if(getVisibleWindowLeft()<0 ){
			array[0] = 0;
			array[1] = getVisibleWindowBottom();
			array[2] = getVisibleWindowRight() -getVisibleWindowLeft();
			array[3] = getVisibleWindowTop();
			return array;
		}
		
		else if( getVisibleWindowTop() > getyMax()){
			array[0] = getVisibleWindowLeft();
			array[1] = getVisibleWindowBottom() - (getVisibleWindowTop() - getyMax());
			array[2] = getVisibleWindowRight();
			array[3] = getyMax();
			return array;
		}
		else if(getVisibleWindowRight()>getxMax()){
			array[0] = getVisibleWindowLeft() - (getVisibleWindowRight()-getxMax());
			array[1] = getVisibleWindowBottom();
			array[2] = getxMax();
			array[3] = getVisibleWindowTop();
			return array;
		}
		else return array;
	}
	
	public boolean isValidVisibleWindow(int[] array){
		if(array[0] > -getVisibleWindowWidth() && array[1] > -getVisibleWindowHeight() 
				&& array[2]< getxMax()+getVisibleWindowWidth() && array[3]< getyMax() +getVisibleWindowHeight())
			return true;
		else return false;
	}
	
	private int xMax;
	
	public int getxMax() {
		return xMax;
	}

	private void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public int getyMax() {
		return yMax;
	}

	private void setyMax(int yMax) {
		this.yMax = yMax;
	}

	private int yMax;
	
	public ArrayList<GameObject> getGameObjects() {
		return (ArrayList<GameObject>) gameObjects.clone();
	}

//	public ArrayList<?> listOf(Class<?> Klasse){
//		ArrayList<Klasse> list = new ArrayList<Klasse>();
//		for(GameObject object: gameObjects)
//			if(Klasse.class.isAssignableFrom(object.getClass()))
//				list.add((Klasse) object);
//				
//		return null;
//	} //TODO: hoe adden we voor een algemeen game object aan een specifieke list (aanpasbaarheid).
	// Ofwel hier 3 methodes van ofwel de lijsten wel 3 maken.
	public ArrayList<Plant> listPlant(){
		ArrayList<Plant> list = new ArrayList<Plant>();
		for(GameObject object: gameObjects)
			if(Plant.class.isAssignableFrom(object.getClass()))
				list.add((Plant) object);
		return list;
	}
	
	public ArrayList<Shark> listShark(){
		ArrayList<Shark> list = new ArrayList<Shark>();
		for(GameObject object: gameObjects)
			if(Shark.class.isAssignableFrom(object.getClass()))
				list.add((Shark) object);
		return list;
	}
	
	public ArrayList<Slime> listSlime(){
		ArrayList<Slime> list = new ArrayList<Slime>();
		for(GameObject object: gameObjects)
			if(Slime.class.isAssignableFrom(object.getClass()))
				list.add((Slime) object);
		return list;
	}

	public void addGameObject(GameObject object)throws IllegalStateException{
		Tile tileObject = tiles.get(findTileForPixel(object.getPositionX(), object.getPositionY()));
		try{
				if(!isPassableTerrain(tileObject))
					throw new IllegalStateException();
		}
		catch(IllegalStateException ext){
			 if((tileObject.getTileX()+tileObject.getLength()-1 )== object.getPositionY()){
					if(gameObjects.size()>= 100)
						throw ext;
					else if(this.getGameIsStarted())
						throw ext;
					else{ 
						gameObjects.add(object);
						if(object instanceof Slime)
							addSchool(((Slime) object).getSchool());
					}
			 }
			 else throw ext;
		}
		if(gameObjects.size() >= 100)
			return;
		else{ 
			gameObjects.add(object);
			if(object instanceof Slime)
				addSchool(((Slime) object).getSchool());
			}

	}

	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private void addSchool(School blobbySchool)throws IllegalArgumentException{
		if(scholen.contains(blobbySchool))
			return;
		else if(this.scholen.size() <10)
			scholen.add(blobbySchool);
		else throw new IllegalArgumentException();
	}
	
	private ArrayList<School> scholen = new ArrayList<School>();

	public Mazub getMazub(){
		return new Mazub(mazub.getPositionX(), mazub.getPositionY(), mazub.getSprites());
	}
	
	public void setMazub(Mazub mazub){
		this.mazub = mazub;
	}
	
	private Mazub mazub;
	
	public void advanceTime(double deltaT){
		mazub.advanceTime(mazub.getHorizontalVelocity(), mazub.getVerticalVelocity(), deltaT);
		for(GameObject object : gameObjects)
			object.advanceTime(object.getHorizontalVelocity(), object.getVerticalVelocity(), deltaT);
	}
	
	public int collisionTile(){
		return 0; //TODO
	}
	
	public boolean collisionGameObject(){
		return false; //TODO
	}
	
	public void startGame(){
		setGameIsStarted(true);
		//TODO: moeten we hier dan nog iets doen? NEE
	}
	
	private boolean getGameIsStarted() {
		return gameIsStarted;
	}


	public void setGameIsStarted(boolean gameIsStarted) {
		this.gameIsStarted = gameIsStarted;
	}

	private boolean gameIsStarted;
	
	public boolean isGameOver(){
		if((mazub.getPositionX() == getTargetTileX() && 
				mazub.getPositionY() == getTargetTileY())||(mazub.isTerminated() == true)){
			setGameIsStarted(false);
			return true;
		}
		return false;
	}
	
	public boolean didPlayerWin(){
		if(mazub.getPositionX() == getTargetTileX() && mazub.getPositionY() == getTargetTileY() && isGameOver() == true)
			return true;
		return false;
	}

}
