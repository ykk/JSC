package simulation.utilities.system;

import java.awt.event.*;

/** Class to trigger an event.
 * @author ykk
 */
public class ActionListenerTrigger
    extends Thread
{
    //Members
    /** Length of time to pause in millseconds.
     */
    public int pauseDuration = 0;
    /** Listener to trigger.
     */
    public ActionListener listener;
    /** Event to trigger.
     */
    public ActionEvent event;

    //Methods
    /** Constructor.
     * @param pauseDuration duration to pause in milliseconds
     * @param listener action listener to trigger
     * @param event event to trigger listener with
     */
    public ActionListenerTrigger(int pauseDuration, ActionListener listener,
				 ActionEvent event)
    {
	this.pauseDuration = pauseDuration;
	this.listener = listener;
	this.event = event;
    }

    /** Run thread.
     */
    public void run()
    {
	Pause.pause(pauseDuration);
	listener.actionPerformed(event);
    }

    /** Pause for specified duration and trigger an actionlistener.
     * @param pauseDuration duration to pause in milliseconds
     * @param listener action listener to trigger
     * @param event event to trigger listener with
     */
    public static void trigger(int pauseDuration, ActionListener listener,
				 ActionEvent event)
    {
	ActionListenerTrigger trigger = 
	    new ActionListenerTrigger(pauseDuration, listener, event);
	trigger.start();
    }
}
