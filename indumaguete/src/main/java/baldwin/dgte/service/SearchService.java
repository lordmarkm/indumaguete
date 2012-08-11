package baldwin.dgte.service;

import java.util.List;
import java.util.Map;

import baldwin.dgte.model.Item;

public interface SearchService {
	/**
	 * 1. Search names 
	 * 2. Search descriptions
	 *  
	 * @param term
	 * @param category
	 *
	 * @return
	 * Required:
	 *  1. type - one of [item, page]
	 *  2. title - item name or page title
	 *  3. text - item description or some page text
	 *  4. sourceDomain - item domain
	 *  5. sourceName - owner name or title
	 *  
	 *  Optional:
	 *  6. pageDomain
	 *  7. imageUrl
	 */
	public List<Map<String, String>> search(String term, String subcategory);
	
	/**
	 * Same thing, returns Items directly
	 * @param term
	 * @param category
	 */
	public List<Item> simpleSearch(String term, String category);
}
