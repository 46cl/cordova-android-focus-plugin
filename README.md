What does it do ?
=================

The plugin offer the possibility to focus elements in the web view by emulating native touches using Android [ViewGroup#dispatchTouchEvent](http://developer.android.com/reference/android/view/ViewGroup.html#dispatchTouchEvent(android.view.MotionEvent))

Why would you need this ?
=========================

For example, if you want to focus an input field to prompt the soft keyboard. If you focus the field from JavaScript, Android won't pop the soft keyboard in the web view. Ionic's [keyboard plugin](https://github.com/driftyco/ionic-plugins-keyboard/) can help you fire the soft keyboard programmatically, but this only work for the default, alphanumeric keyboard. If you need to pop an other keyboard, like a numbers only keyboard, this plugin can come handy.

Installation
============

    cordova plugin add https://github.com/46cl/cordova-android-focus-plugin

Usage
=====

    cordova.plugins.Focus.focus(element);

Element can be an element or array of elements (in which case the first item in the array will be focused).

An other example with jQuery and an input type number in the DOM could be :

    cordova.plugins.Focus.focus($("input[type='number']"));
