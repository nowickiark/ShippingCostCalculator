package com.sda.groupa.shippingcostcalculator.model;
import javax.persistence.*;
import java.util.Objects;


@Entity
public class Borders {

    @Id
    @GeneratedValue(generator = "bordSeq")
    @SequenceGenerator(name = "bordSeq", sequenceName = "bord_seq", allocationSize = 1)
    private Long id;

    private String countryFrom;
    private String cityFrom;
    private String countryTo;
    private String cityTo;


    public Borders(Long id, String countryFrom, String cityFrom, String countryTo, String cityTo) {
       this.id = id;
        this.countryFrom = countryFrom;
        this.cityFrom = cityFrom;
        this.countryTo = countryTo;
        this.cityTo = cityTo;
    }

    public Borders() {
    }

    public String getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(String countryFrom) {
        this.countryFrom = countryFrom;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(String countryTo) {
        this.countryTo = countryTo;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borders borders = (Borders) o;
        return Objects.equals(getId(), borders.getId()) &&
                Objects.equals(getCountryFrom(), borders.getCountryFrom()) &&
                Objects.equals(getCityFrom(), borders.getCityFrom()) &&
                Objects.equals(getCountryTo(), borders.getCountryTo()) &&
                Objects.equals(getCityTo(), borders.getCityTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountryFrom(), getCityFrom(), getCountryTo(), getCityTo());
    }

    @Override
    public String toString() {
        return "Borders{" +
                "id=" + id +
                ", countryFrom='" + countryFrom + '\'' +
                ", cityFrom='" + cityFrom + '\'' +
                ", countryTo='" + countryTo + '\'' +
                ", cityTo='" + cityTo + '\'' +
                '}';
    }
}

