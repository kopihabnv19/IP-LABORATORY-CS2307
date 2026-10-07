<?php
if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    header("Location: index.html");
    exit;
}




$customer_name = trim((string) ($_POST["customer_name"] ?? ""));
$product_name = (string) ($_POST["product_name"] ?? "");
$quantity = filter_input(INPUT_POST, "quantity", FILTER_VALIDATE_INT);
$price = filter_input(INPUT_POST, "price", FILTER_VALIDATE_FLOAT);
if ($customer_name === "" || strlen($customer_name) > 50 || !in_array($product_name, ["Laptop", "Mobile", "Headphone"], true) || $quantity === false || $quantity === null || $quantity < 1 || $price === false || $price === null || $price < 0) {
    http_response_code(400);
    exit("Enter valid order details.");
}
require __DIR__ . "/db.php";

$sql = "INSERT INTO orders
        (customer_name, product_name, quantity, price)
        VALUES (?, ?, ?, ?)";

$stmt = $conn->prepare($sql);

$stmt->bind_param(
    "ssid",
    $customer_name,
    $product_name,
    $quantity,
    $price
);

if ($stmt->execute()) {

    echo "<h2>Order Placed Successfully!</h2>";

    echo "<a href='view_orders.php'>
            View Orders
          </a>";

} else {

    echo "Error: " . $stmt->error;

}

$stmt->close();
$conn->close();

?>
