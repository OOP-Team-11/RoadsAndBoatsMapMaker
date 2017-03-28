package direction;

import direction.Angle;

/**
 * Pre-defined set of Angles that relate to directions on a compass.
 */
public enum CompassAngles {
    EAST(0),
    NORTHEAST(45),
    NORTH_NORTHEAST(60),
    NORTH(90),
    NORTH_NORTHWEST(120),
    NORTHWEST(135),
    WEST(180),
    SOUTHWEST(225),
    SOUTH_SOUTHWEST(240),
    SOUTH(270),
    SOUTH_SOUTHEAST(300),
    SOUTHEAST(315);

    private Angle angle;
    CompassAngles(int degrees) {
        this.angle = new Angle(degrees);
    }

    public Angle getAngle() {
        return this.angle;
    }
}
