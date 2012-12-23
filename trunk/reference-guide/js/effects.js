$(document).ready(function(){
	
	new EffectsController().init();
	
});

EffectsController = function(){
	
	this.init = function(){
		
		showTargetAnchor();
		
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
		elem.effect("highlight", {}, 1000);
		
	}
	
};