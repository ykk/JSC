package simulation.socialnet;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

/** Class representing a webpage.
 * @author ykk
 */

public class WebPage
{
    //Members
    /** String buffer holding webpage.
     */
    public String content = null;
    /** String holding URL.
     */
    public URL url = null;

    //Methods
    /** Constructor.
     */
    public WebPage()
    {
    }

    /** Constructor.
     * @param url string containing url
     */
    public WebPage(String url)
    {
	setUrl(url);
    }

    /** Input url.
     * @param url string containing url
     */
    public void setUrl(String url)
    {
	try
	{
	    this.url = new URL(url);
	} catch (MalformedURLException err)
	{
	    System.err.println(err);
	}
    }

    /** Retrieve webpage from the web.
     * @return string containing webpage
     */
    public void get()
    {
	StringBuffer sb = new StringBuffer();	

	try
	{
	    String str;
	    InputStream stream = url.openStream();
	    DataInputStream dstream = 
		new DataInputStream(new BufferedInputStream(stream));
	    while ((str = dstream.readLine()) != null)
		sb.append(str+"\n");
	} catch (IOException err)
	{
	    System.err.println(err);
	}
	content = sb.toString();
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

    /** Parse away HTML tags.
     */
    public void parseHTML()
    {
	remove("\\<.*?>");
    }

    /** Parse non-alphanumeric.
     */
    public void parseNonAlphaNumeric()
    {
	remove("[^0-9a-zA-Z ]");
    }

    /** Print webpage content.
     * A very simple wget.
     * @param args 1st string is url.
     */
    public static void main(String[] args)
    {
	WebPage page = new WebPage(args[0]);
	page.get();
	page.parseSpace();
	page.parseHTML();
	System.out.println(page.content);
    }
}