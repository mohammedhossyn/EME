<%--
  Created by IntelliJ IDEA.
  User: mohammedhossyn
  Date: 10/29/2023
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Let Me Know You</title>
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
      <a href="/mainPage" class="hover:bg-gray-800 hover:text-white rounded py-2 px-4 mx-2">Main</a>
      <a href="/projects" class="hover:bg-gray-800 hover:text-white rounded py-2 px-4 mx-2">Let's See My
        Projects</a>
      <a href="/aboutMe" class="hover:bg-gray-800 hover:text-white rounded py-2 px-4 mx-2">Let ME Introduce
        MYSELF</a>
      <a href="/callMe" class="hover:bg-gray-800 hover:text-white rounded py-2 px-4 mx-2 selected">Let ME Know you</a>
    </div>
  </div>
</nav>
<div class="container mx-auto flex flex-wrap py-6">
  <!-- Posts Section -->
  <section class="w-full flex flex-col px-3 ">
    <article class="my-4 mx-4 items-center">
      <!-- Article Image -->
      <div class="bg-white p-6 flex flex-col justify-self-center items-center">
        <i class="fa-duotone fa-phone fa-3x" style="--fa-primary-color: #000000; --fa-secondary-color: #d6d6d6;"></i><a href="tel:${sessionScope.me.phone}" class="px-3 py-2 bg-green-500 text-white font-bold text-sm rounded colorize my-3">${sessionScope.me.phone}</a>
        <i class="fa-duotone fa-mailbox fa-4x" style="--fa-primary-color: #000000; --fa-secondary-color: #d6d6d6;"></i><a href="mailto:${sessionScope.me.email}" class="px-3 py-2 bg-blue-500 text-white font-bold text-sm rounded colorize mb-3">${sessionScope.me.email}</a>
        <p class="font-semibold mt-5 text-center">Github, Telegram, Linkedin and Instagram links in footer</p>
      </div>
    </article>
  </section>
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
      <a href="https://github.com/${sessionScope.me.github}" class="px-3" target="_blank"><i
              class="fa-brands fa-github fa-4x hover:text-gray-700"></i></a>
      <a href="https://t.me/${sessionScope.me.telegram}" class="px-3" target="_blank"><i
              class="fa-brands fa-telegram fa-4x hover:text-blue-400"></i></a>
      <a href="https://linkedin.com/in/${sessionScope.me.linkedin}" class="px-3" target="_blank"><i
              class="fa-brands fa-linkedin fa-4x hover:text-blue-800"></i></a>
      <a href="https://instagram.com/${sessionScope.me.instagram}" class="px-3" target="_blank"><i
              class="fa-brands fa-instagram fa-4x instagram"></i></a>
    </div>
    <div class="uppercase pb-6">mohammedhossyn &copy; eme.java</div>
  </div>
</footer>
<jsp:include page="js.jsp"></jsp:include>
</body>
</html>
