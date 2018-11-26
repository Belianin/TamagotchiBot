package tamagotchi;

public class Reply {
	//Этот класс пригодится в будущем, когда захотим передавать не только текст, но и кнопки
	private String text;
	
	public Reply(String text) {
		this.text = text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
}
