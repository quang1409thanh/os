#include <pthread.h>
#include <semaphore.h>
#include <cstdlib>
#include <iostream>
#define N 100      // số lượng vùng đệm
int buffer[N];     // mảng lưu trữ dữ liệu
int fill = 0;      // biến theo dõi số vùng đệm đã được lưu trữ dữ liệu
int use = 0;       // biến theo dõi số vùng đệm đã được đọc dữ liệu
int readcount = 0; // biến theo dõi số tiến trình đang đọc dữ liệu

sem_t mutex, wrt, readcount_sem, empty, full;

void *producer(void *arg)
{
    for (int i = 1; i <= 20; i++)
    {

        // tạo một dữ liệu mới
        int item = rand();

        // chờ đến khi có một vùng đệm trống
        sem_wait(&empty);

        // cập nhật mutex
        sem_wait(&mutex);

        // thêm dữ liệu vào buffer
        buffer[fill] = item;
        fill = (fill + 1) % N;
        std::cout << "Producer wrote " << i
                  << " to buffer" << std::endl;

        // tăng số lượng vùng đệm chứa dữ liệu
        sem_post(&full);

        // giải phóng mutex
        sem_post(&mutex);
    }
}

void *consumer(void *arg)
{
    for (int i = 1; i <= 20; i++)
    {

        // chờ đến khi có một vùng đệm chứa dữ liệu
        sem_wait(&full);

        // cập nhật mutex
        sem_wait(&mutex);

        // lấy dữ liệu từ buffer
        int item = buffer[use];
        use = (use + 1) % N;

        std::cout << "Consumer read " << i
                  << " from buffer" << std::endl;

        // giảm số lượng vùng đệm chứa dữ liệu
        sem_post(&empty);

        // giải phóng mutex
        sem_post(&mutex);

        // sử dụng dữ liệu
        // ...

        // cập nhật readcount_sem để đếm số tiến trình đọc dữ liệu
        sem_wait(&readcount_sem);
        readcount++;
        if (readcount == 1)
        {
            // nếu đây là tiến trình đầu tiên đọc dữ liệu,
            // chờ đến khi không có tiến trình viết dữ liệu đang thực hiện
            sem_wait(&wrt);
        }
        sem_post(&readcount_sem);

        // đọc dữ liệu từ buffer
        // ...

        // cập nhật readcount_sem để đếm số tiến trình đọc dữ liệu
        sem_wait(&readcount_sem);
        readcount--;
        if (readcount == 0)
        {
            // nếu đây là tiến trình cuối cùng đọc dữ liệu,
            // giải phóng mutex để cho phép tiến trình viết dữ liệu khác thực hiện
            sem_post(&wrt);
        }
        sem_post(&readcount_sem);
    }
}

void *writer(void *arg)
{
    for (int i = 1; i <= 20; i++)
    {
        // tạo một dữ liệu mới
        int item = rand();

        // chờ đến khi không có tiến trình đọc dữ liệu đang thực hiện
        sem_wait(&wrt);

        // thêm dữ liệu vào buffer
        buffer[fill] = item;
        fill = (fill + 1) % N;

        std::cout << "du lieu " << i << " duoc them vao buffer" << std::endl;
        // giải phóng mutex
        sem_post(&wrt);

        // tăng số lượng vùng đệm chứa dữ liệu
        sem_post(&full);
    }
}

int main()
{
    // khởi tạo các semaphore
    sem_init(&mutex, 0, 1);
    sem_init(&wrt, 0, 1);
    sem_init(&readcount_sem, 0, 1);
    sem_init(&empty, 0, N);
    sem_init(&full, 0, 0);

    // khởi tạo các thread
    pthread_t prod, cons, wrtr;
    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);
    // pthread_create(&wrtr, NULL, writer, NULL);

    // đợi các thread hoàn thành
    pthread_join(prod, NULL);
    pthread_join(cons, NULL);
    // pthread_join(wrtr, NULL);

    // giải phóng các semaphore
    sem_destroy(&mutex);
    sem_destroy(&wrt);
    sem_destroy(&readcount_sem);
    sem_destroy(&empty);
    sem_destroy(&full);

    return 0;
}
