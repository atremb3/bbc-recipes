$().ready(function() {
	$("#edit-form").submit(function(e) {
		var nbStars = $("#rateit").rateit('value');
		$("#edit-form-nbStars").val(nbStars);
		return true;
	});
})