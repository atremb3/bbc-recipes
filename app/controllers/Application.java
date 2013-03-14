package controllers;

import java.io.File;
import java.util.List;

import models.Recipe;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.recipes.recipe;
import csv.loaders.RecipeData;
import csv.loaders.RecipeFileReader;


public class Application extends Controller {
	
    public static Result index() {
        return redirect(routes.Application.recipes());
    }
    
    public static Result recipes() {
    	List<Recipe> recipes = Recipe.all();
    	return ok(recipe.render(recipes));
    }
    
    public static Result upload() {
    	  MultipartFormData body = request().body().asMultipartFormData();
    	  FilePart recipeFile = body.getFile("recipes");
    	  if (recipeFile != null) {
    	    File file = recipeFile.getFile();
    	    
    	    //do my stuff   
    	    try {
    	    	processRecipeFile(file);
    	    	flash("success", "Recipe file uploaded successfully");
    	    } catch (Exception e) {
    	    	flash("error", "Error encountered while processing file");
    	    }
    	    
    	  } else {
    	    flash("error", "Missing file");   
    	  }
    	  
    	  return redirect(routes.Application.recipes());
    	}

	private static void processRecipeFile(File recipeFile) throws Exception {
				
   	    RecipeFileReader recipeReader = new RecipeFileReader(recipeFile);
	    recipeReader.open();
	    
	    RecipeData recipeData = recipeReader.read();
	    
	    Recipe recipe = null;
	    
	    try {
		    while (recipeData != null) {
		    
		    	recipe = mapToRecipe(recipeData);					
				recipe.save();					
				recipeData = recipeReader.read();
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	throw e;
	    } finally {
	    	recipeReader.close();
	    }
		
	}

	private static Recipe mapToRecipe(RecipeData recipeData) {
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
