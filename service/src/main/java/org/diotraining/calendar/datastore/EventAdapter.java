package org.diotraining.calendar.datastore;


import org.diotraining.calendar.Attender;
import org.diotraining.calendar.Event;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@XmlRootElement(name = "event")
@XmlType(propOrder={
        "title",
        "description",
        "startDate",
        "endDate",
        "attenders"})
public class EventAdapter implements Serializable{
    private String description;
    private String title;
    private List<AttenderAdapter> attenders;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID id;

    public EventAdapter(Event event){
        this.description = event.getDescription();
        this.title = event.getTitle();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.id = event.getId();
        this.attenders = tranformListAttenderToAttenderAdapter(event.getAttenders());
    }

    public EventAdapter() {}

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElementWrapper
    @XmlElement(name = "attender")
    public List<AttenderAdapter> getAttenders() {
        return attenders;
    }

    @XmlElement
    public LocalDateTime getStartDate() {
        return startDate;
    }

    @XmlElement
    public LocalDateTime getEndDate() {
        return endDate;
    }

    @XmlAttribute
    public UUID getId() {
        return id;
    }

    private List<AttenderAdapter> tranformListAttenderToAttenderAdapter(List<Attender> attenders){
        List<AttenderAdapter> adapters = new ArrayList<>();
        for (Attender attender: attenders){
            adapters.add(new AttenderAdapter(attender));
        }
        return adapters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventAdapter that = (EventAdapter) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (attenders != null ? !attenders.equals(that.attenders) : that.attenders != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (attenders != null ? attenders.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventAdapter{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", attenders=" + attenders +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", id=" + id +
                '}';
    }

    //local code review (vtegza): as extension for the Attender - it is ok, if it is a kind of tets - try to move to the actual test @ 12.10.15
    public static void main(String[] args) {
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        Attender user2 = new Attender("Andriy", "Haysan", "ahaysan@gmail.com");
        Attender user3 = new Attender("Ihor", "Kovalenko", "ikovalenko@gmail.com");
        Attender user4 = new Attender("Oleksandr", "Velychko", "ovelychko@gmail.com");
        Attender user5 = new Attender("Maxim", "Mamaev", "mmamaev@gmail.com");
        AttenderAdapter userA1 = new AttenderAdapter(user1);
        AttenderAdapter userA2 = new AttenderAdapter(user2);
        AttenderAdapter userA3 = new AttenderAdapter(user3);
        AttenderAdapter userA4 = new AttenderAdapter(user4);
        AttenderAdapter userA5 = new AttenderAdapter(user5);
        List<Attender> devs = Arrays.asList(user1, user2, user4);
        List<Attender> qas = Arrays.asList(user3, user5);
        List<Attender> team = Arrays.asList(user1, user2, user3, user4, user5);
        Event event1 = new Event.Builder().title("developers' code review").startDate(LocalDateTime.of(2015, 10, 10, 15, 0)).endDate(LocalDateTime.of(2015, 10, 10, 16, 0)).attenders(devs).build();
        Event event2 = new Event.Builder().title("qas' code review").startDate(LocalDateTime.of(2015, 10, 11, 15, 0)).endDate(LocalDateTime.of(2015, 10, 11, 16, 0)).attenders(qas).build();
        Event event3 = new Event.Builder().title("daily stand-up").startDate(LocalDateTime.of(2015, 10, 11, 12, 0)).endDate(LocalDateTime.of(2015, 10, 11, 12, 20)).attenders(team).build();
        EventAdapter eventAdapter = new EventAdapter(event3);
        System.out.println(eventAdapter.getStartDate());
        try {
            File file = new File("event3.xml");
            System.out.println(file.getAbsolutePath());
            JAXBContext context = JAXBContext.newInstance(EventAdapter.class);
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(eventAdapter, file);
            jaxbMarshaller.marshal(eventAdapter, System.out);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
