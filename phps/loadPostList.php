<?

$ID=$_POST["groupID"];

$connect= mysqli_connect("localhost","klure","rain414^", "klure");

if(mysqli_connect_errno()){
  echo "DB connection failed" . mysqli_connect_error();
}

mysqli_set_charset($connect, "utf8");

$insertSQL="select title, (select nickname from accounts where id = accounts_id) as 'accounts_nickname', accounts_id, date, contents from post_board_$ID";

$result = mysqli_query($connect, $insertSQL);

while($row = @mysqli_fetch_assoc($result)){
  echo $row["title"];
  echo "/";
  echo $row["accounts_nickname"];
  echo "/";
  echo $row["accounts_id"];
  echo "/";
  echo $row["date"];
  echo "/";
  echo $row["contents"];
  echo "\n";
}

mysqli_close($connect);



?>
