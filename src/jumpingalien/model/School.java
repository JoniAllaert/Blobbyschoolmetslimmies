package jumpingalien.model;

import java.util.ArrayList;

import jumpingalien.model.Slime;

public class School{
	
	public School(){
	}
	
	public ArrayList<Slime> getSlimes() {
		return slimes;
	}
	
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	
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
