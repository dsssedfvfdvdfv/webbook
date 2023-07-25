var taxRate = 0.05;
var shippingRate = 15.00; 
var fadeTime = 300;

/* Assign actions */
function updateQuantity(quantityInput) {
  var productRow = $(quantityInput).closest('.product');
  var price = parseFloat(productRow.find('.product-price').text());
  var quantity = parseInt($(quantityInput).val());
  var linePrice = price * quantity;
  
  /* Update line price display and recalc cart totals */
  productRow.find('.product-line-price').fadeOut(fadeTime, function() {
    $(this).text(linePrice.toFixed(2));
    recalculateCart();
    $(this).fadeIn(fadeTime);
  });  
}

function removeItem(removeButton) {
    var productRow = $(removeButton).parent().parent();
    productRow.slideUp(fadeTime, function() {
        productRow.remove();
        recalculateCart();
    });
}

/* Recalculate cart */
function recalculateCart() {
  var subtotal = 0;

  /* Sum up row totals */
  $('.product').each(function () {
    var linePrice = parseFloat($(this).find('.product-line-price').text());
    subtotal += linePrice;
  });

  /* Calculate totals */
  var tax = subtotal * taxRate;
  var shipping = (subtotal > 0 ? shippingRate : 0);
  var total = subtotal + tax + shipping;

  /* Update totals display */
  $('.totals-value').fadeOut(fadeTime, function() {
    $('#cart-subtotal').text(subtotal.toFixed(2));
    $('#cart-tax').text(tax.toFixed(2));
    $('#cart-shipping').text(shipping.toFixed(2));
    $('#cart-total').text(total.toFixed(2));
    if(total == 0){
      $('.checkout').fadeOut(fadeTime);
    }else{
      $('.checkout').fadeIn(fadeTime);
    }
    $('.totals-value').fadeIn(fadeTime);
  });
}