public class Process_deadlock extends Process {
    public resources request = new resources();

    public Process_deadlock(resources allocation, resources request) {
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
