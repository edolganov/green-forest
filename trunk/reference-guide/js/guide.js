

$(document).ready(function(){
	
	new GuideController().init();
	
});

GuideController = function(){
	
	this.init = function(){
		
		initToc();
		initSpecialTags();
		addSpaceToP();
		
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
	
	
	function initSpecialTags(){
		
		replaceSpecialTag("gff", "<span class='name'>Green-forest Framework</span>");
		replaceSpecialTag("gf", "<span class='name'>Green-forest</span>");
		replaceSpecialTag("sf", "<span class='name'>Spring Framework</span>");
		replaceSpecialTag("jee", "<span class='name'>JEE</span>");
		
	}
	
	function replaceSpecialTag(name, replaceHtml){
		var parentElems = $(name).parent();
		parentElems.each(function(i, parent){
			parent = $(parent);
			var text = parent.html();
			text = text.replaceAll("<"+name+">", replaceHtml);
			parent.html(text);
		});

	}
	
	function addSpaceToP(){
		
		$("p").each(function(i, p){
			p = $(p);
			var text = p.html();
			text = "&nbsp;&nbsp;"+text;
			p.html(text);
		});
		
	}
	
};