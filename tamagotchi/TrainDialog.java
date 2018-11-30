package tamagotchi;

public class TrainDialog implements Dialog {

	public Reply reply(UserData user, String input)
	{
		//тыры пыры введи я тонял
		if (input.toLowerCase().equals("я понял!!!"))
			return new Reply("Молорик!!! ИДИ В МЕЙНЙ", DialogName.Main);
		return new Reply("dori_ы");
	}
}
