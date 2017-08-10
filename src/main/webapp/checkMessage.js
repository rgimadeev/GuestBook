
$(document).ready(function() {
     $("#MessageForm").submit(function(e){
            e.preventDefault();
     });
       $("#submitButton").click(function(e){
            var data = $(MessageForm).serialize();
         $.ajax({
                     type: "POST",
                     url: "new-message",
                     data: data,
                     datatype:"json",
                     success: function( data, textStatus, jqXHR) {
                          if(data.success){
                           window.location.href = location.protocol+"/messages?sendMes=1";
                          }
                          else if(data.errors){
                          for (var key in data.errors){
                          $("#"+key).text(data.errors[key]);
                         }
                          }
                          },

                     error: function(jqXHR, textStatus, errorThrown){
                          console.log("Something really bad happened " + textStatus);
                     },
                       complete: function(jqXHR, textStatus){
                                         //enable the button
                                         $('#submitButton').attr("disabled", false);
                                     }

		});
           return false;
});

});

