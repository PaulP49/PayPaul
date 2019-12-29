$(document).ready(function(){
    $('.input').focus(function(){
        $(this).parent().find(".label-txt").addClass('label-active');
    });

    $(".input").focusout(function(){
        let labelText = $(this).parent().find(".label-txt");
        labelText.removeClass('label-active');
    });

    $(".submit_button").on("click", function() {
        let activeTab = $('.tab-pane.active');
        if(activeTab.is('#pane-bankAccount')) {
            $("#bankAccountForm").submit();
        } else if (activeTab.is('#pane-creditCard')) {
            $("#creditCardForm").submit();
        }
    });
});