/*
If window.onload has not already been assigned a function,
then func is simply assigned to window.onload.
//
If window.onload has already been set, a brand new function is created
which first calls the original onload handler, then calls the new
handler afterwards
*/
function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      func();
    }
  }
}
