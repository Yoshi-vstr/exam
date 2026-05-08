<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../header.html" %>

<h2>学生登録</h2>

<form action="${pageContext.request.contextPath}/s/studentinsert" method="post">

    <div>
        <label>入学年度</label>
        <select name="entYear">
            <option value="0">--------</option>
            <option value="2024">2024</option>
            <option value="2025">2025</option>
            <option value="2026">2026</option>
            <option value="2027">2027</option>
            <option value="2028">2028</option>
        </select>
    </div>

    <div style="color:red;">
        ${errors.entYear}
    </div>

    <div>
        <label>学生番号</label><br>
        <input type="text" name="no" value="${no}" required maxlength="10"
               placeholder="学生番号を入力してください" />
    </div>

    <div style="color:red;">
        ${errors.no}
    </div>

    <div>
        <label>氏名</label><br>
        <input type="text" name="name" value="${name}" required maxlength="30"
               placeholder="氏名を入力してください" />
    </div>

    <div style="color:red;">
        ${errors.name}
    </div>

    <div>
        <label>クラス</label>
        <select name="classNum">
    <c:forEach var="num" items="${['1-1','1-2']}">
        <option value="${num}">${num}</option>
    </c:forEach>
</select>
    </div>

    <div>
        <button type="submit">登録して終了</button>
    </div>

</form>

<br>

<a href="${pageContext.request.contextPath}/s/studentlist">戻る</a>

<%@ include file="../footer.html" %>