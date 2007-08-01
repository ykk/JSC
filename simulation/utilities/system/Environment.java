package simulation.utilities.system;

/** Class to determine environment.
 * @author ykk
 */
public class Environment
{
    /** Get environment variable.
     * @param name name of variable
     * @return value of environment variable
     */
    public static String getEnvVar(String name)
    {
	return System.getenv(name);
    }
}
