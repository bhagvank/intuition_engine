/**
 * 
 */
package org.architectcorner.ai.intution.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.architectcorner.ai.intuition.imageprocessing.SubImageRecognizer;
import org.architectcorner.ai.intuition.knowledge.FactualKnowledge;
import org.architectcorner.ai.intuition.knowledge.HumanExperience;
import org.architectcorner.ai.intuition.knowledge.KnowledgeBase;
import org.architectcorner.ai.intuition.knowledge.MediaKnowledge;
import org.architectcorner.ai.intuition.nlp.NLPProcessor;
import org.architectcorner.ai.mediaresponse.manager.MediaResponseManager;
/**
 * @author bhagvan.kommadi
 *
 */
public class IntuitionManager {
    private KnowledgeBase knowledgeBase;
    
    private List<MediaKnowledge> mediaKnowledge;
    
    private List<String> baseImageURLs;
    /**
	 * @return the mediaKnowledge
	 */
	public List<MediaKnowledge> getMediaKnowledge() {
		return mediaKnowledge;
	}

	/**
	 * @param mediaKnowledge the mediaKnowledge to set
	 */
	public void setMediaKnowledge(List<MediaKnowledge> mediaKnowledge) {
		this.mediaKnowledge = mediaKnowledge;
	}
	private List<String> intuitionExperiences;
	
	public void init(KnowledgeBase knowledgeBase)
	{
		this.knowledgeBase = knowledgeBase;
	}
	
	public List<String> getIntuitionRelatedExperiences()
	{
		return intuitionExperiences;
	}
	public List<String> getIntuition(List<String> currentContext)
	{
		List<String> intuition = new ArrayList();
		
		intuitionExperiences = new ArrayList();
		
		
	
		
		for(String contexts: currentContext)
		{
			StringTokenizer tokenizer = new StringTokenizer(contexts," ");
			
			//System.out.println(tokenizer.countTokens());
			
			while(tokenizer.hasMoreElements())
			{
				String context = (String) tokenizer.nextElement();
				
				//System.out.println(context);
				
				
				List<HumanExperience> humanExperiences = knowledgeBase.getHumanExperiences();
				
				processHumanExperiences(humanExperiences,intuition,context);
				
				List<FactualKnowledge> facts = knowledgeBase.getFactualKnowledge();
				processFacts(facts,intuition,context);
				
				
				
			}
			
			processSocialMedia(intuition,contexts);
			
			processContent(intuition,contexts);
		}
		return intuition;
	}
	
	public void processContent(List<String> intuition, String context)
	{
		List<String> urls = new ArrayList();
		for(MediaKnowledge media: mediaKnowledge)
		  {
			  if(media.getMediaType().equals("Image"))
			  {
			   urls.add(media.getUrl());
			  } 
		  }
		
		SubImageRecognizer recognizer = new SubImageRecognizer();
		
		if(baseImageURLs != null)
		{
		    
			recognizer.init(baseImageURLs);
	     	recognizer.recognize(urls);
		}
	}
	
	public void processSocialMedia(List<String> intuition,String context)
	{
		  MediaResponseManager manager = new MediaResponseManager();
			
		  List<String> urls = new ArrayList();
		  for(MediaKnowledge media: mediaKnowledge)
		  {
			  if(media.getMediaType().equals("RSSFeed"))
			  {
			   urls.add(media.getUrl());
			  } 
		  }
		  
		  List<String> keywords = new ArrayList();
		  
		  StringTokenizer tokenizer = new StringTokenizer(context," ");
			
			//System.out.println(tokenizer.countTokens());
			
			while(tokenizer.hasMoreElements())
			{
				String element = (String) tokenizer.nextElement();
		  
				keywords.add(element);
			}	
			
			String[] strings = new String[0];
			
			String[] array = keywords.toArray(strings);
			
		  manager.init(urls.toArray(strings), array, array);
		  manager.analyzeAll();
		  //manager.checkForSentiments();
		
		  
		  Map<String, List<String>> foundContent =  manager.getFoundContent();
		  
		  Iterator<String> iterator = foundContent.keySet().iterator();
		  
		  while(iterator.hasNext())
		  {
			  String key = iterator.next();
		    intuition.addAll( foundContent.get(key));
		  }
	}
	public void processFacts(List<FactualKnowledge> facts,List<String> intuition,String context)
	{
		
		for(FactualKnowledge fact:facts)
		{
		
			
			String factType = fact.getFactType();
			
			//System.out.println(factType);
			
		
				
				List<String>  experiences = fact.getFacts();
				
				processExperiences(experiences,context,intuition);
				
				
				
				
			}
		
	}
	private void processExperiences(List<String> experiences,String context,List<String> intuition)
	{
		NLPProcessor processor = new NLPProcessor();
		for(String concepts: experiences)
		{
			
			StringTokenizer conceptTokenizer = new StringTokenizer(concepts," ");
			
			while(conceptTokenizer.hasMoreElements())
			{
				String concept = (String) conceptTokenizer.nextElement();
				
				
				
				if(concept.equalsIgnoreCase(context))
				{
					//System.out.println(concept);
					if(!intuition.contains(concept))
					{
					  intuition.add(concept);
					  if(!intuitionExperiences.contains(concepts))
					  {
						 // System.out.println(concepts);
					    intuitionExperiences.add(concepts);
					  }
					 // mapIntuits.put(key, intuitConcepts);
					}
				}
				
				 
					String synonym = processor.checkForSynonyms(context,concept);
				    String anotonym = processor.checkForAntonyms(context,concept);
				    
				    if(synonym != null)
				    {
				    	if(!intuition.contains(synonym))
				    	{
				          intuition.add(synonym);
				          
				          if(!intuitionExperiences.contains(concepts))
						  {
						    intuitionExperiences.add(concepts);
						  }
						//  mapIntuits.put(key, intuitConcepts);
				    	}
				    }
				    
				    if(anotonym != null)
				    {
				    	if(!intuition.contains(anotonym))
				    	{
				          intuition.add(anotonym);
				          if(!intuitionExperiences.contains(concepts))
						  {
						    intuitionExperiences.add(concepts);
						  }
						  //mapIntuits.put(key, intuitConcepts);
				    	}
				    }
			}
		}
	}
	
	public void processHumanExperiences(List<HumanExperience> humanExperiences,List<String> intuition,String context)
	{
		for(HumanExperience experience:humanExperiences)
		{
		
			
			String date = experience.getDate();
			
			//System.out.println(keys);
			
		
				
				List<String>  experiences = experience.getExperiences();
				
				//System.out.println(knowledge);
				
				processExperiences(experiences,context,intuition);
				
				
			}
		
	     }
	
	
	    public void setBaseImageURLs(List<String> baseImageURLs)
	    {
	      this.baseImageURLs = baseImageURLs;	
	    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List<String> experiences = new ArrayList<String>();
		 experiences.add("I am watching PompeII movie");
		 experiences.add("I watched Test Cricket India vs NewsZealand Fourth day");
		 
		 HumanExperience humanExperience = new HumanExperience();
		 humanExperience.setDate("Oct 2");
		 humanExperience.setExperiences(experiences);
		 
		 
		 List<HumanExperience> humanExperiences = new ArrayList();
		 humanExperiences.add(humanExperience);
		 
		 List<String> movieKnowledge = new ArrayList<String>();
		 movieKnowledge.add("Hollywood movies are made in USA");
		 movieKnowledge.add("PompeII is a movie made in 2014");
		 
		 List<String> cricketKnowledge = new ArrayList<String>();
		 cricketKnowledge.add("Test Cricket started in England");
		 cricketKnowledge.add("Indian Cricket captain is Virat kohli in 2016");
		// System.out.println(experiences);
		 
		FactualKnowledge movieFacts = new FactualKnowledge();
		movieFacts.setFactType("MovieFacts");
		movieFacts.setFacts(movieKnowledge);
		
		FactualKnowledge cricketFacts = new FactualKnowledge();
		cricketFacts.setFactType("CricketFacts");
		cricketFacts.setFacts(cricketKnowledge);
		
		List<FactualKnowledge> factualKnowledge = new ArrayList();
		factualKnowledge.add(movieFacts);
		factualKnowledge.add(cricketFacts);
		 
		 KnowledgeBase knowledgeBase = new KnowledgeBase();
		 knowledgeBase.setHumanExperiences(humanExperiences);
		 knowledgeBase.setFactualKnowledge(factualKnowledge);
		
		 
		 IntuitionManager manager = new IntuitionManager();
		 manager.init(knowledgeBase);
		 
		 
		 MediaKnowledge mediaKnowledge = new MediaKnowledge();
		 mediaKnowledge.setMediaType("RSSFeed");
		 mediaKnowledge.setUrl("http://www.espncricinfo.com/rss/content/story/feeds/0.xml");
	     mediaKnowledge.setMediaInformationOwner("ESPNCRICINFO");
	     
	     
	     MediaKnowledge cricketContent = new MediaKnowledge();
	     cricketContent.setMediaType("Image");
	     cricketContent.setUrl("http://shoptrics.com/image/data/ss/SS%20Premium%20English%20Willow%20Cricket%20Bat2.jpg");
	     cricketContent.setMediaInformationOwner("SHOPTRICS");
	     
	     
	     List<String> baseURLs = new ArrayList();
			
			baseURLs.add("http://4.imimg.com/data4/UR/UX/MY-17997158/cricket-bat-250x250.jpg");
			

	     List<MediaKnowledge> mediaSources = new ArrayList();
	     mediaSources.add(mediaKnowledge);
	     mediaSources.add(cricketContent);
	     manager.setMediaKnowledge(mediaSources);
	     
		 List<String> currentContext = new ArrayList();
		 currentContext.add("I am seeing second day of Cricket Match where our Indian skipper is playing");
		 
		 System.out.println(currentContext);
		 
		 manager.setBaseImageURLs(baseURLs);
		 
		 List<String> intuition = manager.getIntuition(currentContext);
		 
		 
		 
		 for(String intuit:intuition)
		 {
			 System.out.println(intuit);
		 }
		 
		List<String> intuitExperiences = manager.getIntuitionRelatedExperiences();
		 
		 for(String experience:intuitExperiences)
		 {
			 
				 
				 System.out.println(experience);
		 }
	}

}
