package com.example.demo.DTO;

public class TelemetriasDTO {


    private Double velocidad;
    private Double temperatura;
    private Double presion;


    public TelemetriasDTO() {
    }

    public TelemetriasDTO(Double velocidad, Double temperatura, Double presion) {
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
