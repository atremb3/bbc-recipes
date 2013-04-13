package controllers;

import static play.data.Form.form;

import java.io.File;
import java.util.List;

import models.Recipe;

import org.codehaus.jackson.JsonNode;

import play.Routes;
import play.data.Form;
import play.libs.Json;
import play.mvc.BodyParser;
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
	
//	public static Result rate(Long id) {
//		Recipe recipe = Recipe.findById(id);
//		return ok(String.valueOf(recipe.nbStars));
//	}
	
	public static Result addRecipe() {
		
		Form<Recipe> addRecipeForm = form(Recipe.class).bindFromRequest();
		Recipe newRecipe = addRecipeForm.get();
		Recipe.add(newRecipe);
		return redirect(routes.Application.recipes());
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result findRecipes() {
		
		JsonNode json = request().body().asJson();
		  
		Recipe templateRecipe = null;
		if (json == null) {
		    return badRequest("Expecting Json data");
		  } else {
			  templateRecipe = new Recipe();
			  String recipeName = json.findPath("name").getTextValue();
			  if (recipeName != null) {
				  templateRecipe.name = '%' + recipeName.toLowerCase() + '%';  
			  }
			  templateRecipe.category = json.findPath("category").getTextValue();			  
		  }
		
		List<Recipe> recipes = Recipe.find(templateRecipe);
			    
		return ok(Json.toJson(recipes));
	}

	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	        Routes.javascriptRouter("myJsRoutes",
	        	routes.javascript.Application.addRecipe(),
	        	routes.javascript.Application.findRecipes()
	        	//routes.javascript.Application.rate()
	        )
	    );
	}

}