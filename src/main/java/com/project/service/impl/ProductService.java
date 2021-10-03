package com.project.service.impl;

import com.project.dao.IProductDAO;
import com.project.model.Brand;
import com.project.pojo.Products;
import com.project.service.IProductService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.inject.Inject;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;

public class ProductService implements IProductService {
    @Inject
    private IProductDAO productDAO;
    @Override
    public List<Products> getAllProductSelling() {
        return productDAO.getAllProductSelling();
    }

    @Override
    public Products findOneProductById(int id) {
        return productDAO.findOneProductById(id);
    }

    @Override
    public List<Products> get4RelatedProducts(Products product) {
        return productDAO.get4RelatedProducts(product);
    }

    @Override
    public List<Products> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public List<Products> getAllProductSorted(String type, String method) {
        return productDAO.getAllProductSorted(type,method);
    }

    @Override
    public List<Products> getProductsBySearch(String search) {
        return productDAO.getProductsBySearch(search);
    }

    @Override
    public int endPage(int numbersOfPage) {
        return productDAO.endPage(numbersOfPage);
    }

    @Override
    public void writeFileExcel(Workbook workbook) {
        List<Products> list = productDAO.getAllProducts();
        Sheet sheet = workbook.createSheet("Products");
        // write header of file
        int rowTitleIndex = 4;
        writeHeader(sheet,rowTitleIndex);
        // write body of file
        int rowIndex = 5;// write from row 3
        for (Products product : list) {
            Row row = sheet.createRow(rowIndex);
            // write each cell / row
            Cell cell = row.createCell(0);
            cell.setCellValue(product.getId());
            cell = row.createCell(1);
            cell.setCellValue(product.getName());
            cell = row.createCell(2);
            cell.setCellValue(product.getDescription());
            cell = row.createCell(3);
            cell.setCellValue(product.getPrice());
            cell = row.createCell(4);
            cell.setCellValue(product.getImportPrice());
            cell = row.createCell(5);
            cell.setCellValue(product.getSrc());
            cell = row.createCell(6);
            cell.setCellValue(product.getType());
            cell = row.createCell(7);
            cell.setCellValue(product.getBrand());
            cell = row.createCell(8);
            cell.setCellValue(product.getStatus());
            cell = row.createCell(9);
            cell.setCellValue(product.getImportDate().toString());
            rowIndex++;
        }
        // auto resize column width
        int numberOfColumn =sheet.getRow(4).getPhysicalNumberOfCells();
        autosizeColumn(sheet,numberOfColumn);
    }

    @Override
    public int addProduct(Products product) {
        return productDAO.addProduct(product);
    }

    @Override
    public void addProductFromFileExcell(XSSFWorkbook wookbook) {
        XSSFSheet sheet = wookbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        //do read
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            int rowNumber = row.getRowNum();
            if (rowNumber >= 5){//read from row = 5
                Products product = new Products();
                product.setName(row.getCell(1).getStringCellValue());
                product.setDescription(row.getCell(2).getStringCellValue());
                product.setPrice((float) row.getCell(3).getNumericCellValue());
                product.setImportPrice((float) row.getCell(4).getNumericCellValue());
                product.setSrc(row.getCell(5).getStringCellValue());
                product.setType(row.getCell(6).getStringCellValue());
                product.setBrand(row.getCell(7).getStringCellValue());
                product.setStatus(row.getCell(8).getBooleanCellValue());
                product.setImportDate(new Date(System.currentTimeMillis()));
                productDAO.addProduct(product);
            }
        }
    }

    @Override
    public List<Products> getTop3NewProducts() {
        return productDAO.getTop3NewProducts();
    }

    @Override
    public List<Brand> getAllBrand() {
        return productDAO.getAllBrand();
    }

    @Override
    public List<Products> pagingProduct(int index, int numberProduct) {
        return productDAO.pagingProduct(index,numberProduct);
    }

    @Override
    public List<Products> findAllProductBySearch(String category, String search) {
        return productDAO.findAllProductBySearch(category,search);
    }

    @Override
    public int updateProduct(Products product) {
        return productDAO.updateProduct(product);
    }

    @Override
    public List<Products> getProductByNavigation(String type) {
        return productDAO.getProductByNavigation(type);
    }

    // Auto resize column width
    private void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
    // write header of file excel
    private void writeHeader(Sheet sheet, int rowTitleIndex) {
        Row row = sheet.createRow(rowTitleIndex);

        // Create cells
        Cell cell = row.createCell(0);
        cell.setCellValue("ID");

        cell = row.createCell(1);
        cell.setCellValue("NAME");

        cell = row.createCell(2);
        cell.setCellValue("DESCRIPTION");

        cell = row.createCell(3);
        cell.setCellValue("SALE PRICE");

        cell = row.createCell(4);
        cell.setCellValue("IMPORT PRICE");

        cell = row.createCell(5);
        cell.setCellValue("IMAGE SOURCE");

        cell = row.createCell(6);
        cell.setCellValue("TYPE");

        cell = row.createCell(7);
        cell.setCellValue("BRAND");

        cell = row.createCell(8);
        cell.setCellValue("STATUS");

        cell = row.createCell(9);
        cell.setCellValue("DATE");
    }
}
