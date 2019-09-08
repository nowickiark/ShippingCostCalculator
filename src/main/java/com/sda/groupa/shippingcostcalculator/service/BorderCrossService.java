package com.sda.groupa.shippingcostcalculator.service;


import com.sda.groupa.shippingcostcalculator.model.BorderCross;
import com.sda.groupa.shippingcostcalculator.model.Borders;
import com.sda.groupa.shippingcostcalculator.repository.BorderCrossRepository;
import com.sda.groupa.shippingcostcalculator.repository.BordersRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BorderCrossService {

private final BorderCrossRepository borderCrossRepository;
private BordersRepository bordersRepository;

    public BorderCrossService(BordersRepository bordersRepository, BorderCrossRepository borderCrossRepository) {
        this.borderCrossRepository = borderCrossRepository;
        this.bordersRepository = bordersRepository;
    }


    public void addBorderCrossing(BorderCross borderCross) {
        borderCrossRepository.save(borderCross);

    }

    public List<BorderCross> getListOfBorderCrosses() {
        return borderCrossRepository.findAll();
    }

    public Optional<BorderCross> findBorderCrossById(Long id) {
        return borderCrossRepository.findById(id);
    }

    public void addBorder(Borders borders) {
        bordersRepository.save(borders);

    }

    public List<Borders> getListOfBorders() {
        return bordersRepository.findAll();
    }

    public Optional<Borders> findBorderById(Long id) {
        return bordersRepository.findById(id);
    }



}
