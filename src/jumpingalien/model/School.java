package jumpingalien.model;

import java.util.ArrayList;
import jumpingalien.model.Slime;

public class School{
	
	public School(){
	}
	
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	
	public void addSlime(Slime slimmie){
		if(slimes.contains(slimmie))
			return;
		else slimes.add(slimmie);			
	}
}
