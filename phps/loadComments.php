<?

$name=$_POST["data"];

$arr = explode("/",$name);

$connect= mysqli_connect("localhost","klure","rain414^", "klure");

if(mysqli_connect_errno()){
  echo "DB connection failed" . mysqli_connect_error();
}

mysqli_set_charset($connect, "utf8");

$insertSQL="SELECT ACCOUNTS_ID, (SELECT NICKNAME FROM accounts WHERE ID = accounts_id) as 'NICK', DATE, CONTENTS FROM comments_board_$arr[0] WHERE BOARD_ID = $arr[0] AND POST_ID = $arr[1]";

$result = mysqli_query($connect, $insertSQL);

while($row = @mysqli_fetch_assoc($result)){
  echo $row["ACCOUNTS_ID"];
  echo "/";
  echo $row["NICK"];
  echo "/";
  echo $row["DATE"];
  echo "/";
  echo $row["CONTENTS"];
  echo "\n";
}

mysqli_close($connect);



?>
