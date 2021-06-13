<?

$name=$_POST["data"];

$arr = explode("/",$name);

$connect= mysqli_connect("localhost","klure","rain414^", "klure");

if(mysqli_connect_errno()){
  echo "DB connection failed" . mysqli_connect_error();
}

mysqli_set_charset($connect, "utf8");

$insertSQL="insert into accounts(id, password, nickname, name, age, phone) values('$arr[0]','$arr[1]','$arr[2]','$arr[3]',$arr[4],'$arr[5]')";

echo "SQL:";
echo $insertSQL . "\n";

$result = mysqli_query($connect, $insertSQL);

echo $result;

mysqli_close($connect);



?>
