package simulation.utilities.structures;

import java.util.*;
import java.util.regex.*;

/** Class representing a string for regular expression processing.
 * @author ykk
 */

public class RegexString
{
    //Members
    /** String buffer holding content.
     */
    public String content = null;

    //Methods
    /** Constructor.
     */
    public RegexString()
    {
    }

    /** Constructor.
     * @param content content
     */
    public RegexString(String content)
    {
	this.content = content;
    }

    /** Replace regular expression.
     * @param regex regular expression
     * @param replaceStr string to replace with
     */
    public void replace(String regex, String replaceStr)
    {
	Pattern pattern = Pattern.compile(regex);
	content = pattern.matcher(content).replaceAll(replaceStr);
    }
    
    /** Remove regular expression.
     * @param regex regular expression
     */
    public void remove(String regex)
    {
	replace(regex, "");
    }


    /** Find if there is match(es) for regular expression.
     * @param regex regular expression
     * @return if match(es) exist
     */
    public boolean hasMatch(String regex)
    {
	Pattern pattern = Pattern.compile(regex);
	return pattern.matcher(content).find();
    }

    /** Find matches of regular expression.
     * @param regex regular expression
     * @return vector of strings of matches
     */
    public Vector matches(String regex)
    {
	Vector match = new Vector();
	Pattern pattern = Pattern.compile(regex);
	Matcher matched = pattern.matcher(content);

	while (matched.find())
	    match.add(matched.group());
	return match;
    }

    /** Grab matches of regular expression.
     * @param regex regular expression
     * @param divider expression to divide matches
     */
    public void grab(String regex, String divider)
    {
	Vector matches = matches(regex);
	content = new String();
	for (int i = 0 ; i < matches.size(); i++)
	    content += (String) matches.get(i) + divider;
    }

    /** Parse white spaces.
     */
    public void parseSpace()
    {
	replace("\\s"," ");
    }

    /** Parse multiple white spaces.
     */
    public void parseMultiSpace()
    {
	replace("[ ]+"," ");
    }


    /** Parse non-alphanumeric.
     */
    public void parseNonAlphaNumeric()
    {
	remove("[^0-9a-zA-Z ]");
    }
}