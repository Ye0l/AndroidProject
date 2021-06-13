<?

$name=$_POST["data"];

$arr = explode("/",$name);

$connect= mysqli_connect("localhost","klure","rain414^", "klure");

if(mysqli_connect_errno()){
  echo "DB connection failed" . mysqli_connect_error();
}

mysqli_set_charset($connect, "utf8");

$insertSQL="select * from accounts where id = '$arr[0]'";
$selectResult = mysqli_query($connect, $insertSQL);

while($row = @mysqli_fetch_assoc($selectResult)){
  echo $row["id"];
  echo "/";
  echo $row["password"];
  echo "/";
  echo $row["nickname"];
  echo "/";
  echo $row["name"];
  echo "/";
  echo $row["age"];
  echo "/";
  echo $row["phone"];
  echo "/";
  echo $row["introduce"];
}

mysqli_close($connect);



?>
