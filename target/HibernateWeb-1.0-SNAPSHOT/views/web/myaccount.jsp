<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>My Account</title>
</head>
<body>
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <h3 class="breadcrumb-header">My Account</h3>
                <ul class="breadcrumb-tree">
                    <li><a href="#">Home</a></li>
                    <li class="active">My Account</li>
                </ul>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>

<div class="section">
    <!-- container -->
    <div class="container" style="text-align: center">
        <c:if test="${not empty listO}">
            <table class="table table-bordered table-hover">
                <tr>
                    <th>Order ID</th>
                    <th>Status</th>
                    <th>Order time</th>
                    <th>Discount code</th>
                    <th>Address</th>
                    <th>Receiver</th>
                    <th>Totals</th>
                    <th>Detail</th>
                </tr>
                <c:forEach items="${listO}" var="o">
                    <tr>
                        <td>${o.orderId}</td>
                        <td>${o.orderStatus == 0 ? "delivery" : "delivered"}</td>
                        <td>${o.orderTime}</td>
                        <td>${o.orderDiscountCode}</td>
                        <td>${o.orderAddress}</td>
                        <td>${o.orderReceiver}</td>
                        <td>$${o.totals}</td>
                        <td><a href="myaccount?oid=${o.orderId}" style="color: blue">Click</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${not empty listP}">
            <table class="table table-bordered table-hover">
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Type</th>
                    <th>Brand</th>
                    <th>Image</th>
                </tr>
                <c:forEach items="${listP}" var="o">
                    <tr>
                        <td>${o.id}</td>
                        <td>${o.name}</td>
                        <td>$${o.price}</td>
                        <td>${o.type}</td>
                        <td>${o.brand}</td>
                        <td><img src="${o.src}" alt="${o.name}" style="width: 20%"></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
    <!-- /container -->
</div>
</body>
</html>
