package simulation.gui;

import java.awt.*;
import javax.swing.*;

/** Basic class with text field and a label.
 * Limits maximum length if specified.
 * @see LimitDocument
 * @author ykk
 */
public class TextField
    extends Label
{
    //Members
    /** Reference to text field.
     */
    public JTextField textField = new JTextField();
    
    //Methods
    /** Constructor.
     * @param label label text
     * @param size number of columns
     * @param maxlen maximum length
     */
    public TextField(String label, int size, int maxlen)
    {
	super(label);
	if (maxlen != 0)
	    textField.setDocument(new LimitDocument(maxlen));
	textField.setColumns(size);
	this.add(textField);
    }

    /** Constructor.
     * @param label label text
     * @param size number of columns
     */
    public TextField(String label, int size)
    {
	this(label, size, 0);
    }
}
