<html>
<head>
<title>test 测试</title>
</head>
<body>
	<h1>
		Welcome ${user}<#if user == "Big Joe"> , our beloved leader</#if>!
	</h1>
	<p>Our latest product:
	${late.userName}<br>
	${late.password}
	</p>
</body>
</html>