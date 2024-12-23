<?php
$xmlFile = "tips.xml";

if (file_exists($xmlFile)) {
    $xml = simplexml_load_file($xmlFile);
} else {
    die("Error: XML file not found!");
}

echo "<!DOCTYPE html>";
echo "<html lang='en'>";
echo "<head>";
echo "<meta charset='UTF-8'>";
echo "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
echo "<title>Eco-Friendly Tips</title>";
echo "<style>";
echo "body { font-family: Arial, sans-serif; background-color: #e0f7fa; margin: 20px; }";
echo "h1 { color: #008080; }";
echo ".tip { border: 1px solid #ccc; border-radius: 5px; padding: 15px; margin: 10px 0; background: #f9f9f9; }";
echo ".tip h2 { color: #00695c; }";
echo "</style>";
echo "</head>";
echo "<body>";

echo "<h1>Eco-Friendly Tips</h1>";

foreach ($xml->tip as $tip) {
    echo "<div class='tip'>";
    echo "<h2>" . htmlspecialchars($tip->title) . "</h2>";
    echo "<p>" . htmlspecialchars($tip->description) . "</p>";
    echo "</div>";
}

echo "</body>";
echo "</html>";
?>
