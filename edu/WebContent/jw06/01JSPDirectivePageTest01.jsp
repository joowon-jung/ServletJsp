<!-- JSP page directive Element ���� -->

<%@ page contentType = "text/html;charset=euc-kr" %>
<%@ page import = "java.util.*" %>

<%@ page language = "java" %>
<%@ page info = "ó�� �ۼ��ϴ� JSP" %>

<%@ page session = "true" %>
<%@ page isThreadSafe = "true" %>

<%--
	JSP �ּ� : JSP Container�� java File ��ȯ �� �ּ� �ν� X
	<%@ page extends = "java.util.Vector" %>
--%>

<%
	String value = "��������";
	System.out.println(":: "+value);

	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	System.out.println(":: "+ year + "��");

	String info = getServletInfo();
	System.out.println(":: information : "+ info);
%>

<HTML>
 <HEAD></HEAD>

 <BODY>
   �ȳ��ϼ��� html ���� <br/>
   <hr/>
   <%= " :: " + value %><br/>
   <%= " :: " + year + "��" %><br/>
   <%= " :: information : " + info %><br/>
   <hr/>
   �ȳ��ϼ��� html �� <br/>
 </BODY>
</HTML>
