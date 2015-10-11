package org.diotraining.calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ClientMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("clientApplicationContext.xml");
        CalendarService calendarService = (CalendarService)context.getBean("calendarService");
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        Attender user2 = new Attender("Andriy", "Haysan", "ahaysan@gmail.com");
        Attender user3 = new Attender("Ihor", "Kovalenko", "ikovalenko@gmail.com");
        Attender user4 = new Attender("Oleksandr", "Velychko", "ovelychko@gmail.com");
        Attender user5 = new Attender("Maxim", "Mamaev", "mmamaev@gmail.com");
        List<Attender> devs = Arrays.asList(user1, user2, user4);
        List<Attender> qas = Arrays.asList(user3, user5);
        List<Attender> team = Arrays.asList(user1, user2, user3, user4, user5);
        Event event1 = new Event.Builder().title("developers' code review").startDate(LocalDateTime.of(2015, 10, 10, 15, 0)).endDate(LocalDateTime.of(2015, 10, 10, 16, 0)).attenders(devs).build();
        Event event2 = new Event.Builder().title("qas' code review").startDate(LocalDateTime.of(2015, 10, 11, 15, 0)).endDate(LocalDateTime.of(2015, 10, 11, 16, 0)).attenders(qas).build();
        Event event3 = new Event.Builder().title("daily stand-up").startDate(LocalDateTime.of(2015, 10, 11, 12, 0)).endDate(LocalDateTime.of(2015, 10, 11, 12, 20)).attenders(team).build();
        calendarService.addEvent(event1);
        calendarService.addEvent(event2);
        System.out.println(calendarService.getEventsByTitle("developers' code review"));
        System.out.println(calendarService.getEventsForSpecificDate(LocalDateTime.of(2015, 10, 11, 15, 0)));
        calendarService.addEvent(event3);
        System.out.println(calendarService.getEventsByTitle("daily stand-up"));
        calendarService.createEvent("team building", "team will drink and take their time", team, LocalDateTime.of(2015, 10, 13, 12, 0), LocalDateTime.of(2015, 10, 13, 23, 30));
        System.out.println(calendarService.getEventsByTitle("team building"));
    }
}
