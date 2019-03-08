function change_bg() {
    var x = ((Math.random() * 1000) % 283) + 1;
    x = x.toFixed(0);
    // alert("x=" + x);
    document.getElementById("web_bg").style.backgroundImage = "url(/img/pic/2045-" +  x + ".jpg)";
    document.getElementById("down_bg").href = "/img/pic/2045-" +  x + ".jpg";
}