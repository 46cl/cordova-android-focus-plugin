var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var Focus = function() {
};

Focus.focus = function(element) {
    element = element.length ? element[0] : element;
    var jelement = $(element);
    var offset = jelement.offset();
    offset.top = offset.top - $(window).scrollTop();
    offset.left = offset.left - $(window).scrollLeft();
        rect = {
            top: offset.top,
            left: offset.left,
            right: offset.left + jelement.width(),
            bottom: offset.top + jelement.height()
        };
        exec(null, null, "Focus", "focus", [rect]);
};

module.exports = Focus;
