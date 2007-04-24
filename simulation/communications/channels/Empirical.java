package simulation.communications.channels;

import simulation.math.interpolation.*;
import simulation.communications.nodes.*;

/** Unreliable communication channel modeled using empirical data.
 * @see simulation.utilities.linkcosts.Reliability
 * @author ykk
 */
public class Empirical
    extends PathLoss
{
    //Members
    /** Path loss exponent.
     * Overloaded to private.
     */
    private double exponent;
    /** Interpolator for transmit probabilities.
     */
    public Interpolator channel;

    //Methods
    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param channel interpolator to give channel reception probabilities
     */
    public Empirical(double rate, Interpolator channel)
    {
	super(rate, 0);
	this.channel = channel;
    }

    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param headerRate transmission rate for header
     * @param channel interpolator to give channel reception probabilities
     */
    public Empirical(double rate, double headerRate, Interpolator channel)
    {
	super(rate, headerRate, 0);
	this.channel = channel;
    }

    /** Provide probability of success to transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @return probability of success
     */
    public double transmitProb(CommNode source, CommNode destination)
    {
	if (source.transmitPartners.indexOf(destination) != -1)
	{
	    double prob = channel.value(source.distance(destination));
	    if (prob < 0.0)
		return 0.0;
	    else if (prob > 1.0)
		return 1.0;
	    else
		return prob;
	}
	else
	    return 0.0;
    }   
}