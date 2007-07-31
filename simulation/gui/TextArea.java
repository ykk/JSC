package simulation.gui;

import java.awt.*;
import javax.swing.*;

/** Basic class with text area and a label.
 * Limits maximum length if specified.
 * @see LimitDocument
 * @author ykk
 */
public class TextArea
    extends Label
{
    //Members
    /** Reference to text field.
     */
    public JTextArea textArea = new JTextArea();

    //Methods
    /** Constructor.
     * @param label label text
     * @param rows number of rows
     * @param columns number of columns
     * @param maxlen maximum length
     */
    public TextArea(String label, int rows, int columns, int maxlen)
    {
	super(label);
	if (maxlen != 0)
	    textArea.setDocument(new LimitDocument(maxlen));
	textArea.setColumns(columns);
	textArea.setRows(rows);
	textArea.setLineWrap(true);
	this.add(textArea);
    }
}
