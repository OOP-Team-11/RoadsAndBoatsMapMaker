package model.tile;

import direction.TileCompartmentDirection;
import direction.TileEdgeDirection;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TileTest {

    // Creating Tiles using all the Terrain Types --------------------------------------------------
    @Test
    public void constructTestSeaTile() {
        Tile t = new Tile(Terrain.SEA);
        assertEquals(t.getTerrain(), Terrain.SEA);
    }

    @Test
    public void constructTestPastureTile() {
        Tile t = new Tile(Terrain.PASTURE);
        assertEquals(t.getTerrain(), Terrain.PASTURE);
    }

    @Test
    public void constructTestWoodsTile() {
        Tile t = new Tile(Terrain.WOODS);
        assertEquals(t.getTerrain(), Terrain.WOODS);
    }

    @Test
    public void constructTestRockTile() {
        Tile t = new Tile(Terrain.ROCK);
        assertEquals(t.getTerrain(), Terrain.ROCK);
    }

    @Test
    public void constructTestDesertTile() {
        Tile t = new Tile(Terrain.DESERT);
        assertEquals(t.getTerrain(), Terrain.DESERT);
    }

    @Test
    public void constructTestMountainTile() {
        Tile t = new Tile(Terrain.MOUNTAIN);
        assertEquals(t.getTerrain(), Terrain.MOUNTAIN);
    }
    // ----------------------------------------------------------------------------------------------------


    // Toggle TileCompartment in Tile
    @Test
    public void getTileCompartmentTest() {
        Tile t = new Tile(Terrain.SEA);
        t.setHasWater(TileCompartmentDirection.getNorth(), true);
        assertEquals(t.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater(), true);
    }
    // ----------------------------------------------------------------------------------------------------


    // Getting TileEdge from Tile
    @Test
    public void getTileEdgeTest() {
        Tile t = new Tile(Terrain.SEA);
        t.setCanConnectWater(TileEdgeDirection.getNorth(), true);
        assertEquals(t.getTileEdge(TileEdgeDirection.getNorth()).canConnectRiver(), true);
    }
    // ----------------------------------------------------------------------------------------------------


}
