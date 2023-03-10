import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Process> p = fun_schedule.myInput("src/fcfs.txt");
        fun_schedule.pre_prio(p);
        System.out.println("kết quả là: ");
        for (Process process : p) {
            System.out.println(process.toString());
        }
        fun_schedule.tb(p);
    }
}