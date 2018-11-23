public class main
{
    public static void main(String[] args)
    {
	int W,H;
	W=10;
	H=10;	   

	Board Radar = new Board(H,W);
	Board Fleet = new Board(H,W);	    

	
	System.out.println("");
	System.out.println("Radar");
	Radar.Draw();	   
	System.out.println("");
	System.out.println("Fleet");
	Fleet.Draw(); 
    }
}
