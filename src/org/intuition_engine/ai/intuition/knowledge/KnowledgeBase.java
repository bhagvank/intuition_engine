/**
 * 
 */
package org.architectcorner.ai.intuition.knowledge;

import java.util.List;

/**
 * @author bhagvan.kommadi
 *
 */
public class KnowledgeBase {
	
	private List<HumanExperience> humanExperiences;
	/**
	 * @return the humanExperiences
	 */
	public List<HumanExperience> getHumanExperiences() {
		return humanExperiences;
	}
	/**
	 * @param humanExperiences the humanExperiences to set
	 */
	public void setHumanExperiences(List<HumanExperience> humanExperiences) {
		this.humanExperiences = humanExperiences;
	}
	/**
	 * @return the factualKnowledge
	 */
	public List<FactualKnowledge> getFactualKnowledge() {
		return factualKnowledge;
	}
	/**
	 * @param factualKnowledge the factualKnowledge to set
	 */
	public void setFactualKnowledge(List<FactualKnowledge> factualKnowledge) {
		this.factualKnowledge = factualKnowledge;
	}
	private List<FactualKnowledge> factualKnowledge;

}
