package jumpingalien.model;
import jumpingalien.model.World;
/**
 * @invar	The length must be a divisor of both the maximum length 
 * 			in the x-direction as well as the one of the y-direction.
 * @invar	The tileX and tileY should always lie in the screen.
 * @author Joni
 *
 */
public class Tile {
	/**
	 * 
	 * @param tileX
	 * @param tileY
	 * @param length
	 * @param feature
	 */
	public Tile(int tileX, int tileY, int feature, World world){
		setTileX(tileX);
		setTileY(tileY);
		this.length = world.getTileSize();
		this.geologicalFeature = feature;
		setWorld(world);
	}
	
	private World world;
	
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}

	private int tileX;
	public int getTileX() {
		return tileX;
	}
	private void setTileX(int tileX) {
		this.tileX = tileX;
	}
	public int getTileY() {
		return tileY;
	}
	private void setTileY(int tileY) {
		this.tileY = tileY;
	}
	private int tileY;
	public int getLength() {
		return length;
	}
	private void setLength(int length) {
		this.length = length;
	}
	private  int length;
	public int getGeologicalFeature() {
		return geologicalFeature;
	}
	public void setGeologicalFeature(int geologicalFeature) throws IllegalArgumentException {
		if(geologicalFeature > 3 || geologicalFeature < 0)
			throw new IllegalArgumentException();
		this.geologicalFeature = geologicalFeature;
	}

	
	
	private int geologicalFeature;
}
