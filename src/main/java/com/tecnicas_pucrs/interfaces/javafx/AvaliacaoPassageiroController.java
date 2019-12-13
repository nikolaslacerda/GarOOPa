package com.tecnicas_pucrs.interfaces.javafx;


import com.tecnicas_pucrs.casos_de_uso.servicos.Fachada;
import com.tecnicas_pucrs.casos_de_uso.politicas.*;
import com.tecnicas_pucrs.interfaces.repositorios.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AvaliacaoPassageiroController implements Initializable {

    private Fachada fachada = new Fachada(new CalculoCustoViagem(new PrecoIntegro()), new SeletorDeMotorista(new MelhorMotorista()), RepoBairros.getInstance(), RepoCidades.getInstance(), RepoMotoristas.getInstance(), RepoPassageiros.getInstance(), RepoViagens.getInstance());

    @FXML
    private Button btn_finalizar;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_voltar;

    @FXML
    private Text data;

    @FXML
    private Text bairroOrigem;

    @FXML
    private Text bairroDestino;

    @FXML
    private Text custo;

    @FXML
    private ChoiceBox avaliacao;

    private double x;
    private double y;

    public AvaliacaoPassageiroController() throws IOException, URISyntaxException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_finalizar.setDisable(true);
        DadosDoMotorista2Controller dadosDaViagem = null;
        dadosDaViagem = new DadosDoMotorista2Controller();

        int viagemId = dadosDaViagem.getViagemAtual();

        System.out.println(viagemId);

        HashMap<String, String> res = fachada.buscaViagemPorId(viagemId);

        String cpfPassageiro = res.get("cpfPassageiro");
        data.setText(res.get("data"));
        bairroOrigem.setText(res.get("bairroOrigem"));
        bairroDestino.setText(res.get("bairroDestino"));
        custo.setText(res.get("custo"));

        avaliacao.getItems().add("1");
        avaliacao.getItems().add("2");
        avaliacao.getItems().add("3");
        avaliacao.getItems().add("4");
        avaliacao.getItems().add("5");

        btn_voltar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/views/DadosDoMotorista2.fxml"));
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

        avaliacao.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    btn_finalizar.setDisable(false);
            }
        });

        btn_finalizar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (avaliacao.getValue() != null) {
                    fachada.avaliaPassageiro(cpfPassageiro, avaliacao.getValue().toString());
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/views/DadosDoMotorista2.fxml"));
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
            }
        });


    }

    public void exit() {
        System.exit(0);
    }

}