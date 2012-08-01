package baldwin.dgte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baldwin.dgte.model.Item;
import baldwin.dgte.persistence.DgteDao;
import baldwin.dgte.utils.DgteUtil.Filter;

@Service
public class ViewItemServiceImpl implements ViewItemService {
	@Autowired private DgteDao db;
	
	@Override
	public Item getItem(String domain) {
		return db.getItem(domain);
	}
	
	@Override
	public List<Item> getAllItems(Filter filter, int firstResult, int maxResult) {
		return db.getAllItems(filter, firstResult, maxResult);
	}
}
