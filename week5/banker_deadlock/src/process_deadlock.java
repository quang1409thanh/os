public class process_deadlock extends process {
    public resources request = new resources();

    public process_deadlock(resources allocation, resources request) {
        this.allocation = allocation;
        this.request = request;
    }

    @Override
    public String toString() {
        String rs = "";
        rs += "allocation:\t " + allocation.toString();
        rs += "\trequest:\t " + request.toString() + "\n";
        return rs;
    }
}
