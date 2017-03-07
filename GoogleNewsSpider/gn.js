"use strict";
// var page = require('webpage').create(),
//     system = require('system'),
//     t, address;

// if (system.args.length === 1) {
//     console.log('Usage: loadspeed.js <some URL>');
//     phantom.exit(1);
// } else {
//     t = Date.now();
//     address = system.args[1];
//     page.open(address, function (status) {
//         if (status !== 'success') {
//             console.log('FAIL to load the address');
//         } else {
//             t = Date.now() - t;
//             console.log('Page title is ' + page.evaluate(function () {
//                 return document.title;
//             }));
//             console.log('Loading time ' + t + ' msec');
//             console.log(JSON.stringify(page.cookies));
//         }
//         phantom.exit();
//     });
// }

var page = require('webpage').create();
var url='https://www.google.com.hk'
var url2 = 'https://www.google.com.hk/search?q=%E6%A2%85%E8%A5%BF%E7%99%BE%E8%B4%A7&tbm=nws&start=0';
//page.settings.loadImages = false;
page.settings.userAgent = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36';
page.open(url, function() {
    page.open(url2, function() {
        console.log(page.content)
        setTimeout('print_cookies()',3000)
    });
});

function print_cookies(){
    console.log(JSON.stringify(page.cookies, undefined, 4))
    page.render('meixi.png');
    phantom.exit()
}