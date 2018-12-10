//package tamagotchi;

import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BotTest {
	
	private UserData user;
	
	@Before
	public void setUp() {
		user = new UserData("testUserName");
		user.pet = new Pet("testPetName", user);
	}
	
	@Test
	public void petDownTest() {
		int hunger = user.pet.getHunger();
		user.pet.addHunger(-80);
		assertEquals(hunger - 20, user.pet.getHunger());
	}

//    @Test
//    public void reply_txt() {
//        Bot bot = new Bot();
//        assertEquals(bot.reply("Питание", "10"), "Я родился!");
//        assertEquals(bot.reply("Питание", "10"), "Сейчас я буду кушать, сейчас меня покормят!");
//        assertEquals(bot.reply("Сон", "10"), "Я ложусь спать, не беспокой меня несколько минут!");
//        assertEquals(bot.reply("Туалет", "10"), "Секундочку, я отлучусь...");
//        assertEquals(bot.reply("Счастье", "10"), "Я так рад, что ты у меня есть!");
//        assertEquals(bot.reply("Гигиена", "10"), "А сейчас я хочу расслабиться и принять ванну");
//        assertEquals(bot.reply("Трататата", "10"), "Я не знаю такой команды! Если хочешь узнать список доступных, введи \"Команды\"");
//        assertEquals(bot.reply("Счастье", "15"), "Я родился!");
//    }

//    @Test
//    public void getDeathCount_test() {
//        Date currentDate = new Date();
//        Bot bot = new Bot();
//        int count = bot.getDeathCount(currentDate);
//        assertEquals(count, 0);
//
//        currentDate = new Date();
//        Date testDate = new Date(currentDate.getTime() + 7000);
//        count = bot.getDeathCount(testDate);
//        assertEquals(count, 0);
//
//        currentDate = new Date();
//        testDate = new Date(currentDate.getTime() + 20000);
//        count = bot.getDeathCount(testDate);
//        assertEquals(count, 2);
//
//    }
}