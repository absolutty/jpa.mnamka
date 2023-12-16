function removeFromCart(id) {
    fetch('http://localhost:8080/cart/removeFromCart?foodId=' + id , {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            return response.json();
        })
        .then(data => {
            if (data && data.status === 'success') {
                window.location.reload();
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function changeQuantity(id, quantity) {
    if (quantity === undefined || quantity < 1) {
        return;
    }

    fetch('http://localhost:8080/cart/changeQuantity?foodId=' + id + '&quantity=' + quantity, {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            return response.json();
        })
        .then(data => {
            if (data && data.status === 'success') {
                window.location.reload();
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}