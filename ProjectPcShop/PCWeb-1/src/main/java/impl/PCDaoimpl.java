package impl;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import bean.shoppingDB;

public abstract class PCDaoimpl implements PCDAO {
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    Connection con = null;
	private shoppingDB shoppingDB;

    @Override
    public int add(shoppingDB shoppingDB) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTCs3ed4x321qq");
            String sql = "select * from cus_info where cus_id=? and cus_pwd=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, shoppingDB.getPro_name(0));
            preparedStatement.setString(2, shoppingDB.getPro_name(0));
            preparedStatement.setInt(3, shoppingDB.getPro_price(0));
            preparedStatement.setInt(4, shoppingDB.getPro_amount(0));
            preparedStatement.setString(5, shoppingDB.getPro_info(0));
            preparedStatement.setString(6, shoppingDB.getPro_image(0));
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 1;
    }


    public int deletepro_id(Integer id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTCs3ed4x321qq");
            String sql ="delete from shopbook where id=";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 1;
    }

    @Override
    public int updateshoppingDB(shoppingDB pro) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTCs3ed4x321qq");            
            String sql = "update shopbook set name1=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
            String user1 = "hr";
            String pass1 = "hr";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, shoppingDB.getPro_name(0));
            preparedStatement.setString(2, shoppingDB.getPro_name(0));
            preparedStatement.setInt(3, shoppingDB.getPro_price(0));
            preparedStatement.setInt(4, shoppingDB.getPro_amount(0));
            preparedStatement.setString(5, shoppingDB.getPro_info(0));
            preparedStatement.setString(6, shoppingDB.getPro_image(0));
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public shoppingDB queryId11(Integer id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8", "root", "123456");
            String sql = "update shopbook set name1=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	shoppingDB.setPro_name(rs.getInt("ppro_no"));
            	shoppingDB.setPro_no(rs.getString("ppro_name"));
            	shoppingDB.setPro_amount(rs.getString("ppro_amount"));
            	shoppingDB.setPro_info(rs.getBigDecimal("ppro_price"));
            	shoppingDB.setPro_price(rs.getInt("ppro_info"));
            	shoppingDB.setPro_image(rs.getInt("ppro_image"));            	
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		return shoppingDB;
    }

    @Override
    public List<shoppingDB> queryshoppingDB() {
        List<shoppingDB> books = new LinkedList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTCs3ed4x321qq");
            String sql = "select id,name1,author,price,sales,stock,img_path imgPath from shop";
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	shoppingDB shoppingDB = new shoppingDB();
            	shoppingDB.setPro_no(rs.getInt("Pro_no"));
            	shoppingDB.setPro_name(rs.getString("Pro_name"));
            	shoppingDB.setPro_amount(rs.getString("Pro_amount"));
            	shoppingDB.setPro_price(rs.getBigDecimal("Pro_price"));
            	shoppingDB.setPro_info(rs.getInt("Pro_info"));
            	shoppingDB.setPro_image(rs.getString("Pro_image"));
            	shoppingDB.add(shoppingDB);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return books;
    }

    @Override
    public int queryForPageTotalCount() {
        Statement statement=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTCs3ed4x321qq");
            String sql = "select * from shop";
            statement=con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.last();
            return rs.getRow();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public List<shoppingDB> queryForPageItems(Integer begin, Integer pageSize) {
        List<shoppingDB> shoppingDB = new LinkedList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTCs3ed4x321qq");
            String sql = "select * from cus_info where cus_id=? and cus_pwd=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,begin);
            preparedStatement.setInt(2,pageSize);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	shoppingDB shoppingDB1 = new shoppingDB();
            	shoppingDB1.setPro_no(rs.getInt("Pro_no"));
            	shoppingDB1.setPro_name(rs.getString("pro_name"));
            	shoppingDB1.setPro_amount(rs.getString("pro_amount"));
            	shoppingDB1.setPro_price(rs.getBigDecimal("pro_price"));
            	shoppingDB1.setPro_info(rs.getInt("pro_info"));
            	shoppingDB1.setPro_image(rs.getString("pro_image"));
            	shoppingDB.add(shoppingDB1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return shoppingDB;
    }

@Override
public int deleteId(Integer id) {
	// TODO Auto-generated method stub
	return 0;
}

public int update1(shoppingDB pro) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public shoppingDB queryId1(Integer id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<shoppingDB> query() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int addpro(shoppingDB pro) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int update(shoppingDB pro) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public shoppingDB queryId(Integer id) {
	// TODO Auto-generated method stub
	return null;
}
}

