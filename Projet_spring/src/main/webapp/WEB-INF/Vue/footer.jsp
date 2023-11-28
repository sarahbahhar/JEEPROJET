
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
      integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
      crossorigin="anonymous">

<footer class="footer">
    <div class="p1">
        <div class="c1">
            <h5 class="color pt2">Trouvez-nous</h5>
            <p><i class="fa fa-location-arrow"></i> 57 boulevard du port 95000 Cergy</p>
            <p><i class="fa fa-phone"></i> 07-00-00-00-00</p>
            <p><i class="fa fa fa-envelope"></i> websiteprojet2023@gmail.com</p>
        </div>

        <div class="c1">
            <h5 class="color pt2">Concepteurs</h5>
            <ul class="name">
                <li>Simon REN</li>
                <li>Sarah BAHHAR</li>
                <li>Loucas TERCHANI</li>
                <li>Abdellah HASSANI</li>

            </ul>
        </div>
    </div>
    <div class="line"></div>
    <div>
        <div class="footer-nav-bar">
        <ul class="navbar-nav">
            <c:if test="${not empty sessionScope.email}">
                <li class="nav-item ">
                    <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                        <input type="hidden" name="path" value="myProfile.jsp" />
                        <button type="submit"><a class="nav-link">Profil</a></button>
                    </form>
                </li>
            </c:if>


            <li class="nav-item ">
                <form class='style' action="${pageContext.request.contextPath}/#" method="get">
                    <button type="submit"><a class="nav-link">Accueil</a></button>
                </form>
            </li>
            <li class="nav-item ">
                <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                    <input type="hidden" name="path" value="about.jsp" />
                    <button type="submit"><a class="nav-link">A propos</a></button>
                </form>
            </li>
            <li class="nav-item ">
                <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                    <input type="hidden" name="path" value="contact.jsp" />
                    <button type="submit"><a class="nav-link">Contacter</a></button>
                </form>
            </li>
        </ul>
        </div>
        <center> 2023, Divan.com</center>
        <ul class="logo">
            <li style="padding-left: 0px;"><a><i class="fab fa-facebook-f"></i></a></li>
            <li><a><i class="fab fa-twitter"></i></a></li>
            <li><a><i class="fab fa-linkedin"></i></a></li>
            <li><a><i class="fab fa-instagram"></i></a></li>
        </ul>
    </div>
</footer>
