package com.pharmaApp.pharmaAppFX;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;


import com.pharmaApp.pharmaAppFX.controllers.CaptureCameraController;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class Main extends Application {


    public static void main(String[] args) {
        nu.pattern.OpenCV.loadShared();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

            FXMLLoader loginLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
            Scene scene = new Scene(loginLoader.load(), 705, 542);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

    }

    @Override
    public void stop() throws Exception {
        CaptureCameraController.thread.stop();
        super.stop();
    }
}