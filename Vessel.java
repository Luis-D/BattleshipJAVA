import java.util.Arrays;

public class Vessel
{
    private char FLAG;
    public Point2D Stern; //Vessel Pivot
    public Point2D Norm_Dir; //Vessel Direction vector
    public int Length; //Vessel Length
    private boolean Status[];//Vessel Status, each field represent the status of a section of the Vesse Status[0] is the Stern and Status[Length-1] is the Bow of the vessel
    private char Type; //Vessel Type

    public Vessel(char Type,int X, int Y,int Dir_X, int Dir_Y, int Length)
    {
	this.FLAG=1; // 1 = Working vessel
	this.Type=Type;
	Stern = new Point2D(X,Y);
	if(Dir_X>0){Dir_X=1;}
	if(Dir_X<0){Dir_X=-1;}
	if(Dir_X==0)
	{
		if(Dir_Y<0){Dir_Y=-1;}
		if(Dir_Y>0){Dir_Y=1;}	
	}
	Norm_Dir = new Point2D(Dir_X,Dir_Y);
	this.Length=Length;
	this.Status = new boolean[Length];
	Arrays.fill(this.Status,false);
    }

    public char GetFLAG(){return this.FLAG;} 
    public char GetType(){return this.Type;}
    public boolean GetStatus(int Requested_Status)
    {
	return Status[Requested_Status];
    }

    public Point2D GetSection(int Requested_Status)
    {
    /*
 *  This function returns the position
 *  of a given Section of the vessel.
 *  If 0 is given, it returns the position of the
 *  Stern.
 *  If Length-1 is given, it returns the position
 *  of the Bow
 * */
	Point2D RET = new Point2D();
	RET.Set_Point(this.Stern);
	RET.x+=this.Norm_Dir.x*Requested_Status;
	RET.y+=this.Norm_Dir.y*Requested_Status;
	return RET; 
    }

    private boolean checkDMG()
    {
/* It just check the damage of the vessel
 * If the vessel have all of its sections damaged,
 * then the vessel is flagged as "destroyed"
 */
	int L = Status.length;
	for(int i = 0; i<L;i++)
	{
	    if(Status[i]!=true)
	    {return false;}
	}
	this.FLAG=0;
	return true;
    }

    public boolean DMG(int Affected_Status)
    {
/*This functions damages the vessel given a affected section of it.
 * if 0 is given, the Stern is damaged.
 * if Length-1 is given, the Bow is damaged
 */
	Status[Affected_Status] = true;
	return checkDMG();
    } 
}
