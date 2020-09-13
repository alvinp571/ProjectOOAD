package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mySQLConnector.Connect;

public class Genre {
	
	private String id,type;
	private Connect connect = Connect.getInstance();
	
	public List<Genre> all(){
		String query = String.format("SELECT * FROM genres");
		ResultSet rs = connect.executeQuery(query);
		List<Genre> theGenres = new ArrayList<Genre>();
		try {
			while(rs.next()) {
				Genre genre = new Genre();
				genre.id = rs.getString(1);
				genre.type = rs.getString(2);
				theGenres.add(genre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theGenres;
	}
	
	public Genre() {}

	public Genre insert() {
		String query = String.format("INSERT INTO genres VALUES('%s','%s')",id,type);
		connect.executeUpdate(query);
		return this;
	}
	
	public Genre(String type) {
		super();
		this.id = UUID.randomUUID().toString();
		this.type = type;
	}

	public Genre getByType(String type) {
		String query = String.format("SELECT * FROM genres WHERE type = '%s'", type);
		ResultSet rs =  connect.executeQuery(query);
		try {
			if(rs.next()) {
				Genre genre = new Genre();
				genre.id = rs.getString(1);
				return genre;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
