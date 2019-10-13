/**
 * 
 */
package org.intuition_engine.ai.mediaresponse.util;

/**
 * @author bhagvan_kommadi
 *
 */

import javax.swing.text.*;
import javax.swing.text.html.*;

import org.intuition_engine.ai.mediaresponse.util.URLHelper;

import java.net.*;
import java.io.*;
import java.util.*;


public class HTMLUtil
{

 private static Document doc;

 private static HTMLEditorKit kit;


 public static Writer out = new PrintWriter(System.out);
 


 public static Vector getURLs(URL url)
	{

		Vector urls = new Vector();

		try
		{
		Reader reader = new BufferedReader(new InputStreamReader(url.openStream()));

		Document doc = getHTMLDocument(reader);

		ElementIterator it = new ElementIterator(doc);

		javax.swing.text.Element elem;



		while ((elem = it.next()) != null)
		{


		  SimpleAttributeSet s = (SimpleAttributeSet)
		  elem.getAttributes().getAttribute(HTML.Tag.A);
		  if (s != null)
		  {

			     if(s.getAttribute(HTML.Attribute.HREF) != null)
			     {
		          System.out.println(
		            s.getAttribute(HTML.Attribute.HREF).toString());
			     }
                 if(s.getAttribute(HTML.Attribute.HREF) != null)
                 {

				  String urlString = s.getAttribute(HTML.Attribute.HREF).toString();


				   URL createdURL = URLHelper.createURL(urlString,url);

				   if( createdURL != null)
		            {
		             urls.add(createdURL);
				    }

			     }
		  }
		}



	     } catch(Exception e) {

			 e.printStackTrace();


		 }


		 return urls;



	}



	private static Document getHTMLDocument(Reader reader)
	{



        Document doc = getDefaultDocument();


        try
		{

		  kit.read(reader,doc,0);

		  return doc;

		} catch(Exception e)
		  {
			  e.printStackTrace();

			  return null;

		  }


	}

	private static Document getDefaultDocument()
	{
		if(doc == null)
		{

		  kit = new HTMLEditorKit();

	      doc = kit.createDefaultDocument();


		  doc.putProperty("IgnoreCharsetDirective",
				                Boolean.TRUE);

		}

		return doc;


	}










}
