package util;

import javax.xml.bind.annotation.XmlAttribute;

public class Resource {

	private String key;
	private String location;
	
	@XmlAttribute
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@XmlAttribute
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
