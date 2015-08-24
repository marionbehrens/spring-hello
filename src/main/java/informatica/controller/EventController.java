package informatica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import informatica.domain.Event;
import informatica.service.EventService;

@Controller
public class EventController {

	@Autowired
	private EventService eventService;


	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World! " + System.currentTimeMillis();
    }

	@RequestMapping("/events")
    @ResponseBody
	List<Event> getAllEvents() {
		return this.eventService.getAllFutureEvents();
	}
}
