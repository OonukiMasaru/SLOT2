var naviopen = false;
function zoomSet() {
	var wWidth = $(window).width();
	var scale = wWidth / 640 * 100 + '%';
	$('html').css({'zoom' : scale });
}
zoomSet();
$(window).resize(function() {
	zoomSet();
});
$(function() {	
	$('#wrap').delegate('#navi-ctrl', 'click', function() {
		if(naviopen) {
			naviopen = false;
			$('#gnavi').stop(true, false).animate({'top':'-270%'}, 300);
//			$('#gnavi').stop(true, false).animate({'top':'-370px'}, 300);
			$(this).find('img').attr({'src':'images/common/navi_open.png'});
		} else {
			naviopen = true;
			$('#gnavi').stop(true, false).animate({'top':'45%'}, 300); 
//			$('#gnavi').stop(true, false).animate({'top':'45px'}, 300); 
			$(this).find('img').attr({'src':'images/common/navi_close.png'});
		}
	});
});