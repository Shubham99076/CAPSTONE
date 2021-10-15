package com.report.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.report.config.DbConfig;

@Component
public class ReportService {
	Connection conn=null;
	
	public String findTotalInvoiceAmount() {
		
		JSONObject jsonObject=new JSONObject();
		
		try {
			
            conn=DbConfig.getConnection();
            String s="select * from total_balance;";
          PreparedStatement ps=conn.prepareStatement(s);
        ResultSet rs=  ps.executeQuery();
     
       
        while(rs.next()) {      
       		
       	
       	 jsonObject.put("total_amount", rs.getString("total_amount"));
       	 jsonObject.put("recieving_amount", rs.getString("remaining_amount"));
       	 jsonObject.put("remaing_amount", rs.getString("paid_amount"));
       	
       
        }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
	return jsonObject.toString();	
	
		
	}
	
	
	
	
public String findInvoiceByCustomer(String cust_data) {
		
		JSONArray jsonArray=new JSONArray();
		
		try {
			
      conn=DbConfig.getConnection();
     String s="select i.invoiceid,i.description,i.payment_status,i.amount,i.created_at,i.paid_amount,i.remaing_amount,i.updated_at,i.quantity,po.poid,po.po_status,o.orderid,o.item_name,o.category_name,o.cust_email,o.cust_mob,o.cust_name from invoice i inner join po_order_tbl po on i.poid=po.poid inner join order_tbl o on po.orderid=o.orderid where cust_name like '%"+cust_data+"%' or cust_email like '%"+cust_data+"%' or cust_mob like '%"+cust_data+"%'";
            		
          PreparedStatement ps=conn.prepareStatement(s);
        ResultSet rs=  ps.executeQuery();
        
     
       
        while(rs.next()) {      
       		
        	JSONObject jsonObject=new JSONObject();
       	 jsonObject.put("invoiceid", rs.getString("invoiceid"));
       	 jsonObject.put("orderid", rs.getString("orderid"));
       	 jsonObject.put("poid", rs.getString("poid"));
       	 jsonObject.put("amount", rs.getString("amount"));
       	 jsonObject.put("paid_amount", rs.getString("paid_amount"));
       	 jsonObject.put("remaing_amount", rs.getString("remaing_amount"));
       	 jsonObject.put("po_status", rs.getString("po_status"));
       	 jsonObject.put("quantity", rs.getString("quantity"));
      	 jsonObject.put("payment_status", rs.getString("payment_status"));
      	 jsonObject.put("description", rs.getString("description"));
       	 jsonObject.put("item_name", rs.getString("item_name"));
       	 jsonObject.put("category_name", rs.getString("category_name"));
       	 jsonObject.put("cust_name", rs.getString("cust_name"));
       	 jsonObject.put("cust_email", rs.getString("cust_email"));
       	 jsonObject.put("cust_mob", rs.getString("cust_mob"));
       	 jsonObject.put("created_at", rs.getString("created_at"));
       	 jsonObject.put("updated_at", rs.getString("updated_at"));
       	 
       	jsonArray.put(jsonObject);
       	
       
        }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
	return jsonArray.toString();	
	
		
	}



public String findPOByCustomer(String cust_data) {
	
	JSONArray jsonArray=new JSONArray();
	
	try {
		
  conn=DbConfig.getConnection();
 String s="select po.poid, po.po_status,o.orderid,o.item_name,o.category_name,o.qty,o.price,o.cust_email,o.cust_mob,o.cust_name,po.created_at,po.updated_at from po_order_tbl po inner join order_tbl o on po.orderid=o.orderid where cust_name like '%"+cust_data+"%' or cust_email like '%"+cust_data+"%' or cust_mob like '%"+cust_data+"%'";
        		
      PreparedStatement ps=conn.prepareStatement(s);
    ResultSet rs=  ps.executeQuery();
    
   
    while(rs.next()) {      
   		
    	JSONObject jsonObject=new JSONObject();
   	 jsonObject.put("poid", rs.getString("poid"));
   	 jsonObject.put("orderid", rs.getString("orderid"));
   	 jsonObject.put("amount", rs.getString("price"));  
   	 jsonObject.put("po_status", rs.getString("po_status"));
   	 jsonObject.put("quantity", rs.getString("qty"));
  	 jsonObject.put("po_status", rs.getString("po_status"));
   	 jsonObject.put("item_name", rs.getString("item_name"));
   	 jsonObject.put("category_name", rs.getString("category_name"));
   	 jsonObject.put("cust_name", rs.getString("cust_name"));
   	 jsonObject.put("cust_email", rs.getString("cust_email"));
   	 jsonObject.put("cust_mob", rs.getString("cust_mob"));
   	 jsonObject.put("created_at", rs.getString("created_at"));
   	 jsonObject.put("updated_at", rs.getString("updated_at"));
   	 
   	jsonArray.put(jsonObject);
   	
   
    }
} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
}


return jsonArray.toString();	

	
}
	

}
