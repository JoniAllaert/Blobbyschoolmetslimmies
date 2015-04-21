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
	}
	
	
	@Override
	public void startMoveLeft() {
		this.setMove(true);
		this.setTimeStartLeft(this.getTime());		
	}

	@Override
	public void startMoveRight() {
		this.setMove(true);
		this.setTimeStartRight(this.getTime());
	}

	@Override
	public void endMoveLeft() {
		this.setHorizontalVelocity(0);
		this.setMove(false);
	}

	@Override
	public void endMoveRight() {
		this.setHorizontalVelocity(0);
		this.setMove(false);
	}

	
	@Override
	protected void setHorizontalVelocity(double horizontalVelocity){
		if(!isValidHorizontalVelocity(horizontalVelocity) && horizontalVelocity < 0)
			this.horizontalVelocity = -this.getMaximalHorizontalVelocity();
		else if(!isValidHorizontalVelocity(horizontalVelocity) && horizontalVelocity > 0)
			this.horizontalVelocity = this.getMaximalHorizontalVelocity();
		this.horizontalVelocity = horizontalVelocity;
	}

	@Override
	public boolean isValidHorizontalVelocity(double velocity) {
		if(Math.abs(velocity) <= this.getMaximalHorizontalVelocity())
			return true;
		return false;
	}
	
	/**
	 * Gives the initial maximal horizontal velocity of Mazub.
	 * @return 	The initial value for the horizontal velocity of Mazub 
	 * 		   	is never below the initial horizontal velocity.
	 *       	| result >= this.getInitialHorizontalVelocity()
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
	 * Return the value for the horizontal acceleration of Mazub.
	 * 
	 * @return 	the value for the horizontal acceleration of Mazub 
	 * 		   	is never below zero.
	 *       	| result >= 0
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
	 * Mazub starts jumping.
	 * @effect	The variable that registers if Mazub is jumping is set to true.
	 * 			| setJump(true)
	 * @effect 	Mazub starts moving upwards with a specific vertical velocity.
	 * 		 	| setVerticalVelocity(this.getInitialVerticalVelocity())
	 * @throws	IllegalStateException
	 * 			Mazub can not jump while jumping, nor jump while ducking.
	 * 			| this.getJump()||this.getDuck()
	 */
	public void startJump()throws IllegalStateException{
		if(!this.getMove())
			throw new IllegalStateException();
		setVerticalVelocity(this.getInitialVerticalVelocity());
		setJump(true);
	}

	/**
	 * Mazub stops jumping.
	 * This methode prevents Mazub to stop jumping, when he is falling.
	 * @effect 	Mazub's vertical velocity equals 0 m/s.
	 * 			| setVerticalVelocity(0)
	 * @throws 	IllegalStateException
	 * 			Vertical velocity can not be smaller then zero when Mazub ends jumping.
	 * 			| this.getVerticalVelocity() < 0
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
	 * Sets the boolean that registers if Mazub is jumping.
	 * @param flag
	 * 			The new state.
	 * @post	The new state variable that registers if Mazub is jumping.
	 * 			| new this.getJump() = flag
	 */
	private void setJump(boolean flag){
		this.jump = flag;
	}

	/**
	 * Variable registering if Mazub is jumping (true) or is not jumping (false).
	 */
	private boolean jump;
	

	/**
	 * Gives the initial vertical velocity of Mazub.
	 * @return The initial value for the vertical velocity of Mazub 
	 * 		   is equal to 8 m/s.
	 *         | result = 8
	 */
	@Basic @Immutable
	public static double getInitialVerticalVelocity(){
		return INITIAL_VERTICAL_VELOCITY;
	}

	/**
	 * Variable registering the initial vertical velocity.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY = 2;
	
	@Override
	protected void setVerticalVelocity(double verticalVelocity) {
		this.verticalVelocity = verticalVelocity;
	}
	

	public double getVerticalAccaleration() {
		return verticalAccaleration;
	}


	public void setVerticalAccaleration(double verticalAccaleration) {
		if(!isValidVerticalAccelaration(verticalAccaleration) && verticalAccaleration>0)
			this.verticalAccaleration = this.getMaximalVerticalAccelaration();
		else if(!isValidVerticalAccelaration(verticalAccaleration) && verticalAccaleration<0)
				this.verticalAccaleration = -this.getMaximalVerticalAccelaration();
		else this.verticalAccaleration = verticalAccaleration;
	}
	
	
	
	private boolean isValidVerticalAccelaration(double accalaration){
		if(Math.abs(accalaration)<= this.getMaximalVerticalAccelaration())
			return true;
		return false;
	}
	
	private double verticalAccaleration;
	
	/**
	 * Gives the initial maximal horizontal velocity of Mazub.
	 * @return 	The initial value for the horizontal velocity of Mazub 
	 * 		   	is never below the initial horizontal velocity.
	 *       	| result >= this.getInitialHorizontalVelocity()
	 */
	@Basic
	public static  double getMaximalVerticalAccelaration(){
		return MAXIMAL_VERTICAL_ACCELARATION;
	}
	
	/**
	 * Variable that registers the initial maximal horizontal velocity.
	 */
	private static final double MAXIMAL_VERTICAL_ACCELARATION = 0.2;

	
	

	@Override
	public void advanceTime(double horizontalVelocity, double verticalVelocity, double deltaT){
		
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
	
	private double distanceTraveledVertical(double velocity, double deltaT){
		return (velocity * deltaT + 0.5 * (this.getVerticalAccaleration())*deltaT*deltaT)*100;
	}
	
	private double advancedVerticalVelocity(double velocity, double deltaT){
		return velocity + (this.getVerticalAccaleration())*deltaT;
	}

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
	public void doCollisionTile(Tile tile) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}

