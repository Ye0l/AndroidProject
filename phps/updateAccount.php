<?

$name=$_POST["data"];

$arr = explode("/",$name);

$connect= mysqli_connect("localhost","klure","rain414^", "klure");

if(mysqli_connect_errno()){
  echo "DB connection failed" . mysqli_connect_error();
}

mysqli_set_charset($connect, "utf8");

$insertSQL="update accounts set nickname='$arr[1]' where id='$arr[0]';";
echo $insertSQL;
$result = mysqli_query($connect, $insertSQL);
echo $result;
$insertSQL="update accounts set introduce='$arr[2]' where id='$arr[0]';";
echo $insertSQL;
$result = mysqli_query($connect, $insertSQL);
echo $result;

mysqli_close($connect);



?>
