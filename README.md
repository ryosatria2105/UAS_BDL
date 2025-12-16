# 🖥️📦 TUGAS BASIS DATA LANJUT  
## Sistem Manajemen Toko Skincare (Kasir & Admin)

*Implementasi Database Terstruktur dan Integrasi Java Swing (JDBC)*

---

## 📄 Studi Kasus
Proyek ini dibuat untuk memenuhi tugas **Ujian Akhir Semester Mata Kuliah Basis Data Lanjut**.  
Studi kasus yang diangkat adalah **Sistem Manajemen Toko Skincare** dengan dua peran utama pengguna, yaitu **Kasir** dan **Admin**.

Aplikasi ini dirancang untuk mengelola proses bisnis toko skincare secara terstruktur, mulai dari transaksi penjualan, pengelolaan stok barang, restok, hingga pemantauan riwayat transaksi dan pembayaran.  
Seluruh data dikelola menggunakan database relasional dengan penerapan relasi antar tabel dan integritas data.

---

## 🧩 Pembagian Role & Hak Akses

### 👤 Kasir
- Melakukan transaksi penjualan  
- Mengelola keranjang belanja  
- Mengecek data pelanggan  
- Melakukan proses pembayaran  

### 👨‍💼 Admin
- Mengelola master data (barang, kategori, supplier, pegawai, dll)  
- Melakukan restok barang  
- Melihat riwayat penjualan  
- Melihat detail barang terjual  
- Memantau stok menipis, barang terlaris, total transaksi, dan omzet harian  

---

## ⚙️ Konsep Basis Data yang Diterapkan
- Relasi antar tabel menggunakan **Primary Key & Foreign Key**  
- Pemisahan data master dan data transaksi  
- Integritas data dijaga melalui constraint database  
- Data transaksi kasir otomatis terintegrasi ke dashboard admin  
- Struktur database dirancang agar minim redundansi dan mudah dikembangkan  

---

## 🖼️ Tampilan Aplikasi

### 🔐 1. Halaman Login
Pengguna dapat login sebagai **Kasir** atau **Admin** sesuai dengan jabatan pegawai.

<img src="https://github.com/user-attachments/assets/6b2c97d0-7bef-46e9-baf7-0a3d58707769" width="85%" />

---

### 💳 2. Tampilan Kasir
Jika pegawai login sebagai kasir, sistem akan menampilkan halaman transaksi penjualan.

<img src="https://github.com/user-attachments/assets/20109cea-023b-4311-8372-3ced2df0ba27" width="85%" />

#### 🛒 Proses Transaksi Kasir
Kasir memasukkan daftar barang yang dibeli ke dalam keranjang.

<img src="https://github.com/user-attachments/assets/adbf8329-fa78-4a1e-bbbc-f48c903f80cc" width="85%" />

Kasir mengecek apakah pelanggan sudah terdaftar atau belum.

<img src="https://github.com/user-attachments/assets/01b63d69-6f2f-4be4-ae2c-910f4dd1f0d6" width="85%" />

Kasir melakukan proses pembayaran.

<img src="https://github.com/user-attachments/assets/a08266d1-69f1-4cfd-9f00-cfae94c05e95" width="85%" />

---

### 📊 3. Dashboard Admin
Dashboard admin menampilkan ringkasan data hasil transaksi kasir, seperti:
- Stok barang yang hampir habis  
- Barang terlaris  
- Total transaksi  
- Total barang terjual  
- Omzet hari ini  

<img src="https://github.com/user-attachments/assets/a1b836ca-27e4-4ebe-bea5-9bd6c4d490ed" width="85%" />

---

### 🗂️ 4. Master Data
Admin dapat mengelola data master, seperti data barang.

<img src="https://github.com/user-attachments/assets/5655cf2b-4c80-4675-9e83-ba6e1347b6e9" width="85%" />

<br><br>

<img src="https://github.com/user-attachments/assets/36ce2083-aac0-4c17-b6f2-ebaf7f647abc" width="85%" />

---

### 📜 5. Riwayat Penjualan
Admin dapat melihat riwayat transaksi penjualan yang dilakukan oleh kasir.

<img src="https://github.com/user-attachments/assets/e3ad47e5-303c-457f-99ce-7dd8bde6d119" width="85%" />

#### 🔍 Detail Barang Terjual

<img src="https://github.com/user-attachments/assets/15a68604-efcf-4d14-aac4-003a7d080b14" width="85%" />

<br><br>

<img src="https://github.com/user-attachments/assets/0c55e4fa-50bc-474d-b928-21d0aa22cf87" width="85%" />

<br><br>

<img src="https://github.com/user-attachments/assets/01f3de3b-e62b-4ddb-ae09-6e1ae7f73253" width="85%" />

---

### 📦 6. Riwayat & Proses Restok Barang
Admin dapat melihat riwayat restok barang dan melakukan proses restok.

<img src="https://github.com/user-attachments/assets/8a39e2a6-e1d3-4b5e-b439-a7daef366799" width="85%" />

Proses restok barang:

<img src="https://github.com/user-attachments/assets/4bdb82a2-0fb9-45f6-91fe-aadef21a8689" width="85%" />

Detail barang yang direstok:

<img src="https://github.com/user-attachments/assets/cfdb403b-64ef-4ec0-9852-ce377f162d40" width="85%" />

<br><br>

<img src="https://github.com/user-attachments/assets/7cbb2fbf-0c60-4468-bd93-7bc2e67ed17d" width="85%" />

<br><br>

<img src="https://github.com/user-attachments/assets/b1aeac8b-b686-4fb1-9092-feeb02e3fb5c" width="85%" />

---

### 💰 7. Riwayat Pembayaran
Admin dapat melihat riwayat pembayaran dari transaksi yang telah dilakukan.

<img src="https://github.com/user-attachments/assets/02980921-c6c5-4635-a69c-b67198c4be19" width="85%" />

<br><br>

<img src="https://github.com/user-attachments/assets/38fcb2a7-caf7-4a0f-8881-dddbbfe9cf6f" width="85%" />

---

## 👨‍💻 Author
Satria  
Program Studi Sistem Informasi  
Universitas Islam Negeri Sunan Ampel Surabaya  
2025  

---

> Proyek ini dibuat untuk keperluan akademik  
> Mata Kuliah: Basis Data Lanjut
