$().ready(function(){ 
	
	var filterRecipe = function(form) {
		
		//serialize form into a json  object
		var formData = $(form).toObject({mode: 'all'});
		
		$.ajax( { 
			url : "/recipes/find", 
			//url: "@routes.Application.findRecipes()",
			data : JSON.stringify(formData, null, '\t'),
			type: "POST", 
			contentType: "application/json", 
			success: function(json){ 
				var jsonRecipes = JSON.stringify(json, null, '\t');
				
				// create html
				var recipes= jQuery.parseJSON(jsonRecipes);
				
				var recipesHtml = "";
				for (var i = 0; i < recipes.length; i++) {
					recipesHtml += "<tr id='" + recipes[i].id + "'><td>" 
						+ recipes[i].category + "</td><td>" 
	    				+ recipes[i].displayName + "</td><td>" 
	    				+ recipes[i].issue + "</td><td>" 
	    				+ recipes[i].page + "</td><td>"
	    				+ recipes[i].comment + "</td><td>"
	    				+ "<div class='rateit' data-rateit-value='" + recipes[i].nbStars + "' data-rateit-readonly='true'></div>";
				}
				// replace html on page
				$('table').find('tbody').html(recipesHtml);
				applyTableStyling();
				$('div.rateit').rateit();
			},
			error : function(jqXHR, textStatus, errorThrown ) { console.log(errorThrown); }
		} );
		
	};	

	
	$('#findrecipes-dialog-form').dialog({
	      autoOpen: false,
	      height: 600,
	      width: 500,
	      modal: true,
	      buttons: {
	        "Find Recipes": function() {
	        	
	        	var form = $(this).find('form');
	        	
	        	filterRecipe(form);
	        	
//	        	myJsRoutes.controllers.Application.findRecipes(JSON.stringify(formData, null, '\t')).ajax({
//	        		success : function(data) {
//	                	alert('response: ' + data);
//	                },
//	                error : function(jqXHR, textStatus, errorThrown ) {
//	                	alert(errorThrown);
//	                }
//	        	});
	        	
	            $( this ).dialog( "close" );
	        },
	        Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	      },
	      close: function() {
	      }
	});
	
	$('#find-recipes').click(		
		function() {
			$( "#findrecipes-dialog-form" ).dialog( "open" );
		}
	);
	
	$('#clear-filter').click(
			function() {
				
				var form = $(this).find('form');
				
				$(form).each (function(){
					  this.reset();
				});
	        	
	        	filterRecipe(form);
	});
	
});		
