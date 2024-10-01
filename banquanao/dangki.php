<?php
include "connect.php";
$hoten = $_POST['hoten'];
$email = $_POST['email'];
$sdt = $_POST['sdt'];
$matkhau = $_POST['matkhau'];

$query = "SELECT * FROM `tb_khachhang` WHERE `email` = '".$email."'";
$data = mysqli_query($conn, $query);
$numrow = mysqli_num_rows($data);
if ($numrow > 0) {
    $arr = [
        'success' => true,
        'message' => "Email đã tồn tại",
    ];
} else {
    $insertQuery = "INSERT INTO `tb_khachhang` (`Hoten`, `email`, `sdt`, `matkhau`) VALUES ('$hoten', '$email', '$sdt', '$matkhau')";
    $insertData = mysqli_query($conn, $insertQuery);

    if ($insertData) {
        $arr = [
            'success' => true,
            'message' => "Thành công",
            'result' => []
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => "Không thành công",
            'result' => []
        ];
    }
}

echo json_encode($arr);
?>