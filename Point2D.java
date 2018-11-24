public class Point2D
{
    public int x;
    public int y;
    
    public void Set(int X, int Y)
    {this.x=X;this.y=Y;}
    public Point2D(){;}

    public Point2D(int X, int Y)
    {this.Set(X,Y);}

    public void Add(Point2D Point)
    {
	this.x+=Point.x;
	this.y+=Point.y;	
    }

    public void Sub(Point2D Point)
    {
	this.x-=Point.x;
	this.y-=Point.y;	
    }
    
    public boolean Equal(Point2D Point)
    {
	return 
	(this.x==Point.x && this.y==Point.y);
    }

    public void Set_Point(Point2D Point)
    {
	this.x=Point.x;
	this.y=Point.y;
    }
}
