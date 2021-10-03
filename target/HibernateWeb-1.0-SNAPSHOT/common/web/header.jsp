<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- HEADER -->
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-phone"></i> +021-95-51-84</a></li>
                <li><a href="#"><i class="fa fa-envelope-o"></i> email@email.com</a></li>
                <li><a href="#"><i class="fa fa-map-marker"></i> 1734 Stonecoal Road</a></li>
            </ul>
            <ul class="header-links pull-right">
                <c:if test="${sessionScope.acc == null}">
                    <li><a href="register?action=signup"><i class="fa fa-registered"></i> Register</a></li>
                    <li><a href="login?action=signin"><i class="fa fa-sign-in"></i> Login</a></li>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                    <li><a href="myaccount"><i class="fa fa-male"></i> ${sessionScope.acc.name}</a></li>
                    <li><a href="login?action=signin"><i class="fa fa-sign-in"></i> Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <a href="trang-chu" class="logo">
                            <img src="<c:url value='/template/web/img/logo.png'/>" alt="">
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form action="search?action=search" method="post">
                            <select class="input-select" name="category">
                                <option value="all">All Categories</option>
                                <c:forEach items="${listC}" var="o">
                                    <option value="${o.name}" ${category == o.name ? "selected" : ""}>${o.name}</option>
                                </c:forEach>
                            </select>
                            <input class="input" placeholder="Search here"name="search" type="text" required="required">
                            <button class="search-btn" type="submit">Search</button>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <!-- Cart -->
                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                <i class="fa fa-shopping-cart"></i>
                                <span>Your Cart</span>
                                <c:if test="${sessionScope.cart != null}">
                                    <div class="qty">${sessionScope.cart.items.size()}</div>
                                </c:if>
                            </a>
                            <c:if test="${sessionScope.cart != null}">
                                <div class="cart-dropdown">
                                    <div class="cart-list">
                                        <c:forEach items="${sessionScope.cart.items}" var="o">
                                            <div class="product-widget">
                                                <div class="product-img">
                                                    <img src="${o.src}" alt="${o.name}">
                                                </div>
                                                <div class="product-body">
                                                    <h3 class="product-name"><a href="#">${o.name}</a></h3>
                                                    <h4 class="product-price"><span class="qty">${o.number}x</span>$${o.price}</h4>
                                                </div>
                                                <a href="cart?action=del&id=${o.id}">
                                                    <button class="delete"><i class="fa fa-close"></i></button>
                                                </a>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="cart-summary">
                                        <small>${sessionScope.cart.items.size()} Item(s) selected</small>
                                        <h5>SUBTOTAL: $${sessionScope.cart.amount}</h5>
                                    </div>
                                    <div class="cart-btns">
                                        <a href="#">View Cart</a>
                                        <a href="checkout">Checkout <i class="fa fa-arrow-circle-right"></i></a>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <!-- /Cart -->

                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->

<!-- NAVIGATION -->
<nav id="navigation">
    <!-- container -->
    <div class="container">
        <!-- responsive-nav -->
        <div id="responsive-nav">
            <!-- NAV -->
            <ul class="main-nav nav navbar-nav">
                <li class="${nav.equals("laptop") ? "active" : ""}"><a href="navigation?nav=laptop">Laptops</a></li>
                <li class="${nav.equals("smartphone") ? "active" : ""}"><a href="navigation?nav=smartphone">Smartphones</a></li>
                <li class="${nav.equals("camera") ? "active" : ""}"><a href="navigation?nav=camera">Cameras</a></li>
                <li class="${nav.equals("accessories") ? "active" : ""}"><a href="navigation?nav=accessories">Accessories</a></li>
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>
<!-- /NAVIGATION -->
