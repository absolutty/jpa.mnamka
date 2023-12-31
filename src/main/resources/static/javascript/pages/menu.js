let priceForOne;

document.addEventListener('DOMContentLoaded', function () {
    const MINIMUM_QUANTITY = 1;

    let quantityLabel = document.getElementById('quantityLabel');
    let addToCartButton = document.getElementById('addToCartButton');
    let decreaseButton = document.getElementById('decreaseQuantityButton');
    let increaseButton = document.getElementById('increaseQuantityButton');

    decreaseButton.addEventListener('click', function () {
        let actualQuantity = parseInt(quantityLabel.textContent);
        if (actualQuantity > MINIMUM_QUANTITY) {
            actualQuantity--;
            quantityLabel.textContent = actualQuantity.toString(10);
            setButtonText(addToCartButton, priceForOne, actualQuantity);
        }
    });

    increaseButton.addEventListener('click', function () {
        let actualQuantity = parseInt(quantityLabel.textContent);
        actualQuantity++;
        quantityLabel.textContent = actualQuantity.toString(10);
        setButtonText(addToCartButton, priceForOne, actualQuantity);
    });
});

function showModalAddToCart(id, name, description, measurement, price, img_url) {
    const MINIMUM_QUANTITY = 1;
    priceForOne = price;

    let nameLabel = document.getElementById('foodDetailModalLabel');
    nameLabel.textContent = name;

    let foodImgDiv = document.getElementById('foodImg');
    foodImgDiv.style.backgroundImage = 'url(' + img_url + ')';

    let descriptionLabel = document.getElementById('foodDescription');
    descriptionLabel.textContent = description;

    let measurementLabel = document.getElementById('foodMeasurement');
    measurementLabel.textContent = 'Množstvo: ' + measurement;

    let quantityLabel = document.getElementById('quantityLabel');
    quantityLabel.textContent = MINIMUM_QUANTITY;

    let addToCartButton = document.getElementById('addToCartButton');
    setButtonText(addToCartButton, price, parseInt(quantityLabel.textContent));
    addToCartButton.addEventListener('click', function () {
        addToCart(parseInt(id), parseInt(quantityLabel.textContent));
    });

    const foodDetailModal = new bootstrap.Modal('#foodDetailModal');
    if (foodDetailModal) {
        foodDetailModal.show();
    }
}

function setButtonText(button) {
    button.textContent = 'Pridaj do košíka';
}

function addToCart(id, quantity) {
    $.ajax({
        url: 'http://localhost:8080/cart/addToCart',
        method: 'POST',
        data: {
            foodId: id,
            quantity: quantity
        },
        dataType: 'json',
        success: function (data) {
            if (data && data.status === 'success') {
                $('#cartLink').css('display', 'block');
                $('#foodDetailModal').modal('hide');
                $('#addToCartButton').replaceWith($('#addToCartButton').clone());
            }
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}