package com.tecnicas_pucrs.persistencia;

import com.tecnicas_pucrs.interfaces.repositorios.RepoBairros;
import com.tecnicas_pucrs.interfaces.repositorios.RepoCidades;
import com.tecnicas_pucrs.interfaces.repositorios.RepoMotoristas;
import com.tecnicas_pucrs.interfaces.repositorios.RepoPassageiros;
import com.tecnicas_pucrs.entidades.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaViagens {

    private static final String CSV_FILE_PATH = "viagens.dat";

    public static List<Viagem> carregaViagens() throws FileNotFoundException, URISyntaxException, IOException {

        List<Viagem> listaViagens = new ArrayList<>();

        try (
                Reader reader = new BufferedReader(new InputStreamReader(PersistenciaVeiculos.class.getResourceAsStream("/viagens.dat")));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            RepoBairros repoBairros = RepoBairros.getInstance();
            RepoCidades repoCidades = RepoCidades.getInstance();
            RepoMotoristas repoMotoristas = RepoMotoristas.getInstance();
            RepoPassageiros repoPassegeiros = RepoPassageiros.getInstance();

            for (CSVRecord csvRecord : records) {
                int id = Integer.parseInt(csvRecord.get("id"));
                String data = csvRecord.get("dataHora");
                String roteiro = csvRecord.get("roteiro");
                Motorista motorista = repoMotoristas.recuperarPorCPF(csvRecord.get("motorista"));
                Passageiro passageiro = repoPassegeiros.recuperarPorCPF(csvRecord.get("passageiro"));
                double custo = Double.parseDouble(csvRecord.get("custo"));
                String[] Lroteiros = roteiro.split(";");

                Roteiro roteirow = new Roteiro(repoCidades.recuperarPorNome("porto_alegre"), repoBairros.recuperarPorNome(Lroteiros[0]), repoBairros.recuperarPorNome(Lroteiros[1]));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                Viagem viagem = new Viagem(id, LocalDateTime.parse(data, formatter),roteirow,motorista,passageiro,custo);
                listaViagens.add(viagem);
            }
        }

        return listaViagens;
    }

    public static boolean persisteViagens(List<Viagem> lst) throws URISyntaxException, IOException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("id", "dataHora", "roteiro", "motorista", "passageiro", "custo"));
            for (Viagem v : lst) {
                csvPrinter.printRecord(v.getId(), v.getDataHora().toString().replace("T", " "), v.getRoteiro(), v.getMotorista().getCPF(), v.getPassageiro().getCPF(), v.getCusto());
            }
            csvPrinter.flush();
            csvPrinter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
