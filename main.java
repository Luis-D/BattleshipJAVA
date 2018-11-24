public class main
{
    public static void main(String[] args)
    {
	int W,H;
	W=10;
	H=10;	   

	Board Radar = new Board(H,W);
	Board Fleet = new Board(H,W);	    

	Fleet P = new Fleet(2);
	P.Vessel_Add(new Vessel('A',0,1, 1,0, 2));
	P.Vessel_Add(new Vessel('B',2,0,0,1,4)); 

	Fleet X = new Fleet(1);
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
