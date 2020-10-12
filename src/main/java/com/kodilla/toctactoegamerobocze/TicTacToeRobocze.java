package com.kodilla.toctactoegamerobocze;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Random;


public class TicTacToeRobocze extends Application {

    private Tile[][] board = new Tile[3][3];
    boolean isCross = false;
    LinkedList<Tile> tiles = new LinkedList<>();

    private javafx.scene.image.Image imageback = new Image("file:src/main/resources/galaxy-background.jpg");

    private Parent createContent() {
        GridPane pane = new GridPane();

        //Pane root = new Pane();
        //root.setPrefSize(600, 600);

        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                pane.getChildren().add(tile);
                //root.getChildren().add(tile);
                tiles.add(tile);
                board[i][j] = tile;
            }
        }

        return pane;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private class Tile extends StackPane {
        private Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(200, 200);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(72));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);


           // while (!checkGame()) {
                setOnMouseClicked(event -> {
                    if (isCross) {
                        drawX();
                    } else {
                        drawO();
                    }
                    checkGame();
                });
            //}
        }

        public Text getText() {
            return text;
        }

        private void drawX() {
            if (text.getText() == null || text.getText().isBlank()) {
                text.setText("X");
                isCross = !isCross;
            }
        }

        private void drawO() {
            if (text.getText() == null || text.getText().isBlank()) {
                text.setText("O");
                isCross = !isCross;
            }
        }

        public void computerMove() {
            Random rand = new Random();
            int move = rand.nextInt(9) + 1;
            if (tiles.get(move).getText() == null) {
                text.setText("O");
                isCross = !isCross;
            } else {
                return;
            }
        }

        private boolean checkGame() {
            //check X
            //horizontal win
            if ((tiles.get(0).getText().getText().equals("X") && tiles.get(1).getText().getText().equals("X") && tiles.get(2).getText().getText().equals("X")) ||
                    (tiles.get(3).getText().equals("X") && tiles.get(4).getText().equals("X") && tiles.get(4).getText().equals("X")) ||
                    (tiles.get(6).getText().equals("X") && tiles.get(7).getText().equals("X") && tiles.get(8).getText().equals("X"))) {
                System.out.println("X wins!");
                return false;
            //vertical win
            } if ((tiles.get(0).getText().getText().equals("X") && tiles.get(3).getText().getText().equals("X") && tiles.get(6).getText().getText().equals("X")) ||
                    (tiles.get(1).getText().equals("X") && tiles.get(4).getText().equals("X") && tiles.get(7).getText().equals("X")) ||
                    (tiles.get(2).getText().equals("X") && tiles.get(5).getText().equals("X") && tiles.get(8).getText().equals("X"))) {
                System.out.println("X wins!");
                return false;
            //diagonal win
            } if ((tiles.get(0).getText().getText().equals("X") && tiles.get(4).getText().getText().equals("X") && tiles.get(8).getText().getText().equals("X")) ||
                    (tiles.get(2).getText().equals("X") && tiles.get(4).getText().equals("X") && tiles.get(6).getText().equals("X"))) {
                System.out.println("X wins!");
                return false;

            //check O
            } if ((tiles.get(0).getText().getText().equals("O") && tiles.get(1).getText().getText().equals("O") && tiles.get(2).getText().getText().equals("O")) ||
                    (tiles.get(3).getText().equals("O") && tiles.get(4).getText().equals("O") && tiles.get(4).getText().equals("O")) ||
                    (tiles.get(6).getText().equals("O") && tiles.get(7).getText().equals("O") && tiles.get(8).getText().equals("O"))) {
                System.out.println("O wins!");
                return false;
                //vertical win
            } if ((tiles.get(0).getText().getText().equals("O") && tiles.get(3).getText().getText().equals("O") && tiles.get(6).getText().getText().equals("O")) ||
                    (tiles.get(1).getText().equals("O") && tiles.get(4).getText().equals("O") && tiles.get(7).getText().equals("O")) ||
                    (tiles.get(2).getText().equals("O") && tiles.get(5).getText().equals("O") && tiles.get(8).getText().equals("O"))) {
                System.out.println("O wins!");
                return false;
                //diagonal win
            } if ((tiles.get(0).getText().getText().equals("O") && tiles.get(4).getText().getText().equals("O") && tiles.get(8).getText().getText().equals("O")) ||
                    (tiles.get(2).getText().equals("O") && tiles.get(4).getText().equals("O") && tiles.get(6).getText().equals("O"))) {
                System.out.println("O wins!");
                return false;
            }
            return true;

        }
    }

// komunikat 'alert'
    public static void main(String[] args) {
        launch(args);
    }
}
