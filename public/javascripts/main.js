function applyFormStyling(element) {
    if (element === undefined) {
        element = $('body');
    }

    // jQuery UI buttons
//    element.find('div.options a, div.search a, div.links a, td.actions a, input:submit, a.cancel').button();
//    element.find('div.links a.disabled').button('disable');
 
    element.find('a.button, input[type=submit], input[type=file], button').button();
    
    // Dialog links
//    element.find('a.dialog').click(function() {
//        var link = $(this);
//        showRemoteDialog(link.data('url'), link.data('title')); 
//    });
    
//    $( "input[type=submit], a, button" )
//    .button()
//    .click(function( event ) {
//      event.preventDefault();
//    });
    
}

function applyRating() {
	$('div.rateit').rateit();
}

function displayRecipe(id) {
	 myJsRoutes.controllers.Application.recipe(id).ajax({
		    success : function(data) { 
	    	console.log("success");
	    }
	 });
	
}

function applyTableEvents() {
	$(".jtable").delegate("tr", "mouseenter mouseleave", function(event) {
		if (event.type == 'mouseenter') {
			$(this).children("td").addClass("ui-state-hover");
	    } else {
	    	$(this).children("td").removeClass("ui-state-hover");
	    }
	});
//	$(".jtable").delegate("td", "click", function(event) {
//	    displayRecipe($(this).attr('id'));
//	});
	
}

function applyTableStyling() {
	
	$(".jtable th").each(function() {
		$(this).addClass("ui-state-default");
	});
	
	$(".jtable td").each(function() {
		$(this).addClass("ui-widget-content");
	});
	
		// $(".jtable tr").click(function(){
//		   
// //$(this).children("td").toggleClass("ui-state-highlight");
//		 // make line editable
//		 
//		 var idToUpdate = $(this).attr('id');
//		 
//		 myJsRoutes.controllers.Application.updateRecipeForm(idToUpdate).ajax({
//			    success : function(data) { 
//			    	//$(this).replaceWith(data);
//			    	$('#1').replaceWith(data);
//			    } 		
//			});
//		 
//		  });
		 
}

jQuery(function($){//on document ready
    //configureWidgets();
    applyFormStyling();
    //enableRowSelection();
    //enableColumnSorting();
    //enableShortcuts();
    //enableCheckboxSubmission();
	applyTableEvents();
	applyTableStyling();
});
