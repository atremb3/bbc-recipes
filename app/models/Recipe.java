package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import csv.loaders.RecipeData;

@Entity 
public class Recipe extends Model {

	@Id
	public Long id;
	public String name;
	public String displayName;
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
	
	public static void add(Recipe recipe) {
		recipe.save();
	}
	
	public static Recipe map(RecipeData recipeData) {
		Recipe recipe = new Recipe();
		recipe.category = recipeData.category;
		recipe.name = recipeData.name.toLowerCase();
		recipe.displayName = recipeData.name;
		recipe.issue = recipeData.issue;
		recipe.page = recipeData.page;
		recipe.comment = recipeData.comment;
		recipe.nbStars = recipeData.nbStars;
		return recipe;
	}

	public static List<Recipe> find(Recipe templateRecipe) {
		ExpressionList<Recipe> findRecipeExp = Ebean.find(Recipe.class).where().exampleLike(templateRecipe);  	    	    
		List<Recipe> result = findRecipeExp.findList();
		return result;
	}
	
	public static Recipe findById(Long id) {
		Recipe recipe = (Recipe) find.byId(id);
		return recipe;
	}
}
