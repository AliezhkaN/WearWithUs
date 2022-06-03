<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <%@include file="/styles/visibility.css"%>
        <%@include file="/styles/profile.css"%>
        <%@include file="/styles/modal-styles.css"%>
    </style>
</head>


</main>
<body>
<div class="wrapper">
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
        <a class="headerMenu" href="/application">Головна</a>
        <a class="headerMenu" href="boys">Чоловікам</a>
        <a class="headerMenu" href="girls">Жінкам</a>
    </div>

    <div id="acc_bag">
        <a class="acc_bag_links" href="logout">
            <div class="account_block"  >
                <img src="./images/account.png" alt="Account img" style="padding-left: 12px!important;">
                <p class="acc_bag_text">Вийти</p>
            </div>
        </a>
        <a class="acc_bag_links">
            <div id="bag_block">
                <img src="./images/bag.png" alt="Bag img">
                <p class="acc_bag_text">Кошик</p>
            </div>
        </a>
    </div>
</header>
<main style="flex: 1 1 auto">
    <section class="avatar__container">
        <div class="avatar-container">
            <img src="${user.avatar}" alt="ss">
        </div>
        <p class="image-edit" data-bs-toggle="modal" data-bs-target="#modal-image">змінити</p>
    </section>
    <section class="profile__container">
        <div class="profile__info-container">
            <div class="profile__title-container">
                <div class="profile__title">
                    <span>Інформація про Користувача</span>
                </div>
                <div id="email-p" class="profile__email">
                    <div class="name__name">Е. Пошта</div>
                    <div class="name__value">
                            <span>
                                <c:out value="${user.email}"/>
                            </span>
                    </div>
                    <div class="name__edit">
                        <a>Змінити</a>
                    </div>
                </div>
                <form id="email-i" class="profile__email hide" action="changeEmail" method="post">
                    <div class="name__name">Електронна пошта</div>
                    <div class="name__value__edit">
                        <div class="name__name">
                            <p>You will be required to login again after changing your email address.</p>
                        </div>
                        <div id="new_email_label" class="hide">
                            <label for="new_email">New email address</label>
                        </div>
                        <div>
                            <input autocomplete id="new_email" required placeholder="New email address" name="newEmail" type="email" class="input black">
                        </div>
                        <div id="confirm_new_email_label" class="hide">
                            <label for="confirm_new_email">Confirm new email address</label>
                        </div>
                        <div>
                            <input autocomplete id="confirm_new_email" required placeholder="Confirm new email" name="confirmNewEmail" type="email" class="input black">
                        </div>
                        <div id="confirm_password_label" class="hide">
                            <label for="confirm_password">Confirm password</label>
                        </div>
                        <div>
                            <input autocomplete id="confirm_password" required placeholder="Confirm password" name="confirmPassword" type="password" class="input black">
                        </div>
                        <div class="buttons_container">
                            <div class="buttons">
                                <input class="button" type="submit" value="Save">
                                <button type="button" id="email_cancel_button" class="button cancel_button">Cancel</button>
                            </div>
                        </div>

                        <div class="error__message__wrapper">
                            <c:if test="${changeEmailMessage != null}">
                                    <span id="email-error">
                                        <c:out value="${changeEmailMessage}"/>
                                    </span>
                            </c:if>
                        </div>

                    </div>
                </form>

                <div id="password-p" class="profile__email">
                    <div class="name__name">Пароль</div>
                    <div class="name__value">
                        <span>******</span>
                    </div>
                    <div class="name__edit">
                        <a>Змінити</a>
                    </div>
                </div>
                <form id="password-i" class="profile__email hide" action="changePassword" method="post">
                    <div class="name__name">Пароль</div>
                    <div class="name__value__edit">
                        <div class="name__name">
                            <p>You will be required to login again after changing your email address.</p>
                        </div>
                        <div id="current_password_label" class="hide">
                            <label for="current_password">Current password</label>
                        </div>
                        <div>
                            <input autocomplete id="current_password" required placeholder="Current password" name="currentPassword" type="password" class="input black">
                        </div>
                        <div id="new_password_label" class="hide">
                            <label for="new_password">New password</label>
                        </div>
                        <div>
                            <input autocomplete id="new_password" required placeholder="New password" name="newPassword" type="password" class="input black">
                        </div>
                        <div id="confirm_new_password_label" class="hide">
                            <label for="confirm_new_password">Confirm new password</label>
                        </div>
                        <div>
                            <input autocomplete id="confirm_new_password" required placeholder="Confirm new password" name="confirmNewPassword" type="password" class="input black">
                        </div>
                        <div class="buttons_container">
                            <div class="buttons">
                                <input class="button" type="submit" value="Save">
                                <button type="button" id="password_cancel_button" class="button cancel_button">Cancel</button>
                            </div>
                        </div>
                        <div class="error__message__wrapper">
                            <c:if test="${changePasswordMessage != null}">
                                    <span id="password-error">
                                        <c:out value="${changePasswordMessage}"/>
                                    </span>
                            </c:if>
                        </div>
                    </div>
                </form>
                <div class="profile__title margin-t-3">
                    <span>Персональні дані</span>
                </div>
                <div id="fullname-p" class="profile__email">
                    <div class="name__name">ПІБ</div>
                    <div class="name__value">
                        <span><c:out value="${user.fullName}"/></span>
                    </div>
                    <div class="name__edit">
                        <a>Змінити</a>
                    </div>
                </div>
                <form id="fullname-i" class="profile__email hide" action="changeFullName" method="post">
                    <div class="name__name">Full name</div>
                    <div class="name__value__edit">

                        <div id="first_name_label" class="hide">
                            <label for="first_name">ПІБ</label>
                        </div>
                        <div>
                            <input autocomplete  id="first_name" required placeholder="ПІБ"
                                   name="fullName" type="text" class="input black"
                                   value="${user.fullName}">
                        </div>
                        <div class="buttons_container">
                            <div class="buttons">
                                <input class="button" type="submit" value="Save">
                                <button type="button" id="fullname_cancel_button" class="button cancel_button">Cancel</button>
                            </div>
                        </div>
                        <div class="error__message__wrapper">
                                    <span id="fullName-error">

                                    </span>
                        </div>
                    </div>
                </form>
                <div class="profile__email margin-bottom-5">
                    <div class="name__name">Тел. номер</div>
                    <div class="name__value">
                        <span><c:out value="${user.phoneNumber}"/></span>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
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
            &copy; <strong><span class="black">Wear</span><span class="white">With</span><span class="yellow">Us</span></strong>. Усі права захищені
        </div>
    </div>
</footer>

    <div class="modal fade" id="modal-image" tabindex="-1" aria-labelledby="modal-login-label3" aria-hidden="true">
        <div class="modal-dialog">
            <form action="avatarEdit" method="post" class="modal-content modalWindow" id="imageWindow">
                <button class="btn-close close-modal-btn" data-bs-dismiss="modal" style="flex: 1 1 auto" aria-label="close"></button>
                <div class="login-title" id="signUpTitle">
                    Зміна Фото
                </div>
                <div class="i-container">
                    <div style="" id="image_label" class="hide">
                        <label for="image-src">Last name</label>
                    </div>
                    <div>
                        <input autocomplete id="image-src" required placeholder="image src"
                               name="avatar" type="text" class="input black"
                               value="">
                    </div>
                </div>

                <div class="modal-footer-buttons">

                    <input type="submit" value="ЗМІНИТИ" class="logIn" style="width: 100%;">
                </div>
            </form>
        </div>
    </div>

</div>
<script type="text/javascript">
    <%@include file="/js/profile.js" %>
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
