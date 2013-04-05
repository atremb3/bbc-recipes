$().ready(function(){ 
	
	$('#addrecipe-dialog-form').dialog({
	      autoOpen: false,
	      height: 600,
	      width: 500,
	      modal: true,
	      buttons: {
	        "Create Recipe": function() {
	        	
	        	$(this).find('form').submit();
	        	
//	        	myJsRoutes.controllers.Application.addRecipe().ajax({
//	        		data: form.serialize();
//	        		success : function(data) {
//	                	alert('test success');
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
	
	$('#add-recipe').click(		
		function() {
			$( "#addrecipe-dialog-form" ).dialog( "open" );
		}
	);
});		
