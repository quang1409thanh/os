import java.util.*;

public class fun_c {
    public static Output fcfs(Input input) {
        List<Integer> disk_out = input.getDisk_queue();
        disk_out.add(0, input.getStart());
        int total = 0;
        int old = input.getStart();
        for (int i : input.getDisk_queue()) {
            total += Math.abs(i - old);
            old = i;
        }
        return new Output(disk_out, total);
    }

    public static int min_(int old, List<Integer> disk_temp, List<Integer> disk_out) {
        int min_ = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < disk_temp.size(); i++) {
            if (!disk_out.contains(disk_temp.get(i)) && (Math.abs(disk_temp.get(i) - old) < min_)) {
                min_ = Math.abs(disk_temp.get(i) - old);
                index = i;
            }
        }
        return disk_temp.get(index);
    }

    public static Output sstf(Input input) {
        List<Integer> disk_out = new ArrayList<>();
        List<Integer> disk_temp = input.getDisk_queue();
        disk_out.add(input.getStart());
        int total = 0;
        int old = input.getStart();
        for (int i = 0; i < input.getDisk_queue().size(); i++) {
            int min__ = min_(old, disk_temp, disk_out);
            disk_out.add(min__);
            total += Math.abs(min__ - old);
            old = min__;
        }
        return new Output(disk_out, total);
    }

    public static Output scan(Input input) {
        List<Integer> disk_out = new ArrayList<>();
        disk_out.add(input.getStart());
        int total = 0;
        System.out.println("hướng của thuật toán là + hay - ");
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        if (a.equals("+")) {
            System.out.println("hướng dương");
            for (int i = input.getStart(); i <= input.get_max(); i++) {
                if (input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            disk_out.add(input.get_max());
            total += input.get_max() - input.getStart();
            for (int i = input.get_max(); i >= Collections.min(input.getDisk_queue()); i--) {
                if (!disk_out.contains(i) && input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += input.get_max() - Collections.min(input.getDisk_queue());
        } else if (a.equals("-")) {
            System.out.println("hướng âm");
            for (int i = input.getStart(); i >= input.get_min(); i--) {
                if (input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            disk_out.add(input.get_min());
            total += input.getStart() - input.get_min();
            for (int i = input.get_min(); i <= Collections.max(input.getDisk_queue()); i++) {
                if (!disk_out.contains(i) && input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += Collections.max(input.getDisk_queue()) - input.get_min();
        }
        return new Output(disk_out, total);
    }

    public static Output c_scan(Input input) {
        List<Integer> disk_out = new ArrayList<>();
        disk_out.add(input.getStart());
        int total = 0;
        System.out.println("hướng của thuật toán là + hay - ");
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        if (a.equals("+")) {
            System.out.println("hướng dương");
            for (int i = input.getStart(); i <= input.get_max(); i++) {
                if (input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            disk_out.add(input.get_max());
            total += input.get_max() - input.getStart();
            // lấy ra phần tử lớn nhất < start.
            int getMax = 0;
            for (int i = input.get_min(); i < input.getStart(); i++) {
                if (input.getDisk_queue().contains(i)) {
                    getMax = i;
                }
            }
            // quay về đầu luôn mà không cần làm gì cả .
            disk_out.add(input.get_min());
            for (int i = 0; i <= getMax; i++) {
                if (!disk_out.contains(i) && input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += getMax;
            System.out.println("không phục vụ yêu cầu nào trong quá trình " + input.get_max() + " -> " + input.get_min());
        } else if (a.equals("-")) {
            System.out.println("hướng âm");
            for (int i = input.getStart(); i >= input.get_min(); i--) {
                if (input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            disk_out.add(input.get_min());
            total += input.getStart() - input.get_min();

            // lấy ra phần tử bé nhất > start.
            int getMin = 0;
            for (int i = input.getStart(); i < input.get_max(); i++) {
                if (input.getDisk_queue().contains(i)) {
                    getMin = i;
                    break;
                }
            }
            // quay về đầu luôn mà không cần làm gì cả bro.
            disk_out.add(input.get_max());
            for (int i = input.get_max(); i >= getMin; i--) {
                if (!disk_out.contains(i) && input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += input.get_max() - getMin;
            System.out.println("không phục vụ yêu cầu nào trong quá trình " + input.get_min() + " -> " + input.get_max());

        }
        return new Output(disk_out, total);
    }

    public static Output look(Input input) {
        List<Integer> disk_out = new ArrayList<>();
        disk_out.add(input.getStart());
        int total = 0;
        System.out.println("hướng của thuật toán là + hay - ");
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        if (a.equals("+")) {
            System.out.println("hướng dương");
            for (int i = input.getStart(); i <= Collections.max(input.getDisk_queue()); i++) {
                if (input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += Collections.max(input.getDisk_queue()) - input.getStart();
            for (int i = Collections.max(input.getDisk_queue()); i >= Collections.min(input.getDisk_queue()); i--) {
                if (!disk_out.contains(i) && input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += Collections.max(input.getDisk_queue()) - Collections.min(input.getDisk_queue());
        } else if (a.equals("-")) {
            System.out.println("hướng âm");
            for (int i = input.getStart(); i >= Collections.min(input.getDisk_queue()); i--) {
                if (input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += input.getStart() - Collections.min(input.getDisk_queue());
            for (int i = Collections.min(input.getDisk_queue()); i <= Collections.max(input.getDisk_queue()); i++) {
                if (!disk_out.contains(i) && input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += Collections.max(input.getDisk_queue()) - Collections.min(input.getDisk_queue());
        }
        return new Output(disk_out, total);
    }

    public static Output c_look(Input input) {
        List<Integer> disk_out = new ArrayList<>();
        disk_out.add(input.getStart());
        int total = 0;
        System.out.println("hướng của thuật toán là + hay - ");
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        if (a.equals("+")) {
            System.out.println("hướng dương");
            for (int i = input.getStart(); i <= Collections.max(input.getDisk_queue()); i++) {
                if (input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += Collections.max(input.getDisk_queue()) - input.getStart();
            // lấy ra phần tử lớn nhất < start.
            int getMax = 0;
            for (int i = Collections.min(input.getDisk_queue()); i < input.getStart(); i++) {
                if (input.getDisk_queue().contains(i)) {
                    getMax = i;
                }
            }
            for (int i = Collections.min(input.getDisk_queue()); i <= getMax; i++) {
                if (!disk_out.contains(i) && input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += getMax - Collections.min(input.getDisk_queue());
            System.out.println("không phục vụ yêu cầu nào trong quá trình " + Collections.max(input.getDisk_queue()) + " -> " + Collections.min(input.getDisk_queue()));
        } else if (a.equals("-")) {
            System.out.println("hướng âm");
            for (int i = input.getStart(); i >= Collections.min(input.getDisk_queue()); i--) {
                if (input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += input.getStart() - Collections.min(input.getDisk_queue());
            // lấy ra phần tử bé nhất > start.
            int getMin = 0;
            for (int i = input.getStart(); i <= Collections.max(input.getDisk_queue()); i++) {
                if (input.getDisk_queue().contains(i)) {
                    getMin = i;
                    break;
                }
            }
            for (int i = Collections.max(input.getDisk_queue()); i >= getMin; i--) {
                if (!disk_out.contains(i) && input.getDisk_queue().contains(i)) {
                    disk_out.add(i);
                }
            }
            total += Collections.max(input.getDisk_queue()) - getMin;
            System.out.println("không phục vụ yêu cầu nào trong quá trình: " + Collections.min(input.getDisk_queue()) + " -> " + Collections.max(input.getDisk_queue()));

        }
        return new Output(disk_out, total);
    }

}
