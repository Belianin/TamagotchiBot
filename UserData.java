
import java.util.ArrayList;

public class UserData {
	public String id;
	public Pet pet = new Pet();
	public DialogName currentDialog = DialogName.Start;
	public ArrayList<Event> events = new ArrayList<Event>();
	
	public UserData(String id)
	{
		this.id = id;
	}
}
