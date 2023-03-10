import java.util.ArrayList;
import java.util.List;

public class Input {
    public int cntP;
    public List<Process> prc = new ArrayList<>();
    public resources available = new resources();

    public Input() {

    }

    public Input(int cntP, List<Process> prc, resources available) {
        this.cntP = cntP;
        this.prc = prc;
        this.available = available;
    }

    @Override
    public String toString() {
        StringBuilder rs = new StringBuilder();
        rs.append("số tiến trình: ");
        rs.append(cntP);
        rs.append("\n");
        for (int i = 0; i < cntP; i++) {
            rs.append(prc.get(i).toString());
        }
        rs.append("Work: ");
        rs.append(available.toString());
        return rs.toString();
    }

}
