package informatica.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import informatica.domain.Event;

@Service
@Primary
public class EventServiceImpl implements EventService {

	private Map<Long, Event> eventsById;
	  
	private final AtomicLong counter;
	
	public EventServiceImpl()
	{
		this.counter = new AtomicLong();
		this.counter.set(1L);
		this.eventsById = new HashMap<Long, Event>();
		
		long id = counter.getAndIncrement();
		this.eventsById.put(id, new Event(id, new Date(System.currentTimeMillis() - 7 * 24 * 3600 * 1000L), 
				 							  new Date(System.currentTimeMillis() - 6 * 24 * 3600 * 1000L), 
				 							  "last week"));
		id = counter.getAndIncrement();
		this.eventsById.put(id, new Event(id, new Date(System.currentTimeMillis()), 
				 							  new Date(System.currentTimeMillis() + 24 * 3600 * 1000L), 
				 							  "this week"));
		id = counter.getAndIncrement();
		this.eventsById.put(id, new Event(id, new Date(System.currentTimeMillis() + 7 * 24 * 3600 * 1000L), 
				 							  new Date(System.currentTimeMillis() + 8 * 24 * 3600 * 1000L), 
				 							  "next week"));
		id = counter.getAndIncrement();
		this.eventsById.put(id, new Event(id, new Date(System.currentTimeMillis() + 14 * 24 * 3600 * 1000L), 
				 							  new Date(System.currentTimeMillis() + 15 * 24 * 3600 * 1000L), 
				 							  "next-next week"));
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
		// event.setId(newId);
		this.eventsById.put(newId, event);
	}

}
