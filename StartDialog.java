
import java.util.Date;

public class StartDialog implements Dialog {

	public Reply reply(UserData user, String input)
	{
		//наверное нужно вынести реплаи в переменные, чтобы не создавать их заново постоянно
		if (input.toLowerCase().equals("да"))
			return new Reply("Добро пожаловать в обучение! Я - твой питомец, и у меня есть потребности! " +
					"Готов за мной ухаживать?", DialogName.Train, new Button[] { new Button("Да")});
		if (!input.toLowerCase().equals("нет")) {
			Reply reply = new Reply("Привет, это стартовый экран. Хочешь пройти обучение?");
			reply.addRow(new Button[] { new Button("Да"), new Button("Нет")});
			return reply;
		}

		user.events.add(new DeathEvent(user.pet));
		return new Reply("Игра началась!", DialogName.Main, Button.getNeedsButtons());






	}
}
