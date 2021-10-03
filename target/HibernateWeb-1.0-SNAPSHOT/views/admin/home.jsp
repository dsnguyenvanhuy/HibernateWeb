<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Main content -->
<section class="content">
    <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <h1>Today</h1>
        <div class="row">
            <div class="col-lg-3 col-6">
                <!-- small box -->
                <div class="small-box bg-info">
                    <div class="inner">
                        <h3>${orderNumbers}</h3>

                        <p>Orders</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-bag"></i>
                    </div>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-6">
                <!-- small box -->
                <div class="small-box bg-success">
                    <div class="inner">
                        <h3>$${revenue}</h3>

                        <p>Doanh thu</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-stats-bars"></i>
                    </div>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-6">
                <!-- small box -->
                <div class="small-box bg-warning">
                    <div class="inner">
                        <h3>$${cost}</h3>

                        <p>Chi phí</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-person-add"></i>
                    </div>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-6">
                <!-- small box -->
                <div class="small-box bg-danger">
                    <div class="inner">
                        <h3>$${profit}</h3>

                        <p>Lợi Nhuận</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-pie-graph"></i>
                    </div>
                </div>
            </div>
            <!-- ./col -->
        </div>
        <!-- /.row -->
        <!-- Main row -->
        <h1>Filter</h1>
        <div class="row">
            <form method="post" action="admin-filter">
                From : <input name="from" type="date" value="${fromF}">
                To : <input name="to" type="date" value="${toF}">
                <input type="submit" value="Filter">
            </form>
        </div>
        <br>

        <c:if test="${requestScope.orderNumbersF != null}">
            <div class="row">
                <div class="col-lg-3 col-6">
                    <!-- small box -->
                    <div class="small-box bg-info">
                        <div class="inner">
                            <h3>${requestScope.orderNumbersF}</h3>

                            <p>Orders</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-bag"></i>
                        </div>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-6">
                    <!-- small box -->
                    <div class="small-box bg-success">
                        <div class="inner">
                            <h3>$${revenueF}</h3>

                            <p>Doanh thu</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-stats-bars"></i>
                        </div>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-6">
                    <!-- small box -->
                    <div class="small-box bg-warning">
                        <div class="inner">
                            <h3>$${costF}</h3>

                            <p>Chi phí</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-person-add"></i>
                        </div>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-6">
                    <!-- small box -->
                    <div class="small-box bg-danger">
                        <div class="inner">
                            <h3>$${profitF}</h3>

                            <p>Lợi Nhuận</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-pie-graph"></i>
                        </div>
                    </div>
                </div>
                <!-- ./col -->
            </div>
            <div class="row">
                <!-- Left col -->
                <section class="col-lg-7 connectedSortable">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">
                                <i class="fas fa-exchange-alt mr-1"></i>
                                Orders
                            </h3>
                            <br>
                            <table  class="table table-striped table-bordered table-hover">
                                <thead>
                                <th>OrderID</th>
                                <th>Order Date</th>
                                <th>Email</th>
                                <th>Status</th>
                                </thead>
                                <tbody>
                                <c:forEach items="${listO}" var="o">
                                    <tr>
                                        <td>${o.orderId}</td>
                                        <td>${o.orderTime}</td>
                                        <td>${o.userMail}</td>
                                        <td>${o.orderStatus}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
                <section class="col-lg-5 connectedSortable">
                    <div class="card bg-gradient-primary">
                        <div class="card-header border-0">
                            <h3 class="card-title">
                                <i class="fas fa-exchange-alt mr-1"></i>
                                Top 10 products
                            </h3>
                            <br>
                            <table  class="table table-striped table-bordered table-hover">
                                <thead>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Sale Price</th>
                                <th>Import Price</th>
                                </thead>
                                <tbody>
                                <c:forEach items="${listP}" var="o">
                                    <tr>
                                        <td>${o.id}</td>
                                        <td>${o.name}</td>
                                        <td>${o.price}</td>
                                        <td>${o.importPrice}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </div>
        </c:if>
        <!-- /.row (main row) -->
    </div><!-- /.container-fluid -->
</section>
<!-- /.content -->
</body>
</html>
