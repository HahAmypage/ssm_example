<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/2/9
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="box-footer">
    <div class="pull-left">
        <div class="form-group form-inline">
            总共${pageInfo.pages}页，共${pageInfo.total}条数据。 每页 <select class="form-control" onchange="gotoPage(1,this.value)">
            <c:forEach begin="1" end="10" step="2" var="num">
                <option value="${num}" ${pageInfo.pageSize == num ? 'selected':''}>${num}</option>
            </c:forEach>


        </select> 条
        </div>
    </div>

    <div class="box-tools pull-right">
        <ul class="pagination">
            <li><a href="javascript:gotoPage(1,'${pageInfo.pageSize}')" aria-label="Previous">首页</a></li>
            <li><a href="javascript:gotoPage('${pageInfo.prePage}','${pageInfo.pageSize}')">上一页</a></li>

            <c:choose>
                <%--判断总页数是否大于显示的页码--%>
                <c:when test="${pageInfo.pages <= 10}">
                    <c:forEach var="page" begin="1" end="${pageInfo.pages}">
                        <li><a href="javascript:gotoPage('${page}','${pageInfo.pageSize}')">
                            <c:choose>
                                <c:when test="${page == pageInfo.pageNum}">
                                    <span style="color: red">${page}</span>
                                </c:when>
                                <c:otherwise>${page}</c:otherwise>
                            </c:choose></a></li>
                    </c:forEach>
                </c:when>

                <%--头溢出--%>
                <c:when test="${ pageInfo.pages > 10 and pageInfo.pageNum -5 < 1 }">
                    <%--${pageInfo.pageNum -5}--%>
                    <c:forEach var="page" begin="1" end="10">
                        <li><a href="javascript:gotoPage('${page}','${pageInfo.pageSize}')">
                            <c:choose>
                                <c:when test="${page == pageInfo.pageNum}">
                                    <span style="color: red">${page}</span>
                                </c:when>
                                <c:otherwise>${page}</c:otherwise>
                            </c:choose></a></li>
                    </c:forEach>
                </c:when>


                <c:when test="${pageInfo.pages > 10 and pageInfo.pageNum - 5 >=1 and pageInfo.pageNum + 4 <= pageInfo.pages}">
                    <%--正常--%>
                    <c:forEach var="page" begin="${pageInfo.pageNum - 5}" end="${pageInfo.pageNum + 4}">
                        <%--${pageInfo.pageNum - 5} &nbsp;&nbsp;&nbsp;--%>
                        <%--${pageInfo.pageNum + 4}&nbsp;&nbsp;&nbsp;--%>
                        <%--${pageInfo.pages}--%>
                        <li><a href="javascript:gotoPage('${page}','${pageInfo.pageSize}')">
                            <c:choose>
                                <c:when test="${page == pageInfo.pageNum}">
                                    <span style="color: red">${page}</span>
                                </c:when>
                                <c:otherwise>${page}</c:otherwise>
                            </c:choose></a></li>
                    </c:forEach>
                </c:when>


                <c:otherwise>
                    <%--尾溢出--%>
                    <%--<c:when test="${ pageInfo.pages > 10 and pageInfo.pageNum + 4 > pageInfo.pages} ">--%>
                    <c:forEach var="page" begin="${pageInfo.pages - 9}" end="${pageInfo.pages}">

                        <li><a href="javascript:gotoPage('${page}','${pageInfo.pageSize}')">
                            <c:choose>
                                <c:when test="${page == pageInfo.pageNum}">
                                    <span style="color: red">${page}</span>
                                </c:when>
                                <c:otherwise>${page}</c:otherwise>
                            </c:choose></a></li>
                    </c:forEach>
                    <%--</c:when>--%>
                </c:otherwise>
            </c:choose>


            <li><a href="javascript:gotoPage('${pageInfo.nextPage}','${pageInfo.pageSize}')">下一页</a></li>
            <li><a href="javascript:gotoPage('${pageInfo.pages}','${pageInfo.pageSize}')" aria-label="Next">尾页</a></li>
        </ul>
    </div>

</div>


<script type="text/javascript">
    function gotoPage(pageNum,pageSize) {
        $("#pageNum").val(pageNum);
        $("#pageSize").val(pageSize);

        //获取第一个表单，并提交
        document.forms[0].submit();
    }
</script>
