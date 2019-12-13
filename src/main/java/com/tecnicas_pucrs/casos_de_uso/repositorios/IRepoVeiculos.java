package com.tecnicas_pucrs.casos_de_uso.repositorios;

import com.tecnicas_pucrs.entidades.Passageiro;
import com.tecnicas_pucrs.entidades.Veiculo;

import java.util.List;

public interface IRepoVeiculos {

    public Veiculo recuperarPorPlaca(String placa);

    public List<Veiculo> getVeiculos();

}
