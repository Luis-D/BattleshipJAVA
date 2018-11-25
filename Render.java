public class Render
{
    public static void Fleet(Fleet fleet,Board B)
    {
/*It renders the given Fleet (fleet) to a given Board (B)
 * */
	Vessel[] Fleetves = fleet.vessel;
	int L = Fleetves.length;
	for(int i=0;i<L;i++)
	{
	    Vessel tmp = Fleetves[i];
	    int LL = tmp.Length;
	    for(int o = 0;o<LL;o++)
	    {
		Point2D tmp2 = tmp.GetSection(o);
    		char C;
		if(tmp.GetStatus(o)==false)
		{C=tmp.GetType();}
		else{C='-';}
		B.Set(C,tmp2.x,tmp2.y);
	    }
	}
    }
    
    public static void Salvo(AttackPoint[] SalvoReturn,int SalvoReturnCount, Board B)
    {
/*It renders the data of the given Salvo Attack*/
    
	for(int i=0;i<SalvoReturnCount;i++)
	{
	    B.Set(SalvoReturn[i].data,SalvoReturn[i].x,SalvoReturn[i].y);
	}
    }
}
