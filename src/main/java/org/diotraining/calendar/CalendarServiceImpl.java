package org.diotraining.calendar;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalendarServiceImpl implements CalendarService{
    private final CalendarDataStore calendarDataStore;

    public CalendarServiceImpl (CalendarDataStore calendarDataStore){
        this.calendarDataStore = calendarDataStore;
    }

    public void addEvent(Event event) {
        calendarDataStore.addEvent(event);
    }

    public void createEvent(String title, List<Attender> emails) {
//        events.add(new Event.Builder().title(title).attenders(emails).build());
    }

    public List<Event> getEventsByTitle(String title) {
        return calendarDataStore.getEventsByTitle(title);
    }

    public Set<Event> getEventsForSpecificDate(LocalDateTime date) {
        return calendarDataStore.getEventsForSpecificDate(date);
    }

    public List<Calendar[]> checkAvailability(Attender... persons) {
        return null;
    }


}
