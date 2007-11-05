package simulation.optimization.linearprogram;

/** Class for linear equations, i.e., equality or inequality.
 * @author ykk
 */
public class LinearEqn
{
    //Members
    /** Reference to set of variables.
     */
    protected VariableSet variables;
    /** Types of equation.
     */
    public static final int TYPE_EQUAL = 0;
    public static final int TYPE_GREATERTHAN = 1;
    public static final int TYPE_LESSTHAN = 2;

    public static final int TYPE_MIN = 3;
    public static final int TYPE_MAX = 4;
    /** Type of equation.
     * Default to {@link #TYPE_GREATERTHAN}
     * @see #TYPE_EQUAL
     * @see #TYPE_GREATERTHAN
     * @see #TYPE_LESSTHAN
     */
    public int type = TYPE_GREATERTHAN;
    /** Value equation evaluates to.
     * Default to zero
     */
    public double value = 0;
    /** Coefficients of variables in equation.
     * Defaulted to zero.
     */
    public double[] coefficients;

    //Methods
    /** Constructor to create equation,
     * which by default is greater than zero.
     * @param variables set of variables
     */
    public LinearEqn(VariableSet variables)
    {
	this(variables, TYPE_GREATERTHAN, 0);
    }

    /** Constructor to create equation
     * @param variables set of variables
     * @param type type of equation
     * @param value value equation evaluates to
     */
    public LinearEqn(VariableSet variables, int type, double value)
    {
	this.variables = variables;
	this.type = type;
	this.value = value;

	coefficients = new double[variables.size()];
	for (int i = 0; i < variables.size(); i++)
	    coefficients[i] = 0;
    }

    /** Set coefficients for a variable of a given name.
     * @param name name of variable
     * @param coeffvalue coefficient value
     * @return if operation is successful
     */
    public boolean setCoefficient(String name, double coeffvalue)
    {
	int index = variables.indexOf(name);
	if (index != -1)
	{
	    coefficients[index] = coeffvalue;
	    return true;
	}
	else
	    return false;
    }

    /** Return reference to variable set.
     * @return reference to variable set
     */
    public VariableSet variables()
    {
	return variables;
    }

}