package tamgochy;

public class ToiletCommand extends Command {	
	public ToiletCommand() {
		synonyms.add("туалет");
		defaultReply = new Reply("Секундочку, я отлучусь...");
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
    	bot.handleEvent("toilet", id);
    	return defaultReply;
	}
}
