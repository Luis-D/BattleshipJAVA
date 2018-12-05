import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.lang.Character;
import java.util.Random;
public class Human
{
    boolean[][] Memory;
    FleetCommand Fleet;
    public Human(FleetCommand Fleet)
    {
	this.Fleet = Fleet;
	Memory = new boolean[10][10];
	for(int i =0;i<9;i++)
	{
	    for (int o=0;o<9;o++)
	    {
		Memory[o][i]=false;
	    }
	}
    }

    public int Execute_Command(String Command,FleetCommand Enemy)
    {

	int Max = this.Fleet.Get_FleetPower();

	if(Fleet.Streak>1){Max++;Fleet.Streak=0;}

	Point2D[] Attk = new Point2D[Max];


	//No commands means automated attack
	if(Command.length()<=0)
	{
	    int Tests=0; int Chances=1000;
	    Random rand = new Random();
	    for(int i = 0;i<Max;i++)
	    {
		Tests=0;
		while(true)
		{
		    int Y = rand.nextInt((9)+1);
                    int X = rand.nextInt((9)+1);
	
		    if(Tests>=Chances) //<-The system tried to find a place too many times
		    {
			i=Max;
			break;
		    }
		    else
		    {
			if(Memory[X][Y]==true){
			    Tests++;
			    continue;}
		    } 

		    Memory[X][Y]=true;
		    Attk[i] = new Point2D(X,Y);
		    break;
		}
	    }
	}
	else
	{
	    String[] Parts;
	    int PartsCount;
	    Parts = Command.split(" ");	
	    PartsCount = Parts.length;
	    if(PartsCount < Max){Max=PartsCount;}
	    
	    for(int i = 0;i<Max;i++)
	    {
		String tmp_ = Parts[i];
		int Y = ((int)tmp_.charAt(0)) - 65;
		int X = Character.getNumericValue(tmp_.charAt(1)) -1;
	   
		if(tmp_.length()>2)
		{
		    X+= ((X+1)*10)+(Character.getNumericValue(tmp_.charAt(2))) -1;
		    System.out.println(X);
		}

		if(Y<0 || Y>9 ||X<0 ||X>9 || Memory[X][Y]==true)
		{
		    System.out.println("ERROR @ "+tmp_);
		    return -1;
		}

		Memory[X][Y] = true;
		Attk[i] = new Point2D(X,Y);
	    }
	}

	this.Fleet.SalvoAttack(Enemy,Attk);

	return 1;
    }

    public int Generate_Fleet(String File_Name)
    {
	Point2D[] Directions =
	{
	    new Point2D(1,0),new Point2D(0,1),
	    new Point2D(-1,0),new Point2D(0,-1)
	};

	File file = new File(File_Name);
	BufferedReader br ;
    
    try{
	br = new BufferedReader(new FileReader(file));
	
	String CurLine;
	String[] Parts;
	int PartsCount;
    
	while((CurLine = br.readLine()) != null)
	{
	    Parts = CurLine.split(" ");
	    PartsCount = Parts.length;
	    if(PartsCount <9){System.out.println(PartsCount+"<9");return 0;}

	    Point2D TMP1 = new Point2D();
	    Point2D TMP2 = new Point2D();

	    for (int i=0;i<9;i++)
	    {
		String tmp_ = Parts[i];

		char T = tmp_.charAt(0);
		int Y = ((int)tmp_.charAt(1)) - 65;
		int X = Character.getNumericValue(tmp_.charAt(2)) -1;
		int Dir=Character.getNumericValue(tmp_.charAt(3));
		Point2D _Dir_ = Directions[Dir];
		
		if(Y<0 || Y>9 || X<0 || X>9 || Dir<0 || Dir>3)
		{System.out.println("ERROR @ "+tmp_);return -1;}

		int L;
		switch(T)
		{
		    case 'P':{L=4;break;}
		    case 'A':{L=3;break;}
		    case 'D':{L=2;break;}
		    case 'F':{L=1;break;}
		    default:{return -1;}
		};
		
		//System.out.println(T+"|"+L+"|"+X+"|"+Y+"|"+_Dir_.x+","+_Dir_.y);

		if(i>0)
		{
		    for(int e=1;e<i;e++)
		    {
			Vessel Cur = Fleet.vessel[e];

			TMP1.Set_Point(Cur.Stern);

			for(int o=0;o<Cur.Length;o++)
			{
			    for(int a=0;a<e;a++)
			    {
				Vessel CMP = Fleet.vessel[a];
			    	TMP2.Set_Point(CMP.Stern);

				for(int u=0;u<CMP.Length;u++)
				{
				    if(TMP1.Equal(TMP2))
				    {
					System.out.println("ERROR @ "+tmp_);return -1;
				    }
				    TMP2.Add(CMP.Norm_Dir);
				}
			    }
			    TMP1.Add(Cur.Norm_Dir);    
			}
		    }
		}

		this.Fleet.Vessel_Add(new Vessel(T,X,Y,_Dir_.x,_Dir_.y,L));
	    } 
	}

    } catch(IOException e){;}
	return 1;			
    }

}
