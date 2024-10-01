<?php
include "connect.php";
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require 'PHPMailer/src/Exception.php';
require 'PHPMailer/src/PHPMailer.php';
require 'PHPMailer/src/SMTP.php';

$email = $_POST['email'];
$query = "SELECT * FROM `tb_khachhang` WHERE `email`='".$email."'";
$data = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($data)) {
    $result[] = $row;
}

if (empty($result)) {
    $arr = [
        'success' => false,
        'message' => "Email không chính xác",
        'result' => $result
    ];
} else {
    $email = $result[0]['email'];
    $pass = $result[0]['matkhau'];

    $link = "<a href='http://192.168.1.102/banquanao/reset_pass.php?key=".$email."&reset=".$pass."'>Click để đặt lại mật khẩu</a>";

    $mail = new PHPMailer();
    $mail->CharSet = "utf-8";
    $mail->IsSMTP();
    $mail->SMTPAuth = true;
    $mail->Username = "moly0398@gmail.com";
    $mail->Password = "moly1234#";
    $mail->SMTPSecure = "ssl";
    $mail->Host = "smtp.gmail.com";
    $mail->Port = "465";
    $mail->From = "2154810102@vaa.edu.vn";
    $mail->FromName = 'App bán hàng';
    $mail->AddAddress($email, 'receiver_name');
    $mail->Subject = 'Đặt lại mật khẩu';
    $mail->IsHTML(true);
    $mail->Body = $link;

    if($mail->Send()) {
        $arr = [
            'success' => true,
            'message' => "Vui lòng kiểm tra email",
            'result' => $result
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => "Gửi không thành công",
            'result' => $result
        ];
    }
}

echo json_encode($arr);
?>