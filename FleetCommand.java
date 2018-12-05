public class FleetCommand extends Fleet
{
    public int FLAG;
    public int Streak;

    private int SalvoReturnCount;
    public AttackPoint[] SalvoReturn;

    public FleetCommand(int FleetPower)
    {
	super(FleetPower);
	FLAG = 0;
	Streak=0;
	SalvoReturn = new AttackPoint[FleetPower];    
	for(int i = 0;i<FleetPower;i++)
	{
	    SalvoReturn[i]=new AttackPoint(0,0);
	}
    }

    public Vessel Attack(FleetCommand Enemy, Point2D AttackPoint)
    {
	Vessel RET = super.Attack(Enemy, AttackPoint);	
	if(RET != null){Streak++;}else{Streak=0;}	
	return RET;
    } 
    
    public void SalvoAttack(FleetCommand Enemy, Point2D[] AttackPoints)
    {
	Vessel tmp;
	SalvoReturnCount = this.Get_FleetPower(); //Do subclasses inherits private fields?
	if (AttackPoints.length < SalvoReturnCount){SalvoReturnCount=AttackPoints.length;}
	for (int i = 0;i<SalvoReturnCount;i++)
	{
	    tmp = this.Attack(Enemy, AttackPoints[i]);
	    SalvoReturn[i].Set_Point(AttackPoints[i]);
	    if(tmp==null){SalvoReturn[i].data = 'X';}
	    else{SalvoReturn[i].data = tmp.GetType();}
	}	
    }

    public int Get_SalvoReturnCount(){return SalvoReturnCount;}
}
