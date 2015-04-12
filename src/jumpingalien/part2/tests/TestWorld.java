package jumpingalien.part2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import jumpingalien.model.World;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jumpingalien.tests.util.TestUtils;

public class TestWorld {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.world1 = new World(5,3,4,10,10,15,0);
	}
	private World world1;
	
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void constructor_SingleCase() {
		World world = new World(5,3,5,10,10,15,0);
		assertEquals(world.getxMax(), 15);
		assertEquals(world.getyMax(), 25);
		assertEquals(world.getVisibleWindowHeight(), 10);
		assertEquals(world.getVisibleWindowWidth(), 10);
		assertEquals(world.getTargetTileX(), 15);
		assertEquals(world.getTargetTileY(), 0);
		assertEquals(world.getTileSize(), 5);
		assertEquals(world.getNbTilesX(), 3);
		assertEquals(world.getNbTilesY(), 5);
	}

	@Test
	public void getTilePositions_WholeWorld(){
		int[][] array = {{0,0},{5,0},{10,0},{0,5},{5,5},{10,5},{0,10},{5,10},{10,10},{0,15},{5,15},{10,15}};
		array.equals(world1.getTilePositions(0,0,15,20));
		
	}
	
	@Test
	public void getTilePositions_PartWorld(){
		int[][] array = {{5,5},{5,10}};
		array.equals(world1.getTilePositions(5,5,10,15));
		//TODO: hij loopt vast op deze test, waarom?
	}
}
