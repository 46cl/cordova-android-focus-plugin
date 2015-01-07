var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var Focus = function() {
};

Focus.focus = function(element) {
    element = element.length ? element[0] : element;
    var elemRect = element.getBoundingClientRect(),
        bodyRect = document.body.getBoundingClientRect(),
        rect = {
          top: elemRect.top - bodyRect.top,
          left: elemRect.left - bodyRect.left,
          right: elemRect.right - bodyRect.left,
          bottom: elemRect.bottom - bodyRect.top
        };
    exec(null, null, "Focus", "focus", [rect]);
};

module.exports = Focus;
