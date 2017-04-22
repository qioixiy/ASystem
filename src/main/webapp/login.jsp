<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>Login</title>
</head>
<body>
	<s:form action="login">
		<s:textfield name="userName" label="User Name" />
		<s:password name="password" label="Password" />
		<s:submit value="Submit" />
	</s:form>
	<s:form action="loginAsystem">
		<s:textfield name="userName" label="User Name" />
		<s:password name="password" label="Password" />
		<s:submit value="Submit" />
	</s:form>
</body>
</html>