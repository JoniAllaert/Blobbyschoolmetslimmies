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
	 * @effect	The variable that registers if the plant is moving is set to true.
	 * 			| setMove(true)
	 * @effect 	The variable that registers the last time the plant started moving to the left is set 
	 * 			to the current game time.
	 * 			| setTimeStartLeft(this.getTime())
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
	 * @effect	The variable that registers if the plant is moving is set to true.
	 * 			| setMove(true)
	 * @effect 	The variable that registers the last time the plant started moving to the right is set 
	 *			to the current game time.
	 * 			| setTimeStartRight(this.getTime())
	 */
	@Override
	public void startMoveRight() {
		this.setHorizontalVelocity(-getInitialHorizontalVelocity());
		this.setMove(true);
		this.setTimeLastRight(this.getTime());
		//TODO: moet het hier niet timeStartRight zijn
		
	}
	/**
	 * The plant stops moving to the left.
	 * @pre		The plant is moving horizontally to the left.
	 * @effect 	The plant's horizontal velocity equals 0 m/s.
	 * 			| 
	 * @effect	The variable that registers if the plant is moving is set to false.	 
	 * 			| setMove(false)
	 * @effect 	The variable that registers the last time the plant moved to the left is set to the current game time.
	 * 			| setTimeLastLeft(this.getTime())
	 */
	@Override
	public void endMoveLeft() {
		this.setHorizontalVelocity(0);
		this.setMove(false);
		//TODO: moet hier niet timeLastLeft worden geset.

		
	}

	/**
	 * The plant stops moving to the right.
	 * @pre		The plant is moving horizontally to the right.
	 * @effect 	The plant's horizontal velocity equals 0 m/s.
	 * 			| 
	 * @effect	The variable that registers if the plant is moving is set to false.
	 * 			| setMove(false)
	 * @effect 	The variable that registers the last time the plant moved to the right is set to the current game time.
	 * 			| setTimeLastRight(this.getTime())
	 */	
	@Override
	public void endMoveRight() {
		this.setHorizontalVelocity(0);
		this.setMove(false);

		
	}
	
	/**
	 * Gives the initial horizontal velocity of the plant.
	 */
	private static double getInitialHorizontalVelocity(){
		return INITIAL_HORIZONTAL_VELOCITY;
	}
	
	/**
	 * Variable registering the initial velocity of the plant.
	 */
	private static final double INITIAL_HORIZONTAL_VELOCITY = 0.5;

	/**
	 * This method updates the position and velocity of the plant.
	 * @param horizontalVelocity
	 * 					The current horizontal velocity of the plant.
	 * @param verticalVelocity
	 * 					The current vertical velocity of the plant.
	 * @param deltaT
	 * 					The time duration in seconds.
	 * @effect	The variable time of the class Plant gets updated.
	 * 			| this.addTime(deltaT)
	 * @effect	The current sprite of the plant gets updated.
	 * 			|this.getCurrentSprite()
	 * @effect 	if the plant is moving horizontally, the position and velocity get updated in the horizontal direction.
	 * 			|if(this.move == true)
	 * 			|	setPositionX((int)(this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)))
	 * 			|	setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity,deltaT))
	 * @throws 	IllegalArgumentException
	 * 			If the given time does not equal a valid value.
	 * 			|! isValidTime(deltaT)
	 */
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
		
		//TODO: hoe controlleren we dat het precies om de 0.5 seconden wisselt?
		
	}
	
	/**
	 * This method calculates the distance traveled horizontally based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 			The current horizontal velocity of the plant.
	 * @param deltaT
	 * 			The time interval in seconds.
	 * @return  the result is smaller than the velocity times deltaT times 100. We multiply by 100
	 * 			because position is in centimeters, but velocity is in meter per second.
	 * 			| result <= (velocity *deltaT)*100
	 */
	@Override
	protected double distanceTraveledHorizontal(double velocity, double deltaT) {
		return velocity*deltaT*100;
	}

	/**
	 * This method returns the new horizontal velocity after a certain time duration.
	 * @param velocity
	 * 			The current horizontal velocity of the plant (the velocity before this method is invoked) in m/s.
	 * @param deltaT
	 * 			The time duration in seconds
	 * @return  The velocity is constant for a plant.
	 * 			| result == velocity
	 */
	@Override
	protected double advancedHorizontalVelocity(double velocity, double deltaT) {
		return velocity;
		//TODO: ik heb hier velocity van gemaakt omdat die toch constant blijft voor een plant.
	}

	/**
	 * Set the horizontal velocity of the plant to a given velocity.
	 * @param velocity
	 * 			The new horizontal velocity.
	 * @effect	If the given velocity is not a valid horizontal velocity and is bigger than zero,
	 * 			the new velocity is equal to the initial velocity.
	 * 			| if(!isValidHorizontalVelocity(velocity)&& velocity > 0)
	 * 			| new.getHorizontalVelocity() = this.getInitialHorizontalVelocity()
	 * @effect	If the given velocity is not a valid horizontal velocity and is smaller than zero,
	 * 			the new velocity is equal to minus the initial velocity.
	 * 			| if(!isValidHorizontalVelocity(velocity)&& velocity < 0)
	 * 			| new.getHorizontalVelocity() = - this.getInitialHorizontalVelocity()
	 * @effect	if the given velocity is a valid velocity, then the new velocity is equal to the given velocity.
	 * 			| if (isValidHorizontalVelocity(velocity))
	 * 			| new.getHorizontalVelocity() = velocity
	 */
	@Override
	protected void setHorizontalVelocity(double velocity) throws IllegalArgumentException {
		if(!isValidHorizontalVelocity(velocity)&& velocity > 0)
			horizontalVelocity = getInitialHorizontalVelocity();
		else if(!isValidHorizontalVelocity(velocity)&& velocity < 0)
			horizontalVelocity = -getInitialHorizontalVelocity();			
		else horizontalVelocity =velocity;	
	}	

	/**
	 * A method that checks if the current horizontal velocity is equal in absolute value 
	 * to the initial horizontal velocity of the plant.
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the absolute value of the velocity is equal to the initial 
	 * 			horizontal velocity.
	 * 			|result == (Math.abs(velocity) == this.getInitialHorizontalVelocity())
	 */
	@Override
	public boolean isValidHorizontalVelocity(double velocity) {
		if(Math.abs(velocity) == getInitialHorizontalVelocity())
			return true;
		return false;
	}
	
	/**
	 * Set the vertical velocity of the plant to zero.
	 * @param velocity
	 * 			The new vertical velocity.
	 * @post The vertical velocity of the plant will equal zero.
	 * 		 |(new) getVerticalVelocity() == 0
	 */
	@Override
	protected void setVerticalVelocity(double velocity) {
		verticalVelocity = 0;
	}
	//TODO: wij hebben precies gene getVerticalVelocity()? :p

	/**
	 * Set the number of hitpoints of the plant to the given number of hitpoints.
	 * @param hitpoints
	 * 			The new number of hitpoints.
	 * @post If the new number of hitpoints is bigger than one, the number of hitpoints of the plant will be set to one.
	 * 		 |if (hitPoints > 1)
	 * 		 |(new) getHitpoints() == 1
	 * @post If the new number of hitpoints is smaller than zero, the number of hitpoints of the plant will be set to zero.
	 * 		 |if (hitPoints < 0)
	 * 		 |(new) getHitpoints() == 0
	 * @post The number of hitpoints of the plant will equal the given number of hitpoints if this number is between zero and one.
	 * 		 |if(hitpoints >= 0 && hitpoints <= 1)
	 * 		 |(new) getHitpoints() == hitpoints
	 */
	@Override
	protected void setHitPoints(int hitPoints) {
		if(hitPoints > 1)
			this.hitPoints = 1;
		else if(hitPoints <0)
			this.hitPoints = 0;
		else
			this.hitPoints = hitPoints;
	}

	

		
}

