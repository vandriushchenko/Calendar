package org.diotraining.calendar;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public interface CalendarService {

    void addEvent(Event event);

    List<Event> getEventsByTitle(String title);

    Set<Event> getEventsForSpecificDate(LocalDateTime date);

    void createEvent(String title, String description, List<Attender> attenders, LocalDateTime startDate, LocalDateTime endDate);

    List<Calendar[]> checkAvailability(Attender... persons);
}
