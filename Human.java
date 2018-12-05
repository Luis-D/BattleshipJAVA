import java.util.Scanner;
import java.lang.Character;
public class Human
{
    FleetCommand Fleet;
    public Human(FleetCommand Fleet)
    {
	this.Fleet = Fleet;
    }

    public int Generate_Fleet(String File_Name)
    {
	Point2D[] Directions =
	{
	    new Point2D(1,0),new Point2D(0,-1),
	    new Point2D(-1,0),new Point2D(0,1)
	};

	Scanner sc = new Scanner(File_Name);
	
	System.out.println("Reading: "+File_Name);
    

	String CurLine;
	String[] Parts;
	int PartsCount;
    
	while(sc.hasNextLine())
	{
	    CurLine =sc.nextLine();
	    System.out.println(CurLine);
	    Parts = CurLine.split(" ");
	    PartsCount = Parts.length;
	    if(PartsCount <9){System.out.println(PartsCount+"<9");return 0;}

	    for (int i=0;i<9;i++)
	    {
		char T = Parts[i].charAt(0);
		int X = ((int)Parts[i].charAt(1)) - 65;
		int Y = Character.getNumericValue(Parts[i].charAt(2)) -1;
		int Dir=Character.getNumericValue(Parts[i].charAt(3));
		
		System.out.println(T);
	    } 
	}

	return 1;			
    }

}
