function isValidR(){
    return true;
}
function getR(){
    var R = document.getElementsByName("myForm:param-r")[0].value;
    // for(let i = 0; i < document.getElementsByName("myForm:param-r").length; i++){
    //     if(document.getElementsByName("myForm:param-r")[i].checked){
    //         return document.getElementsByName("myForm:param-r")[i].value;
    //     }
    //}
    return R;

}
document.getElementById("zoneCanvas").addEventListener("click", function (e) {
    if(isValidR()) {
        const x = (e.x - document.getElementById("zoneCanvas").getBoundingClientRect().left - 180) / (30);
        const y = (e.y - document.getElementById("zoneCanvas").getBoundingClientRect().top - 180) / (-30);
        const r = getR();
        onPointCliecked(x,y,r);
    }
});

function updateR() {
    let R = getR();
    update([{name:'dbR', value: R}]);
}