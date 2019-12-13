package com.tecnicas_pucrs.casos_de_uso.servicos;

import com.tecnicas_pucrs.casos_de_uso.politicas.CalculoCustoViagem;
import com.tecnicas_pucrs.casos_de_uso.politicas.SeletorDeMotorista;
import com.tecnicas_pucrs.entidades.*;
import com.tecnicas_pucrs.interfaces.repositorios.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fachada {

    private CalculoCustoViagem custoViagem;
    private SeletorDeMotorista selecaoMotorista;
    private RepoBairros bairros;
    private RepoCidades cidades;
    private RepoMotoristas motoristas;
    private RepoPassageiros passageiros;
    private RepoViagens viagens;

    public Fachada(CalculoCustoViagem custoViagem, SeletorDeMotorista selecaoMotorista, RepoBairros bairros, RepoCidades cidades, RepoMotoristas motoristas, RepoPassageiros passageiros, RepoViagens viagens) {
        this.custoViagem = custoViagem;
        this.selecaoMotorista = selecaoMotorista;
        this.bairros = bairros;
        this.cidades = cidades;
        this.motoristas = motoristas;
        this.passageiros = passageiros;
        this.viagens = viagens;
    }

    public HashMap<String, String> buscaMotoristaPorCPF(String cpf){
        Motorista motoristaAtual = motoristas.recuperarPorCPF(cpf);
        HashMap<String, String> motoristaRequest = new HashMap<>();
        motoristaRequest.put("cpfMotorista", motoristaAtual.getCPF());
        motoristaRequest.put("nome", motoristaAtual.getNome());
        motoristaRequest.put("modelo", motoristaAtual.getVeiculo().getMarca());
        motoristaRequest.put("placa", motoristaAtual.getVeiculo().getPlaca());
        return motoristaRequest;
    }

    public boolean validaPassageiroPorCPF(String cpf){
        try {
            Passageiro p = passageiros.recuperarPorCPF(cpf);
            if (p != null)
                return true;
        }catch(Exception e){
            return false;
        }
        return false;
    }

    public HashMap<String, String> buscaViagemPorId(int id){
        Viagem viagemAtual = viagens.recuperaPorId(id);
        HashMap<String, String> viagemRequest = new HashMap<>();
        viagemRequest.put("cpfPassageiro", viagemAtual.getPassageiro().getCPF());
        viagemRequest.put("cpfMotorista", viagemAtual.getMotorista().getCPF());
        viagemRequest.put("data", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(viagemAtual.getDataHora()));
        viagemRequest.put("bairroOrigem", viagemAtual.getRoteiro().getBairroOrigem().getNome());
        viagemRequest.put("bairroDestino", viagemAtual.getRoteiro().getBairroDestino().getNome());
        viagemRequest.put("custo", Double.toString(viagemAtual.getCusto()));
        return viagemRequest;
    }

    public List<Integer> recuperaViagensDoMotorista(String cpfMotorista) {
        List<Integer> viagensDoMotorista = new ArrayList<>();
        Motorista motorista = motoristas.recuperarPorCPF(cpfMotorista);
        List<Viagem> todasViagens = viagens.getViagens();
        for (Viagem v : todasViagens) {
            if (v.getMotorista().getCPF().equals(motorista.getCPF())) {
                viagensDoMotorista.add(v.getId());
            }
        }
        return viagensDoMotorista;
    }

    public List<String> recuperaNomeBairros(){
        List<String> nomeDosBairros = new ArrayList<>();
        List<Bairro> todosBairros = bairros.getBairros();
        for (Bairro b : todosBairros) {
                nomeDosBairros.add(b.getNome());
        }
        return nomeDosBairros;
    }

    public void avaliaPassageiro(String cpfPassageiro, String nota) {
        Passageiro passageiro = passageiros.recuperarPorCPF(cpfPassageiro);
        passageiro.informaPontuacao(Integer.parseInt(nota));
    }

    public void avaliaMotorista(String cpfMotorista, String nota) {
        Motorista motorista = motoristas.recuperarPorCPF(cpfMotorista);
        motorista.informaPontuacao(Integer.parseInt(nota));
    }

    public int solicitaVeiculoParaViagem(String cpf, String cidade, String bairroOrigem, String bairroDestino, String formaPagamento, String catVeiculo) {
        Passageiro passageiro = passageiros.recuperarPorCPF(cpf);
        Cidade cidadeD = cidades.recuperarPorNome(cidade);
        Bairro origem = bairros.recuperarPorNome(bairroOrigem);
        Bairro destino = bairros.recuperarPorNome(bairroDestino);
        Roteiro roteiro = new Roteiro(cidadeD, origem, destino);
        CategoriaVeiculo c = CategoriaVeiculo.SIMPLES;
        if(catVeiculo.equals("Luxo")){
            c = CategoriaVeiculo.LUXO;
        }else if(catVeiculo.equals("Normal")){
            c = CategoriaVeiculo.NORMAL;
        }
        System.out.println(c);
        Motorista motorista = selecaoMotorista.selecionaMotoristaParaViagem(c, passageiro.getPontuacaoMedia(), RepoMotoristas.getInstance(), passageiro.getFormaPagamento());
        Veiculo veiculo = motorista.getVeiculo();

        double custo = custoViagem.getCusto(roteiro, veiculo.getCat());
        System.out.println(custo);

        Viagem viagem = new Viagem(ID_Sequencial_Viagens.getNextId(), LocalDateTime.now(), roteiro, motorista, passageiro, custo);
        //Persiste viagem no repositorio de viagens
        viagens.adicionaViagem(viagem);

        return viagem.getId();
    }

}
