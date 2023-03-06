#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <queue>

using namespace std;

const int BUFFER_SIZE = 10; // kích thước của vùng đệm
queue<int> buffer; // vùng đệm

mutex mtx; // khóa mutex
condition_variable not_full; // biến điều kiện Semaphore đầy
condition_variable not_empty; // biến điều kiện Semaphore rỗng

void producer() {
  for (int i = 1; i <= 20; i++) {
    unique_lock<mutex> lck(mtx); // khóa mutex
    not_full.wait(lck, [] { return buffer.size() < BUFFER_SIZE; }); // chờ Semaphore rỗng
    buffer.push(i); // ghi dữ liệu vào vùng đệm
    cout << "Producer wrote " << i << " to buffer" << endl;
    not_empty.notify_one(); // tăng Semaphore đầy lên 1
  }
}

void consumer() {
  for (int i = 1; i <= 20; i++) {
    unique_lock<mutex> lck(mtx); // khóa mutex
    not_empty.wait(lck, [] { return !buffer.empty(); }); // chờ Semaphore đầy
    int data = buffer.front(); // đọc dữ liệu từ vùng đệm
    buffer.pop();
    cout << "Consumer read " << data << " from buffer" << endl;
    not_full.notify_one(); // giảm Semaphore đầy đi 1
  }
}

int main() {
  thread producer_thread(producer);
  thread consumer_thread(consumer);
  producer_thread.join();
  consumer_thread.join();
  return 0;
}