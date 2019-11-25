package com.sda.groupa.shippingcostcalculator.border.model;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
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
    @ManyToOne(targetEntity = Expedition.class)
    private Expedition expedition;
    @ManyToOne(targetEntity = Borders.class)
    private Borders borders;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBorderCrossing;

    public BorderCross(Expedition expedition, Borders borders, LocalDate dateOfBorderCrossing) {
        this.expedition = expedition;
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

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(expedition, that.expedition) &&
                Objects.equals(borders, that.borders) &&
                Objects.equals(dateOfBorderCrossing, that.dateOfBorderCrossing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expedition, borders, dateOfBorderCrossing);
    }
}
