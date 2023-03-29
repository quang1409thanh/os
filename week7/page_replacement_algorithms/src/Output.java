import java.util.ArrayList;
import java.util.List;

public class Output {
    private List<Integer> reference_string = new ArrayList<>();
    private List<Character> faults = new ArrayList<>();
    private List<String> removed = new ArrayList<>();
    List<List<Integer>> total_Frames = new ArrayList<List<Integer>>();


    private int page_Faults;
    private int page_Replacements;

    private String type;

    public Output() {
    }

    public Output(List<Integer> reference_string, List<Character> faults, List<String> removed, int page_Faults, int page_Replacements) {
        this.reference_string = reference_string;
        this.faults = faults;
        this.removed = removed;
        this.page_Faults = page_Faults;
        this.page_Replacements = page_Replacements;
    }

    public void setReference_string(List<Integer> reference_string) {
        this.reference_string = reference_string;
    }

    public List<Integer> getReference_string() {
        return reference_string;
    }

    public List<Character> getFaults() {
        return faults;
    }

    public void setFaults(List<Character> faults) {
        this.faults = faults;
    }

    public List<String> getRemoved() {
        return removed;
    }

    public void setRemoved(List<String> removed) {
        this.removed = removed;
    }

    public int getPage_Faults() {
        return page_Faults;
    }

    public void setPage_Faults(int page_Faults) {
        this.page_Faults = page_Faults;
    }

    public int getPage_Replacements() {
        return page_Replacements;
    }

    public void setPage_Replacements(int page_Replacements) {
        this.page_Replacements = page_Replacements;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<Integer>> getTotal_Frames() {
        return total_Frames;
    }

    public void setTotal_Frames(List<List<Integer>> total_Frames) {
        this.total_Frames = total_Frames;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-12s", "Reference:"));
        for (int i : reference_string) {
            sb.append(String.format("%-4d", i));

        }
        sb.append("\n");
        // In các hàng trong bảng
        sb.append(String.format("%-12s", "Faults:"));
        for (char i : faults) {
            sb.append(String.format("%-4s", String.valueOf(i)));
        }
        sb.append("\n");

        sb.append(String.format("%-12s", "Removed:"));
        for (String i : removed) {
            sb.append(String.format("%-4s", String.valueOf(i)));
        }
        sb.append("\n");
        sb.append("Page Faults: ").append(page_Faults).append("\n");
        sb.append("Page Replacements: ").append(page_Replacements);
        return sb.toString();
    }
}
