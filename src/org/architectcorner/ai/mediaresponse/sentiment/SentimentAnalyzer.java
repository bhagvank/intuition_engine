/**
 * 
 */
package org.architectcorner.ai.mediaresponse.sentiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bhagvan_kommadi
 *
 */
public class SentimentAnalyzer {

	private String[] sentiments = null;
	
	public void init(String[] sentiments)
	{
		this.sentiments = sentiments;
	}
	
	public Map<String,List<String>> getFoundSentiments(String nonSpeech)
	{
		//TODO Map should have Integer occurrences
		Map<String,List<String>> noOccurrences = new HashMap();
		
		
		//List<String> foundSentiments = new ArrayList<String>();
		for(String sentiment: sentiments)
		{
			if(nonSpeech.contains(sentiment))
			{
				List<String> foundSentiments = noOccurrences.get(sentiment);
				
				if(foundSentiments == null)
				{
					foundSentiments = new ArrayList<String>();
					
				}
				foundSentiments.add(sentiment);
				noOccurrences.put(sentiment, foundSentiments);
			}
		}
		
		return noOccurrences;
	}
	public Map<String,List<String>> getFoundSentiments(List<String> foundArticles)
	{
		Map<String,List<String>> foundSentiments = new HashMap<String,List<String>>();
		
		System.out.println(foundArticles.size());
		for(int i=0; i < sentiments.length; i++)
		{
			String sentiment = sentiments[i];
			List<String> foundArticleSentiments = foundSentiments.get(sentiment);
			
			if(foundArticleSentiments != null)
			{
				for(String article: foundArticles)
				{
					System.out.println(article);
					if(article.contains(sentiment))
					{
						foundArticleSentiments.add(article);
						
						System.out.println("sentiment " + sentiment);
						foundSentiments.put(sentiment,foundArticleSentiments);
					}
				}
			}
			else
			{
				foundArticleSentiments = new ArrayList<String>();
				
				
				for(String article: foundArticles)
				{
					//System.out.println(article);
					if(article.contains(sentiment))
					{
						foundArticleSentiments.add(article);
						
						//System.out.println("sentiment " + sentiment);
						foundSentiments.put(sentiment,foundArticleSentiments);
					}
				}
				foundSentiments.put(sentiment, foundArticleSentiments);
				
				
				
			}
			
			
		}
		  //System.out.println(foundSentiments.size());
		return foundSentiments;
	}
}
