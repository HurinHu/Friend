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
				if(data.indexOf("OK")!=-1){
					$.post("change",{status:"change",pw:$('input#newpw').val()},function(data){
						if(data==="OK"){
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
	
	$('a#rulesetting').click(function(){
		$('div#main').empty();
		$('div#loading').show();
		$.get("ajax",{action:"rulesetting"},function(data){
			$('div#main').html(data);
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
			$('div#loading').hide();
		});
	});
	$('button#submit').click(function(){
		if((parseInt($('input#percentage0').val())+parseInt($('input#percentage1').val())+parseInt($('input#percentage2').val())+parseInt($('input#percentage3').val())+parseInt($('input#percentage4').val())+parseInt($('input#percentage5').val())+parseInt($('input#percentage6').val())+parseInt($('input#percentage7').val())+parseInt($('input#percentage8').val()))!=100){
			alert("Please check the percentage first !!!");
		}else if(!(($('input#supervisor0').is(':checked')||$('input#observer0').is(':checked')||$('input#examiner0').is(':checked'))&&($('input#supervisor1').is(':checked')||$('input#observer1').is(':checked')||$('input#examiner1').is(':checked'))&&($('input#supervisor2').is(':checked')||$('input#observer2').is(':checked')||$('input#examiner2').is(':checked'))&&($('input#supervisor3').is(':checked')||$('input#observer3').is(':checked')||$('input#examiner3').is(':checked'))&&($('input#supervisor4').is(':checked')||$('input#observer4').is(':checked')||$('input#examiner4').is(':checked'))&&($('input#supervisor5').is(':checked')||$('input#observer5').is(':checked')||$('input#examiner5').is(':checked'))&&($('input#supervisor6').is(':checked')||$('input#observer6').is(':checked')||$('input#examiner6').is(':checked'))&&($('input#supervisor7').is(':checked')||$('input#observer7').is(':checked')||$('input#examiner7').is(':checked'))&&($('input#supervisor8').is(':checked')||$('input#observer8').is(':checked')||$('input#examiner8').is(':checked')))){
			alert("Please check for each item !!!");
		}else{
			$('div#rule').hide();
			$('div#loading').show();
			$.post("assessment",{name0:$('input#name0').val(),name1:$('input#name1').val(),name2:$('input#name2').val(),name3:$('input#name3').val(),name4:$('input#name4').val(),name5:$('input#name5').val(),name6:$('input#name6').val(),name7:$('input#name7').val(),name8:$('input#name8').val(),percentage0:$('input#percentage0').val(),percentage1:$('input#percentage1').val(),percentage2:$('input#percentage2').val(),percentage3:$('input#percentage3').val(),percentage4:$('input#percentage4').val(),percentage5:$('input#percentage5').val(),percentage6:$('input#percentage6').val(),percentage7:$('input#percentage7').val(),percentage8:$('input#percentage8').val(),supervisor0:$('input#supervisor0').is(':checked'),supervisor1:$('input#supervisor1').is(':checked'),supervisor2:$('input#supervisor2').is(':checked'),supervisor3:$('input#supervisor3').is(':checked'),supervisor4:$('input#supervisor4').is(':checked'),supervisor5:$('input#supervisor5').is(':checked'),supervisor6:$('input#supervisor6').is(':checked'),supervisor7:$('input#supervisor7').is(':checked'),supervisor8:$('input#supervisor8').is(':checked'),observer0:$('input#observer0').is(':checked'),observer1:$('input#observer1').is(':checked'),observer2:$('input#observer2').is(':checked'),observer3:$('input#observer3').is(':checked'),observer4:$('input#observer4').is(':checked'),observer5:$('input#observer5').is(':checked'),observer6:$('input#observer6').is(':checked'),observer7:$('input#observer7').is(':checked'),observer8:$('input#observer8').is(':checked'),examiner0:$('input#examiner0').is(':checked'),examiner1:$('input#examiner1').is(':checked'),examiner2:$('input#examiner2').is(':checked'),examiner3:$('input#examiner3').is(':checked'),examiner4:$('input#examiner4').is(':checked'),examiner5:$('input#examiner5').is(':checked'),examiner6:$('input#examiner6').is(':checked'),examiner7:$('input#examiner7').is(':checked'),examiner8:$('input#examiner8').is(':checked')},function(data){
				if(data.indexOf("OK")!=-1){
					alert("Update successfully !");
					$('div#rule').show();
					$('div#loading').hide();
				}else{
					alert("Internal Error !");
					$('div#rule').show();
					$('div#loading').hide();
				}
			});
		}
	});
});
