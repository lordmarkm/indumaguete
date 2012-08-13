package baldwin.dgte.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baldwin.dgte.model.Item;
import baldwin.dgte.persistence.SearchDAO;

@Service
public class SearchServiceImpl implements SearchService {
	static Logger log = Logger.getLogger(SearchServiceImpl.class);
	
	@Autowired private SearchDAO db;
	
	public List<Item> simpleSearch(String term, String category) {
		return db.search(term,  category);
	}
	
	public List<Map<String, String>> search(String term, String subcategory) {
		log.info("Searching " + term + " of " + subcategory);
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();

		List<Item> dbResults = db.search(term, subcategory);
		for(Item _item : dbResults) {
			Item item = _item.htmlFriendly();
			Map<String, String> result = new HashMap<String, String>();
			result.put("type", "item");
			result.put("title", item.getName());
			result.put("text", item.getShortDescription());
			result.put("domain", item.getDomain());
			result.put("sourceName", item.getName());
			if(null != item.getSubcategory() && !item.getSubcategory().equals("All")) {
				result.put("subcategory", item.getSubcategory());
			}
			if(null != item.getModified()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				result.put("modified", String.valueOf(sdf.format(item.getModified())));
			}
			if(null != item.getImageUrl() && item.getImageUrl().length() > 0) {
				result.put("imageUrl", item.getImageUrl());
			}
			results.add(result);
		}

		return results;
	}
}
