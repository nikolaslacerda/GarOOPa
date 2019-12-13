package com.tecnicas_pucrs.casos_de_uso.politicas;

import com.tecnicas_pucrs.interfaces.repositorios.RepoBairros;
import com.tecnicas_pucrs.interfaces.repositorios.RepoCidades;
import com.tecnicas_pucrs.entidades.Bairro;
import com.tecnicas_pucrs.entidades.CategoriaVeiculo;
import com.tecnicas_pucrs.entidades.Cidade;
import com.tecnicas_pucrs.entidades.Roteiro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculoCustoTest {

    private CalculoCustoViagem calculoCustoViagem= new CalculoCustoViagem(new PrecoIntegro());;
    private RepoBairros rb = RepoBairros.getInstance();
    private RepoCidades rc = RepoCidades.getInstance();

    /*@BeforeEach
    public void setup(){

    }*/

    /*
    1- Incluir mais bairros em canoas pra testar nos arquivos dat e nos CSVs abaixo
    2- Os "grupos de teste" são misturas de:
     SIMPLES + NORMAL + LUXO(com 2 e 3?(mais que 2) bairros de trajeto)
     DescontoNatal + PrecoIntegro
     3- padrões:
      SIMPLES - soma base * politica
      NORMAL -  soma base + 10% * politica
      LUXO -    soma base + 10% + 2% (para cada bairro percorrido) * politica
    */

    @DisplayName("Calcula custo de Viagens com PrecoIntegro")
    @ParameterizedTest
    @CsvSource({
            "porto_alegre, independencia, menino_deus, SIMPLES, 60",
            "porto_alegre, independencia, menino_deus, NORMAL, 66",
            "porto_alegre, independencia, menino_deus, LUXO, 69,96",
            "porto_alegre, bom_fim, menino_deus, NORMAL, 50",
            "porto_alegre, bom_fim, menino_deus, SIMPLES, 55",
            "porto_alegre, bom_fim, menino_deus, LUXO, 57,2"
    })
    public void getCustoTestIntegro(String cidade, String bairroOrigem, String bairroDestino, CategoriaVeiculo cat, double respCusto){
        calculoCustoViagem.setPolitica(new PrecoIntegro());
        Cidade c = rc.recuperarPorNome(cidade);
        Bairro bo = rb.recuperarPorNome(bairroOrigem);
        Bairro bd = rb.recuperarPorNome(bairroDestino);
        Roteiro r = new Roteiro(c, bo, bd);
        double observed = calculoCustoViagem.getCusto(r, cat);
        assertEquals(respCusto, observed, 0.0000001);
    }

    @DisplayName("Calcula custo de Viagens com DescontoNatal")
    @ParameterizedTest
    @CsvSource({
            "porto_alegre, agronomia, independencia, SIMPLES, 48",
            "porto_alegre, agronomia, independencia, NORMAL, 52,8",
            "porto_alegre, agronomia, independencia, LUXO, 55,968",
            "porto_alegre, bom_fim, menino_deus, NORMAL, 40",
            "porto_alegre, bom_fim, menino_deus, SIMPLES, 44",
            "porto_alegre, bom_fim, menino_deus, LUXO, 45,76"
    })
    public void getCustoTestDescontoNatal(String cidade, String bairroOrigem, String bairroDestino, CategoriaVeiculo cat, double respCusto){
        calculoCustoViagem.setPolitica(new DescontoNatal());
        Cidade c = rc.recuperarPorNome(cidade);
        Bairro bo = rb.recuperarPorNome(bairroOrigem);
        Bairro bd = rb.recuperarPorNome(bairroDestino);
        Roteiro r = new Roteiro(c, bo, bd);
        double observed = calculoCustoViagem.getCusto(r, cat);
        assertEquals(respCusto, observed, 0.0000001);
    }


}
