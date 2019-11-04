package com.sda.groupa.shippingcostcalculator.border.service;


import com.sda.groupa.shippingcostcalculator.border.model.BorderCross;
import com.sda.groupa.shippingcostcalculator.border.model.Borders;
import com.sda.groupa.shippingcostcalculator.border.repository.BorderCrossRepository;
import com.sda.groupa.shippingcostcalculator.border.repository.BordersRepository;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BorderCrossService {

private final BorderCrossRepository borderCrossRepository;
private final BordersRepository bordersRepository;

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

    public List<BorderCross> findListOfBorderCrossesByExpedition(Expedition expedition){
        return borderCrossRepository.findBorderCrossesByExpedition(expedition);
    }


    public List<Borders> getListOfBorders() {
        return bordersRepository.findAll();
    }

    public Optional<Borders> findBorderById(Long id) {
        return bordersRepository.findById(id);
    }




}
