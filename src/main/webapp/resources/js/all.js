function getCarouselData() {
    return {
        currentIndex: 0,
        images: [
            '../resources/img/800x800/coffee.jpg',
            '../resources/img/800x800//eat.jpg',
            '../resources/img/800x800//code.jpg',
            '../resources/img/800x800//game.jpg',
            '../resources/img/800x800//study.jpg',
            '../resources/img/800x800//music.jpg',
            '../resources/img/800x800//sleep.jpg',
            '../resources/img/800x800//repeat.jpg',
        ],
        increment() {
            this.currentIndex = this.currentIndex === this.images.length - 6 ? 0 : this.currentIndex + 1;
        },
        decrement() {
            this.currentIndex = this.currentIndex === 0 ? this.images.length - 6 : this.currentIndex - 1;
        },
    }
}

$("textarea").each(function () {
    this.setAttribute("style", "height:" + (this.scrollHeight) + "px;overflow-y:hidden;");
}).on("input", function () {
    this.style.height = 0;
    this.style.height = (this.scrollHeight) + "px";
});