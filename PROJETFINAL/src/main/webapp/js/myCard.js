function toggleAddCardForm() {
    var form = document.getElementById("addCardForm");
    if (form.style.display === "none") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}