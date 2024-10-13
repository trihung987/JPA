
$("input[type=file]").on('change',function(){
	var name = $(this).val().split("\\fakepath\\")[1];
	console.log(name);
	var delayInMilliseconds = 50; //delay 0.05 second for loading web
			 setTimeout(function() {
	$("img").attr('src', "http://localhost:8080/ServletJPA/image?fname="+name);
    //alert(value);//I mean name of the file
	
	}, delayInMilliseconds);
	
});