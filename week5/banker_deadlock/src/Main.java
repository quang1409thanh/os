import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Input input = banker_fun.read_file();
        resources rs_request1 = new resources(Arrays.asList(1, 0, 2), 3);
        resources rs_request2 = new resources(Arrays.asList(0, 0, 1), 3);

        // request này gây ra deadlock
        banker_fun.my_request(input, rs_request2, 2);
        System.out.println("Đường đi là: ");
        banker_fun.banker_deadlock(input);
        System.out.println(input);
    }
}