function toggleAddCardForm() {
    var form = document.getElementById("addCardForm");
    if (form.style.display === "none") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('paymentForm').addEventListener('submit', function(e) {
        if (!validateForm()) {
            e.preventDefault();
        }
    });
});
function validateForm() {
    var radios = document.getElementsByName('carteId');
    var formValid = false;
    var errorMessage = document.getElementById('error-message');

    var i = 0;
    while (!formValid && i < radios.length) {
        if (radios[i].checked) formValid = true;
        i++;
    }

    if (!formValid) {
        errorMessage.style.display = 'block';
        errorMessage.textContent = 'Veuillez sélectionner une carte.';
    } else {
        errorMessage.style.display = 'none';
    }
    return formValid;
}