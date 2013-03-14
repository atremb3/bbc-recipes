package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity 
public class Recipe extends Model {

	@Id
	public Long id;
	public String name;
	public String category;
	public int page;
	public String issue;
	
	public String nbTimeCooked;
	
	public String comment;
	public int nbStars;
	
	
	public static Finder find = new Finder(Long.class, Recipe.class);
	
	public static List<Recipe> all()  {
		return find.all();
	}
}
