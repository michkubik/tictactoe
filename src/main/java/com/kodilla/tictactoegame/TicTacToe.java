package com.kodilla.tictactoegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.Random;

public class TicTacToe extends Application {
    // Which player has a turn, initially it is the X player
    private char whoseTurn = 'X';
    private Label label = new Label("X's turn to play");
    private Cell[][] cell =  new Cell[3][3];

    private Image backImage = new Image("file:src/main/resources/galaxy-background.jpg");
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
    BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background = new Background(backgroundImage);

    Scene scene, startScene;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Button 1
        Label label1 = new Label("Welcome to the Tic Tac Toe Game!");
        Button buttonStandardGame = new Button("Start a standard game");
        buttonStandardGame.setOnAction(e -> primaryStage.setScene(scene));
        Button buttonDifficultGame = new Button("Start a difficult game");
        buttonDifficultGame.setOnAction(e -> AlertBox.display("Under construction", "Not ready yet. Sorry!"));
        Button buttonClose = new Button("Quit");
        buttonClose.setOnAction(e -> primaryStage.close());


        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.setBackground(background);
        layout1.getChildren().addAll(buttonStandardGame, buttonDifficultGame, buttonClose);
        layout1.setAlignment(Pos.CENTER);
        startScene = new Scene(layout1, 350, 300);


        // Pane to hold cell
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pane.add(cell[i][j] = new Cell(), j, i);
            }
        }

        Button closeButton = new Button("End game");
        closeButton.setOnAction(e -> primaryStage.close());
        Button returnButton = new Button("Return to main menu");
        returnButton.setOnAction(e -> primaryStage.setScene(startScene));

        HBox downButtons = new HBox(20);
        downButtons.getChildren().addAll(returnButton, closeButton);
        downButtons.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setTop(label);
        borderPane.setBottom(downButtons);


        scene = new Scene(borderPane, 450, 450);

        primaryStage.setTitle("Tic Tac Toe"); // Set the stage title
        primaryStage.setScene(startScene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (cell[i][j].getToken() == ' ')
                    return false;

        return true;
    }
// uprościć sprawdzanie oparte na IF - algorytm(?)
    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++)
            if (cell[i][0].getToken() == token
                    && cell[i][1].getToken() == token
                    && cell[i][2].getToken() == token) {
                return true;
            }

        for (int j = 0; j < 3; j++)
            if (cell[0][j].getToken() ==  token
                    && cell[1][j].getToken() == token
                    && cell[2][j].getToken() == token) {
                return true;
            }

        if (cell[0][0].getToken() == token
                && cell[1][1].getToken() == token
                && cell[2][2].getToken() == token) {
            return true;
        }

        if (cell[0][2].getToken() == token
                && cell[1][1].getToken() == token
                && cell[2][0].getToken() == token) {
            return true;
        }

        return false;
    }

    public class Cell extends StackPane {
        private char token = ' ';
        private Text text = new Text();

        public Cell() {
            setStyle("-fx-border-color: black");
            this.setPrefSize(800, 800);
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        public char getToken() {
            return token;
        }

        public void setToken(char c) {
            token = c;

            if (token == 'X') {
                text.setText("X");
                text.setFont(Font.font(80));
                text.setFill(Color.BLUE);
                getChildren().addAll(text);
            }
            else if (token == 'O') {
                text.setText("O");
                text.setFont(Font.font(80));
                text.setFill(Color.GREEN);
                getChildren().addAll(text);
            }
        }

        private void handleMouseClick() {
            // If cell is empty and game is not over
            if (token == ' ' && whoseTurn != ' ') { //jak zrobic, żeby móc klikać na pełnych polach też?

                if (whoseTurn != 'X') {
                    Random randX = new Random();
                    Random randY = new Random();
                    boolean isSet = false;
                    while (!isSet) {
                        int cellX = randX.nextInt(3);
                        int cellY = randY.nextInt(3);
                        System.out.println("X = " + cellX);
                        System.out.println("Y = " + cellY);
                        if (cell[cellX][cellY].getToken() == ' ') {
                            cell[cellX][cellY].setToken(whoseTurn);
                            isSet = true;
                        }
                    }
                }

                else {
                    setToken(whoseTurn); // Set token in the cell
                }

                if (isWon(whoseTurn)) {
                    //label.setText(whoseTurn + " won! The game is over");
                    AlertBox.display("End of Game", whoseTurn + " won! \nThe game is over");
                    whoseTurn = ' '; // Game is over
                }
                else if (isFull()) {
                    //label.setText("Draw! The game is over");
                    AlertBox.display("End of Game", "Draw! \nThe game is over");
                    whoseTurn = ' '; // Game is over
                }
                else {
                    // Change the turn
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                    // Display whose turn
                    label.setText(whoseTurn + "'s turn");
                }
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}