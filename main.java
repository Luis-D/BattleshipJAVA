//Both Human an Computer are players.
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

public class main
{
    public static void main(String[] args)
    {
	int W,H;
	W=10;
	H=10;	   

	Board Radar = new Board(H,W);
	Board Fleet = new Board(H,W);	    

	FleetCommand P = new FleetCommand(2);
	P.Vessel_Add(new Vessel('A',0,1, 1,0, 2));
	P.Vessel_Add(new Vessel('B',2,0,0,1,4)); 

	FleetCommand X = new FleetCommand(1);
	P.Vessel_Add(new Vessel('A',0,0,0,1,1));    

	X.Attack(P,new Point2D(0,1));

	Render.Fleet(P,Fleet);	
	
	System.out.println("");
	System.out.println("Radar");
	Radar.Draw();	   
	System.out.println("");
	System.out.println("Fleet");
	Fleet.Draw(); 
    }
}
