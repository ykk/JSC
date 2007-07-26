package simulation.gui;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/** Basic class with text field and a label.
 * @author ykk
 */
public class ComboBox
    extends Label
{
    //Members
    /** Reference to text field.
     */
    public JComboBox comboBox;
    
    //Methods
    /** Constructor.
     * @param label label text
     * @param items vector of items in combo box
     */
    public ComboBox(String label, Vector items)
    {
	super(label);
	comboBox = new JComboBox(items);
	this.add(comboBox);
    }
}
