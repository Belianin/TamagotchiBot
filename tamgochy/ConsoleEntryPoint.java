package tamgochy;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleEntryPoint {
	public static void main(String[] args) {
		Bot bot = new Bot();
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

				String reply = bot.reply(input, userID);
				System.out.println("ID: " + userID + " || " + reply);
			}
			System.out.println("Введите новый ID, чтобы продожить или \"выход\" чтобы выйти");
		}
	}

	public static String readInput() {
		Scanner input = new Scanner(System.in);
		String command = input.next();
		return command;
	}
}