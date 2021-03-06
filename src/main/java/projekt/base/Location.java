package projekt.base;

/**
 * A tuple for the x- and y-coordinates of a point.
 */
public final class Location {
    private final int x;
    private final int y;

    /**
     * Instantiates a new {@link Location} object using {@code x} and {@code y} as coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of this location.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this location.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Adds the coordinates of this location and the other location and returns a new
     * {@link Location} object with the resulting coordinates.
     *
     * @param other the other {@link Location} object to get the second set of coordinates from
     * @return a new {@link Location} object with the sum of coordinates from both locations
     */
    public Location add(Location other) {
        return new Location(x + other.x, y + other.y);
    }

    /**
     * Subtracts the coordinates of this location from the other location and returns a new
     * {@link Location} object with the resulting coordinates.
     *
     * @param other the other {@link Location} object to get the second set of coordinates from
     * @return a new {@link Location} object with the difference of coordinates from both locations
     */
    public Location subtract(Location other) {
        return new Location(x - other.x, y - other.y);
    }
}
