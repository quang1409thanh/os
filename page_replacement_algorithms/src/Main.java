import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> ref_string = new ArrayList<>();
        int frames = 3;

        int a[] = new int []{2, 1 ,4, 1, 2, 4, 3, 1, 2,5};
        for (int i = 0; i < a.length; i++) {
            ref_string.add(a[i]);
        }
        Input x = new Input();
        x.setPage_frames(frames);
        x.setReference_string(ref_string);
        Output output = new Output();
        System.out.println(" hay chon thuat toan\n" +
                "1. fifo\n" +
                "2. optimal\n" +
                "3. lru\n" +
                "4. mru\n" +
                "5. lfu\n" +
                "6. mfu\n" +
                "7. sc");
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        switch (n) {
            case 1 -> output = func_.fifo(x);
            case 2 -> output = func_.optimal(x);
            case 3 -> output = func_.lru(x);
            case 4 -> output = func_.mru(x);
            case 5 -> output = func_.lfu(x);
            case 6 -> output = func_.mfu(x);
            case 7 -> output = func_.sc(x);
        }
        System.out.println(output);

    }
}
