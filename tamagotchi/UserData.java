package tamagotchi;

import java.util.ArrayList;

public class UserData {
	public String id;
	public Pet pet;
	public DialogName currentDialog = DialogName.Start;
	public ArrayList<Event> events = new ArrayList<Event>();
	public int money;
	
	public UserData(String id)
	{
		this.id = id;
	}
}
