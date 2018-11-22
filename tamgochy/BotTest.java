package tamgochy;
import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Test;

public class BotTest {
	
	@Test
	public void petDownTest() {
		Pet pet = new Pet();
		pet.Down("hunger", 20);
		pet.Down("hunger", 20);
		pet.Down("hunger", 20);
		pet.Down("hunger", 20);
		assertEquals(20, pet.hunger);
	}

    @Test
    public void reply_txt() {
        Bot bot = new Bot();
        assertEquals(bot.reply("Питание", "10"), "Я родился!");
        assertEquals(bot.reply("Питание", "10"), "Сейчас я буду кушать, сейчас меня покормят!");
        assertEquals(bot.reply("Сон", "10"), "Я ложусь спать, не беспокой меня несколько минут!");
        assertEquals(bot.reply("Туалет", "10"), "Секундочку, я отлучусь...");
        assertEquals(bot.reply("Счастье", "10"), "Я так рад, что ты у меня есть!");
        assertEquals(bot.reply("Гигиена", "10"), "А сейчас я хочу расслабиться и принять ванну");
        assertEquals(bot.reply("Трататата", "10"), "Я не знаю такой команды! Если хочешь узнать список доступных, введи \"Команды\"");
        assertEquals(bot.reply("Счастье", "15"), "Я родился!");
    }
    @Test
    public void reply_creater() {
        Bot bot = new Bot();
        Bot expected = new Bot();
        Pet pet = new Pet();
        bot.reply("Счастье", "20");
        expected.putTamagochi("20", pet);
        assertEquals(bot.getTamagochyMap().size(), expected.getTamagochyMap().size());

        pet.Down("Питание", 50);
        bot.putTamagochi("20", pet);
        String expectStates = "Сон: 100 || Счастье: 100 || Гигиена: 100 || Питание: 50 || Туалет: 100 || ";
        assertEquals(bot.reply("Проверка", "20"), expectStates);

    }
    @Test
    public void getDeathCount_test() {
        Date currentDate = new Date();
        Bot bot = new Bot();
        int count = bot.getDeathCount(currentDate);
        assertEquals(count, 0);

        currentDate = new Date();
        Date testDate = new Date(currentDate.getTime() + 7000);
        count = bot.getDeathCount(testDate);
        assertEquals(count, 0);

        currentDate = new Date();
        testDate = new Date(currentDate.getTime() + 20000);
        count = bot.getDeathCount(testDate);
        assertEquals(count, 2);

    }
}