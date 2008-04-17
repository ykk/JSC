package simulation.socialnet;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import simulation.utilities.structures.*;

/** Class representing a webpage.
 * @author ykk
 */

public class WebPage
    extends HTMLString
{
    //Members
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

    /** Constructor.
     * @param url string containing url (can input null)
     * @param content content
     */
    public WebPage(String url, String content)
    {
	if (url != null)
	    setUrl(url);
	this.content = content;
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