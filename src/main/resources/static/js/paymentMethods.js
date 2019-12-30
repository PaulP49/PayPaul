$(document).ready(function() {
    $('[id^="active-switch-"]').change(function () {
        let id = this.id.substr(14);
        if ($(this).is(":checked")) {
            $.post("/paymentMethods/activateMethod?id=" + id);
        } else {
            $.post("/paymentMethods/deactivateMethod?id=" + id);
        }
    });
});