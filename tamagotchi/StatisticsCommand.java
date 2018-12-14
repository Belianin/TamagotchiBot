package tamagotchi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class StatisticsCommand extends Command {
	private Bot bot;
	
	public StatisticsCommand(Bot bot) {
		this.bot = bot;
		synonyms.addAll(Arrays.asList(new String[] {"?"}));
	}
	
	@Override
	public Reply reply(UserData user, String input) {
		List<UserData> users = new ArrayList(bot.users.values());
		Collections.sort(users, new Comparator<UserData>() {
			public int compare(UserData user1, UserData user2) {
				if (user1.pet == null || user2.pet == null)
					return 1;
				return user1.pet.getBirthday().compareTo(user2.pet.getBirthday());
			}
		});
		
		StringBuilder sb = new StringBuilder();
				
		int i = 0;
		for (UserData data : users) {
			if (data.pet == null)
				continue;
			
			i++;
			if (i == 1)
				sb.append("🥇. " + getUserStatistic(data) + "\n");
			else if (i == 2)
				sb.append("🥈. " + getUserStatistic(data) + "\n");
			else if (i == 3)
				sb.append("🥉. " + getUserStatistic(data) + "\n");
			else
				sb.append(i + ". " + getUserStatistic(data) + "\n");
			
			if (i >= 10)
				break;
		}
		return new Reply(sb.toString());
	}
	
	private String getUserStatistic(UserData user) {
		return user.pet.name + " живет уже " + getLifetime(user.pet) + " дней, с " + user.money + "💰";
	}
	
	private int getLifetime(Pet pet)
	{
		return new Date().getDay() - pet.getBirthday().getDay() + 1;
	}

}
