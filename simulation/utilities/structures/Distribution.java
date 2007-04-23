package simulation.utilities.structures;

import java.util.*;

/** Class to hold distributions.
 * @author ykk
 */
public class Distributions
    extends Vector
{
    //Members 
    /** Number of bins
     */
    public int binNumber;
    /** Minimum value of bin values. 
     */
    public double binMin;
    /** Size of bins.
     */
    public double binSize;


    //Members
    /** Constructor.
     * @param binNumber number of bins
     * @param binMin minimum value of all bins
     * @param binSize size of each bin
     */
    public Distributions(int binNumber, double binMin, double binSize)
    {
	this.binNumber = binNumber;
	this.binMin = binMin;
	this.binSize = binSize;
	for (int i = 0; i < binNumber; i++)
	    this.add(new Double(0));
    }

    /** Constructor.
     * @param min minimum of sample values
     * @param max maximum of sample values
     * @param binNumber number of bins
     */
    public Distributions(double min, double max, int binNumber)
    {
	this(binNumber, min,(max-min)/((double) binNumber));
    }

    /** Return maximum value accommodated in bins.
     * @return maximum value
     */
    public double max()
    {
	return binMin+(binSize*binNumber);
    }

    /** Sum of bins.
     * @return sum of all bins
     */
    public double sum()
    {
	double sumResult = 0;
	for (int i = 0; i < binNumber; i++)
	    sumResult += readBin(i);

	return sumResult;
    }

    /** Get bin index.
     * Return -1 if out of range.
     * @param sampleValue value of sample
     * @return index of bin that sample belongs to
     */
    public int binIndex(double sampleValue)
    {
	if ((sampleValue < binMin) || sampleValue > max())
	    return -1;
	else
	    return (int) Math.floor((sampleValue-binMin)/binSize);
    }

    /** Read bin value.
     * Returns -1 if bin is out of range
     * @param binIndex index of bin to read
     * @return value of bin
     */
    public double readBin(int binIndex)
    {
	if ((binIndex < 0) || (binIndex >= this.size()))
	    return -1;
	else
	    return ((Double) this.get(binIndex)).doubleValue();
    }

    /** Write bin with new value.
     * @param binIndex index of bin to overwrite
     * @param value to overwrite bin value with
     * @return if successful
     */
    public boolean writeBin(int binIndex, double value)
    {
	if ((binIndex < 0) || (binIndex >= this.size()))
	    return false;
	else
        {
	    this.remove(binIndex);
	    this.add(binIndex, new Double(value));
	    return true;
	}
    }

    /** String representation of distribution.
     * @return probability mass function of distribution
     */
    public String toString()
    {
	String output = "#Bin Min\tBin Max\tBin Count";
	for (int i = 0; i < this.size(); i++)
	    output += "\n"+
		(binMin+i*binSize)+"\t"+
		(binMin+(i+1)*binSize)+"\t"+
		this.readBin(i);
	return output;
    }
}