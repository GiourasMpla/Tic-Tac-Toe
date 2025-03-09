/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package tictactoe;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author xlord
 */

public class TicTacToe extends Application {
    
    private final int size=3;
    private Button [][] board= new Button[size][size];
    private Label statusLabel;
    private boolean playerTurn = true; // True for 'X', false for 'O'
    private boolean againstComputer = false;
    private boolean easy=false;
    private boolean normal=false;
    private boolean hard=false;
    private int round=2;
    
    private Scene mainScene(Stage primaryStage){
        //Controlls
        VBox mainMenu = new VBox();
        mainMenu.setSpacing(10);
        mainMenu.setAlignment(Pos.CENTER);
        
        // Set background color
        mainMenu.setStyle("-fx-background-color: linear-gradient(to bottom, #40E0D0, #30B96E);"); // Turquoise-green mix
    
        Label lbl=new Label("Choose game mode:");
        lbl.setFont(new Font("Tempus Sans ITC", 24));
        
        Button vsComputerButton = new Button("Vs Computer");
        vsComputerButton.setFont(new Font("Tempus Sans ITC", 20));
        vsComputerButton.setStyle("-fx-background-color: #ffffff;");
        
        Button vsPlayerButton = new Button("Vs Player");
        vsPlayerButton.setFont(new Font("Tempus Sans ITC", 20));
        vsPlayerButton.setStyle("-fx-background-color: #ffffff;");
        
        //Action Events
        vsComputerButton.setOnAction(e->vsComputerGame(primaryStage));
        vsPlayerButton.setOnAction(e->vsPlayerGame(primaryStage));
        mainMenu.getChildren().addAll(lbl,vsComputerButton,vsPlayerButton);
        
        Scene mainScene = new Scene(mainMenu,300,200);

        return mainScene;
    }
    
    // They set the according scene to stage
    private void vsPlayerGame(Stage primaryStage){
        Scene gameScene = createGameScene();
        primaryStage.setScene(gameScene);
    }
    
    private void vsComputerGame(Stage primaryStage){
        againstComputer=true;
        Scene diffSelScene = difficultySelectionScene(primaryStage);
        primaryStage.setScene(diffSelScene);
    }
    
    //Difficulty selection scene only on vs computer game mode available
    private Scene difficultySelectionScene(Stage primaryStage){
        //Controlls
        BorderPane bd=new BorderPane();
        VBox diffSel=new VBox();
        diffSel.setSpacing(10);
        diffSel.setAlignment(Pos.CENTER);
        
        diffSel.setStyle("-fx-background-color: linear-gradient(to bottom, #40E0D0, #30B96E);"); // Turquoise-green mix
        Label diffLabel=new Label("Select difficulty:");
        diffLabel.setFont(new Font("Tempus Sans ITC", 24));
        
        Button easy=new Button("Easy");
        easy.setFont(new Font("Tempus Sans ITC", 20));
        easy.setStyle("-fx-background-color: #ffffff;");
        
        Button normal=new Button("Normal");
        normal.setFont(new Font("Tempus Sans ITC", 20));
        normal.setStyle("-fx-background-color: #ffffff;");
        
        Button hard=new Button("Hard");
        hard.setFont(new Font("Tempus Sans ITC", 20));
        hard.setStyle("-fx-background-color: #ffffff;");
        
        //Action events
        easy.setOnAction(e->easyGameMode(primaryStage));
        normal.setOnAction(e->normalGameMode(primaryStage));
        hard.setOnAction(e->hardGameMode(primaryStage));
        
        //Layout placement
        diffSel.getChildren().addAll(diffLabel,easy,normal,hard);
        bd.setCenter(diffSel);
        Scene diffScene=new Scene(bd,300,200);
        return diffScene;
    }

    private void easyGameMode(Stage primaryStage){
        easy=true;
        Scene gameScene = createGameScene();
        primaryStage.setScene(gameScene);
    }
    
    private void normalGameMode(Stage primaryStage){
        normal=true;
        Scene gameScene = createGameScene();
        primaryStage.setScene(gameScene);
    }
    
    private void hardGameMode(Stage primaryStage){
        hard=true;
        Scene gameScene = createGameScene();
        primaryStage.setScene(gameScene);
    }
    
    private Scene createGameScene(){
        // Create a GridPane for the game board
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER); // Center the grid pane
        gridPane.setStyle("-fx-background-color: linear-gradient(to bottom, #40E0D0, #30B96E);"); // Set background color
        
        // Initialize the game board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button button = new Button(" ");
                button.setMinSize(100, 100);
                button.setStyle("-fx-font-family: 'Tempus Sans ITC'; -fx-font-size: 40;");
                
                final int row = i;
                final int col = j;

                // Add an event handler for button clicks
                button.setOnAction(event -> handleButtonClick(row, col));

                // Add the button to the grid pane
                board[i][j] = button;
                gridPane.add(button, j, i);
            }
        }
        

        // Create a status label
        statusLabel = new Label("Player X's turn");
        statusLabel.setFont(new Font("Tempus Sans ITC", 24));
        
        // Create a root VBox layout to hold the game board and status label
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(statusLabel, gridPane);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #40E0D0, #30B96E);");

        // Create the game scene
        Scene gameScene = new Scene(root, 300, 350);

        // Return the game scene
        return gameScene;
    }
    
    // Handle button clicks on the game board
    private void handleButtonClick(int row, int col) {
        Button button = board[row][col];

        // If the button is already clicked, return early
        if (!button.getText().equals(" ")) {
            return;
        }

        // Determine the current player's mark
        String currentPlayerMark = playerTurn ? "X" : "O";

        // Set the button's text to the current player's mark
        button.setText(currentPlayerMark);
        if (currentPlayerMark.equals("X")) {
            button.setStyle("-fx-font-family: 'Tempus Sans ITC'; -fx-font-size: 40; -fx-text-fill: red;");
        } else {
            button.setStyle("-fx-font-family: 'Tempus Sans ITC'; -fx-font-size: 40; -fx-text-fill: blue;");
        }
        
        // Check for a win or draw
        if (checkWin(row, col)) {
            statusLabel.setText("Player " + currentPlayerMark + " wins!");
            disableBoard();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
            disableBoard();
        } else { //else play
            //ONLY FOR VS PLAYER MODE
            // Switch turns and update status label
            playerTurn = !playerTurn;
            if (playerTurn) {
                statusLabel.setText("Player X's turn");
            } else {
                statusLabel.setText("Player O's turn");
            }
            
            //ONLY FOR VS COMPUTER MODE
            // If playing against the computer and it's the computer's turn, make a move
            if (againstComputer && !playerTurn) {
                if(easy)
                    makeEasyComputerMove();
                else if(normal)
                    makeNormalComputerMove();
                else if(hard)
                    makeHardComputerMove();
            }
        }
    }

    // Make an easy move for the computer player
    private void makeEasyComputerMove() {
        Random random = new Random();
        int row, col;

        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board[row][col].getText().equals(" "));

        board[row][col].setText("O");
        if (checkWin(row, col)) {
            statusLabel.setText("Computer wins!");
            disableBoard();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
            disableBoard();
        } else {
            playerTurn = true;
            statusLabel.setText("Player X's turn");
        }
    }
    
    // Make a normal move for the computer player
    private void makeNormalComputerMove() {
        Random random = new Random();
        int row, col;
        //Specific variables for rounds
        int num=random.nextInt(100);
        int randomCornerPick=random.nextInt(4);
        int checkTicTac;
        
        do {
            if(num>=50){
                row = random.nextInt(size);
                col = random.nextInt(size);
            }
            else{
                row = random.nextInt(size);
                col = random.nextInt(size);
                if(board[1][1].getText().equals(" ")){
                    row = 1;
                    col = 1;
                }else if(round==2){
                    switch(randomCornerPick){
                        case 0 :
                            row = 0;
                            col = 0;
                        case 1 : 
                            row = 0;
                            col = 2;
                        case 2 :
                            row = 2;
                            col = 0;
                        default:
                            row = 0;
                            col = 2;
                    }
                }else if(round==4 || round==6 || round==8){
                    for(int i=0;i<size;i++){
                        for(int j=0;j<size;j++){
                            //Check if a vertical tic tac toe is going to be formed
                            if(i==0){
                                checkTicTac=0;
                                if(board[i][j].getText().equals("X") && board[i+1][j].getText().equals("X")){
                                    checkTicTac++;
                                    row=i+2;
                                }else if(board[i+1][j].getText().equals("X") && board[i+2][j].getText().equals("X")){
                                    checkTicTac++;
                                    row=i;
                                }else if(board[i][j].getText().equals("X") && board[i+2][j].getText().equals("X")){
                                    checkTicTac++;
                                    row=i+1;
                                }
                                col=j;
                                if(checkTicTac==1)
                                    break;
                            }//Check if a horizontal tic tac toe is going to be formed
                            if(j==0){
                                checkTicTac=0;
                                if(board[i][j].getText().equals("X") && board[i][j+1].getText().equals("X")){
                                    checkTicTac++;
                                    col=j+2;
                                }else if(board[i][j+1].getText().equals("X") && board[i][j+2].getText().equals("X")){
                                    checkTicTac++;
                                    col=j;
                                }else if(board[i][j].getText().equals("X") && board[i][j+2].getText().equals("X")){
                                    checkTicTac++;
                                    col=j+1;
                                }
                                row=i;
                                if(checkTicTac==1)
                                    break;
                            }
                            //Check if a main diagonal tic tac toe is going to be formed
                            if(i==0 && j==0){
                                checkTicTac=0;
                                if(board[i][j].getText().equals("X") && board[i+1][j+1].getText().equals("X")){
                                    checkTicTac++;
                                    row=i+2;
                                    col=i+2;
                                }else if(board[i][j].getText().equals("X") && board[i+2][j+2].getText().equals("X")){
                                    checkTicTac++;
                                    row=i+1;
                                    col=i+1;
                                }else if(board[i+1][j+1].getText().equals("X") && board[i+2][j+2].getText().equals("X")){
                                    checkTicTac++;
                                    row=i;
                                    col=i;
                                }
                                if(checkTicTac==1)
                                    break;
                            }
                            //Check if a secondary diagonal tic tac toe is going to be formed
                            if(i==0 && j==2){
                                checkTicTac=0;
                                if(board[i][j].getText().equals("X") && board[i+1][j-1].getText().equals("X")){
                                    checkTicTac++;
                                    row=i+2;
                                    col=i-2;
                                }else if(board[i][j].getText().equals("X") && board[i+2][j-2].getText().equals("X")){
                                    checkTicTac++;
                                    row=i+1;
                                    col=i-1;
                                }else if(board[i+1][j-1].getText().equals("X") && board[i+2][j-2].getText().equals("X")){
                                    checkTicTac++;
                                    row=i;
                                    col=i;
                                }
                                if(checkTicTac==1)
                                    break;
                            }
                        }
                        break;
                    }
                }
            }
            round+=2;
        } while (!board[row][col].getText().equals(" "));

        board[row][col].setText("O");
        if (checkWin(row, col)) {
            statusLabel.setText("Computer wins!");
            disableBoard();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
            disableBoard();
        } else {
            playerTurn = true;
            statusLabel.setText("Player X's turn");
        }
    }
    
    // Make a hard move for the computer player
    private void makeHardComputerMove() {
        Random random = new Random();
        int row, col;
        int randomCornerPick=random.nextInt(4);
        int checkTicTac;
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
            if(board[1][1].getText().equals(" ")){
                row = 1;
                col = 1;
            }else if(round==2){
                switch(randomCornerPick){
                    case 0 :
                        row = 0;
                        col = 0;
                    case 1 : 
                        row = 0;
                        col = 2;
                    case 2 :
                        row = 2;
                        col = 0;
                    default:
                        row = 0;
                        col = 2;
                }
            }else if(round==4 || round==6 || round==8){
                for(int i=0;i<size;i++){
                    for(int j=0;j<size;j++){
                        //Check if a vertical tic tac toe is going to be formed
                        if(i==0){
                            checkTicTac=0;
                            if(board[i][j].getText().equals("X") && board[i+1][j].getText().equals("X")){
                                checkTicTac++;
                                row=i+2;
                            }else if(board[i+1][j].getText().equals("X") && board[i+2][j].getText().equals("X")){
                                checkTicTac++;
                                row=i;
                            }else if(board[i][j].getText().equals("X") && board[i+2][j].getText().equals("X")){
                                checkTicTac++;
                                row=i+1;
                            }
                            col=j;
                            if(checkTicTac==1)
                                break;
                        }//Check if a horizontal tic tac toe is going to be formed
                        if(j==0){
                            checkTicTac=0;
                            if(board[i][j].getText().equals("X") && board[i][j+1].getText().equals("X")){
                                checkTicTac++;
                                col=j+2;
                            }else if(board[i][j+1].getText().equals("X") && board[i][j+2].getText().equals("X")){
                                checkTicTac++;
                                col=j;
                            }else if(board[i][j].getText().equals("X") && board[i][j+2].getText().equals("X")){
                                checkTicTac++;
                                col=j+1;
                            }
                            row=i;
                            if(checkTicTac==1)
                                break;
                        }
                        //Check if a main diagonal tic tac toe is going to be formed
                        if(i==0 && j==0){
                            checkTicTac=0;
                            if(board[i][j].getText().equals("X") && board[i+1][j+1].getText().equals("X")){
                                checkTicTac++;
                                row=i+2;
                                col=i+2;
                            }else if(board[i][j].getText().equals("X") && board[i+2][j+2].getText().equals("X")){
                                checkTicTac++;
                                row=i+1;
                                col=i+1;
                            }else if(board[i+1][j+1].getText().equals("X") && board[i+2][j+2].getText().equals("X")){
                                checkTicTac++;
                                row=i;
                                col=i;
                            }
                            if(checkTicTac==1)
                                break;
                        }
                        //Check if a secondary diagonal tic tac toe is going to be formed
                        if(i==0 && j==2){
                            checkTicTac=0;
                            if(board[i][j].getText().equals("X") && board[i+1][j-1].getText().equals("X")){
                                checkTicTac++;
                                row=i+2;
                                col=i-2;
                            }else if(board[i][j].getText().equals("X") && board[i+2][j-2].getText().equals("X")){
                                checkTicTac++;
                                row=i+1;
                                col=i-1;
                            }else if(board[i+1][j-1].getText().equals("X") && board[i+2][j-2].getText().equals("X")){
                                checkTicTac++;
                                row=i;
                                col=i;
                            }
                            if(checkTicTac==1)
                                break;
                        }
                    }
                    break;
                }
            }
            round+=2;
        } while (!board[row][col].getText().equals(" "));

        board[row][col].setText("O");
        if (checkWin(row, col)) {
            statusLabel.setText("Computer wins!");
            disableBoard();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
            disableBoard();
        } else {
            playerTurn = true;
            statusLabel.setText("Player X's turn");
        }
    }

    // Check if the current player has won
    private boolean checkWin(int row, int col) {
        String currentPlayerMark = playerTurn ? "X" : "O";

        // Check row
        boolean rowWin = true;
        for (int j = 0; j < size; j++) {
            if (!board[row][j].getText().equals(currentPlayerMark)) {
                rowWin = false;
                break;
            }
        }
        if (rowWin) {
            return true;
        }

        // Check column
        boolean colWin = true;
        for (int i = 0; i < size; i++) {
            if (!board[i][col].getText().equals(currentPlayerMark)) {
                colWin = false;
                break;
            }
        }
        if (colWin) {
            return true;
        }

        // Check diagonal
        boolean diagWin1 = true;
        boolean diagWin2 = true;
        for (int i = 0; i < size; i++) {
            if (!board[i][i].getText().equals(currentPlayerMark)) {
                diagWin1 = false;
            }
            if (!board[i][size - 1 - i].getText().equals(currentPlayerMark)) {
                diagWin2 = false;
            }
        }
        return diagWin1 || diagWin2;
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Disable the board after the game ends
    private void disableBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].setDisable(true);
            }
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Scene mainScene = mainScene(stage);
        
        stage.setTitle("Tic Tac Toe");
        Image icon = new Image(getClass().getResource("ttticon.png").toString());
        stage.getIcons().add(icon);

        stage.setScene(mainScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
