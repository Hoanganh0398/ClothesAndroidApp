<?php
include "connect.php";
$email = $_POST['email'];
$matkhau = $_POST['matkhau'];

$query = "SELECT * FROM `tb_khachhang` WHERE `email`='".$email."' AND `matkhau`='".$matkhau."'";
$data = mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
    $result[] = $row;
}

if (!empty($result)) {
    $arr = [
        'success' => true,
        'message' => "thanh cong",
        'result' => $result
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "Nhập sai email hoặc mật khẩu",
        'result' => $result
    ];
}

echo json_encode($arr);
?>