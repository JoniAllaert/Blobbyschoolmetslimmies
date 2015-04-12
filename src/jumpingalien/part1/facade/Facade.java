package jumpingalien.part1.facade;

import jumpingalien.model.Mazub;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

/**
 * Implement this interface to connect your code to the graphical user interface
 * (GUI).
 * 
 * <ul>
 * <li>For separating the code that you wrote from the code that was provided to
 * you, put <b>ALL your code in the <code>src</code> folder.</b> The code that
 * is provided to you stays in the <code>src-provided</code> folder. Only if you
 * modify the provided code, it's advised to move the modified code to the
 * <code>src</code> folder, so that your changes cannot be accidentally
 * overwritten by an updated version of the provided code.</li>
 * 
 * <li>You should at least <b>create the following classes</b> in the package
 * <code>jumpingalien.model</code>:
 * <ul>
 * <li>a class <code>Mazub</code> for representing Mazub the alien</li>
 * </ul>
 * You may, of course, add additional classes as you see fit.
 * 
 * <li>You should <b>create a class <code>Facade</code></b> that implements this
 * interface. This class should be placed <b>in the package
 * <code>jumpingalien.part1.facade</code></b>.</li>
 * 
 * <li>
 * The <b>header of that Facade class</b> should look as follows:<br>
 * <code>class Facade implements IFacade { ... }</code><br>
 * Consult the <a href=
 * "http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">
 * Java tutorial</a> for more information on interfaces, if necessary.</li>
 *
 * <li>Your <code>Facade</code> class should offer a <b>default constructor</b>.
 * </li>
 * 
 * <li><b>Each method</b> defined in the interface <code>IFacade</code> must be
 * implemented by the class <code>Facade</code>. For example, the implementation
 * of <code>getLocation</code> should call one or more methods on the given
 * <code>alien</code> to retrieve its current location.</li>
 * 
 * <li>Methods in this interface are <b>only allowed to throw exceptions of type
 * <code>jumpingalien.util.ModelException</code></b> (this class is provided).
 * No other exception types are allowed. This exception can be thrown only if
 * calling a method of your <code>Mazub</code> class with the given parameters
 * would violate a precondition or if the method of your <code>Mazub</code>
 * class throws an exception (if so, wrap the exception in a
 * <code>ModelException</code>).</li>
 * 
 * <li><b>ModelException should not be used anywhere outside of your Facade
 * implementation.</b></li>
 * 
 * <li>Your Facade implementation should <b>only contain trivial code</b> (for
 * example, calling a method, combining multiple return values into an array,
 * creating @Value instances, catching exceptions and wrapping it in a
 * ModelException). All non-trivial code should be placed in the other classes
 * that you create.</li>
 * 
 * <li>The rules described above and the documentation described below for each
 * method apply only to the class implementing IFacade. <b>Your class for
 * representing aliens should follow the rules described in the assignment.</b></li>
 * 
 * <li><b>Do not modify the signatures</b> of the methods defined in this
 * interface.</li>
 * 
 * </ul>
 *
 */

public class Facade implements IFacade{

	/**
	 * Create an instance of Mazub.
	 * 
	 * @param pixelLeftX
	 *            The x-location of Mazub's bottom left pixel.
	 * @param pixelBottomY
	 *            The y-location of Mazub's bottom left pixel.
	 * @param sprites
	 *            The array of sprite images for Mazub.
	 * 
	 * @return
	 */
	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		// TODO Auto-generated method stub
		Mazub mazub = new Mazub(pixelLeftX, pixelBottomY, sprites);
		return mazub;
	}

	/**
	 * Return the current location of the given alien.
	 * 
	 * @param alien
	 *            The alien of which to get the location.
	 * 
	 * @return an array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given alien's bottom left pixel in the world.
	 */
	@Override
	public int[] getLocation(Mazub alien) {
		// TODO Auto-generated method stub
		int[] array = {alien.getPositionX(), alien.getPositionY()};
		return array;
	}

	/**
	 * Return the current velocity (in m/s) of the given alien.
	 * 
	 * @param alien
	 *            The alien of which to get the velocity.
	 * 
	 * @return an array, consisting of 2 doubles {vx, vy}, that represents the
	 *         horizontal and vertical components of the given alien's current
	 *         velocity, in units of m/s.
	 */
	@Override
	public double[] getVelocity(Mazub alien) {
		// TODO Auto-generated method stub
		double[] array = {alien.getHorizontalVelocity(), alien.getVerticalVelocity()};
		return array;
	}

	/**
	 * Return the current acceleration (in m/s^2) of the given alien.
	 * 
	 * @param alien
	 *            The alien of which to get the acceleration.
	 * 
	 * @return an array, consisting of 2 doubles {ax, ay}, that represents the
	 *         horizontal and vertical components of the given alien's current
	 *         acceleration, in units of m/s^2.
	 */
	@Override
	public double[] getAcceleration(Mazub alien) {
		// TODO Auto-generated method stub
		double[] array = {alien.getHorizontalAccelaration(), alien.getVerticalAcceleration()};
		return array;
	}

	/**
	 * Return the current size of the given alien.
	 * 
	 * @param alien
	 *            The alien of which to get the size.
	 * 
	 * @return An array, consisting of 2 integers {w, h}, that represents the
	 *         current width and height of the given alien, in number of pixels.
	 */
	@Override
	public int[] getSize(Mazub alien) {
		// TODO Auto-generated method stub
		int[] array = {alien.getCurrentSprite().getWidth(), alien.getCurrentSprite().getHeight()};
		return array;
	}

	/**
	 * Return the current sprite image for the given alien.
	 * 
	 * @param alien
	 *            The alien for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given alien, determined by its
	 *         state as defined in the assignment.
	 */
	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		// TODO Auto-generated method stub
		return alien.getCurrentSprite();
	}

	/**
	 * Make the given alien jump.
	 * 
	 * @param alien
	 *            The alien that has to start jumping.
	 */
	@Override
	public void startJump(Mazub alien) {
		// TODO Auto-generated method stub
		try{
			alien.startJump();
		}
		catch(IllegalStateException exc){
			throw new ModelException("You can't carry out this action.");
		}
	}

	/**
	 * Make the given alien move left.
	 * 
	 * @param alien
	 *            The alien that has to start moving left.
	 */
	@Override
	public void endJump(Mazub alien) {
		// TODO Auto-generated method stub
		try{
			alien.endJump();
		}
		catch(IllegalStateException exc){
			throw new ModelException("You can't carry out this action.");		

		}
		
	}

	/**
	 * Make the given alien move left.
	 * 
	 * @param alien
	 *            The alien that has to start moving left.
	 */
	@Override
	public void startMoveLeft(Mazub alien) {
		// TODO Auto-generated method stub
		try{
		alien.startMoveLeft();
		}
		catch(AssertionError exc){
			throw new ModelException("You can't carry out this action.");		
		}
	}

	/**
	 * End the given alien's left move.
	 * 
	 * @param alien
	 *            The alien that has to stop moving left.
	 */
	@Override
	public void endMoveLeft(Mazub alien) {
		// TODO Auto-generated method stub
		try{
			alien.endMoveLeft();
		}
		catch(AssertionError exc){
			throw new ModelException("You can't carry out this action.");		
		}
	}

	/**
	 * Make the given alien move right.
	 * 
	 * @param alien
	 *            The alien that has to start moving right.
	 */
	@Override
	public void startMoveRight(Mazub alien) {
		// TODO Auto-generated method stub
		try{
			alien.startMoveRight();
		}
		catch(AssertionError exc){
			throw new ModelException("You can't carry out this action.");		
		}
		
	}

	/**
	 * End the given alien's right move.
	 * 
	 * @param alien
	 *            The alien that has to stop moving right.
	 */
	@Override
	public void endMoveRight(Mazub alien) {
		// TODO Auto-generated method stub
		try{
			alien.endMoveRight();
		}
		catch(AssertionError exc){
			throw new ModelException("You can't carry out this action.");		
		}
	}

	/**
	 * Make the given alien duck.
	 * 
	 * @param alien
	 *            The alien that has to start ducking.
	 */
	@Override
	public void startDuck(Mazub alien) {
		// TODO Auto-generated method stub
		try{
			alien.startDuck();
		}
		catch(IllegalStateException exc){
			throw new ModelException("You can't carry out this action.");		
		}
		
	}

	/**
	 * End the given alien's ducking.
	 * 
	 * @param alien
	 *            The alien that has to stop ducking.
	 */
	@Override
	public void endDuck(Mazub alien) {
		// TODO Auto-generated method stub
		try{
			alien.endDuck();
		}
		catch(IllegalStateException exc){
			throw new ModelException("You can't carry out this action.");		
		}
		
	}

	/**
	 * Advance the state of the given alien by the given time period.
	 * 
	 * @param alien
	 *            The alien whose time has to be advanced.
	 * @param dt
	 *            The time interval (in seconds) by which to advance the given
	 *            alien's time.
	 */
	@Override
	public void advanceTime(Mazub alien, double dt) {
		// TODO Auto-generated method stub
		alien.advanceTime(alien.getHorizontalVelocity(), alien.getVerticalVelocity(), dt);
		
	}
	
	

}
