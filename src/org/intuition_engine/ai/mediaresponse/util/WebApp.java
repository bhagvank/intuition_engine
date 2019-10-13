/**
 * 
 */
package org.intuition_engine.ai.mediaresponse.util;

/**
 * @author bhagvan_kommadi
 *
 */

import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.text.html.*;

import org.intuition_engine.ai.mediaresponse.util.HTMLUtil;

import javax.swing.text.*;

public class WebApp
{

  private Document doc;

  private HTMLEditorKit kit;

  public static Writer out = new PrintWriter(System.out);

  public HashMap searchForContent(Vector urls,
  			                        Vector preferences,
  			                        int depth
  			                        )

   {



     HashMap contentURLs = new HashMap();

     for(int i=0; i < urls.size(); i++)
     {

       URL url = (URL) urls.elementAt(i);


       HashMap contentFound= searchForContent((URL) urls.elementAt(i),preferences,depth);


       contentURLs.putAll(contentFound);




     }

     return contentURLs;


   }



   private HashMap searchForContent(URL url,
   								  Vector preferences,
   								  int depth)

   	{

       try
       {

        System.out.println(url.toString() + "***********"+ depth);


		HashMap content = new HashMap();



         Vector vector = searchForContent(url,preferences);

         if(vector != null)
         {

		   content.put( url,vector);

	     }

	     depth = depth-1;

         if(depth > 0)
         {

		  Vector urls = HTMLUtil.getURLs(url);

		  for(int i=0; i < urls.size(); i++)
		  {
		    content.putAll(searchForContent((URL) urls.elementAt(i),preferences,depth-1));
	      }

	     }

         return content;
	    }
	    catch(Exception exception)
	    {
			return null;
		}

	}


	private Vector searchForContent(URL url,
	                                Vector preferences)


   	{

      try
      {
    	 
       Reader reader = new BufferedReader(new InputStreamReader(url.openStream()));

       StreamTokenizer tokenizer = new StreamTokenizer(reader);

 	   Vector contentFound = new Vector();


       while(tokenizer.nextToken() != tokenizer.TT_EOF)
       {


		   if( tokenizer.ttype==tokenizer.TT_WORD && preferences.contains(tokenizer.sval))
		    {
                System.out.println("------------- found a link ------------"+tokenizer.sval);
				contentFound.add(tokenizer.sval);

				break;

			}
		   else
		   {
			  // tokenizer.tt
		   }

	   }


		return contentFound;

	  } catch(Exception e) { e.printStackTrace(); return null;}


	}





}