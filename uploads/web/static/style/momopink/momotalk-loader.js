(function() {
    if (window.__momotalkLoadingShown) return;
    window.__momotalkLoadingShown = true;

    var overlay = document.createElement('div');
    overlay.id = 'momotalk-loading-overlay';
    overlay.style.cssText = 'position:fixed;top:0;left:0;width:100%;height:100%;background:url("static/style/momopink/MomotalkLoading.png") no-repeat center center;background-size:cover;z-index:999999999;opacity:1;pointer-events:auto;';

    function addOverlay() {
        if (document.body) {
            document.body.appendChild(overlay);
        } else {
            document.documentElement.appendChild(overlay);
            var checkBody = setInterval(function() {
                if (document.body) {
                    document.body.appendChild(overlay);
                    clearInterval(checkBody);
                }
            }, 5);
        }
    }
    addOverlay();

    function fadeOutAndRemove() {
        if (!overlay.parentNode) return;
        overlay.style.transition = 'opacity 0.2s ease-out';
        overlay.style.opacity = '0';
        setTimeout(function() {
            if (overlay.parentNode) overlay.parentNode.removeChild(overlay);
        }, 250);
    }

    var startTime = Date.now();
    var resourceLoaded = false;

    function done() {
        if (resourceLoaded) return;
        resourceLoaded = true;
        var elapsed = Date.now() - startTime;
        var remaining = 1950 - elapsed;
        if (remaining > 0) {
            setTimeout(fadeOutAndRemove, remaining);
        } else {
            fadeOutAndRemove();
        }
    }

    if (document.readyState === 'complete') {
        done();
    } else {
        window.addEventListener('load', done);
    }
    setTimeout(function() {
        if (!resourceLoaded) done();
    }, 8000);
})();