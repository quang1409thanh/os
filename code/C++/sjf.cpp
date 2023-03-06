#include <iostream>
#include <iomanip>
#include <vector>
#include <fstream>
#include <queue>
#include <algorithm>
using namespace std;
ofstream fcout("out.txt");
int length(int x)
{
    int count = 0;
    if (x < 0)
    {
        count = 1;
        x = -x;
    }
    while (x > 0)
    {
        x /= 10;
        count++;
    }
    return count;
}
struct process
{
    int id;
    int arrival_time;
    int burst_time;
    int priority;
    int start_time;
    int response_time;
    int waiting_time;
    int turnaround_time;
    process(int x0, int x1, int x2, int x3)
    {
        id = x0;
        arrival_time = x1;
        burst_time = x2;
        priority = x3;
    }
    void print()
    {
        fcout << "|P";
        fcout << left << setw(14) << id
              << left << setw(16) << arrival_time
              << left << setw(16) << burst_time
              << left << setw(16) << priority
              << left << setw(16) << start_time
              << left << setw(16) << response_time
              << left << setw(16) << waiting_time
              << turnaround_time;
        for (int i = 0; i < 15 - length(turnaround_time); i++)
        {
            fcout << " ";
        }
        fcout << "|" << endl;
    }
};
struct schedule
{
    int count = 0;
    vector<process> prc;
    schedule(){};
    ~schedule() {}
    void add(process x)
    {
        count++;
        prc.push_back(x);
    }
};
bool write(process x1, process x2)
{
    return x1.id < x2.id;
}
schedule readfile(string file)
{
    schedule rs = schedule();
    fstream fcin(file);
    string line;
    // getline(fcin,line);
    while (getline(fcin, line))
    {
        stringstream ss(line);
        int n;
        vector<int> num;
        while (ss >> n)
        {
            num.push_back(n);
        }
        rs.add(process(num[0], num[1], num[2], num[3]));
    }
    return rs;
}
void write_file(schedule sch)
{
    sort(sch.prc.begin(), sch.prc.end(), write);
    int res = 0, wait = 0, turn = 0;
    for (int i = 0; i < sch.count; i++)
    {
        res += sch.prc[i].response_time;
        wait += sch.prc[i].waiting_time;
        turn += sch.prc[i].turnaround_time;
    }
    float response_average = 1.0 * res / sch.count;
    float waiting_average = 1.0 * wait / sch.count;
    float turnaround_average = 1.0 * turn / sch.count;
    fcout << "================================================================================================================================" << endl;
    fcout << "|                                              CPU scheduling                                                                  |" << endl;
    fcout << "================================================================================================================================" << endl;
    fcout << left << setw(16) << "|process"
          << left << setw(16) << "arrival_time"
          << left << setw(16) << "burst_time"
          << left << setw(16) << "priority"
          << left << setw(16) << "start_time"
          << left << setw(16) << "response_time"
          << left << setw(16) << "waiting_time"
          << left << setw(16) << "turnaround_time|" << endl;
    for (int i = 0; i < sch.count; i++)
    {
        sch.prc[i].print();
    }
    fcout << left << setw(16) << "|average";
    fcout << left << setw(64) << " ";
    fcout << left << setw(16) << response_average
          << left << setw(16) << waiting_average
          << turnaround_average;
    for (int i = 0; i < 15 - length(turnaround_average); i++)
    {
        fcout << " ";
    }
    fcout << "|" << endl;
    fcout << "================================================================================================================================" << endl;
}

bool sort_sjf(process x1, process x2)
{
    return x1.burst_time > x2.burst_time;
}
bool sort_at(process x1, process x2)
{
    return x1.arrival_time < x2.arrival_time;
}
void sjf(schedule &sch)
{
    // xử lý xem trong hàng đợi có process nào không, nếu có thì lấy tiến trình đó lên cpu đã rồi tính tiếp.
    sort(sch.prc.begin(),sch.prc.end(),sort_at);
    // sau bước này sẽ đưa tiến trình có mặt trên hàng đợi đầu tiên đến cpu.
    
    for (int i = 0; i < sch.count; i++)
    {
        if (i == 0)
        {
            sch.prc[i].start_time = sch.prc[i].arrival_time;
        }
        else
        {
            if (sch.prc[i].arrival_time < sch.prc[i - 1].start_time + sch.prc[i - 1].burst_time)
            {
                sch.prc[i].start_time = sch.prc[i - 1].start_time + sch.prc[i - 1].burst_time;
            }
            else
                sch.prc[i].start_time = sch.prc[i].arrival_time;
        }
        sch.prc[i].response_time = sch.prc[i].start_time - sch.prc[i].arrival_time;
        sch.prc[i].waiting_time = sch.prc[i].start_time - sch.prc[i].arrival_time;
        sch.prc[i].turnaround_time = sch.prc[i].start_time + sch.prc[i].burst_time - sch.prc[i].arrival_time;
    }
}

int main()
{
    // process x = process(0, 1, 2, 3);
    schedule fcfs_test = readfile("in.txt");
    sjf(fcfs_test);
    write_file(fcfs_test);
}