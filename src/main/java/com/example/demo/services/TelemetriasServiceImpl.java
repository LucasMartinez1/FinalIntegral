package com.example.demo.services;

import com.example.demo.model.Telemetrias;
import com.example.demo.repository.TelemetriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelemetriasServiceImpl implements TelemetriasServices {

    @Autowired
    TelemetriasRepository telemetriasRepository;

    @Override
    public void crearTelemetria(Telemetrias telemetrias) {
        telemetriasRepository.save(telemetrias);
    }

}
