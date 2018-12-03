//package tamagotchi;

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
	}
	
	public Reply reply(UserData user, String input)
	{
		for (Command command : commands) {
			if (command.matchInput(input)) {
				// временно тут кнопки добавляются
				Reply reply = command.reply(user, input);
				reply.addRow(Button.getNeedsButtons());
				//reply.addRow(new Button[] {new Button("В подземелье!")});
				reply.addRow(new Button[] {new Button("Помощь"), new Button("Статус")});
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
