package com.tecnicas_pucrs.casos_de_uso.repositorios;

import com.tecnicas_pucrs.entidades.Bairro;
import com.tecnicas_pucrs.entidades.Passageiro;

import java.util.List;

public interface IRepoBairros {

    public Bairro recuperarPorNome(String nome);

    public List<Bairro> getBairros();

}
