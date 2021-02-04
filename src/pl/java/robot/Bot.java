package pl.java.robot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Bot {
    private final static Map<Integer, Point2D> coords = new HashMap<>();

    public static void main(String[] args) throws AWTException, InterruptedException {

        int x = 0;
        int y = 490;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == 0) {
                    coords.put(j, new Point2D.Float(625 + x, y));
                } else if (i == 1) {
                    coords.put(j + 12, new Point2D.Float(625 + x, y + 40));
                } else {
                    coords.put(j + 24, new Point2D.Float(625 + x, y + 90));
                }
                x += 60;
            }
            x = 0;
        }
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Losowe nuty\n2. Wybrane nuty");
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> randomNotes();
            case 2 -> chosenNotes();
        }

    }


    private static void randomNotes() throws AWTException, InterruptedException {
        Thread.sleep(1500);
        Robot robot = new Robot();
        int rand;
        Random random = new Random();

        for (int i = 0; i < 18; i++) {
            rand = random.nextInt(36);
            robot.mouseMove((int) coords.get(rand).getX(), (int) coords.get(rand).getY());
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(250);
        }
    }

    private static void chosenNotes() throws AWTException, InterruptedException {//
        Scanner scanner = new Scanner(System.in);                               // 1 24 5 34 12 3 15 12 0 22
        System.out.println("Podaj input");
        String s = scanner.nextLine();
        Thread.sleep(1500);
        List<Integer> list =
                Arrays.stream(s.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList()); // fajny regex:
                                    // This will cause any number of consecutive spaces to split your string into tokens.

        Robot robot = new Robot();

        for (int i = 0; i < list.size(); i++) {
            if (coords.containsKey(list.get(i))) {
                robot.mouseMove((int) coords.get(list.get(i)).getX(), (int) coords.get(list.get(i)).getY());
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(250);
            }
        }
    }
}
