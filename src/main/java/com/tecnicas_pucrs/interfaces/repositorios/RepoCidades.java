package com.tecnicas_pucrs.interfaces.repositorios;

import com.tecnicas_pucrs.casos_de_uso.repositorios.IRepoCidades;
import com.tecnicas_pucrs.entidades.Cidade;
import com.tecnicas_pucrs.persistencia.PersistenciaCidades;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RepoCidades implements IRepoCidades {

    private static RepoCidades repoCidades;
    private List<Cidade> cidades;

    private RepoCidades() throws IOException, URISyntaxException {
        this.cidades = PersistenciaCidades.carregaCidades();
    }

    public static synchronized RepoCidades getInstance(){
        if (repoCidades == null){
            try {
                repoCidades = new RepoCidades();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return repoCidades;
    }

    public List<Cidade> getCidades() {
        return this.cidades;
    }

    public boolean persisteCidades(){
        try {
            return PersistenciaCidades.persisteCidades(cidades);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Cidade recuperarPorNome(String nome) {
        for (Cidade c : this.cidades) {
            if (c.getNome().equals(nome)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Cidade Inexistente");
    }
}