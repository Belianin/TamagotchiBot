package tamagotchi;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleEntryPoint implements BotListener {
	static Scanner input = new Scanner(System.in);
	
	public ConsoleEntryPoint()
	{
		Bot bot = new Bot(this);
		System.out.println("Введите ID: ");
		while (true) {
			String userID = readInput();

			if (userID.toLowerCase().equals("выход"))
				break;
			else
				System.out.println("Добро пожаловать, " + userID);

			while (true) {
				String input = readInput();

				if (input.toLowerCase().equals("выход"))
					break;

				Reply reply = bot.reply(input, userID);
				System.out.println("ID: " + userID + " || " + reply.getText());
			}
			System.out.println("Введите новый ID, чтобы продожить или \"выход\" чтобы выйти");
		}
		
	}
	
	public static void main(String[] args) {
		ConsoleEntryPoint console = new ConsoleEntryPoint();
	}

	public static String readInput() {
		return input.next();
	}

	@Override
	public void processMessage(String id, Reply reply) {
		System.out.println("ID: " + id + " || " + reply.getText());
	}
}