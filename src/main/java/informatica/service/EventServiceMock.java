package informatica.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import informatica.domain.Event;

@Service
public class EventServiceMock implements EventService {
	
	private Map<Long, Event> eventsById;
	  
	private final AtomicLong counter;
	
	public EventServiceMock()
	{
		this.counter = new AtomicLong();
		this.eventsById = new HashMap<Long, Event>();

		long id = counter.getAndIncrement();
		this.eventsById.put(id, new Event(id, new Date(System.currentTimeMillis() - 24 * 3600 * 1000L), 
											  new Date(System.currentTimeMillis() - 23 * 3600 * 1000L), 
											  "yesterday"));

		id = counter.getAndIncrement();
		this.eventsById.put(id, new Event(id, new Date(System.currentTimeMillis() + 24 * 3600 * 1000L), 
											  new Date(System.currentTimeMillis() + 25 * 3600 * 1000L), 
											  "tomorrow"));
		id = counter.getAndIncrement();
		this.eventsById.put(id, new Event(id, new Date(System.currentTimeMillis() + 48 * 3600 * 1000L), 
				 				 			  new Date(System.currentTimeMillis() + 49 * 3600 * 1000L), 
				 				 			  "day after tomorrow"));
	}

	public List<Event> getAllFutureEvents() {
		List<Event> result = new ArrayList<Event>();
		Date now = new Date();
		for (Event event : this.eventsById.values()) {
			if (now.before(event.getStartDate())) {
				result.add(event);
			}
		}
		return result;
	}
	
	public Event getEventByName(String name)
	{
		for (Event event : this.eventsById.values()) {
			if (name.equals(event.getName())) {
				return event;
			}
		}
		return null;
	}
	
	public void deleteEventById(Long id)
	{
		this.eventsById.remove(id);
	}

	public void addEvent(Event event) {
		long newId = counter.getAndIncrement();
		event.setId(newId);
		this.eventsById.put(newId, event);
	}

}
