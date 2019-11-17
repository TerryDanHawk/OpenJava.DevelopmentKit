   function Upload() {
        $("#file_attach").click();
        $("#file_attach").change(function () {
            var formData = new FormData();
            formData.append("file", document.getElementById("file_attach").files[0]);
            formData.append("year",$("#year").val());
            formData.append("month",$("#month").val());
            $.ajax({
                url: "/excelImport",
                type: "POST",
                data: formData,
                /**
                *必须false才会自动加上正确的Content-Type
                */
                contentType: false,
                /**
                * 必须false才会避开jQuery对 formdata 的默认处理
                * XMLHttpRequest会对 formdata 进行正确的处理
                */
                processData: false,
                success: function (response) {
                    console.log(response);
                    window.location.href="/report/output";
                    //清空File
                    var file = $("#file_attach")
                    file.after(file.clone().val(""));
                    file.remove();
                },
                failure: function (response) {
                    console.log(response);
                    //清空File
                    var file = $("#file_attach")
                    file.after(file.clone().val(""));
                    file.remove();

                }
            });


        });

    }