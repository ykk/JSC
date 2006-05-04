package simulation.networks;

/** Abstract class to provide area definition for a network.
 * @author ykk
 */
public abstract class NetworkArea
{

    /** Function to return minimum x-coordinate.
     */
    public abstract double minX();

    /** Function to return maximum x-coordinate.
     */
    public abstract double maxX();

    /** Function to return minimum y-coordinate.
     */
    public abstract double minY();

    /** Function to return maximum y-coordinate.
     */
    public abstract double maxY();

    /** Function to check if point is in area of network.
     * @param coordinate coordinate of point.
     */
    public abstract boolean inArea(Coordinate coordinate);

    /** Function to check if coordinate specified is in area of network.
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     */
    public boolean inArea(double x, double y)
    {
	return inArea(new Coordinate(x,y));
    }
}
