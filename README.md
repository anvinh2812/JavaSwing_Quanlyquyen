## Những điều cần biết bề project này:
- ### Project này không dùng drag-drog để làm giao diện
- ### Project này theo mô hình MVC
- Trong thư mục **Database** có chứa file tạo DB
- Cơ sở dữ liệu trong project này dùng: **SQL Server**
    - *Nếu dùng db với tên đăng nhập và tên db khác với ban đầu thì hãy chỉnh trong file **ConnectSQL.java:***
        - *Ở đoạn `String url = "jdbc:sqlserver://localhost:1433;database=quanlyquyen;user=sa;password=2812;encrypt=true;trustServerCertificate=true";` hãy thay `database` , `user`, `password` của bạn vào*
- ### Các phiên bản của JDK + SDK + JDBC
    - JDK: jdk-19.0.1
    - SDK: sdk-19
    - JDBC: sqljdbc12.2
- ### Nếu có lỗi j thì hãy liên hệ đến: `andangvinh2812.work@gmail.com` hoặc `vinh0209266@huce.edu.vn`
# Xin cảm ơn