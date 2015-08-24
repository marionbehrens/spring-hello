package informatica.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import informatica.domain.Event;

@Service
public class EventServiceImpl implements EventService {

	public List<Event> getAllFutureEvents() {
		List<Event> result = new ArrayList<Event>();

		result.add(new Event(new Date(System.currentTimeMillis() - 24 * 3600 * 1000L), "vor 24 h"));
		result.add(new Event(new Date(System.currentTimeMillis() - 12 * 3600 * 1000L), "vor 12 h"));

		return result;
	}

}
