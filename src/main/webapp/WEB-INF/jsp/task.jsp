
<%@ page import="java.util.List" %>
<%@ page import="by.webproj.carshowroom.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<link rel="stylesheet" href="/selectize/dist/css/selectize.default.css">
<script src="/jquery.min.js"></script>
<script src="/microplugin/src/microplugin.js"></script>
<script src="/sifter/sifter.min.js"></script>
<script src="/selectize/dist/js/selectize.min.js"></script>
<link rel="stylesheet" href="/select2/dist/css/select2.min.css">
<script src="/jquery.min.js"></script>
<script src="/select2/dist/js/select2.min.js"></script>
<script src="/select2/dist/js/i18n/ru.js"></script>
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
<head>
    <title>Сформировать отчет</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="header.jsp"></jsp:include>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <br>
                    <a class="noprint" href="/controller?command=clearSes">Очистить лист</a>
                    <br>
                    <br>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-4">
                            <label for="1">Марка  </label> <input type="text" id="1" name="mark">
                        </div>
                        <div class="col-md-4">
                            <label for="2">Модель  </label> <input type="text" id="2" name="model">
                        </div>
                        <div class="col-md-4">
                            <label for="4">Номер  </label> <input type="text" id="4" name="number">
                            <br>
                            <br>
                            <label for="5">Цена  </label> <input type="text" id="5" name="number">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-2">
                            <br>
                            <br>
                            <input type="text" id="inputSearch" placeholder="Поиск.." class="noprint" title="Type in a category" onkeypress="document.addEventListener('keyup', searchc);">
                            <ul id="list">
                                <c:forEach varStatus="index" items="${requestScope.engines}" var="engine">
                                    <li style="display: none"><a href="/controller?command=addToSession&type=engine&id=${engine.id}">${engine.name}</a></li>
                                </c:forEach>
                            </ul>
                            <script type="text/javascript">
                                function searchc() {
                                    let input = document.getElementById("inputSearch");
                                    let filter = input.value.toUpperCase();
                                    let ul = document.getElementById("list");
                                    let li = ul.getElementsByTagName("li");

                                    for (let i = 0; i < li.length; i++) {
                                        let a = li[i].getElementsByTagName("a")[0];
                                        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                                            li[i].style.display = "";
                                        } else {
                                            li[i].style.display = "none";
                                        }
                                    }
                                    if(input.value.toString().length<=0){
                                        for (let i = 0; i < li.length; i++) {
                                            li[i].style.display = "none";
                                        }
                                    }
                                }
                            </script>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Мотор</th>
                                    <th scope="col" class="noprint">Удалить</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <c:forEach varStatus="index" items="${sessionScope.engines}" var="engine">
                                    <tr>
                                        <th scope="row">${index.index}</th>
                                        <td>${engine.name}</td>
                                        <td>  <a class="noprint" href="/controller?command=delses&type=engine&id=${engine.id}">Удалить</a></td>
                                    </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-2">
                            <br>
                            <br>
                            <input type="text" class="noprint" id="inputSearch11" placeholder="Поиск.." title="Type in a category" onkeypress="document.addEventListener('keyup', searchcc);">
                            <ul id="list11">
                                <c:forEach varStatus="index" items="${requestScope.suspensions}" var="engine">
                                    <li style="display: none"><a href="/controller?command=addToSession&type=susp&id=${engine.id}">${engine.name}</a></li>
                                </c:forEach>
                            </ul>
                            <script type="text/javascript">
                                function searchcc() {
                                    let input = document.getElementById("inputSearch11");
                                    let filter = input.value.toUpperCase();
                                    let ul = document.getElementById("list11");
                                    let li = ul.getElementsByTagName("li");

                                    for (let i = 0; i < li.length; i++) {
                                        let a = li[i].getElementsByTagName("a")[0];
                                        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                                            li[i].style.display = "";
                                        } else {
                                            li[i].style.display = "none";
                                        }
                                    }
                                    if(input.value.toString().length<=0){
                                        for (let i = 0; i < li.length; i++) {
                                            li[i].style.display = "none";
                                        }
                                    }
                                }
                            </script>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Подвеска</th>
                                    <th scope="col" class="noprint">Удалить</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <c:forEach varStatus="index" items="${sessionScope.suspensions}" var="engine">
                                    <tr>
                                        <th scope="row">${index.index}</th>
                                        <td>${engine.name}


                                        </td>
                                        <td> <a class="noprint" href="/controller?command=delses&type=susp&id=${engine.id}">Удалить</a></td>
                                    </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-2">
                            <br>
                            <br>
                            <input type="text" class="noprint" id="inputSearch111" placeholder="Поиск.." title="Type in a category" onkeypress="document.addEventListener('keyup', searchs);">
                            <ul id="list111">
                                <c:forEach varStatus="index" items="${requestScope.bodies}" var="engine">
                                    <li style="display: none"><a href="/controller?command=addToSession&type=body&id=${engine.id}">${engine.name}</a></li>
                                </c:forEach>
                            </ul>
                            <script type="text/javascript">
                                function searchs() {
                                    let input = document.getElementById("inputSearch111");
                                    let filter = input.value.toUpperCase();
                                    let ul = document.getElementById("list111");
                                    let li = ul.getElementsByTagName("li");

                                    for (let i = 0; i < li.length; i++) {
                                        let a = li[i].getElementsByTagName("a")[0];
                                        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                                            li[i].style.display = "";
                                        } else {
                                            li[i].style.display = "none";
                                        }
                                    }
                                    if(input.value.toString().length<=0){
                                        for (let i = 0; i < li.length; i++) {
                                            li[i].style.display = "none";
                                        }
                                    }
                                }
                            </script>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Кузов</th>
                                    <th scope="col" class="noprint">Удалить</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <c:forEach varStatus="index" items="${sessionScope.bodies}" var="engine">
                                    <tr>
                                        <th scope="row">${index.index}</th>
                                        <td>${engine.name}
                                        </td>
                                        <td>  <a class="noprint" href="/controller?command=delses&type=body&id=${engine.id}">Удалить</a></td>
                                    </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-2">
                            <br>
                            <br>
                            <input type="text" class="noprint" id="inputSearch1111" placeholder="Поиск.." title="Type in a category" onkeypress="document.addEventListener('keyup', searchss);">
                            <ul id="list1111">
                                <c:forEach varStatus="index" items="${requestScope.salons}" var="engine">
                                    <li style="display: none"><a href="/controller?command=addToSession&type=salon&id=${engine.id}">${engine.name}</a></li>
                                </c:forEach>
                            </ul>
                            <script type="text/javascript">
                                function searchss() {
                                    let input = document.getElementById("inputSearch1111");
                                    let filter = input.value.toUpperCase();
                                    let ul = document.getElementById("list1111");
                                    let li = ul.getElementsByTagName("li");

                                    for (let i = 0; i < li.length; i++) {
                                        let a = li[i].getElementsByTagName("a")[0];
                                        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                                            li[i].style.display = "";
                                        } else {
                                            li[i].style.display = "none";
                                        }
                                    }
                                    if(input.value.toString().length<=0){
                                        for (let i = 0; i < li.length; i++) {
                                            li[i].style.display = "none";
                                        }
                                    }
                                }
                            </script>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Салон</th>
                                    <th scope="col" class="noprint">Удалить</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <c:forEach varStatus="index" items="${sessionScope.salons}" var="engine">
                                    <tr>
                                        <th scope="row">${index.index}</th>
                                        <td>${engine.name}

                                        </td>
                                        <td> <a class="noprint" href="/controller?command=delses&type=salon&id=${engine.id}">Удалить</a></td>
                                    </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-2">
                            <br>
                            <br>
                            <input type="text" class="noprint" id="inputSearch111111" placeholder="Поиск.." title="Type in a category" onkeypress="document.addEventListener('keyup', searchv);">
                            <ul id="list111111">
                                <c:forEach varStatus="index" items="${requestScope.additions}" var="engine">
                                    <li style="display: none"><a href="/controller?command=addToSession&type=addition&id=${engine.id}">${engine.name}</a></li>
                                </c:forEach>
                            </ul>
                            <script type="text/javascript">
                                function searchv() {
                                    let input = document.getElementById("inputSearch111111");
                                    let filter = input.value.toUpperCase();
                                    let ul = document.getElementById("list111111");
                                    let li = ul.getElementsByTagName("li");

                                    for (let i = 0; i < li.length; i++) {
                                        let a = li[i].getElementsByTagName("a")[0];
                                        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                                            li[i].style.display = "";
                                        } else {
                                            li[i].style.display = "none";
                                        }
                                    }
                                    if(input.value.toString().length<=0){
                                        for (let i = 0; i < li.length; i++) {
                                            li[i].style.display = "none";
                                        }
                                    }
                                }
                            </script>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Дополнительно</th>
                                    <th scope="col" class="noprint">Удалить</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <c:forEach varStatus="index" items="${sessionScope.additions}" var="engine">
                                    <tr>
                                        <th scope="row">${index.index}</th>
                                        <td>${engine.name}


                                        </td>
                                        <td>  <a class="noprint" href="/controller?command=delses&type=addition&id=${engine.id}">Удалить</a></td>
                                    </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-2">
                            <br>
                            <br>
                            <input type="text" class="noprint" id="inputSearch11111" placeholder="Поиск.." title="Type in a category" onkeypress="document.addEventListener('keyup', searchz);">
                            <ul id="list11111">
                                <c:forEach varStatus="index" items="${requestScope.separaters}" var="engine">
                                    <li style="display: none"><a href="/controller?command=addToSession&type=sep&id=${engine.id}">${engine.name}</a></li>
                                </c:forEach>
                            </ul>
                            <script type="text/javascript">
                                function searchz() {
                                    let input = document.getElementById("inputSearch11111");
                                    let filter = input.value.toUpperCase();
                                    let ul = document.getElementById("list11111");
                                    let li = ul.getElementsByTagName("li");

                                    for (let i = 0; i < li.length; i++) {
                                        let a = li[i].getElementsByTagName("a")[0];
                                        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                                            li[i].style.display = "";
                                        } else {
                                            li[i].style.display = "none";
                                        }
                                    }
                                    if(input.value.toString().length<=0){
                                        for (let i = 0; i < li.length; i++) {
                                            li[i].style.display = "none";
                                        }
                                    }
                                }
                            </script>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Отдельно</th>
                                    <th scope="col" class="noprint">Удалить</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <c:forEach varStatus="index" items="${sessionScope.separaters}" var="engine">
                                    <tr>
                                        <th scope="row">${index.index}</th>
                                        <td>${engine.name}

                                        </td>
                                        <td>
                                            <a class="noprint" href="/controller?command=delses&type=sep&id=${engine.id}">Удалить</a>
                                        </td>
                                    </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-2">

                        </div>
                        <br>
                        <br>
                        <br>
                        <%
                            List<EngineEntity> engineEntities = (List<EngineEntity>) session.getAttribute("engines");
                            List<AdditionEntity> additionEntities = (List<AdditionEntity>) session.getAttribute("additions");
                            List<BodyEntity> bodyEntities = (List<BodyEntity>) session.getAttribute("bodies");
                            List<SalonEntity> salonEntities = (List<SalonEntity>) session.getAttribute("salons");
                            List<SeparaterlyEntity> separaterlyEntities = (List<SeparaterlyEntity>) session.getAttribute("separaters");
                            List<SuspensionEntity> suspensionEntities = (List<SuspensionEntity>) session.getAttribute("suspensions");
                            double engineSum = 0;
                            if(engineEntities!=null){
                                engineSum += engineEntities.stream().mapToDouble(EngineEntity::getWeight).sum();
                            }
                            if(additionEntities!=null){
                                engineSum += additionEntities.stream().mapToDouble(AdditionEntity::getWeight).sum();
                            }
                            if(bodyEntities!=null){
                                engineSum += bodyEntities.stream().mapToDouble(BodyEntity::getWeight).sum();
                            }
                            if(salonEntities!=null){
                                engineSum+=salonEntities.stream().mapToDouble(SalonEntity::getWeight).sum();
                            }
                            if(separaterlyEntities!=null){
                                engineSum+=separaterlyEntities.stream().mapToDouble(SeparaterlyEntity::getWeight).sum();
                            }
                            if(suspensionEntities!=null){
                                engineSum+=suspensionEntities.stream().mapToDouble(SuspensionEntity::getWeight).sum();
                            }
                            if(engineSum>0){
                                out.println("<h1>Итоговый вес: " + Math.round(engineSum) + " </h1>");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<style type="text/css">
    .select_wrp {
        width: 300px;
        margin: 0 auto;
    }
    @media print {
        .noprint {
            display: none !important;
        }
    }
</style>
</body>
</html>