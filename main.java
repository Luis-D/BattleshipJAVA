//Both Human and Computer are players.
//The Human enters instructions via the input (in-game command line).
//The Computer enters instructions via its AI.
//Each player is a FleetCommand.
//Each FleetCommand contains a Fleet and the gameplay variables.
//A FleetCommand can schedule a bunch of attacks, a Salvo Attack.
//A FleetCommand attack is described by an array of AttackPoints
//In fact the FleetCommand class is a son of the Fleet class.
//The AttackPoint class is a son of the Point2D class.
//An AttackPoint contains the attack data.
//A Fleet contains an array of vessels (reserved memory).
//A Fleet shall add Vessels to its array as the array starts reserved, but empty.
//A Fleet can attack an enemy fleet to destroy its vessels.
//A Fleet counts the number of working vessels, it's "Power".
//If a Fleet gets a vessel destroyed it's "Power" gets lowered.
//Vessels contains it's status and position variables.
//Vessels can't attack, only entire fleets (like Fleets or FleetCommands) do.
//Vessels are just the building blocks of Fleets, themselves alone are useless.
//Vessels are described by points, Point2D.
//A Point2D is just two integers, x and y.
//The Board is the space where the game occurs.
//The Board is just a 2D array of chars.
//The Board can draw itself.
//The Render class takes information from objects like the Fleets
//The Render class renders the information in the given Board.
//Then, when the Board draws itself, the rendered information appears.

import java.util.Scanner;

public class main
{
    public static void main(String[] args)
    {
	int argsc = args.length;
	
	if(argsc==0)
	{
	    System.out.println("Please enter a Fleet file as an argument");
	    System.out.println("A Fleet file is a plain text file describing a fleet");

	    return;
	}
    
	Scanner cin = new Scanner(System.in);
	String Command;
    
	int BoardSize[]={10,10};	   
	int FleetSize[]={9,9};

	Board Radar = new Board(BoardSize[0],BoardSize[1]);
	Board Fleet = new Board(BoardSize[0],BoardSize[1]);

	FleetCommand P1 = new FleetCommand(FleetSize[0]);
	FleetCommand P2 = new FleetCommand(FleetSize[1]);

	Human Player = new Human(P1);
	if(Player.Generate_Fleet(args[0])!=1)
	{return;}

	IA Computer = new IA(P2);	
	Computer.Generate_Fleet(BoardSize[0],BoardSize[1]);


	while(P1.Get_FleetPower()>0 && P2.Get_FleetPower()>0) //Game Loop
	{	    
	    Render.Fleet(P1,Fleet);	
	    Render.Salvo(P1.SalvoReturn,P1.Get_SalvoReturnCount(),Radar);
	    System.out.println("");
	    System.out.println("Radar");
	    Radar.Draw();	   
	    System.out.println("");
	    System.out.println("Fleet");
	    Fleet.Draw();
	    System.out.println("Vessels: "+P1.Get_FleetPower());
	    do
	    {
		System.out.println("Enter Attack Command (XY XY XY ...):");
		Command = cin.nextLine(); //<-- This string (Command) is the entered command
	    }while(Player.Execute_Command(Command,Computer.Fleet)!=1);

	    Computer.Act(Player.Fleet);

	    Render.Salvo(P2.SalvoReturn,P2.Get_SalvoReturnCount(),Fleet);

	    if(Player.Fleet.Get_FleetPower()<=0)
	    {System.out.println("You Lose");break;}

	    if(Computer.Fleet.Get_FleetPower()<=0)
	    {System.out.println("You Win");break;}
	} 
    }
}
