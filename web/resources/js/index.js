function ShowTime() {
    document.getElementById("time").innerHTML=new Date().toLocaleString();

}
setInterval(() => {
    ShowTime();
}, 9999);