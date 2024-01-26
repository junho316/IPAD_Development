window.onload = function () {
    const myCarousel = document.getElementById("mainCarousel")
    var carouselIndicators = myCarousel.querySelectorAll(
        ".carousel-indicators button span"
    );
    let intervalID;
    const carousel = new bootstrap.Carousel(myCarousel);


    fillCarouselIndicator(1);


    myCarousel.addEventListener("slide.bs.carousel", function (e) {
        let index = e.to;
        fillCarouselIndicator(++index);
    });

    function fillCarouselIndicator(index) {
        let i = 0;
        for (const carouselIndicator of carouselIndicators) {
            carouselIndicator.style.width = 0;
        }
        clearInterval(intervalID);
        carousel.pause();

        intervalID = setInterval(function () {
            i++;

            myCarousel.querySelector(".carousel-indicators .active span").style.width =
                i + "%";

            if (i >= 100) {
                // i = 0; -> just in case
                carousel.next();
            }
        }, 40);
        // 4초여서 40으로 해놓은 것
    }

}