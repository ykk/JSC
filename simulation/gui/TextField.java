package simulation.gui;

import java.awt.*;
import javax.swing.*;

/** Basic class with text field and a label.
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
     * @apram size number of columns
     */
    public TextField(String label, int size)
    {
	super(label);
	this.add(textField);
	textField.setColumns(size);
    }
}
