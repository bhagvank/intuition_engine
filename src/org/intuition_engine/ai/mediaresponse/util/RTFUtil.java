/**
 * 
 */
package org.intuition_engine.ai.mediaresponse.util;

/**
 * @author bhagvan_kommadi
 *
 */
import javax.swing.text.rtf.*;
import javax.swing.text.*;
import java.io.*;

public class RTFUtil
{

  public String getTextFromRTF(String filePath)
  {

   try {
   	        RTFEditorKit rtf = new RTFEditorKit();
   	        Document doc = rtf.createDefaultDocument();
   	        BufferedReader input = new BufferedReader(new FileReader(filePath));
   	        rtf.read(input,doc,0);
   	        input.close();
   	        return doc.getText(0,doc.getLength());
   	     } catch (Exception e) {
   	        e.printStackTrace();
   	     }

      return null;

  }


}
