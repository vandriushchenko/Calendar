package org.diotraining.calendar;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Event {
    private final String description;
    private final List<String> attenders;
    private final Date startDate;
    private final Date endDate;
    private final UUID id;

    private Event(Builder builder){
        this.description = builder.description;
        this.attenders = builder.attenders;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.id = builder.id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAttenders() {
        return attenders;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (attenders != null ? !attenders.equals(event.attenders) : event.attenders != null) return false;
        if (startDate != null ? !startDate.equals(event.startDate) : event.startDate != null) return false;
        if (endDate != null ? !endDate.equals(event.endDate) : event.endDate != null) return false;
        return !(id != null ? !id.equals(event.id) : event.id != null);

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (attenders != null ? attenders.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "org.diotraining.calendar.Event{" +
                "description='" + description + '\'' +
                ", attenders=" + attenders +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", id=" + id +
                '}';
    }

    public static class Builder{
        private String description;
        private List<String> attenders;
        private Date startDate;
        private Date endDate;
        private UUID id;

        public Builder(){}

        public Builder(Event origin){
            this.description = origin.description;
            this.attenders = origin.attenders;
            this.startDate = origin.startDate;
            this.id = origin.id;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder attenders(List<String> attenders){
            this.attenders = attenders;
            return this;
        }

        public Builder startDate(Date startDate){
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate){
            this.endDate = endDate;
            return this;
        }

        public Builder id(UUID id){
            this.id = id;
            return this;
        }

        public Event build(){
            return new Event(this);
        }
    }

}
