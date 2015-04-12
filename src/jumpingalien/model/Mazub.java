package jumpingalien.model;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.model.World;
/**
 * A class for dealing with aliens called Mazub.
 * @invar	 Mazub's bottom left pixel must always stay on the screen.
 * 			 | (this.getPositionX() <= this.MAX_POSITIONX) && (this.getPositionX() >= this.MIN_POSITIONX)&&
 * 			 | (this.getPositionY() <= this.MAX_POSITIONY) && (this.getPositionY() >= this.MIN_POSITIONY)
 * @invar	 Mazub's horizontal velocity lies between minus the maximum horizontal velocity and the maximum 
 * 			 horizontal velocity.
 * 			 | (this.getHorizontalVelocity() <= this.getMaximumHorizontalVelocity) && 
 * 			 (this.getHorizontalVelocity() >= -this.getMaximumHorizontalVelocity)
 * @invar	 Mazub's vertical velocity is always smaller than its initial vertical velocity.
 * 			 | this.getVerticalVelocity <= this.INITIAL_VERTICAL_VELOCITY
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
public class Mazub extends GameObject{
	
	/**
	 * Initializes an alien with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the alien.
	 * @post  The current sprite is the image of the alien when he is not moving horizontally,
	 * 		  has not moved horizontally in the last second of in-game-time and is not ducking.
	 * 		  | new.getCurrentSprite() = sprites[0]
	 */
	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		super(pixelLeftX,pixelBottomY,sprites,100);
	}

	/**
	 * Mazub starts moving horizontally to the left.
	 * @pre		Mazub is not moving.		
	 * @effect 	Mazub starts moving with an initial horizontal velocity to the left (negative).
	 * 		 	| setHorzontalAcceleration( - this.getInitialHorizontalVelocity())
	 * @effect	The variable that registers if Mazub is moving is set to true.
	 * 			| setMove(true)
	 * @effect 	The variable that registers the last time Mazub started moving to the left is set to the current game time.
	 * 			| setTimeStartLeft(this.getTime())
	 */
	@Override
	public void startMoveLeft(){
		assert (!this.getMove());
		this.setHorizontalVelocity(-this.getInitialHorizontalVelocity());
		this.setMove(true);
		this.setTimeStartLeft(this.getTime());
	}

	

	/**
	 * Mazub starts moving horizontally to the right.
	 * @pre		Mazub is not moving.
	 * @effect	Mazub starts moving with an initial horizontal velocity to the right (positive).
	 * 		 	| setHorzontalAcceleration(this.getInitialHorizontalVelocity())
	 * @effect	The variable that registers if Mazub is moving is set to true.
	 * 			| setMove(true)
	 * @effect 	The variable that registers the last time Mazub started moving to the right is set to the current game time.
	 * 			| setTimeStartRight(this.getTime())
	 */
	@Override
	public void startMoveRight(){
		assert (!this.getMove());
		this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
		this.setMove(true);
		this.setTimeStartRight(this.getTime());
	}

	/**
	 * Mazub stops moving to the left.
	 * @pre		Mazub is moving horizontally to the left.
	 * @effect 	Mazub's horizontal velocity equals 0 m/s.
	 * 			| setHorizontalVelocity(0)
	 * @effect	The variable that registers if Mazub is moving is set to false.
	 * 			| setMove(false)
	 * @effect 	The variable that registers the last time Mazub moved to the left is set to the current game time.
	 * 			| setTimeLastLeft(this.getTime())
	 */
	@Override
	public void endMoveLeft(){
		assert (this.getMove() && (this.getHorizontalVelocity() <= 0));
		this.setHorizontalVelocity(0);
		this.setMove(false);
		this.setTimeLastLeft(this.getTime());
	}

	/**
	 * Mazub stops moving to the right.
	 * @pre		Mazub is moving horizontally to the right.
	 * @effect  Mazub's horizontal velocity equals 0 m/s.
	 * 			| setHorizontalVelocity(0)
	 * @effect	The variable that registers if Mazub is moving is set to false.
	 * 			| setMove(false)
	 * @effect 	The variable that registers the last time Mazub moved to the right is set to the current game time.
	 * 			| setTimeLastRight(this.getTime())
	 */
	@Override
	public void endMoveRight(){
		assert (this.getMove() && (this.getHorizontalVelocity() >= 0 ));
		this.setHorizontalVelocity(0);
		this.setMove(false);
		this.setTimeLastRight(this.getTime());
	}

	/**
	 * Set the horizontal velocity of Mazub to a given velocity.
	 * @param velocity
	 * 			The new horizontal velocity.
	 * @post	If the given velocity is bigger than the maximum horizontal velocity,
	 * 			the new velocity is equal to the maximum velocity.
	 * 			| if(velocity > this.getMaximumHorizontalVelocity())
	 * 			| new.getHorizontalVelocity() = this.getMaximumHorizontalVelocity()
	 * @post	if the given velocity is smaller than minus the maximum horizontal velocity,
	 * 			the new velocity is equal to minus the maximum horizontal velocity.
	 * 			| if(velocity < - this.getMaximumHorizontalVelocity()) 
	 * 			| new.getHorizontalVelocity() = -this.getMaximumHorizontalVelocity()
	 * @post	if the given velocity is between minus the maximum velocity, and the
	 * 			maximum velocity, the new velocity is equal to the given velocity.
	 * 			| if ((velocity < -this.getMaximumHorizontalVelocity())&&
	 * 			| 		(velocity> this.getMaximumHorizontalVelocity()))
	 * 			| new.getHorizontalVelocity() = velocity
	 */
	@Override
	protected void setHorizontalVelocity(double velocity){
		if(velocity > this.getMaximumHorizontalVelocity()) 
			this.horizontalVelocity = this.getMaximumHorizontalVelocity();
		if(velocity < -(this.getMaximumHorizontalVelocity())) 
			this.horizontalVelocity = -(this.getMaximumHorizontalVelocity());
		else
			this.horizontalVelocity = velocity;
	}

	/**
	 * Gives the initial horizontal velocity of Mazub.
	 * @return 	The initial value for the horizontal velocity of Mazub 
	 * 		   	is never below 1 m/s.
	 *       	| result >= 1 m/s
	 */
	@Basic @Immutable
	public double getInitialHorizontalVelocity(){
		return this.INITIAL_HORIZONTAL_VELOCITY;
	}

	/**
	 * This variable registers the initial horizontal velocity.
	 */
	private final double INITIAL_HORIZONTAL_VELOCITY=1;

	/**
	 * Return the maximal value for the horizontal velocity of Mazub.
	 * @return 	The maximal value for the horizontal velocity of Mazub 
	 * 		   	is bigger than or equal to the initial horizontal velocity.
	 *       	| result >= this.getInitialHorizontalVelocity.
	 */
	@Basic 
	public double getMaximumHorizontalVelocity(){
		return maximumHorizontalVelocity;	
	}

	/**
	 * Set the maximum horizontal velocity of Mazub.
	 * @param 	velocity
	 * 			The new maximum horizontal velocity.
	 * @post 	If the given velocity is bigger than the initial horizontal velocity,
	 * 			then the maximum horizontal velocity is set to the given value.
	 * 			| if(velocity > this.getInitialHorizontalVelocity)
	 * 			| 	new this.getMaximumHorizontalVelocity = velocity
	 * @post 	If the given velocity is smaller than the initial horizontal velocity,
	 * 			then the maximum horizontal velocity is set to the initial horizontal velocity.
	 * 	 		| if(velocity <= this.getInitialHorizontalVelocity)
	 * 			| 	new this.getMaximumHorizontalVelocity = this.getInitialHorizontalVelocity
	 */
	private void setMaximumHorizontalVelocity(double velocity){
		if(velocity <= this.getInitialHorizontalVelocity())
			this.maximumHorizontalVelocity = this.getInitialHorizontalVelocity();
		else this.maximumHorizontalVelocity = velocity;
	}

	/**
	 * Variable registering the maximum horizontal velocity.
	 */
	private double maximumHorizontalVelocity = getInitialMaximalHorizontalVelocity();
	
	/**
	 * Gives the initial maximal horizontal velocity of Mazub.
	 * @return 	The initial value for the horizontal velocity of Mazub 
	 * 		   	is never below the initial horizontal velocity.
	 *       	| result >= this.getInitialHorizontalVelocity()
	 */
	@Basic
	public static  double getInitialMaximalHorizontalVelocity(){
		return INITIAL_MAXIMAL_HORIZONTAL_VELOCITY;
	}
	//TODO: hier heb ik static van gemaakt, van zowel de vriable als de methode om hem te getten.
	
	/**
	 * Variable that registers the initial maximal horizontal velocity.
	 */
	private static final double INITIAL_MAXIMAL_HORIZONTAL_VELOCITY = 3;
	

	/**
	 * A method that checks if the current horizontal velocity is smaller than the maximum
	 * horizontal velocity of Mazub.
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the absolute value of the velocity is smaller than the 
	 * 			maximum horizontal velocity.
	 * 			| result == (Math.abs(velocity) <= this.getMaximumHorizontalVelocity())
	 */
	@Override
	public boolean isValidHorizontalVelocity(double velocity){
		return(Math.abs(velocity) <= this.getMaximumHorizontalVelocity());
	}


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
	private static final double HORIZONTAL_ACCELERATION = 0.9;

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
		if(this.getJump() || this.getDuck())
			throw new IllegalStateException();
		this.setJump(true);
		setVerticalVelocity(this.getInitialVerticalVelocity());
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
		if(this.getVerticalVelocity() < 0)
			throw new IllegalStateException();
		setVerticalVelocity(0);
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
	 * Set the Vertical velocity of Mazub to a given velocity.
	 * @param velocity
	 * 			The new vertical velocity.
	 * @post	The new velocity is equal to the given velocity.
	 * 			| new.getVerticalVelocity() = velocity
	 */
	@Override
	protected void setVerticalVelocity(double velocity){
		this.verticalVelocity = velocity;
	}

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
	//TODO: hier heb ik ook static van gemaakt.

	/**
	 * Variable registering the initial vertical velocity.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY = 8;

	/**
	 * Gives the vertical acceleration of Mazub.
	 * @return The value for the vertical acceleration of Mazub is equal to -10 m/s^2.
	 *         | result = -10
	 */
	@Basic @Immutable
	public static double getVerticalAcceleration(){
		return VERTICAL_ACCELERATION;
	}
	//TODO: hier heb ik ook static van gemaakt.

	/**
	 * Variable registering the vertical acceleration.
	 */
	public static final double VERTICAL_ACCELERATION = -10;

	/**
	 * Mazub starts ducking.
	 * @effect 	Mazub moves at a maximum velocity of 1m/s.
	 * 			|setMaximumHorizontalVelocity(1)
	 * @effect	The variable that registers if Mazub is ducking is set to true.
	 * 			|setDuck(true)
	 * @throws	IllegalStateException
	 * 			Mazub can not start ducking when he's jumping.
	 * 			|this.getJump() == true
	 */
	public void startDuck()throws IllegalStateException{
		if(this.getJump() == true)
			throw new IllegalStateException();
		this.setMaximumHorizontalVelocity(1);
		this.setDuck(true);
	}

	/**
	 * Mazubs stops ducking.
	 * @effect 	The maximum horizontal velocity of Mazub is set to the initial maximum horizontal velocity.
	 * 			|setMaximumHorizontalVelocity(getInitialMaximalHorizontalVelocity())
	 * @effect	The variable that registers if Mazub is ducking is set to false.
	 * 			|setDuck(false)
	 * @throws 	IllegalStateException
	 * 			Mazub can't stop ducking when he's not ducking.
	 * 			| this.getDuck() == false
	 */
	public void endDuck() throws IllegalStateException{
		if(this.getDuck() == false)
			throw new IllegalStateException();
		this.setMaximumHorizontalVelocity(getInitialMaximalHorizontalVelocity());
		this.setDuck(false);
	}

	/**
	 * This method gives you the current state of the boolean variable duck.
	 */
	@Basic
	public boolean getDuck(){
		return this.duck;
	}

	/**
	 * Sets the boolean that registers if Mazub is ducking.
	 * @param flag
	 * 			The new state.
	 * @post	The new state variable that registers if Mazub is ducking.
	 * 			| new this.getDuck() = flag
	 */
	private void setDuck(boolean flag){
		this.duck = flag;
	}

	/**
	 * Variable registering if Mazub is ducking (true) or is not ducking (false).
	 */
	private boolean duck;

	/**
	 * This method returns the current sprite of Mazub.
	 * @pre	 Mazub can not jump and duck at the same time. 
	 * @post if Mazub is not moving horizontally, has not moved horizontally within the last
	 * 		 second of in-game-time and is not ducking, then images_0 is displayed.
	 * @post if Mazub is not moving horizontally, has not moved horizontally within the last
	 * 		 second of in-game-time and is ducking, then images_1 is displayed.
	 * @post if Mazub is not moving horizontally but its last horizontal movement was to the right (within 1s) and is not ducking, 
	 * 		 then images_2 is displayed.
	 * @post if Mazub is not moving horizontally but its last horizontal movement was to the left (within 1s) and is not ducking, 
	 * 		 then images_3 is displayed.
	 * @post if Mazub is moving to the right and is jumping and not ducking, 
	 * 		 then images_4 is displayed.
	 * @post if Mazub is moving to the left and is jumping and not ducking, 
	 * 		 then images_5 is displayed.
	 * @post if Mazub is moving to the right or was moving to the right (within 1s) and is ducking, 
	 * 		 then images_6 is displayed.
	 * @post if Mazub is moving to the left or was moving to the left (within 1s) and is ducking, 
	 * 		 then images_7 is displayed.
	 * @post if Mazub is moving to the right or was moving to the right (within 1s) and is not ducking nor jumping, 
	 * 		 then images_8 until 8+ M are displayed.
	 * @post if Mazub is moving to the left or was moving to the left (within 1s)and is not ducking nor jumping, 
	 * 		 then images_9+M until 9+2*M are displayed.
	 * @return
	 */
	@Override
	public Sprite getCurrentSprite(){
		assert (this.getJump()==false)||(this.getDuck()==false);
		if((this.getMove() == false)&&(this.getTime() > this.getTimeLastLeft()+1)&&
				(this.getTime() <= this.getTimeLastRight()+1)&&(this.getDuck() == false))
			return this.sprites[2];
		else if((this.getMove() == false)&&(this.getTime() <= this.getTimeLastLeft()+1)&&
				(this.getTime() > this.getTimeLastRight()+1)&&(this.getDuck() == false))
			return this.sprites[3];
		else if((this.getMove() == true)&&(this.getHorizontalVelocity() > 0)&&
				(this.getDuck() == false)&&(this.getJump() == true))
			return this.sprites[4];
		else if((this.getMove() == true)&&(this.getHorizontalVelocity() < 0)&&
				(this.getDuck() == false)&&(this.getJump() == true))
			return this.sprites[5];
		else if((((this.getMove() == true)&&(this.getHorizontalVelocity() > 0))||
				((this.getMove() == false)&&(this.getTime() <= this.getTimeLastRight()+1)))&&
				(this.getDuck() == true))
			return this.sprites[6];
		else if((((this.getMove() == true)&&(this.getHorizontalVelocity() < 0))||
				((this.getMove() == false)&&(this.getTime() <= this.getTimeLastLeft()+1)))&&
				(this.getDuck() == true))
			return this.sprites[7];
		else if((this.getMove() == true)&&(this.getHorizontalVelocity() > 0)&&
				(this.getDuck() == false)&&(this.getJump() == false))
			return this.sprites[8 + (int)(((this.getTime()-this.getTimeStartRight())/0.075)%(this.getM()+1))];
		else if((this.getMove() == true)&&(this.getHorizontalVelocity() < 0)&&
				(this.getDuck() == false)&&(this.getJump() == false))
			return this.sprites[9 + this.getM() + 
			                    (int)(((this.getTime()-this.getTimeStartLeft())/0.075)%(this.getM()+1))];
		else if((this.getMove() == false)&&(this.getDuck() == true)&&(this.getJump() == false))
			return this.sprites[1];
		return this.sprites[0];
	}

	/**
	 * This method gives you the number of images to display a walking Mazub.
	 */
	public int getM(){
		return this.M;
	}

	/**
	 * Variable that determines the number of images to display a walking Mazub.
	 */
	private final int M = 10;

	
	/**
	 * This method updates the position and velocity of Mazub in both directions.
	 * @param horizontalVelocity
	 * 					The current horizontal velocity of Mazub.
	 * @param verticalVelocity
	 * 					The current vertical velocity of Mazub.
	 * @param deltaT
	 * 					The time duration in seconds.
	 * @effect	The variable time of the class Mazub gets updated.
	 * 			| this.addTime(deltaT)
	 * @effect	The current sprite of Mazub gets updated.
	 * 			|this.getCurrentSprite()
	 * @effect 	if Mazub is moving horizontally, the position and velocity get updated in the horizontal direction.
	 * 			|if(this.move == true)
	 * 			|	setPositionX((int)(this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)))
	 * 			|	setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity,deltaT))
	 * @effect 	if Mazub is moving vertically, the position and velocity get updated in the vertical direction.
	 * 			|if(this.jump == true)
	 * 			|	setPositionY((int)(this.getPositionY() + distanceTraveledVertical(verticalVelocity, deltaT)))
	 * 			|	setVerticalVelocity(advancedVerticalVelocity(verticalVelocity, deltaT))
	 * @post	If Mazub is moving horizontally and if Mazub is located at the bottom of the screen, Mazub stops falling.
	 * 			| if((this.getPositionY() == 0)&&(this.jump==true)) this.jump = false
	 * @throws 	IllegalArgumentException
	 * 			If the given time does not equal a valid value.
	 * 			|! isValidTime(deltaT)
	 */
	@Override
	public void advanceTime( double horizontalVelocity, double verticalVelocity, double deltaT) 
			throws IllegalArgumentException{
		if (! isValidTime(deltaT))
			throw new IllegalArgumentException();
		if(!isValidHorizontalVelocity(horizontalVelocity)){
			if(horizontalVelocity< -this.getMaximumHorizontalVelocity())
				horizontalVelocity=-this.getMaximumHorizontalVelocity();
			else horizontalVelocity = this.getMaximumHorizontalVelocity();
		}
		this.addTime(deltaT);
		this.getCurrentSprite();
		if(this.getMove() == true){
			setPositionX((int)(this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)));
			setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity,deltaT));
		}
		if(this.getJump() == true){
			setPositionY((int)(this.getPositionY() + distanceTraveledVertical(verticalVelocity, deltaT)));
			setVerticalVelocity(advancedVerticalVelocity(verticalVelocity, deltaT));
			if(this.getPositionY() == 0)
				this.setJump(false);
		}

	}

	/**
	 * This method calculates the distance travelled vertically based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 			The current vertical velocity of Mazub
	 * @param deltaT
	 * 			The time interval in seconds.
	 * @return  if the absolute value of the given velocity equals the maximum horizontal velocity,
	 * 			then the result is smaller than the velocity times deltaT times 100. We multiply by 100
	 * 			because position is in centimeters, but velocity is in meter per second.
	 * 			| if(Math.abs(velocity) == this.getMaximumHorizontalVelocity())
	 * 			| result <= (velocity *deltaT)*100
	 * @return  if the given velocity is bigger than zero,
	 * 			then the result is smaller than the velocity times deltaT times 100 
	 * 			plus 0.5 times the horizontal accelaration times deltaT squared times 100. We multiply by 100
	 * 			 because position is in centimeters, but velocity is in meter per second.
	 * 			| if(velocity > 0)
	 * 			| result <= (velocity * deltaT + 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100
	 * @return	if the given velocity is smaller than zero,
	 * 			then the result is smaller than the velocity times deltaT times 100 
	 * 			minus 0.5 times the horizontal accelaration times deltaT squared times 100. We multiply by 100
	 * 			because position is in centimeters, but velocity is in meter per second.
	 * 			| if(velocity <= 0)
	 * 			| result <= (velocity * deltaT - 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100
	 */
	@Override
	protected double distanceTraveledHorizontal(double velocity, double deltaT){
		if (Math.abs(velocity) == this.getMaximumHorizontalVelocity())
			return (velocity *deltaT)*100;
		if(velocity>0)
			return (velocity * deltaT + 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100;
		return (velocity * deltaT - 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100;
	}

	/**
	 * This method calculates the distance travelled vertically based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 			The current vertical velocity of Mazub
	 * @param deltaT
	 * 			The time interval in seconds.
	 * @return  The result is smaller than the velocity times deltaT times 100 
	 * 			plus 0.5 times the vertical accelaration times deltaT squared times 100. We multiply by 100
	 * 			because position is in centimeters, but velocity is in meter per second.
	 * 			| result <= (velocity * deltaT + 0.5 * this.VERTICAL_ACCELARATION*deltaT*deltaT)*100
	 */
	private double distanceTraveledVertical(double velocity, double deltaT){
		return (velocity * deltaT + 0.5 * (this.getVerticalAcceleration())*deltaT*deltaT)*100;
	}

	/**
	 * This method returns the new horizontal velocity after a certain time duration.
	 * @param velocity
	 * 			The current horizontal velocity of Mazub (the velocity before this method is invoked) in m/s.
	 * @param deltaT
	 * 			The time duration in seconds
	 * @return  If the given velocity is bigger than zero, then the result is smaller than the velocity plus 
	 * 			the horizontal accelaration times deltaT.
	 * 			| if(velocity > 0)
	 * 			| result <= velocity + this.getHorizontalAccelaration()*deltaT
	 * @return  If the given velocity is smaller than zero, then the result is smaller than the velocity minus
	 * 			the horizontal accelaration times deltaT.
	 * 			| if(velocity <= 0)
	 * 			| result <= velocity - this.getHorizontalAccelaration()*deltaT
	 */
	@Override
	protected double advancedHorizontalVelocity(double velocity, double deltaT){
		if(velocity > 0)
			return velocity + this.getHorizontalAccelaration()*deltaT;
		return velocity - this.getHorizontalAccelaration()*deltaT;
	}

	/**
	 * This method returns the new vertical velocity after a certain time duration.
	 * @param velocity
	 * 			The current vertical velocity of Mazub (the velocity before this method is invoked) in m/s.
	 * @param deltaT
	 * 			The time duration in seconds
	 * @return  The result is smaller than the velocity plus
	 * 			the vertical accelaration times deltaT.
	 * 			| result <= velocity + (this.getVerticalAcceleration())*deltaT
	 */
	private double advancedVerticalVelocity(double velocity, double deltaT){
		return velocity + (this.getVerticalAcceleration())*deltaT;
	}

	@Override
	protected void setHitPoints(int hitPoints) {
		if(hitPoints < 0)
			this.hitPoints=0;
		if(hitPoints > 500)
			this.hitPoints =500;
		else this.hitPoints=hitPoints;
	}

}
