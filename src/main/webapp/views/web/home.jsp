<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- ASIDE -->
            <div id="aside" class="col-md-3">
                <!-- aside Widget -->
                <div class="aside">
                    <h3 class="aside-title">Categories</h3>
                    <div class="checkbox-filter">
                        <c:forEach items="${listC}" var="o">
                            <div class="input-checkbox">
                                <input type="checkbox" id="category-1">
                                <label for="category-1">
                                    <span></span>
                                    ${o.name}
                                    <small>(${o.amount})</small>
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- /aside Widget -->

                <!-- aside Widget -->
                <div class="aside">
                    <h3 class="aside-title">Price</h3>
                    <div class="price-filter">
                        <div id="price-slider"></div>
                        <div class="input-number price-min">
                            <input id="price-min" type="number">
                            <span class="qty-up">+</span>
                            <span class="qty-down">-</span>
                        </div>
                        <span>-</span>
                        <div class="input-number price-max">
                            <input id="price-max" type="number">
                            <span class="qty-up">+</span>
                            <span class="qty-down">-</span>
                        </div>
                    </div>
                </div>
                <!-- /aside Widget -->

                <!-- aside Widget -->
                <div class="aside">
                    <h3 class="aside-title">Brand</h3>
                    <div class="checkbox-filter">
                        <c:forEach items="${listB}" var="o">
                            <div class="input-checkbox">
                                <input type="checkbox" id="brand-1">
                                <label for="brand-1">
                                    <span></span>
                                    ${o.name}
                                    <small>(${o.amount})</small>
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- /aside Widget -->

                <!-- aside Widget -->
                <div class="aside">
                    <h3 class="aside-title">Top New</h3>
                    <c:forEach items="${listN}" var="o">
                        <div class="product-widget">
                            <div class="product-img">
                                <img src="${o.src}" alt="${o.name}">
                            </div>
                            <div class="product-body">
                                <p class="product-category">${o.brand}</p>
                                <h3 class="product-name"><a href="#">${o.name}</a></h3>
                                <h4 class="product-price">$${o.price}</h4>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- /aside Widget -->
            </div>
            <!-- /ASIDE -->

            <!-- STORE -->
            <div id="store" class="col-md-9">
                <!-- store top filter -->
                <div class="store-filter clearfix">
                    <div class="store-sort">
                        <label>
                            Sort By:
                            <select class="input-select">
                                <option value="0">Popular</option>
                                <option value="1">Position</option>
                            </select>
                        </label>

                        <label>
                            Show:
                            <select class="input-select">
                                <option value="0">20</option>
                                <option value="1">50</option>
                            </select>
                        </label>
                    </div>
                    <ul class="store-grid">
                        <li class="active"><i class="fa fa-th"></i></li>
                        <li><a href="#"><i class="fa fa-th-list"></i></a></li>
                    </ul>
                </div>
                <!-- /store top filter -->

                <!-- store products -->
                <div class="row">
                    <c:forEach items="${listP}" var="o">
                        <!-- product -->
                        <div class="col-md-4 col-xs-6">
                            <div class="product">
                                <a href="product?id=${o.id}">
                                    <div class="product-img">
                                        <img src="${o.src}" alt="${o.name}">
                                    </div>
                                </a>
                                <div class="product-body">
                                    <p class="product-category">${o.type}</p>
                                    <h3 class="product-name"><a href="product?id=${o.id}">${o.name}</a></h3>
                                    <h4 class="product-price">$${o.price}</h4>
                                    <div class="product-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                    <div class="product-btns">
                                        <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                        <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                        <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                    </div>
                                </div>
                                <div class="add-to-cart">
                                    <a href="cart?action=add&id=${o.id}">
                                        <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- /product -->
                    </c:forEach>
                </div>
                <!-- /store products -->

                <!-- store bottom filter -->
                <div class="store-filter clearfix">
                    <span class="store-qty">Showing 20-100 products</span>
                    <ul class="store-pagination">
                        <c:if test="${tag > 1}">
                            <li><a href="trang-chu?index=${tag-1}"><i class="fa fa-angle-left"></i></a></li>
                        </c:if>
                        <c:forEach begin="1" end="${endP}" var="i">
                            <a href="trang-chu?index=${i}"><li class="${tag == i?"active":""}">${i}</li></a>
                        </c:forEach>
                        <c:if test="${tag < endP}">
                            <li><a href="trang-chu?index=${tag+1}"><i class="fa fa-angle-right"></i></a></li>
                        </c:if>
                    </ul>
                </div>
                <!-- /store bottom filter -->
            </div>
            <!-- /STORE -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->
</body>
</html>
