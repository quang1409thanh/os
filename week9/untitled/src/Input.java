import java.util.ArrayList;
import java.util.List;

public class Input {
    private List<Integer> disk_queue = new ArrayList<>();

    // bắt đầu trục rãnh;
    private int _min;
    // kết thúc trục rãnh
    private int _max;
    private int start;

    public Input(List<Integer> disk_queue, int start, int _min, int _max) {
        this.disk_queue = disk_queue;
        this.start = start;
        this._min = _min;
        this._max = _max;
    }

    public void setDisk_queue(List<Integer> disk_queue) {
        this.disk_queue = disk_queue;
    }

    public List<Integer> getDisk_queue() {
        return disk_queue;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public int get_max() {
        return _max;
    }

    public void set_max(int _max) {
        this._max = _max;
    }

    public int get_min() {
        return _min;
    }

    public void set_min(int _min) {
        this._min = _min;
    }

    @Override
    public String toString() {
        String rs = "";
        rs += "start " + start + "\n";
        rs += disk_queue;
        return rs;
    }
}
