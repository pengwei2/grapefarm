package com.pw.grapefarm.dao;

import com.pw.grapefarm.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedDao extends JpaRepository<Breed,Integer> {
}
