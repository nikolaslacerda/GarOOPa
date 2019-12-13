package com.tecnicas_pucrs.interfaces.repositorios;

import com.tecnicas_pucrs.casos_de_uso.repositorios.IRepoPassageiros;
import com.tecnicas_pucrs.entidades.Passageiro;
import com.tecnicas_pucrs.persistencia.PersistenciaPassageiros;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RepoPassageiros implements IRepoPassageiros {

    private static RepoPassageiros repoPassageiros;
    private List<Passageiro> passageiros;

    private RepoPassageiros() throws IOException, URISyntaxException {
        this.passageiros = PersistenciaPassageiros.carregaPassageiros();
    }

    public static synchronized RepoPassageiros getInstance(){
        if (repoPassageiros == null){
            try {
                repoPassageiros = new RepoPassageiros();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return repoPassageiros;
    }

    public boolean persistePassageiros(){
        try {
            return PersistenciaPassageiros.persistePassageiros(passageiros);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Passageiro> getPassageiros() {
        return this.passageiros;
    }

    public Passageiro recuperarPorCPF(String cpf) {
        for (Passageiro p : this.passageiros) {
            if (p.getCPF().equals(cpf)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Passageiro Inexistente");
    }

}