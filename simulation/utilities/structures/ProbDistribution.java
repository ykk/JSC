package simulation.utilities.structures;

/** Class to hold probability distribution.
 * @author ykk
 */
public class ProbDistribution
    extends DataDistribution
{
    //Members
    /** Constructor.
     * @param binNumber number of bins
     * @param binMin minimum value of all bins
     * @param binSize size of each bin
     */
    public ProbDistribution(int binNumber, double binMin, double binSize)
    {
	super(binNumber, binMin, binSize);
    }

    /** Constructor.
     * @param min minimum of sample values
     * @param max maximum of sample values
     * @param binNumber number of bins
     */
    public ProbDistribution(double min, double max, int binNumber)
    {
	this(binNumber, min,(max-min)/((double) binNumber));
    }

    /** Normalize distribution, i.e., make sum equal to 1.
     */
    public void normalize()
    {
	double total = sum();
	for (int i = 0; i < binNumber; i++)
	    writeBin(i,readBin(i)/total);
    }

    /** Increment bin value by 1.
     * @param index index of bin
     */
    public void increment(int index)
    {
	writeBin(index, readBin(index)+1);
    }

    /** Decrement bin value by 1.
     * @param index index of bin
     */
    public void decrement(int index)
    {
	writeBin(index, readBin(index)-1);
    }
}