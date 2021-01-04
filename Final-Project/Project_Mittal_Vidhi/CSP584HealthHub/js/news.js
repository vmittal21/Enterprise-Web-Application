$(document).ready(function() {
    var url = 'http://newsapi.org/v2/top-headlines?' +
    		  'country=us&category=health&' +
	          'apiKey=234ecd66713a42f29cb57d2158344b56';
    fetch(url)
        .then(res => res.json())
        .then(data => {
        	var carouselItem = document.querySelector(".carousel-inner").children;
        	for (i = 0; i < carouselItem.length; i++) {
        		carouselItem[i].children[0].innerText = data.articles[i].title;
        		carouselItem[i].children[0].href = data.articles[i].url;
        		carouselItem[i].children[1].src = data.articles[i].urlToImage;
        	}
        })
        .catch(err => console.log('Request Failed:', err));
})