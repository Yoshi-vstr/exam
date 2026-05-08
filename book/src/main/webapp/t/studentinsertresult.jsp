<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.html" %>

<c:choose>
    <c:when test="${result}">
        登録に成功しました。
    </c:when>
    <c:otherwise>
        登録に失敗しました。
    </c:otherwise>
</c:choose>

<br><br>
<a href="#">メニューへ</a>
<a href="${pageContext.request.contextPath}/s/studentlist">一覧へ戻る</a>
<%@ include file="../footer.html" %>