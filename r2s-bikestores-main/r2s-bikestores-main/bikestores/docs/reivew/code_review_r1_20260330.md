# Code review R1 — Bikestores (Java / JDBC)

**Ngày:** 2026-03-30  
**Phạm vi:** `jbe/LinhMT/BikeStore/r2s-bikestores-main/r2s-bikestores-main/bikestores`

---

## Điểm tốt

| Hạng mục | Ghi chú |
|----------|---------|
| SQL an toàn | DAO dùng `PreparedStatement`, tránh SQL injection |
| Tài nguyên JDBC | `try-with-resources` cho `Connection` / `PreparedStatement` (và `ResultSet` trong `findAll`) |
| Kiến trúc | Tách entity, DAO, form, app — dễ đọc và mở rộng |
| Xử lý lỗi | `DAOException` + `GlobalExceptionHandler` gom lỗi DB thay vì in stack trace rải rác |
| Nhập liệu | `ScannerUtil` lặp khi sai định dạng số |
| Form | `CustomerForm` giới hạn gender (Male/Female/Other) rõ ràng |

---

## Cần điều chỉnh — ưu tiên cao

### 1. Bảo mật: cấu hình database trong mã nguồn

- **Vấn đề:** `JDBCUtil` hardcode URL, user, password.
- **Đề xuất:** Đưa ra biến môi trường hoặc `application.properties` / `config.properties` (không commit), có thể kèm Maven filtering hoặc profile local.

### 2. Nhiều `Scanner` trên `System.in`

- **Vấn đề:** `ScannerUtil` dùng một `Scanner` tĩnh; `BrandApp`, `CustomerApp`, `CategoryApp` lại `new Scanner(System.in)`.
- **Đề xuất:** Thống nhất một nguồn — mở rộng `ScannerUtil` (ví dụ `readChoice`, `readLine`) hoặc một `Scanner` dùng chung được inject.

### 3. Import thừa / code không dùng

- **CategoryApp:** import `util.Constants` nhưng không sử dụng — xóa.
- **CustomerForm:** import `ValidationUtil` nhưng không sử dụng — xóa hoặc áp dụng cho phone/email.
- **DashboardUI:** cùng package `app` — import `CategoryApp`, `CustomerApp` thừa (có thể bỏ).

### 4. Định dạng mã

- **DashboardUI:** `handleCustomerCategory()` — chỉnh thụt lề cho đồng nhất với phần còn lại.

---

## Cần điều chỉnh — ưu tiên trung bình

### 5. `ResultSet` trong `findById`

- **Ghi chú:** Đóng `PreparedStatement` thường đóng `ResultSet` theo; vẫn nên dùng `try-with-resources` cho `ResultSet` trong `findById` để rõ ràng và an toàn khi refactor sau này.

### 6. `update` / `delete` không kiểm tra số dòng ảnh hưởng

- **Đề xuất:** Dùng giá trị trả về của `executeUpdate()`; nếu 0 thì thông báo (ví dụ không tìm thấy ID).

### 7. `GlobalExceptionHandler`

- **Vấn đề:** Chỉ in `getMessage()` — có thể `null`; lỗi gốc DB dễ bị che.
- **Đề xuất:** `e.toString()` hoặc logger; khi dev có thể log stack trace / level DEBUG.

### 8. Đồng nhất định dạng bảng console

- **Ghi chú:** `Constants` có format cho Brand; Category/Customer in khác — có thể đưa header/row format vào `Constants` hoặc lớp riêng.

### 9. Dependency MySQL trong `pom.xml`

- **Ghi chú:** Artifact `mysql:mysql-connector-java` là tên cũ; phiên bản mới thường dùng `com.mysql:mysql-connector-j` khi cập nhật dependency.

---

## Ghi chú kiến trúc / phạm vi

- Dashboard có nhiều mục (Staff, Store, Orders, …) chưa có module tương ứng — nên ghi chú trong README để tránh hiểu nhầm phạm vi đã hoàn thành.
- Mỗi thao tác DAO mở connection mới — chấp nhận được cho CLI/học tập; hệ thống lớn hơn nên xem xét connection pool (ví dụ HikariCP).

---

## Tóm tắt hành động

1. Loại bỏ secret khỏi repo; cấu hình DB qua env/properties.  
2. Thống nhất cách đọc console (`Scanner`).  
3. Dọn import, chỉnh format `DashboardUI`.  
4. (Tuỳ chọn) `findById` + `ResultSet`, kiểm tra `executeUpdate`, cải thiện exception/logging, đồng nhất format bảng, cập nhật connector MySQL.
