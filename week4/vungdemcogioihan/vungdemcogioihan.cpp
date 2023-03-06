#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>

using namespace std;

const int BUFFER_SIZE = 10;
int buffer[BUFFER_SIZE];
int count = 0;
int in = 0;
int out = 0;

mutex mtx;
condition_variable cv_empty;
condition_variable cv_full;

void producer()
{
    for (int i = 1; i <= 100; i++)
    {

        unique_lock<mutex> lock(mtx);

        while (count == BUFFER_SIZE)
        {
            cv_full.wait(lock);
        }

        // Produce an item in nextp
        int item = rand() % 100 + 1;

        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        count++;

        cout << "Produced item " << item << endl;

        cv_empty.notify_all();
        lock.unlock();
    }
}

void consumer()
{
    for (int i = 1; i <= 100; i++)
    {

        unique_lock<mutex> lock(mtx);

        while (count == 0)
        {
            cv_empty.wait(lock);
        }

        // Remove an item from buffer to nextc
        int item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        count--;

        cout << "Consumed item " << item << endl;

        cv_full.notify_all();
        lock.unlock();

        // Consume the item in nextc
        // ...
    }
}

int main()
{
    thread t1(producer);
    thread t2(consumer);

    t1.join();
    t2.join();

    return 0;
}
