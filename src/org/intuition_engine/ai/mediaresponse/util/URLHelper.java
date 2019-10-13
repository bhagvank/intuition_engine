/**
 * 
 */
package org.intuition_engine.ai.mediaresponse.util;

/**
 * @author bhagvan_kommadi
 *
 */

import java.net.*;

public class URLHelper
{




 public static URL createURL(String str, URL parentURL) {

       try {


		   if(str.startsWith("#") || str.startsWith("/")) {

 			URL url = new URL(new StringBuffer(parentURL.toString()).append(str).toString());

 			return url;

 		} else if( str.startsWith("https")) { // to be handled

 					return null;

 		} else if( str.startsWith("http")) {

 			URL url = new URL(str);

 			return url;

 		}

 		else if(str.startsWith("ftp")) { // to be handled


 		return null; }
 		else if(str.startsWith("javascript")) { // to be handled

 			return null;

 		} } catch(Exception e) {

 			 e.printStackTrace();

 			 return null; }

 		 return null;

	}






}
