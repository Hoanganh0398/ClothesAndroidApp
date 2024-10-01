# ClothesAndroidApp
# ClothesShopAndroid
Hướng dẫn sử dụng android của nhóm 4: - Tải banquanao về
- Vào trong xampp mở mục htdocs và bỏ banquanao vào
  ![image](https://github.com/user-attachments/assets/88afd823-135c-4b18-90d4-58c175b61967)

- Vào trang admin Xampp và import database clothes:
![image](https://github.com/user-attachments/assets/e637ca0d-5071-4bae-804b-d5a441dfb9c9)
![image](https://github.com/user-attachments/assets/da46323f-7306-45e5-aa93-05e53724922c)

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/ --
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2023 at 04:56 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
/*!40101 SET
@OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET
@OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET
@OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
--
-- Database: `clothes`
--
-- --------------------------------------------------------
--
-- Table structure for table `tb_chitietdonhang`
--CREATE TABLE `tb_chitietdonhang` (
`id` int(11) NOT NULL, `mahoadon` int(11) DEFAULT NULL, `masp` int(10) DEFAULT NULL, `makhuyenmai` int(10) DEFAULT NULL, `soluong` int(10) DEFAULT NULL, `tonggia` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `tb_chitietdonhang`
--
INSERT INTO `tb_chitietdonhang` (`id`, `mahoadon`, `masp`, `makhuyenmai`, `soluong`, `tonggia`) VALUES
(1, 4001, 3001, 1, 2, 280), (2, 4002, 3002, 2, 4, 900), (3, 4003, 3011, 4, 2, 500), (4, 4004, 3014, 6, 1, 210), (5, 4005, 3017, 6, 3, 630), (6, 4006, 3003, NULL, 5, 1050), (7, 4007, 3015, 6, 3, 420), (8, 4008, 3016, 9, 6, 1380), (9, 4008, 3017, NULL, 2, 400), (10, 4009, 3018, NULL, 1, 500), (11, 4010, 3019, NULL, 2, 600), (12, 4010, 3020, NULL, 1, 300); -- --------------------------------------------------------
--
-- Table structure for table `tb_danhmuc`
--CREATE TABLE `tb_danhmuc` (
`id_dm` int(11) NOT NULL, `Tendanhmuc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `tb_danhmuc`
--
INSERT INTO `tb_danhmuc` (`id_dm`, `Tendanhmuc`) VALUES
(1001, 'Áo sơ mi'), (1002, 'Áo thun'), (1003, 'Áo kiểu'), (1004, 'Quần dài'), (1005, 'Váy ngắn'), (1006, 'Chân váy'), (1007, 'Set đồ nam'), (1008, 'Set đồ nữ'); -- --------------------------------------------------------
--
-- Table structure for table `tb_donhang`
--CREATE TABLE `tb_donhang` (
`id` int(11) NOT NULL, `hoten` varchar(255) DEFAULT NULL, `email` varchar(255) DEFAULT NULL, `sodt` varchar(255) DEFAULT NULL, `diachi` varchar(255) DEFAULT NULL, `ghichu` varchar(255) DEFAULT NULL, `thoidiemdathang` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `tb_donhang`
--
INSERT INTO `tb_donhang` (`id`, `hoten`, `email`, `sodt`, `diachi`, `ghichu`, `thoidiemdathang`) VALUES
(4001, 'Lê Vân Anh', 'Anh123@gmailcom ', '09856244421', 'Bắc Giang', 'Anh123',
'2023-06-09'), (4002, 'Phạm khánh Như ', 'Khanhnhu123@gmail.com', '0985624870', 'Đà lạt', 'xxxx',
'2023-07-23'), (4003, 'Nguyễn Văn Dũng', 'Dung123@gmailcom', '0985624826', 'Lâm Đồng',
'xxxxx', '2023-08-09'), (4004, 'Quách Khánh Duy ', 'Duy123@gmail.com', '0789236487', 'Bến Tre', 'xxxxx',
'2023-10-27'), (4005, 'Phan Dĩnh Tuệ', 'Tue123@gmailcom ', '09856245693', 'TP HCM', 'xxxx',
'2023-10-25'),
(4006, 'Nguyễn Văn Dũng', 'Dug123@gmailcom', '0985624826', 'Lâm Đồng', 'xxxxx',
'2023-06-26'), (4007, 'Phan Minh Quốc Anh', 'Anh123@gmail.com', '0985624777', 'Vĩnh long', 'xxx',
'2023-10-21'), (4008, 'Phạm Thị bé', 'Be123@gmail.com', '0985624568', 'Đắt Nông', 'xxxxxx', '2023- 11-28'), (4009, 'Lê Anh Nam', 'Nam123@gmail.com', '0985666997', 'An Giang', 'xxxxxx',
'2023-12-29'), (4010, 'Lê Vân Anh', 'Anh123@gmailcom ', '09856244421', 'Bắc Giang', 'Anh123',
'2023-11-14'); -- --------------------------------------------------------
--
-- Table structure for table `tb_khachhang`
--CREATE TABLE `tb_khachhang` (
`id` int(11) NOT NULL, `hoten` varchar(255) DEFAULT NULL, `email` varchar(255) DEFAULT NULL, `sdt` varchar(255) DEFAULT NULL, `diachi` varchar(255) DEFAULT NULL, `matkhau` varchar(255) DEFAULT NULL, `hinhanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `tb_khachhang`
--
INSERT INTO `tb_khachhang` (`id`, `hoten`, `email`, `sdt`, `diachi`, `matkhau`, `hinhanh`) VALUES
(1, 'Lương Thị Thanh Mai', 'maideptrai@gmail.com', '0375002936', '40/8 Đ. Ấp Bắc, Phường 13, Tân Bình, Thành phố Hồ Chí Minh, Việt Nam', 'maideptraithat', 'mai'), (2, 'Nguyễn Trúc Thanh', 'daucatmoi@gmail.com', '0371829394', 'V7XH+MJR, QL60, Lương Hoà, Châu Thành, Trà Vinh, Việt Nam', 'datcatmoimoi', 'thanh'), (3, 'Quách Dũy Khang', 'khangbebe@gmail.com', '0362839921', 'Giao lộ, Đ. Châu
Văn Liêm, Khóm 3, Tp. Cà Mau, Cà Mau, Việt Nam', '12345qwert', 'khang'), (4, 'Trần Thanh Minh', 'traihotran@gmail.com', '03628462848', 'Phường 10, Tp. Đà
Lạt, Lâm Đồng, Việt Nam', 'thanhminh1212', 'minh'), (5, 'Đinh Kiến Phong', 'phong@gmail.com', '02746284283', '5/10 Đường 84 Cao Lỗ, Phường 4, Quận 8, Thành phố Hồ Chí Minh 73018, Việt Nam', 'phong12345', 'phong'), (6, 'Linh Lee', 'leemaikeo@gmail.com', '02747269271', '1397 Hoàng Văn Thụ, Phường 4, Tân Bình, Thành phố Hồ Chí Minh, Việt Nam', 'leelinhhehe', 'linhlee'), (7, 'Ngô Ngọc Nhi', 'nhinhinhnham@gmail.com', '03749263723', 'PH8V+8GR, Khuất
Văn Bức, Tân Kiên, Bình Chánh, Thành phố Hồ Chí Minh, Việt Nam',
'ngongocnhi00', 'nhi'),
(8, 'Bùi Thị Tâm', 'tamtam@gmail.com', '02623764232', 'Đường 3/2, Thành phố Vũng
Tầu, Bà Rịa - Vũng Tàu, Việt Nam', 'tamtam', 'tam'), (9, 'Lê Yến', 'yen123@gmail.com', '07463372816', 'C564+JGR, QL51B, Phường 12, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam', 'khang11', 'yen'), (10, 'Trần Minh Quân', 'quanquan@gmail.com', '0284728837', '126 Đường Số 17A, Bình Trị Đông B, Bình Tân, Thành phố Hồ Chí Minh, Việt Nam', 'minquanne', 'quan'), (11, 'Hạo Nam', 'haonamday@gmail.com', '0327382333', '20a Đường Số 8, Tân Tạo A, Bình Tân, Thành phố Hồ Chí Minh, Việt Nam', 'deptrainhatnha', 'nam'), (12, 'Lý Dũng', 'dungxauxi@gmail.com', '0972722332', '4CF4+JR7, Đường tỉnh 787, Hưng Thuận, Trảng Bàng, Tây Ninh, Việt Nam', 'dunglychilatoi', 'dung'), (13, 'Phát Phan', 'phanphat@gmail.com', '0237238762', 'RFXH+96Q, ĐT824, Mỹ
Hạnh Nam, Đức Hòa, Long An, Việt Nam', 'phankhokhan', 'phan'), (16, 'Lương Thị Thanh Mai', 'maideptrai@gmail.com', '0375002936', '40/8 Đ. Ấp Bắc, Phường 13, Tân Bình, Thành phố Hồ Chí Minh, Việt Nam', 'maideptraithat', 'mai'), (18, '1', '1', '1', NULL, '1', ''); -- --------------------------------------------------------
--
-- Table structure for table `tb_khuyenmai`
--CREATE TABLE `tb_khuyenmai` (
`id` int(11) NOT NULL, `ten` text NOT NULL, `ngay_bat_dau` date NOT NULL, `ngay_ket_thuc` date NOT NULL, `mota` varchar(100) DEFAULT NULL, `Hinhanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `tb_khuyenmai`
--
INSERT INTO `tb_khuyenmai` (`id`, `ten`, `ngay_bat_dau`, `ngay_ket_thuc`, `mota`, `Hinhanh`) VALUES
(1, 'Khuyến mãi mùa hè', '2023-06-01', '2023-06-30', 'Giảm 30% cho toàn bộ mặt
hàng thuộc bộ sưu tập mùa hè! Áp dụng trên mỗi hóa đơn', ''), (2, 'Khuyến mãi đặc biệt', '2023-09-15', '2023-09-30', 'Giảm 50% tối đa 300.000 cho
hóa đơn trên 1.000.000', ''), (3, 'Khuyến mãi cuối năm', '2023-12-01', '2023-12-31', 'Giảm 12% tất cả các mặt hàng
trong shop', ''), (4, 'Khuyến mãi mùa hè 2', '2023-07-01', '2023-07-31', 'Tặng áo thun Unisex khi mua
hóa đơn bất kỳ từ 500.000', ''), (5, 'Gì cũng rẻ', '2023-12-01', '2023-12-31', 'Mua 1 tặng 1 áp dụng cho các loại áo
khoác mùa đông', ''), (6, 'Treat or Trick?', '2023-10-20', '2023-10-31', 'Giảm 30% tối đa 100K cho các mặt
hàng thuộc bộ sưu tập Halloween!', ''),
(7, 'Valentine ấm áp', '2024-02-01', '0000-00-00', 'Tặng móc khóa khi mua 2 cái áo', ''), (8, 'Tết sum vầy', '2023-12-31', '2024-01-01', 'Giảm 40% tối đa 100K cho tất cả các
mặt hàng thuộc danh mục áo dài', ''), (9, 'Hàng xịn giá tốt', '2023-11-01', '2023-11-30', 'Giảm 20% tối đa 500K cho hóa đơn
trên 1.000.000', ''), (10, 'Mua 1 được 2', '2023-11-01', '2023-11-20', 'Mua 1 tặng 1 áp dụng cho các mặt
hàng thuộc danh mục đồ ngủ', ''); -- --------------------------------------------------------
--
-- Table structure for table `tb_lienhe`
--CREATE TABLE `tb_lienhe` (
`malienhe` int(11) NOT NULL, `hoten` varchar(50) DEFAULT NULL, `email` varchar(50) DEFAULT NULL, `sdt` int(12) DEFAULT NULL, `diachi` varchar(100) DEFAULT NULL, `noidung` text DEFAULT NULL, `trangthai` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; -- --------------------------------------------------------
--
-- Table structure for table `tb_quanlyphanhoi`
--CREATE TABLE `tb_quanlyphanhoi` (
`id` int(11) NOT NULL, `hoten` varchar(255) DEFAULT NULL, `email` varchar(255) DEFAULT NULL, `sodt` varchar(255) DEFAULT NULL, `tensp` varchar(255) DEFAULT NULL, `ghichu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `tb_quanlyphanhoi`
--
INSERT INTO `tb_quanlyphanhoi` (`id`, `hoten`, `email`, `sodt`, `tensp`, `ghichu`)
VALUES
(7002, 'Nguyễn Hữu Bằng', 'Huubang123@gmail.com', '094543565854', 'Aó', 'sản
phẩm đẹp'),
(7003, 'Vũ Thùy Linh', 'Linh123@gmail.com', '05697589654', 'váy', 'sản phẩm giống
hình'), (7004, 'Nguyễn Đình Trọng', 'Trong123@gmail.com', '0956879681', 'quần', 'hàng chất
lượng'), (7005, 'Nguyễn Đăng Khoa ', 'Khoa123@gmail.com', '0987363636', 'Aó', 'mặc vừa'), (7006, 'Phạm Thị bé', 'Be123@gmail.com', '0985856361', 'Áo thun nam', 'đẹp quá à'), (7007, 'Phan Minh Quốc Anh', 'Anh123@gmail.com', '0987760740', 'Quần', 'xuất sắc'), (7008, 'Trần Khánh Ly', 'Ly123@gmail.com', '0956842374', 'Aó thun', 'sản phẩm
giống hình'), (7009, 'Lê Anh Nam', 'Nam123@gmail.com', '0963257469', 'váy', 'đẹp quá à'), (7010, 'Phạm Diễm Lệ', 'Le123@gmail.com', '0978416273', 'Aó sơ mi', 'khỏi chê'), (7011, 'Nguyễn Văn Dũng', 'Dung123@gmail.com', '08756913591', 'Quần short',
'kiwikiwi'), (7012, 'Tạ Tuyết Hạnh', 'Hanh123@gmail.com', '0956284607', 'Áo thun', 'hàng chất
lượng'), (7013, 'Nguyễn Thị Băng', 'Bang123@gmail.com ', '0978784749', 'Váy ', '5 sao'), (7014, 'Nguyễn Anh Đức ', 'Duc123@gmail.com ', '0398524796', 'Quần tây',
'kiwikiwi'), (7015, 'Võ Minh Tâm', 'Tam123@gmail.com', '0987485836', 'đồ nam', 'hàng chất
lượng'), (7016, 'Phan Dĩnh Tuệ', 'Tue123@gmail.com', '0978989595', 'đồ nữ', 'sản phẩm đẹp'), (7017, 'Lê Vân Anh', 'Anh123@gmail.com ', '0969357159', 'Quần', 'khỏi chê'), (7018, 'Phạm Việt Hải', 'Hai123@gmail.com ', '0876924833', 'Aó sơ mi', 'mặc vừa'); -- --------------------------------------------------------
--
-- Table structure for table `tb_sanpham`
--CREATE TABLE `tb_sanpham` (
`id` int(11) NOT NULL, `danhmuc_id` int(11) DEFAULT NULL, `tensp` varchar(255) DEFAULT NULL, `price` int(11) DEFAULT NULL, `hinhanh` varchar(255) DEFAULT NULL, `mota` longtext DEFAULT NULL, `ngaymoban` date DEFAULT NULL, `ngayketthuc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `tb_sanpham`
--
INSERT INTO `tb_sanpham` (`id`, `danhmuc_id`, `tensp`, `price`, `hinhanh`, `mota`, `ngaymoban`, `ngayketthuc`) VALUES
(3001, 1003, 'Áo bánh bèo 1 ', 200000, 'aobanhbeo', 'Mô tả sản phẩm: Mềm mại, dễ
chịu và ai mặc cũng xink', '2023-05-21', '2023-10-21'),
(3002, 1003, 'Áo bánh bèo có ren ', 300000, 'aobanhbeocorem', 'Nữ tính, xinh đẹp
xuất sắc', '2023-05-21', '2023-10-21'), (3003, 1003, 'Áo bánh bèo chấm đen ', 210000, 'aobanhbeochamden', 'Còn chiếc áo
nào đep hơn không?', '2023-05-21', '2023-10-21'), (3004, 1003, 'Áo công sở nữ ', 250000, 'aocongso', 'Áo phong cách nhà quý sờ tộc',
'2023-05-21', '2023-10-21'), (3005, 1002, 'Áo thun nam nữ ', 200000, 'aodearphanquang', 'Áo thun quốc dân 0 có
gì để nói', '2023-05-21', '2023-10-21'), (3006, 1003, 'Áo kiểu Hàn Quốc ', 320000, 'aohanquoc', 'Áo chỉ có tại hàn quắc',
'2023-05-21', '2023-10-21'), (3007, 1003, 'Áo khoác đen ', 400000, 'aokhoacden', 'Áo giày, chất vải 0 xù, dễ dùng',
'2023-05-21', '2023-10-21'), (3008, 1001, 'Áo sơ mi đi biển ', 2000000, 'aosomidibien', 'Thích hợp cho mùa hè đi
biển', '2023-05-21', '2023-10-21'), (3009, 1001, 'Áo sơ mi lưới tím ', 3400000, 'aosomiluoitim', 'Áo xinh đep đi học đi
chơi', '2023-05-21', '2023-10-21'), (3010, 1002, 'Áo thun TOMM ', 2000000, 'aothuntomm', 'Áo thun quốc dân thứ
2222222222222222', '2023-05-21', '2023-10-21'), (3011, 1002, 'Áo xám trắng ', 250000, 'aoxamdennam', 'Áo xám hàn xẻng có 102',
'2023-05-21', '2023-10-21'), (3012, 1002, 'Áo xám đen ', 2500000, 'aoxamnam', 'Áo xám nam mỏng mát ', '2023- 05-21', '2023-10-21'), (3013, 1006, 'Chân váy dài ', 310000, 'chanvaydai', 'Chân váy phù hợp cho các chị
gái chứ con trai thì 0', '2023-05-21', '2023-10-21'), (3014, 1006, 'Chân váy jean dài ', 300000, 'chanvayjeanday', 'Như chân váy dài, Halloween', '2023-10-20', '2024-04-23'), (3015, 1006, 'Chân váy jean ngắn ', 2000000, 'chanvayjeanngan', 'Váy ngắn xinh đẹp, chủ shop còn mê, Halloween', '2023-10-21', '2024-04-23'), (3016, 1006, 'Chân váy ngắn ', 2800000, 'chanvayngan1', 'Chủ tiệm cũng mê chân váy
này, đẹp khỏi bàn', '2023-10-21', '2024-04-23'), (3017, 1006, 'Chân váy tennis ', 2000000, 'chanvaytennis', 'Chân váy quốc dân,khách
hàng tự oánh giá ', '2023-10-21', '2024-04-23'), (3018, 1005, 'Đầm vải voan ', 5000000, 'damvaivoan', 'Đầm sinh nhật, dự tiệc, kỉ
yếu......', '2023-10-21', '2024-04-23'), (3019, 1001, 'Áo polo trắng', 3000000, 'polotrang', 'Áo polo thanh lịch quý phái',
'2023-10-21', '2024-04-23'), (3020, 1004, 'Quần da nữ ', 3000000, 'quandanu', 'Quần mang phong cách tráy như fi
fai', '2023-10-21', '2024-04-23'), (3021, 1004, 'Quần jean nam ', 300000, 'quanjeannams', 'Quần jean quốc dân ', '2023- 10-21', '2024-04-23'), (3022, 1004, 'Quần jean ', 300000, 'quanjeannu', 'Quần jean nhập khẩu từ hàn quắc',
'2023-10-21', '2024-04-23'), (3023, 1004, 'Quần short nam ', 200000, 'quanshort', 'Quần short lấy sỉ 180k rồi lời có
20k thôi, xin đừng áp voucher', '2023-10-21', '2024-04-23'), (3024, 1004, 'Quần tây ', 470000, 'quantay', 'Quần đẹp, sang trọng', '2023-10-21',
'2024-04-23'), (3025, 1004, 'Quần xám ', 4700000, 'quanxam', 'Quần đẹp, thoáng mát, thoải mái, tặng
kèm tất', '2023-10-21', '2024-04-23'), (3026, 1007, 'Set đồ nam 1 ', 8000000, 'setdonamhanquoc', 'Set đồ siêu xinh', '2023- 10-21', '2024-04-23'),
(3027, 1008, 'Set váy nữ 1 ', 700000, 'setvaynu', 'Set váy bán chạy nhất, chỉ duy nhất 1
shop bán', '2023-10-21', '2024-04-23'), (3028, 1001, 'Áo sơ mi đen ', 280000, 'somiden', 'Áo sơ mi lịch sự', '2023-10-21',
'2024-04-23'), (3029, 1001, 'Áo sơ mi đen xanh nam ', 3500000, 'somidennam', 'Áo sơ mi đen sử
dụng mọi trường hợp', '2023-10-21', '2024-04-23'), (3030, 1005, 'Váy hồng bồng bềnh ', 400000, 'vayhongcongchua', 'Váy bồng bềnh, mềm mịn, thoải mái, nhưng sẽ hơi ngứa', '2023-10-21', '2024-04-23'), (3031, 1005, 'Váy trắng bánh bèo ', 5000000, 'vaytrang', 'Váy mềm mịn, xink', '2023- 10-21', '2024-04-23'), (3032, 1005, 'Váy tím mộng mer ', 550000, 'vaytimmongmo', 'Váy nhập khẩu, bên
trong là vải voan tơ nên rất mềm mịn', '2023-10-21', '2024-04-23'); -- --------------------------------------------------------
--
-- Table structure for table `tb_tintuc`
--CREATE TABLE `tb_tintuc` (
`id_tt` int(11) NOT NULL, `hinhanhtintuc` varchar(50) DEFAULT NULL, `tieude` varchar(255) DEFAULT NULL, `content` longtext DEFAULT NULL, `ngaydangtin` datetime DEFAULT NULL, `nguoidang` varchar(50) DEFAULT NULL, `likes` varchar(255) DEFAULT NULL, `views` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `tb_tintuc`
--
INSERT INTO `tb_tintuc` (`id_tt`, `hinhanhtintuc`, `tieude`, `content`, `ngaydangtin`, `nguoidang`, `likes`, `views`) VALUES
(881, 'tt1-1.jpg', 'Năm nay, Tay áo càng dài rộng càng mốt', 'Thời trang đúng là thứ
trở mặt còn nhanh hơn thời tiết! Bất cứ thứ gì hôm qua còn là mốt, hôm nay có thể
không nhận được “sủng ái” nữa. Ngược lại, có những thứ mới vài hôm trước tưởng
chẳng ra sao, hôm nay đã bao nhiêu người muốn mặc. Kiểu áo tay dài và rộng nhìn
qua có chút bất hợp lý có lẽ là một trong những item thời trang như vậy.', '0000-00-00
00:00:00', 'Mr.MAI', '600K', '1200M'), (882, 'tt22.jpg', '10 items đại diện cho phong cách thời trang Geek Chic. ', 'Phong cách
thời trang Geek Chic – hay còn gọi là phong cách mọt sách, là một trong những
phong cách thú vị bạn nên thử áp dụng.Phong cách thời...', '2023-04-09 00:00:00',
'ThanhtrucNguyen', '211k', '477M'), (883, 'tt3.jpg', 'Áo kẻ ngang chưa bao giờ lo lỗi mốt', 'Không phải tự nhiên mà áo kẻ
ngang lại chiếm được cảm tình của các bạn trẻ đến thế Striped Tee hay còn với tên gọi
thuần việt là áo kẻ...', '2023-01-12 00:00:00', 'Mr.Lyly', '200k', '700M'),
(884, 'tt4.jpg', 'LÝ DO KHIẾN BỘ SƯU TẬP CỦA KBM Shop TRỞ THÀNH XU
HƯỚNG', 'Bộ sưu tập này gợi lại ký ức tuổi thơ của các bạn trẻ về hình ảnh chú mèo
méo đáng yêu, thông minh và tình cảm. Với sự cá tính, phá cách và nhiệt huyết của
các bạn trẻ thế hệ gen Z, tụi mình tin rằng DORAEMON Collection sẽ trở nên phổ
biến và được ưa chuộng rộng rãi. ', '2023-12-06 00:00:00', 'Khang', '666k', '1000'), (885, 'tt5.jpg', 'MIX AND MATCH LÀ GÌ? NGUYÊN TẮC KHI MIX AND
MATCH TRONG KBM', 'Gu thời trang tối giản chưa bao giờ lỗi thời đối với các bạn
trẻ yêu thích sự năng động và phóng khoáng. Với mong muốn đem đến các bạn những
item Streetwear cao cấp, sang trọng nhưng lại giản dị, nhiều mẫu áo thun trơn được
với thiết kế tối giản luôn được Lì Ven ra mắt trong suốt nhiều năm qua. ', '2320-11-15
00:00:00', 'LELE', '500K', '500K'), (886, 'tt6.jpg', 'KHÁM PHÁ 5 TONE MÀU ĐÓN HÈ CÙNG LEVENTS', 'Nhận thấy
phản ứng tích cực của khách hàng khi áo thun baby tee xuất hiện, nhiều thương hiệu
thời trang tại Việt Nam đã bắt đầu tung ra thị trường các bộ sưu tập baby tee mang
phong cách riêng. Để thu hút sự chú ý của các bạn trẻ gen Z, họ ưu tiên sử dụng tone
màu tươi sáng kết hợp với các họa tiết, in, thêu cùng chất liệu vượt trội. ', '2023-06-06
00:00:00', 'Trong', '200K', '100M'), (887, 'tt7.jpg', 'THỜI TRANG THẬP NIÊN 90 VÀ NHỮNG OUTFIT TỪNG LÀM
MƯA LÀM GIÓ', 'Thập niên 90 có thể nói là một thời kỳ đỉnh cao của ngành công
nghiệp thời trang Việt Nam. Bởi trong giai đoạn này, Việt Nam được chứng kiến sự
đổi mới và phát triển của nền kinh tế, văn hóa – xã hội, góp phần tạo nên sự đa dạng, phong phú hơn trong lối ăn mặc của người Việt. ', '2023-04-22 00:00:00', 'KHANG',
'120K', '100M'), (888, 'tt8.jpg', 'XU HƯỚNG THỜI TRANG CHO PHÁI ĐẸP QUA CÁC THỜI KỲ ',
'Những chiếc chân váy xòe dáng rộng và áo sơ mi là những item được phụ nữ của thời
đại này ưa chuộng và theo đuổi nhờ thiết kế thanh lịch. Sự bồng bềnh của những chiếc
váy đem lại một cảm giác mềm mại, nhẹ nhàng làm nổi bật nét nữ tính của phái đẹp.',
'2023-04-22 00:00:00', 'MR.VIET', '12K', '100M'), (889, 'tt9.jpg', 'MỆNH THỦY HỢP VỚI MÀU GÌ VÀ BÍ QUYẾT DIỆN ĐỒ THEO
MỆNH HỢP TREND', 'Xét theo nhân tướng học, phần lớn những người mệnh thủy
đều sở hữu khuôn mặt bầu bĩnh, đầy đặn. Chính lý do này khiến người mệnh thủy trẻ
lâu và tràn đầy sức sống hơn những mệnh còn lại. Nam nữ mệnh Thủy có chiều cao
tương đối cùng với sắc da hồng hào nên ở họ luôn toát ra nguồn năng lượng tích cực
lan tỏa đến mọi người xung quanh.', '2023-04-22 00:00:00', 'KHANG', '120K',
'100M'), (890, 'tt10.jpg', 'PHONG CÁCH ROCK LÀ GÌ? BỎ TÚI 7 CÁCH MIX ĐỒ CỰC
CHẤT', 'Phong cách rock là gì? Chắc hẳn đây là tên gọi không còn quá xa lạ với
những bạn trẻ yêu thích style trẻ trung, phá cách và có phần khá “gai góc”. Rock style
được hình thành từ sự kết hợp giữa nét cổ điển và phong cách hiện đại, tạo nên lối ăn
mặc cá tính nhưng vẫn đảm bảo sự thanh lịch và sang trọng.', '2023-06-04 00:00:00',
'ThanhTrucNguuyen', '550', '650k'); -- --------------------------------------------------------
--
-- Table structure for table `users`
--CREATE TABLE `users` (
`id_KH` int(100) NOT NULL,
`fullname` varchar(100) NOT NULL, `username` varchar(15) NOT NULL, `email` varchar(100) NOT NULL, `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci; --
-- Dumping data for table `users`
--
INSERT INTO `users` (`id_KH`, `fullname`, `username`, `email`, `password`)
VALUES
(1, 'Phạm Việt Hải', 'pvh123', 'pvh123@gmail.com', '123456789'), (2, 'Võ Hoàng Quân', 'vhq123', 'vhq123@gmail.com', '123456788'), (3, 'Nguyễn Thị Diệu Thảo', 'ntdt123', 'ntdt123@gmail.com', '123456787'), (4, 'Nguyễn Đoàn Hồng Sơn', 'ndhs123', 'ndhs123@gmail.com', '123456786'), (5, 'Nguyễn Thanh Hoa', 'nth123',
'ndhs123@gmail.comndhs123@gmail.comndhs123@gmail.comndhs123@gmail.com
ndhs123@gmail.comndhs123@gmail.c', '123456785'), (6, 'Võ Việt Hương', 'vvh123',
'ndhs123@gmail.comndhs123@gmail.comndhs123@gmail.comndhs123@gmail.com
ndhs123@gmail.comndhs123@gmail.c', '123456784'), (7, 'Nguyễn Thị Phương Thảo', 'ntpt123',
'ndhs123@gmail.comndhs123@gmail.comndhs123@gmail.comndhs123@gmail.com
ndhs123@gmail.com', '123456783'), (8, 'Tạ Cộng Hòa', 'tch123',
'ndhs123@gmail.comndhs123@gmail.comndhs123@gmail.comndhs123@gmail.com',
'123456782'), (9, 'Đặng Thị Thu Trang', 'dttt123',
'ndhs123@gmail.comndhs123@gmail.comndhs123@gmail.com', '123456781'), (10, 'Đào Thị Hồng', 'dth123', 'ndhs123@gmail.comndhs123@gmail.com',
'123456780'), (11, 'Phạm Hoàng Việt', 'phv123', 'ndhs123@gmail.com', '123456779'), (12, 'Nguyễn Công Dung', 'ncd2123', 'ndhs123@gmail.comndhs123@gmail.com',
'123456778'), (13, 'Trần Vân Anh', 'tva123', 'ndhs123@gmail.com', '123456777'), (14, 'Trần Khánh Linh', 'tkl123', 'ndhs123@gmail.comndhs123@gmail.com',
'123456776'), (15, 'Nguyễn Kim Bằng', 'nkb123', 'ndhs123@gmail.com', '123456775'), (16, 'Nguyễn Anh Đức', 'nad123', '', '123456774'), (17, 'Nguyễn Khánh Như', 'nkn123', 'ndhs123@gmail.com', '123456773'), (18, 'Lê Anh Đức', 'lad123', 'ndhs123@gmail.com', '123456772'), (19, 'Ngô Thị Thoa', 'ntt123', 'ndhs123@gmail.com', '123456771'), (20, 'Trần Công Chiến', 'tcc123', 'ndhs123@gmail.com', '123456770'), (21, 'Phan Thanh Sang', 'pts123', 'ndhs123@gmail.com', '123456769'), (22, 'Cao Văn Bình', 'cvb123', 'ndhs123@gmail.com', '123456768'), (23, 'Đỗ Kim Kha', 'dkk123', 'ndhs123@gmail.com', '123456767'), (24, 'Trương Đức Việt', 'tdv123', 'ndhs123@gmail.com', '123456766'), (25, 'Mai Thanh Hà', 'mth123', 'ndhs123@gmail.com', '123456765'),
(26, 'Vũ Hoàng Khải Đăng', 'vhkd123', 'ndhs123@gmail.com', '123456764'), (27, 'Phạm Khánh Huyền', 'pkh1233', 'ndhs123@gmail.com', '123456763'), (42, 'VHL', 'le123', 'ndhs123@gmail.com', '123'), (77, 'fullname', 'username', 'email',
'$2y$10$9X.84goIQwtqFPbEzn0qhemGzwQC6bbv.ifzWv6wV0W6tHjYMepYK'), (78, 'fullname', 'username', 'email',
'$2y$10$fcs7YWMNCrGJJqhTkHF3Yul.ix54O3KBZsu.y3mTiRvOebC90QJ9G'); --
-- Indexes for dumped tables
--
--
-- Indexes for table `tb_chitietdonhang`
--ALTER TABLE `tb_chitietdonhang` ADD PRIMARY KEY (`id`), ADD KEY `masp` (`masp`), ADD KEY `makhuyenmai` (`makhuyenmai`), ADD KEY `mahoadon` (`mahoadon`); --
-- Indexes for table `tb_danhmuc`
--ALTER TABLE `tb_danhmuc` ADD PRIMARY KEY (`id_dm`); --
-- Indexes for table `tb_donhang`
--ALTER TABLE `tb_donhang` ADD PRIMARY KEY (`id`); --
-- Indexes for table `tb_khachhang`
--ALTER TABLE `tb_khachhang` ADD PRIMARY KEY (`id`); --
-- Indexes for table `tb_khuyenmai`
--ALTER TABLE `tb_khuyenmai` ADD PRIMARY KEY (`id`); --
-- Indexes for table `tb_lienhe`
--ALTER TABLE `tb_lienhe` ADD PRIMARY KEY (`malienhe`);
--
-- Indexes for table `tb_quanlyphanhoi`
--ALTER TABLE `tb_quanlyphanhoi` ADD PRIMARY KEY (`id`); --
-- Indexes for table `tb_sanpham`
--ALTER TABLE `tb_sanpham` ADD PRIMARY KEY (`id`), ADD KEY `danhmuc_id` (`danhmuc_id`); --
-- Indexes for table `tb_tintuc`
--ALTER TABLE `tb_tintuc` ADD PRIMARY KEY (`id_tt`); --
-- Indexes for table `users`
--ALTER TABLE `users` ADD PRIMARY KEY (`id_KH`); --
-- AUTO_INCREMENT for dumped tables
--
--
-- AUTO_INCREMENT for table `tb_chitietdonhang`
--ALTER TABLE `tb_chitietdonhang` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13; --
-- AUTO_INCREMENT for table `tb_danhmuc`
--ALTER TABLE `tb_danhmuc` MODIFY `id_dm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1011; --
-- AUTO_INCREMENT for table `tb_donhang`
--ALTER TABLE `tb_donhang` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4011;
--
-- AUTO_INCREMENT for table `tb_khachhang`
--ALTER TABLE `tb_khachhang` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24; --
-- AUTO_INCREMENT for table `tb_lienhe`
--ALTER TABLE `tb_lienhe` MODIFY `malienhe` int(11) NOT NULL AUTO_INCREMENT; --
-- AUTO_INCREMENT for table `tb_sanpham`
--ALTER TABLE `tb_sanpham` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3033; --
-- AUTO_INCREMENT for table `tb_tintuc`
--ALTER TABLE `tb_tintuc` MODIFY `id_tt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=891; --
-- AUTO_INCREMENT for table `users`
--ALTER TABLE `users` MODIFY `id_KH` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79; --
-- Constraints for dumped tables
--
--
-- Constraints for table `tb_chitietdonhang`
--ALTER TABLE `tb_chitietdonhang` ADD CONSTRAINT `tb_chitietdonhang_ibfk_1` FOREIGN KEY (`masp`)
REFERENCES `tb_sanpham` (`id`), ADD CONSTRAINT `tb_chitietdonhang_ibfk_2` FOREIGN KEY (`makhuyenmai`)
REFERENCES `tb_khuyenmai` (`id`), ADD CONSTRAINT `tb_chitietdonhang_ibfk_3` FOREIGN KEY (`mahoadon`)
REFERENCES `tb_donhang` (`id`);
--
-- Constraints for table `tb_sanpham`
--ALTER TABLE `tb_sanpham` ADD CONSTRAINT `tb_sanpham_ibfk_1` FOREIGN KEY (`danhmuc_id`)
REFERENCES `tb_danhmuc` (`id_dm`);
COMMIT;
/*!40101 SET
CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET
CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET
COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */; 

- Sau khi import thì trong android, setting lại là Project để hiển thị tất cả
mục:
![image](https://github.com/user-attachments/assets/e0e9a7c8-ab9b-4e33-a37b-b7fb45ebb1a8)

- Vào app => src => main => java => Utils
- Chỉnh Utils lại theo ip của máy:
![image](https://github.com/user-attachments/assets/6308e219-e7eb-4a11-b2a9-40370da02c98)

- Check SDK, vào local.properties nằm gần cuối, chỉnh SDK theo vị trí
trong máy đã cài:
![image](https://github.com/user-attachments/assets/0d3cc52a-8488-45c1-bbd1-8c8960d54a44)

![image](https://github.com/user-attachments/assets/232a3152-e8d9-46b9-b55a-d76f2f10d4ab)

- Bật xampp và chạy app, done!
