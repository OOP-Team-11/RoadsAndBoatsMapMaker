package model;


import direction.DirectionToLocation;
import direction.TileEdgeDirection;
import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Tile;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.StrictMath.abs;

public class Map
{
    private java.util.Map<Location, Tile> tiles;

    public Map()
    {
        tiles = new HashMap<Location, Tile>();
    }

    public Tile getTile(Location tileLocation)
    {
        return tiles.get(tileLocation);
    }

    public boolean placeTile(Location tileLocation, Tile tile) {
        if (!isValidPlacement(tileLocation, tile)) return false;

        tiles.put(tileLocation, tile);
        return true;
    }

	public boolean isValidPlacement(Location tileLocation, Tile tile)
	{
            return hasAdjacentTile(tileLocation) && hasMatchingEdges(tileLocation, tile) || this.tiles.size() == 0;
	}
	
	private boolean hasAdjacentTile(Location tileLocation)
	{		
		for(TileEdgeDirection dir: TileEdgeDirection.getAllDirections())
		{
            Location loc = DirectionToLocation.getLocation(tileLocation, dir);
			
			if(tiles.get(loc) != null)
			{
                return true;
			}
		}
		
		return false;
	}
	
	public boolean hasMatchingEdges(Location tileLocation, Tile tile)
	{
		for(TileEdgeDirection dir: TileEdgeDirection.getAllDirections())
		{
            Location loc = DirectionToLocation.getLocation(tileLocation, dir);
			Tile t = tiles.get(loc);
			
			if(t != null)
            {
                return (t.getTileEdge(dir.reverse()).canConnectRiver() !=  tile.getTileEdge(dir).canConnectRiver());
			}
		}
		
		return true;
	}

    public void recenter()
    {
        Location center = calculateCenter();

        HashMap<Location, Tile> newMap = new HashMap<Location, Tile>();

        for (java.util.Map.Entry<Location, Tile> entry : tiles.entrySet())
        {
            Location loc = offsetLocation(entry.getKey(), center);
            newMap.put(loc, entry.getValue());
            this.tiles = newMap;
        }
    }

    private Location calculateCenter()
    {
        int minX = 0;
        int minY = 0;
        int minZ = 0;

        int maxX = 0;
        int maxY = 0;
        int maxZ = 0;

        for (Location loc : getAllLocations())
        {
            minX = Math.min(minX, loc.getX());
            minY = Math.min(minY, loc.getY());
            minZ = Math.min(minZ, loc.getZ());

            maxX = Math.max(maxX, loc.getX());
            maxY = Math.max(maxY, loc.getY());
            maxZ = Math.max(maxZ, loc.getZ());
        }

        int x = (maxX + minX) / 2;
        int y = (maxY + minY) / 2;
        int z = (maxZ + minZ) / 2;

        try
        {
            return new Location(x, y, z);
        }
        catch (InvalidLocationException e)
        {
            return new Location(x, y);
        }
    }

    private static Location offsetLocation(Location loc, Location center)
    {
        try
        {
            return new Location(loc.getX() - center.getX(),
                    loc.getY() - center.getY(),
                    loc.getZ() - center.getZ());
        }
        catch (InvalidLocationException e)
        {
            return null; //Impossible to reach
        }
    }

    private Set<Location> getAllLocations()
    {
        return tiles.keySet();
    }


    /**
     * Removes a tile at a location in the map if it exists.
     * @param location
     * @return true if tile was removed. false otherwise.
     */
    public boolean removeTileAtLocation(Location location) {
        if (this.tiles.containsKey(location)) {
            this.tiles.remove(location);
            return true;
        }

        return false;
    }
}
