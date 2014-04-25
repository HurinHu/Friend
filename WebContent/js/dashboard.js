$(document).ready(function(){
	
	/*
	  Set button action
	 */
	
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

/*
  Set print action
 */

function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     document.body.innerHTML = printContents;
     window.print();
     window.location.reload();
}