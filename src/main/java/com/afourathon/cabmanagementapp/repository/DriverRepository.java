package com.afourathon.cabmanagementapp.repository;

import com.afourathon.cabmanagementapp.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
