import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc1 = new Scanner(new File("./traffic.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./traffic.out")));

        int segments = sc1.nextInt();

        String[] type = new String[segments];
        int[] num1 = new int[segments];
        int[] num2 = new int[segments];

        int min = 0, max = 0;
        for (int x = 0; x < segments; x++) {
            sc1.nextLine();
            type[x] = sc1.next();
            num1[x] = sc1.nextInt();
            num2[x] = sc1.nextInt();

            min -= num1[x];
            max += num2[x];
        }

        int[] xy = new int[] {min, max};

        for (int x = segments -1; x >= 0; x--) {
            if (type[x].equals("on")) {
                off(xy, num1[x], num2[x]);
            } else if (type[x].equals("off")) {
                on(xy, num1[x], num2[x]);
            } else {
                none(xy, num1[x], num2[x]);
            }
        }
        pw.println(xy[0]+" " + xy[1]);

        for (int x = 0; x < segments; x++) {
            if (type[x].equals("on")) {
                on(xy, num1[x], num2[x]);
            } else if (type[x].equals("off")) {
                off(xy, num1[x], num2[x]);
            } else {
                none(xy, num1[x], num2[x]);
            }
        }
        pw.println(xy[0]+" " + xy[1]);
        pw.close();
    }

    public static void on(int[] xy, int first, int second) {
        xy[0] = Math.max(0, xy[0] + first);
        xy[1] = Math.max(0, xy[1] + second);
    }

    public static void off(int[] xy, int first, int second) {
        xy[0] = Math.max(0, xy[0] - second);
        xy[1] = Math.max(0, xy[1] - first);
    }

    public static void none(int[] xy, int first, int second) {
        xy[0] = Math.max(first, xy[0]);
        xy[1] = Math.min(second, xy[1]);
    }
}
