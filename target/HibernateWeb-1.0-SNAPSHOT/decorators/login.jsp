<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value='/template/login/css/loginStyle.css'/>">
  <link rel="stylesheet" href="<c:url value='/template/login/css/bootstrap.min.css'/>">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
  <link rel="stylesheet" href="<c:url value='/template/login/js/jquery.min.js'/>">
  <title>Login</title>
</head>

<body>
<div class="container">
  <div class="row">
    <div class="col-md-6">
      <div class="card">
        <form class="box" action="login?action=signin" method="post">
          <h1>Login</h1>
          <c:if test="${mess != null}">
            <p class="text-muted"> ${mess}</p>
          </c:if>
          <c:if test="${mess == null}">
            <p class="text-muted"> Please enter your login and password!</p>
          </c:if>
          <input type="text" name="email" placeholder="Email" />
          <input type="password" name="password" placeholder="Password" />
          <a class="forgot text-muted" href="#">Forgot password?</a>
          <input type="submit" value="Login">
          <div class="col-md-12">
            <ul class="social-network social-circle">
              <li><a href="#" class="icoFacebook" title="Facebook"><i class="fab fa-facebook-f"></i></a></li>
              <li><a href="#" class="icoTwitter" title="Twitter"><i class="fab fa-twitter"></i></a></li>
              <li><a href="#" class="icoGoogle" title="Google +"><i class="fab fa-google-plus"></i></a></li>
            </ul>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>