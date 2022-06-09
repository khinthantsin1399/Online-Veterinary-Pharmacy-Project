function showImage() {
    if (this.files && this.files[0]) {
        var obj = new FileReader();
        obj.onload = function(data) {
            document.getElementById("imageData").value = data.target.result;
        }
        obj.readAsDataURL(this.files[0]);
    }
}

$('#myModal').on(
    'show.bs.modal',
    function(e) {
        $(this).find('.btn-ok').attr('href',
            $(e.relatedTarget).data('href'));
        $('.debug-url').html(
            'Delete URL: <strong>' +
            $(this).find('.btn-ok').attr('href') +
            '</strong>');
    });

$(".alert").delay(3000).addClass("in").fadeOut(2000);


$(".heightline-medicine").heightLine({
    fontSizeCheck: true
});


$('#quantity').change(function() {
   var theme = $(this).val();
   changeQuantity(theme);
});

function changeQuantity(theme) {

    var data = {
            theme : theme
    };

    var url = "updateQuantity?id=${cartDetail.c_id }&quantity=${cartDetail.quantity}";

    $.ajax( {
        type : "POST",
        url : url,
        data : data
    }).done(function() {
            console.log("success");
    });
}