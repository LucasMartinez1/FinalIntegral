package com.example.demo.model;

import com.example.demo.DTO.InstruccionesDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Instrucciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String instruccion;

    public Instrucciones() {
    }

    public Instrucciones(InstruccionesDTO instruccionesDTO){
        this.instruccion = instruccionesDTO.getInstruccion();
    }
}
