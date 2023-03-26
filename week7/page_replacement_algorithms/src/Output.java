import java.util.ArrayList;
import java.util.List;

public class Output {
    private List<Integer> reference_string = new ArrayList<>();
    private List<Character> faults = new ArrayList<>();
    private List<Character> removed = new ArrayList<>();

    private int page_Faults;
    private int page_Replacements;

    public Output() {
    }

    public Output(List<Integer> reference_string, List<Character> faults, List<Character> removed, int page_Faults, int page_Replacements) {
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

    public List<Character> getRemoved() {
        return removed;
    }

    public void setRemoved(List<Character> removed) {
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
        for (char i : removed) {
            sb.append(String.format("%-4s", String.valueOf(i)));
        }
        sb.append("\n");
        sb.append("Page Faults: ").append(page_Faults).append("\n");
        sb.append("Page Replacements: ").append(page_Replacements);
        return sb.toString();
    }
}
