/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guinim.nim;

import java.util.Random;
import java.util.Scanner;

public class Nim
{
	public boolean startTurn; //Bool who starts
	public int totalNim; //Startingn number of Nims
	public int possibleNumMoves; //Possible number that can be taken per turn
	public Scanner scan = new Scanner(System.in);

  public Nim()
  {
    	startTurn = true;
    	totalNim = 12;
    	possibleNumMoves = 4;
  }
  //Function for the player.
  public String Player(String inputText)
  {
   		try
   		{
   			int input = Integer.parseInt(inputText);
   			if(input > 0 && input <= totalNim && input <= possibleNumMoves)
   			{
   				totalNim = totalNim - input;
                                startTurn = !startTurn;
                                return "You removed " + input + " from the stack.";
   			}
   			else
   			{
   				return "This is not a correct input try again";
   			}
   		}
   		catch (NumberFormatException e) //Catches if wrong input is written by the user.
   		{
   			return "This is not a correct input try again";
   		}
   	}

   	//Randomly takes a number.
   	public String DumbAi()
   	{
   		int AIchoice;

   		if(totalNim <= possibleNumMoves)
   		{
   			AIchoice = totalNim;
   		}
   		else
   		{
   			Random random = new Random();
   			AIchoice = (random.nextInt(possibleNumMoves)+1);
   		}
   		totalNim = totalNim - AIchoice;
   		startTurn = !startTurn; //Inverse bool;
                return("The AI took: " + AIchoice +"\n");
   	}

   	//Function that prints the remaining number of Nim by 4 4
   	public String printNim()
   	{
                String text="";
   		text = ("Remaining number of sticks = ");
   		int spacing = 0; //use for spacing
   		for (int i = 0 ; i < totalNim ; i++) //Continuously print while it does not reach totalNim;
   		{
   			spacing++;
   			text +=("|");
   			if(spacing == 4)
   			{
   				text+=(" ");
   				spacing = 0;
   			}
   		}
   		//
   		text+= (" (" + totalNim + ")");
                return text;
   	}

   	//Function that prints the Winner.
   	public String printWinner()
   	{
   		if(startTurn) //Inversed it since it autommatically get switched at the end of the function
   		{
   			return("The AI Won.");
   		}
   		else
   		{
   			return("You Won.");
   		}
   	}
}