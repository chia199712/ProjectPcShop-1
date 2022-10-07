package bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class shoppingDB implements Serializable {
	
	private static Integer[] pro_no;	 
	private static String[] pro_name;
	private static Integer[] pro_price;	 
	private static Integer[] pro_amount;	
	private static String[] pro_info;
	private static String[] pro_image;
	
	private static int itemInd;
	
	
	public static void setPro_no(Integer[] ppro_no) {
		pro_no = ppro_no;
	}
	
	public static void setPro_name(String[] ppro_name) {
		pro_name=ppro_name;
	}
	
	public static void setPro_price(Integer[] ppro_price) {
		pro_price = ppro_price;
	}

	public static void setPro_amount(Integer[] ppro_amount) {
		pro_amount = ppro_amount;
	}
	
	public static void setPro_info(String[] ppro_info) {
		pro_info=ppro_info;
	}
	
	public static void setPro_image(String[] ppro_image) {
		pro_image=ppro_image;
	}
	
	public static void setItemPageIndex(int Ind) {
		itemInd = Ind;
	}
		 
	/** Return the size of the database table */
	public static int size() {
	       return pro_no.length;
	}
		 
	/** Return the number of the product */
    public static int getPro_no(int shopNO) {
	      return pro_no[shopNO].intValue();
    }
	
	/** Return the title of the given bookID */
	public static String getPro_name(int shopNO) {
	       return pro_name[shopNO];
	}
		 
	/** Return the price of the product */
    public static int getPro_price(int shopNO) {
	      return pro_price[shopNO].intValue();
    }
    
    /** Return the price of the product */
    public static int getPro_amount(int shopNO) {
	      return pro_amount[shopNO].intValue();
  }
    
    /** Return the info of the product */
    public static String getPro_info(int shopNO) {
	      return pro_info[shopNO];
    }
    
    /** Return the image url of the product */
    public static String getPro_image(int shopNO) {
	      return pro_image[shopNO];
  }
    
    
    
    public static int getItemPageIndex() {
    	return itemInd;
    }
    
    public static int getItemPage_no() {
    	return pro_no[itemInd].intValue();
    }
    
    public static String getItemPage_image() {
    	return pro_image[itemInd];
    }
    
    public static String getItemPage_name() {
    	return pro_name[itemInd];
    }
    
    public static int getItemPage_amount() {
	      return pro_amount[itemInd].intValue();
}
    
    public static int getItemPage_price() {
	      return pro_price[itemInd].intValue();
  }
    
    public static String getItemPage_info() {
    	return pro_info[itemInd];
    }
    
    

	public void setPro_image(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_price(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_info(BigDecimal bigDecimal) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_amount(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_no(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_name(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_no(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_name(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_price(BigDecimal bigDecimal) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_info(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setPro_image(String string) {
		// TODO Auto-generated method stub
		
	}

	public void add(shoppingDB shoppingDB) {
		// TODO Auto-generated method stub
		
	}

}