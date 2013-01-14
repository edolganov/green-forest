

EffectsController = function(){
	
	this.init = function(){
		
		showTargetAnchor();
		initBackToTop();
		
	};
	
	function showTargetAnchor(){
		
		$("a").click(function(){
			
			var fromElem = $(this);
			var toStr = fromElem.attr("href");
			if( ! toStr || ! toStr.startsWith("#")){
				return;
			}
			
			var targetId = toStr.substring(1);
			highlightElemById(targetId);
			
			
		});
		
	}
	
	function highlightElemById(id){
		
		var elem = $("#"+id);
		elem.effect("highlight", {}, 2000);
		
	}
	
	function initBackToTop(){
		
		$(window).scroll(function() {
			if($(this).scrollTop() != 0) {
				$('#toTop').fadeIn();	
			} else {
				$('#toTop').fadeOut();
			}
		});
	 
		$('#toTop').click(function() {
			$('body,html').animate({scrollTop:0},0);
		});
		
	}
	
};