package com.sda.groupa.shippingcostcalculator.driver.driverModel;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.login.model.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Driver {

    @Id
    @GeneratedValue(generator = "driverSeq")
    @SequenceGenerator(name = "driverSeq", sequenceName = "driver_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private String personalIdNumber;

    @OneToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = Expedition.class, cascade = {CascadeType.ALL})
    private Expedition expedition;

    public Driver() {
    }

    public Driver(String firstName, String surname, String phoneNumber, String personalIdNumber, User user, Expedition expedition) {
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.personalIdNumber = personalIdNumber;
        this.user = user;
        this.expedition = expedition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    public void setPersonalIdNumber(String personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) &&
                Objects.equals(firstName, driver.firstName) &&
                Objects.equals(surname, driver.surname) &&
                Objects.equals(phoneNumber, driver.phoneNumber) &&
                Objects.equals(personalIdNumber, driver.personalIdNumber) &&
                Objects.equals(user, driver.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, surname, phoneNumber, personalIdNumber, user);
    }
}
