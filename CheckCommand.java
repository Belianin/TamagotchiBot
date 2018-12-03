
import java.util.Arrays;

public class CheckCommand extends Command {	
	public CheckCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"статус", "проверка"}));
	}

	@Override
	public Reply reply(UserData user, String input) {
		//здесь не дефолтный, так как эта переменная меняет свое значение в процессе работы
    	return new Reply(user.pet.getStates());
	}
}
