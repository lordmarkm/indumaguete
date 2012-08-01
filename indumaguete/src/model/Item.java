package baldwin.dgte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;

@Entity  @Table(name="indgte_items", schema="njfp") @JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Cloneable {
	private long itemIndex;
	private int popularity;
	private Date modified;
	private String domain;
	private String name;
	private String password;
	private String passwordHash;
	private String address;
	private String shortDescription;
	private String email;
	private String cellphone;
	private String landline;
	private String imageUrl;
	private boolean cellphoneVisible;
	private boolean landlineVisible;
	private boolean emailVisible;
	
	private String category;
	private String subcategory;
	
	@Override
	public String toString() {
		return domain + " | " + name;
	}
	
	public Item htmlFriendly() {
		Item p = (Item) this.clone();
		p.setAddress(p.getAddress().replaceAll("\r\n", "<br>").replaceAll("\n", "<br>"));
		return p;
	}
	
	/**
	 * Automapped by Jackson. Encode the password here. salt=asin
	 */
	public void setPassword(String password) {
		this.password = password;
		this.passwordHash = DigestUtils.shaHex(password + "asin");
	}
	
	@Transient
	public String getPassword() {
		return password;
	}
	
	@Id @GeneratedValue 
	public long getItemIndex() {
		return itemIndex;
	}
	public void setItemIndex(long index) {
		this.itemIndex = index;
	}
	
	@Column(unique = true, nullable = false) 
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column 
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	@Column 
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column 
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	@Column 
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column 
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	@Column 
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	
	@Column 
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Column
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	@Column
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Column
	public boolean isCellphoneVisible() {
		return cellphoneVisible;
	}

	public void setCellphoneVisible(boolean cellphoneVisible) {
		this.cellphoneVisible = cellphoneVisible;
	}

	@Column
	public boolean isLandlineVisible() {
		return landlineVisible;
	}

	public void setLandlineVisible(boolean landlineVisible) {
		this.landlineVisible = landlineVisible;
	}

	@Column
	public boolean isEmailVisible() {
		return emailVisible;
	}

	public void setEmailVisible(boolean emailVisible) {
		this.emailVisible = emailVisible;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@Version
	public Date getModified() {
		return modified;
	}
	
	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	@Column
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
}
