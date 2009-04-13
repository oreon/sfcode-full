jQuery.fn.defuscate = function(){
   return this.each(function(){
	 var email = String($(this).html()).replace(/\s*\(.+\)\s*/, "@");
//	 $(this).before('<a href="mailto:'+email+'">'+email+'</a>').remove();
	 $(this).before().html('<a href="mailto:'+email+'">'+email+'</a>');
   });
};


// remove() is too aggressive, it takes out the custom li:before content
// as well as the tag content we want to replace. instead, we'll get 
// rid of remove() and just re-write the html tag contents with html().
