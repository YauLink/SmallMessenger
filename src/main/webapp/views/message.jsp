<!DOCTYPE html>
<html>
<head>
    <title>Send a Message</title>
</head>
<body>
    <h1>Send a Message</h1>
    <form action="message" method="post">
        <label for="to">Send To (Login):</label>
        <input type="text" id="to" name="to" required><br>

        <label for="text">Message:</label>
        <textarea id="text" name="text" required></textarea><br>

        <button type="submit">Send</button>
    </form>
</body>
</html>
