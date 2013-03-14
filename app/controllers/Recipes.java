package controllers;

import java.util.List;

import models.RecipeHeader;
import play.mvc.Controller;

public class Recipes extends Controller {

	
	public static List<RecipeHeader> listByCategory(String category) {
		
		List<RecipeHeader> result = RecipeHeader.listByCategory(category);
		
		return result;
	
	}
}
