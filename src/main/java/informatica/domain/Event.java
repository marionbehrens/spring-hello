package informatica.domain;

import java.util.Date;

public class Event {
	
	private Date date;
	private String description;
	
	public Event(Date date, String description) {
		this.date = date;
		this.description = description;
	}
	public String getDate() {
		return date.toString();
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
