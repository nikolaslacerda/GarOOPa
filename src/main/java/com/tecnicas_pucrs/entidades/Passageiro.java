package com.tecnicas_pucrs.entidades;

public class Passageiro{

    private String cpf;
    private String nome;
    private FormaPagamento formaPagamento;
    private String nroCartao;
    private double pontuacaoMedia;
    private int countAvaliacoes;

    public Passageiro(String nome, String cpf, FormaPagamento formaPagamento, String nroCartao, double pontuacaoMedia){
        this.cpf = cpf;
        this.nome = nome;
        this.formaPagamento = formaPagamento;
        this.nroCartao = nroCartao;
        this.pontuacaoMedia = pontuacaoMedia;
    } 

    public String getCPF(){
        return this.cpf;
    }

    public String getNome(){
        return this.nome;
    }

    public FormaPagamento getFormaPagamento(){
        return this.formaPagamento;
    }

    public String getNroCartao(){
        return this.nroCartao;
    }

    public void setPontuacaoMedia(double pontuacao){
        this.pontuacaoMedia = pontuacao;
    }

    public double getPontuacaoMedia(){
        return this.pontuacaoMedia;
    }

    public int getCountAvaliacoes(){
        return this.countAvaliacoes;
    }

    public void informaPontuacao(int pontuacao){
        countAvaliacoes++;
        setPontuacaoMedia(getPontuacaoMedia() + (pontuacao / getCountAvaliacoes()));
    }

    @Override
    public String toString() {
        return "Passageiro{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", formaPagamento=" + formaPagamento +
                ", nroCartao='" + nroCartao + '\'' +
                ", pontuacaoMedia=" + pontuacaoMedia +
                ", countAvaliacoes=" + countAvaliacoes +
                '}';
    }
}