/**
 * 
 */
package org.intuition_engine.ai.mediaresponse.manager;


import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.intuition_engine.ai.mediaresponse.manager.MediaResponseManager;
import org.intuition_engine.ai.mediaresponse.manager.MediaSourceAnalyzer;
import org.intuition_engine.ai.mediaresponse.util.WebApp;
import org.intuition_engine.ai.mediaresponse.sentiment.SentimentAnalyzer;

/**
 * @author bhagvan_kommadi
 *
 */
public class MediaResponseManager {

	 String[] urls = null;
	 String[] keywords = null;
	 String[] sentiments = null;
	 /**
	 * @return the foundContent
	 */
	public Map<String, List<String>> getFoundContent() {
		return foundContent;
	}

	/**
	 * @param foundContent the foundContent to set
	 */
	public void setFoundContent(Map<String, List<String>> foundContent) {
		this.foundContent = foundContent;
	}
	Map<String,List<String>> foundContent = new HashMap<String,List<String>>();
	 List<Map<String,List<String>>> foundSentimentArticles = new ArrayList<Map<String,List<String>>>();
	  public void init(String[] urls,String[] keywords,String[] sentiments)
	  {
		  this.urls = urls;
		  this.keywords = keywords;
		  this.sentiments = sentiments;
	  }
	  
	  public List<Map<String, List<String>>> getFoundSentimentArticles() {
			return foundSentimentArticles;
		}
	  public Map<String,List<String>> getArticles(String url,String[] keywords)
	  {
	        MediaSourceAnalyzer analyzer = new MediaSourceAnalyzer();
		      
	         return analyzer.analyze(url,keywords);
	        
	  }
	  
	  public void analyzeAll()
	  {
		  
		  for(int i=0; i< urls.length; i++)
		  {
			  Map<String,List<String>>  foundArticles = getArticles(urls[i],keywords);
			  
			  System.out.println(foundArticles.size());
			  
			  for(int j=0; j< keywords.length; j++)
			  {
				  String keyword = keywords[j];
				  List<String> foundContentStr = foundContent.get(keyword);
				  
				  if(foundContentStr != null)
				  {
					  List<String> foundArticlesByKeyWord = foundArticles.get(keyword);
					  if(foundArticlesByKeyWord != null)
					  {
					   foundContentStr.addAll(foundArticles.get(keyword));
					   foundContent.put(keyword,foundContentStr);
					  }
				  }
				  else
				  {
					  foundContentStr = new ArrayList<String>();
					  List<String> foundArticlesByKeyWord = foundArticles.get(keyword);
					  if(foundArticlesByKeyWord != null)
					  {
					   foundContentStr.addAll(foundArticles.get(keyword));
					   foundContent.put(keyword,foundContentStr);
					  }
				  }
			  }
		  }
	  }
	  
	  public void checkForSentiments()
	  {
		  SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		  sentimentAnalyzer.init(sentiments);
		  
		  for(int i=0; i< keywords.length; i++)
		  {
			  String keyword = keywords[i];
			  List<String> foundArticles = foundContent.get(keyword);
			  
			  
			  
			  if(foundArticles != null && foundArticles.size() !=0)
			  {
				  System.out.println("keyword "+keyword + " "+foundArticles.size());
				  
				  Map<String,List<String>> foundSentiments = sentimentAnalyzer.getFoundSentiments(foundArticles);
				  
				 // System.out.println("keyword "+ keyword + " sentiments "+foundSentiments.size());
				  foundSentimentArticles.add(foundSentiments);
			  }
		  }
		  
	  }
	  public void writeOutput()
	  {
		  FileWriter output = null;
	        try
	        {
	          output = new FileWriter("/users/bhagvan_kommadi/documents/mediaresponse.txt",false);
	          
	            int index = 0;
	       
	        	for(int j=0; j < keywords.length; j++)
	        	{
	        		
	        		
	        		String keyword = keywords[j];
	        		//output.write("KeyWord "+keyword+ "\n");
	        		
	        	    
	              List<String> content = foundContent.get(keyword);
	              
	              
	             if(content != null)
	             {
	            	 output.write(keyword+" "+content.size() + "\n");
	            	// output.write("Found Sentiment Articles " + foundSentimentArticles.size()+ "\n");
	            	 
	            	 if(index < foundSentimentArticles.size())
	            	 {
	            	 Map<String,List<String>> foundSentiments = foundSentimentArticles.get(index);
	            	 
		               for(int i=0; i< sentiments.length; i++)
		               {
		            	   String sentiment = sentiments[i];
		            	   
		            	   List<String> sentimentArticles = foundSentiments.get(sentiment);
		            	  
		            	   //output.write("Sentiment "+sentiment+"\n");
		            	   
		            	   if(sentimentArticles != null)
		            	   {
		            	   
		            	     output.write(sentiment +" " +sentimentArticles.size()+"\n");
		            	   }
		            	   else
		            	   {
		            		  output.write(sentiment +" 0"+"\n"); 
		            	   }
		               }
		               
		               index ++;
	            	 }
	             }
	             else
	             {
	            	 output.write("Found articles 0" + "\n");
	             }
	             
	             
	        	}
	        	
	        
	        
	        
	       /* if( !output.exists())
	        {
	        	output.createNewFile();
	        }*/
	        }
	        catch(Exception exception)
	        {
	        	exception.printStackTrace();
	        }
	        finally
	        {
	        	if(output != null)
	        	{
	        		try
	        		{
	        		 output.close();
	        		}
	        		catch(Exception exception)
	        		{
	        			exception.printStackTrace();
	        		}
	        	}
	        }
		  
	  }
	  public static void main(String[] args) throws Exception {
	        // TODO code application logic here
	        
		  String[] urls = new String[5];
		  
		  urls[0] = "https://twitrss.me/twitter_user_to_rss/?user=ideacellular";
		  urls[1] = "http://gadgets.ndtv.com/rss/telecom/feeds";
		  urls[2] = "http://feedsrss.com/rss.php?q=Idea+Cellular";
		  urls[3] = "http://feeds.feedburner.com/cellular-news/LmiX";
		/*  urls[4] = "http://treasurytoday.com/feed/latest.xml";
		  urls[5] = "http://www.slate.com/rss";
		  urls[6] = "http://qz.com/feed/";
		  urls[7] = "http://rssfeeds.usatoday.com/usatoday-NewsTopStories";
		  urls[8] = "http://rss.time.com/web/time/rss/top/index.xml";
		  urls[9] = "http://www.washingtonpost.com/rss/homepage";
		  urls[10] = "http://rss.news.yahoo.com/rss/topstories";
		  urls[11] ="http://www.guardian.co.uk/world/usa/rss";*/
		  
		  //String[] htmlurls = new String[3];
		  
		  Vector htmlurls = new Vector();
		 
		//  String url1 = "http://downdetector.in/problems/"+URLEncoder.encode("idea-cellular/news/57678-problems-at-idea-cellular",java.nio.charset.StandardCharsets.UTF_8.toString());
		  //htmlurls.add(new URL(url1));
		  //String url2 = "http://downtoday.in/"+URLEncoder.encode("idea-cellular-network/",java.nio.charset.StandardCharsets.UTF_8.toString());
		  //htmlurls.add(new URL(url2));
		  String url3 = "http://www.complaintboard.in/?search="+URLEncoder.encode("idea cellular",java.nio.charset.StandardCharsets.UTF_8.toString());
		  htmlurls.add(new URL(url3));
		  String url4 = "http://www.isitdownrightnow.com/"+URLEncoder.encode("ideacellular.com.html",java.nio.charset.StandardCharsets.UTF_8.toString());
		  htmlurls.add(new URL(url4));
		  htmlurls.add(new URL("http://www.grahakseva.com/topic/idea-network"));
          htmlurls.add(new URL("https://www.facebook.com/Idea"));
		  
		  String[] keywords = new String[12];
		  
		  keywords[0] = "Idea";
		  keywords[1] = "Network";
		  keywords[2] = "Mobile";
		  keywords[3] = "Internet";
		  keywords[4] = "Prepaid";
		  keywords[5] = "Billing";
		  keywords[6] = "Customer";
		  keywords[7] = "Service";
		  keywords[8] = "Call";
		  keywords[9] = "Connection";
		  keywords[10] = "Landline";
		  keywords[11] = "Topup";
		  
		  
		  String[] sentiment = new String[14];
		  sentiment[0] = "good";
		  sentiment[1] = "bad";
		  sentiment[2] = "poor";
		  sentiment[3] = "spectacular";
		  sentiment[4] = "terrible";
		  sentiment[5] = "loss";
		  sentiment[6] = "fantastic";
		  sentiment[7] = "excellent";
		  sentiment[8] = "down";
		  sentiment[9] = "working";
		  sentiment[10] = "loss";
		  sentiment[11] = "live";
		  sentiment[12] = "problem";
		  sentiment[13] = "issue";
		  
		  String[] places = new String[10];
		  
		  String[] states = new String[10];
		  
		  String[] actions = new String[10];
		  
		  
	       // System.out.println("Media Response Manager");
	        
		  MediaResponseManager responseManager = new MediaResponseManager();
		  responseManager.init(urls, keywords,sentiment);
		  responseManager.analyzeAll();
		  responseManager.checkForSentiments();
		  responseManager.writeOutput();
		  

			WebApp webapp = new WebApp();

			 //System.out.println(urls.toString());

			 //System.out.println(preferences.toString());

			 int iDepth=1;
			Vector preferences = new Vector();
			preferences.add("down");
			preferences.add("loss");
			preferences.add("connecting");
			preferences.add("mobile");
			preferences.add("quality");
			preferences.add("issue");
			preferences.add("working");
			preferences.add("signal");
			preferences.add("available");
			preferences.add("network");
			preferences.add("problem");
			preferences.add("Duplicate");
			preferences.add("poor");
			HashMap content = webapp.searchForContent(htmlurls,preferences,iDepth);

			Set keyUrls = content.keySet();
			Iterator iterator = keyUrls.iterator();
			while(iterator.hasNext())
			{
				URL keyUrl = (URL) iterator.next();
				
				Vector value = (Vector) content.get(keyUrl);
				
				for(int i=0; i< value.size(); i++)
				{
				 System.out.println((String) value.elementAt(i));
				}
			}

	
	    }
}
