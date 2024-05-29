package com.example.demo.services;

import com.example.demo.model.Instrucciones;
import com.example.demo.repository.InstruccionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstruccionesServiceImpl implements InstruccionesServices{
   @Autowired
    InstruccionesRepository instruccionesRepository;
    @Override
    public void crearInstruccion(Instrucciones instruccion) {
        instruccionesRepository.save(instruccion);
    }
}
