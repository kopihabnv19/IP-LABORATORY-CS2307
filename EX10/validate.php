<?php
if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    header("Location: register.html");
    exit;
}


$password = (string) ($_POST["password"] ?? "");
$card = (string) ($_POST["card"] ?? "");
$email = (string) ($_POST["email"] ?? "");
$phone = (string) ($_POST["phone"] ?? "");

$errors = array();


// Password validation
// Minimum 6 characters
if (!preg_match("/^.{6,}$/", $password)) {

    $errors[] = "Password must contain at least 6 characters.";

}


// Credit Card validation
// Exactly 16 digits
if (!preg_match("/^[0-9]{16}$/", $card)) {

    $errors[] = "Credit Card Number must contain 16 digits.";

}


// Email validation
if (!preg_match(
    "/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/",
    $email
)) {

    $errors[] = "Invalid Email Format.";

}


// Phone validation
// Exactly 10 digits
if (!preg_match("/^[0-9]{10}$/", $phone)) {

    $errors[] = "Phone Number must contain 10 digits.";

}


if (count($errors) == 0) {

    echo "<h2>Registration Successful</h2>";

    echo "Email: " . htmlspecialchars($email);

    echo "<br>Phone Number: "
         . htmlspecialchars($phone);

}
else {

    echo "<h2>Validation Errors</h2>";

    foreach ($errors as $error) {

        echo $error . "<br>";

    }

}

?>
