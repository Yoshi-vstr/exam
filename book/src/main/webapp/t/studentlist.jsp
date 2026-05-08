<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../header.html" %>

<h2>学生管理</h2>

<div>
    <a href="${pageContext.request.contextPath}/t/studentinsert.jsp">新規登録</a>
</div>

<br>

<form method="get">

    <label>入学年度</label>
    <select name="entYear">
        <option value="0">--------</option>
        <option value="2024">2024</option>
        <option value="2025">2025</option>
        <option value="2026">2026</option>
        <option value="2027">2027</option>
    </select>

    <label>クラス</label>
    <select name="classNum">
        <option value="0">--------</option>
        <option value="1-1">1-1</option>
        <option value="1-2">1-2</option>
    </select>

    <label>在学中</label>
    <input type="checkbox" name="attend"/>

    <button type="submit">絞込み</button>

</form>

<br>

<c:choose>

    <c:when test="${list.size() > 0}">

        <div>検索結果：${list.size()}件</div>

        <table border="1">

            <tr>
                <th>入学年度</th>
                <th>学生番号</th>
                <th>氏名</th>
                <th>クラス</th>
                <th>在学中</th>
                <th>学校コード</th>
                <th></th>
            </tr>

            <c:forEach var="stu" items="${list}">
                <tr>
                    <td>${stu.entYear}</td>
                    <td>${stu.no}</td>
                    <td>${stu.name}</td>
                    <td>${stu.classNum}</td>

                    <td>
                        <c:choose>
                            <c:when test="${stu.attend}">
                                ○
                            </c:when>
                            <c:otherwise>
                                ×
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>${stu.school.code}</td>

                    <td>
                        <a href="${pageContext.request.contextPath}/s/studentupdate?no=${stu.no}">
                            変更
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </c:when>

    <c:otherwise>
        <div>学生情報が存在しませんでした。</div>
    </c:otherwise>

</c:choose>

<%@ include file="../footer.html" %>