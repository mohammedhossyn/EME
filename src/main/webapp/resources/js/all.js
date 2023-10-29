// function openNav(){
//     if($("#nav").hasClass("open")){
//         $("#nav").removeClass("open");
//         $("#navIcon").removeClass("fa-bars-filter").addClass("fa-bars");
//         $("#navItems").removeClass("flex","justify-left").addClass("hidden");;
//     }else{
//         $("#nav").addClass("open");
//         $("#navIcon").removeClass("fa-bars").addClass("fa-bars-filter");
//         $("#navItems").removeClass("hidden").addClass("flex","justify-left");
//
//     }
// }
    function getCarouselData() {
    return {
    currentIndex: 0,
    images: [
    '../img/800x800/coffee.jpg',
    '../img/800x800//eat.jpg',
    '../img/800x800//code.jpg',
    '../img/800x800//game.jpg',
    '../img/800x800//study.jpg',
    '../img/800x800//music.jpg',
    '../img/800x800//sleep.jpg',
    '../img/800x800//repeat.jpg',
    ],
    increment() {
    this.currentIndex = this.currentIndex === this.images.length - 6 ? 0 : this.currentIndex + 1;
},
    decrement() {
    this.currentIndex = this.currentIndex === 0 ? this.images.length - 6 : this.currentIndex - 1;
},
}
}