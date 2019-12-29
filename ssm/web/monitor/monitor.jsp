<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>通话记录</title>
    <link rel="stylesheet" type="text/css" href="../css/my.css">
    <script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript">

        //定义函数
        function getMonitorInfo() {
            $("#t1 tbody").empty();
            $.getJSON("/json/monitor/getMonitorInfo",function (data) {
               $("#div_1").append(data);

            });
        }

        $(function () {
            setInterval(getMonitorInfo, 2000);
        })
    </script>

</head>
<body>
    <div id="div_1" style="border:1px solid blue;width: 400px ; height: 300px">

    </div>
</body>
</html>