var percent = 0

function eatCount(){
  document.querySelector(".monsterText").innerHTML = ("你好<br>在这儿");
  // $(".monsterText").html("We are<br>SQUARE<br>MONSTER!")
}

var timer = setInterval(function(){
  // $(".bar").css("width",percent+"%")
  document.querySelector(".bar").style.setProperty("width",percent+"%")
  percent+=1
  if (percent>100){
    // $(".pageLoading").addClass("complete")
    document.querySelector(".pageLoading").classList.add("complete");
    setTimeout(eatCount,3000);
    clearInterval(timer);
  }
},30)