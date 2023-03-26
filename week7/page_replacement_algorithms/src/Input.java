import java.util.ArrayList;
import java.util.List;

public class Input {
    private List<Integer> reference_string = new ArrayList<>();
    private int page_frames;

    public Input() {

    }

    public Input(List<Integer> reference_string, int page_frames) {
        this.reference_string = reference_string;
        this.page_frames = page_frames;
    }

    public void setReference_string(List<Integer> reference_string) {
        this.reference_string = reference_string;
    }

    public List<Integer> getReference_string() {
        return reference_string;
    }

    public void setPage_frames(int page_frames) {
        this.page_frames = page_frames;
    }

    public int getPage_frames() {
        return page_frames;
    }

    public String toString() {
        StringBuilder s = new StringBuilder(page_frames + "\n");
        for (int i : reference_string) {
            s.append(i).append(" ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        return s.toString();
    }

}
