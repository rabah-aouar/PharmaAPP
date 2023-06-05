package com.pharmaApp.pharmaAppFX.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio.*;


import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import org.opencv.core.Mat;
import org.opencv.videoio.Videoio.*;

import static org.opencv.videoio.Videoio.CAP_DSHOW;
import static org.opencv.videoio.Videoio.CAP_MSMF;


public class CaptureCameraController implements Initializable {

    public Mat frame;
    public Image image;
    public static Thread thread;
    public static Object lock = new Object();
    public VideoCapture video;
    ImageProcessing imageProcessing = new ImageProcessing();

    @FXML
    private ImageView imageView;
    private String scriptPath;


    @FXML
    void CaptureONMouseClicked(MouseEvent event) throws InterruptedException {
        thread.stop();
        thread.stop();
        imageView.setImage(image);
        String workingDir = System.getProperty("user.dir");

        // Define the relative path and filename
        String relativePath = "images/captured_image.jpg"; // Modify the path and filename as needed

        // Create the absolute path
        String imagepath = String.valueOf(Path.of(workingDir, relativePath));

         ImageProcessing.executePythonFile(scriptPath,imagepath);


    }

    @FXML
    void repeatOnMouseClicked(MouseEvent event) throws InterruptedException {

        thread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    video = new VideoCapture(0);
                    if (video.isOpened()) System.out.println("it works 22");
                    video.read(frame);
                    Mat processedFrame = imageProcessing.processFrame(frame);
                    image = imageProcessing.matToImage(processedFrame);
                    imageView.setImage(image);
                }
            }
        });

        thread.start();


    }










    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        thread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    frame=new Mat();
                    video = new VideoCapture(0);
                    if (video.isOpened()) System.out.println("it works");
                    video.read(frame);
                    Mat processedFrame = imageProcessing.processFrame(frame);
                    image = imageProcessing.matToImage(processedFrame);
                    imageView.setImage(image);
                }
            }
        });

        thread.start();



        String workingDir = System.getProperty("user.dir");

        // Define the relative path and filename
        String relativePath = "images/new_product.py"; // Modify the path and filename as needed

        // Create the absolute path
        scriptPath = String.valueOf(Path.of(workingDir, relativePath));
    }






}
