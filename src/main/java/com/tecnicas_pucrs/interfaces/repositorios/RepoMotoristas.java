package com.tecnicas_pucrs.interfaces.repositorios;

import com.tecnicas_pucrs.casos_de_uso.repositorios.IRepoMotoristas;
import com.tecnicas_pucrs.entidades.Motorista;
import com.tecnicas_pucrs.persistencia.PersistenciaMotoristas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RepoMotoristas implements IRepoMotoristas {

    private static  RepoMotoristas repoMotoristas;
    private List<Motorista> motoristas;

    private RepoMotoristas() throws IOException, URISyntaxException {
        this.motoristas = PersistenciaMotoristas.carregaMotoristas();
    }

    public static synchronized RepoMotoristas getInstance(){
        if (repoMotoristas == null){
            try {
                repoMotoristas = new RepoMotoristas();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return repoMotoristas;
    }

    public boolean persisteMotoristas(){
        try {
            return PersistenciaMotoristas.persisteMotoristas(motoristas);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Motorista> getMotoristas() {
        return this.motoristas;
    }

    public Motorista recuperarPorCPF(String cpf){
        for (Motorista v: this.motoristas) {
            if(v.getCPF().equals(cpf)){
                return v;
            }
        }
        throw new IllegalArgumentException("Placa Inexistente" + cpf);
    }
}