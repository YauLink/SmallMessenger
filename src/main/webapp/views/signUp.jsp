<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <h1>Sign Up</h1>
    <form action="signUp" method="POST">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" required><br>

        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" required><br>

        <button type="submit">Sign Up</button>
    </form>
</body>
</html>
