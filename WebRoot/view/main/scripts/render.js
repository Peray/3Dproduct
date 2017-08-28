function renderjs()
{
	this.filmstripCollapsed=false;
}
renderjs.prototype.PointOfInterestBuilderViewModel= function(viewer){
    this.camera = viewer.scene.camera;
    this.cameraInfo = ko.observable("foo");
    this.update = function() {
        var lla = viewer.scene.globe.ellipsoid.cartesianToCartographic(this.camera.positionWC);
        var upStr = "new Cesium.Cartesian3(" + this.camera.upWC.x + ", " + this.camera.upWC.y + ", " + this.camera.upWC.z + ")";
        var dirStr = "new Cesium.Cartesian3(" + this.camera.directionWC.x + ", " + this.camera.directionWC.y + ", " + this.camera.directionWC.z + ")";
        var destStr = "new Cesium.Cartographic(" + lla.longitude + ", " + lla.latitude + ", " + lla.height + ")";
        var cameraJson = {
            up: upStr,
            direction: dirStr,
            destination: destStr,
            duration: 10000
        };
        var info = JSON.stringify(cameraJson, null, "    ");
        this.cameraInfo(info);
    };
}
renderjs.prototype.PointOfInterestViewModel=function() {
    var self = this;
    self.pointsOfInterest = ko.observableArray([
        new ChicagoPOI_1(),
        new ChicagoPOI_2(),
        new ChicagoPOI_3(),
        new ChicagoPOI_4(),
        new ChicagoPOI_5()
    ]);
    self.flightPointIndex = 0;
    self.info = ko.observable("");

    self.changePointOfInterest = function(pointOfInterest) {
        self.cancelFlight();
        var infoDiv = document.getElementById("info");
        infoDiv.className = "uncollapsed";
        self.flightPointIndex = 0;
        self.selectedPointOfInterest = pointOfInterest;
        self.info(pointOfInterest.info);
        self.flyTo(pointOfInterest.flightPoints[0]);
    };

    self.onPointReached = function() {
        self.flightPointIndex += 1;
        if (self.flightPointIndex >= this.selectedPointOfInterest.flightPoints.length) {
            var infoDiv = document.getElementById("info");
            infoDiv.className = "collapsed";
            return;
        }
        self.flyTo(self.selectedPointOfInterest.flightPoints[this.flightPointIndex]);
    };

    self.cancelFlight = function() {
        //     viewer.scene.animations.removeAll();
        var infoDiv = document.getElementById("info");
        infoDiv.className = "collapsed";
    };

    self.flyTo = function(pointOfInterestFlightPoint) {
        var up;
        var direction;
        /*
         var flight = Cesium.CameraFlightPath.createTween(viewer.scene, {
                        destination : pointOfInterestFlightPoint.destination,
                        up : pointOfInterestFlightPoint.up,
                        direction : pointOfInterestFlightPoint.direction,
                        duration : pointOfInterestFlightPoint.duration/1000,
                        linearPath : true,
                        complete : function() {
                            console.log('done');
                            self.onPointReached();
                        }
                    });
                    console.log(flight);
                    viewer.scene.tweens.add(flight);
        */
        var timestep = 0.5;
        var startTime = 0;
        var endTime = 150;
        var currentTime = 0;
        var p1 = viewer.camera._position.clone();
        var p2 = pointOfInterestFlightPoint.destination;
        var ellipsoid = viewer.scene.globe.ellipsoid;

        function animateTest(currentTime, startTime, endTime, p1, p2) {
            var start = p1.clone();
            var t = (currentTime - startTime) / (endTime - startTime);
            //  console.log(t);
            viewer.camera.position = Cesium.Cartesian3.lerp(p1, p2, t, start);
            if (currentTime < endTime) {
                setTimeout(function() {
                    animateTest(currentTime + timestep, startTime, endTime, p1, p2);
                }, 0)
            }
            else {
                console.log('done');
            }
        }
        viewer.camera.flyTo({
            destination: pointOfInterestFlightPoint.destination,
            orientation: {
                up: pointOfInterestFlightPoint.up,
                direction: pointOfInterestFlightPoint.direction
            },

            duration: pointOfInterestFlightPoint.duration,
            complete: function() {
                self.onPointReached()
            }
        });
    };
}
renderjs.prototype.onCollapse=function() {
    var collapseButton = document.getElementById("collapse");
    var filmstripDiv = document.getElementById("filmstrip");
    var subNav = document.getElementById("subNav");
    var infobox = document.getElementById("infobox");
    if (filmstripCollapsed) {
        collapseButton.className = "uncollapsed";
        filmstripDiv.className = "uncollapsed";
        subNav.className = "uncollapsed";
        infobox.className = "uncollapsed";
    }
    else {
        collapseButton.className = "collapsed";
        filmstripDiv.className = "collapsed";
         subNav.className = "collapsed";
         infobox.className = "collapsed";
    }
    filmstripCollapsed = !filmstripCollapsed;
}
renderjs.prototype.onClickInfo=function() {
    var infoDiv = document.getElementById("info");
    infoDiv.className = "collapsed";
}

