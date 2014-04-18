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

	$('button#changepw').click(function(){
		$('div#changeform').hide();
		$('div#loading1').show();
		if($('input#newpw').val()!=$('input#confirmpw').val()){
			alert("Password is not match");
			$('div#changeform').show();
			$('div#loading1').hide();
		}else{
			$.post("changepw",{pw:$('input#oldpw').val()},function(data){
				if(data=="OK"){
					$('#close1').trigger('click');
					$('#error').hide();
					$('#mainsearch').show();
				}else{
					alert("Old Password is wrong");
					$('div#changeform').show();
					$('div#loading1').hide();
				}
			});
		}
	});
});
