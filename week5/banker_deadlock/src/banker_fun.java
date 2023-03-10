import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class banker_fun {

    public static boolean check(resources a) {
        for (int i = 0; i < a.getCnt(); i++) {
            if (a.getArr().get(i) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true nếu a < b
     */
    public static boolean check_less(resources a, resources b) {
        for (int i = 0; i < a.getCnt(); i++) {
            if (a.getArr().get(i) > b.getArr().get(i)) {
                return false;
            }
        }
        return true;
    }

    public static resources sub(resources a, resources b) {
        resources rs = new resources();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < a.getCnt(); i++) {
            arr.add(a.getArr().get(i) - b.getArr().get(i));
        }
        rs.setArr(arr);
        rs.setCnt(a.getCnt());
        return rs;
    }

    public static resources add(resources a, resources b) {
        resources rs = new resources();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < a.getCnt(); i++) {
            arr.add(a.getArr().get(i) + b.getArr().get(i));
        }
        rs.setArr(arr);
        rs.setCnt(a.getCnt());
        return rs;
    }

    public static Input read_file() {

        String s;
        String console_in;
        do {
            System.out.println("Bạn muốn đọc file cho chương trình banker hay deadloc ? [B/D]");
            Scanner sc_in = new Scanner(System.in);
            console_in = sc_in.next();
            System.out.println(console_in);
        }
        while (!console_in.equals("D") && !console_in.equals("B"));

        if (console_in.equals("D")) {
            s = "src/data_deadlock.txt";
        } else {
            s = "src/data_banker.txt";
        }

        File myfile = new File(s);
        Scanner sc = null;
        try {
            sc = new Scanner(myfile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int n;
        System.out.println("Đã nhận vào số n");
        n = sc.nextInt();
        List<Process> p = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Đã nhận số phần từ và mảng của Allocation P" + i + ":");
            int cnt_al = sc.nextInt();
            List<Integer> arr_al = new ArrayList<>();
            for (int j = 0; j < cnt_al; j++) {
                arr_al.add(sc.nextInt());
            }
            resources allocation = new resources(arr_al, cnt_al);
            System.out.println(allocation.toString());
            if (console_in.equals("B")) {
                System.out.println("Đã nhận số phần từ và mảng của max P" + i + ":");

                int cnt_m = sc.nextInt();
                List<Integer> arr_m = new ArrayList<>();
                for (int j = 0; j < cnt_m; j++) {
                    arr_m.add(sc.nextInt());
                }
                resources max = new resources(arr_m, cnt_m);
                System.out.println(max.toString());
                p.add(new Process_banker(allocation, max));
            } else {
                System.out.println("Đã nhận số phần từ và mảng của request P" + i + ":");
                int cnt_m = sc.nextInt();
                List<Integer> arr_m = new ArrayList<>();
                for (int j = 0; j < cnt_m; j++) {
                    arr_m.add(sc.nextInt());
                }
                resources request = new resources(arr_m, cnt_m);
                System.out.println(request.toString());
                p.add(new Process_deadlock(allocation, request));
            }
        }
        int cnt_ava = sc.nextInt();
        List<Integer> ava = new ArrayList<>();
        for (int i = 0; i < cnt_ava; i++) {
            ava.add(sc.nextInt());
        }
        resources available = new resources(ava, cnt_ava);
        return new Input(n, p, available);
    }

    public static void banker_deadlock(Input input) {
        boolean[] finish = new boolean[input.cntP];
        if (input.prc.get(0) instanceof Process_banker) {
            List<Process_banker> prc_ = new ArrayList<Process_banker>();
            for (Process x : input.prc) {
                Process_banker processBanker = (Process_banker) x;
                prc_.add(processBanker);
            }
            for (int i = 0; i < input.cntP; i++) {
                finish[i] = false;
            }
            for (int i = 0; i < input.cntP; i++) {
                for (int j = 0; j < input.cntP; j++) {
                    while (check_less(prc_.get(j).need, input.available) && !finish[j]) {
                        input.available = banker_fun.add(input.available, input.prc.get(j).allocation);
                        finish[j] = true;
                        System.out.print("P" + j + "->");
                    }
                }
            }
            for (int i = 0; i < input.cntP; i++) {
                if (!finish[i]) {
                    System.out.println("un_safe");
                    return;
                }
            }
            System.out.println("safe");

        } else {
            boolean deadlock = false;
            List<Process_deadlock> prc_ = new ArrayList<Process_deadlock>();
            for (Process x : input.prc) {
                Process_deadlock processDeadlock = (Process_deadlock) x;
                prc_.add(processDeadlock);
            }
            for (int i = 0; i < input.cntP; i++) {
                if (!prc_.get(i).allocation.equals(new resources(Arrays.asList(0, 0, 0), 3))) {
                    finish[i] = false;
                } else finish[i] = true;
            }
            for (int i = 0; i < input.cntP; i++) {
                for (int j = 0; j < input.cntP; j++) {
                    while (check_less(prc_.get(j).request, input.available) && !finish[j]) {
                        input.available = banker_fun.add(input.available, input.prc.get(j).allocation);
                        finish[j] = true;
                        System.out.print("P" + j + "->");
                    }
                }
            }
            for (int i = 0; i < input.cntP; i++) {
                if (!finish[i]) {
                    deadlock = true;
                    break;
                }

            }
            if (deadlock) {
                System.out.println("deadlock_detection");
                System.out.print("Các tiến trình gây ra deadlock là: ");
                for (int i = 0; i < input.cntP; i++) {
                    if (!finish[i]) {
                        System.out.print("P" + i + " ");
                    }
                }
                System.out.println();
            } else System.out.println("deadlock_free");

        }
        return;
    }

    public static void my_request(Input input, resources rs_request, int i) {
        // trường hợp request của banker
        // Trường hợp này nếu có thêm 1 yêu cầu thì chứng tỏ allocation được thêm rs_request, need và available giảm đi rs_request
        // trả về trạng thái khi nhận được 1 request
        if (input.prc.get(0) instanceof Process_banker) {
            input.available = sub(input.available, rs_request);
            input.prc.get(i).allocation = add(rs_request, input.prc.get(i).allocation);
            Process_banker x = (Process_banker) input.prc.get(i);
            ((Process_banker) input.prc.get(i)).need = sub(x.max, x.allocation);
        }
        // trường hợp request của deadlock

        else {
            Process_deadlock x = (Process_deadlock) input.prc.get(i);
            ((Process_deadlock) input.prc.get(i)).request = add(x.request, rs_request);
        }
    }
}
