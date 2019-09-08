package com.sda.groupa.shippingcostcalculator.border.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BorderCross {

    @Id
    @GeneratedValue(generator = "crossSeq")
    @SequenceGenerator(name = "crossSeq", sequenceName = "cross_seq", allocationSize = 1)
    private Long id;


    @ManyToOne(targetEntity = Borders.class)
    private Borders borders;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBorderCrossing;

    public BorderCross(Borders borders, LocalDate dateOfBorderCrossing) {
        this.borders = borders;
        this.dateOfBorderCrossing = dateOfBorderCrossing;
    }


    public BorderCross() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfBorderCrossing() {
        return dateOfBorderCrossing;
    }

    public void setDateOfBorderCrossing(LocalDate dateOfBorderCrossing) {
        this.dateOfBorderCrossing = dateOfBorderCrossing;
    }

    public Borders getBorders() {
        return borders;
    }

    public void setBorders(Borders borders) {
        this.borders = borders;
    }

    @Override
    public String toString() {
        return "BorderCross{" +
                "id=" + id +
                ", borders=" + borders +
                ", dateOfBorderCrossing=" + dateOfBorderCrossing +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorderCross that = (BorderCross) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(borders, that.borders) &&
                Objects.equals(getDateOfBorderCrossing(), that.getDateOfBorderCrossing());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), borders, getDateOfBorderCrossing());
    }
}
