package com.afourathon.cabmanagementapp.repository;

import com.afourathon.cabmanagementapp.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab,Long> {
}
