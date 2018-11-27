package tamagotchi;

public class Button {

	public static Button Feed = new Button("🍎", "feed");
	public static Button Sleep = new Button("💤", "sleep");
	public static Button Toilet = new Button("🚽", "toilet");
	public static Button Clean = new Button("💦", "clean");
	public static Button Fun = new Button("🎉", "fun");
	public static Button Like = new Button("👍", "like");
	public static Button Dislike = new Button("👎", "dislike");
	
	public String title;
	public String value;
	
	public Button(String title, String value)
	{
		this.title = title;
		this.value = value;
	}
	
	public static Button[] getNeedsButtons() {
		return new Button[] {
				Button.Feed, Button.Sleep, Button.Toilet,
				Button.Clean, Button.Fun
		};
	}
}
