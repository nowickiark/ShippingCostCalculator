package com.sda.groupa.shippingcostcalculator.truck.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Truck {

    @Id
    @GeneratedValue(generator = "truckSeq")
    @SequenceGenerator(name = "truckSeq", sequenceName = "truck_seq", allocationSize = 1)
    private Long id;

    private String plateNumber;
    private String brand;
    private String model;
    private long horsePower;

    public Truck(){};

    public Truck(String plateNumber, String brand, String model, long horsePower) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(long horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
