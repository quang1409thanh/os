import java.util.ArrayList;
import java.util.List;

public class resources {
    // với mỗi tài nguyên sẽ lưu lại giá trị tài nguyên đó.
    private List<Integer> arr = new ArrayList<>();

    private int cnt;

    // số tài nguyên.
    public resources() {
    }

    public resources(List<Integer> arr, int cnt) {
        this.arr = arr;
        this.cnt = cnt;
    }

    public resources(resources rs) {
        this.arr = rs.getArr();
        this.cnt = rs.getCnt();
    }

    public List<Integer> getArr() {
        return arr;
    }

    public int getCnt() {
        return cnt;
    }

    public void setArr(List<Integer> arr) {
        this.arr = arr;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        StringBuilder rs = new StringBuilder("[ ");
        rs.append(cnt).append(": ");
        for (Integer integer : arr) {
            rs.append(integer).append(" ");
        }
        rs.append("]");
        return rs.toString();
    }
}

