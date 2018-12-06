package tamagotchi;

import java.util.ArrayList;

public class MainDialog implements Dialog {

	private DialogName name = DialogName.Main;
	private ArrayList<Command> commands = new ArrayList<Command>();

	MainDialog() {
		commands.add(new FeedCommand());
		commands.add(new SleepCommand());
		commands.add(new HealthCommand());
		commands.add(new ToiletCommand());
		commands.add(new CleanCommand());
		commands.add(new CheckCommand());
		commands.add(new ShowCommandsCommand());
		commands.add(new EnterDungeonCommand());
	}
	
	public Reply reply(UserData user, String input)
	{
		if (user.pet.isSleep) {
			Reply reply =  new Reply("Я сплю, отстань");
			return reply;
		}
		for (Command command : commands) {
			if (command.matchInput(input)) {
				Reply reply = command.reply(user, input);
				reply.setButtons(Button.getMainButtons(user.pet));
				return reply;
			}
		}
		
		return new Reply("Я не знаю такой команды! Если хочешь узнать список доступных, введи \"Команды\"");
	}
	
	public DialogName getName()
	{
		return name;
	}
}
