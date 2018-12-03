package tamagotchi;

import java.util.ArrayList;

public class TrainingDialog implements Dialog {
	private DialogName name = DialogName.Main;
	private ArrayList<Command> commands = new ArrayList<Command>();

	public Reply reply(UserData user, String input) {
		input = input.toLowerCase();
		if (input.equals("да") && user.pet.trainLvl == 0) {
			user.pet.trainLvl += 1;
			return new Reply("Твоя главная цель - следить за моими потребностями. Они будут влиять на мою жизнь " +
					"самым прямым образом. Самая быстропонижающаяся потребность - туалет. Чтобы я сходила в туалет, напиши" +
					"\"туалет\". Или нажми на кнопку. Затем мы перейдем к следующей команде", new Button[]{new Button("🚽")});
		}
		else if ((input.equals("туалет")) || (input.equals("🚽")) && user.pet.trainLvl == 1) {
			user.pet.trainLvl += 1;
			return new Reply("Какое облегчение! Вторая потребность, напрямую связанная с туалетом, - гигиена." +
					"Если вдруг ты не успеешься прийти вовремя и случиться конфуз... гигиена, естественно, упадет до нуля." +
					"Неприятная ситуация. Чтобы исправить ее, введи одну из команд: \"гигиена\", \"мыться\", \"мойся\" " +
					"или нажми на кнопку", new Button[]{new Button("💦")});
		}
		else if ((input.equals("гигиена") || input.equals("мыться") || input.equals("мойся") || input.equals("💦")) && user.pet.trainLvl == 2) {
			user.pet.trainLvl += 1;
			return new Reply("Здорово! Вода была такой теплой... ♥. Теперь поговорим о более важных характеристиках." +
					"Например сон. Если я не лягу спать вовремя, я усну где попало. И черт знает, чем это может" +
					"кончится... Чтобы я лег спать, используй команды: \"спать\", \"сон\", \"спи\". Ну и кнопка" +
					"также в твоем распоряжении", new Button[]{new Button("💤")});
		}
		else if (input.equals("спать") || input.equals("сон") || input.equals("спи") || input.equals("💤") && user.pet.trainLvl == 3) {
			user.pet.trainLvl += 1;
			return new Reply("Кстати, когда игра начнется, сон будет занимать некоторое время, в течение которого " +
					"взаимодействовать со мной будет невозможно. Но не волнуйся, я высыпаюсь быстрее, чем вы, люди!" +
					"А сейчас я бы хотел перекусить. И учти, что от моего питания зависит моя жизнь! Если оно упадет" +
					"до нуля - я умру, и тебе придется начать все заново. Ну или мне... Неважно! Чтобы меня покормить," +
					"введи \"питание\", \"еда\", \"ешь\", \"покормить\". Да, кнопка по-прежнему в твоем распоряжении",
					new Button[]{new Button("🍎")});
		}
		else if (input.equals("питание") || input.equals("еда") || input.equals("ешь") || input.equals("покормить") ||
				input.equals("🍎") && user.pet.trainLvl == 4) {
			user.pet.trainLvl += 1;
			return new Reply("Последняя характеристика - здоровье. Это единственная потребность, которая не зависит от времени." +
					"Она будет понижаться (а если повезет - повышаться) только в подземелье. И если ты упустишь момент" +
					"и не успеешь вовремя меня подлечить... Да, придется начать сначала. Чтобы этого не случилось," +
					"введи \"здоровье\" или \"жизни\". И кнопка, да. Все для твоего удобства!", new Button[]
					{new Button("💊")});
		}
		else if ((input.equals("здоровье")) || (input.equals("жизни")) || (input.equals("💊")) && user.pet.trainLvl == 5) {
			user.pet.trainLvl += 1;
			return new Reply("Вот и все! Возможно, в плане обучения у тебя возник вопрос: \"А зачем вообще" +
					"все это нужно?\" Знай: мое текущее состояние прямым образом влияет на сюжет в подземелье." +
					"В зависимости от него я буду принимать те или иные решения. Ты никогда не узнаешь, как именно" +
					"твоя команда повлияет на историю, но в этом-то вся прелесть! Ну что, ты готов?", new Button[]
					{new Button("Да! ✨")});
		}
		else if (input.equals("да! ✨") && user.pet.trainLvl == 6) {
			user.events.add(new DeathEvent(user.pet));
			Reply reply = new Reply("Игра началась!", DialogName.Main);
			reply.setButtons(Button.getMainButtons());
			return reply;
		}
		System.out.println(user.pet.trainLvl);
		return new Reply("Кажется, ты делаешь что-то не так! Перечитай обучающее сообщение и попробуй еще раз!");
	}
}
