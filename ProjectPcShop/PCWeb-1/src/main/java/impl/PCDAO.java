package impl;
import java.util.List;

import bean.shoppingDB;



	/**
	 * @createTime 2020/11/24 14:51
	 * @description
	 */

	public interface PCDAO {

	    public int addpro(shoppingDB pro);
	    public int deleteId(Integer id);
	    public int update(shoppingDB pro);
	    public shoppingDB queryId(Integer id);
	    public List<shoppingDB> query();
	    public int queryForPageTotalCount();
	    public List<shoppingDB> queryForPageItems(Integer begin, Integer pageSize);
		int add(shoppingDB shoppingDB);
		shoppingDB queryId1(Integer id);
		List<shoppingDB> queryshoppingDB();
		int updateshoppingDB(shoppingDB pro);
	}
