package com.pw.grapefarm.dao;

import com.pw.grapefarm.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmDao extends JpaRepository<Farm,Integer> {
}
