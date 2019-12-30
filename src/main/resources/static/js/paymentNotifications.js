$(document).ready(function() {
    $('[id^="active-switch-"]').change(function () {
        let id = this.id.substr(14);
        if ($(this).is(":checked")) {
            $.post("/paymentNotifications/activateNotification?id=" + id);
        } else {
            $.post("/paymentNotifications/deactivateNotification?id=" + id);
        }
    });
});
