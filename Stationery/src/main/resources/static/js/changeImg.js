  var index = 1;
  var imgs = ["../img/anhqc/LeMayT5_Slide_V2_840x320.jpg", "../img/anhqc/DinhTi_T5_Slide_840x320.jpg", "../img/anhqc/Slide_V2_840x320.jpg"];
  var timer;

function startSlideShow() {
  timer = setInterval(changeIMG, 2000);
}

function stopSlideShow() {
  clearInterval(timer);
}

function changeIMG() {
  index++;
  if (index >= imgs.length) {
    index = 0;
  }
  document.getElementById('img').src = imgs[index];
}

let currentSlide = 0;
const imgElement = document.getElementById('img');

function showSlide(slideIndex) {
  if (slideIndex < 0) {
    slideIndex = imgs.length - 1;
  } else if (slideIndex >= imgs.length) {
    slideIndex = 0;
  }

  imgElement.src = imgs[slideIndex];
  currentSlide = slideIndex;
}

function changeSlide(n) {
  showSlide(currentSlide + n);
  stopSlideShow();
}

showSlide(currentSlide);
startSlideShow();
