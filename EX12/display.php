<?php

$xml = simplexml_load_file(__DIR__ . "/books.xml")
       or die("Error: Cannot load XML file.");

?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Details</title>
</head>

<body>

<h1>Book Details</h1>

<table border="1" cellpadding="10">

    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Year</th>
        <th>Price</th>
    </tr>

<?php

foreach ($xml->book as $book) {

?>

    <tr>

        <td>
            <?php echo htmlspecialchars((string) $book->title, ENT_QUOTES, "UTF-8"); ?>
        </td>

        <td>
            <?php echo htmlspecialchars((string) $book->author, ENT_QUOTES, "UTF-8"); ?>
        </td>

        <td>
            <?php echo htmlspecialchars((string) $book->year, ENT_QUOTES, "UTF-8"); ?>
        </td>

        <td>
            <?php echo htmlspecialchars((string) $book->price, ENT_QUOTES, "UTF-8"); ?>
        </td>

    </tr>

<?php

}

?>

</table>

</body>
</html>
