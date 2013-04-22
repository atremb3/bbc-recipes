$().ready(function(){ 
		
	$('table tr').each( function() {
		
		var recipeId = $(this).attr('id');
		var rateElem = $(this).find('td div.rateit');
		var rateValue = $(rateElem).val();
		$.ajax( { 
			url : "/recipes/recipe/" + recipeId + "/rate", 
			data : {
	            id: recipeId
	        },
			type: "GET", 
			success: function(rate){ 				
				rateElem.rateit('value', rate);			
			},
			error : function(jqXHR, textStatus, errorThrown ) { console.log(errorThrown); }
		} );
		
	});
	
});