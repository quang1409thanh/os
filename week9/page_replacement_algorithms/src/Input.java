import java.util.ArrayList;
import java.util.List;

public class Input {
    private List<Integer> disk_queue = new ArrayList<>();
    private int count;

    public Input() {

    }

    public Input(List<Integer> disk_queue, int count) {
        this.disk_queue = disk_queue;
        this.count = count;
    }

    public void setDisk_queue(List<Integer> disk_queue) {
        this.disk_queue = disk_queue;
    }

    public List<Integer> getDisk_queue() {
        return disk_queue;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        StringBuilder s = new StringBuilder(count + "\n");
        for (int i : disk_queue) {
            s.append(i).append(" ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        return s.toString();
    }

}
