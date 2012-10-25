

$(document).ready(function(){
    new AppController().init();
});


AppController = function(){
	
	this.init = function(){
		
		$(".item").each(function(i, item){
			item = $(item);
			initItem(item);
		});
		
	};
	
	function initItem(item){
		
		var label = $(".item-text", item);
		var form = $(".item-form", item);
		var input = $(".item-input", form);
		
		var edit = $(".button-edit", item);
		var confirm = $(".button-confirm", item);
		var cancel = $(".button-cancel", item);
		
		var editFunc = function(){
			edit.hide();
			confirm.show();
			cancel.show();
			
			label.hide();
			
			input.val(label.text());
			
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
		};
		
		edit.click(editFunc);
		cancel.click(cancelFunc);
		
		input.keydown(function(e) {
			if (e.keyCode == 27) { 
				cancelFunc();
			}
		});
		
	}
	
};