package simulation.socialnet;

import java.io.*;
import java.net.*;
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

    /** Parse white spaces.
     */
    public void parseSpace()
    {
	Pattern removeSpace = Pattern.compile("\\s");
	content = removeSpace.matcher(content).replaceAll(" ");
    }

    /** Parse away HTML tags.
     */
    public void parseHTML()
    {
	Pattern removeHTML = Pattern.compile("\\<.*?>");
	content = removeHTML.matcher(content).replaceAll("");
    }

    /** Parse non-alphanumeric.
     */
    public void parseNonAlphaNumeric()
    {
	Pattern getAlphaNumeric = Pattern.compile("[^0-9a-zA-Z ]");
	content = getAlphaNumeric.matcher(content).replaceAll("");
    }

    /** Print webpage content.
     * A very simple wget.
     * @param args 1st string is url.
     */
    public static void main(String[] args)
    {
	WebPage page = new WebPage(args[0]);
	page.get();
	System.out.println(page.content);
    }
}