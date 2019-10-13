/**
 * 
 */
package org.intuition_engine.ai.intuition.knowledge;

import java.util.List;

/**
 * @author bhagvan.kommadi
 *
 */
public class FactualKnowledge {
	
	private String factType;
	/**
	 * @return the factType
	 */
	public String getFactType() {
		return factType;
	}
	/**
	 * @param factType the factType to set
	 */
	public void setFactType(String factType) {
		this.factType = factType;
	}
	/**
	 * @return the facts
	 */
	public List<String> getFacts() {
		return facts;
	}
	/**
	 * @param facts the facts to set
	 */
	public void setFacts(List<String> facts) {
		this.facts = facts;
	}
	private List<String> facts;

}
