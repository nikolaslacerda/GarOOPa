package com.tecnicas_pucrs.interfaces.javafx;


import com.tecnicas_pucrs.casos_de_uso.servicos.Fachada;
import com.tecnicas_pucrs.casos_de_uso.politicas.CalculoCustoViagem;
import com.tecnicas_pucrs.casos_de_uso.politicas.MotoristaEquivalente;
import com.tecnicas_pucrs.casos_de_uso.politicas.PrecoIntegro;
import com.tecnicas_pucrs.casos_de_uso.politicas.SeletorDeMotorista;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DadosDaSolicitacaoController implements Initializable {

    private Fachada fachada = new Fachada(new CalculoCustoViagem(new PrecoIntegro()), new SeletorDeMotorista(new MotoristaEquivalente()), RepoBairros.getInstance(), RepoCidades.getInstance(), RepoMotoristas.getInstance(), RepoPassageiros.getInstance(), RepoViagens.getInstance());

    @FXML
    private Button btn_buscar;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField cpf_passageiro;

    @FXML
    private ChoiceBox bairro_origem;

    @FXML
    private ChoiceBox bairro_destino;

    @FXML
    private ChoiceBox pagamento;

    @FXML
    private ChoiceBox categoria;

    static int idViagemAtual;

    private double x;
    private double y;

    public DadosDaSolicitacaoController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_buscar.setDisable(true);

        pagamento.getItems().add("Dinheiro");
        pagamento.getItems().add("Débito");
        pagamento.getItems().add("Crédito");

        categoria.getItems().add("Simples");
        categoria.getItems().add("Normal");
        categoria.getItems().add("Luxo");

        List<String> bairros = fachada.recuperaNomeBairros();

        for (String str : bairros) {
            bairro_origem.getItems().add(str);
            bairro_destino.getItems().add(str);
        }

        btn_buscar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (fachada.validaPassageiroPorCPF(cpf_passageiro.getText())) {
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    try {
                        idViagemAtual = fachada.solicitaVeiculoParaViagem(cpf_passageiro.getText(), "porto_alegre", bairro_origem.getSelectionModel().getSelectedItem().toString(), bairro_destino.getSelectionModel().getSelectedItem().toString(), pagamento.getSelectionModel().getSelectedItem().toString(), categoria.getSelectionModel().getSelectedItem().toString());
                        Parent root = FXMLLoader.load(getClass().getResource("/views/DadosDaViagem.fxml"));
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

                } else {
                    cpf_passageiro.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                }
            }
        });

        pagamento.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(!cpf_passageiro.getText().equals("") && !categoria.getSelectionModel().getSelectedItem().toString().equals("") && !bairro_origem.getSelectionModel().getSelectedItem().toString().equals("") && !bairro_destino.getSelectionModel().getSelectedItem().toString().equals(""))
                btn_buscar.setDisable(false);
            }
        });

        categoria.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(!cpf_passageiro.getText().equals("") && !pagamento.getSelectionModel().getSelectedItem().toString().equals("") && !bairro_origem.getSelectionModel().getSelectedItem().toString().equals("") && !bairro_destino.getSelectionModel().getSelectedItem().toString().equals(""))
                    btn_buscar.setDisable(false);
            }
        });

        bairro_origem.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(!cpf_passageiro.getText().equals("") && !categoria.getSelectionModel().getSelectedItem().toString().equals("") && !pagamento.getSelectionModel().getSelectedItem().toString().equals("") && !bairro_destino.getSelectionModel().getSelectedItem().toString().equals(""))
                    btn_buscar.setDisable(false);
            }
        });

        bairro_destino.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(!cpf_passageiro.getText().equals("") && !categoria.getSelectionModel().getSelectedItem().toString().equals("") && !bairro_origem.getSelectionModel().getSelectedItem().toString().equals("") && !pagamento.getSelectionModel().getSelectedItem().toString().equals(""))
                    btn_buscar.setDisable(false);
            }
        });
    }

    public void exit() {
        System.exit(0);
    }

    public static int getViagemAtual() {
        return idViagemAtual;
    }
}