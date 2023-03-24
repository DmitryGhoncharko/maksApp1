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
                Страница добавления типа деталей машины
              </h3>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <form role="form" action="/controller?command=addNewDetail" method="post">
                <div class="form-group">
                  <label for="exampleInputEmail1">
                    Тип детали
                  </label>
                  <div class="input-group mb-3" id="exampleInputEmail1">
                    <div class="input-group-prepend">
                      <label class="input-group-text" for="inputGroupSelect01">Выберите</label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect01" name="detailType">
                      <option selected>Тип детали</option>
                      <option value="1">Мотор</option>
                      <option value="2">Подвеска</option>
                      <option value="3">Кузов</option>
                      <option value="4">Салон</option>
                      <option value="5">Отдельно</option>
                      <option value="6">Дополнительно</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">

                  <label for="exampleInputPassword1">
                    Название детали
                  </label>
                  <input required type="text" class="form-control" name="detailName" id="exampleInputPassword1" />
                  <label for="exampleInputPassword2">
                    Вес детали
                  </label>
                  <input required type="text" pattern="^[0-9]*[.,]{0,1}[0-9]+$" class="form-control" name="detailWeight" id="exampleInputPassword2" />
                </div>
                <c:if test="${not empty requestScope.error}">
                  <h1>Не получилось добавить деталь.Возможно деталь с таким именем уже существует!</h1>
                </c:if>
                <button type="submit" class="btn btn-primary">
                  Добавить деталь
                </button>
              </form>
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
</html>

