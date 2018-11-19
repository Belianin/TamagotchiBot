package cities;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Cities implements Runnable {
        public static ArrayList reader () {
            ArrayList<String> cityesArray = new ArrayList<String>();
            try {
                FileInputStream fstream = new FileInputStream("C:\\Users\\Dori\\IdeaProjects\\untitled\\src\\cityes");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));  // отдельный метод
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    cityesArray.add(strLine);
                }
            } catch (IOException e) {
                System.out.println("Ошибка");
            }
            return cityesArray;
        }


        public void run () {
            IO.game = true;
            IO.activity = true;
            ArrayList<String> cityes = reader();
            Scanner in = new Scanner(System.in);
            char lastChar = '\u0000';
            System.out.println("Начинай!");
            while (true) {
                String userCity = in.nextLine();
                userCity = userCity.substring(0, 1).toUpperCase() + userCity.substring(1);
                if (userCity.equals("Выход")) {
                    IO.activity = false;
                    IO.game = false;
                    return;
                }
                if (cityes.indexOf(userCity) == -1) {
                    System.out.println("Неправильно! Попробуй еще раз");
                    continue;
                }

                if (lastChar != '\u0000' && userCity.charAt(0) != Character.toUpperCase(lastChar)) {
                    System.out.println("Неверно! Попробуй еще");
                    continue;
                }

                if (cityes.indexOf(userCity) != -1) {
                    lastChar = userCity.charAt(userCity.length() - 1);
                    if (lastChar == 'ы' || lastChar == 'ь' || lastChar == 'ъ' || lastChar == 'ц') {
                        lastChar = userCity.charAt(userCity.length() - 2);
                    }
                    cityes.remove(userCity);
                    boolean flag = true;
                    for (String city : cityes) {
                        if (city.charAt(0) == Character.toUpperCase(lastChar)) {
                            System.out.println(city);
                            cityes.remove(city);
                            System.out.println("Твой ход");
                            flag = false;
                            lastChar = city.charAt(city.length() - 1);
                            if (lastChar == 'ы' || lastChar == 'ь' || lastChar == 'ъ' || lastChar == 'ц') {
                                lastChar = city.charAt(city.length() - 2);
                            }
                            break;
                        }
                    }
                    System.out.println(lastChar);
                    if (flag) {
                        System.out.println("Кажется, я проиграл... :(");
                    }
                }
            }
    }

}
