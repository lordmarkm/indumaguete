package baldwin.dgte.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.inject.Named;

import baldwin.dgte.model.Item;

@Named
public class DgteUtil {
	public static final String CATEGORIES_NO_GROUPS = "CATEGORIES_NO_GROUPS";
	private static final String CATEGORIES_WITH_GROUPS = "CATEGORIES_WITH_GROUPS";
	
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

	public List<String> getSimpleCategories(String includeGroups) {
		List<String> simpleCategories = new ArrayList<String>();
		if(includeGroups.equals(CATEGORIES_WITH_GROUPS)) {
			simpleCategories.add("All");
		}
		for(Entry<String, List<String>> categoryGroup : categories.entrySet()) {
			if(includeGroups.equals(CATEGORIES_WITH_GROUPS)) {
				simpleCategories.add("Any " + categoryGroup.getKey());
			}
			simpleCategories.addAll(categoryGroup.getValue());
		}
		if(includeGroups.equals(CATEGORIES_NO_GROUPS)) {
			Collections.sort(simpleCategories);
		}
		return simpleCategories;
	}
	
	public String determineCategory(String subcategory) {
		for(Entry<String, List<String>> entry : categories.entrySet()) {
			if(entry.getValue().contains(subcategory)) return entry.getKey();
		}
		return null;
	}
}
