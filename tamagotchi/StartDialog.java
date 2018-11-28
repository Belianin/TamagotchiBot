package tamagotchi;

import java.util.Date;

public class StartDialog implements Dialog {

	public Reply reply(UserData user, String input)
	{
		//наверное нужно вынести реплаи в переменные, чтобы не создавать их заново постоянно
		if (!input.toLowerCase().equals("да")) {
			return new Reply("Привет, это стартовый экран. Готов(а) начать игру?", new Button[] { new Button("Да", "да")});
		}
			
		Date currentDate = new Date();
		user.events.add(new Death(user.pet));
		return new Reply("Это основной экран, Бла-бла", DialogName.Main, Button.getNeedsButtons());
			
		
					
		
	}
}
