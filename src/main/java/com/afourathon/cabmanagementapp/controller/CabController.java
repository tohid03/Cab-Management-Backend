package com.afourathon.cabmanagementapp.controller;

import com.afourathon.cabmanagementapp.model.Cab;
import com.afourathon.cabmanagementapp.model.Driver;
import com.afourathon.cabmanagementapp.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cab-management")
@CrossOrigin("*")
public class CabController {
    private final CabService cabService;
    @Autowired
    public CabController(CabService cabService) {
        this.cabService = cabService;
    }
    @GetMapping("/cabs")
    public ResponseEntity<List<Cab>> getAllCabs(){
        List<Cab> cabs = cabService.getCabs();
        return new ResponseEntity<>(cabs, HttpStatus.OK);
    }
    @GetMapping(path = ("/cabs/{id}"))
    public ResponseEntity<Cab> getCabById(@PathVariable Long id){
        Cab cab = cabService.getCabById(id);
        return new ResponseEntity<>(cab,HttpStatus.OK);
    }
    @PostMapping("/cabs")
    public ResponseEntity<Cab> addCab(@RequestBody Cab cab){
        return new ResponseEntity<>(cabService.addCab(cab),HttpStatus.CREATED);
    }
    @PutMapping("/cabs/{id}")
    public ResponseEntity<Cab> updateCab(@PathVariable Long id, @RequestBody Cab cab){
        Cab cab1 = cabService.ubdateCab(id,cab);
        return new ResponseEntity<>(cab1,HttpStatus.OK);
    }
    @DeleteMapping("/cabs/{id}")
    public ResponseEntity<Boolean> deleteCab(@PathVariable Long id){
        boolean flag = cabService.deleteCab(id);
        return new ResponseEntity<>(flag,HttpStatus.OK);
    }
    @PutMapping("/cabs/{cabId}/driver/{driverId}")
    public ResponseEntity<Cab> addDriverToCab(@PathVariable Long cabId, @PathVariable Long driverId){
    Cab cab = cabService.updateDriverCab(cabId,driverId);
    return new ResponseEntity<>(cab,HttpStatus.CREATED);
    }
    @PutMapping("/cabs/removeCabDriver/{cabId}")
    public ResponseEntity<?> removeCabDriver(@PathVariable Long cabId){
        cabService.removeCabDriver(cabId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
