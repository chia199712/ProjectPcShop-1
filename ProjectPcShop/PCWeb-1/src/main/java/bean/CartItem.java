package bean;
import java.math.BigDecimal;

public class CartItem {
	
	    private int id;
	    private String name;
	    private int count;
	    private int price;
	    private int totalPrice;
	   public CartItem(){
		   
	   }
	   
	   public CartItem(int item_ID, int item_count) {
		      this.id = item_ID;
		      this.count = item_count;
		   }

	    public CartItem(int id, String name, int count, int price) {
	        this.id = id;
	        this.name = name;
	        this.count = count;
	        this.price = price;;
	    }
	    
	    public void setC_itemID(int id) {
	    	this.id = id;
	    }
	    
	    public int getC_itemID() {
	    	return id;
	    }
	    
	    public void setC_itemName(String name) {
	    	this.name = name;
	    }
	    
	    public String getC_itemName() {
	    	return name;
	    }
	    
	    public void setC_itemCount(int count) {
	    	this.count = count;
	    }
	    
	    public int getC_itemCount() {
	    	return count;
	    }
	    
	    public void setC_itemPrice(int count) {
	    	this.count = count;
	    }
	    
	    public int getC_itemPrice() {
	    	return price;
	    }
	    
//	    public String getC_itemName() {
//	    	return shoppingDB.getItemPage_name();
//	    }
	    
}