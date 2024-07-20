// src/main/resources/static/scripts/scripts.js
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.custom-text').forEach(function (textElement) {
        textElement.addEventListener('click', function () {
            this.classList.toggle('show');
        });
    });
});
