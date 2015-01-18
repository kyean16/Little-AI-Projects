/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetatictactoe;

import java.util.ArrayList;

/**
 *
 * @author Keyean
 */
public class TicTacToeState implements Comparable
{
    //Global Variables
    private int[][] state; 
   // private int turn; //true == Player1 , false == Player2
    private int winCondition; //0 == Draw, 1 == Winner , -1 == Loser, 2 still going
    private int branchWinCondition;
    private boolean zeroes;
    private ArrayList<Integer> emptySpots;
    
    /**
     * Constructor that takes in a 2-D array and sets the 2-D Array accordingly
     * @param state 
     * @param turn
     */
    public TicTacToeState(int[][] newState, boolean turn)
    {
        state = new int[3][3];
        zeroes = false;
        emptySpots = new ArrayList<Integer>();
        for(int i = 0 ; i < 3 ; i++)
        {
            for (int x = 0 ; x < 3 ; x++)
            {
                state[i][x] = newState[i][x];
                if(state[i][x] == 0)
                {
                   zeroes = true;
                   emptySpots.add(i);
                   emptySpots.add(x);
                }
            }
        }
        winCondition = 2;
        isWinCondition(turn);
    }
    
    /**
     * Constructor creates an empty 3x3 2-D Array
     * @param turn
     */
    public TicTacToeState(boolean turn)
    {
        for(int i = 0 ; i < 3 ; i++)
        {
            for (int x = 0 ; x < 3 ; x++)
            {
                state[i][x] = 0;
                emptySpots.add(i);
                emptySpots.add(x);
            }
        }
        zeroes = true;
        winCondition = 2;
        isWinCondition(turn);
    }
    
    /**
     * Return State
     * @return state
     */
    public int[][] getState()
    {
        return state;
    }
    
    /**
     * Return emptySpots
     * @return  emptySpots
     */
    public ArrayList<Integer> getEmptySpots()
    {
        return emptySpots;
    }
    
    /**
     * Return the 2-D state into string format
     * @return stringState
     */
    @Override
    public String toString()
    {
        String stringState = "";
        for(int i = 0 ; i < 3 ; i++)
        {
            for (int x = 0 ; x < 3 ; x++)
            {
                stringState += state[i][x] + " ";
            }
            stringState += "\n";
        }
        return stringState;
    }
    
    /** 
     * Return getZeroes
     * @return zeroes
     */
    public boolean getZeroes()
    {
        return zeroes;
    }
    
    /**
     * Return winCondition
     * @return winCondition
     */
    public int getWinCondition()
    {
        return winCondition;
    }
    
    /**
     * Checks and Updates if the WinCondition has been reached
     * @param turn 
     */
    public void isWinCondition(boolean turn)
    {
        if(!zeroes)
        {
            winCondition = 0;
        }
        HorizontalWinCondition(turn);
        VerticalWinCondition(turn);
        DiagonalWinCondition(turn);
    }
    /***
     * Checks Rows
     * @param turn 
     */
    public void HorizontalWinCondition(boolean turn)
    {
        int condition;
        if(turn)
            condition = 1;
        else
            condition = -1;
        for(int row = 0 ; row < 3 ; row++)
        {
            if(condition == state[row][0] && condition == state[row][1] && condition == state[row][2])
            {
                winCondition = condition;
                branchWinCondition = condition;
                break;
            }
        }
    }
    
   /**
   * Check Columns
   * @param turn 
   */
    public void VerticalWinCondition(boolean turn)
    {
        int condition;
        if(turn)
            condition = 1;
        else
            condition = -1;
        for(int column = 0 ; column < 3 ; column++)
        {
            if(condition == state[0][column] && condition == state[1][column] && condition == state[2][column])
            {
                winCondition = condition;
                branchWinCondition = condition;
                break;
            }
        }
    }
    
    /**
     * Check Diagonals
     * @param turn 
     */
    public void DiagonalWinCondition(boolean turn)
    {
        int condition;
        if(turn)
            condition = 1;
        else
            condition = -1;
         if(condition == state[0][0] && condition == state[1][1] && condition == state[2][2])
         {
                winCondition = condition;
                branchWinCondition = condition;
         }
         else if(condition == state[0][2] && condition == state[1][1] && condition == state[2][0])
         {
                winCondition = condition;
                branchWinCondition = condition;
         }
    }
    
    /**
     * Sets branchWinCondition of that State
     * @param i 
     */
    public void setBranchWinCondition(int i)
    {
        branchWinCondition = i;
    }
    
    /**
     * Gets branchWinCondition of that State
     * @return branchWinCondition
     */
    public int getBranchWinCondition()
    {
        return branchWinCondition;
    }
    
    @Override
    public int compareTo(Object compare) 
    {
        int compareBranchWinCondition=((TicTacToeState)compare).getBranchWinCondition();
		return this.branchWinCondition-compareBranchWinCondition;
    }
}
