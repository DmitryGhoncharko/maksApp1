<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<html>
<head>
    <title>Добавить машину</title>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
</head>
<body>
<div class="container-fluid">
    <form action="/controller?command=addCar" method="post" enctype="multipart/form-data">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <header>
                        <jsp:include page="header.jsp"></jsp:include>
                    </header>
                </div>
            </div>
            <div class="row" style="padding-right: 20%;padding-left: 40%; padding-top: 5%; padding-bottom: 5%">
                <div class="col-md-12">
                    <label>Название машины</label>
                    <input type="text" name="carName" >
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-6" style="padding-left: 10%; padding-right: 5%; padding-bottom: 5%; padding-top: 5%">
                            <label>Фото машины</label>
                            <input type="file" name="carImage" >
                        </div>
                        <div class="col-md-6" style="padding-left: 10%; padding-right: 5%; padding-bottom: 5%; padding-top: 5%">
                            <label>Описание машины</label>
                            <input type="text" name="carDescription" style="width: 100px; height: 100px">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" style="padding-left: 40%; padding-bottom: 5%">
                    <button type="submit" class="btn btn-primary">Добавить машину</button>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <footer>
                        <jsp:include page="footer.jsp"></jsp:include>
                    </footer>
                </div>
            </div>
        </div>
    </div>
    </form>
</div>





<%--<div class="container-fluid">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-12">--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-12">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-12">--%>
<%--                    <header>--%>
<%--                        <jsp:include page="header.jsp"></jsp:include>--%>
<%--                    </header>--%>

<%--                        <form action="/controller?command=addCar" method="post" enctype="multipart/form-data">--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-12">--%>
<%--                                    <h3>--%>
<%--                                        <label>Название машины</label>--%>
<%--                                            <input type="text" name="carName" >--%>
<%--                                    </h3>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Фото машины</label>--%>
<%--                                    <input type="file" name="carImage" >--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <h2>--%>
<%--                                        <label>Описание машины</label>--%>
<%--                                        <input type="text" name="carDescription" >--%>
<%--                                    </h2>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <button type="submit">Добавить машину</button>--%>
<%--                        </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-12">--%>
<%--                    <footer>--%>
<%--                        <jsp:include page="footer.jsp"></jsp:include>--%>
<%--                    </footer>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
</body>
</html>
