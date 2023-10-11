package com.afourathon.cabmanagementapp.service.implementation;

import com.afourathon.cabmanagementapp.exception.ResourceNotFoundException;
import com.afourathon.cabmanagementapp.model.Cab;
import com.afourathon.cabmanagementapp.model.Driver;
import com.afourathon.cabmanagementapp.repository.CabRepository;
import com.afourathon.cabmanagementapp.repository.DriverRepository;
import com.afourathon.cabmanagementapp.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CabServiceImpl implements CabService {
    private final CabRepository cabRepository;
    private final DriverRepository driverRepository;
    @Autowired
    public CabServiceImpl(CabRepository cabRepository, DriverRepository driverRepository) {
        this.cabRepository = cabRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Cab addCab(Cab cab) {
        final String cabRegNum = "CAB000";
        int count = (int) cabRepository.count()+1;
        cab.setCabRegistrationNumber(cabRegNum+count);
        return cabRepository.save(cab);
    }

    @Override
    public List<Cab> getCabs() {
        return cabRepository.findAll();
    }

    @Override
    public Cab getCabById(Long cabId) {
        Optional<Cab> cab = cabRepository.findById(cabId);
        return cab.orElseThrow(
                ()-> new ResourceNotFoundException("Cab not exist with id:"+ cabId)
        );
    }

    @Override
    public Cab ubdateCab(Long cabId, Cab cab) {
        Optional<Cab> optionalCab = cabRepository.findById(cabId);
        if(optionalCab.isPresent()){
            Cab cab1 = optionalCab.get();
            cab1.setCabRegistrationNumber(cab.getCabRegistrationNumber());
            cab1.setCabModel(cab.getCabModel());
            cab1.setCabColour(cab.getCabColour());
            return cabRepository.save(cab1);
        }else {
            throw new ResourceNotFoundException("Cab not exist with id:"+ cabId);
        }
    }

    @Override
    public boolean deleteCab(Long cabId) {
        Cab cab = cabRepository.findById(cabId).orElseThrow(
                ()-> new ResourceNotFoundException("Cab not exist with id:"+ cabId)
        );
        cabRepository.deleteById(cabId);
        return true;
    }

    @Override
    public Cab assignedDriver(Long cabId, Long driverId){
        Cab cab = cabRepository.findById(cabId).orElseThrow(
                ()-> new ResourceNotFoundException("Cab not exist with id:"+ cabId)
        );
        Driver driver = driverRepository.findById(driverId).orElseThrow(
                ()-> new ResourceNotFoundException("driver not exist with id:"+ driverId)
        );
        cab.setDriver(driver);
        cabRepository.save(cab);
        return cab;
    }

    @Override
    public void unassignedDriver(Long cabId) {
        Cab cab = cabRepository.findById(cabId).orElseThrow(()-> new ResourceNotFoundException("Cab not exist with id:"+ cabId));
        cab.setDriver(null);
        cabRepository.save(cab);

    }
}
