

$(document).ready(function(){
    new AppController().init();
});


AppController = function(){
	
	var allItems = [];
	
	this.init = function(){
		
		$(".item").each(function(i, item){
			item = $(item);
			initItem(item);
		});
		
	};
	
	function initItem(item){
		
		item.hasError = function(){
			return this.parent().hasClass("error-wrap");
		}
		
		var label = $(".item-text", item);
		var form = $(".item-form", item);
		var input = $(".item-input", form);
		
		var edit = $(".button-edit", item);
		var confirm = $(".button-confirm", item);
		var cancel = $(".button-cancel", item);
		var errorText = $(".msg-label", item.parent());
		
		var editFunc = function(){
			
			$.each(allItems, function(i, otherItem){
				otherItem.cancel();
			});
			
			edit.hide();
			confirm.show();
			cancel.show();
			
			label.hide();
			
			if( ! item.hasError()){
				input.val(label.text());
			}
			
			form.show();
			input.focus();
			input.caretToEnd();
		};
		
		var cancelFunc = function(){
			confirm.hide();
			cancel.hide();
			edit.show();
			
			form.hide();
			label.show();
			
			errorText.hide();
			item.parent().removeClass("error-wrap");
			item.parent().removeClass("updated-wrap");
		};
		
		edit.click(editFunc);
		cancel.click(cancelFunc);
		
		input.keydown(function(e) {
			if (e.keyCode == 27) { 
				cancelFunc();
			}
		});
		
		if(item.hasError()){
			editFunc();
		}
		
		allItems.push({cancel:cancelFunc});
		
		
		edit.addClass("inactive");
		
		edit.mouseover(function(){
			edit.removeClass("inactive");
		});
		
		edit.mouseleave(function(){
			edit.addClass("inactive");
		});
		
	}
	
};