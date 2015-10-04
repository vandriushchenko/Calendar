package org.diotraining.calendar;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class CalendarServiceImpl implements CalendarService{
    private Set<Event> events = new HashSet<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public void createEvent(String title, List<Person> emails) {
        events.add(new Event.Builder().title(title).attenders(emails).build());
    }

    public Event searchEvent(String title) {
        for (Event event:events){
            if(event.getTitle().equals(title))
                return event;
        }
        return null;
    }

    public Set<Event> search(LocalDateTime date) {
        return events.stream().filter(event -> date.compareTo(event.getStartDate()) >= 0 && date.compareTo(event.getEndDate()) <= 0).collect(Collectors.toSet());
    }

    public List<Calendar[]> checkAvailability(Person... persons) {
        return null;
    }


}
