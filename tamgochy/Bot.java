//package tamgochy;

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

	public Reply reply(String input, String id) {
	    if (input.equals("да")) {
            makeTraining(id);
	        return new Reply("Я - твой питомец! Твоя главная цель - не дать мне умереть. " +
                    "Чтобы узнать, как мои дела, напиши \"проверка\"");
        }

		if (!tamagochyMap.containsKey(id) || !tamagochyMap.get(id).alive) {
			Pet needs = new Pet();
			tamagochyMap.put(id, needs);
			return new Reply("Я родился! Хочешь получать подсказки, как со мной обращаться?");
		}
        Pet currentPet = tamagochyMap.get(id);
		if (!currentPet.alive) {
			return new Reply("Кажется, это конец... Чтобы начать новую игру, введи любое сообщение");
		}

		checkForDeath(id);
		if (currentPet.trainLvl < 7) {
			if (currentPet.train && currentPet.messages.length() != 0) {
				String reply = currentPet.messages.toString();
				currentPet.messages.setLength(0);
				return new Reply(reply);
			}
		}
		else {
			currentPet.train = false;
			currentPet.trainLvl = 0;
		}
		for (Command command : commands) {
			if (command.matchInput(input))
				return command.reply(input, id, this);
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
			death.apply();
//			events.remove(death)
		    //events.add(death);
		}
		if (events.size() != 0) {
			for (int i = events.size() - 1; i >= 0; i--) {
				{
					if (events.get(i).When.before(currentDate)) {
						events.get(i).apply();
						//System.out.println(events.get(i).What);
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
	public void makeTraining(String id) {
        Date currentDate = new Date();
	    Date date = new Date(currentDate.getTime() + 10000);
        Training train0 = new Training(id, date, tamagochyMap.get(id), "проверка");
        train0.Message = "Мои потребности периодически убывают. Мне становится голодно, холодно..." +
                " Если ты будешь редко меня навещать," +
                "то... я умру! В ужасных муках! Ты ведь не дашь мне умереть???";
        events.add(train0);


        date = new Date(currentDate.getTime() + 20000);
	    Training train1 = new Training(id, date, tamagochyMap.get(id), "toilet");
	    train1.Message = "Потребность ходить в туалет - самая быстропонижающаяся из всех. Если она упадет до " +
                "нуля, то... в общем, постараемся этого не допускать? Чтобы сводить меня в туалет, напиши" +
                "\"туалет\"";
	    events.add(train1);

        date = new Date(currentDate.getTime() + 35000);
        Training train2 = new Training(id, date, tamagochyMap.get(id), "clean");
        train2.Message = "Я у тебя питомец порядочный, чистоплотный! И чтобы другие питомцы от меня не шарахались," +
                "почаще води меня в ванную! Для этого введи любую из команд: \"гигиена\", \"мыться\", \"мойся\"." +
                "Кстати, если случится оказия, и я не успею сходить в туалет, гигиена упадет до нуля!";
        events.add(train2);

        date = new Date(currentDate.getTime() + 45000);
        Training train3 = new Training(id, date, tamagochyMap.get(id), "fun");
        train3.Message = "Иногда мне не нужно от тебя ничего, кроме обнимашек! На самом деле, в будущем за счастье" +
                "будут отвечать другие вазимодействия - например, игры или другое общение, но пока что так..." +
                "Чтобы сделать меня счастливым, много не надо. Просто введи \"счастье\" или \"веселье\". ";
        events.add(train3);

		date = new Date(currentDate.getTime() + 55000);
		Training train4 = new Training(id, date, tamagochyMap.get(id), "hungry");
		train4.Message = "Голод - опасная штука. Как только моя сытость упадет до нуля, я умру. И все! И мы " +
				"больше никогда не увидимся! Поэтому корми меня почаще. От переедания еще никто не умирал, " +
				"а вот от голода... ЧТобы меня покормить, напиши \"питание\", \"еда\", \"ешь\", \"покормить\"";
		events.add(train4);

		date = new Date(currentDate.getTime() + 65000);
		Training train5 = new Training(id, date, tamagochyMap.get(id), "sleep");
		train5.Message = "И, наконец, сон! Для того, что полностью выспаться, мне нужно всего лишь пару минуточек, " +
				"а еще я сплю очень крепко, поэтому не пытайся меня разбудить, бесполезно! Чтобы отправить меня спать," +
				"введи \"спать\", \"сон\", \"спи\"";
		events.add(train5);

		date = new Date(currentDate.getTime() + 80000);
		Training train6 = new Training(id, date, tamagochyMap.get(id), "end");
		train6.Message = "На этом пока что все! Теперь ты умеешь ухаживать за мной. Надеюсь, мы подружимся!";
		events.add(train6);


    }
}
