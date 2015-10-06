package org.diotraining.calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class CalendarServiceImplTest {
    @Mock
    private CalendarDataStore dataStore;
    private CalendarService calendarService;

    @Before
    public void setup(){
        calendarService = new CalendarServiceImpl(dataStore);
    }

    @Test
    public void testAddEvent(){
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        Attender user2 = new Attender("Andriy", "Haysan", "ahaysan@gmail.com");
        Attender user3 = new Attender("Ihor", "Kovalenko", "ikovalenko@gmail.com");
        List<Attender> devs = Arrays.asList(user1, user2, user3);
        Event event = new Event.Builder().title("developers' code review").startDate(LocalDateTime.of(2015, 10, 10, 15, 0)).endDate(LocalDateTime.of(2015, 10, 10, 16, 0)).attenders(devs).build();
        calendarService.addEvent(event);
        verify(dataStore).addEvent(event);
    }

    @Test
    public void testCreateEvent(){
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        Attender user2 = new Attender("Andriy", "Haysan", "ahaysan@gmail.com");
        Attender user3 = new Attender("Ihor", "Kovalenko", "ikovalenko@gmail.com");
        List<Attender> team = Arrays.asList(user1, user2, user3);
        calendarService.createEvent("team building", "team will drink and take their time", team, LocalDateTime.of(2015, 10, 13, 12, 0), LocalDateTime.of(2015, 10, 13, 23, 30));
        verify(dataStore).createEvent("team building", "team will drink and take their time", team, LocalDateTime.of(2015, 10, 13, 12, 0), LocalDateTime.of(2015, 10, 13, 23, 30));
    }

    @Test
    public void testGetEventsByTitleDelegating(){
        calendarService.getEventsByTitle("daily stand-up");
        verify(dataStore).getEventsByTitle("daily stand-up");
    }

    @Test
    public void testGetEventsByTitleReturnsEvents(){
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        Attender user2 = new Attender("Andriy", "Haysan", "ahaysan@gmail.com");
        Attender user3 = new Attender("Ihor", "Kovalenko", "ikovalenko@gmail.com");
        List<Attender> devs = Arrays.asList(user1, user2, user3);
        Event event = new Event.Builder().title("daily stand-up").startDate(LocalDateTime.of(2015, 10, 10, 15, 0)).endDate(LocalDateTime.of(2015, 10, 10, 16, 0)).attenders(devs).build();
        List<Event> eventList = Collections.singletonList(event);
        when(dataStore.getEventsByTitle("daily stand-up")).thenReturn(eventList);
        Assert.assertEquals(eventList, calendarService.getEventsByTitle("daily stand-up"));
    }

    @Test
    public void testGetEventsByTitleDoesntReturnEvents(){
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        Attender user2 = new Attender("Andriy", "Haysan", "ahaysan@gmail.com");
        Attender user3 = new Attender("Ihor", "Kovalenko", "ikovalenko@gmail.com");
        List<Attender> devs = Arrays.asList(user1, user2, user3);
        Event event = new Event.Builder().title("daily stand-up").startDate(LocalDateTime.of(2015, 10, 10, 15, 0)).endDate(LocalDateTime.of(2015, 10, 10, 16, 0)).attenders(devs).build();
        List<Event> eventList = Collections.singletonList(event);
        when(dataStore.getEventsByTitle("daily stand-up")).thenReturn(eventList);
        Assert.assertNotEquals(eventList, calendarService.getEventsByTitle("stand-up"));
    }

    @Test
    public void testGetEventsForSpecificDate(){
        calendarService.getEventsForSpecificDate(LocalDateTime.of(2015, 10, 13, 12, 0));
        verify(dataStore).getEventsForSpecificDate(LocalDateTime.of(2015, 10, 13, 12, 0));
    }

    @Test
    public void testGetEventsForSpecificDateReturnsEvents(){
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        Attender user2 = new Attender("Andriy", "Haysan", "ahaysan@gmail.com");
        Attender user3 = new Attender("Ihor", "Kovalenko", "ikovalenko@gmail.com");
        List<Attender> devs = Arrays.asList(user1, user2, user3);
        Event event = new Event.Builder().title("daily stand-up").startDate(LocalDateTime.of(2015, 10, 13, 12, 0)).endDate(LocalDateTime.of(2015, 10, 10, 15, 0)).attenders(devs).build();
        Set<Event> eventList = Collections.singleton(event);
        when(dataStore.getEventsForSpecificDate(LocalDateTime.of(2015, 10, 13, 12, 0))).thenReturn(eventList);
        Assert.assertEquals(eventList, calendarService.getEventsForSpecificDate(LocalDateTime.of(2015, 10, 13, 12, 0)));
    }

    @Test
    public void testGetEventsForSpecificDateDoesntReturnEvents(){
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        Attender user2 = new Attender("Andriy", "Haysan", "ahaysan@gmail.com");
        Attender user3 = new Attender("Ihor", "Kovalenko", "ikovalenko@gmail.com");
        List<Attender> devs = Arrays.asList(user1, user2, user3);
        Event event = new Event.Builder().title("daily stand-up").startDate(LocalDateTime.of(2015, 10, 13, 12, 0)).endDate(LocalDateTime.of(2015, 10, 10, 15, 0)).attenders(devs).build();
        Set<Event> eventList = Collections.singleton(event);
        when(dataStore.getEventsForSpecificDate(LocalDateTime.of(2015, 10, 13, 12, 0))).thenReturn(eventList);
        Assert.assertNotEquals(eventList, calendarService.getEventsForSpecificDate(LocalDateTime.of(2015, 10, 15, 12, 0)));
    }
}
