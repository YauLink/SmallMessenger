<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chats</title>
</head>
<body>
    <h2>Your Messages</h2>

    <table border="1">
        <tr>
            <th>From</th>
            <th>Message</th>
            <th>Timestamp</th>
        </tr>
        <c:choose>
            <c:when test="${not empty messages}">
                <c:forEach var="msg" items="${messages}">
                    <tr>
                        <td>${msg.from}</td>
                        <td>${msg.text}</td>
                        <td>${msg.timestamp}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="3">No messages found.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>

    <br>
    <a href="message.jsp">Send a new message</a> |
    <a href="logout">Logout</a>
</body>
</html>
