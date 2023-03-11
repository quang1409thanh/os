
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Process> p = fun_schedule.myInput("src/fcfs.txt");
        fun_schedule.srtf(p);
        p.sort(Comparator.comparing(Process::getProcessId));
        for (Process process : p) {
            System.out.println(process.toString());
        }
        fun_schedule.tb(p);
    }
}