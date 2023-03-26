import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class func_ {

    public static Output fifo(Input input) {
        Queue<Integer> frames = new ArrayDeque<>(input.getPage_frames());
        int page_Faults = 0;
        int page_Replacements = 0;
        Output output = new Output();
        output.setReference_string(input.getReference_string());
        List<Character> faults = new ArrayList<>();
        List<Character> removes = new ArrayList<>();
        for (int i = 0; i < input.getReference_string().size(); i++) {
            if (frames.contains(input.getReference_string().get(i))) {
                faults.add('-');
                removes.add('-');
            } else {
                page_Faults++;
                faults.add('F');
                // ***
                if (frames.size() == input.getPage_frames()) {
                    page_Replacements++;
                    // thay the phan tu fifo
                    removes.add((char) (frames.remove() + '0'));
                    frames.add(input.getReference_string().get(i));
                } else {
                    frames.add(input.getReference_string().get(i));
                    removes.add('-');
                }
            }

        }
        output.setFaults(faults);
        output.setRemoved(removes);
        output.setPage_Faults(page_Faults);
        output.setPage_Replacements(page_Replacements);
        return output;
    }

    public static int find_optimal(List<Integer> pages, List<Integer> frames, int currentIndex) {

        int optimalIndex = -1;
        int farthestIndex = 0;
        for (int i = 0; i < frames.size(); i++) {
            int frame = frames.get(i);
            boolean found = false;
            for (int j = currentIndex + 1; j < pages.size(); j++) {
                if (pages.get(j) == frame) {
                    found = true;
                    if (j > farthestIndex) {
                        farthestIndex = j;
                        optimalIndex = i;
                    }
                    break;
                }
            }
            // nếu không thấy trả về phần tử không tìm thấy đầu tiên khi duyệt frame/ nếu yêu cầu fifo sửa lại chỗ này.
            if (!found) {
                return i;
            }
        }
        return optimalIndex;
    }

    public static Output optimal(Input input) {
        List<Integer> frames = new ArrayList<>(input.getPage_frames());
        int page_Faults = 0;
        int page_Replacements = 0;
        Output output = new Output();
        output.setReference_string(input.getReference_string());
        List<Character> faults = new ArrayList<>();
        List<Character> removes = new ArrayList<>();
        for (int i = 0; i < input.getReference_string().size(); i++) {
            if (frames.contains(input.getReference_string().get(i))) {
                faults.add('-');
                removes.add('-');
            } else {
                page_Faults++;
                faults.add('F');
                // quan trong thay the cai nao
                if (frames.size() == input.getPage_frames()) {
                    page_Replacements++;
                    // thay the phan tu optimal
                    int j = find_optimal(input.getReference_string(), frames, i);
                    removes.add((char) (frames.get(j) + '0'));
                    frames.set(j, input.getReference_string().get(i));
                } else {
                    frames.add(input.getReference_string().get(i));
                    removes.add('-');
                }
            }

        }
        output.setFaults(faults);
        output.setRemoved(removes);
        output.setPage_Faults(page_Faults);
        output.setPage_Replacements(page_Replacements);
        return output;
    }

    public static int find_LRU(List<Integer> pages, List<Integer> frames, int currentIndex) {

        int optimalIndex = -1;
        int farthestIndex = pages.size();
        for (int i = 0; i < frames.size(); i++) {
            int frame = frames.get(i);
            boolean found = false;
            for (int j = currentIndex - 1; j >= 0; j--) {
                if (pages.get(j) == frame) {
                    found = true;
                    if (j < farthestIndex) {
                        farthestIndex = j;
                        optimalIndex = i;
                    }
                    break;
                }
            }
            // nếu không thấy trả về phần tử không tìm thấy đầu tiên khi duyệt frame/ nếu yêu cầu fifo sửa lại chỗ này.
            if (!found) {
                return i;
            }
        }
        return optimalIndex;
    }

    public static Output lru(Input input) {
        List<Integer> frames = new ArrayList<>(input.getPage_frames());
        int page_Faults = 0;
        int page_Replacements = 0;
        Output output = new Output();
        output.setReference_string(input.getReference_string());
        List<Character> faults = new ArrayList<>();
        List<Character> removes = new ArrayList<>();
        for (int i = 0; i < input.getReference_string().size(); i++) {
            if (frames.contains(input.getReference_string().get(i))) {
                faults.add('-');
                removes.add('-');
            } else {
                page_Faults++;
                faults.add('F');
                if (frames.size() == input.getPage_frames()) {
                    page_Replacements++;
                    // thay the phan tu lru
                    int j = find_LRU(input.getReference_string(), frames, i);
                    removes.add((char) (frames.get(j) + '0'));
                    frames.set(j, input.getReference_string().get(i));
                } else {
                    frames.add(input.getReference_string().get(i));
                    removes.add('-');
                }
            }

        }
        output.setFaults(faults);
        output.setRemoved(removes);
        output.setPage_Faults(page_Faults);
        output.setPage_Replacements(page_Replacements);
        return output;
    }

    public static int find_MRU(List<Integer> pages, List<Integer> frames, int currentIndex) {

        int optimalIndex = -1;
        int farthestIndex = 0;
        for (int i = 0; i < frames.size(); i++) {
            int frame = frames.get(i);
            boolean found = false;
            for (int j = currentIndex - 1; j >= 0; j--) {
                if (pages.get(j) == frame) {
                    found = true;
                    if (j > farthestIndex) {
                        farthestIndex = j;
                        optimalIndex = i;
                    }
                    break;
                }
            }
            // nếu không thấy trả về phần tử không tìm thấy đầu tiên khi duyệt frame/ nếu yêu cầu fifo sửa lại chỗ này.
            if (!found) {
                return i;
            }
        }
        return optimalIndex;
    }

    public static Output mru(Input input) {
        List<Integer> frames = new ArrayList<>(input.getPage_frames());
        int page_Faults = 0;
        int page_Replacements = 0;
        Output output = new Output();
        output.setReference_string(input.getReference_string());
        List<Character> faults = new ArrayList<>();
        List<Character> removes = new ArrayList<>();
        for (int i = 0; i < input.getReference_string().size(); i++) {
            if (frames.contains(input.getReference_string().get(i))) {
                faults.add('-');
                removes.add('-');
            } else {
                page_Faults++;
                faults.add('F');
                if (frames.size() == input.getPage_frames()) {
                    page_Replacements++;
                    // thay the phan tu lru
                    int j = find_MRU(input.getReference_string(), frames, i);
                    removes.add((char) (frames.get(j) + '0'));
                    frames.set(j, input.getReference_string().get(i));
                } else {
                    frames.add(input.getReference_string().get(i));
                    removes.add('-');
                }
            }

        }
        output.setFaults(faults);
        output.setRemoved(removes);
        output.setPage_Faults(page_Faults);
        output.setPage_Replacements(page_Replacements);
        return output;
    }

    public static int find_LFU(List<Integer> pages, List<Integer> frames, List<Integer> frames_index, int currentIndex) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int j = 0; j < currentIndex; j++) {
            if (frequency.containsKey(pages.get(j))) {
                frequency.put(pages.get(j), frequency.get(pages.get(j)) + 1);
            } else {
                frequency.put(pages.get(j), 1);
            }
        }
        int optimalIndex = -1;
        int frequency_min = pages.size();
        int fifo = pages.size();
        for (int i = 0; i < frames.size(); i++) {
            int frame = frames.get(i);
            if (frequency.get(frame) < frequency_min) {
                frequency_min = frequency.get(frame);
                optimalIndex = i;
                fifo = frames_index.get(i);
            } else if (frequency.get(frame) == frequency_min && frames_index.get(i) < fifo) { // trường hợp cùng tần suất lấy cái vào trước.
                frequency_min = frequency.get(frame);
                optimalIndex = i;
                fifo = frames_index.get(i);
            }
        }
        return optimalIndex;
    }

    public static Output lfu(Input input) {
        List<Integer> frames = new ArrayList<>(input.getPage_frames());
        List<Integer> frames_index = new ArrayList<>(input.getPage_frames());
        int page_Faults = 0;
        int page_Replacements = 0;
        Output output = new Output();
        output.setReference_string(input.getReference_string());
        List<Character> faults = new ArrayList<>();
        List<Character> removes = new ArrayList<>();
        for (int i = 0; i < input.getReference_string().size(); i++) {
            if (frames.contains(input.getReference_string().get(i))) {
                faults.add('-');
                removes.add('-');
            } else {
                page_Faults++;
                faults.add('F');
                if (frames.size() == input.getPage_frames()) {
                    page_Replacements++;
                    // thay the phan tu lfu
                    int j = find_LFU(input.getReference_string(), frames, frames_index, i);
                    removes.add((char) (frames.get(j) + '0'));
                    frames.set(j, input.getReference_string().get(i));
                    frames_index.set(j, i);
                } else {
                    frames.add(input.getReference_string().get(i));
                    frames_index.add(i);
                    removes.add('-');
                }
            }

        }
        output.setFaults(faults);
        output.setRemoved(removes);
        output.setPage_Faults(page_Faults);
        output.setPage_Replacements(page_Replacements);
        return output;
    }

    public static int find_MFU(List<Integer> pages, List<Integer> frames, List<Integer> frames_index, int currentIndex) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int j = 0; j < currentIndex; j++) {
            if (frequency.containsKey(pages.get(j))) {
                frequency.put(pages.get(j), frequency.get(pages.get(j)) + 1);
            } else {
                frequency.put(pages.get(j), 1);
            }
        }
        int optimalIndex = -1;
        int frequency_max = 0;
        int fifo = pages.size();
        for (int i = 0; i < frames.size(); i++) {
            int frame = frames.get(i);
            if (frequency.get(frame) > frequency_max) {
                frequency_max = frequency.get(frame);
                optimalIndex = i;
                fifo = frames_index.get(i);
            } else if (frequency.get(frame) == frequency_max && frames_index.get(i) < fifo) { // trường hợp cùng tần suất lấy cái vào trước.
                frequency_max = frequency.get(frame);
                optimalIndex = i;
                fifo = frames_index.get(i);
            }
        }
        return optimalIndex;
    }

    public static Output mfu(Input input) {
        List<Integer> frames = new ArrayList<>(input.getPage_frames());
        List<Integer> frames_index = new ArrayList<>(input.getPage_frames());
        int page_Faults = 0;
        int page_Replacements = 0;
        Output output = new Output();
        output.setReference_string(input.getReference_string());
        List<Character> faults = new ArrayList<>();
        List<Character> removes = new ArrayList<>();
        for (int i = 0; i < input.getReference_string().size(); i++) {
            if (frames.contains(input.getReference_string().get(i))) {
                faults.add('-');
                removes.add('-');
            } else {
                page_Faults++;
                faults.add('F');
                if (frames.size() == input.getPage_frames()) {
                    page_Replacements++;
                    // thay the phan tu lfu
                    int j = find_MFU(input.getReference_string(), frames, frames_index, i);
                    removes.add((char) (frames.get(j) + '0'));
                    frames.set(j, input.getReference_string().get(i));
                    frames_index.set(j, i);
                } else {
                    frames.add(input.getReference_string().get(i));
                    frames_index.add(i);
                    removes.add('-');
                }
            }

        }
        output.setFaults(faults);
        output.setRemoved(removes);
        output.setPage_Faults(page_Faults);
        output.setPage_Replacements(page_Replacements);
        return output;
    }
}
