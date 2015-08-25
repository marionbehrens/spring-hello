package informatica.controller;

import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import informatica.domain.Event;
import informatica.service.EventService;
import informatica.service.EventServiceImpl;

@RestController
public class EventController {

	private EventService eventService = new EventServiceImpl();

	@RequestMapping("/event")
	public Event getEventByName(@RequestParam(value = "name") String name) {
		if (name != null && !name.isEmpty()) {
			return this.eventService.getEventByName(name);
		}
		return null;
	}

	@RequestMapping("/event/{name}")
	public Event getEventByNameFromPath(@PathVariable String name) {
		if (name != null && !name.isEmpty()) {
			return this.eventService.getEventByName(name);
		}
		return null;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addEvent(@RequestBody Event event) {
		if (event != null)
		{
			this.eventService.addEvent(event);
			System.out.println("POST: " + event);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteEvent(@RequestParam(value = "id") Long id) {
		if (id != null) {
			this.eventService.deleteEventById(id);
		}
	}

	@RequestMapping("/")
    String home() {
        return "Hello World! " + System.currentTimeMillis();
    }

	@RequestMapping("/events")
	List<Event> getAllEvents() {
		return this.eventService.getAllFutureEvents();
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
}
