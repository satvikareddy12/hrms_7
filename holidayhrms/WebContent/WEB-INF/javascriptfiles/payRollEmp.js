 function payroll() {
    	
    	$("#msg").text("Generating...........");

      $.ajax({
        type: "POST",
        url: "getpayslip", 
        data: $("#payrollemp").serialize(),
        success: function(response) {
        	
        	$("#msg").empty();
          var containerDiv = $(".main");
          containerDiv.html(response);
        },
        error: function() {
        	$("#msg").text("Error Occured")
          alert("Error occurred. Please try again later.");
        }
      });
    	
  
    }
 /**
 * 
 */