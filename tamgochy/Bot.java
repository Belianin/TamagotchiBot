package tamgochy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class Bot {
	private HashMap<String, Pet> tamagochyMap = new HashMap<String, Pet>();;
	private ArrayList<Event> events = new ArrayList<Event>();;
	private int deathTime = 10;
	private ArrayList<Command> commands = new ArrayList<Command>();

	Bot() {
		commands.add(new FeedCommand());
		commands.add(new SleepCommand());
		commands.add(new FunCommand());
		commands.add(new ToiletCommand());
		commands.add(new CleanCommand());
		commands.add(new CheckCommand());
		commands.add(new ShowCommandsCommand());
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public HashMap<String, Pet> getTamagochyMap() {
		return tamagochyMap;
	}

	public void putTamagochi(String id, Pet pet) {
		tamagochyMap.put(id, pet);
	}

	public void addEvent(Event event) {
		events.add(event);
	}

	public void removeEvent() {
		events.remove(0);
	}

	public Reply reply(String input, String ID) {

		if (!tamagochyMap.containsKey(ID) || !tamagochyMap.get(ID).alive) {
			Pet needs = new Pet();
			tamagochyMap.put(ID, needs);
			return new Reply("Я родился!");
		}

		if (!tamagochyMap.get(ID).alive) {
			return new Reply("Кажется, это конец... Чтобы начать новую игру, введи любое сообщение");
		}

		for (Command command : commands) {
			if (command.matchInput(input))
				return command.reply(input, ID, this);
		}
		return new Reply("Я не знаю такой команды! Если хочешь узнать список доступных, введи \"Команды\"");

	}

	public int getDeathCount(Date date) {
		Date currentDate = new Date();
		long difference = currentDate.getTime() - date.getTime();
		int sec = (int) (difference / 1000);
		return Math.abs(sec / deathTime);
	}

	public void checkForDeath(String id) {
		// if (!tamagochyMap.isEmpty() && tamagochyMap.get(id).alive)
		if (tamagochyMap.isEmpty() && !tamagochyMap.get(id).alive)
			return;
		
		Date currentDate = new Date();
		int coeff = getDeathCount(tamagochyMap.get(id).getLastUpdate());
		if (coeff > 0) {
			Event death = new Death(this, id, coeff);
			events.add(death);
		}
		if (events.size() != 0) {
			for (int i = events.size() - 1; i >= 0; i--) {
				{
					if (events.get(i).When.before(currentDate)) {
						events.get(i).apply();
						events.remove(i);
					}
				}
			}
		}
	}

	public void handleEvent(String input, String id) {
		if (tamagochyMap.containsKey(id)) {
			Event event = new Event(input, id, this, new Date());
			events.add(event);
		}
		checkForDeath(id);
	}
}
