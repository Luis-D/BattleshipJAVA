public class Fleet
{
    int vesseltmp;
    Vessel[] vessel;//Array of vessels 
    int FleetPower; //Number of working vessels

    public int Get_FleetPower()
    {return FleetPower;}

    public Fleet(int Number_of_Vessels)
    {
	vesseltmp=0;
	vessel = new Vessel[Number_of_Vessels];
	FleetPower = Number_of_Vessels;
    }

    public Vessel Get_Vessel(int i)
    {
	return this.vessel[i];
    }
    
    public void Vessel_Add(Vessel new_vessel)
    {
	if(vesseltmp<vessel.length)
	{
	    vessel[vesseltmp] = new_vessel;	
	    vesseltmp++;
	}
    }
    
    public Vessel Attack(Fleet Enemy,Point2D Attk)
    {
/*With this function, this fleet proceeds to attack a given fleet given a 2D point.
 * Based in this given 2D point, the attack is computed, in success it returns a pointer to the damaged enemy vessel.
If a vessel gets hit, this function returns its reference. otherwise it returns a null pointer
If a vessel gets destroyed, the Enemy fleet gets its FleetPower lowered by 1.i
*/
	Point2D Test = new Point2D();
	int VC = Enemy.vessel.length;
	for(int i = 0;i<VC;i++)
	{
	    Vessel crrnt = Enemy.vessel[i];
	    if(crrnt.GetFLAG()==1)//If not destroyed
	    {
		int o = 0;
		Test.Set_Point(crrnt.Stern);
		for(;o < crrnt.Length;o++)
		{
		    if(Test.Equal(Attk))
		    {
			crrnt.DMG(o);
			if(crrnt.GetFLAG()==0)
			{Enemy.FleetPower--;}
			return crrnt;
		    }
		    Test.Add(crrnt.Norm_Dir);
		}
	    }
	}

	return null;		
    }
}
