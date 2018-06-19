function getUploadToken(setToken) {
    $.ajax({
        url: "/uploadToken",
        type: "GET",
        dataType: "JSON",
        success: function (result) {
            setToken(result.token)
        },
        error: function (error) {
            alert("暂时无法上传文件")
        }
    })
}

function uploadFile() {

    let form = new FormData(document.getElementById("uploadForm"))
    $.ajax({
        url: "http://up-z2.qiniup.com/",
        type: "POST",
        data: form,
        processData: false,
        contentType: false,
        success: function (result) {
            alertAutoDismiss("alert-upload", "文件上传成功")
            setLastFile(global.domain.qiniu + result.key)
        },
        error: function (error) {
            alert("上传文件失败")
        }
    })
}

function setLastFile(url) {
    let lastFile = $("#lastUploadFile a")
    lastFile.html(url)
    lastFile.attr("href",url)
    $("#lastUploadFile").show()
}