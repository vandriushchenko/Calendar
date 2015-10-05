package org.diotraining.calendar;

public class Attender implements Comparable<Attender>{
    private final String name;
    private final String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attender attender = (Attender) o;

        if (name != null ? !name.equals(attender.name) : attender.name != null) return false;
        if (surname != null ? !surname.equals(attender.surname) : attender.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Attender{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Attender(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public int compareTo(Attender o) {
        return 0;
    }
}
