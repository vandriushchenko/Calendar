package org.diotraining.calendar;

import java.util.*;
import java.util.stream.Collectors;

public class CalendarDataStore {
    private Map<UUID, List<String>> idWithEmails = new HashMap<>();
    private Map<String, List<UUID>> titleWithId = new HashMap<>();
    private Map<UUID, Event> events = new HashMap<>();


    public void addEvent(Event event) {
        events.put(event.getId(), event);
        addTitleMapping(event);
        addEmailsMapping(event);
    }

    public List<Event> getEventsByTitle(String title) {
        List<UUID> ids = titleWithId.get(title);  // получаем title
        List<Event> eventsByTitle = new ArrayList<>(); // временные
        for (UUID uuid : ids) {
            eventsByTitle.add(events.get(uuid));
        }
        return eventsByTitle;

    }

    private void addTitleMapping(Event event) {
        if (titleWithId.containsKey(event.getTitle())) {
            List<UUID> id = new ArrayList<>(titleWithId.get(event.getTitle()));
            id.add(event.getId());
            titleWithId.replace(event.getTitle(), id);
        } else {
            List<UUID> id = new ArrayList<>();
            id.add(event.getId());
            titleWithId.put(event.getTitle(), id);
        }
    }

    private void addEmailsMapping(Event event) {
        if (idWithEmails.containsKey(event.getId())) {
            List<String> emails = new ArrayList<>(idWithEmails.get(event.getId()));
            emails.addAll(event.getAttenders().stream().map(Attender::getEmail).collect(Collectors.toList()));
            idWithEmails.replace(event.getId(), emails);
        } else {
            List<String> emails = event.getAttenders().stream().map(Attender::getEmail).collect(Collectors.toList());
            idWithEmails.put(event.getId(), emails);
        }
    }
}
