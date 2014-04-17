$(document).ready(function(){
	$('button#menu').click(function(){
		if($('div.sidebar').is(':visible')){
			$('div.sidebar').hide();
		}else{
			$('div.sidebar').show();
		}
	});
	$('img.setting').click(function(){
		if($('ul.dropdownmenu').is(':visible')){
			$('ul.dropdownmenu').hide();
		}else{
			$('ul.dropdownmenu').show();
		}
	});
	$('ul.dropdownmenu').mouseleave(function(){
  				setTimeout("$('ul.dropdownmenu').hide()",5000);
	});
});
function init() {
	var winW, winH;
	if (document.body && document.body.offsetWidth) {
		winW = document.body.offsetWidth;
		winH = document.body.offsetHeight;
	}
	if (document.compatMode=='CSS1Compat' && document.documentElement && document.documentElement.offsetWidth ) {
		winW = document.documentElement.offsetWidth;
		winH = document.documentElement.offsetHeight;
	}
	if (window.innerWidth && window.innerHeight) {
		winW = window.innerWidth;
		winH = window.innerHeight;
	}
	if(winW>768){
		$('div.sidebar').show();
	}else{
		$('div.sidebar').hide();
	}
	var data = [
	{
		value: 15,
		color:"#F38630"
	},
	{
		value : 30,
		color : "#E0E4CC"
	},
	{
		value : 35,
		color : "#69D2E7"
	},	
	{
		value : 15,
		color : "#7D4F6D"
	},
	{
		value : 5,
		color : "#F7464A"
	}			
]
var ctx = document.getElementById("myChart").getContext("2d");
var myNewChart = new Chart(ctx).Pie(data);
var data1 = {
	labels : ["Attitude","Report","Literature","Depth","Writing","Experiment","Presentation","Q&A","Demo"],
	datasets : [
		{
			fillColor : "rgba(224,228,204,0.6)",
			strokeColor : "rgba(220,220,220,1)",
			data : [65,59,90,81,56,55,50,60,80]
		}
	]
}
var ctx1 = document.getElementById("myChart1").getContext("2d");
new Chart(ctx1).Bar(data1);
}
function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}
window.onload=init;
window.onresize = init;