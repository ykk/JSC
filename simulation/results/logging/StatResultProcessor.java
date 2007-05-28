package simulation.results.processors;

import java.util.*;
import simulation.results.*;

/** Basic class for statistical result processing.
 * @see Result
 * @author ykk
 */
public class StatResultProcessor
{
    //Members
    /** Vector of objects associated with result objects.
     */
    private Vector objects = new Vector();

    /** Vector of result objects.
     */
    private Vector results = new Vector();

    //Methods
    /** Register object and result.
     * @param object associative object
     * @param result result object
     */
    public void register(Object object, Result result)
    {
	objects.add(object);
	results.add(result);
    }

    /** Input result with object associated.
     * Provisioned for passing in associated object for {@link ResultPrint}.
     * @param object associated object
     * @param inputValue input value
     */
    public void input(Object object, double inputValue)
    {
	if (objects.indexOf(object) != -1)
	{
	    if (((Result) results.get(objects.indexOf(object))) instanceof ResultPrint)
		((ResultPrint) results.get(objects.indexOf(object))).input(object, inputValue);
	    else
		((Result) results.get(objects.indexOf(object))).input(inputValue);
	}
	else
	    throw new RuntimeException(this+" received "+object+" not in its object list.");
    }
}