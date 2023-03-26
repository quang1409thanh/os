#include <iostream>
#include <unistd.h>
#include <sys/types.h>

int main() {
    pid_t pid;
    pid = fork();
    if (pid == 0) {
        // Đây là tiến trình con
        sleep(10); // Tiến trình con dừng lại trong 10s
        std::cout << "Child process with PID " << getpid() << std::endl;
        std::cout << "Parent process with PID " << getppid() << std::endl;
    } else if (pid > 0) {
        // Đây là tiến trình cha
        std::cout << "Parent process with PID " << getpid() << std::endl;
        std::cout << "Child process with PID " << pid << std::endl;
        // Đợi tiến trình con kết thúc
        sleep(12); // Đợi 12s để đảm bảo tiến trình con đã trở thành tiến trình mồ côi
        std::cout << "Orphan process with PID " << pid << std::endl;
    } else {
        // Fork không thành công
        std::cerr << "Fork failed." << std::endl;
        return 1;
    }
    return 0;
}
