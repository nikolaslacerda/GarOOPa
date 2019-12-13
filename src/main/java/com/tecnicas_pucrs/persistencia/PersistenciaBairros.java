package com.tecnicas_pucrs.persistencia;

import com.tecnicas_pucrs.entidades.*;
import com.tecnicas_pucrs.entidades.geometria.Area;
import com.tecnicas_pucrs.entidades.geometria.Ponto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaBairros {

    private static final String CSV_FILE_PATH = "bairros.dat";

    public static List<Bairro> carregaBairros() throws IOException {

        List<Bairro> listaBairros = new ArrayList<>();

        try (
                Reader reader = new BufferedReader(new InputStreamReader(PersistenciaVeiculos.class.getResourceAsStream("/bairros.dat")));
        ) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : records) {
                String nome = csvRecord.get("nome");
                int x1 = Integer.parseInt(csvRecord.get("x1"));
                int y1 = Integer.parseInt(csvRecord.get("y1"));
                int x2 = Integer.parseInt(csvRecord.get("x2"));
                int y2 = Integer.parseInt(csvRecord.get("y2"));
                int custo = Integer.parseInt(csvRecord.get("custo"));

                Bairro bairro = new Bairro(nome, new Area(new Ponto(x1, y1), new Ponto(x2, y2)), custo);
                listaBairros.add(bairro);
            }
        }

        return listaBairros;
    }

    public static boolean persisteBairros(List<Bairro> lst) throws URISyntaxException, IOException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("nome", "x1", "y1", "x2", "y2", "custo"));
            for (Bairro b : lst) {
                csvPrinter.printRecord(b.getNome(),
                        b.getLimites().getPSupEsq().x,
                        b.getLimites().getPSupEsq().y,
                        b.getLimites().getPInfDir().x,
                        b.getLimites().getPInfDir().y,
                        b.getCustoBasico()
                );
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