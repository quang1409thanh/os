# Bài tập lớn OOP - Bomberman Game

## 1. Giới thiệu

- Tên game: Bomberman

- Nhóm: 5

- Tên các thành viên trong nhóm:

    - Nhóm trưởng:  21020796 - Bùi Thế Thuật

    - Thành viên:

        + 21020791 - Nguyễn Quang Thành

        + 21020799 - Triệu Thanh Tùng

- Ngôn ngữ sử dụng: Java swing

- Hình ảnh : nhóm em lấy trên bài mẫu, trên mạng và tự thiết kế

- Âm thanh : nhóm em lấy ở trên mạng

## 2.Mô tả về các đối tượng trong trò chơi và luật chơi
### 2.1 Các đối tượng
Nếu bạn đã từng chơi Bomberman, bạn sẽ cảm thấy quen thuộc với những đối tượng này. Chúng được được chia làm hai loại chính là nhóm đối tượng động (*Bomber*, *Enemy*, *Bomb*) và nhóm đối tượng tĩnh (*Grass*, *Wall*, *Brick*, *Portal*, *Item*).

- ![](src/main/resources/player/blue/boy_down_1.png) *Bomber*  là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống (A/D/W/S) theo sự điều khiển của người chơi.
- ![](src/main/resources/balloom/balloom_left1.png) *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![](src/main/resources/bomb/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng *Flame* ![](src/main/resources/player/boy_down_1.png) được tạo ra.


- ![](src/main/resources/tiles/grass.png) *Grass* là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó
- ![](src/main/resources/wall/wall.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- ![](src/main/resources/wall/brick.png) *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.
  w
- ![](src/main/resources/portal/portal.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

Các *Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
- ![](src/main/resources/Item/SpeedItem.png) *SpeedItem* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
- ![](src/main/resources/Item/FlameItem.png) *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn)
- ![](src/main/resources/Item/BombItem.png) *BombItem* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.

Có nhiều loại Enemy trong Bomberman như:
- ![](src/main/resources/balloom/balloom_left1.png) *Balloom* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định không thể di chuyển qua Brick và Wall.
- ![](src/main/resources/kondoria/kondoria_left3.png) *Kondoria* Là Enemy có chức năng giống như Balloom nhưng có khả năng xuyên Brick và di chuyển ngẫu nhiên.
- ![](src/main/resources/oneal/oneal_left1.png) *Oneal* là Enemy thông minh có khả năng đuổi theo người chơi trong phạm vi nhất định. Không có khả năng di chuyển qua Brick và Wall.
- ![](src/main/resources/ovape/ovape_left_1.png) *Ovape* là Enemy thông minh có  khả năng đuổi theo người chơi bằng đường đi ngắn nhất trong một phạm vi nhất định và có khả năng đi qua Brick.

**Mô tả game play, xử lý va chạm và xử lý bom nổ**
- Trong một màn chơi, Bomber sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy và tìm ra vị trí Portal để có thể qua màn mới
- Bomber sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi Bomb nổ. Lúc đấy trò chơi kết thúc.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb nổ
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.
- Khi Bomb nổ, một Flame trung tâm ![](src/main/resources/bomb/bomb_exploded.png) tại vị trí Bomb nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng 
trên![](src/main/resources/bomb/w1.png)
/dưới![](src/main/resources/bomb/s1.png)
/trái![](src/main/resources/bomb/a1.png)
/phải![](src/main/resources/bomb/d1.png). Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick thì sẽ bị phá hủy còn Wall thì không. Các Enemy và Bomber khi gặp flame thì cũng sẽ chết.

**2.2. Luật chơi:**

- Người chơi có 3 mạng, mỗi lần bị bom nổ trúng hoặc chạm vào quái sẽ bị trừ 1 mạng và reset lại trò chơi

- Điều kiện thắng: Tiêu diệt toàn bộ quái vật trên bản đồ và đi vào các cổng của các level và đi đến cổng của level cuối cùng thì sẽ thắng

- Điều kiện thua: Hết mạng

**2.3. Các thuật toán được sử dụng trong game**

- Thuật toán A* - Pathfinding

- Thuật toán va chạm

- Thuật toán sinh ngẫu nhiên đường đi

**2.4. Các chức năng đã cài đặt**

- Đã cài đặt được tất cả các chức năng của từng đối tượng đã nêu ở phần đầu.

- Bản đồ( gồm các level), nhạc, sound effect, có thể bật tắt.

- Menu

- Quái vật:

    + Ballom (di chuyển ngẫu nhiên)

    + Korondia (di chuyển ngẫu nhiên, có khả năng đi xuyên tường)

    + Oneal (di chuyển ngẫu nhiên nếu nhân vật ở xa hơn một khoảng cách nhất định hoặc không có đường đi từ bản thân nó đến nhân vật, hoặc ngược lại thì sẽ đuổi theo nhân vật)

    + Ovape (giống oneal nhưng có khả năng đi xuyên tường)

- Item:

    + SpeedItem (tăng tốc người chơi)

    + BombItem  (tăng số lượng bom có thể đặt đồng thời lên 2 )

    + FlameItem (tăng độ rộng phá hủy của bom)