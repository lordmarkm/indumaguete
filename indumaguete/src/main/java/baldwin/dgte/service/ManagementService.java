package baldwin.dgte.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import baldwin.dgte.model.Article;
import baldwin.dgte.model.Item;
import baldwin.dgte.persistence.ArticleDao;
import baldwin.dgte.persistence.DgteDao;
import baldwin.dgte.utils.DgteUtil;

@Service
public class ManagementService {
	public static final String VALIDATION_SUCCESS = "Success";

	static Logger log = Logger.getLogger(ManagementService.class);
	
	@Autowired ServletContext servletContext;
	@Autowired DgteDao db;
	@Autowired ArticleDao articleDb;
	@Autowired DgteUtil dgteUtil;
	
	/*
	 * Item services
	 */
	
	public Item get(String domain) {
		return db.getItem(domain);
	}
	
	/**
	 * Should validate if the dude owns or has edit permissions to the domain
	 * @param dude - the user
	 * @param domain
	 * @return
	 */
	public Item getIfEditable(String domain) {
		SecurityContextHolder.getContext().getAuthentication(); //TODO
		return db.getItem(domain);
	}
	
	public String validate(Item item) {
		return VALIDATION_SUCCESS;
	}
	
	public Item save(Item item) {
		String category = dgteUtil.determineCategory(item.getSubcategory());
		if(null != category) {
			log.info("Setting category: " + category + " for " + item + " and saving.");
			item.setCategory(category);
		} else {
			log.info("No category found for " + item + ". Saving anyway");
		}
		return db.newItem(item);
	}
	
	public void update(Item item, Item newProperties) throws Exception {
		db.updatePrimaryDetails(item, newProperties);
	}
	
	/*
	 * Article services
	 */

	public Article getArticle(String domain) {
		return articleDb.getArticle(domain); 
	}
	
	public String saveHomepage(String domain, String businessName, String textboxContents) {
		if(null == domain || !domain.matches("[A-Za-z_]*")) return "Invalid domain: " + domain;
		if(null == textboxContents || textboxContents.length() < 1) return "Invalid long description (null or 0 length).";
		try {
			Article article = articleDb.getArticle(domain, false);
			if(null == article) {
				articleDb.newArticle("", businessName, domain);
			} else {
				log.info("No need to create new article, " + domain + " already exists.");
			}
		} catch (Exception e) {
			return "Unable to write to database due to error " + e.getMessage();
		}
		return writeArticle(domain, textboxContents);
	}
	
	public Article newArticle(String ancestry, String title, String domain, String content) throws Exception {
		String writeStatus = writeArticle(domain, content);
		if(!"success".equals(writeStatus)) throw new Exception("Write failed: " + writeStatus);
		return articleDb.newArticle(ancestry, title, domain);
	}
	
	public String updateArticle(Article article, String domain, String newDomain, String newTitle, String content) {
		if(!domain.equals(newDomain)) {
			try {
				articleDb.update(article, newDomain, newTitle);
			} catch (Exception e) {
				return "Exception updating article: " + e.getMessage();
			}
		}
		String writeResult = writeArticle(domain, newDomain, content);
		return writeResult;
	}
	
	private String writeArticle(String domain, String content) {
		return writeArticle(null, domain, content);
	}
	
    private String writeArticle(String oldDomain, String domain, String content) {
    	if(null != oldDomain) {
    		String toDeletePath = servletContext.getRealPath("/resources/html/descriptions/" + oldDomain + ".html");
    		File toDelete = new File(toDeletePath);
    		if(toDelete.exists()) {
    			log.info("Deleting: " + toDelete.getAbsolutePath());
    			toDelete.delete();
    		} else {
    			log.warn(toDelete.getAbsolutePath() + " does not exist.");
    		}
    	}
    	
        FileWriter fw = null;
        BufferedWriter writer = null;
        String filepath = null;
        try {
        	filepath = servletContext.getRealPath("/resources/html/descriptions/" + domain + ".html");
            File f = new File(filepath);
            if(f.exists()) f.delete();
            f.createNewFile();

            fw = new FileWriter(f);
            writer = new BufferedWriter(fw);

            log.info("Writing " + content + " to " + f.getAbsolutePath());
            writer.write(content);
            writer.flush();
        } catch(Exception e) {
        	log.error("Exception writing long description. Attempted path: " + filepath, e);
            return "Exception writing file: " + e.getMessage();
        } finally {
            try {
                if(null != fw) fw.close();
                if(null != writer) writer.close();
            }catch(IOException e) {
                //eat e
            }
        }
        return "success";
    }
}
