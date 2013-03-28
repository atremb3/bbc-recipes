package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import csv.loaders.RecipeData;

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
	
	public static void add(Recipe recipe) {
		recipe.save();
	}
	
	public static Recipe map(RecipeData recipeData) {
		Recipe recipe = new Recipe();
		recipe.category = recipeData.category;
		recipe.name = recipeData.name;
		recipe.issue = recipeData.issue;
		recipe.page = recipeData.page;
		recipe.comment = recipeData.comment;
		recipe.nbStars = recipeData.nbStars;
		return recipe;
	}
}
