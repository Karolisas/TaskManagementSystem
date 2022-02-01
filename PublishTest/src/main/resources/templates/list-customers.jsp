<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>asdfff</h1>
<body>
<div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
        </tr>
        <c:foreach var="tempCustomer" items="${customers}">
            <tr>
                <td>${tempCustomer.company}</td>
                <td>${tempCustomer.id}</td>
                <td>${tempCustomer.company}</td>
            </tr>

        </c:foreach>
    </table>
</div>
</body>