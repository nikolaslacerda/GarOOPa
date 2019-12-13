package com.tecnicas_pucrs.casos_de_uso.repositorios;

import com.tecnicas_pucrs.entidades.Passageiro;

import java.util.List;

public interface IRepoPassageiros {

    public Passageiro recuperarPorCPF(String cpf);

    public List<Passageiro> getPassageiros();

}
