package com.afourathon.cabmanagementapp.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Data
@Entity
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cabRegistrationNumber;
    private String cabModel;
    private String cabColour;
    @OneToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name = "driver_id")
    @JsonIgnoreProperties("cab")
    private Driver driver;

}
