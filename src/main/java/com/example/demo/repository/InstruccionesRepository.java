package com.example.demo.repository;

import com.example.demo.model.Instrucciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InstruccionesRepository extends CrudRepository<Instrucciones, Integer> {

}
