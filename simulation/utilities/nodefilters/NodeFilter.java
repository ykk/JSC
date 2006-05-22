package simulation.utilities.nodefilters;

import java.util.*;

/** Basic abstract class for node filters.
 * @author ykk
 */
public abstract class NodeFilter
{
    /** Function to filter and return resulting set of nodes.
     * @param nodes vector of nodes to filter
     * @return resulting set of nodes after filtering
     */
    public abstract Vector filter(Vector nodes);
}
