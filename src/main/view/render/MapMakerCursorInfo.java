package view.render;

import model.tile.Location;

public class MapMakerCursorInfo {
    private Location cursorLocation;
    private boolean isCursorValid;

    public MapMakerCursorInfo(Location cursorLocation, boolean isValid ) {
        this.cursorLocation = cursorLocation;
        this.isCursorValid = isValid;
    }

    public Location getCursorLocation() {
        return cursorLocation;
    }
    public void setCursorLocation(Location cursorLoc) {
        this.cursorLocation = cursorLocation;
    }
    public boolean getIsCursorValid() {
        return isCursorValid;
    }
    public void setIsCursorValid(boolean isValid) {
        this.isCursorValid = isValid;
    }
}