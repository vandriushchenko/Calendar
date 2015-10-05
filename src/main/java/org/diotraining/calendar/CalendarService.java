package org.diotraining.calendar;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public interface CalendarService {

    void addEvent(Event event);

    void createEvent(String title, List<Attender> emails);

    List<Event> getEventsByTitle(String title);

    Set<Event> getEventsForSpecificDate(LocalDateTime date);

    List<Calendar[]> checkAvailability(Attender ... persons);
}
