package simulation.utilities.structures;

import java.util.*;

/** Class to hold data distributions.
 * To plot, use {@link simulation.files.gnuplot.plotters.PlotVector}
 * after converting to string using 
 * {@link simulation.utilities.processors.VectorOp#vecString(Vector objects)}
 * @author ykk
 */
public class DataDistribution
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
    public DataDistribution(int binNumber, double binMin, double binSize)
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
    public DataDistribution(double min, double max, int binNumber)
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

    /** Return cumulative distribution.
     * @return cumulative distribution
     */
    public DataDistribution cumulative()
    {
	DataDistribution cumu = new DataDistribution(binNumber, binMin, binSize);
	double sum = 0;
	for (int i = 0 ; i < this.size(); i++)
	{
	    sum += this.readBin(i);
	    cumu.writeBin(i, sum);
	}

	return cumu;
    }

    /** Return normalized distribution.
     * @param norm value of sum in normalized distribution
     * @return normalized distribution
     */ 
    public DataDistribution normalize(double norm)
    {
	DataDistribution normalized = new DataDistribution(binNumber, binMin, binSize);
	double sum = sum();
	for (int i = 0; i < this.size(); i++)
	    normalized.writeBin(i, (this.readBin(i)*norm)/sum);

	return normalized;
    }

    /** Trim data distribution, i.e., remove any zero bins from start and end.
     * @return trimmed distribution
     */
    public DataDistribution trim()
    {
	int startBin = 0;
	while (readBin(startBin) == 0)
	{
	    startBin++;
	    if (startBin == this.size())
		return new DataDistribution(0.0,0.0,0);
	}

	int endBin = this.size()-1;
	while (readBin(endBin) == 0)
	    endBin--;

	DataDistribution trimmed = new DataDistribution(endBin-startBin+1, 
							binMin+startBin*binSize, binSize);
	for (int i = startBin; i <= endBin; i++)
	    trimmed.writeBin(i-startBin, this.readBin(i));

	return trimmed;
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