public class Process {
    private int processId;
    private int arrivalTime;
    private int burstTime;
    private int priority;

    private int startTime;
    private int completionTime;
    private int turnaroundTime;
    private int waitingTime;
    private int response_time;

    private int remaining_time;


    // constructor
    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    // getters and setters
    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getResponse_time() {
        return response_time;
    }

    public void setResponse_time(int response_time) {
        this.response_time = response_time;
    }

    public int getRemaining_time() {
        return remaining_time;
    }

    public void setRemaining_time(int remaining_time) {
        this.remaining_time = remaining_time;
    }

    @Override
    public String toString() {
        return String.format("Process ID: %d | Arrival Time: %d | Burst Time: %d | Priority: %d | Start Time: %d | Completion Time: %d | Turnaround Time: %d | Waiting Time: %d | Response Time: %d | x: %d " ,
                processId, arrivalTime, burstTime, priority, startTime, completionTime, turnaroundTime, waitingTime, response_time, remaining_time);
    }
}
