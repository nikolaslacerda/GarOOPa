package com.tecnicas_pucrs.persistencia;

import com.tecnicas_pucrs.entidades.CategoriaVeiculo;
import com.tecnicas_pucrs.entidades.Veiculo;
import com.tecnicas_pucrs.entidades.VeiculosFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaVeiculos {

    private static final String CSV_FILE_PATH = "veiculos.dat";
    //static InputStream in = PersistenciaVeiculos.class.getResourceAsStream("/veiculos.dat");

    public static List<Veiculo> carregaVeiculos() throws FileNotFoundException, IOException, URISyntaxException {

        List<Veiculo> listVeiculos = new ArrayList<>();

        try (
                Reader reader = new BufferedReader(new InputStreamReader(PersistenciaVeiculos.class.getResourceAsStream("/veiculos.dat")));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : records) {
                String placa = csvRecord.get("placa");
                String marca = csvRecord.get("marca");
                String cor = csvRecord.get("cor");
                String categoria = csvRecord.get("categoria");

                CategoriaVeiculo cat = CategoriaVeiculo.SIMPLES;
                if (categoria.toUpperCase().equals("SIMPLES")) cat = CategoriaVeiculo.SIMPLES;
                if (categoria.toUpperCase().equals("NORMAL")) cat = CategoriaVeiculo.NORMAL;
                if (categoria.toUpperCase().equals("LUXO")) cat = CategoriaVeiculo.LUXO;

                Veiculo veiculo = VeiculosFactory.getVeiculo(placa, marca, cor, cat);
                listVeiculos.add(veiculo);
            }
        }

        return listVeiculos;
    }

    public static boolean persisteVeiculos(List<Veiculo> lst) throws IOException, URISyntaxException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("placa", "marca", "cor", "categoria"));
            for (Veiculo v : lst) {
                if (v.getCat().equals(CategoriaVeiculo.SIMPLES)) {
                    csvPrinter.printRecord(v.getPlaca(), v.getMarca(), v.getCor(), "SIMPLES");
                };
                if (v.getCat().equals(CategoriaVeiculo.NORMAL)) {
                    csvPrinter.printRecord(v.getPlaca(), v.getMarca(), v.getCor(), "NORMAL");
                };
                if (v.getCat().equals(CategoriaVeiculo.LUXO)) {
                    csvPrinter.printRecord(v.getPlaca(), v.getMarca(), v.getCor(), "LUXO");
                };
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
