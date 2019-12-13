package com.tecnicas_pucrs.entidades.geometria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AreaTest{
    private Area area;

    @BeforeEach
    public void setup() {
        area = new Area(new Ponto(10,30),new Ponto(50,10));
    }


    @DisplayName("Classifica retas")
    @ParameterizedTest
    @CsvSource({"15,20,35,20,TODA_DENTRO",
            "15,50,35,50,TODA_FORA",
            "15,5,35,5,TODA_FORA",
            "5,40,5,10,TODA_FORA",
            "55,40,55,5,TODA_FORA",
            "30,90,90,20,TODA_FORA",
            "-5,15,15,-20,TODA_FORA"
    })
    public void codRetaTest(int xi,int yi,int xf,int yf,SituacaoDaReta rEsp) {
        Reta reta = new Reta(new Ponto(xi,yi),new Ponto(xf,yf));
        SituacaoDaReta observed = area.classifica(reta);
        assertEquals(rEsp,observed);
    }
}