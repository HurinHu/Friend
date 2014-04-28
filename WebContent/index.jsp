<!-- 
                                                                                      
 __  |   |        __  o  __  |__  _|_       __   ___    __  ___   __        ___   __| 
(__( |_, |_,     |  ' | (__| |  )  |_,     |  ' (__/_ __)  (__/_ |  ' (__| (__/_ (__| 
                         __/                                                          
 
-->

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="http://www.iceloof.com">
    <meta name="author" content="Hurin">

    <title>Final Year Project Coordination System - Login</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/dashboard.js"></script>
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">FYPCS</a>
        </div>      
      </div>
    </div>

    <div class="container-fluid">
    	<br />
      	<div class="well well-lg" style="min-height:500px;">
      		<div class="container" style="margin-top:10%;">
	      	<form class="form-signin" role="form" action="login" method="post">
        		<h2 class="form-signin-heading">Please sign in</h2>
        		<br />
        		<p class="text-danger"><%if(session.getAttribute("errormessage")!=null){out.println(session.getAttribute("errormessage"));}%></p>
        		<div class="row">
        			<div class="col-md-4">
        			<div style="padding-top:5pt;"><font size="4em"><strong>User</strong></font></div>
        		</div>
        		<div class="col-md-8">
	        		<input type="text" name="username" class="form-control" placeholder="User Name" required autofocus>
        		</div>
        		<div class="col-md-4">
        			<div style="padding-top:5pt;"><font size="4em"><strong>Password</strong></font></div>
        		</div>
        		<div class="col-md-8">
	        		<input type="password" name="password" class="form-control" placeholder="Password" required>
        		</div>
        	</div>
        	<input type="hidden" name="status" value="login">
        	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      	</form>
      		</div>	
      	</div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="js/bootstrap.min.js"></script>
    <script src="js/docs.min.js"></script>
  </body>
</html>

<!-- Code by Hurin, April 25, 2014 -->