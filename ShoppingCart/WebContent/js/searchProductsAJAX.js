    var request;
	function searchInfo(){
	 	if (window.XMLHttpRequest) {
	    	// code for modern browsers
	    	request = new XMLHttpRequest();
	   	} else {
	    	// code for IE6, IE5
	    	request = new ActiveXObject("Microsoft.XMLHTTP");
	    }
		var term = document.getElementById('search').value;
		var url = "SingleController?page=searchAJAX&term=" + term;
		var string='';
		try {
			if((term.trim())==''||(term==null)){
				document.getElementById('searchingList').innerHTML = '';
			} else { 
				request.onreadystatechange=function(){
					if(request.readyState==4){
						var b = "</a>";
						var val = JSON.parse(request.responseText);
						for (var i=0; i < val.length; i++) {
							var a1 = "<a class='aLinks' href='SingleController?page=filterP&prname=" + val[i]+ "'" + "target='frame3'>";
							string +=  a1 + val[i] + b;
						}
						document.getElementById('searchingList').innerHTML = string;
					}else{
						document.getElementById('searchingList').innerHTML = '';
					}
				}//end of function
				request.open("GET",url,true);
				request.send();
			}
		}catch(e){
			alert("Unable to connect to server");
			document.getElementById('searchingList').innerHTML = '';
		}
	}	
