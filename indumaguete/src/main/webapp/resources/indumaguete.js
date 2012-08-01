;var $dgte = $dgte || {
	defaultImageUrl : 'images/default.gif',
	
	constructSummary : function(item) {
		var $summary = $('<div class="summary ui-widget-content">');
		$summary.attr('title', 'Preview of ' + item.name);		
		
		//image
		var $img = $('<img class="summary_image">').appendTo($summary);
		if(item.imageUrl) {
			$img.attr('src', item.imageUrl);
		} else {
			$img.attr('src', this.defaultImageUrl);
		}
		
		//primary info
		var $pInfo = $('<div class="summary_primaryInfo">').appendTo($summary)
			.append('<div>Name: ' + item.name)
			.append('<div>Description: <span class="summary_description">' + item.shortDescription)
			.append('<div>Business: ' + item.natureOfBusiness)
			.append('<div>Address: ' + item.address);
		
		//secondary info
		var $sInfo = $('<div class="summary_secondaryInfo">').appendTo($summary)
			.append('<div>Email: ' + item.email)
			.append('<div>Cellphone: ' + item.cellphone)
			.append('<div>Landline: ' + item.landline);
			
		return $summary;
	},
	
	alert : function (title, message) {
		$('<div>').attr('title', title).text(message).dialog({modal:true});
	}
};