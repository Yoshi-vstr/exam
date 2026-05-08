<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../header.html" %>

<h2>学生情報変更</h2>

<form action="${pageContext.request.contextPath}/s/studentupdate" method="post">

    <div>
        <label>入学年度</label><br>
        <select name="entYear">
            <option value="2024" <c:if test="${student.entYear == 2024}">selected</c:if>>2024</option>
            <option value="2025" <c:if test="${student.entYear == 2025}">selected</c:if>>2025</option>
            <option value="2026" <c:if test="${student.entYear == 2026}">selected</c:if>>2026</option>
            <option value="2027" <c:if test="${student.entYear == 2027}">selected</c:if>>2027</option>
        </select>
    </div>

    <div>
        <label>学生番号</label><br>
        <input type="text" name="no" value="${student.no}" readonly />
    </div>

    <div>
        <label>氏名</label><br>
        <input type="text" name="name" value="${student.name}" required maxlength="30" />
    </div>

    <div>
        <label>クラス番号</label><br>
        <select name="classNum">
            <option value="1-1" <c:if test="${student.classNum == '1-1'}">selected</c:if>>1-1</option>
            <option value="1-2" <c:if test="${student.classNum == '1-2'}">selected</c:if>>1-2</option>
        </select>
    </div>

    <div>
        <label>在学中</label><br>
        <input type="checkbox" name="attend"
               <c:if test="${student.attend}">checked</c:if> />
    </div>

    <div>
        <label>学校コード</label><br>
        <c:out value="${student.school.code}" />
    </div>

    <div>
        <input type="submit" value="変更" />
    </div>

</form>

<br>

<a href="${pageContext.request.contextPath}/s/studentlist">戻る</a>

<%@ include file="../footer.html" %>