<?php
include "connect.php";
$page = $_POST['page'];
$total = 10; //số sp trên 1 trang
$pos = ($page-1)*$total;
$loai = $_POST['loai'];
$query = "SELECT id, danhmuc_id, tensp, price, hinhanh, mota FROM `tb_sanpham` WHERE `danhmuc_id` = '".$loai."' LIMIT ".$pos.", ".$total;
$data = mysqli_query($conn,$query);
$result = array();
while ($row = mysqli_fetch_assoc($data)){
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