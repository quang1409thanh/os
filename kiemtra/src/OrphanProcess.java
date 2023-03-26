import java.io.IOException;

public class OrphanProcess {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("sleep", "10");
        Process child = pb.start();
        System.out.println("Child process created with PID: " + child.pid());
        Thread.sleep(5000);
        System.out.println("Parent process is terminating");
    }
}
