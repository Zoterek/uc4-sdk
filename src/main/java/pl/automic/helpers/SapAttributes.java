package pl.automic.helpers;

public class SapAttributes {
	public boolean delete = true;
	public int index;
	
	public void setDelete(String value) {
		this.delete = stringToBoolean(value, this.delete);
	}
	
	private boolean stringToBoolean(String str, boolean def) {
		if(str == null) {
			return def;
		}
		
		str = str.toUpperCase();
		switch (str) {
		case "Y":
		case "YES":
		case "TRUE":
			return true;
		case "N":
		case "NO":
		case "FALSE":
			return false;
		default:
			return def;
		}
	}
}
