package org.diotraining.calendar.datastore;

import org.diotraining.calendar.Attender;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.Serializable;

@XmlRootElement(name = "attender")
public class AttenderAdapter implements Serializable{
    private String name;
    private String surname;
    private String email;

    public AttenderAdapter(Attender attender) {
        this.name = attender.getName();
        this.surname = attender.getSurname();
        this.email = attender.getEmail();
    }

    public AttenderAdapter() {}

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AttenderAdapter{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttenderAdapter that = (AttenderAdapter) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        return !(email != null ? !email.equals(that.email) : that.email != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    //local code review (vtegza): as extension for the Attender - it is ok, if it is a kind of tets - try to move to the actual test @ 12.10.15
    public static void main(String[] args) {
        Attender user1 = new Attender("Volodymyr", "Kosovsky", "vkosovsky@gmail.com");
        try {
            File file = new File("1.xml");
            System.out.println(file.getAbsolutePath());
            JAXBContext context = JAXBContext.newInstance(Attender.class);
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(user1, file);
            jaxbMarshaller.marshal(user1, System.out);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
