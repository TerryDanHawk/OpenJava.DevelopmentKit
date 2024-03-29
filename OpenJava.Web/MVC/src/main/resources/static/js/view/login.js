'use strict';

var placeholders = document.querySelectorAll('.styled-input__placeholder-text'),
    inputs = document.querySelectorAll('.styled-input__input');

placeholders.forEach(function (el, i) {
    var value = el.innerText,
        html = '';
    for (var _iterator = value, _isArray = Array.isArray(_iterator), _i = 0, _iterator = _isArray ? _iterator : _iterator[Symbol.iterator]();;) {
        var _ref;

        if (_isArray) {
            if (_i >= _iterator.length) break;
            _ref = _iterator[_i++];
        } else {
            _i = _iterator.next();
            if (_i.done) break;
            _ref = _i.value;
        }

        var w = _ref;

        if (!value) value = '&nbsp;';
        html += '<span class="letter">' + w + '</span>';
    }
    el.innerHTML = html;
});

inputs.forEach(function (el) {
    var parent = el.parentNode;
    el.addEventListener('focus', function () {
        parent.classList.add('filled');
        placeholderAnimationIn(parent, true);
    }, false);
    el.addEventListener('blur', function () {
        if (el.value.length) return;
        parent.classList.remove('filled');
        placeholderAnimationIn(parent, false);
    }, false);
});

function placeholderAnimationIn(parent, action) {
    var act = action ? 'add' : 'remove';
    var letters = parent.querySelectorAll('.letter');
    letters = [].slice.call(letters, 0);
    if (!action) letters = letters.reverse();
    letters.forEach(function (el, i) {
        setTimeout(function () {
            var contains = parent.classList.contains('filled');
            if (action && !contains || !action && contains) return;
            el.classList[act]('active');
        }, 50 * i);
    });
}

setTimeout(function () {
    document.body.classList.add('on-start');
}, 100);

setTimeout(function () {
    document.body.classList.add('document-loaded');
}, 1800);

function Login()
{
   var username=$("#username").val();
   var password=$("#password").val();
   password=btoa(password);
   var formData= new FormData();
   formData.append("username",username);
   formData.append("password",password);
   $.ajax({
                url: "/api/login",
                type: "POST",
                contentType: false,
                // 告诉jQuery不要去设置Content-Type请求头
                processData: false,
                // 告诉jQuery不要去处理发送的数据
                data: formData,
                success: function (response) {
                  if(response=="")
                  {
                    window.location.href="/Home/Index";
                  }
                  else
                  {
                     $("#msg").html(response);
                     $("#msgbox").addClass("is-active");
                  }
                },
                failure: function (response) {
                      $("#msg").html(response);
                      $("#msgbox").addClass("is-active");

                }
            });

}

function CloseModal()
{
   $("#msgbox").removeClass("is-active");
}

