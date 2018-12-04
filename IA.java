import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class IA
{
    FleetCommand Fleet;
    char FLAG;
    List<Point2D> Point2DList =new ArrayList<Point2D>();

    public IA(FleetCommand Fleet)
    {
	this.Fleet = Fleet;
    }

    public void Generate_Fleet(int Board_W, int Board_H)
    {
	/*
	    1 x	    P(4)
	    3 x	    A(3)
	    3 x	    D(2)
	    2 x	    F(1)
	*/
    
	int[] Ls ={4,3,3,3,2,2,2,1,1};
	char[]Ts ={'P','A','A','A','D','D','D','F','F'};

	Random rand = new Random();

	Point2D XY = new Point2D();
	Point2D DIR_XY = new Point2D();
	Point2D Pivot = new Point2D();	
   
	int i=0; 
	for(;i<9;i++)
	{
	    int Dirx,Diry;
	    int L = Ls[i];    
	    char T = Ts[i];
	    boolean cont = false;


	    do
	    {

		cont=false;
		Point2DList.clear();


	    	//Generate random position to use as a pivot
		XY.x = rand.nextInt((Board_W-1)+1);
		XY.y = rand.nextInt((Board_H-1)+1);

		//System.out.println(XY.x+","+XY.y);	  


		int o; int u=0;
		for(o=0;o<i;o++)
		{
		    Vessel Cur = Fleet.vessel[o];
		    Pivot.Set_Point(Cur.Stern);
		    int l = Cur.Length;

		    boolean cont2=false;

		    for(u=0;u<l;u++)
		    {
			if(XY.Equal(Pivot))
			{
			    cont2=true;break;
			}		
	
			Pivot.Add(Cur.Norm_Dir);
		    }

		    if(cont2){break;}

		}
		

		//Generate random direction to use as a Direction
	
		DIR_XY.x=0;DIR_XY.y=0;
		
		while(DIR_XY.x==0 && DIR_XY.y==0)
		{
		    DIR_XY.x = rand.nextInt((1-(-1))+1) -1;
		    if(DIR_XY.x==0)
		    {DIR_XY.y = rand.nextInt((1-(-1))+1) -1;}
		    else{DIR_XY.y=0;}
		}

	
		Point2DList.add(new Point2D(XY.x,XY.y));
	
		for(o=1;o<L;o++)
		{
		    Point2D tmpptr = Point2DList.get(o-1);
		    Point2DList.add(new Point2D(tmpptr.x,tmpptr.y));
		    Point2D tmpptr2 = Point2DList.get(o);
		    tmpptr2.Set_Point(tmpptr);
		    tmpptr2.Add(DIR_XY);


		//  System.out.println(i+" "+T+" ("+tmpptr.x+","+tmpptr.y+") ");	    
		//  System.out.println(i+" "+T+" ("+tmpptr2.x+","+tmpptr2.y+") ");
		}

		for(int a=0;a<L;a++)
		{
		    Point2D TMP = Point2DList.get(a);
		    
//		    System.out.println(i+" "+T+" ("+TMP.x+","+TMP.y+") ");		    

		    if( TMP.x>=Board_W || TMP.x<0
		    ||
		    TMP.y>=Board_H || TMP.y<0)
		    {
			//System.out.println("break");
			cont=true;break;
		    }

		    boolean cont3=false;		

		    for(o=0;o<i;o++)
		    {
			Vessel Cur = Fleet.vessel[o];
			Pivot.Set_Point(Cur.Stern);
			int l = Cur.Length;

			for(u=0;u<l;u++)
			{
			    if(TMP.Equal(Pivot))
			    {
		//		System.out.println("!");
				{
				cont3=true;cont=true;break;
				}
			    }		
			    Pivot.Add(Cur.Norm_Dir);
			}	
		    }

		    if(cont3){break;}
		}

	    }while(cont);
	
	   this.Fleet.Vessel_Add(new Vessel(T,XY.x,XY.y,DIR_XY.x,DIR_XY.y,L)); 
	
	//System.out.println("("+i+")"+T+"("+L+") : "+XY.x +","+XY.y+"|"+DIR_XY.x+","+DIR_XY.y); 
	}
    }

    
}
