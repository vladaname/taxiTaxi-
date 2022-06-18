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

<div class="jumbotron text-center">
    <p>Vase ime je: ${korisnik.ime}</p>
    <p>Vasa uloga je: ${korisnik.uloga.nazivUloga}</p>


    <h1>Free Driving List</h1>
    <p>We specialize in blablabla</p>
    <%--@elvariable id="pricelistDto" type=""--%>
    <div class="form-group">

        <table class = "table">
            <tr>
                <td>vreme</td>
                <td>adresa start</td>
                <td>adresa cilj</td>
                <td>tel</td>
                <td>dodeli voznju</td>

            </tr>
            <c:forEach var="voznjaStart" items="${listaSlobodnihVoznji}">
                <tr>
                    <td>${voznjaStart.vremePolazak}</td>
                    <td>${voznjaStart.adresaPolazak}</td>
                    <td>${voznjaStart.adresaCilj}</td>
                    <td>${voznjaStart.telefonMob}</td>
                    <td>
                    <%--@elvariable id="zauzmiVoznjuDto" type=""--%>
                    <form:form method="post" action="/promeniStatus" modelAttribute="zauzmiVoznjuDto">
                        <input type="hidden" name="idVoznja" value="${voznjaStart.idVoznja}">
                        <input type="submit" class="btn btn-default" value="promeniStatus">
                    </form:form>
                     </td>
                </tr>

            </c:forEach>
        </table>

    </div>
