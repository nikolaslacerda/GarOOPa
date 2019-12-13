package com.tecnicas_pucrs.interfaces.repositorios;

import com.tecnicas_pucrs.entidades.Viagem;

public abstract class ID_Sequencial_Viagens {

    private static int id_atual = -1;

    public static int getNextId(){
        if (id_atual == -1) {
            for (Viagem v : RepoViagens.getInstance().getViagens()){
                if (v.getId() >= id_atual) { id_atual = v.getId(); }
            }
        }
        id_atual +=1;
        return id_atual;
    }
}
