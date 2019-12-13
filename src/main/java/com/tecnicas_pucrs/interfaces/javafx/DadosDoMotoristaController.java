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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class DadosDoMotoristaController implements Initializable {

    private Fachada fachada = new Fachada(new CalculoCustoViagem(new PrecoIntegro()), new SeletorDeMotorista(new MelhorMotorista()), RepoBairros.getInstance(), RepoCidades.getInstance(), RepoMotoristas.getInstance(), RepoPassageiros.getInstance(), RepoViagens.getInstance());

    @FXML
    private Button btn_buscar;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField cpf_motorista;

    static String nomeDoMotoristaAtual = "";
    static List<Integer> viagensDoMotoristaAtual = new ArrayList<>();

    private double x;
    private double y;

    public DadosDoMotoristaController() throws IOException, URISyntaxException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_voltar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/views/TelaPrincipal.fxml"));
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

        btn_buscar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    HashMap<String, String> res = fachada.buscaMotoristaPorCPF(cpf_motorista.getText());
                    nomeDoMotoristaAtual = res.get("nome");
                    viagensDoMotoristaAtual = fachada.recuperaViagensDoMotorista(cpf_motorista.getText());

                    ((Node) (event.getSource())).getScene().getWindow().hide();
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
                } catch (Exception e) {
                    cpf_motorista.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                    //System.out.println("Motorista Inválido");
                }
            }
        });
    }

    public String retornaNome() {
        return nomeDoMotoristaAtual;
    }

    public List<Integer> retornaViagens() {
        return viagensDoMotoristaAtual;
    }

    public void exit() {
        System.exit(0);
    }

}