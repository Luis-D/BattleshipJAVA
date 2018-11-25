public class FleetCommand extends Fleet
{
    public char FLAG;
    public int Streak;

    public int SalvoReturnCount;
    public AttackPoint[] SalvoReturn;

    public FleetCommand(int FleetPower)
    {
	super(FleetPower);
	FLAG = 0;
	Streak=0;
	SalvoReturn = new AttackPoint[FleetPower];
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
	int SalvoReturnCount = this.Get_FleetPower(); //Do subclasses inherits private fields?
	if (AttackPoints.length < SalvoReturnCount){SalvoReturnCount=AttackPoints.length;}
	for (int i = 0;i<SalvoReturnCount;i++)
	{
	    tmp = this.Attack(Enemy, AttackPoints[i]);
	    if(tmp==null){SalvoReturn[i].data = 'X';}
	    else{SalvoReturn[i].data = tmp.GetType();}
	}	
    }
}
