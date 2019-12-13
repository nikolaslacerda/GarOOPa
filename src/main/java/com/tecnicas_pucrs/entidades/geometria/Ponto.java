package com.tecnicas_pucrs.entidades.geometria;

public class Ponto { //PRONTA

    public int x;
    public int y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Ponto (" + x + "," + y + ")";
    }
}