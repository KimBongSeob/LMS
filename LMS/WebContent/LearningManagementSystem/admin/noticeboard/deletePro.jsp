<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${check==1}">
	<meta http-equiv="Refresh" content="0;url=/LMS/LearningManagementSystem/admin/noticeboard/list.do?pageNum=${pageNum}">
</c:if>