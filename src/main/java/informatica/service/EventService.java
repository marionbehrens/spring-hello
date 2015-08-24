package informatica.service;

import java.util.List;

import informatica.domain.Event;

public interface EventService {
	
	public List<Event> getAllFutureEvents();

}
