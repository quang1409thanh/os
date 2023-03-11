import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.min;
import static java.lang.Math.max;

public class fun_schedule {
    /**
     * sắp xếp danh sách các tiến trình theo trình tự tới hàng đợi để đến cpu
     */
    static Comparator<Process> comparator_at = new Comparator<Process>() {
        @Override
        public int compare(Process x1, Process x2) {
            return Integer.compare(x1.getArrivalTime(), x2.getArrivalTime());
        }
    };

    public static List<Process> myInput(String s) {
        File myfile = new File(s);
        Scanner sc = null;
        try {
            sc = new Scanner(myfile);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        int n;
        System.out.println("Đã nhận vào số n");
        n = sc.nextInt();
        List<Process> p = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Process t_prc = new Process(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            t_prc.setRemaining_time(t_prc.getBurstTime());// sử dụng cho các trường hợp cho phép dừng.
            System.out.println("Đã nhận thông số của P[" + t_prc.getProcessId() + "]");
            p.add(t_prc);
        }
        return p;
    }

    /**
     * done
     * fcfs giải quyết theo thứ tự các tiến trình đến hàng đợi trước khi lên cpu
     *
     * @author: by Nguyễn Quang Thành, tự code
     */
    public static void fcfs(List<Process> p) {
        // sắp xếp theo thứ tự arrivalTime
        p.sort(comparator_at);
        for (int i = 0; i < p.size(); i++) {
            if (i == 0) {
                p.get(i).setStartTime(p.get(i).getArrivalTime());
            } else {
                if (p.get(i).getArrivalTime() <= p.get(i - 1).getCompletionTime()) {
                    p.get(i).setStartTime(p.get(i - 1).getCompletionTime());
                } else {
                    p.get(i).setStartTime(p.get(i).getArrivalTime());
                    System.out.println("idle: " + p.get(i - 1).getCompletionTime() + "->" + p.get(i).getArrivalTime());
                }
            }
            p.get(i).setWaitingTime(p.get(i).getStartTime() - p.get(i).getArrivalTime());
            p.get(i).setResponse_time(p.get(i).getStartTime() - p.get(i).getArrivalTime());
            p.get(i).setCompletionTime(p.get(i).getStartTime() + p.get(i).getBurstTime());
            p.get(i).setTurnaroundTime(p.get(i).getCompletionTime() - p.get(i).getArrivalTime());
            System.out.println("P" + p.get(i).getProcessId() + ": " + p.get(i).getStartTime() + " -> " + p.get(i).getCompletionTime());
        }
    }

    /**
     * sắp xếp theo thứ tự thời gian đến, nếu cùng thời gian đến thì sắp xếp theo sắp xếp theo thời gian bùng nổ.
     */
    public static void sort_sjf(List<Process> p) {
        for (int i = 0; i < p.size(); i++) {
            for (int j = i; j < p.size(); j++) {
                if (p.get(j).getArrivalTime() < p.get(i).getArrivalTime()) {
                    Collections.swap(p, i, j);
                } else if (p.get(j).getArrivalTime() == p.get(i).getArrivalTime()) {
                    if (p.get(j).getBurstTime() < p.get(i).getBurstTime()) {
                        Collections.swap(p, i, j);
                    }
                }
            }
        }
    }

    /**
     * thứ tự prio thấp tới cao nếu n == 0 và cao tới thấp nếu n == 1;
     */
    public static void sort_prio(List<Process> p, int n) {
        for (int i = 0; i < p.size(); i++) {
            for (int j = i; j < p.size(); j++) {
                if (p.get(j).getArrivalTime() < p.get(i).getArrivalTime()) {
                    Collections.swap(p, i, j);
                } else if (p.get(j).getArrivalTime() == p.get(i).getArrivalTime()) {
                    if (n == 1) {
                        if (p.get(j).getPriority() > p.get(i).getPriority()) {
                            Collections.swap(p, i, j);
                        }
                    } else if (n == 0) {
                        if (p.get(j).getPriority() < p.get(i).getPriority()) {
                            Collections.swap(p, i, j);
                        }
                    }
                }
            }
        }
    }

    /**
     * done _ xử lý các tính toán trung bính sau.
     * thuật toán sjf non-preemtive
     * 1. sắp xếp theo thứ tự trên
     * 2. tính start_time của từng tiến trình theo thứ tự.
     * 3. bắt đầu trường hợp 0
     * 4. trường hợp khác, nếu thời gian đến <= của trường hợp trước đó thì start_time là thời gian hoàn thành trước đó
     * 5. Trường hợp không đúng trên thì là lúc idle của cpu, thời gian bắt đầu bằng
     */
    public static void sjf(List<Process> p) {
        sort_sjf(p);
        // tính start_time cái khó nhất//
        int index = 0;
        boolean check = false;
        for (int i = 0; i < p.size(); i++) {
            check = false;
            if (i == 0) {
                p.get(i).setStartTime(p.get(i).getArrivalTime());
            } else {
                /**
                 * làm sao để xử lý trường hợp này đúng nhất ???
                 * yêu/cầu hiển thị các quá trình có trên hàng đợi khi hoàng thành quá trình trước theo thứ tự, in ra màn hình.
                 * */
                /**
                 * trường hợp có process có thời gian đến nhỏ hơn <= đưa ra danh sách này + trường hợp mà có thời gian hoàng thành bé hơn thời gian đến
                 * dùng 1 vòng for để kiểm tra và check
                 * */
                for (int j = i; j < p.size(); j++) {
                    if (p.get(j).getArrivalTime() <= p.get(index).getCompletionTime()) {
                        check = true;
                        break;
                    }
                }
                // nếu dúng đưa ra danh sách các tiến trình có thời gian đến <= thời gian hoàn thành của tiến trình trước.
                if (check) {
                    // đưa ra danh sách.

                    for (int j = i; j < p.size(); j++) {
                        if (p.get(j).getArrivalTime() <= p.get(index).getCompletionTime()) {
                            if (p.get(j).getBurstTime() < p.get(i).getBurstTime()) {
                                Collections.swap(p, i, j);
                            }
                        }
                    }
                    p.get(i).setStartTime(p.get(index).getCompletionTime());
                    index = i;
                } else {
                    System.out.println("idle: " + p.get(index).getCompletionTime() + "->" + p.get(i).getArrivalTime());
                    p.get(i).setStartTime(p.get(i).getArrivalTime());
                    index = i;
                }
            }
            p.get(i).setWaitingTime(p.get(i).getStartTime() - p.get(i).getArrivalTime());
            p.get(i).setResponse_time(p.get(i).getStartTime() - p.get(i).getArrivalTime());
            p.get(i).setCompletionTime(p.get(i).getStartTime() + p.get(i).getBurstTime());
            p.get(i).setTurnaroundTime(p.get(i).getCompletionTime() - p.get(i).getArrivalTime());
            System.out.println("P" + p.get(i).getProcessId() + ": " + p.get(i).getStartTime() + "->" + p.get(i).getCompletionTime());
        }
    }

    /**
     * thuật toán sjf cho phép dừng
     * đếm số lần chuyển trạng thái
     *
     * @param p là 1 list các tiến trình đầu vào cần xử lý
     * @author: by Nguyễn Quang Thành tham khảo thuật toán tại đây <a href="https://www.studytonight.com/operating-system/shortest-remaining-time-first-scheduling-algorithm">...</a>
     */
    public static void srtf(List<Process> p) {
        /**
         * tại thời gian t mình lấy ra tiến trình thỏa mãn điều kiện có thời gian bùng nổ nhỏ nhất.
         * */
        boolean[] is_complete = new boolean[p.size()];
        int complete = 0;
        int x = p.size();
        int current_time = 0;
        int index;
        int prev_index = -2;
        int cnt = 0;
        // chọn 1 biến là thời gian bùng nổ min.
        while (complete != x) {
            // index là tiến trình thỏa mãn ở thời điểm hiện tại
            index = -1;
            int mn_max = 1000000;
            // tại thời điểm đang xét lấy các tiến trình chưa hoàn thành và có mặt ở hàng đơi, sau đấy chọn ra tiến trình có thời gian bùng nổ nhỏ nhất.
            for (int i = 0; i < x; i++) {
                if (p.get(i).getArrivalTime() <= current_time && !is_complete[i]) {
                    if (p.get(i).getRemaining_time() < mn_max) {
                        mn_max = p.get(i).getRemaining_time();
                        index = i;
                    } else if (p.get(i).getRemaining_time() == mn_max) {
                        if (p.get(i).getArrivalTime() < p.get(index).getArrivalTime()) {
                            mn_max = p.get(i).getRemaining_time();
                            index = i;
                        }
                    }
                }
            }
            if (prev_index != index && index != 1) {
                cnt++;
            }
            prev_index = index;
            // sau quá trình trên mình đã chọn được tiến trình trên hàng đợi có thời gian bùng nổ bé nhất
            // bây giờ xử lý tiến trình được chọn này
            if (index != -1) {
                // nếu tiến trình có thời gian còn lại chính là thời gian bùng nổ thì hiện tại chính là thời gian bắt đầu của tiến trình đấy.
                if (p.get(index).getRemaining_time() == p.get(index).getBurstTime()) {
                    p.get(index).setStartTime(current_time);
                }
                p.get(index).setRemaining_time(p.get(index).getRemaining_time() - 1);
                current_time++;
                // nếu tiến trình có thời gian còn lại bằng 0 chính là lúc tiến trình đó xong nhiệm vụ
                if (p.get(index).getRemaining_time() == 0) {
                    // tại thời điểm này chính là lúc tiến trình hoàn thành. bắt đầu tính các thuộc tính của nó.
                    p.get(index).setCompletionTime(current_time);
                    p.get(index).setResponse_time(p.get(index).getStartTime() - p.get(index).getArrivalTime());
                    p.get(index).setTurnaroundTime(p.get(index).getCompletionTime() - p.get(index).getArrivalTime());
                    p.get(index).setWaitingTime(p.get(index).getTurnaroundTime() - p.get(index).getBurstTime());
                    is_complete[index] = true;
                    complete++;
                    System.out.println("P" + p.get(index).getProcessId() + ": " + p.get(index).getStartTime() + "->" + p.get(index).getCompletionTime());
                }
            } else {
                // ở đây chính là lúc tiến trình đang nhàn rỗi, không có tiến trình tại thời điểm xét được lấy ra;
                System.out.println("idel tại: " + current_time);
                current_time++;
            }
        }
        System.out.println("số lần chuyển trạng thái: " + cnt);
    }

    public static void prio(List<Process> p) {
        System.out.println("thứ tự ưu tiên là thấp hay cao [0/1]");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sort_prio(p, n);
        // tính start_time cái khó nhất//
        int index = 0;
        boolean check = false;
        for (int i = 0; i < p.size(); i++) {
            check = false;
            if (i == 0) {
                p.get(i).setStartTime(p.get(i).getArrivalTime());
            } else {
                /**
                 * làm sao để xử lý trường hợp này đúng nhất ???
                 * yêu/cầu hiển thị các quá trình có trên hàng đợi khi hoàng thành quá trình trước theo thứ tự, in ra màn hình.
                 * */
                /**
                 * trường hợp có process có thời gian đến nhỏ hơn <= đưa ra danh sách này + trường hợp mà có thời gian hoàng thành bé hơn thời gian đến
                 * dùng 1 vòng for để kiểm tra và check
                 * */
                for (int j = i; j < p.size(); j++) {
                    if (p.get(j).getArrivalTime() <= p.get(index).getCompletionTime()) {
                        check = true;
                        break;
                    }
                }
                // nếu dúng đưa ra danh sách các tiến trình có thời gian đến <= thời gian hoàn thành của tiến trình trước.
                if (check) {
                    // đưa ra danh sách.
                    for (int j = i; j < p.size(); j++) {
                        if (p.get(j).getArrivalTime() <= p.get(index).getCompletionTime()) {
                            if (n == 0) {
                                if (p.get(j).getPriority() < p.get(i).getPriority()) {
                                    Collections.swap(p, i, j);
                                }
                            } else if (n == 1) {
                                if (p.get(j).getPriority() > p.get(i).getPriority()) {
                                    Collections.swap(p, i, j);
                                }
                            }
                        }
                    }
                    p.get(i).setStartTime(p.get(index).getCompletionTime());
                    index = i;
                } else {
                    System.out.println("idle: " + p.get(index).getCompletionTime() + "->" + p.get(i).getArrivalTime());
                    p.get(i).setStartTime(p.get(i).getArrivalTime());
                    index = i;
                }
            }
            p.get(i).setWaitingTime(p.get(i).getStartTime() - p.get(i).getArrivalTime());
            p.get(i).setResponse_time(p.get(i).getStartTime() - p.get(i).getArrivalTime());
            p.get(i).setCompletionTime(p.get(i).getStartTime() + p.get(i).getBurstTime());
            p.get(i).setTurnaroundTime(p.get(i).getCompletionTime() - p.get(i).getArrivalTime());
            System.out.println("P" + p.get(i).getProcessId() + ": " + p.get(i).getStartTime() + "->" + p.get(i).getCompletionTime());
        }
    }

    public static void pre_prio(List<Process> p) {
        /**
         * tại thời gian t mình lấy ra tiến trình thỏa mãn điều kiện có thời gian bùng nổ nhỏ nhất.
         * */
        System.out.println("thứ tự ưu tiên là thấp hay cao [0/1]");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        boolean[] is_complete = new boolean[p.size()];
        int complete = 0;
        int x = p.size();
        int current_time = 0;
        int index;
        int prev_index = -2;
        int cnt = 0;
        // chọn 1 biến là thời gian bùng nổ min.
        while (complete != x) {
            // index là tiến trình thỏa mãn ở thời điểm hiện tại
            index = -1;
            int mn_max = 1000000;
            int mn_min = -1000000;
            // tại thời điểm đang xét lấy các tiến trình chưa hoàn thành và có mặt ở hàng đơi, sau đấy chọn ra tiến trình có thời gian bùng nổ nhỏ nhất.
            for (int i = 0; i < x; i++) {
                if (p.get(i).getArrivalTime() <= current_time && !is_complete[i]) {
                    if (n == 0) {
                        if (p.get(i).getPriority() < mn_max) {
                            mn_max = p.get(i).getPriority();
                            index = i;
                        } else if (p.get(i).getPriority() == mn_max) {
                            if (p.get(i).getArrivalTime() < p.get(index).getArrivalTime()) {
                                mn_max = p.get(i).getPriority();
                                index = i;
                            }
                        }
                    } else if (n == 1) {
                        if (p.get(i).getPriority() > mn_min) {
                            mn_min = p.get(i).getPriority();
                            index = i;
                        } else if (p.get(i).getPriority() == mn_min) {
                            if (p.get(i).getArrivalTime() < p.get(index).getArrivalTime()) {
                                mn_min = p.get(i).getPriority();
                                index = i;
                            }
                        }
                    }
                }
            }
            if (prev_index != index && index != 1) {
                cnt++;
            }
            prev_index = index;
            // sau quá trình trên mình đã chọn được tiến trình trên hàng đợi có thời gian bùng nổ bé nhất
            // bây giờ xử lý tiến trình được chọn này
            if (index != -1) {
                // nếu tiến trình có thời gian còn lại chính là thời gian bùng nổ thì hiện tại chính là thời gian bắt đầu của tiến trình đấy.
                if (p.get(index).getRemaining_time() == p.get(index).getBurstTime()) {
                    p.get(index).setStartTime(current_time);
                }
                p.get(index).setRemaining_time(p.get(index).getRemaining_time() - 1);
                current_time++;
                // nếu tiến trình có thời gian còn lại bằng 0 chính là lúc tiến trình đó xong nhiệm vụ
                if (p.get(index).getRemaining_time() == 0) {
                    // tại thời điểm này chính là lúc tiến trình hoàn thành. bắt đầu tính các thuộc tính của nó.
                    p.get(index).setCompletionTime(current_time);
                    p.get(index).setResponse_time(p.get(index).getStartTime() - p.get(index).getArrivalTime());
                    p.get(index).setTurnaroundTime(p.get(index).getCompletionTime() - p.get(index).getArrivalTime());
                    p.get(index).setWaitingTime(p.get(index).getTurnaroundTime() - p.get(index).getBurstTime());
                    is_complete[index] = true;
                    complete++;
                    System.out.println("P" + p.get(index).getProcessId() + ": " + p.get(index).getStartTime() + "->" + p.get(index).getCompletionTime());

                }
            } else {
                // ở đây chính là lúc tiến trình đang nhàn rỗi, không có tiến trình tại thời điểm xét được lấy ra;
                System.out.println("idel tại: " + current_time);
                current_time++;
            }
        }
        System.out.println("số lần chuyển trạng thái: " + cnt);
    }


    /**
     * round_robin
     */
    public static void round_robin(List<Process> p) {
        System.out.println("Hãy nhập qq đi: ");
        Scanner sc = new Scanner(System.in);
        int qq = sc.nextInt();
        p.sort(comparator_at);
        //1. khởi tạo hàng đợi gồm các phần từ theo thứ tự thời gian đến
        Queue<Process> queue = new ArrayDeque<>();
        boolean[] is_start = new boolean[p.size()];
        boolean[] is_add = new boolean[p.size()];
        int x = p.size();
        int current_time = p.get(0).getArrivalTime();
        queue.add(p.get(0));
        is_add[0] = true;
        while (!queue.isEmpty()) {
            // lấy ra khỏi hàng đợi và cập nhật lại
            Process temp = queue.poll();
            for (int i = 0; i < x; i++) {
                // lấy ra tiến trình trong List p và xử lý nó.
                if (p.get(i).getProcessId() == temp.getProcessId()) {
                    boolean check = false;
                    // kiểm tra xem có tiến trình nào được thêm ở hàng đợi không
                    if (!is_start[i]) {
                        is_start[i] = true;
                        p.get(i).setStartTime(current_time);
                    }
                    if (p.get(i).getRemaining_time() <= qq) {
                        // trạng thái đã xong.
                        // cập nhật thời gian
                        System.out.println("P" + p.get(i).getProcessId() + ": " + current_time + " -> " + (current_time + p.get(i).getRemaining_time()));
                        current_time += p.get(i).getRemaining_time();
                        p.get(i).setCompletionTime(current_time);
                        p.get(i).setResponse_time(p.get(i).getStartTime() - p.get(i).getArrivalTime());
                        p.get(i).setTurnaroundTime(p.get(i).getCompletionTime() - p.get(i).getArrivalTime());
                        p.get(i).setWaitingTime(p.get(i).getTurnaroundTime() - p.get(i).getBurstTime());
                        for (int j = 0; j < x; j++) {
                            if (p.get(j).getArrivalTime() <= current_time && !is_add[j]) {
                                queue.add(p.get(j));
                                is_add[j] = true;
                            }

                        }
                        p.get(i).setRemaining_time(0);
                        // nếu không có tiến trình nào được thêm thì phải làm sao ? trường hợp này gần giống với trường hợp cuối. có sự khác biệt là nếu ở cuối thì tất cả các tiến trình được thêm rồi
                        // tăng tiếp current_time đến thời điểm có tiến trình tiếp theo đến
                        for (int k = 0; k < x; k++) {
                            if (!is_add[k]) {
                                check = true;
                                break;
                            }
                        }
                        if (queue.isEmpty() && check) {
                            System.out.println("idle: " + current_time + "->" + p.get(i + 1).getArrivalTime());
                            current_time = p.get(i + 1).getArrivalTime();
                            for (int j = 0; j < x; j++) {
                                if (p.get(j).getArrivalTime() <= current_time && !is_add[j]) {
                                    queue.add(p.get(j));
                                    is_add[j] = true;
                                }

                            }
                        }

                    } else if (p.get(i).getRemaining_time() > qq) {
                        // tăng thời gian thành thời gian lượng tử, cập nhật hàng đợi, thêm vào cuối hàng đơi,
                        System.out.println("P" + p.get(i).getProcessId() + ": " + current_time + " -> " + (current_time + qq));
                        current_time += qq;
                        for (int j = 0; j < x; j++) {
                            if (p.get(j).getArrivalTime() <= current_time & !is_add[j]) {
                                queue.add(p.get(j));
                                is_add[j] = true;
                            }
                        }
                        p.get(i).setRemaining_time(p.get(i).getRemaining_time() - qq);
                        queue.add(p.get(i));
                    }
                }
            }
        }

    }

    /**
     * trả về các thông số trung bình
     */
    public static void tb(List<Process> p) {
        int wt = 0;
        int tat = 0;
        int res = 0;
        int complete_max = p.get(0).getCompletionTime();
        int arrival_min = p.get(0).getArrivalTime();
        for (Process process : p) {
            complete_max = max(complete_max, process.getCompletionTime());
            arrival_min = min(arrival_min, process.getArrivalTime());
            wt += process.getWaitingTime();
            tat += process.getTurnaroundTime();
            res += process.getResponse_time();
        }
        double wt_tb = 1.0 * wt / p.size();
        double tat_tb = 1.0 * tat / p.size();
        double res_tb = 1.0 * res / p.size();
        System.out.println("average wt: " + wt_tb);
        System.out.println("average tat: " + tat_tb);
        System.out.println("average res: " + res_tb);
        System.out.println("throughput: " + (double) p.size() / (complete_max - arrival_min));
    }
}

