var Filter = (function () {
    function Filter(){
        this.categories = [];
        this.brands = [];
        this.priceRanges = [];
    }

    Filter.prototype.addCategory = function (categoryId) {
        categoryId = "" + categoryId;
        this.categories.push(categoryId);
    }

    Filter.prototype.removeCategory = function (categoryId) {
        var categoryIdAsStr = "" + categoryId;
        var index = this.categories.indexOf(categoryIdAsStr);
        if (index == this.categories.length - 1) {
            this.categories.pop();
        } else {
            this.categories = this.categories.slice(index, index + 1);
        }
    }

    Filter.prototype.addBrand = function (brandId) {
        brandId = "" + brandId;
        this.brands.push(brandId);
    }

    Filter.prototype.removeBrand = function (brandId) {
        var brandIdAsStr = "" + brandId;
        var index = this.brands.indexOf(brandIdAsStr);
        if (index == this.brands.length - 1) {
            this.brands.pop();
        } else {
            this.brands = this.brands.slice(index, index + 1);
        }
    }

    Filter.prototype.addPriceRange = function (priceRange) {
        this.priceRanges.push(priceRange);
    }

    Filter.prototype.removePriceRange = function (priceRange) {
        var index = this.priceRanges.indexOf(priceRange);
        if (index == this.priceRanges.length - 1) {
            this.priceRanges.pop();
        } else {
            this.priceRanges = this.priceRanges.slice(index, index + 1);
        }
    }

    var filter;

    return {
        getFilter: function () {
            if (!filter) {
                filter = new Filter();
            }
            return filter;
        }
    };
}) ();