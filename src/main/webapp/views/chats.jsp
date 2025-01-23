<!DOCTYPE html>
<html>
<head>
    <title>Your Chats</title>
</head>
<body>
    <h1>Your Chats</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Date/Time</th>
                <th>From</th>
                <th>Message</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="message" items="${messages}">
                <tr>
                    <td>${message.timestamp}</td>
                    <td>${message.from}</td>
                    <td>${message.text}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>