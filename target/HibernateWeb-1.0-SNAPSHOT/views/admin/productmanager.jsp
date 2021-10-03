<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product manager</title>
</head>
<body>
<section class="content">
    <div class="container-fluid">
        <div>
            <div style="display: inline">
                <form action="admin-product?action=filter" method="post" style="float: left">
                    <select name="type">
                        <option value="id">ID</option>
                        <option value="name">Name</option>
                        <option value="saleprice">Saleprice</option>
                        <option value="importprice">Importprice</option>
                        <option value="brand">Brand</option>
                        <option value="date">Date</option>
                        <option value="status">Status</option>
                    </select>
                    <select name="method">
                        <option value="asc">Ascending</option>
                        <option value="desc">Descending</option>
                    </select>
                    <input type="submit" value="filter">
                </form>
            </div>
            <div>
                <form action="admin-product?action=search" method="post" style="float: right">
                    <input name="search" placeholder="Search" type="text" value="${search}">
                    <input type="submit" value="search">
                </form>
            </div>
            <br>
            <br>
            <div class="row">
                <div style="display: inline-block; margin-right: 10px">
                    <a href="admin-export">
                        <i class="fa fa-print bigger-130" style="color: green">Export File </i>
                    </a>
                </div>
                <div style="display: inline-block;margin-right: 10px">
                    <a href="#" style="float: right">
                        <a href="#addModal" class="trigger-btn" data-toggle="modal">
                            <i class="fa fa-cart-plus bigger-130" style="color: green">Add</i>
                        </a>
                    </a>
                </div>
                <div style="display: inline-block">
                    <a href="#importModal" class="trigger-btn" data-toggle="modal">
                        <i class="fa fa-file-import bigger-130" style="color: green">Import File</i>
                    </a>
                </div>
            </div>
            <div style="text-align: center">
                <c:if test="${not empty mess}">
                    <div class="alert alert-${result == 1 ? "success" : "danger"}" role="alert" style="display: inline">
                            ${mess}
                    </div>
                </c:if>
            </div>
            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th class="center">#ID</th>
                    <th>Product Name</th>
                    <th>Saleprice</th>
                    <th>Import Price</th>
                    <th class="hidden-480">Brand</th>

                    <th>
                        <i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>
                        Day added
                    </th>
                    <th class="hidden-480">Status</th>

                    <th></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${listP}" var="o">
                    <tr>
                        <td class="center">
                                ${o.id}
                        </td>
                        <td>
                            <a href="#editModal${o.id}" class="trigger-btn" data-toggle="modal">${o.name}</a>
                        </td>
                        <td>$${o.price}</td>
                        <td>$${o.importPrice}</td>
                        <td class="hidden-480">${o.brand}</td>
                        <td>${o.importDate}</td>

                        <td class="hidden-480">
                            <span class="label label-sm label-success">${o.status}</span>
                        </td>
                    </tr>

                    <!-- Modal Edit Product -->
                    <div id="editModal${o.id}" class="modal fade">
                        <div class="modal-dialog modal-login">
                            <div class="modal-content">
                                <form action="admin-product?action=edit&id=${o.id}" method="post">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">&times;
                                        </button>
                                    </div>
                                    <div class="form-group">
                                        <label><h3>#ID</h3></label>
                                        <input name="id" class="form-control" readonly value="${o.id}">
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label><h3>Product Name</h3></label>
                                            <textarea name="name" class="form-control" required="required"
                                                      cols="10" rows="1">${o.name}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label><h3>Description</h3></label>
                                            <textarea name="description" class="form-control" cols="10"
                                                      rows="10" required="required">${o.description}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label><h3>Import Price($)</h3></label>
                                            <input name="importprice" type="number" required="required" class="form-control" value="${o.price}">
                                        </div>
                                        <div class="form-group">
                                            <label><h3>Sale Price($)</h3></label>
                                            <input name="saleprice" type="number" required="required" class="form-control" value="${o.importPrice}">
                                        </div>
                                        <div class="form-group">
                                            <label><h3>Image</h3></label>
                                            <textarea name="image" class="form-control" cols="10"
                                                      rows="2" required="required">${o.src}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label><h3>Type</h3></label>
                                            <textarea name="type" class="form-control" cols="10"
                                                      rows="1" required="required">${o.type}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label><h3>Brand</h3></label>
                                            <textarea name="brand" class="form-control"
                                                      cols="10" rows="1"
                                                      required="required">${o.brand}</textarea>
                                        </div>
                                        <div class="modal-footer justify-content-between">
                                            <label class="form-check-label" style="float: left">
                                                <input type="checkbox" name="status" ${o.status == true ? "checked" : ""}>Enable
                                                Selling</label>
                                            <input type="submit" class="btn btn-primary"
                                                   value="Update">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>
            <div class="row">
                <div style="display: inline-block">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>

        </div>
    </div>
    <!-- Modal Add Product -->
    <div id="addModal" class="modal fade">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <form action="admin-product?action=add" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">&times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label><h3>Product Name</h3></label>
                            <textarea name="name" class="form-control" required="required"
                                      cols="10" rows="1"></textarea>
                        </div>
                        <div class="form-group">
                            <label><h3>Description</h3></label>
                            <textarea name="description" class="form-control" cols="10"
                                      rows="10" required="required"></textarea>
                        </div>
                        <div class="form-group">
                            <label><h3>Import Price($)</h3></label>
                            <input name="importprice" type="number" required="required" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><h3>Sale Price($)</h3></label>
                            <input name="saleprice" type="number" required="required" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><h3>Image</h3></label>
                            <textarea name="image" class="form-control" cols="10"
                                      rows="2" required="required"></textarea>
                        </div>
                        <div class="form-group">
                            <label><h3>Type</h3></label>
                            <textarea name="type" class="form-control" cols="10"
                                      rows="1" required="required"></textarea>
                        </div>
                        <div class="form-group">
                            <label><h3>Brand</h3></label>
                            <textarea name="brand" class="form-control"
                                      cols="10" rows="1"
                                      required="required"></textarea>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <label class="form-check-label" style="float: left"><input
                                    type="checkbox" name="status">Enable
                                Selling</label>
                            <input type="submit" class="btn btn-primary"
                                   value="ADD">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal Import Product -->
    <div id="importModal" class="modal fade">
        <div class="modal-dialog modal-login">
            <div class="modal-content">
                <form action="admin-import" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" placeholder="File">
                    <button type="submit">Submit</button>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
