package informatica.service;

import java.util.List;

import informatica.domain.Event;

public interface EventService {
	
	public List<Event> getAllFutureEvents();
	
	public Event getEventByName(String name);
	
	public void deleteEventById(Long id);

	public void addEvent(Event event);

}
