<%-- 
    Document   : movie
    Created on : 18 May 2025, 11:22:51?pm
    Author     : sitif
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Movies - Veloura Cinema</title>
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Poppins:wght@400;600&display=swap" rel="stylesheet">
        <style>
            body {
                background-color: #000;
                color: #fff;
                font-family: 'Poppins', sans-serif;
                margin: 0;
                padding: 0;
            }

            .tabs {
                display: flex;
                gap: 20px;
            }

            .tab-link {
                background: none;
                border: none;
                color: #fff;
                font-size: 16px;
                cursor: pointer;
                border-bottom: 2px solid transparent;
                padding: 8px 12px;
                transition: 0.3s ease;
            }

            .tab-link:hover, .tab-link.active {
                color: #EDDE80;
                border-bottom: 2px solid #EDDE80;
            }

            .movie-container {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                gap: 20px;
                padding: 20px 40px;
            }

            .movie-card {
                background-color: #111;
                border-radius: 12px;
                overflow: hidden;
                position: relative;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }

            .movie-card img {
                width: 100%;
                height: 350px;
                object-fit: cover;
            }

            .movie-card:hover {
                transform: scale(1.05);
                box-shadow: 0 8px 12px rgba(0, 0, 0, 0.8);
            }

            .movie-info {
                padding: 10px 15px;
            }

            .movie-info h4 {
                margin: 0;
                font-size: 18px;
                color: #EDDE80;
            }

            .movie-info p {
                margin: 5px 0;
                font-size: 14px;
            }

            @media (max-width: 768px) {
                .movie-header {
                    flex-direction: column;
                    align-items: flex-start;
                }

                .tabs {
                    justify-content: center;
                    margin-top: 10px;
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="includes/header.jsp" flush="true"/>

        <div class="movie-header">
            <h1>Movies</h1>
            <div class="tabs">
                <button class="tab-link active" onclick="openTab('now')">Now Showing</button>
                <button class="tab-link" onclick="openTab('coming')">Coming Soon</button>
            </div>
        </div>

        <!-- Now Showing Movies -->
        <div id="now" class="tab-content">
            <div class="movie-container">
                <c:forEach var="movie" items="${nowShowingMovies}">
                    <div class="movie-card">
                        <img src="${movie.imagePath}" alt="${movie.title} Poster">
                        <div class="movie-info">
                            <h4>${movie.title}</h4>
                            <p>Genre: ${movie.genre}</p>
                            <p>Language: ${movie.language}</p>
                            <p>Duration: ${movie.hours} hr ${movie.minutes} mins</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Coming Soon Movies -->
        <div id="coming" class="tab-content" style="display:none;">
            <div class="movie-container">
                <c:forEach var="movie" items="${comingSoonMovies}">
                    <div class="movie-card">
                        <img src="${movie.imagePath}" alt="${movie.title} Poster">
                        <div class="movie-info">
                            <h4>${movie.title}</h4>
                            <p>Genre: ${movie.genre}</p>
                            <p>Language: ${movie.language}</p>
                            <p>Duration: ${movie.hours} hr ${movie.minutes} mins</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <jsp:include page="includes/footer.jsp" flush="true"/>

        <script>
            function openTab(tabName) {
                const tabs = document.querySelectorAll('.tab-content');
                const links = document.querySelectorAll('.tab-link');

                tabs.forEach(tab => tab.style.display = 'none');
                document.getElementById(tabName).style.display = 'block';

                links.forEach(link => link.classList.remove('active'));
                document.querySelector(`.tab-link[onclick="openTab('${tabName}')"]`).classList.add('active');
            }
        </script>
    </body>
</html>


