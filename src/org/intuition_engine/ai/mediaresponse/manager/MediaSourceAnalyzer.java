/**
 * 
 */
package org.intuition_engine.ai.mediaresponse.manager;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author bhagvan_kommadi
 *
 */
public class MediaSourceAnalyzer {
    
    public Map<String,List<String>> analyze(String url,String[] keywords)
    {
    	
       Map<String,List<String>> foundContent = new HashMap();
        try
        {
        
            URL feedUrl = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));
                
                List entryList = feed.getEntries();

               // System.out.println(feed);
                for(int i=0; i< entryList.size();i++)
                {
                    SyndEntry entry = (SyndEntry) entryList.get(i);
                    
                 // System.out.println("Title" + entry.getTitle());
                    //System.out.println("URI " + entry.getUri());
                   // System.out.println(entry.getContents());
              
                    //System.out.println(entry.getDescription());
                    
                    List<SyndContent> syndContentList = entry.getContents();
                    
                    if(syndContentList.isEmpty())
                    {
                         SyndContent syndContent = entry.getDescription();	
                         
                         String content = syndContent.getValue();
                     	
                         for(int j=0; j< keywords.length; j++)
                         {
                         	
                         	if(content.contains(keywords[j]))
                         	{
                         		List<String> keyWordContent = foundContent.get(keywords[j]);
                         		
                         		if(keyWordContent == null)
                         		{
                         			keyWordContent = new ArrayList<String>();
                         			keyWordContent.add(content);
                         			foundContent.put(keywords[j],keyWordContent);
                         			
                         			//System.out.println(content);
                         		}
                         		else
                         		{
                         		   keyWordContent.add(content);
                         		   
                         		   foundContent.put(keywords[j],keyWordContent);
                         		  //System.out.println(content);
                         		   
                         		   
                         		}
                         		
                         				
                         	}
                         }
                    }
                    else
                    {
	                    for(SyndContent syndContent:syndContentList)
	                    {
	                    	String content = syndContent.getValue();
	                    	
	                    for(int j=0; j< keywords.length; j++)
	                    {
	                    	String keyword = keywords[j];
	                    	
	                    	if(keyword.contains("&"))
	                    	{
	                    		keyword.replace("&", "&amp;");
	                    	}
	                    	
	                    	if(content.contains(keyword))
	                    	{
	                    		List<String> keyWordContent = foundContent.get(keywords[j]);
	                    		
	                    		if(keyWordContent == null)
	                    		{
	                    			keyWordContent = new ArrayList<String>();
	                    			keyWordContent.add(content);
	                    			foundContent.put(keywords[j],keyWordContent);
	                    			
	                    			//System.out.println(content);
	                    		}
	                    		else
	                    		{
	                    		   keyWordContent.add(content);
	                    		   
	                    		   foundContent.put(keywords[j],keyWordContent);
	                    		  //System.out.println(content);
	                    		   
	                    		   
	                    		}
	                    		
	                    				
	                    	}
	                    }
	                    }
	                     //List<SyndEntry> syndEntryList = entry.getContents();
	                     
	                    /* for(SyndEntry syndEntry: syndEntryList)
	                     {
	                    	 
	                    	      //syndEntry.toString();
	                     }*/
                    }   
                }
        }
        catch(Exception exception)
        {
            //exception.printStackTrace();
        }
        
       /* for(String foundStr:foundContent)
        {
        	//System.out.println(foundStr);
        }*/
        
        return foundContent;
    }
}
