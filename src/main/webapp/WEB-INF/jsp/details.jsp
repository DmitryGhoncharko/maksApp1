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
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="text-center">
                               Список добавленных деталей
                            </h3>
                        </div>
                    </div>
                    <div class="row">
                        <div id="container">
                        <div class="col-md-12">
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item" onclick="changeTab('home')">
                                    <a class="nav-link active" id="home-tab" onclick="changeTab('home')" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Моторы</a>
                                </li>
                                <li class="nav-item" onclick="changeTab('profile')">
                                    <a class="nav-link" id="profile-tab" onclick="changeTab('profile')" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Подвеска</a>
                                </li>
                                <li class="nav-item" onclick="changeTab('contact')">
                                    <a class="nav-link" id="contact-tab1" onclick="changeTab('contact')" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Кузов</a>
                                </li>
                                <li class="nav-item" onclick="changeTab('contact1')">
                                    <a class="nav-link" id="contact-tab2" onclick="changeTab('contact1')" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Салон</a>
                                </li>
                                <li class="nav-item" onclick="changeTab('contact2')">
                                    <a class="nav-link" id="contact-tab3" onclick="changeTab('contact2')" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Отдельно</a>
                                </li>
                                <li class="nav-item" onclick="changeTab('contact3')">
                                    <a class="nav-link" id="contact-tab4" onclick="changeTab('contact3')" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Дополнительно</a>
                                </li>
                            </ul>
                            <div class="tab-content" id="myTabContent">
                                <div class="tab-pane show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Название детали</th>
                                            <th scope="col">Вес детали</th>
                                            <th scope="col">Удалить</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                       <c:forEach varStatus="index" items="${requestScope.engines}" var="engine">

                                              <tr>
                                                  <th scope="row">${index.index}</th>
                                                  <td>${engine.engineName}</td>
                                                  <td>${engine.engineWeight}</td>
                                                  <td>
                                                      <form method="post" action="/controller?command=deleteEngine">
                                                          <input hidden="hidden" name="detailId" value="${engine.id}">
                                                      <button type="submit">Удалить деталь</button>
                                                      </form>
                                                  </td>
                                              </tr>

                                       </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Название детали</th>
                                            <th scope="col">Вес детали</th>
                                            <th scope="col">Удалить</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach varStatus="index" items="${requestScope.suspensions}" var="engine">

                                            <tr>
                                                <th scope="row">${index.index}</th>
                                                <td>${engine.suspensionName}</td>
                                                <td>${engine.suspensionWeight}</td>
                                                <td>
                                                    <form method="post" action="/controller?command=deleteSuspension">
                                                        <input hidden="hidden" name="detailId" value="${engine.id}">
                                                        <button type="submit">Удалить деталь</button>
                                                    </form>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Название детали</th>
                                            <th scope="col">Вес детали</th>
                                            <th scope="col">Удалить</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach varStatus="index" items="${requestScope.bodies}" var="engine">

                                            <tr>
                                                <th scope="row">${index.index}</th>
                                                <td>${engine.bodyName}</td>
                                                <td>${engine.bodyWeight}</td>
                                                <td>
                                                    <form method="post" action="/controller?command=deleteBody">
                                                        <input hidden="hidden" name="detailId" value="${engine.id}">
                                                        <button type="submit">Удалить деталь</button>
                                                    </form>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane" id="contact1" role="tabpanel" aria-labelledby="contact-tab">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Название детали</th>
                                            <th scope="col">Вес детали</th>
                                            <th scope="col">Удалить</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach varStatus="index" items="${requestScope.salons}" var="engine">

                                            <tr>
                                                <th scope="row">${index.index}</th>
                                                <td>${engine.salonName}</td>
                                                <td>${engine.salonWeight}</td>
                                                <td>
                                                    <form method="post" action="/controller?command=deleteSalon">
                                                        <input hidden="hidden" name="detailId" value="${engine.id}">
                                                        <button type="submit">Удалить деталь</button>
                                                    </form>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane" id="contact2" role="tabpanel" aria-labelledby="contact-tab">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Название детали</th>
                                            <th scope="col">Вес детали</th>
                                            <th scope="col">Удалить</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach varStatus="index" items="${requestScope.separaters}" var="engine">

                                            <tr>
                                                <th scope="row">${index.index}</th>
                                                <td>${engine.sepName}</td>
                                                <td>${engine.sepWeight}</td>
                                                <td>
                                                    <form method="post" action="/controller?command=deleteSep">
                                                        <input hidden="hidden" name="detailId" value="${engine.id}">
                                                        <button type="submit">Удалить деталь</button>
                                                    </form>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane" id="contact3" role="tabpanel" aria-labelledby="contact-tab">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Название детали</th>
                                            <th scope="col">Вес детали</th>
                                            <th scope="col">Удалить</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach varStatus="index" items="${requestScope.additions}" var="engine">
                                            <tr>
                                                <th scope="row">${index.index}</th>
                                                <td>${engine.additionName}</td>
                                                <td>${engine.additionWeight}</td>
                                                <td>
                                                    <form method="post" action="/controller?command=deleteAddition">
                                                        <input hidden="hidden" name="detailId" value="${engine.id}">
                                                        <button type="submit">Удалить деталь</button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                          </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="footer.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function changeTab(name) {
            var contact3 = document.querySelector('#contact3');
            var contact2 = document.querySelector('#contact2');
            var contact1 = document.querySelector('#contact1');
            var contact = document.querySelector('#contact');
            var profile = document.querySelector('#profile');
            var home = document.querySelector('#home');
            switch (name) {
                case "contact3": {
                    contact3.setAttribute("class", "tab-pane active show")
                    contact2.setAttribute("class", "tab-page hidden")
                    contact1.setAttribute("class", "tab-page hidden")
                    contact.setAttribute("class", "tab-page hidden")
                    profile.setAttribute("class", "tab-page hidden")
                    home.setAttribute("class", "tab-page hidden")
                    break
                }
                case "contact2": {
                    contact2.setAttribute("class", "tab-pane active show")
                    contact3.setAttribute("class", "tab-page hidden")
                    contact1.setAttribute("class", "tab-page hidden")
                    contact.setAttribute("class", "tab-page hidden")
                    profile.setAttribute("class", "tab-page hidden")
                    home.setAttribute("class", "tab-page hidden")
                    break
                }
                case "contact1": {
                    contact1.setAttribute("class", "tab-pane active show")
                    contact2.setAttribute("class", "tab-page hidden")
                    contact3.setAttribute("class", "tab-page hidden")
                    contact.setAttribute("class", "tab-page hidden")
                    profile.setAttribute("class", "tab-page hidden")
                    home.setAttribute("class", "tab-page hidden")
                    break
                }
                case "contact": {
                    contact.setAttribute("class", "tab-pane active show")
                    contact1.setAttribute("class", "tab-page hidden")
                    contact2.setAttribute("class", "tab-page hidden")
                    contact3.setAttribute("class", "tab-page hidden")
                    profile.setAttribute("class", "tab-page hidden")
                    home.setAttribute("class", "tab-page hidden")
                    break
                }
                case "profile": {
                    profile.setAttribute("class", "tab-pane active show")
                    contact.setAttribute("class", "tab-page hidden")
                    contact1.setAttribute("class", "tab-page hidden")
                    contact2.setAttribute("class", "tab-page hidden")
                    contact3.setAttribute("class", "tab-page hidden")
                    home.setAttribute("class", "tab-page hidden")
                    break
                }
                case "home": {
                    home.setAttribute("class", "tab-pane active show")
                    profile.setAttribute("class", "tab-page hidden")
                    contact.setAttribute("class", "tab-page hidden")
                    contact1.setAttribute("class", "tab-page hidden")
                    contact2.setAttribute("class", "tab-page hidden")
                    contact3.setAttribute("class", "tab-page hidden")
                    break
                }
            }
    }
</script>
<script>
    $("body").on("keyup", "#filter-input", function() {
        var searchText =
            $("#filter-input")
                .val()
                .toLowerCase() || "___";
        $("#container > p").each(function(i) {
            var elem = $(this);
            if (
                elem
                    .html()
                    .toLowerCase()
                    .indexOf(searchText) === -1
            ) {
                elem.addClass("hidden");
            } else {
                elem.removeClass("hidden");
            }
        });
    });
</script>
</html>