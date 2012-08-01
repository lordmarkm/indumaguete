package baldwin.dgte.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import baldwin.dgte.model.Item;

@Named
public class DgteUtil {
	List<String> reserved;
	@Resource(name="categories") Map<String, List<String>> categories;
	
	public DgteUtil() {
		reserved = new ArrayList<String>();
	}
	
	public String checkAgainstReservedKeywords(String domain) {
		if(reserved.contains(domain)) return domain;
		return null;
	}
	
	public enum Filter {
		recent,
		popular
	}

	public List<Item> htmlFriendly(List<Item> items) {
		List<Item> htmlFriendlyItems = new ArrayList<Item>();
		for(Item item : items) {
			htmlFriendlyItems.add(item.htmlFriendly());
		}
		return htmlFriendlyItems;
	}
	
	public Map<String, List<String>> getCategories() {
		return categories;
	}

	public String determineCategory(String subcategory) {
		for(Entry<String, List<String>> entry : categories.entrySet()) {
			if(entry.getValue().contains(subcategory)) return entry.getKey();
		}
		return null;
	}
}
