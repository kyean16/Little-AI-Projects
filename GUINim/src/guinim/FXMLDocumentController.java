/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guinim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import guinim.nim.Nim;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Keyean
 */
public class FXMLDocumentController{
    
    @FXML // fx:id="easyButton"
    private Button easyButton;
    @FXML //fx:id = startButton
    private Button startButton;
    @FXML // fx:id="textArea"
    private TextArea textArea;
    @FXML //fx:id = "sticksTextArea"
    private TextArea sticksTextArea;
    @FXML //fx:id = "fieldTextArea"
    private TextField fieldTextArea;
    @FXML // fx:id="label"
    private Label label;
    @FXML //fix:id ="difficultyLabel"
    private Label difficultyLabel;
    
    //Private variables
    private String difficulty = null;
    private Nim game = null;
    
    @FXML //EasyButton
    private void easyButtonClick(ActionEvent event) {
        difficulty = "easy";
        difficultyLabel.setText("Difficulty: Easy");
        easyButton.setDisable(true);
        startButton.setDisable(false);
    }
    
    @FXML //startButton
    private void startButtonClick(ActionEvent event){
        if(difficulty!=null)
        {
            textArea.setText("");
            startButton.setText("Restart");
            Start();
        }
    }
    
    @FXML //When the textfield is selected and pressed
    private void onEnterPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER)
        {
            Player(); //Start the player turns.
        }
    }
    @FXML // Empty
    void initialize(){
        startButton.setDisable(true);
    }    
    
    private void Start()  //Sets ups the game depending on the rules set up by the player.
    {
        fieldTextArea.setEditable(true);//Enable ediditing of the textfield
        game = new Nim(); //Sets up the game
        printSticks(); //Prints the number sticks
        printHumanTurn(); //Human begins.
    }
      
    private void Player() //Human Turn.
    {
        String input = fieldTextArea.getText(); //Get the input of the player
        //If the input is incorrect. It remains the player turn as it does not call AI
        if("This is not a correct input try again".equals(game.Player(input)))
        { 
            label.setText(game.Player(input)+'\n');
            fieldTextArea.setText("");
        }
        //Right input, does the job and calls AI if the game is not over
        else
        {
            label.setText(""); //Reset label
            textArea.appendText(input + "\n"); //Add to the text area
            fieldTextArea.setText(""); //Reset field text
            printSticks(); //Update remaining number of sticks
            if(!checkGame()) //Check if game won
            {
                fieldTextArea.setEditable(false); //Disable player
                AI(); //AI turn
            }
        }
    }
    
    private void AI()
    {
                textArea.appendText( "---AI Turn \n" ); //Prints turn
                textArea.appendText(game.DumbAi()); //Add text
                printSticks(); //Print remaining number of sticks
                if(!checkGame())//If game is not over
                {
                    printHumanTurn();
                    fieldTextArea.setEditable(true); //Enable player
                }
    }
    
    //Prints the number of sticks
    private void printSticks()
    {
        sticksTextArea.setText(game.printNim());
    }
    
    //Print human turn.
    private void printHumanTurn()
    {
                textArea.setText(textArea.getText()
                                + "---Human Turn \n"
                                + "How many would sticks would like to take from 1 to " 
                                + game.possibleNumMoves + " : \n");
    }
    
    //Check if the game is over
    private boolean checkGame()
    {
        if(game.totalNim == 0)
        {
            textArea.appendText(game.printWinner());//Since zero check who won;
            endGame();
            return true;
        }
        return false;
    } 
    
    //Ends game set everything to zero.
    private void endGame()
    {
        fieldTextArea.setEditable(false);
        game = null;
        startButton.setText("Start");
    }
}

