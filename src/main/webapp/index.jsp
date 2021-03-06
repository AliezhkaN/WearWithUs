<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <link rel="icon" href="./images/t-shirtLogo.png" type="image/x-icon">
    <title>WearWithUs</title>
    <style>
        <%@include file="/styles/styles.css"%>
        <%@include file="/styles/modal-styles.css"%>
        <%@include file="/styles/sidebar.css"%>
        <%@include file="/styles/select.css"%>

    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
</head>
<body>

<header>
    <a id="logoLink" href="/application">
        <div id="logo">
            <img id="t-shirtLogo" src="./images/t-shirtLogo.png" alt="T-shirt img">
            <span class="name">
                    <span class="black">Wear</span><span class="white">With</span><span class="yellow">Us</span>
                </span>
        </div>
    </a>

    <div id="menu">
        <a class="headerMenu" href=""> <fmt:message key="Home"/></a>
        <a class="headerMenu" href="boys"><fmt:message key="FOR_MEN"/></a>
        <a class="headerMenu" href="girls"><fmt:message key="FOR_WOMEN"/></a>
    </div>

    <div id="acc_bag">
        <c:choose>
        <c:when test="${userId != null}">
            <a class="acc_bag_links" href="profile">
                <div class="account_block">
                    <img src="./images/account.png" alt="Account img">
                    <p class="acc_bag_text"><fmt:message key="ACCOUNT"/></p>
                </div>
            </a>
        </c:when>
        <c:otherwise>
            <a class="acc_bag_links">
                <div class="account_block" data-bs-toggle="modal" data-bs-target="#modal-login">
                    <img src="./images/account.png" alt="Account img">
                    <p class="acc_bag_text"><fmt:message key="ACCOUNT"/></p>
                </div>
            </a>
        </c:otherwise>
        </c:choose>


        <a class="acc_bag_links">
            <div id="bag_block">
                <img src="./images/bag.png" alt="Bag img">
                <p class="acc_bag_text"><fmt:message key="BAG"/></p>
            </div>
        </a>
        <form action="changeLocale" method="post" style="height: 8vh; display: flex; justify-content: center; align-items: center">
            <select class="select" name="lang" id="" onchange="this.form.submit()">
                <c:if test="${lang == 'en'}">
                    <option>en</option>
                    <option>uk</option>
                </c:if>
                <c:if test="${lang == 'uk'}">
                    <option>uk</option>
                    <option>en</option>
                </c:if>
            </select>
        </form>
    </div>

</header>

<!----------------------------------Account modal window---------------------------------->
<div class="modal pfade" id="modal-login" tabindex="-1" aria-labelledby="modal-login-label" aria-hidden="true">
    <div class="modal-dialog">
        <form action="login" method="post" class="modal-content modalWindow">
            <button class="btn-close close-modal-btn" data-bs-dismiss="modal" aria-label="close"></button>
            <div class="login-title">
                <fmt:message key="LOGIN"/>
            </div>
            <div class="login-title subscription">
                <fmt:message key="WITH_ACCOUNT"/> WearWithUS
            </div>
            <div class="input-form">
                <label for="input-form-email"><fmt:message key="MAIL"/></label><br>
                <input id="input-form-email" name="email" required type="email" >
            </div>
            <div class="input-form">
                <label for="input-form-pass"><fmt:message key="PASSWORD"/></label><br>
                <input id="input-form-pass" name="password" required type="password">
            </div>
            <br>

            <div class="modal-footer-buttons">
                <button class="signUp" data-bs-target="#modal-registration" data-bs-toggle="modal" data-bs-dismiss="modal"><span class="arrow-left">???</span><fmt:message key="SIGN_UP"/></button>
                <input type="submit" value="<fmt:message key="LOGIN"/>" class="logIn">
            </div>
        </form>
    </div>
</div>

<div class="modal pfade" id="modal-registration" tabindex="-1" aria-labelledby="modal-login-label2" aria-hidden="true">
    <div class="modal-dialog">
        <form method="post" action="signUp" class="modal-content modalWindow" id="registrationWindow">
            <button class="btn-close close-modal-btn" data-bs-dismiss="modal" aria-label="close"></button>
            <div class="login-title" id="signUpTitle">
                <fmt:message key="SIGN_UP"/>
            </div>
            <div class="input-form">
                <label for="input-form-email_reg"><fmt:message key="MAIL"/></label><br>
                <input id="input-form-email_reg" name="email" required type="email" >
            </div>
            <div class="input-form">
                <label for="input-form-name_reg"><fmt:message key="FULL_NAME"/></label><br>
                <input id="input-form-name_reg" name="fullName" required type="text">
            </div>
            <div class="input-form">
                <label for="input-form-tel_reg"><fmt:message key="PHONE_NUMBER"/></label><br>
                <input id="input-form-tel_reg" name="phone" maxlength="14" required type="tel" value="+380">
            </div>
            <div class="input-form">
                <label for="input-form-pass_reg1"><fmt:message key="PASSWORD"/></label><br>
                <input id="input-form-pass_reg1" name="password" required type="password">
            </div>
            <div class="input-form">
                <label for="input-form-pass_reg2"><fmt:message key="CONFIRM_PASSWORD"/></label><br>
                <input id="input-form-pass_reg2" name="confirm" required type="password">
            </div>

            <div class="form-check">
                <input class="form-check-input checkbox-reg-input" type="checkbox" value="" required id="flexCheckDefault">
                <label class="form-check-label checkbox-reg-label" for="flexCheckDefault">
                    <fmt:message key="I_AGREE_WITH"/>
                </label>
            </div>
            <br>

            <div class="modal-footer-buttons">
                <button class="signUp" data-bs-target="#modal-login" data-bs-toggle="modal" data-bs-dismiss="modal"><span class="arrow-left">???</span><fmt:message key="LOGIN"/></button>
                <input type="submit" value="<fmt:message key="SIGN_UP"/>" class="logIn">
            </div>
        </form>
    </div>
</div>
<!------------------------------------------------------------------------------------------->

<main>
    <div id="start_block">
        <p><fmt:message key="DIVE_INTO"/></p>
        <c:choose>
        <c:when test="${userId != null}">
            <a href="profile"><button class="btnn"><fmt:message key="GET_STARTED"/></button></a>

        </c:when>
            <c:otherwise>
                <button class="btnn" data-bs-target="#modal-login" data-bs-toggle="modal" data-bs-dismiss="modal"><fmt:message key="GET_STARTED"/></button>
            </c:otherwise>
        </c:choose>

    </div>
    <div id="newClothes_block">
        <p><fmt:message key="NEW_CLOTHES_ALREADY"/></p>
        <div id="items">
            <img src="./images/newClothes/item1.png" alt="image1">
            <img src="./images/newClothes/item2.png" alt="image2">
            <img src="./images/newClothes/item3.png" alt="image3">
        </div>
    </div>
    <div id="about_block">
        <div id="our_center">
            <h3><fmt:message key="OUR_CENTER"/></h3>
            <table>
                <tr>
                    <td><img src="./images/about/phone.png" alt="Phone img"></td>
                    <td><fmt:message key="PHONE"/></td>
                    <td>+380 88 888 8888</td>
                </tr>
                <tr>
                    <td><img src="./images/about/mail.png" alt="Mail img"></td>
                    <td><fmt:message key="EMAIL"/></td>
                    <td>wearWithUs@gmail.com</td>
                </tr>
                <tr>
                    <td><img src="./images/about/location.png" alt="Location img"></td>
                    <td><fmt:message key="LOCATION"/></td>
                    <td><fmt:message key="St_BN"/><br><fmt:message key="ST_B"/></td>
                </tr>
                <tr>
                    <td><img src="./images/about/maps.png" alt="Maps img"></td>
                    <td><fmt:message key="MAPS"/></td>
                    <td><a target="_blank" id="googleMaps" href="https://goo.gl/maps/t4CcXYu1xads1J3R7"><fmt:message key="VIEW_ON"/><br>Google Maps</a></td>
                </tr>
            </table>
        </div>
        <div id="contact_form">
            <h3><fmt:message key="CONTACT_FORM"/></h3>
            <p><fmt:message key="YOU_HAVE_QUESTIONS"/><br><fmt:message key="CONTACT_US_TODAY"/></p>
            <div class="text-field_floating-3">
                <input class="text-field__input" type="text" id="name" placeholder="Your name">
                <label class="text-field__label" for="name"><fmt:message key="FULL_NAME"/></label>
            </div>
            <div class="text-field_floating-3">
                <input class="text-field__input" type="email" id="email" placeholder="Your e-mail">
                <label class="text-field__label" for="email"><fmt:message key="EMAIL"/></label>
            </div>
            <div class="text-field_floating-3">
                <input class="text-field__input" type="text" id="message" placeholder="Your message">
                <label class="text-field__label" for="message"><fmt:message key="MESSAGE"/></label>
            </div>
            <button><fmt:message key="SEND"/></button>
        </div>
    </div>
</main>

<div id="sideBar" class="side-bar phide">
    <div  class="product-container" style="">
        <c:choose>
            <c:when test="${productS != null && productS.size() > 0}">
                <c:forEach var="product" items="${productS}">
                    <div class="_item">
                        <div class="img-cnt">
                            <img src="${product.src}" alt="itm">
                        </div>
                        <div class="product-info">
                            <div class="info-title">${product.name}</div>
                            <div class="info-price"><div class="pp"><fmt:message key="PRICE"/> :</div><div class="ppp"> ${product.price} <fmt:message key="UAH"/></div></div>
                            <form action="deleteProduct" method="post" class="trash">
                                <div class="trash-container">
                                    <input type="hidden" name="id" value="${product.id}">

                                    <input class="trash-input" type="submit" value="">
                                </div>
                            </form>
                        </div>
                    </div>

                </c:forEach>

                <c:choose>
                    <c:when test="${userId != null}">

                        <form action="buyProduct" method="post" class="button-_container"><input class="bb" type="submit" value="<fmt:message key="BUY"/>"></form>
                    </c:when>
                    <c:otherwise>
                        <div data-bs-target="#modal-login" data-bs-toggle="modal" data-bs-dismiss="modal" class="button-_container"> <button class="bb"><fmt:message key="BUY"/></button></div>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <div class="bag-container"><fmt:message key="BAG_IS_EMPTY"/></div>
            </c:otherwise>
        </c:choose>


    </div>
</div>




</div>
<footer id="footer">
    <div id="empty"></div>
    <div class="footer-container">
        <div class="social-links">
            <a target="_blank" href="https://www.instagram.com" class="instagram">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
                     class="bi bi-instagram" viewBox="0 0 16 16">
                    <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
                </svg>
            </a>
            <a target="_blank" href="https://web.telegram.org" class="telegram">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
                     class="bi bi-telegram" viewBox="0 0 16 16">
                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.287 5.906c-.778.324-2.334.994-4.666 2.01-.378.15-.577.298-.595.442-.03.243.275.339.69.47l.175.055c.408.133.958.288 1.243.294.26.006.549-.1.868-.32 2.179-1.471 3.304-2.214 3.374-2.23.05-.012.12-.026.166.016.047.041.042.12.037.141-.03.129-1.227 1.241-1.846 1.817-.193.18-.33.307-.358.336a8.154 8.154 0 0 1-.188.186c-.38.366-.664.64.015 1.088.327.216.589.393.85.571.284.194.568.387.936.629.093.06.183.125.27.187.331.236.63.448.997.414.214-.02.435-.22.547-.82.265-1.417.786-4.486.906-5.751a1.426 1.426 0 0 0-.013-.315.337.337 0 0 0-.114-.217.526.526 0 0 0-.31-.093c-.3.005-.763.166-2.984 1.09z"/>
                </svg>
            </a>
            <a target="_blank" href="https://www.youtube.com/" class="youtube">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-youtube"
                     viewBox="0 0 16 16">
                    <path d="M8.051 1.999h.089c.822.003 4.987.033 6.11.335a2.01 2.01 0 0 1 1.415 1.42c.101.38.172.883.22 1.402l.01.104.022.26.008.104c.065.914.073 1.77.074 1.957v.075c-.001.194-.01 1.108-.082 2.06l-.008.105-.009.104c-.05.572-.124 1.14-.235 1.558a2.007 2.007 0 0 1-1.415 1.42c-1.16.312-5.569.334-6.18.335h-.142c-.309 0-1.587-.006-2.927-.052l-.17-.006-.087-.004-.171-.007-.171-.007c-1.11-.049-2.167-.128-2.654-.26a2.007 2.007 0 0 1-1.415-1.419c-.111-.417-.185-.986-.235-1.558L.09 9.82l-.008-.104A31.4 31.4 0 0 1 0 7.68v-.123c.002-.215.01-.958.064-1.778l.007-.103.003-.052.008-.104.022-.26.01-.104c.048-.519.119-1.023.22-1.402a2.007 2.007 0 0 1 1.415-1.42c.487-.13 1.544-.21 2.654-.26l.17-.007.172-.006.086-.003.171-.007A99.788 99.788 0 0 1 7.858 2h.193zM6.4 5.209v4.818l4.157-2.408L6.4 5.209z"/>
                </svg>
            </a>
        </div>
        <div class="copyright">
            &copy; <strong><span class="black">Wear</span><span class="white">With</span><span class="yellow">Us</span></strong>. <fmt:message key="ALL_RIGHTS_RESERVED"/>
        </div>
    </div>
</footer>
<c:if test="${message!=null}">
    <script>
        swal({
            title: '<fmt:message key="SUCCESS"/>',
            text: '<fmt:message key="${message}"/>',
            icon: 'success',
            type: 'success',
            confirmButtonText: '<fmt:message key="OK"/>'
        })
    </script>
    <% session.removeAttribute("message");%>
</c:if>
<c:if test="${error!=null}">
    <input type="hidden" id="msg" value="${error}">
    <script>
        swal({
            title: '<fmt:message key="FAILURE"/>',
            text: '<fmt:message key="${error}"/>',
            icon: 'error',
            type: 'error',
            confirmButtonText: '<fmt:message key="OK"/>'
        })
    </script>
    <% session.removeAttribute("error");%>
</c:if>
<c:if test="${open == true}">
    <script>
        const sideBar = document.querySelector('#sideBar')
        sideBar.classList.add('pshow','pfade');
        sideBar.classList.remove('phide');
    </script>
    <%session.removeAttribute("open");%>
</c:if>
<script type="text/javascript">
    <%@include file="/js/sidebar.js" %>
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>