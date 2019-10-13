/**
 * 
 */
package org.intuition_engine.ai.intuition.nlp;

/**
 * @author bhagvan.kommadi
 *
 */
public class NLPProcessor {

	
	public String checkForSynonyms(String context, String concept)
	{

		String synonym = null;
		if(concept.equalsIgnoreCase("movie"))
		{
			synonym = "picture";
		}
		if(synonym != null)
		{
			if(synonym.equalsIgnoreCase(context))
			{
				return synonym;
			}
		}
		
		return null;
	}
	
	public String checkForAntonyms(String context, String concept)
	{
		String antonym = null;
		
		if(context.equalsIgnoreCase("identify"))
		{
			antonym ="unable to watch";
		}
		
		return antonym;
	}
	
}
