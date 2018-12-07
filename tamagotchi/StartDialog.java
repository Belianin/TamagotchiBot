package tamagotchi;

import java.util.Date;

public class StartDialog implements Dialog {

	public Reply reply(UserData user, String input)
	{
		//наверное нужно вынести реплаи в переменные, чтобы не создавать их заново постоянно
		if (input.toLowerCase().equals("да"))
			return new Reply("Добро пожаловать в обучение! Я - твой питомец, и у меня есть потребности! " +
					"Готов за мной ухаживать?", DialogName.Training, new Button[] { new Button("Да")});
		if (!input.toLowerCase().equals("нет")) {
			Reply reply = new Reply("Привет, это стартовый экран. Хочешь пройти обучение?");
			reply.addRow(new Button[] { new Button("Да"), new Button("Нет")});
			return reply;
		}

		return new Reply("Дайте имя вашему питомцу", DialogName.Creation);






	}
}
