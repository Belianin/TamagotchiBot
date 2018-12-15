package tamagotchi;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.*;

public class FileWorker {
	String userDirectory;
	String[] encountersDirectory = {"data/castle/", "data/beach/"};
	Gson gson;

	public FileWorker(String userDir) {
		userDirectory = "data/users/"; //userDir;
		gson = new Gson();
	}

	public String readFile(String fileName) {
		StringBuilder sb = new StringBuilder();
		try (FileReader fr = new FileReader(fileName)) {
			Scanner scan = new Scanner(fr);

			while (scan.hasNextLine()) {
				sb.append(scan.nextLine());
			}
			scan.close();
			// fr.close();
		} catch (IOException e) {
			// System.out.println("File not found");
			return null;
		}
		return sb.toString();
	}

	public String readFile(File file) {
		StringBuilder sb = new StringBuilder();
		try (FileReader fr = new FileReader(file)) {
			Scanner scan = new Scanner(fr);

			while (scan.hasNextLine()) {
				sb.append(scan.nextLine());
			}
			scan.close();
			// fr.close();
		} catch (IOException e) {
			// System.out.println("File not found");
			return null;
		}
		return sb.toString();
	}

	public void deleteUser(String name) {
		File file = new File(userDirectory + name);
		file.delete();
	}
	
	public ConcurrentHashMap<String, UserData> loadUsers() {
		ConcurrentHashMap<String, UserData> users = new ConcurrentHashMap<String, UserData>();
		
		File directory = new File(userDirectory);
		File[] files = directory.listFiles();
		if (files == null)
			return users;

		for (File file : files) {
			UserData user = gson.fromJson(readFile(file), UserData.class);
			users.put(user.id, user);
		}
		
		return users;
			
		
	}

	public UserData loadUser(String name) {
		String rawUser = readFile(userDirectory + name);
		if (rawUser == null)
			return null;
		UserData user = gson.fromJson(rawUser, UserData.class);
		user.pet.master = user;
		return user;
	}
	
	public void saveUsers(ConcurrentHashMap<String, UserData> users) {
		for (UserData user : users.values())
			saveUser(user);
	}

	public void saveUser(UserData user) {
		try (FileWriter fw = new FileWriter(userDirectory + user.id, false)) {
			fw.write(gson.toJson(user));
			fw.close();
		} catch (IOException e) {
			System.out.println("Writting error!");
		}
	}

	public void loadEncounters() {
		File directory = new File("data/encounters/");
		File[] files = directory.listFiles();
		if (files == null)
			return;

		for (File file : files)
			Encounters.addEncounter(gson.fromJson(readFile(file), Encounter.class));


//		directory = new File("data/castle");
//		files = directory.listFiles();
//		if (files == null)
//			return;

		for (String dir : encountersDirectory) {
			String key = dir.split("/")[1];
			File direct = new File(dir);
			files = direct.listFiles();
			Encounters.putStoryName(key);
			for (File file : files) {
				Encounters.addStoryEncounter(gson.fromJson(readFile(file), Encounter.class), key);

			}
		}
	}
}