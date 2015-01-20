/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetatictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.swing.JFrame;

/**
 *
 * @author Keyean
 */
public class TicTacToe implements Initializable {
    
    //Global Variables
    private int[][]tempState; //2D Array
    private boolean turn; //true: Player 1 ; false: Player 2
    private boolean PlayerTurn; //true: Player Starts, false: Player finishes
    private TicTacToeState currentGame;
    private int win; //Keeps a tally of wins
    private int loss; //Keeps a tally of losses
    private int draw; //Keeps a tally draws.

    //Objects in the GUI
    @FXML
    private GridPane boardPane;
    @FXML
    private AnchorPane menu;
    @FXML
    private Button loadStateButton; //handleLoadStateButtonAction()
    @FXML
    private Button newGame; //handleNewGame()
    @FXML
    private Label turnLabel;
    @FXML
    private Label helpLabel;
    @FXML
    private Label winLabel;
    @FXML
    private Label lossLabel;
    @FXML
    private Label drawLabel;
    @FXML
    private Button displayMinMax;
    @FXML
    private Button buttonOne; //handleButtons //Mouse Hover
    @FXML
    private Button buttonTwo; //handleButtons
    @FXML
    private Button buttonThree; //handleButtons
    @FXML
    private Button buttonFour; //handleButtons
    @FXML
    private Button buttonFive; //handleButtons
    @FXML
    private Button buttonSix; //handleButtons
    @FXML
    private Button buttonSeven; //handleButtons
    @FXML
    private Button buttonEight; //handleButtons
    @FXML
    private Button buttonNine; //handleButtons
    
    /**
     * Loads a Pre Created 2-D Array and starts the game
     * @param event 
     */
    @FXML
    private void handleLoadStateButtonAction() {
        boardPane.setDisable(false); //Enable the Pane
        tempState = new int[3][3];
        tempState[0][0] = -1;
        tempState[0][1] = -1;
        tempState[0][2] = 0;
        tempState[1][0] = 1;
        tempState[1][1] = 1;
        tempState[1][2] = -1 ;
        tempState[2][0] = 0;
        tempState[2][1] = 0;
        tempState[2][2] = 1;
        currentGame = new TicTacToeState(tempState, turn); //Creates new TicTacToeState
        setGUI(currentGame); //Updates GUI
        helpLabel.setText("Player: O , AI: X");
        if(!turn) //If False
            AI(); //Start AI Turn
    }
    
    /**
     * Launches a new Game
     */
    @FXML
    public void handleNewGame()
    {
        boardPane.setDisable(false);
        tempState = new int[3][3];
        PlayerTurn = !PlayerTurn;
        currentGame = new TicTacToeState(tempState, turn);
        if(!PlayerTurn)
        {
            AI();
            helpLabel.setText("Player: O , AI: X");
        }
        else
        {
            helpLabel.setText("Player: X , AI: O");
        }
        setGUI(currentGame);
    }
    
    @FXML
    private void handleButtons(ActionEvent event) throws InterruptedException
    {   
        int value;
        if(PlayerTurn)
            value = 1;
        else
            value = -1;
        if(turn) //If True
        {
            String button = (event.getSource().toString()); //Gets the toString value of the button pressed
            switch(button)
            {
                    case "Button[id=buttonOne, styleClass=button]''":
                                     update(buttonOne,0,0,value);
                                     break;
                    case "Button[id=buttonTwo, styleClass=button]''":
                                     update(buttonTwo,0,1,value);
                                     break;
                    case "Button[id=buttonThree, styleClass=button]''":
                                     update(buttonThree,0,2,value);
                                     break;
                    case "Button[id=buttonFour, styleClass=button]''":
                                     update(buttonFour,1,0,value);
                                     break;
                    case "Button[id=buttonFive, styleClass=button]''":
                                     update(buttonFive,1,1,value);
                                     break;
                    case "Button[id=buttonSix, styleClass=button]''":
                                     update(buttonSix,1,2,value);
                                     break;
                    case "Button[id=buttonSeven, styleClass=button]''":
                                     update(buttonSeven,2,0,value);
                                     break;
                    case "Button[id=buttonEight, styleClass=button]''":
                                     update(buttonEight,2,1,value);
                                     break;
                    case "Button[id=buttonNine, styleClass=button]''":
                                     update(buttonNine,2,2,value);
                                     break;
                    default : System.out.println("Yo");
                            break;
            }
            currentGame = new TicTacToeState(tempState, turn);
            if(currentGame.getWinCondition() == 2)
            {
                turn = !turn;
                setGUI(currentGame);
                try 
                {  
                 Thread.sleep(100);  
                }  
                catch (InterruptedException e) 
                {  
                } 
                AI();
            }
            else
                setWin(currentGame);
        }
    }
    
    @FXML
    public void onMouseButtonHover()
    {
        System.out.println("Here");
    }
    
    @FXML
    private void handledisplayMinMaxAction() {
        HelloWorld frame = new HelloWorld();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boardPane.setDisable(true);
    }
    
    /**
     * Updates Board given the button, location in the 2D array and whether looking for 1 or -1
     * @param button
     * @param row
     * @param column
     * @param choice 
     */
    public void update(Button button, int row, int column, int choice)
    {
        tempState[row][column] = choice;
        button.setDisable(true);
        if(choice == 1)
            button.setText("X");
        else if (choice == -1)
            button.setText("O");
        else
        {
            button.setText("");
            button.setDisable(false);
        }
    }
    
    /**
     * Updates every Single button
     */
    public void setBoard()
    {
        //To Keep if it breaks
        /*if(tempState[0][1] != 0)
        {
            buttonTwo.setDisable(true);
            if(tempState [0][1] == 1)
                buttonTwo.setText("X");
            else
                buttonTwo.setText("O");
        }
        else
        {
            buttonTwo.setDisable(false);
            buttonTwo.setText("");
        }*/
        update(buttonOne,0,0,tempState[0][0]);
        update(buttonTwo,0,1,tempState[0][1]);
        update(buttonThree,0,2,tempState[0][2]);
        update(buttonFour,1,0,tempState[1][0]);
        update(buttonFive,1,1,tempState[1][1]);
        update(buttonSix,1,2,tempState[1][2]);
        update(buttonSeven,2,0,tempState[2][0]);
        update(buttonEight,2,1,tempState[2][1]);
        update(buttonNine,2,2,tempState[2][2]);
    }
    
    /**
     * Calculates and sets the turn boolean.
     */
    public void setTurn()
    {
        if(PlayerTurn)
        {
            int x = 0;
            int O = 0;
            for(int i = 0 ; i < 3 ; i++)
            {
                for (int y = 0 ; y < 3 ; y++)
                {
                    if(tempState[i][y] == 1)
                        x++;
                    else if (tempState[i][y] == -1)
                        O++;
                }
            }
            if (x > O)
                turn = false;
            else
                turn = true;
        }
        else if(!PlayerTurn)
        {
            int x = 0;
            int O = 0;
            for(int i = 0 ; i < 3 ; i++)
            {
                for (int y = 0 ; y < 3 ; y++)
                {
                    if(tempState[i][y] == 1)
                        x++;
                    else if (tempState[i][y] == -1)
                        O++;
                }
            }
            if (x > O)
                turn = true;
            else
                turn = false;
        }
    }
    
    /**
     * Call AI Turn
     */
    public void AI()
    {
        System.out.println(turn);
        if(!PlayerTurn)
        {
            turn = true;
        }
        TicTacToeState newGame = new TicTacToeState(tempState, turn);
        AI AITurn = new AI(newGame,turn);
        int[][]temp = AITurn.getBestChoice(turn);
        currentGame = new TicTacToeState(temp, turn);
        //System.out.println("___");
        //for(int i = 0; i < AITurn.allMoves.size(); i++)
          //  System.out.println(AITurn.allMoves.get(i).getBranchWinCondition());
        tempState = temp;
        setGUI(currentGame);
        //System.out.println(turn);
    }
    
    /**
     * Updates everything on the GUI
     * @param temp 
     */
    public void setGUI(TicTacToeState temp)
    {
        setTurn();
        setTurnLabel();
        setBoard();
        if(temp.getWinCondition() != 2)
            setWin(temp);
    }
    
    /**
     * Set the Turn Label
     */
    public void setTurnLabel()
    {
        if(turn)
           turnLabel.setText("Turn: Player");
        else
           turnLabel.setText("Turn: AI");
    }
    
    /**
     * Set the Win Label
     * @param temp
     */
    public void setWin(TicTacToeState temp)
    {
        if(PlayerTurn)
        {
            if(temp.getWinCondition() == 1)
            {
               turnLabel.setText("Player Won.");
               win++;
            }
            else if (temp.getWinCondition() == -1)
            {
               turnLabel.setText("AI Won.");
               loss++;
            }
            else
            {
               turnLabel.setText("Draw.");
               draw++;
            }
        }
        else if(!PlayerTurn)
        {
            if(temp.getWinCondition() == 1)
            {
               turnLabel.setText("AI Won.");
               loss++;
            }
            else if (temp.getWinCondition() == -1)
            {
               turnLabel.setText("Player Won.");
               win++;
            }
            else
            {
               turnLabel.setText("Draw.");
               draw++;
            }
        }
        helpLabel.setText("Click Load State or New Game");
        boardPane.setDisable(true);
        upDateTracker();
    }
    
    /**
     * Updates Tracker.
     */
    public void upDateTracker()
    {
        winLabel.setText("Player: " + win);
        lossLabel.setText("Loss: " + loss);
        drawLabel.setText("Draw: " + draw);
    }
}
