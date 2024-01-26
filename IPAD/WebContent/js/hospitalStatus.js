var firstBtn = document.getElementById('firstBtn');
var firstInfo = document.getElementById('firstInfo');

firstBtn.addEventListener("mouseover", function () {
	firstInfo.style.display = 'block'
});

firstBtn.addEventListener("mouseout", function () {
	firstInfo.style.display = 'none'
});

// --------------------------------
var secondBtn = document.getElementById('secondBtn');
var secondInfo = document.getElementById('secondInfo');

secondBtn.addEventListener("mouseover", function () {
	secondInfo.style.display = 'block'
});

secondBtn.addEventListener("mouseout", function () {
	secondInfo.style.display = 'none'
});

// --------------------------------
var thirdBtn = document.getElementById('thirdBtn');
var thirdInfo = document.getElementById('thirdInfo');

thirdBtn.addEventListener("mouseover", function () {
	thirdInfo.style.display = 'block'
});

thirdBtn.addEventListener("mouseout", function () {
	thirdInfo.style.display = 'none'
});