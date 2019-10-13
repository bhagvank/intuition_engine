/**
 * 
 */
package org.intuition_engine.ai.intuition.knowledge;

import java.util.List;

/**
 * @author bhagvan.kommadi
 *
 */
public class HumanExperience {

	 private String date;
	 /**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the experiences
	 */
	public List<String> getExperiences() {
		return experiences;
	}
	/**
	 * @param experiences the experiences to set
	 */
	public void setExperiences(List<String> experiences) {
		this.experiences = experiences;
	}
	private List<String> experiences;
}
