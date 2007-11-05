package simulation.utilities.processors;

import java.util.*;

/** Processor for string.
 * @see Table
 * @author ykk
 */
public class StringProcessor
{
    /** Parse string into vector.
     * @param string string to be parsed
     * @param separator separator to use
     * @return vector of strings
     */
    public static Vector strToVector(String string, String separator)
    {
	Vector content = new Vector();
	StringTokenizer tokens = new StringTokenizer(string,separator);

	while (tokens.hasMoreTokens())
	    content.add(tokens.nextToken());

	return content;
    }   
}