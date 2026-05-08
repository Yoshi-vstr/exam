<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.html" %>

<h2>学生変更結果</h2>

<c:choose>
    <c:when test="${result}">
        更新に成功しました。
    </c:when>
    <c:otherwise>
        更新に失敗しました。
    </c:otherwise>
</c:choose>

<br><br>
<a href="#">メニューへ</a>

<%@ include file="../footer.html" %>