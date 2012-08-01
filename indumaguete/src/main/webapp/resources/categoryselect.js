var catsel = {
	createCategoryTable : function(categories, $parent, mode) {		
		var $categoryTable = $('<table>');
		var $body = $('<tbody>').appendTo($categoryTable);
		
		if(mode != 'registration') {
			var allTd = $('<td align="right">').append('<span class="dgte-select-title-any">All').click(function(){
				catsel.choose('All', $parent);
			});
			$('<tr>').appendTo($body).append('<td colspan=3>').append(allTd); //All categories
		} 
		
		var c = 0;
		$.each(categories, function(i, members) {			
			c = 0;
			var tr = $('<tr>').appendTo($body);
			var td = $('<td colspan=3>').appendTo(tr);
			var div = $('<span class="dgte-select-title">').text(i).appendTo(td);
			
			if(mode != 'registration') {
				var anyTd = $('<td align="right">').appendTo(tr);
				var any = $('<span class="dgte-select-title-any">').attr('category', i).text('Any ' + i).appendTo(anyTd)
					.click(function(){
						catsel.choose('Any ' + i, $parent);
					});
			}
			
			$('<tr>').appendTo($body);
						
			var $currentTd;
			$.each(members, function(j, member){  
				$currentTd = $('<td>').appendTo($body.find('tr:last'));
				$('<span class="dgte-select-member">' + member + '</span>').appendTo($currentTd)
				.click(function(){
					catsel.choose(member, $parent);
				});
				
				c++;				
				if(c%4 == 0) $body.append('<tr>'); 				
			});//each memeber
		});//each category		
		
		return $categoryTable;
	},
	
	choose : function(value, $parent) {
		$parent.find('.ui-button-text').text(value);
		if(this.select) {
			this.select.offset({top: 0, left: 0});	
			this.select.hide();
		}
	},
	
	createSelect : function(categories, $parent, mode) {		
		this.select = this.select || $('<div class="dgte-select ui-widget">').append(catsel.createCategoryTable(categories, $parent, mode)).appendTo('body').click(function(event){event.stopPropagation();});			
		this.positionSelect($parent);
		return this.select;
	},
	
	positionSelect : function($parent) {
		var offset = $parent.offset();
		var height = $parent.height();
		var top = offset.top + height + "px";
		var left = offset.left + "px";

		this.select.css( {
		    'position': 'absolute',
		    'left': left,
		    'top': top
		});
	}
};

(function(){
	$('html').click(function(){		
		if(catsel.select) {
			catsel.select.hide();
		}	
	});
})();