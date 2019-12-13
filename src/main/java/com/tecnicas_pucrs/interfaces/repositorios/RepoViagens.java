package com.tecnicas_pucrs.interfaces.repositorios;

import com.tecnicas_pucrs.entidades.Viagem;
import com.tecnicas_pucrs.persistencia.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RepoViagens {

    private static  RepoViagens repoViagens;
    private List<Viagem> viagens;

    private RepoViagens() throws IOException, URISyntaxException {
        this.viagens = PersistenciaViagens.carregaViagens();
    }

    public static synchronized RepoViagens getInstance(){
        if (repoViagens == null){
            try {
                repoViagens = new RepoViagens();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return repoViagens;
    }

    public boolean persisteViagens(){
        try {
            return PersistenciaViagens.persisteViagens(viagens);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void adicionaViagem(Viagem v){
        viagens.add(v);
    }

    public List<Viagem> getViagens() {
        return this.viagens;
    }

    public Viagem recuperaPorId(int id) {
        for (Viagem v : this.viagens) {
            if (v.getId() == id) {
                return v;
            }
        }
        throw new IllegalArgumentException("Viagem Inexistente");
    }
}