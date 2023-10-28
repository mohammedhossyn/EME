function openNav(){
    if($("#nav").hasClass("open")){
        $("#nav").removeClass("open");
        $("#navIcon").removeClass("fa-bars-filter").addClass("fa-bars");
        $("#navItems").removeClass("flex","justify-left").addClass("hidden");;
    }else{
        $("#nav").addClass("open");
        $("#navIcon").removeClass("fa-bars").addClass("fa-bars-filter");
        $("#navItems").removeClass("hidden").addClass("flex","justify-left");

    }
}