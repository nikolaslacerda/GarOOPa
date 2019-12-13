package com.tecnicas_pucrs.entidades;

public class Motorista{
    
    private String cpf;
    private String nome;
    private Veiculo veiculo;
    private FormaPagamento pagamento;
    private int somatorioDeAvaliacoes;
    private int quantidadeDeAvaliacoes;
    private int pontuacaoMedia;

    public Motorista(String cpf, String nome, Veiculo v, FormaPagamento f, int pontuacaoMedia){
        this.cpf = cpf;
        this.nome = nome;
        this.veiculo = v;
        this.pagamento = f;
        this.somatorioDeAvaliacoes = 0;
        this.quantidadeDeAvaliacoes = 0;
        this.pontuacaoMedia = pontuacaoMedia;
    }

    public String getCPF(){
        return cpf;
    }

    public String getNome(){
        return nome;
    }

    public Veiculo getVeiculo(){
        return veiculo;
    }
    
    public FormaPagamento getFormaPagamento(){
        return pagamento;
    }

    public int getQuantidadeDeAvaliacoes() {
        return quantidadeDeAvaliacoes;
    }

    public void informaPontuacao(int pontuacao){
        quantidadeDeAvaliacoes++;
        setPontuacaoMedia(getPontuacaoMedia() + (pontuacao / getQuantidadeDeAvaliacoes()));
    }

    public void setPontuacaoMedia(int pontuacaoMedia) {
        this.pontuacaoMedia = pontuacaoMedia;
    }

    public int getPontuacaoMedia() {
        return pontuacaoMedia;
    }

    @Override
    public String toString() {
        return  "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", veiculo=(" + veiculo + ")" +
                ", pagamento=" + pagamento +
                ", somatorioDeAvaliacoes=" + somatorioDeAvaliacoes;
    }
}