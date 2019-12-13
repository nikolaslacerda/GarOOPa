package com.tecnicas_pucrs.interfaces.repositorios;

import com.tecnicas_pucrs.casos_de_uso.repositorios.IRepoBairros;
import com.tecnicas_pucrs.entidades.Bairro;
import com.tecnicas_pucrs.persistencia.PersistenciaBairros;

import java.io.IOException;
import java.util.List;

public class RepoBairros implements IRepoBairros {

    private static RepoBairros repoBairros;
    private List<Bairro> bairros;

    private RepoBairros() throws IOException {
        this.bairros = PersistenciaBairros.carregaBairros();
    }

    public static synchronized RepoBairros getInstance(){
        if (repoBairros == null){
            try {
                repoBairros = new RepoBairros();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return repoBairros;
    }

    public List<Bairro> getBairros() {
        return this.bairros;
    }

    public boolean persisteBairros(){
        try {
            return PersistenciaBairros.persisteBairros(bairros);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Bairro recuperarPorNome(String nome) {
        for (Bairro b : this.bairros) {
            if (b.getNome().equals(nome)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Bairro Inexistente");
    }
}