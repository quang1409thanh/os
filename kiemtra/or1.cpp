#include <iostream>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork();
    
    if (pid == 0) { // Process is child
        std::cout << "Child process is running" << std::endl;
        sleep(15); // Child process becomes orphan after 15 seconds
        std::cout << "Child process is now orphan" << std::endl;
    } else if (pid > 0) { // Process is parent
        std::cout << "Parent process is running" << std::endl;
        wait(NULL); // Wait for child process to finish
    } else { // Error
        std::cerr << "Fork failed" << std::endl;
        return 1;
    }
    
    return 0;
}
