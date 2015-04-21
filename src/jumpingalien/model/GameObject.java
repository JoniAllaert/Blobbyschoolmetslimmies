package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
/**
 * A class for dealing with game objects.
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */

//TODO: kunnen we een final static variable in game object bewaren en in een subklasse initialiseren. Kunt ge ni overerven.
//TODO: hoe associeren we de world met gameobject zonder hem mee te geven in constructor. Waarschijnlijk niet nodig en anders voorbeeld boek.
public abstract class GameObject {
	/**
	 * Initializes a game object with the given position and given sprite and the number of hitpoints.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the game object.
	 * @param hitpoints
	 * 		  The number of hitpoints the game object maximally possesses.
	 */
	protected GameObject(int pixelLeftX, int pixelBottomY, Sprite[] sprites, int hitpoints){
		setPositionX(pixelLeftX);
		setPositionY(pixelBottomY);
		setSprites(sprites);
		setHitPoints(hitpoints);
	}
	
	/**
	 * moeten worden gedocumenteerd, basic dingen die het moet uitvoeren, zie voorbeeld session 8.
	 */

	//TODO: Moet een abstracte methode gedocumenteerd worden, ik zou denken van niet.

	public abstract void startMoveLeft();
	public abstract void startMoveRight();
	public abstract void endMoveLeft();
	public abstract void endMoveRight();
	

	/**
	 * This method gives you the current state of the boolean variable move.
	 */
	public boolean getMove(){
		return this.move;
	}

	/**
	 * Sets the boolean that registers if the game object is moving.
	 * @param flag
	 * 			The new state.
	 * @post	The new state variable that registers if the game object is moving.
	 * 			| new this.getMove() = flag
	 */
	protected void setMove(boolean flag){
		this.move = flag;
	}

	/**
	 * Variable registering if the game object is moving (true) or is not moving (false).
	 */
	private boolean move;
	
	/**
	 * This method returns the current sprite of the game object.
	 * @post if the game object is moving horizontally, then images_1 is displayed.
	 * @post if Mazub is not moving, then images_0 is displayed.
	 */
	public Sprite getCurrentSprite() {
		if(this.getHorizontalVelocity() > 0)
			return this.sprites[1];
		else
			return this.sprites[0];
	}
	
	/**
	 * This method gives you the all the possible images of the game object.
	 */
	public Sprite[] getSprites() {
		return sprites.clone();
	}

	/**
	 * Set the array of possible sprites of the game object.
	 * @param 	sprites
	 * 			The new given sprites of the game object.
	 */
	private void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
	/**
	 * Array of all the images to display the game object
	 */
	protected Sprite[] sprites;
	
	/**
	 * This method gives the width of the current sprite of the game object.
	 */
	@Basic
	public int getWidth(){
		return this.getCurrentSprite().getWidth() - 1;
	}

	/**
	 * This method gives the height of the current sprite of the game object.
	 */
	@Basic
	public int getHeight() {
		return this.getCurrentSprite().getHeight() - 2;
	}

	/**
	 * Returns the x-coordinate of the position of the game object.
	 */
	@Basic
	public int getPositionX(){
		return this.positionX;
	}

	//TODO: documentatie checken als we het juist ge•mplementeerd hebben.
	/**
	 * Set the x-coordinate of the position of game object. 
	 *
	 * @param	position
	 *			The new x-position of the game object.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of the game object, the x-coordinate of the game object is equal to position. 
	 *        	| if ( (position >= this.getMinPositionX()) && (position <= this.getMaxPositionX()) )
	 *        	| 	new this.getPositionX() == position
	 * @post    If the given position is in the range established by the minus of the
	 *          maximum position and the minimum position of the game object, the x-coordinate of the game object 
	 *          is equal to position plus the maximum position + 1. 
	 *        	| if((position >= -this.getMaxPositionX()-1)&&(position < this.getMinPositionX()))
	 * 			| position += this.getMaxPositionX()+1;
	 * @post    If the given position is in the range established by the maximum position
	 *          and two times the maximum position + 1 of the game object, the x-coordinate 
	 *          of the game object is equal to position minus the maximum position + 1.
	 *        	| if((position > this.getMaxPositionX())&&(position <= 2*this.getMaxPositionX()+1))
	 * 			| position -= this.getMaxPositionX()+1;
	 * @throws  IllegalArgumentException
	 * 			The position exceeds 2 times the maximum position plus 1 or is smaller than 
	 * 			minus the maximum position minus 1.
	 * 			| ( position > 2*this.getMaxPositionX() +1) || (position < -this.getMaxPositionX() -1) 			
	 */	
	protected void setPositionX(int position){
		this.positionX = position;
		//TODO: als hij het beeld uitloopt gaat het beestje dood?
	}

//	/**
//	 * A method that checks if the position of X is a valid position.
//	 * @param 	position
//	 * 			The position to check.
//	 * @return	True if and only if the value of the position is larger than the minimum value 
//	 * 			for the x-position and is smaller than the maximum value for the x-position.
//	 * 			| result = ((position>=this.getMinPositionX())&&(position<=this.getMaxPositionX()))
//	 */
//	public boolean isValidPositionX(int position){
//		return ((position>=this.getMinPositionX())&&(position<=this.getMaxPositionX()));
//	}

	/**
	 * Variable that registers the x-coordinate of the game object.
	 */
	private int positionX;

	/**
	 * Returns the y-coordinate of the position of the game object.
	 */
	@Basic
	public int getPositionY(){
		return this.positionY;
	}

	//TODO: documentatie analoog aan X.
	/**
	 * Set the y-coordinate of the position of the game object. 
	 *
	 * @param	position
	 *			The new y-position of the game object.
	 * @post    If the given position is in the range established by the minimum and maximum position of the game object, 
	 *          the y-coordinate of the game object is equal to the given position. 
	 *        	| if ( (position >= this.getMinPositionY()) && (position <= this.getMaxPositionY()) )
	 *        	|   then new.getPositionY() = position
	 * @post    If the given position exceeds the the maximum position of the game object the new 
	 * 			y-coordinate of the game object is equal to the maximum position of the game object.
	 *        	| if (position > this.getMaxPositionY())
	 *        	|  		new.getPositionY() = this.getMaxPositionY()
	 * @post	If the given position is smaller than the minimum position of the game object, the
	 * 			new y-coordinate of the game object is equal to the minimum position of the game object.
	 * 			| if (position< this.getMinPositionY())
	 * 			| 		new.getPositionY() = this.getMinPositionY()
	 */
	protected void setPositionY(int position){
		this.positionY = position;
		//TODO: analoog aan X.
	}

//	/**
//	 * A method that checks if the position of Y is a valid position.
//	 * @param 	position
//	 * 			The position to check.
//	 * @return	True if and only if the value of the position is larger than the minimum value for 
//	 * 			the y-position and is smaller than the maximum value for the y-position.
//	 * 			| result = ((position>=this.getMinPositionY())&&(position<=this.getMaxPositionY()))
//	 */
//	public boolean isValidPositionY(int position){
//		return ((position>=this.getMinPositionY())&&(position<=this.getMaxPositionY()));
//	}

	/**
	 * Variable registering the y-coordinate of the game object.
	 */
	private int positionY;
	
	/**
	 * This method gives you the current horizontal velocity of the game object.
	 */
	public double getHorizontalVelocity(){
		return horizontalVelocity;
	}
	
	protected abstract void setHorizontalVelocity(double velocity);
	
	public abstract boolean isValidHorizontalVelocity(double velocity);

	/**
	 * Variable registering the horizontal velocity of the game object.
	 */
	protected double horizontalVelocity;
	
	/**
	 * This method gives you the current vertical velocity of the game object.
	 */
	public double getVerticalVelocity(){
		return verticalVelocity;
	}
	
	protected abstract void setVerticalVelocity(double velocity);
	
	/**
	 * Variable registering the vertical velocity of the game object.
	 */
	protected double verticalVelocity;
	
//	/**
//	 * This method gives you the minimal x-position of the game object.
//	 */
//	public int getMinPositionX(){
//		return MIN_POSITIONX;
//	}
//
//	/**
//	 * Variable that registers the minimal x-position of the game object.
//	 */
//	private static final int MIN_POSITIONX = 0;
//
//	/**
//	 * This method gives you the maximal x-position of the game object.
//	 */
//	public int getMaxPositionX(){
//		return MAX_POSITIONX;
//	}
//	/**
//	 * Variable that registers the maximal x-position of the game object.
//	 */
//	private static final int MAX_POSITIONX = 1023;
//	
//	/**
//	 * This method gives you the minimal y-position of the game object.
//	 */
//	public int getMinPositionY(){
//		return MIN_POSITIONY;
//	}
//
//	/**
//	 * Variable registering the minimal y-coordinate of the game object.
//	 */
//	private static final int MIN_POSITIONY = 0;
//
//	/**
//	 * This method gives you the maximal y-position of the game object.
//	 */
//	public int getMaxPositionY(){
//		return MAX_POSITIONY;
//	}
//
//	/**
//	 * Variable registering the maximal y-coordinate of the game object.
//	 */
//	private static final int MAX_POSITIONY = 767;
	
	/**
	 * This method gives you the current time duration of the game.
	 */
	@Basic
	public double getTime(){
		return this.time;
	}

	/**
	 * Sets the current time duration of the game.
	 * @param time
	 * 			The new current time.
	 * @post	The new current time is equal to the given time.
	 * 			| new this.getTime = time
	 */
	protected void setTime(double time){
		this.time = time;
	}

	/**
	 * This method adds the given time to the variable this.time
	 * @param time
	 * 			the given time that needs to be added.
	 * @post	The variable that registers the current game time is updated with the given time.
	 * 			| new this.time = this.time + time
	 */
	protected void addTime(double time){
		this.setTime(this.getTime() + time);
	}
	
	/**
	 * A variable that keeps track of the time that has passed.
	 */
	private double time;	
	
	/**
	 * This method gives you the time when the game object last finished moving left.
	 */
	@Basic
	public double getTimeLastLeft(){
		return this.timeLastLeft;
	}

	/**
	 * Sets the time when the game object last stops moving to the left.
	 * @param time
	 * 			The new time when the game object last stopped moving to the left.
	 * @post	The new time when the game object last stopped moving to the left is equal to the given time
	 * 			| new this.getTimeLastLeft = time
	 */
	protected void setTimeLastLeft(double time){
		this.timeLastLeft = time;
	}

	/**
	 * Variable registering when the game object stops moving to the left. 
	 * This variable is initialized to -2 because the game object has not yet moved to the left.
	 */
	private double timeLastLeft=-2;
	
	/**
	 * This method gives you the time when the game object last started moving left.
	 */
	@Basic
	public double getTimeStartLeft(){
		return this.timeStartLeft;
	}

	/**
	 * Sets the time when the game object last started moving to the left.
	 * @param time
	 * 			The new time when the game object last started moving to the left.
	 * @post	The new time when the game object last started moving to the left is equal to the given time.
	 * 			| new this.getTimeStartLeft = time
	 */
	protected void setTimeStartLeft(double time){
		this.timeStartLeft = time;
	}

	/**
	 * Variable registering the time when the game object starts moving to the left.
	 */
	private double timeStartLeft;
	
	/**
	 * This method gives you the time when the game object last finished moving right.
	 */
	@Basic
	public double getTimeLastRight(){
		return this.timeLastRight;
	}

	/**
	 * Sets the time when the game object last stops moving to the right.
	 * @param time
	 * 			The new time when the game object last stopped moving to the right.
	 * @post	The new time when the game object last stopped moving to the right is equal to the given time
	 * 			| new this.getTimeLastRight = time
	 */
	protected void setTimeLastRight(double time){
		this.timeLastRight = time;
	}

	/**
	 * Variable registering when the game object stops moving to the right. 
	 * This variable is initialized to -2 because the game object has not yet moved to the right.
	 */
	private double timeLastRight=-2;
	
	/**
	 * This method gives you the time when the game object last started moving right.
	 */
	@Basic
	public double getTimeStartRight(){
		return this.timeStartRight;
	}

	/**
	 * Sets the time when the game object last started moving to the right.
	 * @param time
	 * 			The new time when game object last started moving to the right.
	 * @post	The new time when the game object last started moving to the right is equal to the given time.
	 * 			| new this.getTimeStartRight = time
	 */
	protected void setTimeStartRight(double time){
		this.timeStartRight = time;
	}

	/**
	 * Variable registering the time when the game object starts moving to the right. 
	 */
	private double timeStartRight;
	
	
	public abstract void advanceTime(double horizontalVelocity, double verticalVelocity, double deltaT);
	
	protected abstract double distanceTraveledHorizontal(double velocity, double deltaT);
	
	protected abstract double advancedHorizontalVelocity(double velocity, double deltaT);


	/**
	 * A method that checks if the time duration is valid.
	 * @param 	deltaT
	 * 			a time duration in seconds.
	 * @return 	True if deltaT is bigger than zero and smaller than 0.2.
	 * 			|result == ((deltaT >= 0) && (deltaT<= 0.2))
	 */
	public boolean isValidTime(double deltaT){
		return((deltaT >=0)&&(deltaT <= 0.2));
	}
	
	/**
	 * Returns the current number of hitpoints that the game object possesses.
	 */
	public int getHitPoints() {
		return hitPoints;
	}
	
	/**
	 * Sets the number of hitpoints to the given integer.
	 * @param hitPoints
	 * 		  The new number of hitpoints the game object possesses.
	 */
	protected abstract void setHitPoints(int hitPoints);
	
	/**
	 * Variable registering the number of hitpoints of the game object.
	 */
	protected int hitPoints;
		
	/**
	 * This method changes the status of the game object to terminated.
	 * @post the variable dead will be equal to true.
	 * 		 | dead == true
	 */
	protected void terminates(){
		this.dead = true;
	}
		
	/**
	 * This method returns true if the game object is dead, otherwise it returns false.
	 */
	public boolean isTerminated(){
		return dead;
	}
	
	/**
	 * Variable registering if a game object is terminated or not.
	 */
	private boolean dead;
	
	public abstract void doCollision(GameObject object);
	public abstract void doCollisionTile(Tile tile, int i,int j);
}
