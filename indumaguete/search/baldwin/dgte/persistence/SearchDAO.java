package baldwin.dgte.persistence;

import java.util.List;

import baldwin.dgte.model.Item;

public interface SearchDAO {
	/**
	 * Search businessName, category (support synonyms in the future), and short description 
	 * @return List[] {businessName matches, cat matches, desc matches}
	 */
	public List<Item>[] search(String term);
	public List<Item> search(String term, String subcategory);
}
