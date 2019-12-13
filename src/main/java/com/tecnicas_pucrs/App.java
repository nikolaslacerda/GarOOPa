package com.tecnicas_pucrs;

import com.tecnicas_pucrs.interfaces.repositorios.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;

public class App extends Application {

    private double x;
    private double y;

    public Parent telaPrincipal() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/TelaPrincipal.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });



        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {

        try {
            stage.setTitle("UBER");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            Parent root = telaPrincipal();
            Scene scene = new Scene(root);
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                }
            });
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            Image applicationIcon = new Image(getClass().getResourceAsStream("/img/logo.png"));
            stage.getIcons().add(applicationIcon);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro!" + e);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        RepoVeiculos repoVeiculos = RepoVeiculos.getInstance();
        RepoMotoristas repoMotoristas = RepoMotoristas.getInstance();
        RepoPassageiros repoPassageiros = RepoPassageiros.getInstance();
        RepoBairros repoBairros = RepoBairros.getInstance();
        RepoCidades repoCidades = RepoCidades.getInstance();
        RepoViagens repoViagens = RepoViagens.getInstance();

        launch(args);

        repoVeiculos.persisteVeiculos();
        repoMotoristas.persisteMotoristas();
        repoPassageiros.persistePassageiros();
        repoBairros.persisteBairros();
        repoCidades.persisteCidades();
        repoViagens.persisteViagens();



    }
}
