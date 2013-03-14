package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.ebean.Model;

@Entity
public class RecipeHeader extends Model {
	
	public String name;
	public String category;
	public int page;
	public String issue;

	// -- Queries
	
	public static Model.Finder<String, RecipeHeader> find = new Model.Finder(String.class, RecipeHeader.class);

	public static List<RecipeHeader> listByCategory(String category) {

		List<RecipeHeader> results = null;
		find.where().eq("category", category).findList();

		return results;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecipeHeader []");
		return builder.toString();
	}
	
	
	
}
