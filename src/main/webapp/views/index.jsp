<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <jsp:include page="shared/css.jsp"/>
</head>

  <body ng-app="app">
    <ks-navbar></ks-navbar>
    <div>
      <div ui-view></div>
    </div>
    <h3><a href="/downloadPDF">Download PDF Document</a></h3>
    <h3><a href="/downloadExcel">Download Excel Document</a></h3>
  </body>
<jsp:include page="shared/componentsFiles.jsp"/>
<jsp:include page="shared/appFiles.jsp"/>
</html>