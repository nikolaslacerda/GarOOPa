package com.tecnicas_pucrs.interfaces.javafx;


import com.tecnicas_pucrs.casos_de_uso.servicos.Fachada;
import com.tecnicas_pucrs.casos_de_uso.politicas.*;
import com.tecnicas_pucrs.interfaces.repositorios.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DadosDaViagemController implements Initializable {

    private Fachada fachada = new Fachada(new CalculoCustoViagem(new PrecoIntegro()), new SeletorDeMotorista(new MelhorMotorista()), RepoBairros.getInstance(), RepoCidades.getInstance(), RepoMotoristas.getInstance(), RepoPassageiros.getInstance(), RepoViagens.getInstance());

    @FXML
    private Button btn_finalizar;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_voltar;

    @FXML
    private Text motorista;

    @FXML
    private Text placa;

    @FXML
    private Text modelo;

    @FXML
    private Text custo;

    static String cpfMotoristaAtual;
    private double x;
    private double y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DadosDaSolicitacaoController dadosDaSolicitacao = new DadosDaSolicitacaoController();

        int viagemId = dadosDaSolicitacao.getViagemAtual();

        System.out.println(viagemId);

        HashMap<String, String> res = fachada.buscaViagemPorId(viagemId);


        cpfMotoristaAtual = res.get("cpfMotorista");

        custo.setText(res.get("custo"));

        HashMap<String, String> res2 = fachada.buscaMotoristaPorCPF(cpfMotoristaAtual);

        motorista.setText(res2.get("nome"));
        modelo.setText(res2.get("modelo"));
        placa.setText(res2.get("placa"));

        btn_finalizar.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/views/AvaliacaoMotorista.fxml"));
                    root.setOnMousePressed(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            x = event.getSceneX();
                            y = event.getSceneY();
                        }
                    });
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
                        }
                    });

                    stage.setScene(scene);
                    stage.setTitle("UBER");
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    Image applicationIcon = new Image(getClass().getResourceAsStream("/img/logo.png"));
                    stage.getIcons().add(applicationIcon);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void exit() {
        System.exit(0);
    }

    public static String getCpfMotorista() {
        return cpfMotoristaAtual;
    }
}