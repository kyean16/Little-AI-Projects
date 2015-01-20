/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabetatictactoe;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Keyean
 */
public class AI 
{
    private int[][] currentState; 
    public ArrayList<TicTacToeState> allMoves;
    private boolean currentTurn;
    
    /**
     * Constructor AI which generate all possible further moves.
     * @param state
     * @param turn 
     */
    public AI (TicTacToeState state , boolean turn)
    {
        currentTurn = turn;
        currentState = new int[3][3];
        allMoves = new ArrayList<>();
        int[][] oldState = state.getState();
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int x = 0 ; x < 3 ; x++)
            {
                currentState[i][x] = oldState[i][x];
            }
        }
        ArrayList <Integer> newList = new ArrayList<>();
        ArrayList <Integer> oldList = state.getEmptySpots();
        for(int i = 0 ; i < oldList.size() ; i++)
        {
            newList.add(oldList.get(i));
        }
        if(state.getWinCondition() == 2)
        {
            for(int i = 0 ; i <newList.size() ; i = i+2)
            {
               allMoves.add(generateMove(newList.get(i),newList.get(i+1),turn));
            }
        }
        Collections.sort(allMoves);
    }
    
    public TicTacToeState generateMove(int row, int column,boolean turn)
    {
        int [][] newState = new int[3][3];
        for(int i = 0 ; i < 3 ; i++)
        {
            for (int x = 0 ; x < 3 ; x++)
            {
                newState[i][x] = currentState[i][x];
            }
        }
        if(turn)
            newState[row][column] = 1;
        else
            newState[row][column] = -1;
        TicTacToeState tempState = new TicTacToeState(newState, turn);
        AI newAI = new AI(tempState,!turn);
        if(tempState.getWinCondition() == 2)
            tempState.setBranchWinCondition(newAI.getMinMax());
        return tempState;
    }
    
    public int getMinMax()
    {
        if(currentTurn) //Max
        {
            for(int i = 0 ; i < allMoves.size() ; i++)
            {
                if(allMoves.get(i).getBranchWinCondition() == 1)
                {
                    return 1;
                }
            }
            for(int i = 0 ; i < allMoves.size() ; i++)
            {
                if(allMoves.get(i).getBranchWinCondition() == 0)
                {
                   return 0;
                }
            }
            for (int i = 0; i < allMoves.size(); i++) 
            {
                if (allMoves.get(i).getBranchWinCondition() == -1) 
                {
                return -1;
                }
            }
        }
        else //Min
        {
            for(int i = 0 ; i < allMoves.size() ; i++)
            {
                if(allMoves.get(i).getBranchWinCondition() == -1)
                {
                   return -1;
                }
            }
            for(int i = 0 ; i < allMoves.size() ; i++)
            {
               if(allMoves.get(i).getBranchWinCondition() == 0)
                {
                   return 0;
                }
            }
            for(int i = 0 ; i < allMoves.size() ; i++)
            {
                if(allMoves.get(i).getBranchWinCondition() == 1)
                {
                    return 1;
                }
            }
        }
        return 0;
    }
    
    public int[][] getBestChoice(boolean turn)
    {
        
        if(!turn) //False
        {
        for(int i = 0 ; i < allMoves.size() ; i++)
        {
            if(allMoves.get(i).getBranchWinCondition() == -1)
            {
                return allMoves.get(i).getState();
            }
        }
        for(int i = 0 ; i < allMoves.size() ; i++)
        {
            if(allMoves.get(i).getBranchWinCondition() == 0)
            {
                return allMoves.get(i).getState();
            }
        }
        for(int i = 0 ; i < allMoves.size() ; i++)
        {
            if(allMoves.get(i).getBranchWinCondition() == 1)
            {
                return allMoves.get(i).getState();
            }
        }
        }
        else if(turn)
        {
        for(int i = 0 ; i < allMoves.size() ; i++)
        {
            if(allMoves.get(i).getBranchWinCondition() == 1)
            {
                return allMoves.get(i).getState();
            }
        }
        for(int i = 0 ; i < allMoves.size() ; i++)
        {
            if(allMoves.get(i).getBranchWinCondition() == 0)
            {
                return allMoves.get(i).getState();
            }
        }
        for(int i = 0 ; i < allMoves.size() ; i++)
        {
            if(allMoves.get(i).getBranchWinCondition() == -1)
            {
                return allMoves.get(i).getState();
            }
        }
        }
        return  allMoves.get(0).getState();
        
    }
}
