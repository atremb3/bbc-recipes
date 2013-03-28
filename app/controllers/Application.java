package controllers;

import static play.data.Form.form;

import java.io.File;
import java.util.List;

import models.Recipe;
import play.Routes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import csv.loaders.RecipeData;
import csv.loaders.RecipeFileReader;
public class Application extends Controller {
	
    public static Result index() {
        return redirect(routes.Application.recipes());
    }
    
    public static Result recipes() {
    	List<Recipe> recipes = Recipe.all();
    	Form<Recipe> addRecipeForm = form(Recipe.class).bindFromRequest();
    	return ok(views.html.recipes.recipe.render(recipes, addRecipeForm));
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
		    	recipe = Recipe.map(recipeData);					
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

	public static Result updateRecipe(Long id) {
		
		return ok();
	}
	
	public static Result addRecipe() {
		
		Form<Recipe> addRecipeForm = form(Recipe.class).bindFromRequest();
		Recipe newRecipe = addRecipeForm.get();
		Recipe.add(newRecipe);
		return redirect(routes.Application.recipes());
	}

	public static Result updateRecipeCategory(Long id) {
		
		return ok();
	}


	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	        Routes.javascriptRouter("myJsRoutes",
	        	routes.javascript.Application.addRecipe()
	        )
	    );
	}

}

