   function Upload() {
        $("#file_attach").click();
        $("#file_attach").change(function () {
            var formData = new FormData();
            formData.append("file", document.getElementById("file_attach").files[0]);
            formData.append("year",$("#year").val());
            formData.append("month",$("#month").val());
            $("#progress").show();
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
                     $("#progress").hide();
                },
                failure: function (response) {
                    alert(response);
                    //清空File
                    var file = $("#file_attach")
                    file.after(file.clone().val(""));
                    file.remove();
                    $("#progress").hide();

                }
            });


        });

    }


    $(document).ready(function(){

        var nowdate=new Date();
        var nowYear=nowdate.getFullYear();
        var nowMonth=nowdate.getMonth()+1;
        for(var i=1;i>=-5;i--)
        {
           $("#year").append(String.format("<option value='{0}'>{0}</option>",nowYear+i));
        }
        $("#year").val(nowYear);
        $("#month").val(nowMonth);


    });