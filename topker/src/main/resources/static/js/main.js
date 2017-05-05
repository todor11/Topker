$(document).ready(function() {
    $('#changeToEnglish').click(changeToEnglish);
    $('#changeToBulgarian').click(changeToBulgarian);
    validateLanguageAndSetClass();

    getBrowserWidth();
    changeElementsHeight();

    window.addEventListener('resize',getBrowserWidth,false);
    window.addEventListener('resize',changeElementsHeight,false);

    var deleteButtons = document.getElementsByClassName("delete-product-button");
    if (deleteButtons != undefined) {
        for (var i = 0; i < deleteButtons.length; i++) {
            deleteButtons[i].addEventListener('click', adminDeleteProduct, false);
        }
    }

    var addProductToCartButtons = document.getElementsByClassName("add-product-cart-button");
    if (addProductToCartButtons != undefined) {
        for (var i = 0; i < addProductToCartButtons.length; i++) {
            addProductToCartButtons[i].addEventListener('click', addToCart, false);
        }
    }

    countProductInCart();

});

var token = $("meta[name='_csrf']").attr("content");

$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN', token);
    }
});

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    //Show scroll button
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("scrollButton").style.display = "block";
    } else {
        document.getElementById("scrollButton").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

function getBrowserWidth() {
    var width = $( window ).width();
    if (width < 600) {
        removeUnnecessaryTextFromMenu();
    } else {
        addTextToMenu();
    }

    var mainPageContainer = $('.main-page-container');
    if (mainPageContainer != undefined) {
        if (width < 1000) {
            $('.main-page-container').css('margin-top', '70px');
            if ($('.forms') != undefined) {
                $('.forms').css('margin-top', '70px');
            }
        } else {
            $('.main-page-container').css('margin-top', '120px');
            if ($('.forms') != undefined) {
                $('.forms').css('margin-top', '120px');
            }
        }
    }
}

function changeElementsHeight() {
    var height = window.innerHeight;
    var width = $(window).width();
    var sidebarHeight = height - 140;

    if ($('#sidebar') != undefined) {
        if (width < 1000) {
            sidebarHeight = height - 90;
            $('#sidebar').css('top', '85px');
            $('#sidebarButton').css('top', '85px');
        }

        sidebarHeight = sidebarHeight + 'px';
        $('.category-filters').css('height', sidebarHeight);
    }
}

function removeUnnecessaryTextFromMenu() {
    var cart = document.getElementById("cartText");
    if (cart != undefined) {
        cart.className = "hideTextInMenu";
    }

    var logout = document.getElementById("logoutText");
    if (logout != undefined) {
        logout.className = "hideTextInMenu";
    }

    var myAccount = document.getElementById("myAccountText");
    if (myAccount != undefined) {
        myAccount.className = "hideTextInMenu";
    }

    var login = document.getElementById("loginText");
    if (login != undefined) {
        login.className = "hideTextInMenu";
    }

    var signUp = document.getElementById("signUpText");
    if (signUp != undefined) {
        signUp.className = "hideTextInMenu";
    }
}

function addTextToMenu() {
    var cart = document.getElementById("cartText");
    if (cart != undefined) {
        cart.className = "showTextInMenu";
    }

    var logout = document.getElementById("logoutText");
    if (logout != undefined) {
        logout.className = "showTextInMenu";
    }

    var myAccount = document.getElementById("myAccountText");
    if (myAccount != undefined) {
        myAccount.className = "showTextInMenu";
    }

    var login = document.getElementById("loginText");
    if (login != undefined) {
        login.className = "showTextInMenu";
    }

    var signUp = document.getElementById("signUpText");
    if (signUp != undefined) {
        signUp.className = "showTextInMenu";
    }
}

function adminDeleteProduct(e) {
    var element = e.target;
    var productId = element.id.split('_')[1];
    $.ajax({
        type: 'DELETE',
        url: "/admin/products/delete/" + productId
    })

    var mainElementId = 'tr_' + productId;
    var elToDel = document.getElementById(mainElementId);
    elToDel.remove();
}

function addToCart(e) {
    var element = e.target;
    var productId = element.id.split('_')[1];

    $.ajax({
        type: 'POST',
        url: '/cart/add/' + productId,
        dataType: 'json',
        contentType: 'application/json',
        success: function (cart) {
            drawSmallCart(cart);
        }
    });
}

function drawSmallCart(cart) {
    var container = document.getElementById('smallCart');
    while (container.childNodes.length > 5) {
        container.removeChild(container.childNodes[0]);
    }

    for (var i = cart.productNames.length - 1; i >= 0; i--) {
        var productName = cart.productNames[i];
        var productQuantity = cart.productQuantities[i];
        var productId = cart.productsIds[i];

        var elemId = 'smallCart_' + productId;
        var elementCont = document.createElement('a');
        $(elementCont)
            .addClass('dropdown-item')
            .addClass('small-cart-prod')
            .attr('id', elemId);
        var firstSpan = document.createElement('span');
        $(firstSpan).addClass('small-cart-name-prod');
        firstSpan.innerText = productName;
        var secondSpan = document.createElement('span');
        secondSpan.innerText = ' | ';
        var thirdSpan = document.createElement('span');
        $(thirdSpan).addClass('small-cart-quan-prod');
        thirdSpan.innerText = productQuantity;
        elementCont.appendChild(firstSpan);
        elementCont.appendChild(secondSpan);
        elementCont.appendChild(thirdSpan);

        container.insertBefore(elementCont, container.childNodes[0]);
    }

    countProductInCart();
}

function countProductInCart() {
    var container = document.getElementById('smallCart');
    var containerElements = container.childNodes;
    var counter = 0;
    for (var i = 0; i < containerElements.length; i++) {
        var elementId = $(containerElements[i]).attr('id');
        if (elementId != undefined && elementId.indexOf('smallCart_') != -1) {
            var parent = document.getElementById(elementId);
            var subElements = parent.childNodes;
            for (var j = subElements.length; j >= 0; j--){
                var subEl = subElements[j];
                if ($(subEl).hasClass('small-cart-quan-prod')) {
                    var currentProductQuantity = subElements[subElements.length - 1].innerText;
                    currentProductQuantity = parseInt(currentProductQuantity);
                    counter += currentProductQuantity;
                    break;
                }
            }
        }
    }

    var wholeQuantityElement = document.getElementById('whole-products-in-cart');
    wholeQuantityElement.innerText = counter;
}
