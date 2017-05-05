

function changeToEnglish() {
    createCookie("lang", "en", 30);
    location.reload();
}

function changeToBulgarian() {
    createCookie("lang", "bg", 30);
    location.reload();
}

function validateLanguageAndSetClass() {
    var lang = getCookie("lang");
    if (lang == "bg") {
        $('#changeToEnglish').removeClass('active-language');
        $('#changeToBulgarian').addClass('active-language');
    } else if (lang == "en") {
        $('#changeToEnglish').addClass('active-language');
        $('#changeToBulgarian').removeClass('active-language');
    }
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function createCookie(name, value, days) {
    var expires;

    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    } else {
        expires = "";
    }
    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
}