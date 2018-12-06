package tamagotchi;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelegramEntryPoint extends TelegramLongPollingBot implements BotListener  {
	private Bot bot;
	
	public TelegramEntryPoint()
	{
		bot = new Bot(this);
	}

	public static void main(String[] args) {
		System.out.println("Starting...");
		ApiContextInitializer.init();
		TelegramBotsApi botapi = new TelegramBotsApi();
		try {
			botapi.registerBot(new TelegramEntryPoint());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		System.out.println("Settled");
	}

	@Override
	public String getBotUsername() {
		return "DungotchiBot";
	}

	@Override
	public void onUpdateReceived(Update e) {
		processMessage(e);
	}

	private void processMessage(Update e) {
		Message msg = null;
		String text = null;

		if (e.hasMessage() && e.getMessage().hasText()) {
			msg = e.getMessage();
			text = msg.getText();
		} else if (e.hasCallbackQuery()) {
			msg = e.getCallbackQuery().getMessage();
			text = e.getCallbackQuery().getData();
		}

		if (msg == null || text == null)
			return;

		System.out.println(msg.getChatId() + ": " + text);

		sendAnswer(msg, bot.reply(text, msg.getChatId().toString()));
	}
	
	private void sendAnswer(Message msg, Reply reply)
	{
		sendMessage(msg.getChatId(), reply);
	}

	private void sendMessage(Long chatId, Reply reply) {
		SendMessage s = new SendMessage();
		s.setChatId(chatId);
		s.setText(reply.getText());

		if (reply.getButtons().size() != 0)
			s.setReplyMarkup(getMarkup(reply.getButtons()));

		try {
			execute(s);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private ReplyKeyboardMarkup getMarkup(List<List<Button>> buttons) {

		ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
		markup.setResizeKeyboard(true);
		markup.setOneTimeKeyboard(true);
		markup.setSelective(true);

		List<KeyboardRow> keyboard = new ArrayList<>();

		for (List<Button> row : buttons) {
			KeyboardRow rowKeyboard = new KeyboardRow();
			
			for (Button rawButton : row) {
				rowKeyboard.add(rawButton.title);
			}

			keyboard.add(rowKeyboard);
		}
		
		markup.setKeyboard(keyboard);
		return markup;
	}

	@Override
	public String getBotToken() {
		return System.getenv("DUNGOTCHI_TOKEN");
	}

	@Override
	public void processMessage(String id, Reply reply) {
		try {
			Long chatId = Long.parseLong(id);
			sendMessage(chatId, reply);
		} catch (NumberFormatException e)
		{
			
		}
		
	}
}
