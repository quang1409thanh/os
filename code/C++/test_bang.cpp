#include<iostream>
#include<algorithm>
#include<queue>

using namespace std;

struct Process {
    int id;
    int bt; // burst time - thời gian xử lý
    int at; // arrival time - thời gian đến
};

bool comparison(Process a, Process b) {
    return (a.at < b.at);
}

void findWaitingTime(Process proc[], int n, int wt[]) {
    wt[0] = 0;
    for (int i = 1; i < n; i++)
        wt[i] = proc[i - 1].bt + wt[i - 1];
}

void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {
    for (int i = 0; i < n; i++)
        tat[i] = proc[i].bt + wt[i];
}

void findAverageTime(Process proc[], int n) {
    int wt[n], tat[n], total_wt = 0, total_tat = 0;

    findWaitingTime(proc, n, wt);
    findTurnAroundTime(proc, n, wt, tat);

    cout << "Processes  "<< " Burst time  "<< " Arrival time  "<< " Waiting time  " << " Turn around time\n";

    for (int i = 0; i < n; i++) {
        total_wt = total_wt + wt[i];
        total_tat = total_tat + tat[i];
        cout << "   " << proc[i].id << "\t\t" << proc[i].bt << "\t\t" << proc[i].at << "\t\t" << wt[i] << "\t\t" << tat[i] << endl;
    }

    cout << "\nAverage waiting time = " << (float)total_wt / (float)n;
    cout << "\nAverage turn around time = " << (float)total_tat / (float)n;
}

void FCFS(Process proc[], int n) {
    sort(proc, proc + n, comparison);

    // cout << "Order of execution: ";
    // for (int i = 0; i < n; i++)
    //     cout << proc[i].id << " ";

    findAverageTime(proc, n);
}

int main() {
    Process proc[] = { {1, 6, 1}, {2, 8, 1}, {3, 7, 2}, {4, 3, 3} };
    int n = sizeof(proc) / sizeof(proc[0]);
    FCFS(proc, n);
    return 0;
}
