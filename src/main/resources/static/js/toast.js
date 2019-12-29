$(document).ready(function() {
   $('.toast-button').on("click", function() {
       $(this).parent().parent().css("opacity", 0);
   });
});