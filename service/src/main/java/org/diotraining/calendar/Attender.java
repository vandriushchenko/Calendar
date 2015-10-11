package org.diotraining.calendar;

import java.io.Serializable;

public class Attender implements Comparable<Attender>, Serializable {
    private String name;
    private String surname;
    private String email;

    public Attender(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Attender{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attender attender = (Attender) o;

        if (email != null ? !email.equals(attender.email) : attender.email != null) return false;
        if (name != null ? !name.equals(attender.name) : attender.name != null) return false;
        if (surname != null ? !surname.equals(attender.surname) : attender.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    //local code review (vtegza): remove or implement @ 12.10.15
    @Override
    public int compareTo(Attender o) {
        return 0;
    }


}
