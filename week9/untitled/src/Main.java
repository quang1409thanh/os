import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Integer[] a = {2069, 1212, 2296, 2800, 544, 1618, 356, 1523, 4965, 3681};
        List<Integer> disk_queue = new ArrayList<>(Arrays.asList(a));
        int start = 2150;
        Input input = new Input(disk_queue, start, 0, 4999);
        Output output = new Output();
        System.out.println("hãy nhập số thứ tự thuật toán\n" +
                "1. fcfs \n" +
                "2. sstf \n" +
                "3. scan \n" +
                "4. c_scan \n" +
                "5. look \n" +
                "6. c_look \n" +
                "7. exit \n");
        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            switch (n) {
                case 1 -> output = fun_c.fcfs(input);
                case 2 -> output = fun_c.sstf(input);
                case 3 -> output = fun_c.scan(input);
                case 4 -> output = fun_c.c_scan(input);
                case 5 -> output = fun_c.look(input);
                case 6 -> output = fun_c.c_look(input);
                case 7 -> exit = true;
            }
            System.out.println(output);
        }
    }
}
