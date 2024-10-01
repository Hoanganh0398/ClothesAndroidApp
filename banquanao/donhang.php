<?php
include "connect.php";
date_default_timezone_set('Asia/Ho_Chi_Minh'); // Set múi giờ hiện hành của csdl về múi giờ VN

// Khởi tạo
$hoten = $_POST['hoten'];
$email = $_POST['email'];
$sdt = $_POST['sdt'];
$diachi = $_POST['diachi'];
$tongtien = $_POST['tongtien'];
$thoidiemdathang = date('Y-m-d H:i:s');
$chitiet = $_POST['chitiet'];

// Thêm vào bảng đơn hàng
$query = "INSERT INTO `tb_donhang`(`hoten`, `email`, `sdt`, `diachi`, `ghichu`, `thoidiemdathang`, `tongtien`) VALUES ('$hoten', '$email', '$sdt', '$diachi', '', '$thoidiemdathang', '$tongtien')";
$data = mysqli_query($conn, $query);

if ($data) {
    $query = "SELECT `id` AS `iddonhang` FROM `tb_donhang` WHERE `email` = '$email' ORDER BY `id` DESC LIMIT 1";
    $data = mysqli_query($conn, $query);

    while ($row = mysqli_fetch_assoc($data)) {
        $iddonhang = $row['iddonhang'];
    }

    if (!empty($iddonhang)) {
        if (!is_null($chitiet)) {
            $chitiet = json_decode($chitiet, true);

            foreach ($chitiet as $key => $value) {
                $idsp = $value["id"];
                $soluong = $value["soluong"];
                $giasp = $value["price"];
                $truyvan = "INSERT INTO `tb_chitietdonhang`(`iddonhang`, `idsp`, `makhuyenmai`, `soluong`, `price`) VALUES ('$iddonhang', '$idsp', NULL, '$soluong', '$giasp')";
                $data = mysqli_query($conn, $truyvan);
            }

            if ($data) {
                $arr = [
                    'success' => true,
                    'message' => "thanh cong"     
                ];
            } else {
                $arr = [
                    'success' => false,
                    'message' => "khong thanh cong"
                ];
            }
        } else {
            $arr = [
                'success' => false,
                'message' => "chitiet is null"
            ];
        }

        echo json_encode($arr);
    }
} else {
    $arr = [
        'success' => false,
        'message' => "khong thanh cong"
    ];
    echo json_encode($arr);
}
?>