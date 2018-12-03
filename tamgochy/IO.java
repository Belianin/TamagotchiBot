//import java.util.Scanner;
//
//public class IO implements Runnable {
//    static boolean activity = false;
//    static boolean game = false;
//    public void run() {
//        Scanner s = new Scanner(System.in);
//        while (true) {
//            while (game) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                }
//            }
//            String key = s.next();
//            if (key.equals("проверка")) {
//                System.out.println(chatBot2.state);
//                activity = false;
//            }
//            if (key.equals("покормить")) {
//                chatBot2.state.put("Питание", Life.food(chatBot2.state.get("Питание")));
//                chatBot2.state.put("Общение", chatBot2.state.get("Общение") +5);
//                activity = false;
//            }
//            if (key.equals("туалет")) {
//                chatBot2.state.put("Естественная нужда", Life.toilet(chatBot2.state.get("Естественная нужда")));
//                chatBot2.state.put("Общение", chatBot2.state.get("Общение") +5);
//                activity = false;
//            }
//            if (key.equals("спать")) {
//                chatBot2.state.put("Сон", Life.sleeping(chatBot2.state.get("Сон")));
//                System.out.println(chatBot2.state.get("Сон"));
//                activity = false;
//            }
//            if (key.equals("вымыть")) {
//                chatBot2.state.put("Гигиена", Life.bath(chatBot2.state.get("Гигиена")));
//                chatBot2.state.put("Общение", chatBot2.state.get("Общение") +5);
//                activity = false;
//            }
//            if (key.equals("справка")) {
//                System.out.println(Texts.info);
//
//            }
////            if (key.equals("команды")) {
////                for (String el: Texts.commands) {
////                    System.out.println(el);
////                }
////            }
//            if (key.equals("выход")) {
//                System.exit(0);
//            }
//            if (key.equals("города")) {
//                activity = true;
//                chatBot2.state.put("Досуг", 100);
//                cityes mythread = new cityes();
//                Thread th = new Thread(mythread);
//                th.start();
//
//            }
//            }
//            }
//
//
//
//
//    }


