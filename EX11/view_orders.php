<?php

include "db.php";

$customer_name = $_POST["customer_name"];
$product_name = $_POST["product_name"];
$quantity = $_POST["quantity"];
$price = $_POST["price"];

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

5. view_orders.php
<?php

include "db.php";

$sql = "SELECT * FROM orders
        ORDER BY order_date DESC";

$result = $conn->query($sql);

?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
</head>

<body>

<h1>Order Details</h1>

<table border="1" cellpadding="10">

    <tr>
        <th>Order ID</th>
        <th>Customer Name</th>
        <th>Product</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Order Date</th>
    </tr>

<?php

if ($result->num_rows > 0) {

    while ($row = $result->fetch_assoc()) {

?>

    <tr>

        <td>
            <?php echo htmlspecialchars((string) $row["order_id"], ENT_QUOTES, "UTF-8"); ?>
        </td>

        <td>
            <?php echo htmlspecialchars((string) $row["customer_name"], ENT_QUOTES, "UTF-8"); ?>
        </td>

        <td>
            <?php echo htmlspecialchars((string) $row["product_name"], ENT_QUOTES, "UTF-8"); ?>
        </td>

        <td>
            <?php echo htmlspecialchars((string) $row["quantity"], ENT_QUOTES, "UTF-8"); ?>
        </td>

        <td>
            ₹<?php echo htmlspecialchars((string) $row["price"], ENT_QUOTES, "UTF-8"); ?>
        </td>

        <td>
            <?php echo htmlspecialchars((string) $row["order_date"], ENT_QUOTES, "UTF-8"); ?>
        </td>

    </tr>

<?php

    }

} else {

    echo "<tr>
            <td colspan='6'>
                No orders found
            </td>
          </tr>";

}

$conn->close();

?>

</table>

<br>

<a href="index.html">
    Place New Order
</a>

</body>
</html>
