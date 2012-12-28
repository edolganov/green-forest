

$(document).ready(function(){
	
	new GuideController().init();
	
});

GuideController = function(){
	
	this.init = function(){
		
		var skipPreprocessing = $(".disablePreprocessingFlag").length > 0;
		
		if( ! skipPreprocessing){
			
			addSkipFlag();
			
			loadImports(function(){
				initToc();
				initSpecialTags();
				addSpaceToP();
				
				afterPreprocessing();
			});
			
		} else {
			afterPreprocessing();
		}

		
	};
	
	function addSkipFlag(){
		$("body").prepend("<span class='disablePreprocessingFlag'></span>");
	}
	
	function afterPreprocessing(){
		SyntaxHighlighter.config.bloggerMode = true;
		SyntaxHighlighter.defaults['toolbar'] = false;
		SyntaxHighlighter.highlight();
		new EffectsController().init();
	}
	
	
	function loadImports(finishCallback){
		
		var imports = $(".importBlock");
		var elems = [];
		$.each(imports, function(i, elem){
			elems.push($(elem));
		});
		
		var i = 0;
		var onLoad = function(html){
			
			//process loaded
			var elem = elems[i];
			setImportHtml(elem, html);
			
			//load next
			i++;
			if(i < elems.length){
				loadImport(elems[i], onLoad);
			} else {
				finishCallback();
			}
		};
		
		//load first
		if(elems.length > 0){
			loadImport(elems[i], onLoad);
		} else {
			finishCallback();
		}
		
	}
	
	function loadImport(elem, callback){
		
		var url = elem.attr("p-url");
		$.ajax({
			url: url,
			cache:false,
			dataType:"text",
			success: callback,
			error: function(){
				alert("error while loading html by url: "+url);
			}
		});
		
	}
	
	function setImportHtml(elem, html){
		elem.html(html);
	}
	
	
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
			var spaceStr = "<span class='start-space'/>";
			p = $(p);
			var text = p.html();
			text = spaceStr+text;
			text = text.replaceAll("<br>","<br>"+spaceStr);
			p.html(text);
		});
		
	}
	
};