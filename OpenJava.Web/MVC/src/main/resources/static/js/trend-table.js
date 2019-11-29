$(document).ready(function(){

  $('#quicksearch').keyup(function (event) {
            if (event.keyCode == 13) {
                $('table tbody tr').hide()
                    .filter(":contains('" + ($(this).val()) + "')").show();
            }
        }).keyup();//DOM加载完时，绑定事件完成之后立即触发


});

function Export(sender) {


        return ExcellentExport.excel(sender, 'trendTable', 'Sheet1');

   }