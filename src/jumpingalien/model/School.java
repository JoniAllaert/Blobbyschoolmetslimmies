package jumpingalien.model;

import java.util.ArrayList;
import jumpingalien.model.Slime;

public class School{
	
	/**
	 * Initializes an empty school for slimes.
	 */
	public School(){
	}
	
	/**
	 * This lists registers the slimes in the school.
	 */
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	
	/**
	 * This method adds a new slime to the school.
	 */
	public void addSlime(Slime slimmie){
		if(slimes.contains(slimmie))
			return;
		else slimes.add(slimmie);			
	}
}
