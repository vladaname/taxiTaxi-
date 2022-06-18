<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Theme Made By www.w3schools.com -->
    <title>TaxiTaxi</title>
    <%--    <link rel="stylesheet" href="/src/main/resources/static/main.css">--%>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/main.css">


</head>

<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#myPage">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#about">ABOUT</a></li>
                <li><a href="#services">SERVICES</a></li>
                <li><a href="#portfolio">PORTFOLIO</a></li>
                <li><a href="#pricing">PRICING</a></li>
                <li><a href="#contact">CONTACT</a></li>
            </ul>
        </div>
    </div>
</nav>
ABC ${korisnik.ime}
<div class="jumbotron text-center">
    <h1>Create Price</h1>
    <p>We specialize in blablabla</p>
    <%--@elvariable id="pricelistDto" type=""--%>
    <form:form method="post" action="/createCenovnik" modelAttribute="pricelistDto" >
    <div class="form-group">

        <div class="form-group">
            <label class="control-label col-sm-2" for="cenaDan">Day price:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="cenaDan" id="cenaDan" placeholder="Day price">
            </div>
        </div>
        <br><br>
        <div class="form-group">
            <label class="control-label col-sm-2" for="cenaNoc">Night price:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="cenaNoc" id="cenaNoc" placeholder="Night price">
            </div>
        </div>
        <br><br>
        <div class="form-group">
            <label class="control-label col-sm-2" for="cenaStart">Start price:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="cenaStart" id="cenaStart" placeholder="Start price">
            </div>
        </div>
        <br><br>
        <h3>${message}</h3>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
        </form:form>
    </div>
