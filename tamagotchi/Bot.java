//package tamagotchi;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Bot {
	private ConcurrentHashMap<String, UserData> users = new ConcurrentHashMap<>();
	private HashMap<DialogName, Dialog> dialogs = new HashMap<>();
	
	public Bot()
	{
		dialogs.put(DialogName.Start, new StartDialog());
		dialogs.put(DialogName.Main, new MainDialog());
		dialogs.put(DialogName.Train, new TrainingDialog());
	}
	
	private UserData getUser(String id)
	{
		UserData user = users.putIfAbsent(id, new UserData(id));
		if (user != null)
			return user;
		
		return users.putIfAbsent(id, new UserData(id));
	}
	
	public void processEvents(UserData user)
	{
		for (Event event : user.events.toArray(new Event[user.events.size()])) {
			if (event.when.before(new Date())) {
				if (event.tryApply())
					user.events.remove(event);
			}
		}
	}

	public Reply reply(String input, String id) {
		UserData user = getUser(id);
		
		synchronized (user) {
			processEvents(user);
			Dialog dialog = dialogs.get(user.currentDialog);
			Reply reply =  dialog.reply(user, input);
			
			if (reply.getNextDialog() != DialogName.None)
				user.currentDialog = reply.getNextDialog();
			
			return reply;
		}
//
//		if (input.equals("да")) {
//			makeTraining(id);
//			return new Reply(
//					"Я - твой питомец! Твоя главная цель - не дать мне умереть. "
//							+ "Чтобы узнать, как мои дела, напиши \"проверка\"",
//					new Button[] { Button.Like, Button.Dislike });
//		}
//
//		if (!tamagochyMap.containsKey(id) || !tamagochyMap.get(id).alive) {
//			Pet needs = new Pet();
//			tamagochyMap.put(id, needs);
//			return new Reply("Я родился! Хочешь получать подсказки, как со мной обращаться?");
//		}
//		Pet currentPet = tamagochyMap.get(id);
//		if (!currentPet.alive) {
//			return new Reply("Кажется, это конец... Чтобы начать новую игру, введи любое сообщение");
//		}
//
//		checkForDeath(id);
//		if (currentPet.trainLvl < 7) {
//			if (currentPet.train && currentPet.messages.length() != 0) {
//				String reply = currentPet.messages.toString();
//				currentPet.messages.setLength(0);
//				return new Reply(reply);
//			}
//		} else {
//			currentPet.train = false;
//			currentPet.trainLvl = 0;
//		}
	}
//
//	public void makeTraining(String id) {
//		UserData user = getUser(id);
//		Pet pet = user.pet;
//		Date currentDate = new Date();
//		Date date = new Date(currentDate.getTime() + 10000);
//		Training train0 = new Training(id, date, pet, "проверка");
//		train0.Message = "Мои потребности периодически убывают. Мне становится голодно, холодно..."
//				+ " Если ты будешь редко меня навещать,"
//				+ "то... я умру! В ужасных муках! Ты ведь не дашь мне умереть???";
//		user.events.add(train0);
//
//		date = new Date(currentDate.getTime() + 20000);
//		Training train1 = new Training(id, date, pet, "toilet");
//		train1.Message = "Потребность ходить в туалет - самая быстропонижающаяся из всех. Если она упадет до "
//				+ "нуля, то... в общем, постараемся этого не допускать? Чтобы сводить меня в туалет, напиши"
//				+ "\"туалет\"";
//		user.events.add(train1);
//
//		date = new Date(currentDate.getTime() + 35000);
//		Training train2 = new Training(id, date, pet, "clean");
//		train2.Message = "Я у тебя питомец порядочный, чистоплотный! И чтобы другие питомцы от меня не шарахались,"
//				+ "почаще води меня в ванную! Для этого введи любую из команд: \"гигиена\", \"мыться\", \"мойся\"."
//				+ "Кстати, если случится оказия, и я не успею сходить в туалет, гигиена упадет до нуля!";
//		user.events.add(train2);
//
//		date = new Date(currentDate.getTime() + 45000);
//		Training train3 = new Training(id, date, pet, "fun");
//		train3.Message = "Иногда мне не нужно от тебя ничего, кроме обнимашек! На самом деле, в будущем за счастье"
//				+ "будут отвечать другие вазимодействия - например, игры или другое общение, но пока что так..."
//				+ "Чтобы сделать меня счастливым, много не надо. Просто введи \"счастье\" или \"веселье\". ";
//		user.events.add(train3);
//
//		date = new Date(currentDate.getTime() + 55000);
//		Training train4 = new Training(id, date, pet, "hungry");
//		train4.Message = "Голод - опасная штука. Как только моя сытость упадет до нуля, я умру. И все! И мы "
//				+ "больше никогда не увидимся! Поэтому корми меня почаще. От переедания еще никто не умирал, "
//				+ "а вот от голода... ЧТобы меня покормить, напиши \"питание\", \"еда\", \"ешь\", \"покормить\"";
//		user.events.add(train4);
//
//		date = new Date(currentDate.getTime() + 65000);
//		Training train5 = new Training(id, date, pet, "sleep");
//		train5.Message = "И, наконец, сон! Для того, что полностью выспаться, мне нужно всего лишь пару минуточек, "
//				+ "а еще я сплю очень крепко, поэтому не пытайся меня разбудить, бесполезно! Чтобы отправить меня спать,"
//				+ "введи \"спать\", \"сон\", \"спи\"";
//		user.events.add(train5);
//
//		date = new Date(currentDate.getTime() + 80000);
//		Training train6 = new Training(id, date, pet, "end");
//		train6.Message = "На этом пока что все! Теперь ты умеешь ухаживать за мной. Надеюсь, мы подружимся!";
//		user.events.add(train6);
//
//	}
}
