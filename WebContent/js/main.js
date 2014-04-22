$(document).ready(function(){
	
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
							alert("Password Update ! ");
							$('#close').trigger('click');
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
	
	$('li#overview').click(function(){
		$('div#main').empty();
		$('div#main').hide();
		$('div#loading').show();
		$('li#overview').addClass("active");
		$('li#studentlist').removeClass("active");
		$('li#grade').removeClass("active");
		$.get("ajax",{action:"overview"},function(data){
			$('div#main').html(data);
		});
		$('div#main').show();
		$('div#loading').hide();
	});
	
	$('li#studentlist').click(function(){
		$('div#main').empty();
		$('div#main').hide();
		$('div#loading').show();
		$('li#studentlist').addClass("active");
		$('li#overview').removeClass("active");
		$('li#grade').removeClass("active");
		$.get("ajax",{action:"studentlist"},function(data){
			$('div#main').html(data);
		});
		$('div#main').show();
		$('div#loading').hide();
	});
	
	$('li#overview').trigger('click');
});