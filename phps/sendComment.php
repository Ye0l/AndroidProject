<?

$name=$_POST["data"];

$arr = explode("/",$name);

$connect= mysqli_connect("localhost","klure","rain414^", "klure");

if(mysqli_connect_errno()){
  echo "DB connection failed" . mysqli_connect_error();
}

mysqli_set_charset($connect, "utf8");

$insertSQL="INSERT INTO comments_board_$arr[0](board_id, post_id, accounts_id, contents) values($arr[0], $arr[1], '$arr[2]', '$arr[3]')";
$selectResult = mysqli_query($connect, $insertSQL);

echo $insertSQL;
echo $selectResult;

mysqli_close($connect);



?>
