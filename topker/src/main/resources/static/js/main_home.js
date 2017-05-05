window.onload = function () {
    loadJSONProductCategories();
    loadJSONBrands();
    //loadJSONProducts();
    $('#priceRanges label input').click(captureFilters);
    $('#closeSidebarIcon').hover(sidebarCloseEnter, sidebarCloseLeave);
};

function captureFilters(e) {
    var element = e.target;
    var elementTypeAndId = e.target.id.split('_');
    var elementType = elementTypeAndId[0];
    var id = elementTypeAndId[1];
    var productFilter = Filter.getFilter();

    if (elementType === 'category') {
        if ($(element).is(':checked')) {
            productFilter.addCategory(id);
        } else {
            productFilter.removeCategory(id);
        }
    } else if (elementType === 'brand') {
        if ($(element).is(':checked')) {
            productFilter.addBrand(id);
        } else {
            productFilter.removeBrand(id);
        }
    } else if (elementType === 'priceRange') {
        if ($(element).is(':checked')) {
            productFilter.addPriceRange(id);
        } else {
            productFilter.removePriceRange(id);
        }
    }

    //loadJSONProducts();
}

//Get JSON Products
function loadJSONProducts() {
    var filter = Filter.getFilter();
    $.ajax({
        type: 'POST',
        url: '/products/filter',
        data: JSON.stringify(filter),
        dataType: 'json',
        contentType: 'application/json',
        success: function (allProducts) {
            //products = allProducts;
            //if (products.length > 20) {
            //    var firstTwentyProducts = products.slice(0, 20);
            //    indexOfLastCreatedDomProduct = 19;
            //    loadDOMProducts(firstTwentyProducts);
            //} else {
            //    isAllProductsShown = true;
            //    loadDOMProducts(products);
            //}
        }
    });
}

function loadDOMProducts(products) {
    $.each(products, function (i, product) {
        var productName = product.name;
        var productId = 'category_' + product.id;
        //TODO




    });
}

//Get JSON Data
function loadJSONProductCategories() {
    $.ajax({
        type: 'GET',
        url: '/categories',
        data: 'json',
        success: function (categories) {
            loadDOMCategories(categories);
        }
    });
}

//Create Category DOM
function loadDOMCategories(categories) {
    $.each(categories, function (i, category) {
        var categoryName = category.name;
        var categoryId = 'category_' + category.id;
        var input = document.createElement('input');
        var indicator = document.createElement('span');
        var text = document.createElement('span');
        $(input)
            .addClass('custom-control-input')
            .addClass('product-filter')
            .attr('type', 'checkbox')
            .attr('id', categoryId)
            .click(captureFilters);
        $(indicator)
            .addClass('custom-control-indicator');
        $(text)
            .addClass('custom-control-description');
        text.innerText = categoryName;

        $('#category-box')
            .append($('<label></label>')
                .addClass('custom-control')
                .addClass('custom-checkbox')
                .append(input)
                .append(indicator)
                .append(text)
            )
    });
}

//Get JSON Data
function loadJSONBrands() {
    $.ajax({
        type: 'GET',
        url: '/brands',
        data: 'json',
        success: function (brands) {
            loadDOMBrands(brands);
        }
    });
}

//Create Category DOM
function loadDOMBrands(brands) {
    $.each(brands, function (i, brand) {
        var brandName = brand.name;
        var brandId = 'brand_' + brand.id;
        var input = document.createElement('input');
        var indicator = document.createElement('span');
        var text = document.createElement('span');
        $(input)
            .addClass('custom-control-input')
            .addClass('product-filter')
            .attr('type', 'checkbox')
            .attr('id', brandId)
            .click(captureFilters);
        $(indicator)
            .addClass('custom-control-indicator');
        $(text)
            .addClass('custom-control-description');
        text.innerText = brandName;

        $('#brand-box')
            .append($('<label></label>')
                .addClass('custom-control')
                .addClass('custom-checkbox')
                .append(input)
                .append(indicator)
                .append(text)
            )
    });
}

function sidebarCloseEnter() {
    $('#closeSidebarIcon').removeClass('fa-times');
    $('#closeSidebarIcon').addClass('fa-window-close-o');
}

function sidebarCloseLeave() {
    $('#closeSidebarIcon').removeClass('fa-window-close-o');
    $('#closeSidebarIcon').addClass('fa-times');
}

