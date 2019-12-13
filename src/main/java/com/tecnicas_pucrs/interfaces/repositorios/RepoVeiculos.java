package com.tecnicas_pucrs.interfaces.repositorios;

import com.tecnicas_pucrs.casos_de_uso.repositorios.IRepoVeiculos;
import com.tecnicas_pucrs.entidades.Veiculo;
import com.tecnicas_pucrs.persistencia.PersistenciaVeiculos;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RepoVeiculos implements IRepoVeiculos {

    private static RepoVeiculos repoVeiculos;
    private List<Veiculo> veiculos;

    private RepoVeiculos() throws IOException, URISyntaxException {
        this.veiculos = PersistenciaVeiculos.carregaVeiculos();
    }

    public static synchronized RepoVeiculos getInstance(){
        if (repoVeiculos == null){
            try {
                repoVeiculos  = new RepoVeiculos();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return repoVeiculos;
    }

    public boolean persisteVeiculos(){
        try {
            return PersistenciaVeiculos.persisteVeiculos(veiculos);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Veiculo> getVeiculos() {
        return this.veiculos;
    }

    public Veiculo recuperarPorPlaca(String placa) {
        for (Veiculo v : this.veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        throw new IllegalArgumentException("Placa Inexistente");
    }
}