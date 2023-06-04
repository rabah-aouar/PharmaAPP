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

    @FXML
    private ImageView imageView;


    @FXML
    void CaptureONMouseClicked(MouseEvent event) throws InterruptedException {
        imageView.setImage(image);
        thread.stop();
        thread.stop();
    }

    @FXML
    void repeatOnMouseClicked(MouseEvent event) throws InterruptedException {
            /*
        thread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        finalGrabber.start();
                        frame = finalGrabber.grab();
                        image = converter.convert(frame);
                        imageView.setImage(image);
                    } catch (FrameGrabber.Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

             */

        thread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    video = new VideoCapture("http://192.168.11.153:8080/video");
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


    ImageProcessing imageProcessing = new ImageProcessing();







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*
        String rtspUrl = "http://http://192.168.43.1:8080/"; // Replace with your IP camera's RTSP URL


        finalGrabber= new FFmpegFrameGrabber(rtspUrl);
        //finalGrabber.setFormat("h264");

        thread = new Thread(() -> {
                while (true) {
                    synchronized (lock) {
                        try {
                            finalGrabber.start();
                            frame = finalGrabber.grab();
                            image = converter.convert(frame);
                            imageView.setImage(image);
                        } catch (FrameGrabber.Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        thread.start();
        }


        */


        thread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    frame=new Mat();
                    video = new VideoCapture("http://192.168.43.1:8080/video");
                    if (video.isOpened()) System.out.println("it works");
                    video.read(frame);
                    Mat processedFrame = imageProcessing.processFrame(frame);
                    image = imageProcessing.matToImage(processedFrame);
                    imageView.setImage(image);
                }
            }
        });

        thread.start();
    }






}
