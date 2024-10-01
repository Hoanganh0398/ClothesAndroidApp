<?php
include "connect.php";
$query = "SELECT * FROM `tb_donhang` ORDER BY `id` DESC LIMIT 1";
$data = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($data)) {
    $result[] = $row;
}

if (!empty($result)) {
    $arr = [
        'success' => true,
        'message' => "Thành công",
        'result' => $result
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "Không thành công",
        'result' => $result
    ];
}

echo json_encode($arr);
?>