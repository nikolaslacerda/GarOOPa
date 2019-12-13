package com.tecnicas_pucrs.interfaces.javafx;


import com.tecnicas_pucrs.entidades.Motorista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DadosDoMotorista2Controller implements Initializable {

    @FXML
    private Button btn_avaliar;

    @FXML
    private Button btn_finalizar;

    @FXML
    private Button btn_voltar;

    @FXML
    private Text nome;

    @FXML
    private ListView viagens;

    static int viagemAtual;

    private double x;
    private double y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_avaliar.setDisable(true);

        DadosDoMotoristaController dadosDoMotoristaAtual = null;
        try {
            dadosDoMotoristaAtual = new DadosDoMotoristaController();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.nome.setText(dadosDoMotoristaAtual.retornaNome());

        List<Integer> viagensDoMotoristaAtual = dadosDoMotoristaAtual.retornaViagens();

        for (Integer i : viagensDoMotoristaAtual) {
            viagens.getItems().add("Viagem: " + i);
        }

        btn_voltar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/views/DadosDoMotorista.fxml"));
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

        btn_finalizar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/views/DadosDoMotorista.fxml"));
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

        btn_avaliar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    String viagemSelecionada = viagens.getSelectionModel().getSelectedItem().toString();
                    viagemAtual = Integer.parseInt(viagemSelecionada.replace("Viagem: ", ""));
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/views/AvaliacaoPassageiro.fxml"));
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

                }
            }
        });

        viagens.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
              if (viagens.getSelectionModel().getSelectedItem() != null){
                  btn_avaliar.setDisable(false);
              }else{
                  btn_avaliar.setDisable(true);
              }
            }
        });
    }

    public void exit() {
        System.exit(0);
    }

    public int getViagemAtual(){
        return viagemAtual;
    }

}