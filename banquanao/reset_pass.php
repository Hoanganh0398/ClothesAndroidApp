<?php

include "connect.php";

if($_GET['key'] && $_GET['reset'])
{
  $email=$_GET['key'];
  $pass=$_GET['reset'];


    $query = "SELECT `email`,`matkhau` from `tb_khachhang` where `email`='".$email."' and matkhau='".$pass."'";
    $data = mysqli_query($conn, $query);

  if($data==true)
  {
    ?>
    <form method="post" action="submit_new.php">
    <input type="hidden" name="email" value="<?php echo $email;?>">
    <p>Enter New password</p>
    <input type="password" name='password'>
    <input type="submit" name="submit_password">
    </form>
    <?php
  }
}
?>