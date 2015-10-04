package org.diotraining.calendar;

import java.util.Calendar;
import java.util.List;

public interface CalendarService {

    void addEvent(Event event);

    void createEvent(String title, List<String> emails);

    Event searchEvent(String title);

    List<Event> search(Calendar date);

    List<Calendar[]> checkAvailability(Person ... persons);
}
