package tamagotchi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reply {
	private String text;
	private ArrayList<List<Button>> buttons = new ArrayList<List<Button>>();
	private DialogName nextDialog = DialogName.None;
	
	public DialogName getNextDialog()
	{
		return nextDialog;
	}
	
	public Reply(String text) {
		this.text = text;
	}
	
	public Reply(String text, DialogName nextDialog)
	{
		this.text = text;
		this.nextDialog = nextDialog;
	}
	
	public Reply(String text, DialogName nextDialog, Button[] buttons)
	{
		this.text = text;
		this.nextDialog = nextDialog;
		this.buttons.add(Arrays.asList(buttons));
	}
	
	public Reply(String text, Button[] buttons)
	{
		this.text = text;
		this.buttons.add(Arrays.asList(buttons));
	}

	public void setButtons(ArrayList<List<Button>> buttons) {
		this.buttons = buttons;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void addRow(Button[] buttons)
	{
		this.buttons.add(Arrays.asList(buttons));
	}
	
	public List<List<Button>> getButtons()
	{
		return buttons;
	}
}
