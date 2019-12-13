package com.tecnicas_pucrs.entidades;

import java.util.ArrayList;
import java.util.List;

import com.tecnicas_pucrs.entidades.Bairro;

public class Cidade {

    private String nome;
    private List<Bairro> listaDeBairros;

    public Cidade(String nome, List<Bairro> listaDeBairros){
        this.nome = nome;
        this.listaDeBairros = listaDeBairros;
    }

    public String getNome(){
        return this.nome;
    }

    public List<Bairro> getListaDeBairros(){
        return this.listaDeBairros;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "nome='" + nome + '\'' +
                ", listaDeBairros=" + listaDeBairros +
                '}';
    }
}