package jumpingalien.model;

import jumpingalien.util.Sprite;

/**
 * A class for dealing with game objects called plants.
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
public class Plant extends GameObject{

	/**
	 * Initializes a plant with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the plant.
	 */
	public Plant(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		super(pixelLeftX, pixelBottomY, sprites, 1);
	}

	/**
	 * The plant starts moving horizontally to the left.
	 * @pre		Plant is not moving.		
	 * @effect 	Plant starts moving with an initial horizontal velocity to the left (negative).
	 * 		 	| 
//	 * @effect	The variable that registers if the plant is moving is set to true.
//	 * 			| setMove(true)
//	 * @effect 	The variable that registers the last time the plant started moving to the left is set 
//	 * 			to the current game time.
//	 * 			| setTimeStartLeft(this.getTime())
	 */
	@Override
	public void startMoveLeft() {
		this.setHorizontalVelocity(getInitialHorizontalVelocity());
		this.setMove(true);
		this.setTimeLastLeft(this.getTime());		
	}

	/**
	 * The plant starts moving horizontally to the right.
	 * @pre		The plant is not moving.
	 * @effect	The plant starts moving with an initial horizontal velocity to the right (positive).
	 * 		 	| 
//	 * @effect	The variable that registers if the plant is moving is set to true.
//	 * 			| setMove(true)
//	 * @effect 	The variable that registers the last time the plant started moving to the right is set 
//	 *			to the current game time.
//	 * 			| setTimeStartRight(this.getTime())
	 */
	@Override
	public void startMoveRight() {
		this.setHorizontalVelocity(-getInitialHorizontalVelocity());
		this.setMove(true);
		this.setTimeLastRight(this.getTime());
		
	}
	/**
	 * The plant stops moving to the left.
	 * @pre		The plant is moving horizontally to the left.
	 * @effect 	The plant's horizontal velocity equals 0 m/s.
	 * 			| 
//	 * @effect	The variable that registers if the plant is moving is set to false.
//	 * 			| setMove(false)
//	 * @effect 	The variable that registers the last time the plant moved to the left is set to the current game time.
//	 * 			| setTimeLastLeft(this.getTime())
	 */
	@Override
	public void endMoveLeft() {
		this.setHorizontalVelocity(0);
		this.setMove(false);

		
	}

	/**
	 * The plant stops moving to the right.
	 * @pre		The plant is moving horizontally to the right.
	 * @effect 	The plant's horizontal velocity equals 0 m/s.
	 * 			| 
//	 * @effect	The variable that registers if the plant is moving is set to false.
//	 * 			| setMove(false)
//	 * @effect 	The variable that registers the last time the plant moved to the right is set to the current game time.
//	 * 			| setTimeLastRight(this.getTime())
	 */	
	@Override
	public void endMoveRight() {
		this.setHorizontalVelocity(0);
		this.setMove(false);

		
	}
	
	private static double getInitialHorizontalVelocity(){
		return INITIAL_HORIZONTAL_VELOCITY;
	}
	
	/**
	 * Variable registering the initial velocity of the plant.
	 */
	private static final double INITIAL_HORIZONTAL_VELOCITY = 0.5;


	@Override
	public void advanceTime(double horizontalVelocity, double verticalVelocity,
			double deltaT) throws IllegalArgumentException{
		if (! isValidTime(deltaT))
			throw new IllegalArgumentException();
		this.addTime(deltaT);
		this.getCurrentSprite();
		if(this.getMove() == true)
			setPositionX((int) (this.getPositionX() + distanceTraveledHorizontal(this.getHorizontalVelocity(), deltaT)));
		if((this.getTimeLastLeft() + 0.5 >= this.getTime())&&(this.getTimeLastRight() + 0.5 <= this.getTime())){
			endMoveLeft();
			startMoveRight();
		}
		else {
			endMoveRight();
			startMoveLeft();
		}
		
		//TODO: hoe controlleren we dat het precies om de 0.5 seconden wisselt? is waarschijnlijk goed. moet zo snel mogelijk gebeuren na 0.5 seconden.
		
	}
	

	@Override
	protected double distanceTraveledHorizontal(double velocity, double deltaT) {
		return velocity*deltaT*100;
	}

	@Override
	protected double advancedHorizontalVelocity(double velocity, double deltaT) {
		return 0;
	}

	@Override
	protected void setHorizontalVelocity(double velocity) throws IllegalArgumentException {
		if(!isValidHorizontalVelocity(velocity)&& velocity > 0)
			horizontalVelocity = getInitialHorizontalVelocity();
		else if(!isValidHorizontalVelocity(velocity)&& velocity < 0)
			horizontalVelocity = -getInitialHorizontalVelocity();			
		else horizontalVelocity =velocity;	
	}	

	
	@Override
	public boolean isValidHorizontalVelocity(double velocity) {
		if(Math.abs(velocity) == getInitialHorizontalVelocity())
			return true;
		return false;
	}
	

	@Override
	protected void setVerticalVelocity(double velocity) {
		verticalVelocity = 0;
	}

	@Override
	protected void setHitPoints(int hitPoints) {
		if(hitPoints > 1)
			this.hitPoints = 1;
		else if(hitPoints <0)
			this.hitPoints = 0;
		else
			this.hitPoints = hitPoints;
	}

	@Override
	public void doCollision(GameObject object) {
	}

	@Override
	public void doCollisionTile(Tile tile) {
		// TODO Auto-generated method stub
		
	}

	

		
}

