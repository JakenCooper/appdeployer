<%--
  Created by IntelliJ IDEA.
  User: jaken
  Date: 1/13/19
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
</head>
<body>
    <form action="/appdeployer/upload/upload2" method="post" enctype="multipart/form-data">
        pagename: <input type="text" name="pagename"/><br/>
        <input type="file" multiple="multiple" name="files"/>
        <input type="submit" value="提交！"/>
    </form>
</body>
</html>
