var global = {
    domain: {}
}

getQiNiuDomain()

function getQiNiuDomain() {
    $.ajax({
        url: "/config",
        type: "GET",
        success: function (result) {
            let url = result.domain.qiniu
            if (!url.startsWith("http://")) {
                url = "http://" + url
            }
            if (!url.endsWith("/")) {
                url += "/"
            }
            global.domain.qiniu = url
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function alertAutoDismiss(alertId, text) {
    let alertElement = $("#"+alertId)
    alertElement.html(text)
    alertElement.fadeTo(2000, 500).slideUp(500, function(){
        alertElement.slideUp(500);
    });
}

