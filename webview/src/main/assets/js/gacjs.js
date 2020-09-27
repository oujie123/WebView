var gacjs = {};
gacjs.os = {};
gacjs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
gacjs.os.isAndroid = !gacjs.os.isIOS;
gacjs.callbacks = {}

gacjs.callback = function (callbackname, response) {
   var callbackobject = gacjs.callbacks[callbackname];
   console.log("xxxx"+callbackname);
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
          console.log("xxxxxx"+response);
          var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete gacjs.callbacks[callbackname];
       }
   }
}

gacjs.takeNativeAction = function(commandname, parameters){
    console.log("gacjs takenativeaction")
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

gacjs.takeNativeActionWithCallback = function(commandname, parameters, callback) {
    var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    gacjs.callbacks[callbackname] = {callback:callback};

    var request = {};
    request.name = commandname;
    request.param = parameters;
    request.param.callbackname = callbackname;
    if(window.gacjs.os.isAndroid){
        window.gacwebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.gacwebview.postMessage(JSON.stringify(request))
    }
}

window.gacjs = gacjs;
