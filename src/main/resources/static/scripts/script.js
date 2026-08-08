// O Arauto do Rei - Interactive Medieval Gazette Engine

document.addEventListener('DOMContentLoaded', function () {
    // Article text expander
    document.querySelectorAll('.custom-text').forEach(function (textElement) {
        textElement.addEventListener('click', function () {
            this.classList.toggle('show');
        });
    });

    // Map node tooltip interactive highlights
    const mapNodes = document.querySelectorAll('.map-node');
    mapNodes.forEach(node => {
        node.addEventListener('mouseenter', function () {
            this.classList.add('shadow-lg');
        });
        node.addEventListener('mouseleave', function () {
            this.classList.remove('shadow-lg');
        });
    });

    // Raven notification alert dismiss
    const ravenBanner = document.querySelector('.raven-banner');
    if (ravenBanner) {
        console.log('📜 Despacho de Corvo Ativo em Porto Real');
    }
});
