package tamgochy;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleEntryPoint {
	static Scanner input = new Scanner(System.in);
	
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

				Reply reply = bot.reply(input, userID);
				System.out.println("ID: " + userID + " || " + reply.getText());
			}
			System.out.println("Введите новый ID, чтобы продожить или \"выход\" чтобы выйти");
		}
	}

	public static String readInput() {
		return input.next();
	}
}