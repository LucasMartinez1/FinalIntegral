package com.example.demo.repository;

import com.example.demo.model.Telemetrias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TelemetriasRepository extends CrudRepository<Telemetrias,Integer> {


    Telemetrias findById(int id);
}
