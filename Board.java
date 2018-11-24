public class Board
{
    int Width, Height;
    char[][] Quad;
    
    public Board(int Width, int Height)
    {
	this.Width=Width;this.Height=Height;
	this.Quad = new char[Width][Height];

	for(int i =0;i<Height;i++)
	{
	    int o=0;
	    for (;o<Width;o++)
	    {
		Quad[o][i]=' ';
	    }
	}

    }

    public void Draw()
    {
	char Chara = 'A';
	System.out.print("   ");
	for (int u = 0;u<Width-1;u++)
	{System.out.print(Chara+"  ");Chara++;}
	System.out.println(Chara);

	for(int i =0;i<Height;i++)
	{
	    int o=0;

	    o=0;
	    
	    System.out.print(i+" ");
	    for (;o<Width-1;o++)
	    {System.out.print("["+Quad[o][i]+"]");}
	    System.out.println("["+Quad[o][i]+"]");
	}
    }
    
    public char Set(char Char, int X, int Y)
    { 
	if(X<0||Y<0||X>=this.Width||Y>=this.Height)
	{return 0;}
	else{this.Quad[X][Y]=Char; return 1;}
    } 
} 
