public class process_banker extends process {
    public resources max = new resources();
    public resources need = new resources();

    public process_banker(resources allocation, resources max) {
        this.allocation = allocation;
        this.max = max;
        this.need = banker_fun.sub(this.max, this.allocation);
    }

    @Override
    public String toString() {
        String rs = "";
        rs += "allocation:\t " + allocation.toString();
        rs += "\tmax:\t " + max.toString();
        rs += "\tneed:\t " + need.toString() + "\n";
        return rs;
    }
}
