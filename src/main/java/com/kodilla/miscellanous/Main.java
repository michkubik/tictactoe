package com.kodilla.miscellanous;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

        public static void main(String[]args){
        launch(args);
        }

    @Override
    public  void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe Game");


        StackPane layout = new StackPane();

        Scene scene = new Scene(layout, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
