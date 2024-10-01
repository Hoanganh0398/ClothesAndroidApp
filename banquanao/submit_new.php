<?php
include "connect.php";

$email = $_POST['email'];
$pass = $_POST['password'];

$query = "SELECT * FROM `tb_khachhang` WHERE `email` = '".$email."'";
$data = mysqli_query($conn, $query);

if (!$data) {
    $arr = [
        'success' => false,
        'message' => "Lỗi truy vấn: " . mysqli_error($conn),
    ];
} else {
    $numrow = mysqli_num_rows($data);

    if ($numrow == 0) {
        $arr = [
            'success' => false,
            'message' => "Email không tồn tại",
        ];
    } else {
        $row = mysqli_fetch_assoc($data);
        $storedPass = $row['matkhau'];

        if ($pass == $storedPass) {
            $arr = [
                'success' => false,
                'message' => "Bạn đã nhập mật khẩu cũ",
            ];
        } else {
            $query = "UPDATE tb_khachhang SET matkhau='$pass' WHERE email='$email'";
            $updateResult = mysqli_query($conn, $query);

            if ($updateResult) {
                $arr = [
                    'success' => true,
                    'message' => "Thay đổi mật khẩu thành công",
                ];
            } else {
                $arr = [
                    'success' => false,
                    'message' => "Không thể thay đổi mật khẩu: " . mysqli_error($conn),
                ];
            }
        }
    }
}

echo json_encode($arr);
?>