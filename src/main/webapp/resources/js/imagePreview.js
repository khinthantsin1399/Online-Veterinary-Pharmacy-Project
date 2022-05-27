$(document).ready(function() {
    $('#fileUpload').change(function() {
        showImgThumbnail(this);
    });
});
function showImgThumbnail(fileInput) {
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function(e) {
        $('#medicine_image').attr('src', e.target.result);
    };
    reader.readAsDataURL(file);
}