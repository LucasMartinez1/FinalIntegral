package com.example.demo.model;

import com.example.demo.DTO.TelemetriasDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Telemetrias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double velocidad;
    private Double temperatura;
    private Double presion;

    public Telemetrias() {
    }

    public Telemetrias(TelemetriasDTO telemetriasDTO) {
        this.velocidad = telemetriasDTO.getVelocidad();
        this.temperatura = telemetriasDTO.getTemperatura();
        this.presion = telemetriasDTO.getPresion();
    }
    public Telemetrias(Integer id, Double velocidad, Double temperatura, Double presion) {
        this.id = id;
        this.velocidad = velocidad;
        this.temperatura = temperatura;
        this.presion = presion;
    }

    public Telemetrias(Double velocidad, Double temperatura, Double presion) {
        this.velocidad = velocidad;
        this.temperatura = temperatura;
        this.presion = presion;
    }


    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getPresion() {
        return presion;
    }

    public void setPresion(Double presion) {
        this.presion = presion;
    }
}
