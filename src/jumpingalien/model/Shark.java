package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.util.Sprite;

/**
 * A class for dealing with game objects called sharks.
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
public class Shark extends GameObject{



	/**
	 * Initializes a shark with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the plant.
	 */
	public Shark(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		super(pixelLeftX, pixelBottomY, sprites, 100);
		startMoveLeft();
	}
	
	/**
	 * The shark starts moving horizontally to the left.
	 * @pre		shark is not moving.		
	 * @effect 	The shark starts moving with an initial horizontal velocity to the left (negative).
	 * 		 	| 
	 * @effect	The variable that registers if the shark is moving is set to true.
	 * 			| setMove(true)
	 * @effect 	The variable that registers the last time the shark started moving to the left is set 
	 * 			to the current game time.
	 * 			| setTimeStartLeft(this.getTime())
	 */
	@Override
	public void startMoveLeft() {
		this.setMove(true);
		this.setTimeStartLeft(this.getTime());
		this.setMovementPeriods(this.getMovementPeriods() +1);
	}

	

	/**
	 * The shark starts moving horizontally to the right.
	 * @pre		The shark is not moving.
	 * @effect	The shark starts moving with an initial horizontal velocity to the right (positive).
	 * 		 	| 
	 * @effect	The variable that registers if the shark is moving is set to true.
	 * 			| setMove(true)
	 * @effect 	The variable that registers the last time the shark started moving to the right is set 
	 *			to the current game time.
	 * 			| setTimeStartRight(this.getTime())
	 */
	@Override
	public void startMoveRight() {
		this.setMove(true);
		this.setTimeStartRight(this.getTime());
		this.setMovementPeriods(this.getMovementPeriods() +1);
	}

	/**
	 * The shark stops moving to the left.
	 * @pre		The shark is moving horizontally to the left.
	 * @effect 	The shark's horizontal velocity equals 0 m/s.
	 * 			| 
	 * @effect	The variable that registers if the shark is moving is set to false.	 
	 * 			| setMove(false)
	 * @effect 	The variable that registers the last time the shark moved to the left is set to the current game time.
	 * 			| setTimeLastLeft(this.getTime())
	 */
	@Override
	public void endMoveLeft() {
		this.setHorizontalVelocity(0);
		this.setMove(false);
	}

	/**
	 * The shark stops moving to the right.
	 * @pre		The shark is moving horizontally to the right.
	 * @effect 	The shark's horizontal velocity equals 0 m/s.
	 * 			| 
	 * @effect	The variable that registers if the shark is moving is set to false.
	 * 			| setMove(false)
	 * @effect 	The variable that registers the last time the shark moved to the right is set to the current game time.
	 * 			| setTimeLastRight(this.getTime())
	 */	
	@Override
	public void endMoveRight() {
		this.setHorizontalVelocity(0);
		this.setMove(false);
	}
	
	public int getMovementPeriods() {
		return movementPeriods;
	}

	public void setMovementPeriods(int movementPeriods) {
		this.movementPeriods = movementPeriods;
	}
	
	private int movementPeriods;

	/**
	 * Set the horizontal velocity of the shark to a given velocity.
	 * @param velocity
	 * 			The new horizontal velocity.
	 * @effect	If the given velocity is not a valid horizontal velocity and is bigger than zero,
	 * 			the new velocity is equal to the maximal velocity.
	 * 			| if(!isValidHorizontalVelocity(velocity)&& velocity > 0)
	 * 			| new.getHorizontalVelocity() = this.getMaximalHorizontalVelocity()
	 * @effect	If the given velocity is not a valid horizontal velocity and is smaller than zero,
	 * 			the new velocity is equal to minus the maximal velocity.
	 * 			| if(!isValidHorizontalVelocity(velocity)&& velocity < 0)
	 * 			| new.getHorizontalVelocity() = - this.getMaximalHorizontalVelocity()
	 * @effect	if the given velocity is a valid velocity, then the new velocity is equal to the given velocity.
	 * 			| if (isValidHorizontalVelocity(velocity))
	 * 			| new.getHorizontalVelocity() = velocity
	 */
	@Override
	protected void setHorizontalVelocity(double horizontalVelocity){
		if(!isValidHorizontalVelocity(horizontalVelocity) && horizontalVelocity < 0)
			this.horizontalVelocity = -this.getMaximalHorizontalVelocity();
		else if(!isValidHorizontalVelocity(horizontalVelocity) && horizontalVelocity > 0)
			this.horizontalVelocity = this.getMaximalHorizontalVelocity();
		this.horizontalVelocity = horizontalVelocity;
	}

	/**
	 * A method that checks if the current horizontal velocity in absolute value is smaller
	 * than the maximal horizontal velocity of the shark.
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the absolute value of the velocity is smaller than the maximal 
	 * 			horizontal velocity.
	 * 			|result == (Math.abs(velocity) <= this.getMaximalHorizontalVelocity())
	 */
	@Override
	public boolean isValidHorizontalVelocity(double velocity) {
		if(Math.abs(velocity) <= this.getMaximalHorizontalVelocity())
			return true;
		return false;
	}
	
	/**
	 * Gives the initial maximal horizontal velocity of the shark.
	 */
	@Basic
	public static  double getMaximalHorizontalVelocity(){
		return MAXIMAL_HORIZONTAL_VELOCITY;
	}
	
	/**
	 * Variable that registers the initial maximal horizontal velocity.
	 */
	private static final double MAXIMAL_HORIZONTAL_VELOCITY = 4;

	/**
	 * Returns the value for the horizontal acceleration of the shark.
	 */
	@Basic @Immutable
	private static double getHorizontalAccelaration(){
		return HORIZONTAL_ACCELERATION;
	}

	/**
	 * Variable registering the horizontal acceleration.
	 */
	private static final double HORIZONTAL_ACCELERATION = 1.5;
	
	/**
	 * The shark starts jumping.
	 * @effect	The variable that registers if the shark is jumping is set to true.
	 * 			| setJump(true)
	 * @effect 	The shark starts moving upwards with a specific vertical velocity.
	 * 		 	| setVerticalVelocity(this.getInitialVerticalVelocity())
	 * @throws	IllegalStateException
	 * 			The shark can not jump if he is not moving.
	 * 			| !this.getMove()
	 */
	public void startJump()throws IllegalStateException{
		if(!this.getMove())
			throw new IllegalStateException();
		else{
			if(this.getMovementPeriods() < 4)
				setVerticalAccaleration(((Math.random())*0.4) -0.2);
			else{
				setVerticalVelocity(this.getInitialVerticalVelocity());
				setJump(true);
				setMovementPeriods(0);
			}
		}
	}

	/**
	 * The shark stops jumping.
	 * @effect 	The shark's vertical velocity equals 0 m/s.
	 * 			| setVerticalVelocity(0)
	 * @effect  The variable registering if the shark is moving is set to false.
	 * 			| this.getMove() == false
	 * @throws ... //TODO:
	 * 			
	 */
	public void endJump()throws IllegalStateException{
		setVerticalVelocity(0);
		setMove(false);
	}
	
	/**
	 * This method gives you the current state of the boolean variable jump.
	 */
	@Basic
	public boolean getJump(){
		return this.jump;
	}

	/**
	 * Sets the boolean that registers if the shark is jumping.
	 * @param flag
	 * 			The new state.
	 * @post	The new state variable that registers if the shark is jumping.
	 * 			| new this.getJump() = flag
	 */
	private void setJump(boolean flag){
		this.jump = flag;
	}

	/**
	 * Variable registering if the shark is jumping (true) or is not jumping (false).
	 */
	private boolean jump;
	

	/**
	 * Gives the initial vertical velocity of the shark.
	 * @return The initial value for the vertical velocity of the shark 
	 * 		   is equal to 2 m/s.
	 *         | result == 2
	 */
	@Basic @Immutable
	public static double getInitialVerticalVelocity(){
		return INITIAL_VERTICAL_VELOCITY;
	}

	/**
	 * Variable registering the initial vertical velocity.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY = 2;
	
	/**
	 * Sets the vertical velocity of the shark to the given velocity.
	 * @param verticalVelocity
	 * 			The new vertical velocity of the shark.
	 */
	@Override
	protected void setVerticalVelocity(double verticalVelocity) {
		this.verticalVelocity = verticalVelocity;
	}
	
	/**
	 * Gives the the current vertical acceleration of the shark.
	 */
	public double getVerticalAccaleration() {
		return verticalAccaleration;
	}

	/**
	 * Sets the vertical acceleration of the shark to the given acceleration.
	 * @param verticalAcceleration
	 * 			The new vertical acceleration of the shark.
	 * @effect	If the given acceleration is not a valid vertical acceleration and is bigger than zero,
	 * 			the new acceleration is equal to the maximal acceleration.
	 * 			| if(!isValidVerticalAccelaration(verticalAccaleration)&& verticalAccaleration > 0)
	 * 			| new.getVerticalAccaleration() = this.getMaximalVerticalAccelaration()
	 * @effect	If the given acceleration is not a valid vertical acceleration and is smaller than zero,
	 * 			the new acceleration is equal to minus the maximal acceleration.
	 * 			| if(!isValidVerticalAccelaration(verticalAccaleration)&& verticalAccaleration < 0)
	 * 			| new.getVerticalAccaleration() = - this.getMaximalVerticalAccelaration()
	 * @effect	if the given acceleration is a valid acceleration, then the new acceleration is equal to the given acceleration.
	 * 			| if (isValidVerticalAccelaration(verticalAccaleration))
	 * 			| new.getVerticalAccaleration() = verticalAccaleration
	 */
	public void setVerticalAccaleration(double verticalAccaleration) {
		if(!isValidVerticalAccelaration(verticalAccaleration) && verticalAccaleration>0)
			this.verticalAccaleration = this.getMaximalVerticalAccelaration();
		else if(!isValidVerticalAccelaration(verticalAccaleration) && verticalAccaleration<0)
				this.verticalAccaleration = -this.getMaximalVerticalAccelaration();
		else this.verticalAccaleration = verticalAccaleration;
	}
	
	/**
	 * A method that checks if the current vertical acceleration in absolute value is smaller
	 * than the maximal vertical acceleration of the shark.
	 * @param 	acceleration)
	 * 			The acceleration) to check.
	 * @return	True if and only if the absolute value of the acceleration) is smaller than the maximal 
	 * 			vertical acceleration).
	 * 			|result == (Math.abs(acceleration)) <= this.getMaximalVerticalAccelaration())
	 */
	private boolean isValidVerticalAccelaration(double acceleration){
		if(Math.abs(acceleration)<= this.getMaximalVerticalAccelaration())
			return true;
		return false;
	}
	
	/**
	 * Variable registering the vertical acceleration.
	 */
	private double verticalAccaleration;
	
	/**
	 * Gives the initial maximal vertical acceleration of the shark.
	 */
	@Basic
	public static  double getMaximalVerticalAccelaration(){
		return MAXIMAL_VERTICAL_ACCELARATION;
	}
	
	/**
	 * Variable that registers the maximal vertical acceleration.
	 */
	private static final double MAXIMAL_VERTICAL_ACCELARATION = 0.2;

	//TODO: documentatie na implementatie.
	@Override
	public void advanceTime(double horizontalVelocity, double verticalVelocity,
			double deltaT)throws IllegalArgumentException{
			if (! isValidTime(deltaT))
				throw new IllegalArgumentException();
			this.addTime(deltaT);
			this.getCurrentSprite();
			if(this.getMove() == true){
				this.setPositionX((int) (this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)));
				this.setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity, deltaT));
				this.setPositionY((int) (this.getPositionY() + distanceTraveledVertical(verticalVelocity, deltaT)));
				this.setVerticalVelocity(advancedVerticalVelocity(verticalVelocity, deltaT));
				if((this.getTimeLastLeft() + 1 <= this.getTime())&&(this.getTimeLastLeft() + 4 >= this.getTime())){
					endMoveLeft();
					if(this.getJump() == true){
						endJump();
					 	startMoveRight();
					}
					else startMoveRight();
				}
				else if((this.getTimeLastRight() + 1 <= this.getTime())&&(this.getTimeLastRight() + 4 >= this.getTime())){
					endMoveRight();
					if(this.getJump() == true){
						endJump();
					 	startMoveLeft();
					}
					else startMoveLeft();
				}
				else return;
				if(Math.random() < 0.5)
					startJump();
			}
	}
	
	@Override
	protected double distanceTraveledHorizontal(double velocity, double deltaT) {
		if (Math.abs(velocity) == this.getMaximalHorizontalVelocity())
			return (velocity *deltaT)*100;
		if(velocity>0)
			return (velocity * deltaT + 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100;
		return (velocity * deltaT - 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100;
	}

	@Override
	protected double advancedHorizontalVelocity(double velocity, double deltaT) {
		if(velocity > 0)
			return velocity + this.getHorizontalAccelaration()*deltaT;
		return velocity - this.getHorizontalAccelaration()*deltaT;
	}
	
	/**
	 * This method calculates the distance traveled vertically based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 			The current vertical velocity of the shark.
	 * @param deltaT
	 * 			The time interval in seconds.
	 * @return  The result is smaller than the velocity times deltaT times 100 
	 * 			plus 0.5 times the vertical acceleration times deltaT squared times 100. We multiply by 100
	 * 			because position is in centimeters, but velocity is in meter per second.
	 * 			| result <= (velocity * deltaT + 0.5 * this.getVerticalAcceleration()*deltaT*deltaT)*100
	 */
	private double distanceTraveledVertical(double velocity, double deltaT){
		return (velocity * deltaT + 0.5 * (this.getVerticalAccaleration())*deltaT*deltaT)*100;
	}
	
	/**
	 * This method returns the new vertical velocity after a certain time duration.
	 * @param velocity
	 * 			The current vertical velocity of the shark (the velocity before this method is invoked) in m/s.
	 * @param deltaT
	 * 			The time duration in seconds
	 * @return  The result is smaller than the velocity plus
	 * 			the vertical acceleration times deltaT.
	 * 			| result <= velocity + (this.getVerticalAccaleration())*deltaT
	 */
	private double advancedVerticalVelocity(double velocity, double deltaT){
		return velocity + (this.getVerticalAccaleration())*deltaT;
	}

	/**
	 * Set the number of hitpoints of the shark to the given number of hitpoints.
	 * @param hitpoints
	 * 			The new number of hitpoints.
	 * @post If the new number of hitpoints is bigger than one hundred, the number of hitpoints of the shark will be set to one hundred.
	 * 		 |if (hitPoints > 100)
	 * 		 |(new) getHitpoints() == 100
	 * @post If the new number of hitpoints is smaller than zero, the number of hitpoints of the shark will be set to zero.
	 * 		 |if (hitPoints < 0)
	 * 		 |(new) getHitpoints() == 0
	 * @post The number of hitpoints of the shark will equal the given number of hitpoints if this number is between zero and one hundred.
	 * 		 |if(hitpoints >= 0 && hitpoints <= 100)
	 * 		 |(new) getHitpoints() == hitpoints
	 */
	@Override
	protected void setHitPoints(int hitPoints) {
		if(hitPoints > 100)
			this.hitPoints = 100;
		else if(hitPoints <0)
			this.hitPoints = 0;
		else
			this.hitPoints = hitPoints;		
	}


	@Override
	public void doCollision(GameObject object) {
		if(object instanceof Slime){
			this.setHitPoints(this.getHitPoints() - 50);		
		}
	}


	@Override
	public void doCollisionTile(Tile tile, int i, int j) {
		if(tile.getGeologicalFeature() == 1){
			setInAir(false);
			setInMagma(false);
			if(i == 0){
				this.setHorizontalVelocity(0);
				this.setMove(false);
				this.setTimeLastLeft(this.getTime());
			}
			else if(i==1){
				this.setHorizontalVelocity(0);
				this.setMove(false);
				this.setTimeLastRight(this.getTime());
			}
			else if(i==3){
				this.setVerticalVelocity(0);
			}
			else {
				this.setVerticalVelocity(0);
				this.setJump(false);
			}
		}
		else if(tile.getGeologicalFeature() == 0 ){
			setInMagma(false);
			if(this.isInAir() == true && (this.getTime() - this.getTimeStartAir() ) > 0.2 ){
				setHitPoints(this.getHitPoints() - (int) ((this.getTime() - this.getTimeStartAir() )*30)); //TODO: nu gaan er te veel af waarschijnlijk.
				setTimeStartAir(this.getTime());
			}
			else if(this.isInAir() ==false){
				this.setInAir(true);
				this.setTimeStartAir(this.getTime());
			}
		}
		else if(tile.getGeologicalFeature() == 3){
			setInAir(false);
			if(this.isInMagma() == true && (this.getTime() - this.getTimeStartMagma() ) > 0.2){
				setHitPoints(this.getHitPoints() - (int) ((this.getTime() - this.getTimeStartMagma() )*250)); //TODO: nu gaan er te veel af waarschijnlijk.
			}
			else if(this.isInMagma() == false){
				this.setInMagma(true);
				this.setTimeStartMagma(this.getTime());
			}			
		}
		else{
			setInAir(false);
			setInMagma(false);
		}
		
	}
	
	private boolean isInAir;
	public boolean isInAir() {
		return isInAir;
	}

	public void setInAir(boolean isInAir) {
		this.isInAir = isInAir;
	}

	public double getTimeStartAir() {
		return timeStartAir;
	}

	public void setTimeStartAir(double timeStartAir) {
		this.timeStartAir = timeStartAir;
	}

	public boolean isInMagma() {
		return isInMagma;
	}

	public void setInMagma(boolean isInMagma) {
		this.isInMagma = isInMagma;
	}

	public double getTimeStartMagma() {
		return timeStartMagma;
	}

	public void setTimeStartMagma(double timeStartMagma) {
		this.timeStartMagma = timeStartMagma;
	}

	private double timeStartAir;
	private boolean isInMagma;
	private double timeStartMagma;
	

	
}

