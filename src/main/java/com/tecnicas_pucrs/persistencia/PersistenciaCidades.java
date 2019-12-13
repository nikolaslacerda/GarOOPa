package com.tecnicas_pucrs.persistencia;

import com.tecnicas_pucrs.entidades.*;
import com.tecnicas_pucrs.interfaces.repositorios.RepoBairros;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaCidades {

    private static final String CSV_FILE_PATH = "cidades.dat";

    public static List<Cidade> carregaCidades() throws URISyntaxException, IOException {

        List<Cidade> listaCidades = new ArrayList<>();

        try (
                Reader reader = new BufferedReader(new InputStreamReader(PersistenciaVeiculos.class.getResourceAsStream("/cidades.dat")));
        ) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : records) {
                String nome = csvRecord.get("nome");
                String bairros = csvRecord.get("bairros");

                String[] Lbairros = bairros.split(";");

                List<Bairro> listaDeBairro = new ArrayList<>();
                RepoBairros repoBairros = RepoBairros.getInstance();

                for (String b : Lbairros) {
                    listaDeBairro.add(repoBairros.recuperarPorNome(b));
                }

                Cidade cidade = new Cidade(nome, listaDeBairro);
                listaCidades.add(cidade);
            }
        }

        return listaCidades;
    }

    public static boolean persisteCidades(List<Cidade> lst) throws URISyntaxException, IOException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("nome", "bairros"));

            for (Cidade c : lst) {
                String bairros = "";
                for (Bairro b: c.getListaDeBairros()) {
                    bairros += b.getNome() + ";";
                }
                csvPrinter.printRecord(c.getNome(), bairros);
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
