package simulation.eventbased.mediumaccess;

/** Class to store specifications for WiFi.
 * @author ykk
 */
public class WiFiSpec
{
    //Members
    /** Minimum contention window.
     */
    public int minCW;
    /** Maximum contention window.
     */
    public int maxCW ;
    /** Short interframe spacing.
     */
    public double sifs;
    /** Slot time.
     */
    public double slotTime;
    /** IEEE 802.11b standard.
     * CWmin = 31; CWmax = 1023; SIFS = 10 us; SlotTime = 20 us.
     */
    public static final int IEEE802_11b = 0;

    //Methods
    /** Constructor.
     * @param std number to indicate standard used.
     */
    public WiFiSpec(int std)
    {
	switch (std)
	{
	case IEEE802_11b:
	    minCW = 31;
	    maxCW = 1023;
	    sifs = 10e-6;
	    slotTime = 20e-6;
	    break;
	}
    }
}