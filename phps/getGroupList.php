<?

$connect= mysqli_connect("localhost","klure","rain414^", "klure");

if(mysqli_connect_errno()){
  echo "DB connection failed" . mysqli_connect_error();
}

mysqli_set_charset($connect, "utf8");

$insertSQL="select id, name, introduce, (select count(*) from group_member where group_id = gp.id) memberCount from `group` gp";

$result = mysqli_query($connect, $insertSQL);

while($row = @mysqli_fetch_assoc($result)){
  echo $row["id"];
  echo "/";
  echo $row["name"];
  echo "/";
  echo $row["introduce"];
  echo "/";
  echo $row["memberCount"];
  echo "\n";
}

mysqli_close($connect);



?>
