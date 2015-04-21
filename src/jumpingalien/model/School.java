package jumpingalien.model;

import java.util.ArrayList;

import jumpingalien.model.Slime;

public class School{
	
	/**
	 * Initializes an empty school for slimes.
	 */
	public School(){
	}
	
<<<<<<< HEAD
	public ArrayList<Slime> getSlimes() {
		return slimes;
	}
	
=======
	/**
	 * This lists registers the slimes in the school.
	 */
>>>>>>> origin/master
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	
	/**
	 * This method adds a new slime to the school.
	 */
	public void addSlime(Slime slimmie){
		if(slimes.contains(slimmie))
			return;
		else slimes.add(slimmie);			
	}
	
	public void removeSlime(Slime slimmie){
		if(! slimes.contains(slimmie))
			return;
		else slimes.remove(slimmie);
	}
}
