<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>Welcome, ${username}!</h2>
    <p>You have successfully logged in.</p>
    <form action="/logout" method="get">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
