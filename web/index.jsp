<%-- 
    Document   : homepage
    Created on : 26 May 2025, 1:00:35?pm
    Author     : sitif
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Veloura Cinema</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&family=Vibur&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/header.css"/>
        <link rel="stylesheet" href="assets/css/footer.css"/>
        <link rel="stylesheet" href="assets/css/movie.css">
    </head>
    <body>
        <jsp:include page="includes/header.jsp" flush="true"/>

        <h1 class="glow-gold mt-3 display-4" style="text-align: left; padding-left: 2.5rem;">Showtimes</h1>
        <div class="container py-4 homepage-container">


            <!-- Now Showing Custom Scroll Carousel -->
            <h3 class="glow-gold mb-3">Now Showing</h3><br>

            <div class="neon-legend-box" id="nowShowingContainer">
                <div class="movie-scroll-wrapper">
                    <c:forEach var="movie" items="${movies}">
                        <c:if test="${movie.status ne 'Expired'}">
                            <div class="movie-card ">
                                <img src="${pageContext.request.contextPath}/assets/images/${movie.imagePath}" alt="${movie.title} Poster">
                                <div class="movie-info overflow-auto scrollbar-gold">
                                    <button>View More</button>
                                    <div class="details">
                                        Genre: ${movie.genre}<br>
                                        Duration: ${movie.hours} hr ${movie.minutes} mins<br>
                                        Language: ${movie.language}<br>
                                        Subtitle: ENG, MALAY
                                    </div>
                                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/booking?id=${movie.movieId}">Buy Now</a>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

            <button class="arrow left" onclick="scrollMovies(-1)">&#9664;</button>
            <button class="arrow right" onclick="scrollMovies(1)">&#9654;</button>
        </div>


        <!-- Top 5 Ranking Section -->
        <!-- Top 10 Ranking Section -->
        <section class="top-movies-section container mb-5">
            <h2 class="glow-gold mb-4">Top 10 Movies</h2>
            <div class="top10-grid">
                <div class="movie-rank"><span class="rank">1</span> Blood Brothers: Bara Naga <span class="badge now">Now Showing</span></div>
                <div class="movie-rank"><span class="rank">6</span> Avatar: The Way of Water <span class="badge now">Now Showing</span></div>

                <div class="movie-rank"><span class="rank">2</span> Final Destination Bloodlines <span class="badge coming">Coming Soon</span></div>
                <div class="movie-rank"><span class="rank">7</span> Howl's Moving Castle <span class="badge coming">Coming Soon</span></div>

                <div class="movie-rank"><span class="rank">3</span> Barbie <span class="badge now">Now Showing</span></div>
                <div class="movie-rank"><span class="rank">8</span> Oppenheimer <span class="badge now">Now Showing</span></div>

                <div class="movie-rank"><span class="rank">4</span> Spider-Man: Across the Spider-Verse <span class="badge now">Now Showing</span></div>
                <div class="movie-rank"><span class="rank">9</span> Puss in Boots: The Last Wish <span class="badge coming">Coming Soon</span></div>

                <div class="movie-rank"><span class="rank">5</span> Wonka <span class="badge coming">Coming Soon</span></div>
                <div class="movie-rank"><span class="rank">10</span> Hunger Games: The Ballad of Songbirds & Snakes <span class="badge now">Now Showing</span></div>
            </div>
        </section>


        <jsp:include page="includes/footer.jsp" flush="true"/>
        <script>
            function scrollMovies(direction) {
                const container = document.querySelector('.movie-scroll-wrapper');
                const scrollAmount = container.querySelector('.movie-card').offsetWidth + 20;
                container.scrollBy({
                    left: direction * scrollAmount,
                    behavior: 'smooth'
                });
            }

            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll('.star-rating').forEach(star => {
                    const rating = parseFloat(star.getAttribute('data-rating'));
                    const percent = (Math.min(rating, 5) / 5) * 100;
                    star.style.setProperty('--percent', `${percent}%`);
                });
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>