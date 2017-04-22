<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>Login</title>
</head>
<body>
	<s:form action="login.action">
		<s:textfield name="userName" label="User Name" />
		<s:password name="password" label="Password" />
		<s:submit value="Submit" />
	</s:form>
</body>
</html>