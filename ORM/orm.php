<?PHP
#
$username = "******";
$password = "***************";
$database = "bwprouddb";
$server = "classroom.cs.unc.edu";
$fPre ="F";
$lPre= "";
$studentId=112;
$conn = new mysqli($server, $username, $password, $database);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
if($studentId>=0){
    $sql = "select * from Student s where s.sid='$studentId'";
    $result=$conn->query($sql);
    if ($result) {
        while ($next_row = $result->fetch_array()) {
            echo $next_row['SID']." ".$next_row['FirstName']." ".$next_row['LastName']."\n";
        }
    }
}else{
    $sql = "select s.SID from Student s";
    $result=$conn->query($sql);
    if ($result) {
        while ($next_row = $result->fetch_array()) {
            echo $next_row['SID']."\n";
        }
    }
}
if($fPre!="" && $lPre!=""){
    $sql = "select * from Student s where s.FirstName LIKE '$fPre%' AND s.LastName LIKE '$lPre%'";
    $result=$conn->query($sql);
    if ($result) {
        while ($next_row = $result->fetch_array()) {
            echo $next_row['SID']." ".$next_row['FirstName']." ".$next_row['LastName']."\n";
        }
    }
}else if($fPre!=""){
    $sql = "select * from Student s where s.FirstName LIKE '$fPre%'";
    $result=$conn->query($sql);
    if ($result) {
        while ($next_row = $result->fetch_array()) {
            echo $next_row['SID']." ".$next_row['FirstName']." ".$next_row['LastName']."\n";
        }
    }
}else if($lPre!=""){
    $sql = "select * from Student s where s.LastName LIKE '$lPre%'";
    $result=$conn->query($sql);
    if ($result) {
        while ($next_row = $result->fetch_array()) {
            echo $next_row['SID']." ".$next_row['FirstName']." ".$next_row['LastName']."\n";
        }
    }
}else{}
$conn->close();
?>