//package tamgochy;

public class ToiletCommand extends Command {	
	public ToiletCommand() {
		synonyms.add("туалет");
		defaultReply = new Reply("Секундочку, я отлучусь...");
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
		if (bot.getTamagochyMap().get(id).canToilet) {
			bot.getTamagochyMap().get(id).addToilet(100);
			return defaultReply;
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
