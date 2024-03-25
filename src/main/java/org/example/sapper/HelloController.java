package org.example.sapper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HelloController {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private boolean isGame = true;

    private char[][] gameField = new char[][]{
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
    };

    @FXML
    public void onClick(ActionEvent event) {
        if(!isGame) return;
        Button btn = (Button)event.getSource();
        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);
        if(isBomb(rowIndex,columnIndex)){
            System.out.println(1);
            lose();
        }   else{
            btn.setText(String.valueOf(numNearBomb(rowIndex,columnIndex)));
        }
    }

    @FXML
    void initialize() {
        System.out.println("qq");
        bombs();
    }

    public void bombs(){
        int counter = 0;
        while (counter < 12){
            int indX = (int) (Math.random() * 10);
            int indY = (int) (Math.random() * 10);
            if(gameField[indX][indY] == 'B'){
                continue;
            }
            gameField[indX][indY] = 'B';
            counter++;
            System.out.println(indX + " " + indY);
        }
    }

    public boolean isBomb(int x, int y){
        return gameField[x][y] == 'B';
    }

    public boolean isBeyond(int x, int y){
        return x < 0 || x > 9 || y < 0 || y > 9;
    }

    public int numNearBomb(int x, int y){
        int nearBombs = 0;
        if(!isBeyond(x + 1, y + 1)){
            if(isBomb(x + 1, y + 1)) nearBombs++;
        }
        if(!isBeyond(x + 1, y)){
            if(isBomb(x + 1, y)) nearBombs++;
        }
        if(!isBeyond(x + 1, y - 1)){
            if(isBomb(x + 1, y - 1)) nearBombs++;
        }
        if(!isBeyond(x, y + 1)){
            if(isBomb(x, y + 1)) nearBombs++;
        }
        if(!isBeyond(x, y - 1)){
            if(isBomb(x, y - 1)) nearBombs++;
        }
        if(!isBeyond(x - 1, y + 1)){
            if(isBomb(x - 1,y + 1)) nearBombs++;
        }
        if(!isBeyond(x - 1, y)){
            if(isBomb(x - 1,y)) nearBombs++;
        }
        if(!isBeyond(x - 1, y - 1)){
            if(isBomb(x - 1,y - 1)) nearBombs++;
        }
        System.out.println(nearBombs);
        return nearBombs;
    }

    public void lose(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You LOSER!!!");
        alert.showAndWait();
        isGame = false;
    }
}
