

$(document).ready(function(){
	
	new GuideController().init();
	
});

GuideController = function(){
	
	this.init = function(){
		
		initToc();
		
	};
	
	function initToc(){
		
		var tocRoot = $(".toc");
		var listRoot = tocRoot.html("<dl></dl>").children().last();
		
		var h2Elems = $("h2");
		$.each(h2Elems, function(i, elem){
			
			//update title
			elem = $(elem);
			var count = i + 1;
			var content = elem.text();
			var contentElem = elem.html("<a id='p"+count+"'></a>").children().last();
			content = count+". "+content;
			contentElem.text(content);
			
			//add to toc
			var listElem = listRoot.append("<dt></dt>").children().last();
			var tocElem = listElem.html("<a href='#p"+count+"'></a>").children().last();
			tocElem.text(content);
			
		});
		
		
	}
	
};