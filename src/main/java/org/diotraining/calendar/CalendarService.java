package org.diotraining.calendar;

import java.util.Calendar;
import java.util.List;

public interface CalendarService {

    public void addEvent(Event event);

    public void createEvent(String title, List<String> emails);

    public Event searchEvent(String title);

    public List<Event> search(Calendar date);

    public List<Calendar[]> checkAvailability(Person ... persons);
}
