<?

$ID=$_POST["groupID"];

$connect= mysqli_connect("localhost","klure","rain414^", "klure");

if(mysqli_connect_errno()){
  echo "DB connection failed" . mysqli_connect_error();
}

mysqli_set_charset($connect, "utf8");

$insertSQL="select * from group_board where group_id = $ID";

$result = mysqli_query($connect, $insertSQL);

while($row = @mysqli_fetch_assoc($result)){
  echo $row["id"];
  echo "/";
  echo $row["name"];
  echo "\n";
}

mysqli_close($connect);



?>
