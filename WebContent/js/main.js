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
			$.getJSON('timetable',{teacher:$('input#user').val()}, function(json) {
				if(json!=null){
					var i;
					var j;
					var k;
					var week=new Array("Mon","Tue","Wed","Thu","Fri");
					var time=new Array("09:00-09:30","09:30-10:00","10:00-10:30","10:30-11:00","11:00-11:30","11:30-12:00","14:00-14:30","14:30-15:00","15:00-15:30","15:30-16:00","16:00-16:30","16:30-17:00","17:00-17:30","17:30-18:00");
					var html="";
					var d=new Date();
					for(i=0;i<14;i++){
						for(j=0;j<5;j++){
							if(inner(time[i])&&(d.getDay()-1)==j){
								html="<td class=\"danger currenttime\">";
							}else{
								html="<td>";
							}
							$.each(json,function(index,array){ 
								if(array['date']==week[j]&&array['time']==time[i]){
									html+="Student: "+array['name']+"<br />Supervisor:"+array['supervisor']+"<br />Observer:"+array['observer']+"<br />Examiner:"+array['examiner']+"<br />Room:"+array['room'];
								}
				            });
							html+="</td>";
							$('tr#t'+(i+1)).append(html);
							html="";
						}
					}
				}
			});
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
			$.getJSON('studentlist',{type:"supervisor",teacher:$('input#user').val()}, function(json) {
				if(json!=null){
					var html="";
					$.each(json,function(index,array){ 
						html="<tr class=\"success\"><td>"+array['name']+"</td><td><button type=\"button\" class=\"btn btn-primary btn-sm pull-right\" onclick=\"showdetail("+array['num']+")\">Detail</button></td></tr>";
						$('tbody#Supervision').append(html);
					});
				}
			});
			$.getJSON('studentlist',{type:"observer",teacher:$('input#user').val()}, function(json) {
				if(json!=null){
					var html="";
					$.each(json,function(index,array){ 
						html="<tr class=\"success\"><td>"+array['name']+"</td><td><button type=\"button\" class=\"btn btn-primary btn-sm pull-right\" onclick=\"showdetail("+array['num']+")\">Detail</button></td></tr>";
						$('tbody#Observation').append(html);
					});
				}
			});
			$.getJSON('studentlist',{type:"examiner",teacher:$('input#user').val()}, function(json) {
				if(json!=null){
					var html="";
					$.each(json,function(index,array){ 
						html="<tr class=\"success\"><td>"+array['name']+"</td><td><button type=\"button\" class=\"btn btn-primary btn-sm pull-right\" onclick=\"showdetail("+array['num']+")\">Detail</button></td></tr>";
						$('tbody#Examination').append(html);
					});
				}
			});
			$('div#main').append("<a data-toggle=\"modal\" href=\"#showdetail\" id=\"showstudent\" style=\"display:none\"></a>");
			
		});
		$('div#main').show();
		$('div#loading').hide();
	});
	
	$('li#grade').click(function(){
		$('div#main').empty();
		$('div#main').hide();
		$('div#loading').show();
		$('li#grade').addClass("active");
		$('li#overview').removeClass("active");
		$('li#studentlist').removeClass("active");
		$.get("ajax",{action:"gradechoose"},function(data){
			$('div#main').html(data);			
		});
		$('div#main').show();
		$('div#loading').hide();
	});
	
	$('li#overview').trigger('click');
});

function inner(time){
	var stra=time.split("-");
    if(stra.length!=2){
    	return false;
    }
    var strb=stra[0].split(":");
    if(strb.length!=2){
    	return false;
    }
    var stre=stra[1].split(":");
    if(stre.length!=2){
    	return false;
    }

    var b = new Date();
    var e = new Date();
    var n = new Date();
    b.setHours(strb[0]);
    b.setMinutes(strb[1]);
    e.setHours(stre[0]);
    e.setMinutes(stre[1]);

    if(n.getTime()-b.getTime()>0 && n.getTime()-e.getTime()<0){
    	return true;
    }else{
    	return false;
    }
}

function showdetail(id){
	$.getJSON('studentlist',{type:"student",num:id}, function(json) {
		if(json!=null){
			var html="";
			$.each(json,function(index,array){ 
				html="<p class=\"text-primary lead\">Student ID: "+array['id']+"<br />Name: "+array['name']+"<br />Phone: "+array['tel']+"<br />Project: "+array['project']+"<br />Presentation Time: "+array['date']+" "+array['time']+"<br />Supervisor: "+array['supervisor']+"<br />Observer: "+array['observer']+"<br />Examiner: "+array['examiner']+"<br />Room: "+array['room']+"<br /></p>";
			});
			$('div#detailmodal').html(html);
		}
	});
	$('a#showstudent').trigger('click');
}

function grade(id){
	$('div#editmodal').empty();
	$.getJSON("gradelist",{type:"grade",i:id,type1:$('input#type').val(),teacher:$('input#user').val()}, function(json) {
		if(json!=null){
			$.getJSON("gradelist",{type:"authority",teacher:$('input#type').val()}, function(json1) {
				var html="";
				if(json1!=null){
					$.each(json,function(index,array){
						$.each(json1,function(index,array1){
							html="<h5 class='text-danger'>"+array1['title']+"</h5><input type=\"text\" id="+array1['title']+" class=\"form-control\" >";
							$('div#editmodal').append(html);
						});
					});
				}
			});
		}
	});
	$('a#showedit').trigger('click');
}