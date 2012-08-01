package baldwin.dgte.service;

import java.util.List;

import baldwin.dgte.model.Item;
import baldwin.dgte.utils.DgteUtil.Filter;

public interface ViewItemService {
	public List<Item> getAllItems(Filter filter, int firstResult, int maxResult);
	public Item getItem(String domain);
}
