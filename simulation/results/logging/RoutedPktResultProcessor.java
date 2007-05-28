package simulation.results.processors;

import simulation.communications.packets.*;

/** Class for result processing of RoutedPacket.
 * @see RoutedPacket
 * @author ykk
 */
public class RoutedPktResultProcessor
    extends TimedPktResultProcessor
{
    //Methods
    /** Input object to process for result.
     * @param object object to be processed
     */
    public void input(Object object)
    {
	if (!(object instanceof RoutedPacket))
	    throw new RuntimeException(this+" told to handle "+object);
	RoutedPacket pkt = (RoutedPacket) object;

	if (printing) System.out.println(string(pkt));
    }

    /** Return string for object.
     * @param pkt packet to process
     */
    public String string(RoutedPacket pkt)
    {
	return ((RoutedPacket) pkt).route.source()+separator+
	    ((RoutedPacket) pkt).route.destination()+separator+
	    super.string(pkt);
    }
}