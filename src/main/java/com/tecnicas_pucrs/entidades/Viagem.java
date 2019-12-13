package com.tecnicas_pucrs.entidades;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Viagem {

    private int id;
    private LocalDateTime dataHora;
    private Roteiro roteiro;
    private Motorista motorista;
    private Passageiro passageiro;
    private double custo;

    public Viagem(int id, LocalDateTime dataHora, Roteiro roteiro, Motorista motorista, Passageiro passageiro, double custo) {
        this.id = id;
        this.dataHora = dataHora;
        this.roteiro = roteiro;
        this.motorista = motorista;
        this.passageiro = passageiro;
        this.custo = custo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Roteiro getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(Roteiro roteiro) {
        this.roteiro = roteiro;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        return "Viagem{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", roteiro=" + roteiro +
                ", motorista=" + motorista +
                ", passageiro=" + passageiro +
                ", custo=" + custo +
                '}';
    }
}
