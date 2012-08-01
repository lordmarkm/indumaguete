package baldwin.dgte.persistence;

import java.util.List;

import baldwin.dgte.model.Item;
import baldwin.dgte.utils.DgteUtil.Filter;

public interface DgteDao {
	//C
	public Item newItem(Item item);
	
	//R
	public Item getItem(String domain);
	List<Item> getAllItems(Filter filter, int firstResult, int maxResult);
	public List<Item> findByProperty(String property, String value);

	//U
	public void updateItem(Item item);
	public void updatePrimaryDetails(Item item, Item newProperties) throws Exception;
	
	//D
	public void deleteItem(Item item);
}