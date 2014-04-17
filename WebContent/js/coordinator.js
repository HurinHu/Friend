$(document).ready(function(){
	$.getJSON('assessment', function(json) {
		if(json!=null){
			var i=0;
			$.each(json,function(index,array){ 
				$('#name'+i).val(array['name']);
				$('#percentage'+i).val(array['percentage']);
				if(array['supervisor']=="yes"){
					$('#supervisor'+i).prop('checked', true);
				}
				if(array['observer']=="yes"){
					$('#observer'+i).prop('checked', true);
				}
				if(array['examiner']=="yes"){
					$('#examiner'+i).prop('checked', true);
				}
				i++;
            }); 
		}
    	
	});

});
