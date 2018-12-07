package tamagotchi;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class Bot {
	private ConcurrentHashMap<String, UserData> users = new ConcurrentHashMap<>();
	private HashMap<DialogName, Dialog> dialogs = new HashMap<>();
	private BotListener listener;
	private FileWorker fileWorker;
	
	public Bot(BotListener listener)
	{
		fileWorker = new FileWorker("data/users/");
		fileWorker.loadEncounters();
		this.listener = listener;
		dialogs.put(DialogName.Start, new StartDialog());
		dialogs.put(DialogName.Main, new MainDialog());
		dialogs.put(DialogName.Training, new TrainingDialog());
		dialogs.put(DialogName.Creation, new CreationDialog());
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				for (UserData user : users.values())
					processEvents(user);
			}
		}, 20000, 20000);
	}
	
	private UserData getUser(String id)
	{
		UserData newUserData = new UserData(id);
		UserData user = users.putIfAbsent(id, newUserData);
		if (user != null)
			return user;
		
		return newUserData;
	}
	
	public void processEvents(UserData user)
	{
		for (Event event : user.events.toArray(new Event[user.events.size()])) {
			if (event.when.before(new Date())) {
				if (event.tryApply())
					user.events.remove(event);
				if (event.hasReply())
					listener.processMessage(user.id, event.getReply());
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

			return reply;
		}
	}
}
