package tamagotchi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Button {

	public static Button Feed = new Button("🍎");
	public static Button Sleep = new Button("💤");
	public static Button Toilet = new Button("🚽");
	public static Button Clean = new Button("💦");
	public static Button Health = new Button("💊");
	public static Button Like = new Button("👍");
	public static Button Dislike = new Button("👎");
	
	public String title;
	
	public Button(String title)
	{
		this.title = title;
	}

	public static ArrayList<List<Button>> getMainButtons() {
		ArrayList<List<Button>> result = new ArrayList<>();
		result.add(Arrays.asList(getNeedsButtons()));
		result.add(Arrays.asList(new Button("В подзмелье!")));
		result.add(Arrays.asList(new Button("Помощь"), new Button("Статус")));
		return result;
	}
	
	public static Button[] getNeedsButtons() {
		return new Button[] {
				Button.Feed, Button.Sleep, Button.Toilet,
				Button.Clean, Button.Health
		};
	}
}
