package controllers;

import java.util.HashMap;

import java.util.List;


import components.Message;
import models.Genre;
import views.CreateGenre;
import views.ViewGenre;
import views.base.IView;

public class GenreHandler {
	public List<Genre> getAll(){
		Genre genre = new Genre();
		List<Genre> theGenres = genre.all();
		if(theGenres.isEmpty()) {
			Message.error("Genre is empty !");
		}
		return theGenres;
	}
	
	public IView showGenre() {
		return new ViewGenre();
	}
	
	public IView showCreateGenre() {
		return new CreateGenre();
	}
	
	public Genre insert(HashMap<String, String> inputs) {
		Genre genre = new Genre(inputs.get("name"));
		genre.insert();
		Message.success("Successfully create a new genre");
		return genre;
	}
	
	public Boolean register(HashMap<String, String>inputs) {
		if(inputs.get("name").isEmpty()) {
			Message.error("Name cannot be empty !");
			return false;
		}
		this.insert(inputs);
		return true;
	}
}
