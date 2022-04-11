//	Stores some data abouts Keys
//		Used to easily pass around information

public class Key {

	private String primary;
	private String[] synonyms;
	
	private String secondary;
	
	
	public Key(String primary, String secondary, String[] synonyms) {
		setPrimary(primary);
		setSecondary(secondary);
		setSynonyms(synonyms);
	}

	
	
	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}
	
	
	public String toString() {
		return primary + "-" + secondary;
	}

	
	public String[] getSynonyms() {
		return synonyms;
	}


	public void setSynonyms(String[] synonyms) {
		this.synonyms = synonyms;
	}
}
