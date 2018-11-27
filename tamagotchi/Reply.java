package tamagotchi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reply {
	//Этот класс пригодится в будущем, когда захотим передавать не только текст, но и кнопки
	private String text;
	private ArrayList<List<Button>> buttons = new ArrayList<List<Button>>();
	
	public Reply(String text) {
		this.text = text;
	}
	
	public Reply(String text, Button[] buttons)
	{
		this.text = text;
		this.buttons.add(Arrays.asList(buttons));
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
