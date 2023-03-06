#include <iostream>
#include <semaphore.h>
#include <unistd.h> // for sleep() function

using namespace std;

sem_t chopsticks[5];
sem_t numSeats;

void printStatus()
{
    for (int i = 0; i < 5; i++)
    {
        int left = i;
        int right = (i + 1) % 5;
        int leftValue, rightValue;
        sem_getvalue(&chopsticks[left], &leftValue);
        sem_getvalue(&chopsticks[right], &rightValue);
        cout << "Philosopher " << i << ": chopstick " << left << " = " << leftValue << ", chopstick " << right << " = " << rightValue << endl;
    }
}

void *philosopher(void *arg)
{
    int i = *((int *)arg);
    int left = i;
    int right = (i + 1) % 5;

    while (true)
    {
        // Wait for available seat
        sem_wait(&numSeats);

        // Pick up chopsticks
        sem_wait(&chopsticks[left]);
        sem_wait(&chopsticks[right]);

        // Eat
        cout << "Philosopher " << i << " is eating" << endl;
        sleep(1); // Eating for 1 second

        // Put down chopsticks
        sem_post(&chopsticks[left]);
        sem_post(&chopsticks[right]);

        // Release seat
        sem_post(&numSeats);

        // Print status
        printStatus();
        cout << endl;

        // Sleep for 5 seconds
        usleep(500); // Sleeping for 5 seconds
    }
}

int main()
{
    // Initialize semaphores
    sem_init(&numSeats, 0, 4);
    for (int i = 0; i < 5; i++)
    {
        sem_init(&chopsticks[i], 0, 1);
    }

    // Create philosopher threads
    pthread_t threads[5];
    int ids[5];
    for (int i = 0; i < 5; i++)
    {
        ids[i] = i;
        pthread_create(&threads[i], NULL, philosopher, &ids[i]);
    }

    // Wait for philosopher threads to finish
    for (int i = 0; i < 5; i++)
    {
        pthread_join(threads[i], NULL);
    }

    // Destroy semaphores
    sem_destroy(&numSeats);
    for (int i = 0; i < 5; i++)
    {
        sem_destroy(&chopsticks[i]);
    }
    return 0;
}
