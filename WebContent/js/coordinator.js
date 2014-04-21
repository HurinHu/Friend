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
		}else if($('input#newpw').val()==""||$('input#confirmpw').val()==""){
			alert("Please check your new password");
			$('div#changeform').show();
			$('div#loading1').hide();
		}else{
			$.post("change",{status:"check",pw:$('input#oldpw').val()},function(data){
				if(data.localeCompare("OK")){
					$.post("change",{status:"change",pw:$('input#newpw').val()},function(data){
						if(data.localeCompare("OK")){
							$('#close1').trigger('click');
							$('#error').hide();
							$('#mainsearch').show();
						}else{
							alert("Internal Error ! ");
							$('div#changeform').show();
							$('div#loading1').hide();
						}
					});
				}else{
					alert("Old Password is wrong");
					$('div#changeform').show();
					$('div#loading1').hide();
				}
			});
		}
	});
	
	$('button#submit').click(function(){
		if((parseInt($('input#percentage0').val())+parseInt($('input#percentage1').val())+parseInt($('input#percentage2').val())+parseInt($('input#percentage3').val())+parseInt($('input#percentage4').val())+parseInt($('input#percentage5').val())+parseInt($('input#percentage6').val())+parseInt($('input#percentage7').val())+parseInt($('input#percentage8').val()))!=100){
			alert("Please check the percentage first !!!");
		}else if(!(($('input#supervisor0').is(':checked')||$('input#observer0').is(':checked')||$('input#examiner0').is(':checked'))&&($('input#supervisor1').is(':checked')||$('input#observer1').is(':checked')||$('input#examiner1').is(':checked'))&&($('input#supervisor2').is(':checked')||$('input#observer2').is(':checked')||$('input#examiner2').is(':checked'))&&($('input#supervisor3').is(':checked')||$('input#observer3').is(':checked')||$('input#examiner3').is(':checked'))&&($('input#supervisor4').is(':checked')||$('input#observer4').is(':checked')||$('input#examiner4').is(':checked'))&&($('input#supervisor5').is(':checked')||$('input#observer5').is(':checked')||$('input#examiner5').is(':checked'))&&($('input#supervisor6').is(':checked')||$('input#observer6').is(':checked')||$('input#examiner6').is(':checked'))&&($('input#supervisor7').is(':checked')||$('input#observer7').is(':checked')||$('input#examiner7').is(':checked'))&&($('input#supervisor8').is(':checked')||$('input#observer8').is(':checked')||$('input#examiner8').is(':checked')))){
			alert("Please check for each item !!!");
		}
	});
});
