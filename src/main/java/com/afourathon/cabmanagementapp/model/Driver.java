package com.afourathon.cabmanagementapp.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String idNumber;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE},
            mappedBy = "driver", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("driver")
     private Cab cab;
}
