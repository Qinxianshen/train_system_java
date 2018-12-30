<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Simple ideas for enhancing text input interactions" />
<meta name="keywords" content="input, text, effect, focus, transition, interaction, inspiration, web design" />
<meta name="author" content="Codrops" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/normalize.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/component.css" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fonts/font-awesome-4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/select/style.css" />

<title>Success</title>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/img/login/0.jpg); background-size: cover;">

	   <h2 style="color:#ffffff">--------登录成功，欢迎${username}---</h2>
	
	   <div style="width:1230px;margin:0 auto;">
        <form action="/TrainQuery/station/select" method="post">
            <section class="content">
            <span class="input input--hoshi">
                <input class="input__field input__field--hoshi" type="text" id="input-4" name="initialStationName" />
                <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                    <span class="input__label-content input__label-content--hoshi">出发站</span>
                </label>
            </span>
            <span class="input input--hoshi">
                <input class="input__field input__field--hoshi" type="text" id="input-5" name="destinationStationName" />
                <label class="input__label input__label--hoshi input__label--hoshi-color-2" for="input-5">
                    <span class="input__label-content input__label-content--hoshi">终点站</span>
                </label>
            </span>
            <span class="input input--hoshi">
                <input class="input__field input__field--hoshi" type="submit" id="input-6" />
                <label class="input__label input__label--hoshi input__label--hoshi-color-3" for="input-6">
                    <span class="input__label-content input__label-content--hoshi"></span>
                </label>
            </span>
            </section>
        </form>
        <article>
            <section style="margin:40px 0 0 0;">
                <table>
                    <thead>
                        <tr>
                            <th>车次</th>
                            <th>出发站</th>
                            <th>终点站</th>
                            <th>历程</th>
                            <th>出发时间</th>
                            <th>到达时间</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="train_route" items="${TrainRoute}">
                        <tr>
                            <td>${train_route.trainName}</td>
                            <td>${train_route.initialStationName}</td>
                            <td>${train_route.destinationStationName}</td>
                            <td>${train_route.distance}</td>
                            <td><fmt:formatDate value="${train_route.arriveTime}" pattern="MM-dd HH:mm"/></td>
                            <td><fmt:formatDate value="${train_route.leaveTime}" pattern="MM-dd HH:mm"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </section>
        </article>
    </div>
    <div class="fieldContainer">
        <label><a style="color:#ffffff" href="/TrainQuery/station/AdvancedSearch">测试 ${NODirect}</a></label>
        |
        <label><a style="color:#ffffff" href="/TrainQuery/station/StationDijkstra">StationDijkstra</a></label>
    </div>

    <script type="text/javascript" src="../js/select/classie.js"></script>
    <script type="text/javascript">
        (function() {
            // trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
            if (!String.prototype.trim) {
                (function() {
                    // Make sure we trim BOM and NBSP
                    var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
                    String.prototype.trim = function() {
                        return this.replace(rtrim, '');
                    };
                })();
            }

            [].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
                // in case the input is already filled..
                if( inputEl.value.trim() !== '' ) {
                    classie.add( inputEl.parentNode, 'input--filled' );
                }

                // events:
                inputEl.addEventListener( 'focus', onInputFocus );
                inputEl.addEventListener( 'blur', onInputBlur );
            } );

            function onInputFocus( ev ) {
                classie.add( ev.target.parentNode, 'input--filled' );
            }

            function onInputBlur( ev ) {
                if( ev.target.value.trim() === '' ) {
                    classie.remove( ev.target.parentNode, 'input--filled' );
                }
            }
        })();
    </script>
	
</body>
</html>