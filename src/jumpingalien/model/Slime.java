package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.util.Sprite;
/**
 * A class for dealing with game objects called slimes.
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
//TODO: vallen met gravitatiekracht? 

public class Slime extends GameObject{
	/**
	 * Initializes a plant with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the plant.
	 */
	public Slime(int pixelLeftX, int pixelBottomY, Sprite[] sprites, School school) {
		super(pixelLeftX, pixelBottomY, sprites, 100);
		setSchool(school);
		school.addSlime(this);
	}

	@Override
	public void startMoveLeft() {
		this.setMove(true);
		this.setTimeLastLeft(this.getTime());	
	}

	@Override
	public void startMoveRight() {
		this.setMove(true);
		this.setTimeLastRight(this.getTime());		
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
	protected void setHorizontalVelocity(double horizontalVelocity) throws IllegalArgumentException {
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
	public static double getHorizontalAccelaration(){
		return HORIZONTAL_ACCELERATION;
	}

	/**
	 * Variable registering the horizontal acceleration.
	 */
	private static final double HORIZONTAL_ACCELERATION = 0.7;

	@Override
	protected void setVerticalVelocity(double velocity) {
		verticalVelocity = 0;
	
	}
	
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
		}
		if((this.getTimeLastLeft() + 2 <= this.getTime())&&(this.getTimeLastLeft() + 6 >= this.getTime())){
			endMoveLeft();
			startMoveRight();
		}
		else if((this.getTimeLastRight() + 2 <= this.getTime())&&(this.getTimeLastRight() + 6 >= this.getTime())){
			endMoveRight();
			startMoveLeft();
		}
		else return;
		
		//TODO: zie zelfde als Plant.
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

	@Override
	protected void setHitPoints(int hitPoints) {
		if(hitPoints >100)
			this.hitPoints = 100;
		else if(hitPoints <0)
			this.hitPoints =0;
		else this.hitPoints =hitPoints;
		
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	private School school;
}
