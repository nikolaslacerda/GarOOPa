package com.tecnicas_pucrs.casos_de_uso.repositorios;

import com.tecnicas_pucrs.entidades.Motorista;
import com.tecnicas_pucrs.entidades.Viagem;

import java.util.List;
import java.util.stream.Stream;

public interface IRepoViagens {

    public void cadastrarViagem(Viagem umaViagem);

    public List<Viagem> getViagens(Motorista motorista);

}
