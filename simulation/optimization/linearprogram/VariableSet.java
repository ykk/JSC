package simulation.optimization.linearprogram;

import java.util.*;

/** Class for set of variables for equations.
 * @author ykk
 */
public class VariableSet
    extends Vector
{
    /** Constructor -- create empty set of variable
     */
    public VariableSet()
    {
	super();
    }

    /** Constructor -- create set of variables with given names
     * @param variables name of variables
     */
    public VariableSet(String[] variables)
    {
	for (int i = 0; i < variables.length; i++)
	    this.add(variables[i]);
    }

    /** Add variable.
     * @param name name of variable to add
     */
    public void addVar(String name)
    {
	this.add(name);
    }

    /** Return index of a variable given name.
     * Returns -1 if not found.
     * Index from 0 to size-1.
     * @param name name of variable
     * @return index of variable
     */
    public int indexOf(String name)
    {
	for (int i = 0; i < size(); i++)
	    if (name.compareTo((String) get(i)) == 0)
		return i;

	return -1;
    }

    /** Return cardinality of variable set.
     * @return size of variable set
     */
    public int size()
    {
	return super.size();
    }
}
