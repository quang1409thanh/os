#include <iostream>
#include <thread>
#include <mutex>
#include <chrono>
#include <semaphore.h>
using namespace std;

const int MAX_READERS = 5;
int read_count = 0;
sem_t wrt_sem, mutex_sem;
mutex read_count_mutex;

void writer_thread() {
    while (true) {
        sem_wait(&wrt_sem);
        // Perform writing
        cout << "Writer is writing" << endl;
        sem_post(&wrt_sem);
        // Sleep for a while before writing again
        this_thread::sleep_for(chrono::seconds(1));
    }
}

void reader_thread(int id) {
    while (true) {
        sem_wait(&mutex_sem);
        read_count_mutex.lock();
        read_count++;
        if (read_count == 1) {
            sem_wait(&wrt_sem);
        }
        read_count_mutex.unlock();
        sem_post(&mutex_sem);

        // Perform reading
        cout << "Reader " << id << " is reading" << endl;

        sem_wait(&mutex_sem);
        read_count_mutex.lock();
        read_count--;
        if (read_count == 0) {
            sem_post(&wrt_sem);
        }
        read_count_mutex.unlock();
        sem_post(&mutex_sem);

        // Sleep for a while before reading again
        this_thread::sleep_for(chrono::milliseconds(500));
    }
}

int main() {
    // Initialize semaphores
    sem_init(&wrt_sem, 0, 1);
    sem_init(&mutex_sem, 0, 1);

    // Create writer thread
    thread writer(writer_thread);

    // Create reader threads
    thread readers[MAX_READERS];
    for (int i = 0; i < MAX_READERS; i++) {
        readers[i] = thread(reader_thread, i+1);
    }

    // Wait for threads to finish
    writer.join();
    for (int i = 0; i < MAX_READERS; i++) {
        readers[i].join();
    }

    // Destroy semaphores
    sem_destroy(&wrt_sem);
    sem_destroy(&mutex_sem);

    return 0;
}
