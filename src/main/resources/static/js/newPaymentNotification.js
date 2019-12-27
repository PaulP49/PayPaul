$(document).ready(function(){
    $.fn.selectpicker.Constructor.BootstrapVersion = '4';

    $('.input').focus(function(){
        $(this).parent().find(".label-txt").addClass('label-active');
    });

    $(".input").focusout(function(){
        let labelText = $(this).parent().find(".label-txt");
        labelText.removeClass('label-active');
    });

    // Map selected options to thymeleaf fields
    $('#paymentDirSelect').on("change", function() {
        console.log("changed");
        let value = $(this).val();
        let incomingField = $('#inputForIncomingPayments');
        let outgoingField = $('#inputForOutgoingPayments');
        switch (value.length) {
            case 0:
                incomingField.val(false);
                outgoingField.val(false);
                break;
            case 1:
                if (value[0] === 'Incoming') {
                    incomingField.val(true);
                    outgoingField.val(false);
                } else if (value[0] === 'Outgoing') {
                    incomingField.val(false);
                    outgoingField.val(true);
                }
                break;
            case 2:
                incomingField.val(true);
                outgoingField.val(true);
                break;
        }
    });
});