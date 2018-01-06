$(document).mouseup(function (e)
		{
	var container = new Array();
	container.push($('#note-title'));
	container.push($('#note_icons'));

	if(!$('#note-body').is(e.target)){
		$.each(container, function(key, value) {
			if (!$(value).is(e.target) && !$(e.target).parents('#note').length) // if the target of the click isn't the container...
				$(value).hide();
			else
				$(value).show();
		});
	}
	else {
		$.each(container, function(key, value) {
			$(value).show();
		});
	}	
		});

$(document).ready(function(){

	$("button").click(function(){
		$("#toggleNote").toggle();
	});
});
