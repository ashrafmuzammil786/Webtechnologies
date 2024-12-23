<?php
$servername = "127.0.0.1";
$username = "root";
$password = "root123";
$dbname = "boxing_tracker";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Add class
if (isset($_POST['submit'])) {
    $class_name = $_POST['class_name'];
    $date = $_POST['date'];
    $sql = "INSERT INTO boxing_classes (class_name, date) VALUES ('$class_name', '$date')";
    $conn->query($sql);
}

// Delete class
if (isset($_GET['delete_id'])) {
    $id = $_GET['delete_id'];
    $conn->query("DELETE FROM boxing_classes WHERE id=$id");
}

// Update class
if (isset($_POST['update'])) {
    $id = $_POST['id'];
    $class_name = $_POST['class_name'];
    $date = $_POST['date'];
    $conn->query("UPDATE boxing_classes SET class_name='$class_name', date='$date' WHERE id=$id");
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Boxing Classes Tracker</title>
    <style>
        body { font-family: 'Arial', sans-serif; background: #202020; color: #f0f0f0; margin: 0; padding: 0; }
        .container { width: 80%; margin: 0 auto; padding: 20px; }
        h1, h2 { color: #e53935; text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.6); }
        table { width: 100%; border-collapse: collapse; }
        table, th, td { border: 1px solid #e53935; }
        th, td { padding: 10px; text-align: left; }
        th { background-color: #c62828; color: white; }
        .btn { display: inline-block; padding: 10px 20px; background-color: #d32f2f; color: white; text-decoration: none; margin: 10px 0; border-radius: 5px; }
        .btn:hover { background-color: #b71c1c; }
        .form-container { background-color: #333; padding: 20px; border-radius: 5px; margin-top: 20px; }
        input[type="text"], input[type="date"] { padding: 10px; margin-bottom: 10px; width: 100%; border-radius: 5px; border: none; }
        button[type="submit"] { padding: 10px 20px; background-color: #d32f2f; color: white; border: none; border-radius: 5px; cursor: pointer; }
        button[type="submit"]:hover { background-color: #b71c1c; }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome to the Boxing Classes Tracker</h1>
    <p>Track your boxing training sessions, challenges, and goals.</p>
    <a href="#addClassForm" class="btn">Add New Class</a>

    <h2>Your Boxing Classes</h2>
    <table>
        <thead>
            <tr>
                <th>Class Name</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <?php
            $result = $conn->query("SELECT * FROM boxing_classes");
            while ($row = $result->fetch_assoc()) {
                echo "<tr>
                        <td>{$row['class_name']}</td>
                        <td>{$row['date']}</td>
                        <td>
                            <a href='?edit_id={$row['id']}' class='btn'>Edit</a> | 
                            <a href='?delete_id={$row['id']}' class='btn'>Delete</a>
                        </td>
                    </tr>";
            }
            ?>
        </tbody>
    </table>

    <?php if (isset($_GET['edit_id'])):
        $id = $_GET['edit_id'];
        $result = $conn->query("SELECT * FROM boxing_classes WHERE id=$id");
        $class = $result->fetch_assoc();
    ?>
    <h2>Edit Boxing Class</h2>
    <div class="form-container">
        <form method="POST">
            <input type="hidden" name="id" value="<?= $class['id'] ?>">
            <label for="class_name">Class Name:</label>
            <input type="text" id="class_name" name="class_name" value="<?= $class['class_name'] ?>" required>
            <label for="date">Date:</label>
            <input type="date" id="date" name="date" value="<?= $class['date'] ?>" required>
            <button type="submit" name="update">Update Class</button>
        </form>
    </div>
    <?php endif; ?>

    <h2 id="addClassForm">Add New Boxing Class</h2>
    <div class="form-container">
        <form method="POST">
            <label for="class_name">Class Name:</label>
            <input type="text" id="class_name" name="class_name" required>
            <label for="date">Date:</label>
            <input type="date" id="date" name="date" required>
            <button type="submit" name="submit">Add Class</button>
        </form>
    </div>
</div>

</body>
</html>

<?php
$conn->close();
?>
