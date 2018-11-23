public class Vessel
{
    private Point2D Stern; //Vessel Pivot
    private Point2D Norm_Dir; //Vessel Direction, it should be normalized
    private int Length; //Vessel Length
    private char Status[]; //Vessel Status, each field represent the status of a sector of the Vessel
    private char Type; //Vessel Type

    public Vessel(char Type,int X, int Y,int Dir_X, int Dir_Y, int Length)
    {
	this.Type=Type;
	this.Stern.x=X;this.Stern.y=Y;
	this.Norm_Dir.x=Dir_X;
	this.Norm_Dir.y=Dir_Y;
	this.Length=Length;
	this.Status = new char[Length];
    }
}
