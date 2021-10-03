<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <h3 class="breadcrumb-header">Checkout</h3>
                <ul class="breadcrumb-tree">
                    <li><a href="#">Home</a></li>
                    <li class="active">Checkout</li>
                </ul>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <form action="checkout" method="post">
                <div class="col-md-7">
                    <!-- Billing Details -->
                    <div class="billing-details">
                        <div class="section-title">
                            <h3 class="title">Information Order</h3>
                        </div>
                        <div class="form-group">
                            <input class="input" type="text" name="fullname" placeholder="Full Name"
                                   value="${sessionScope.acc.name}">
                        </div>
                        <div class="form-group">
                            <input class="input" type="email" name="email" placeholder="Email" required="required"
                                   value="${sessionScope.acc.mail}">
                        </div>
                        <div class="form-group">
                            <input class="input" type="text" name="address" placeholder="Address" required="required"
                                   value="${sessionScope.acc.address}">
                        </div>
                        <div class="form-group">
                            <input class="input" type="text" name="city" placeholder="City">
                        </div>
                        <div class="form-group">
                            <input class="input" type="text" name="country" placeholder="Country">
                        </div>
                        <div class="form-group">
                            <input class="input" type="text" name="zipcode" placeholder="ZIP Code">
                        </div>
                        <div class="form-group">
                            <input class="input" type="tel" name="phone" placeholder="Telephone" required="required"
                                   value="${sessionScope.acc.phone}">
                        </div>
                    </div>
                    <!-- /Billing Details -->
                    <!-- Order notes -->
                    <div class="order-notes">
                        <textarea class="input" name="note" placeholder="Order Notes"></textarea>
                    </div>
                    <!-- /Order notes -->
                </div>

                <!-- Order Details -->
                <div class="col-md-5 order-details">
                    <div class="section-title text-center">
                        <h3 class="title">Your Order</h3>
                    </div>
                    <div class="order-summary">
                        <div class="order-col">
                            <div><strong>PRODUCT</strong></div>
                            <div><strong>TOTAL</strong></div>
                        </div>
                        <div class="order-products">
                            <c:forEach items="${sessionScope.cart.items}" var="o">
                                <div class="order-col">
                                    <div>${o.number}x ${o.name}</div>
                                    <div>$${Math.round(o.price*o.number * 100.0) / 100.0}</div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="order-col">
                            <div>Shiping</div>
                            <div><strong>FREE</strong></div>
                        </div>
                        <div class="order-col">
                            <div><strong>TOTAL</strong></div>
                            <div><strong class="order-total">$${sessionScope.cart.amount}</strong></div>
                        </div>
                    </div>
                    <c:if test="${sessionScope.cart.items.size() > 0}">
                        <input type="submit" value="Place order" class="primary-btn order-submit">
                    </c:if>
                </div>
            </form>
            <!-- /Order Details -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
</body>
</html>
