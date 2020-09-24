var gacjs = {};
gacjs.os = {};
gacjs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
gacjs.os.isAndroid = !gacjs.os.isIOS;

gacjs.takeNativeAction = function(commandname, parameters){
    console.log("xiangxuejs takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.gacjs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.gacwebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.gacwebview.postMessage(JSON.stringify(request))
    }
}

window.gacjs = gacjs;
