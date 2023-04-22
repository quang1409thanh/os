import java.util.ArrayList;
import java.util.List;

public class Output {
    private List<Integer> disk_out = new ArrayList<>();
    private int total;

    public Output() {
    }

    public Output(List<Integer> disk_out, int total) {
        this.disk_out = disk_out;
        this.total = total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setDisk_out(List<Integer> disk_out) {
        this.disk_out = disk_out;
    }

    public List<Integer> getDisk_out() {
        return disk_out;
    }

    @Override
    public String toString() {
        StringBuilder rs = new StringBuilder("result: " + total + " \nthat is: ");
        for (int i : disk_out) {
            rs.append(i).append(" -> ");
        }
        rs.delete(rs.length() - 4, rs.length());
        return rs.toString();
    }
}

