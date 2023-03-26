#include <unistd.h>
#include <iostream>

using namespace std;

int main() {
    pid_t pid = fork(); // tạo một tiến trình con
    if (pid == 0) { // tiến trình con
        sleep(10); // giữ tiến trình con ở trạng thái Orphan trong 10 giây
        cout << "Child process (PID: " << getpid() << ", PPID: " << getppid() << ") is now an orphan process" << endl;
    } else { // tiến trình cha
        cout << "Parent process (PID: " << getpid() << ") created child process (PID: " << pid << ")" << endl;
        sleep(11); // ngủ 11 giây để đảm bảo tiến trình con đã trở thành Orphan
    }
    return 0;
}
