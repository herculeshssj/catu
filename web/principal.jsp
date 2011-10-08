<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <f:subview id="header">
        <jsp:include page="header.jsp" />
    </f:subview>

            <center>
               <img src="images/support.jpg" alt="logo" />
            </center>
            
    <f:subview id="footer">
        <jsp:include page="footer.jsp" />
    </f:subview>

</f:view>
