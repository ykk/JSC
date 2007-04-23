package simulation.results.distribution;

import simulation.utilities.structures.*;
import simulation.results.*;

/** Abstract class for recording and providing distribution of the result.
 * @author ykk
 */
public abstract class ResultDistribution
    extends ResultVar
{
    //Methods
    /** Return distribution of results.
     *@return result distribution
     */
    public abstract Distributions distribution();
}