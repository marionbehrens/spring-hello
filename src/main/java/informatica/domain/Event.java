package informatica.domain;

import java.util.Date;

public class Event {

	private Long id;
	private Date startDate, endDate;
	private String name;

	public Event(){}
	
	public Event(Long id, Date startDate, Date endDate, String name) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", name=" + name + "]";
	}
	
	

}
