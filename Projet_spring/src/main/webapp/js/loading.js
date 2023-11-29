
$(document).ready(function() {
    // Animate function
    function stepAnimateText(element, animation, delay){
        console.log("Fonction exécutée");
        const text = $(element).text();
        let curr = '';

        for (let i=0; i < text.length; i++){
            const character = text.charAt(i);
            $(element).html(curr+'<span class="'+animation+'" style="-webkit-animation-delay: '+i*delay+'s; animation-delay: '+i*delay+'s">'+character +"</span>");
            curr = $(element).html();
        }
    }
    console.log("Le DOM est prêt !");
    stepAnimateText('.fade', 'bounceInDown', 0.3);
    setTimeout(function() {
        window.location.href = '/home';
    }, 2000);
});
// Init on load
