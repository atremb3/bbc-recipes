function applyFormStyling(element) {
    if (element === undefined) {
        element = $('body');
    }

    // jQuery UI buttons
//    element.find('div.options a, div.search a, div.links a, td.actions a, input:submit, a.cancel').button();
//    element.find('div.links a.disabled').button('disable');
 
    // Dialog links
//    element.find('a.dialog').click(function() {
//        var link = $(this);
//        showRemoteDialog(link.data('url'), link.data('title')); 
//    });
    
    $( "input[type=submit], a, button" )
    .button()
    .click(function( event ) {
      event.preventDefault();
    });
    
}

jQuery(function($){//on document ready
    //configureWidgets();
    //applyFormStyling();
    //enableRowSelection();
    //enableColumnSorting();
    //enableShortcuts();
    //enableCheckboxSubmission();
});
