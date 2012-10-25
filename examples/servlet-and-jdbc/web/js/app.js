

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
		var edit = $(".button-edit", item);
		var confirm = $(".button-confirm", item);
		var cancel = $(".button-cancel", item);
		
		edit.click(function(){
			
			edit.hide();
			confirm.show();
			cancel.show();
			
			label.hide();
			form.show();
		});
		
		cancel.click(function(){
			
			confirm.hide();
			cancel.hide();
			edit.show();
			
			form.hide();
			label.show();
		});
	}
	
};