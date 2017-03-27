package model;

import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;

import java.util.HashMap;

public class Map {
    private java.util.Map<Location, Tile> tiles;
    public Map(){
        tiles = new HashMap<Location, Tile>();
        this.initialize();
    }

//    Completely ignore this, just for Anip to work with a small initial map on FileExporter.
    private void initialize() {
        tiles.put(new Location(0,0,0), new Tile(Terrain.SEA));
        tiles.put(new Location(0,-1,1), new Tile(Terrain.SEA));
        tiles.put(new Location(0,1,-1), new Tile(Terrain.SEA));
        tiles.put(new Location(1,0,-1), new Tile(Terrain.SEA));
        tiles.put(new Location(-1,0,1), new Tile(Terrain.SEA));
        tiles.put(new Location(1,-1,0), new Tile(Terrain.SEA));
    }

}
