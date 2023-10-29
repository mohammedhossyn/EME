<%--
  Created by IntelliJ IDEA.
  User: mohammedhossyn
  Date: 10/29/2023
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <meta name="author" content="EME">
    <meta name="description" content="">
    <link rel="icon" href="../resources/img/eme%20logo-white.png"/>
    <jsp:include page="css.jsp"></jsp:include>
</head>
<body class="bg-white font-family-karla">
<!-- Text Header -->
<header class="w-full container mx-auto">
    <div class="flex flex-col items-center py-12">
        <a class="font-bold text-gray-800 uppercase text-5xl">
            | EME |
        </a>
        <p class="text-lg text-gray-600">
            I'm Glad you Are Here!
        </p>
    </div>
</header>
<!-- Topic Nav -->
<nav class="w-full py-4 border-t border-b bg-gray-100" x-data="{ open: false }">
    <div class="block sm:hidden">
        <a
                href="#"
                class="block md:hidden text-base font-bold uppercase text-center flex text-left ml-5"
                @click="open = !open"
        >
            <i :class="open ? 'fa fa-bars': 'fa fa-bars'" class="fas ml-2 mt-1 mr-1"></i>Menu
        </a>
    </div>
    <div :class="open ? 'block': 'hidden'" class="w-full flex-grow sm:flex sm:items-left sm:w-auto">
        <div class="w-full container mx-auto flex flex-col sm:flex-row sm:items-center sm:justify-center text-sm font-bold uppercase mt-0 px-6 py-2">
            <a href="/main" class="hover:bg-gray-800 hover:text-white rounded py-2 px-4 mx-2 selected">Main</a>
            <a href="/projects" class="hover:bg-gray-800 hover:text-white rounded py-2 px-4 mx-2">Let's See My
                Projects</a>
            <a href="/aboutME" class="hover:bg-gray-800 hover:text-white rounded py-2 px-4 mx-2">Let ME Introduce
                MYSELF</a>
            <a href="/callME" class="hover:bg-gray-800 hover:text-white rounded py-2 px-4 mx-2">Let ME Know you</a>
        </div>
    </div>
</nav>
<div class="container mx-auto flex flex-wrap py-6">

    <!-- Posts Section -->
    <c:forEach items="${sessionScope.bookList}" var="book">
    <section class="w-full md:w-2/3 flex flex-col items-center px-3">
        <p class="text-xl font-semibold pb-1">"Every programmer needs to read these books, In my opinion"</p>
        <article class="flex flex-col shadow my-4">
            <!-- Article Image -->
            <a href="${book.googleLink}" target="_blank">
                <img class="colorize" src="${book.attachment.path}" alt="${book.attachment.name}">
            </a>
            <div class="bg-white flex flex-col justify-start p-6">
                <p class="text-blue-700 text-sm font-bold uppercase pb-4">${book.title}</p>
                <a href="${book.googleLink}"
                   class="text-3xl font-bold hover:text-gray-700 pb-4" target="_blank">${book.name}</a>
                <p class="text-sm pb-3">
                    By <a class="font-semibold hover:text-gray-800">${book.writer}</a>
                </p>
                <p class="pb-6">${book.explanation}</p>
            </div>
        </article>
        </c:forEach>

        <!--            &lt;!&ndash; Pagination &ndash;&gt;-->
        <!--            <div class="flex items-center py-8">-->
        <!--                <a href="#" class="h-10 w-10 bg-blue-800 hover:bg-blue-600 font-semibold text-white text-sm flex items-center justify-center">1</a>-->
        <!--                <a href="#" class="h-10 w-10 font-semibold text-gray-800 hover:bg-blue-600 hover:text-white text-sm flex items-center justify-center">2</a>-->
        <!--                <a href="#" class="h-10 w-10 font-semibold text-gray-800 hover:text-gray-900 text-sm flex items-center justify-center ml-3">Next <i class="fas fa-arrow-right ml-2"></i></a>-->
        <!--            </div>-->

    </section>

    <!-- Sidebar Section -->
    <aside class="w-full md:w-1/3 flex flex-col items-center px-3">

        <div class="w-full bg-white shadow flex flex-col my-4 p-6">
            <p class="text-xl font-semibold pb-5">Who Am I</p>
            <div class="grid pb-5 justify-center">
                <img class="hover:opacity-75 rounded-full justify-self-center" src="${me.attachment.path}" alt="${me.attachment.name}"
                     width="50%">
            </div>
            <p class="pb-2">${me.shortAboutMeContent}</p>
            <a href="/aboutME"
               class="w-full bg-gray-800 text-white font-bold text-sm uppercase rounded hover:bg-gray-700 flex items-center justify-center px-2 py-3 mt-4">
                MORE
            </a>
        </div>

        <div class="w-full bg-white shadow flex flex-col my-4 p-6">
            <p class="text-xl font-semibold pb-5">What I am Learning and Know some</p>
            <div class="grid grid-cols-3 gap-3">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/java.png">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/jakartaEE.png">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/oracleDB.png">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/tomcat.png">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/glassfish.png">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/python.png">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/html5.png">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/css3.png">
                <img class="hover:opacity-75 colorize" src="../resources/img/150x150/tailwind.png">
            </div>
            <p href="#"
               class="w-full bg-gray-200 text-black text-sm rounded flex items-center justify-center px-2 py-3 mt-6">
                To know detail, visit <a href="/aboutME" class="text-gray-500 font-semibold hover:text-gray-800 pl-1">"Let
                ME Introduce MYSELF"</a>
            </p>
        </div>

    </aside>

</div>
<footer class="w-full bg-white pb-12">
    <div
            class="relative w-full flex items-center invisible md:visible md:pb-12"
            x-data="getCarouselData()"
    >
        <button
                class="absolute bg-gray-800 hover:bg-gray-700 text-white text-2xl font-bold hover:shadow rounded-full w-16 h-16 ml-12 z-40"
                x-on:click="decrement()">
            &#8592;
        </button>
        <template x-for="image in images.slice(currentIndex, currentIndex + 6)" :key="images.indexOf(image)">
            <img class="w-1/6 invert" :src="image">
        </template>
        <button id="decrementBtn"
                class="absolute right-0 bg-gray-800 hover:bg-gray-700 text-white text-2xl font-bold hover:shadow rounded-full w-16 h-16 mr-12"
                x-on:click="increment()">
            &#8594;
        </button>
    </div>
    <div class="w-full container mx-auto flex flex-col items-center">
        <div class="flex  md:flex-row text-center md:text-left md:justify-between py-6">
            <a href="${me.github}" class="px-3" target="_blank"><i
                    class="fa-brands fa-github fa-4x hover:text-gray-700"></i></a>
            <a href="${me.telegram}" class="px-3" target="_blank"><i
                    class="fa-brands fa-telegram fa-4x hover:text-blue-400"></i></a>
            <a href="${me.linkedin}" class="px-3" target="_blank"><i
                    class="fa-brands fa-linkedin fa-4x hover:text-blue-800"></i></a>
            <a href="${me.instagram}" class="px-3" target="_blank"><i
                    class="fa-brands fa-instagram fa-4x instagram"></i></a>
        </div>
        <div class="uppercase pb-6">mohammedhossyn &copy; eme.java</div>
    </div>
</footer>

</body>
</html>
