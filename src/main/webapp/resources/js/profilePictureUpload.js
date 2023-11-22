$('#profilePicture').on('click', function () {
    $("input:file").trigger('click');
});
$("input:file").on('change', function () {
    $(".uploadButton").trigger('click');
})
