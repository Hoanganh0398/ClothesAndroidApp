<?php
include "connect.php";
$email = $_POST['email'];


$query = "SELECT * FROM `tb_donhang` WHERE `email` = '$email'";
$data = mysqli_query($conn,$query);
$result = array();
while ($row = mysqli_fetch_assoc($data)){
        $truyvan = 'SELECT * FROM `tb_chitietdonhang` INNER JOIN tb_sanpham ON tb_chitietdonhang.idsp = tb_sanpham.id WHERE tb_chitietdonhang.iddonhang ='.$row['id'];
        $data1 = mysqli_query($conn,$truyvan);
    $item = array();
     while ($row1 = mysqli_fetch_assoc($data1)) {
        $item[] = $row1;
    }
    $row['item'] = $item;
    $result[] = ($row);
}

if (!empty($result)){
    $arr = [
        'success' => true,
        'message' => "thanh cong",
        'result' => $result
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "khong thanh cong",
        'result' => $result
    ];
}

print_r(json_encode($arr))
?>