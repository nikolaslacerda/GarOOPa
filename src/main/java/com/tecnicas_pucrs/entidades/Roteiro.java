package com.tecnicas_pucrs.entidades;

import com.tecnicas_pucrs.entidades.geometria.Ponto;
import com.tecnicas_pucrs.entidades.geometria.Reta;
import com.tecnicas_pucrs.entidades.geometria.SituacaoDaReta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Roteiro {

    private Cidade cidade;
    private Bairro bairroOrigem;
    private Bairro bairroDestino;

    public Roteiro(Cidade cidade, Bairro bairroOrigem, Bairro bairroDestino) {
        this.cidade = cidade;
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
    }

    public List<Bairro> bairrosPercorridos(){
        List<Bairro> percorridos = new ArrayList<>();
        Reta trajeto = getRota();
        for (Bairro b : cidade.getListaDeBairros()){
            if(b.getLimites().classifica(trajeto) != (SituacaoDaReta.TODA_FORA)){
                percorridos.add(b);
            }
        }
        System.out.println(percorridos);
        return percorridos;
    }

    public Reta getRota(){
        Ponto pOrig = bairroOrigem.getLimites().pontoCentral();
        Ponto pDest = bairroDestino.getLimites().pontoCentral();
        return new Reta(pOrig,pDest);
    }

    @Override
    public String toString() {
        return bairroOrigem + ";" + bairroDestino ;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Bairro getBairroOrigem() {
        return bairroOrigem;
    }

    public Bairro getBairroDestino() {
        return bairroDestino;
    }
}
