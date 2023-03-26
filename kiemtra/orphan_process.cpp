#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <iostream>


using namespace std;


int main()
{
   pid_t pid = fork(); // tạo một tiến trình con


   if (pid == 0)
   {
       // tiến trình con
       std::cout << "Child process is running" << std::endl;
       sleep(10); // Child process becomes orphan after 10 seconds
       std::cout << "Child process is now orphan" << std::endl;
   }
   else if (pid > 0)
   {
       // tiến trình cha
       std::cout << "Parent process is running" << std::endl;
       wait(NULL); // Wait for child process to finish
   }
   else
   {
       std::cerr << "Fork failed" << std::endl;
       return 1;
   }


}
