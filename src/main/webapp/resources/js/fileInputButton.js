$(document).ready(function () {
    $('#fakeInput').click(function () {
        $(".mainInput").click();
    });
    $('#editFakeInput').click(function () {
        $(".editMainInput").click();
    });
});
$('.mainInput').change(function () {
    $('#selected_filename').text($('.mainInput')[0].files[0].name);
});
$('.editMainInput').change(function () {
    $('#editSelected_filename').text($('.editMainInput')[0].files[0].name);
});