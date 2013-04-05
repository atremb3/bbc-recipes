$().ready(function(){ 
	
//	$('#find-recipes').click(function() {
//		
//		var form = $('#findrecipes-dialog-form').find('form');
//		
//		
//		form.submit();
//		
////		myJsRoutes.controllers.Application.findRecipes().ajax({
////    		//data: form.serialize();
////			
////    		success : function(data) {
////            	alert(data);
////            	var recipes = $.parseJSON(data);
////            },
////            error : function(jqXHR, textStatus, errorThrown ) {
////            	alert(errorThrown);
////            }
////    	});
//	});
	
	$('#findrecipes-dialog-form').dialog({
	      autoOpen: false,
	      height: 600,
	      width: 500,
	      modal: true,
	      buttons: {
	        "Find Recipes": function() {
	        	
	        	//serialize form into a json  object
	        	var formData = $(this).find('form').toObject({mode: 'all'});

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
	        			var recipesHtml = "<ul>";
	        			for (var i = 0; i < recipes.length; i++) {
	        				recipesHtml += "<li>"+recipes[i].displayName+"</li>"; 
	        			}
	        			recipesHtml += "</ul>";
	        			
	        			// append html to page
	        			$('#search-result-dialog').html(recipesHtml);
	        		},
	        		error : function(jqXHR, textStatus, errorThrown ) { console.log(errorThrown); }
	        	} );
	        	
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
	
});		
