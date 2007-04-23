package simulation.results.distribution;

import simulation.utilities.structures.*;
import simulation.results.*;
import java.util.*;

/** Class that create subsets of result by binning them.
 * @author ykk
 */
public class ResultBins
    extends ResultDistribution
{
    /** Reference to bin distribution.
     * This is the distribution for which the values will be bin according to.
     */
    public DataDistribution binDistro;
    /** Vector to hold Result class for each bin.
     */
    public Vector results = new Vector();

    //Methods
    /** Constructor.
     * Create {@link Result} for each bin by default.
     * @param binDistro binnning distribution
     */
    public ResultBins(DataDistribution binDistro)
    {
	this(binDistro, false);
    }

    /** Constructor.
     * @param binDistro binnning distribution
     * @param maintainVar indicate if variance is maintained
     */
    public ResultBins(DataDistribution binDistro, boolean maintainVar)
    {
	this.binDistro = binDistro;
	for (int i = 0; i < binDistro.binNumber; i++)
	    if (maintainVar)
		results.add(new ResultVar());
	    else
		results.add(new Result());
    }

    /** Input values.
     * @param binValue value to decide bin with
     * @param value value to bin
     */
    public void input(double binValue, double value)
    {
	int binIndex = binDistro.binIndex(binValue);
	((Result) results.get(binIndex)).input(value);
    }


    /** Return distribution of standard deviation for results.
     *@return distribution of standard deviation for each bin
     */
    public DataDistribution stdDevDistribution()
    {
	DataDistribution distro = new DataDistribution(binDistro.binNumber,
						       binDistro.binMin,
						       binDistro.binSize);
	for (int i = 0; i < results.size(); i++)
	    distro.writeBin(i, ((ResultVar) results.get(i)).stdDev());

	return distro;
    }

    /** Return distribution of sample size for results.
     *@return distribution of count for each bin
     */
    public DataDistribution countDistribution()
    {
	DataDistribution distro = new DataDistribution(binDistro.binNumber,
						       binDistro.binMin,
						       binDistro.binSize);
	for (int i = 0; i < results.size(); i++)
	    distro.writeBin(i, ((Result) results.get(i)).sampleSize);

	return distro;
    }

    /** Return distribution of mean for results.
     *@return distribution of mean 
     */
    public DataDistribution distribution()
    {
	DataDistribution distro = new DataDistribution(binDistro.binNumber,
						       binDistro.binMin,
						       binDistro.binSize);
	for (int i = 0; i < results.size(); i++)
	    distro.writeBin(i, ((Result) results.get(i)).mean);

	return distro;
    }
}