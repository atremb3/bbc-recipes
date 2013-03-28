$().ready(function(){ 
 $(".jtable th").each(function(){
 
  $(this).addClass("ui-state-default");
 
  });
 $(".jtable td").each(function(){
 
  $(this).addClass("ui-widget-content");
 
  });
 $(".jtable tr").hover(
     function()
     {
      $(this).children("td").addClass("ui-state-hover");
     },
     function()
     {
      $(this).children("td").removeClass("ui-state-hover");
     }
    );

// $(".jtable tr").click(function(){
//	   
//	   //$(this).children("td").toggleClass("ui-state-highlight");
//	 // make line editable
//	 
//	 var idToUpdate = $(this).attr('id');
//	 
//	 myJsRoutes.controllers.Application.updateRecipeForm(idToUpdate).ajax({
//		    success : function(data) { 
//		    	//$(this).replaceWith(data);
//		    	$('#1').replaceWith(data);
//		    } 		
//		});
//	 
//	  });
 
}); 