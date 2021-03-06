package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.Tile;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements jumpingalien.part2.facade.IFacadePart2 {

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
		try{
			alien.endDuck();
		}
		catch(IllegalStateException exc){
			throw new ModelException("You can't carry out this action.");		
		}
		
	}

	/**
	 * Returns the current number of hitpoints of the given alien.
	 */
	public int getNbHitPoints(Mazub alien){
		return alien.getHitPoints();
	}

	/**
	 * Create a new game world with the given parameters.
	 * 
	 * @param tileSize
	 *            Length (in pixels) of a side of each square tile in the world
	 * @param nbTilesX
	 *            Number of tiles in the horizontal direction
	 * @param nbTilesY
	 *            Number of tiles in the vertical direction
	 * @param visibleWindowWidth
	 *            Width of the visible window, in pixels
	 * @param visibleWindowHeight
	 *            Height of the visible window, in pixels
	 * @param targetTileX
	 *            Tile x-coordinate of the target tile of the created world
	 * @param targetTileY
	 *            Tile y-coordinate of the target tile of the created world
	 */
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) throws ModelException{
		try{
		World world = new World(tileSize, nbTilesX, nbTilesY, visibleWindowWidth, visibleWindowHeight, targetTileX, targetTileY);
		return world;
		}
		catch(IllegalArgumentException ext){
			throw new ModelException("");
		}
	}

	/**
	 * Returns the size of the given game world, in number of pixels.
	 * 
	 * @param world
	 *            The world for which to return the size.
	 * @return The size of the game world, in pixels, as an array of two
	 *         elements: width (X) and height (Y), in that order.
	 */
	public int[] getWorldSizeInPixels(World world){
		int[] array = {world.getxMax(), world.getyMax()};
		return array;
	}

	/**
	 * Returns the length of a square tile side in the given world.
	 * 
	 * @param world
	 *            The game world for which to retrieve the tile length
	 * 
	 * @return The length of a square tile side, expressed as a number of
	 *         pixels.
	 */
	public int getTileLength(World world){
		return world.getTileSize();
	}

	/**
	 * Starts the game that is played in the given world.
	 * After this method has been invoked, no further game objects will be added
	 * via {@link IFacadePart2#addPlant(World, Plant)},
	 * {@link IFacadePart2#addShark(World, Shark)},
	 * {@link IFacadePart2#addSlime(World, Slime)}, or
	 * {@link IFacadePart2#setMazub(World, Mazub)}), and no geological features
	 * will be changed via
	 * {@link IFacadePart2#setGeologicalFeature(World, int, int, int)}.
	 * 
	 * @param The
	 *            world for which to start the game.
	 */
	public void startGame(World world){
		world.startGame();
	}

	/**
	 * Returns whether the game, played in the given game world, is over.
	 * The game is over when Mazub has died, or has reached the target tile.
	 * 
	 * @param world
	 *            The world for which to check whether the game is over
	 * @return true if the game is over, false otherwise.
	 */
	public boolean isGameOver(World world){
		return world.isGameOver();
	}

	/**
	 * Returns whether the game played in the given world has finished and the
	 * player has won. The player wins when Mazub has reached the target tile.
	 * 
	 * @param world
	 *            The world for which to check whether the player won
	 * @return true if the game is over and the player has won; false otherwise.
	 */
	public boolean didPlayerWin(World world){
		return world.didPlayerWin();
	}

	/**
	 * Advance the time for the world and all its objects by the given amount.
	 * 
	 * This method replaces {@link IFacadePart2#advanceTime(Mazub, double)}.
	 * 
	 * @param world
	 *            The world whose time needs to advance
	 * @param dt
	 *            The time interval (in seconds) by which to advance the given
	 *            world's time.
	 */
	public void advanceTime(World world, double dt){
		world.advanceTime(dt);
	}

	/**
	 * Return the coordinates of the rectangular visible window that moves
	 * together with Mazub.
	 * 
	 * @return The pixel coordinates of the visible window, in the order
	 *         <b>left, bottom, right, top</b>.
	 */
	public int[] getVisibleWindow(World world)throws ModelException{
		try{
		return world.getVisibleWindow();
		}
		catch(IllegalStateException ext){
			throw new ModelException("");
		}
	}

	/**
	 * Returns the bottom left pixel coordinate of the tile at the given tile
	 * position.
	 * 
	 * @param world
	 *            The world from which to retrieve the tile.
	 * @param tileX
	 *            The x-position x_T of the tile
	 * @param tileY
	 *            The y-position y_T of the tile
	 * @return An array which contains the x-coordinate and y-coordinate of the
	 *         bottom left pixel of the given tile, in that order.
	 */
	// In pixel teruggeven dus maal tilesize.
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY){
		return world.getBottomLeftPixelOfTile(tileX, tileY);
	}

	/**
	 * Returns the tile positions of all tiles within the given rectangular
	 * region.
	 * 
	 * @param world
	 *            The world from which the tile positions should be returned.
	 * @param pixelLeft
	 *            The x-coordinate of the left side of the rectangular region.
	 * @param pixelBottom
	 *            The y-coordinate of the bottom side of the rectangular region.
	 * @param pixelRight
	 *            The x-coordinate of the right side of the rectangular region.
	 * @param pixelTop
	 *            The y-coordinate of the top side of the rectangular region.
	 * 
	 * @return An array of tile positions, where each position (x_T, y_T) is
	 *         represented as an array of 2 elements, containing the horizontal
	 *         (x_T) and vertical (y_T) coordinate of a tile in that order.
	 *         The returned array is ordered from left to right,
	 *         bottom to top: all positions of the bottom row (ordered from
	 *         small to large x_T) precede the positions of the row above that.
	 * 
	 */
	// hier gedeeld door tilesize want de tile coordinaten.
	public int[][] getTilePositionsIn(World world, int pixelLeft, int pixelBottom,
			int pixelRight, int pixelTop){ 
			return world.getTilePositions(pixelLeft, pixelBottom, pixelRight, pixelTop);
	}

	/**
	 * Returns the geological feature of the tile with its bottom left pixel at
	 * the given position.
	 * 
	 * @param world
	 *            The world containing the tile for which the
	 *            geological feature should be returned.
	 * 
	 * @param pixelX
	 *            The x-position of the pixel at the bottom left of the tile for
	 *            which the geological feature should be returned.
	 * @param pixelY
	 *            The y-position of the pixel at the bottom left of the tile for
	 *            which the geological feature should be returned.
	 * 
	 * @return The type of the tile with the given bottom left pixel position,
	 *         where
	 *         <ul>
	 *         <li>the value 0 is returned for an <b>air</b> tile;</li>
	 *         <li>the value 1 is returned for a <b>solid ground</b> tile;</li>
	 *         <li>the value 2 is returned for a <b>water</b> tile;</li>
	 *         <li>the value 3 is returned for a <b>magma</b> tile.</li>
	 *         </ul>
	 * 
	 * @note This method must return its result in constant time.
	 * 
	 * @throw ModelException if the given position does not correspond to the
	 *        bottom left pixel of a tile.
	 */
	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException{			
			return world.getGeologicalFeature(pixelX, pixelY);
	}

	/**
	 * Modify the geological type of a specific tile in the given world to a
	 * given type.
	 * 
	 * @param world
	 *            The world in which the geological type of a tile needs to be
	 *            modified
	 * @param tileX
	 *            The x-position x_T of the tile for which the type needs to be
	 *            modified
	 * @param tileY
	 *            The y-position y_T of the tile for which the type needs to be
	 *            modified
	 * @param tileType
	 *            The new type for the given tile, where
	 *            <ul>
	 *            <li>the value 0 is provided for an <b>air</b> tile;</li>
	 *            <li>the value 1 is provided for a <b>solid ground</b> tile;</li>
	 *            <li>the value 2 is provided for a <b>water</b> tile;</li>
	 *            <li>the value 3 is provided for a <b>magma</b> tile.</li>
	 *            </ul>
	 */
	public void setGeologicalFeature(World world, int tileX, int tileY, int tileType){
		world.setGeologicalFeature(tileX, tileY, tileType);
	}

	/**
	 * Sets the given alien as the player's character in the given world.
	 * 
	 * @param world
	 *            The world for which to set the player's character.
	 * @param mazub
	 *            The alien to be set as the player's character.
	 */
	public void setMazub(World world, Mazub alien){
		world.setMazub(alien);
	}

	/**
	 * Returns whether the given alien is currently immune against enemies (see
	 * section 1.2.5 of the assignment).
	 * 
	 * @param alien
	 *            The alien for which to retrieve the immunity status.
	 * @return True if the given alien is immune against other enemies (i.e.,
	 *         there are no interactions between the alien and enemy objects).
	 */
	public boolean isImmune(Mazub alien){
		return false;
		//TODO
	}

	/**
	 * Creates a new plant, located at the provided pixel location (x, y).
	 * The returned plant should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the plant's initial position
	 * @param y
	 *            The y-coordinate of the plant's initial position
	 * @param sprites
	 *            An array of sprites for the new plant
	 * 
	 * @return A new plant, located at the provided location. The returned plant
	 *         should not belong to a world.
	 */
	public Plant createPlant(int x, int y, Sprite[] sprites){
		Plant plant = new Plant(x, y, sprites);
		return plant;
	}

	/**
	 * Add the given plant as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the plant should be added.
	 * @param plant
	 *            The plant that needs to be added to the world.
	 */
	public void addPlant(World world, Plant plant){
		world.addGameObject(plant);
	}

	/**
	 * Returns all the plants currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all plants.
	 * @return All plants that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each plant may only be returned once.
	 */
	public Collection<Plant> getPlants(World world){
		return world.listPlant();
	}

	/**
	 * Returns the current location of the given plant.
	 * 
	 * @param plant
	 *            The plant of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given plant's bottom left pixel in the world.
	 */
	public int[] getLocation(Plant plant){
		int[] array = {plant.getPositionX(), plant.getPositionY()};
		return array;
	}

	/**
	 * Return the current sprite image for the given plant.
	 * 
	 * @param plant
	 *            The plant for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given plant, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Plant plant){
		return plant.getCurrentSprite();
	}

	/**
	 * Creates a new shark, located at the provided pixel location (x, y).
	 * The returned shark should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the shark's initial position
	 * @param y
	 *            The y-coordinate of the shark's initial position
	 * @param sprites
	 *            An array of sprites for the new shark
	 * 
	 * @return A new shark, located at the provided location. The returned shark
	 *         should not belong to a world.
	 */
	public Shark createShark(int x, int y, Sprite[] sprites){
		Shark shark = new Shark(x, y, sprites);
		return shark;
	}

	/**
	 * Add the given shark as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the shark should be added.
	 * @param shark
	 *            The shark that needs to be added to the world.
	 */
	public void addShark(World world, Shark shark){
		world.addGameObject(shark);
	}

	/**
	 * Returns all the sharks currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all sharks.
	 * @return All sharks that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each shark may only be returned once.
	 */
	public Collection<Shark> getSharks(World world){
		return world.listShark();
	}

	/**
	 * Returns the current location of the given shark.
	 * 
	 * @param shark
	 *            The shark of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given shark's bottom left pixel in the world.
	 */
	public int[] getLocation(Shark shark){
		int[] array = {shark.getPositionX(), shark.getPositionY()};
		return array;
	}

	/**
	 * Return the current sprite image for the given shark.
	 * 
	 * @param shark
	 *            The shark for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given shark, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Shark shark){
		return shark.getCurrentSprite();
	}

	/**
	 * Creates a new slime school.
	 * 
	 * @return A new school for slimes, without any members.
	 */
	public School createSchool(){
		School school = new School();
		return school;
		//TODO: hoe beperk je het aantal scholen tot 10 ? 
	}

	/**
	 * Creates a new slime, located at the provided pixel location (x, y).
	 * The returned slime should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the slime's initial position
	 * @param y
	 *            The y-coordinate of the slime's initial position
	 * @param sprites
	 *            An array of sprites for the new slime
	 * @param school
	 *            The initial school to which the new slime belongs
	 * 
	 * @return A new slime, located at the provided location and part of the
	 *         given school. The returned slime should not belong to a world.
	 */
	public Slime createSlime(int x, int y, Sprite[] sprites, School school){
		Slime slime = new Slime(x, y, sprites, school);
		return slime;
	}

	/**
	 * Add the given slime as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the slime should be added.
	 * @param slime
	 *            The slime that needs to be added to the world.
	 */
	public void addSlime(World world, Slime slime){
		world.addGameObject(slime);
	}

	/**
	 * Returns all the slimes currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all slimes.
	 * @return All slimes that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each slime may only be returned once.
	 */
	public Collection<Slime> getSlimes(World world){
		return world.listSlime();
	}

	/**
	 * Returns the current location of the given slime.
	 * 
	 * @param slime
	 *            The slime of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given slime's bottom left pixel in the world.
	 */
	public int[] getLocation(Slime slime){
		int[] array = {slime.getPositionX(),slime.getPositionY()};
		return array;
	}

	/**
	 * Return the current sprite image for the given slime.
	 * 
	 * @param slime
	 *            The slime for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given slime, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Slime slime){
		return slime.getCurrentSprite();
	}

	/**
	 * Returns the current school to which the given slime belongs.
	 * 
	 * @param slime
	 *            The slime for which to retrieve the school.
	 * 
	 * @return The current school of the given slime.
	 */
	public School getSchool(Slime slime){
		return slime.getSchool();
	}

}