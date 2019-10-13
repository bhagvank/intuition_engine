/**
 * 
 */
package org.intuition_engine.ai.intuition.knowledge;

/**
 * @author bhagvan.kommadi
 *
 */
public class MediaKnowledge {
	
	private String mediaType;
	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}
	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the mediaInformationOwner
	 */
	public String getMediaInformationOwner() {
		return mediaInformationOwner;
	}
	/**
	 * @param mediaInformationOwner the mediaInformationOwner to set
	 */
	public void setMediaInformationOwner(String mediaInformationOwner) {
		this.mediaInformationOwner = mediaInformationOwner;
	}
	private String url;
	private String mediaInformationOwner;

}
