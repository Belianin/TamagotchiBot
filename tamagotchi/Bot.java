package tamagotchi;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class Bot {
	public ConcurrentHashMap<String, UserData> users;
	private HashMap<DialogName, Dialog> dialogs = new HashMap<>();
	private BotListener listener;
	private FileWorker fileWorker;

	public Bot(BotListener listener) {
		fileWorker = new FileWorker("data/users/");
		fileWorker.loadEncounters();
		users = fileWorker.loadUsers();
		this.listener = listener;
		dialogs.put(DialogName.Start, new StartDialog());
		dialogs.put(DialogName.Main, new MainDialog(this));
		dialogs.put(DialogName.Training, new TrainingDialog());
		dialogs.put(DialogName.Creation, new CreationDialog());

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				for (UserData user : users.values()) {
					processEvents(user);
					warnUser(user);
				}
			}
		}, 20000, 20000);
	}
	
	public void saveUsers() {
		fileWorker.saveUsers(users);
	}

	private UserData getUser(String id) {
		UserData newUserData = new UserData(id);
		UserData user = users.putIfAbsent(id, newUserData);
		if (user != null)
			return user;

		return newUserData;
	}

	public void processEvents(UserData user) {
		for (Event event : user.events.toArray(new Event[user.events.size()])) {
			if (event.when.before(new Date())) {
				if (event.tryApply())
					user.events.remove(event);
				if (event.hasReply()) {
					Reply reply = event.getReply();
					if (reply.getNextDialog() != DialogName.None)
						user.currentDialog = reply.getNextDialog();
					listener.processMessage(user.id, reply);
				}
			}
		}
	}

	public Reply reply(String input, String id) {
		UserData user = getUser(id);

		synchronized (user) {
			processEvents(user);
			Dialog dialog = dialogs.get(user.currentDialog);
			Reply reply = dialog.reply(user, input);

			if (reply.getNextDialog() != DialogName.None)
				user.currentDialog = reply.getNextDialog();

			warnUser(user);

			return reply;
		}
	}

	private void warnUser(UserData user) {
		if (user.pet == null)
			return;
		if (!user.pet.alive) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		if (user.pet.getHunger() <= 20)
			sb.append("🍎: " + user.pet.getHunger() + " | ");
		if (user.pet.getHealth() <= 20)
			sb.append("💊: " + user.pet.getHunger() + " | ");
		if (sb.length() > 0) {
			listener.processMessage(user.id, new Reply("⚠️ Внимание!⚠\nНизкие показатели:\n" + sb.toString()));
		}
	}
}
