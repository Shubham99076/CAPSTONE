package com.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemsUploadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemsUploadingApplication.class, args);
		updateItems();
	}

	
	public static void updateItems() {
		   String jdbcURL = "jdbc:mysql://localhost:3306/capstonedb";
	        String username = "root";
	        String password = "root";
	 
	        String excelFilePath = "C:\\Users\\ASUS\\Videos\\CAPESTONE\\ItemsUploading\\src\\main\\resources\\Items1.xlsx";
	 
	        int batchSize = 20;
	 
	        Connection connection = null;
	 
	        try {
	            long start = System.currentTimeMillis();
	             
	            FileInputStream inputStream = new FileInputStream(excelFilePath);
	 
	            Workbook workbook = new XSSFWorkbook(inputStream);
	 
	            Sheet firstSheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = firstSheet.iterator();
	 
	            connection = DriverManager.getConnection(jdbcURL, username, password);
	            connection.setAutoCommit(false);
	  
	            String sql = "INSERT INTO item (item_name, price,category_name,qty) VALUES (?, ?, ?, ?)";
	            PreparedStatement statement = connection.prepareStatement(sql);    
	             
	            int count = 0;
	             
	            rowIterator.next(); // skip the header row
	             
	            while (rowIterator.hasNext()) {
	                Row nextRow = rowIterator.next();
	                Iterator<Cell> cellIterator = nextRow.cellIterator();
	 
	                while (cellIterator.hasNext()) {
	                    Cell nextCell = cellIterator.next();
	                    System.out.println(nextCell);
	 
	                    int columnIndex = nextCell.getColumnIndex();
	                    System.out.println("col"+columnIndex);
	 
	                    switch (columnIndex) {
	                    case 0:
	                        String item_name = nextCell.getStringCellValue();
	                        statement.setString(1, item_name);
	                        break;
	                    case 1:
	                    	  int price = (int) nextCell.getNumericCellValue();
	                        statement.setInt(2, price);
	                        
	                    case 2:                    	
	                    		System.out.println(nextCell.getNumericCellValue());
//	                    	 String category_name = nextCell.getNumericCellValue();	                    
//	                    	 statement.setString(3, category_name);
	                    	 
	                        
	                    case 3:
	                    	
	                    	 int qty = (int) nextCell.getNumericCellValue();
		                     statement.setInt(4, qty);

	                       
	                   
	                    }
	 
	                }
	                 
	                statement.addBatch();
	                 
	                if (count % batchSize == 0) {
	                    statement.executeBatch();
	                }              
	 
	            }
	 
	            workbook.close();
	             
	            // execute the remaining queries
	            statement.executeBatch();
	  
	            connection.commit();
	            connection.close();
	             
	            long end = System.currentTimeMillis();
	            System.out.printf("Import done in %d ms\n", (end - start));
	             
	        } catch (IOException ex1) {
	            System.out.println("Error reading file");
	            ex1.printStackTrace();
	        } catch (SQLException ex2) {
	            System.out.println("Database error");
	            ex2.printStackTrace();
	        }
	}
}
