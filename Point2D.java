public class Point2D
{
    public int x;
    public int y;

    public Point2D(int X, int Y)
    {
	this.x=X; this.y=Y;
    }

    public void add(Point2D Point)
    {
	this.x+=Point.x;
	this.y+=Point.y;	
    }

    public void sub(Point2D Point)
    {
	this.x-=Point.x;
	this.y-=Point.y;	
    }
}
