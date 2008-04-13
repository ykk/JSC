package simulation.socialnet;

import java.io.*;
import java.net.*;
import java.util.*;

/** Class representing a webpage.
 * @author ykk
 */

public class WebPage
{
    //Members
    /** Vector holding webpage.
     */
    public Vector content = null;
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
	if (content == null)
	    content = new Vector();
	else
	    content.clear();
	
	try
	{
	    String str;
	    InputStream stream = url.openStream();
	    DataInputStream dstream = 
		new DataInputStream(new BufferedInputStream(stream));
	    while ((str = dstream.readLine()) != null)
		content.add(str);
	} catch (IOException err)
	{
	    System.err.println(err);
	}
    }

    /** Print webpage content.
     * A very simple wget.
     * @param args 1st string is url.
     */
    public static void main(String[] args)
    {
	WebPage page = new WebPage(args[0]);
	page.get();

	for (int i = 0; i < page.content.size(); i++)
	    System.out.println(page.content.get(i));
	
    }
}