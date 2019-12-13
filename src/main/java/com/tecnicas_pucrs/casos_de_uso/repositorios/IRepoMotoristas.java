package com.tecnicas_pucrs.casos_de_uso.repositorios;

import com.tecnicas_pucrs.entidades.Motorista;
import com.tecnicas_pucrs.entidades.Passageiro;

import java.util.List;

public interface IRepoMotoristas {

    public Motorista recuperarPorCPF(String cpf);

    public List<Motorista> getMotoristas();

}
