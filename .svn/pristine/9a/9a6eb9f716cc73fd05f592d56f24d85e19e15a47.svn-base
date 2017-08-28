
var noselect = false;


var viewer = new Cesium.Viewer("cesiumContainer", {
    timeline: false,
    animation: false,
    baseLayerPicker: false, // Only showing one layer in this demo
    //useDefaultRenderLoop : false,
    //homeButton:false,
    fullscreenButton: false,
    geocoder: true,
    selectionIndicator: false,
    infoBox: false,
    sceneModePicker: false,
    navigationHelpButton: true,
    navigationInstructionsInitiallyVisible: false
});

viewer.scene.globe.enableLighting = true;

var terrainProvider = new Cesium.CesiumTerrainProvider({
    url: '//assets02.agi.com/stk-terrain/world',
    credit: '',
    requestWaterMask: true
});
viewer.terrainProvider = terrainProvider;

viewer.scene.globe.depthTestAgainstTerrain = false;

viewer.scene.frameState.creditDisplay._imageContainer.style.display = 'none';
viewer.scene.frameState.creditDisplay._textContainer.style.display = 'none';

viewer.homeButton.viewModel.command.beforeExecute.addEventListener(function(commandInfo) {

    var startPos = {
        "duration": 1,
        "direction": {
            "x": 0.24146393146600847,
            "y": 0.9513712823598792,
            "z": 0.19127951511268426
        },
        "up": {
            "x": 0.20628135068895498,
            "y": -0.24292852105730334,
            "z": 0.9478574460406468
        },
        "destination": {
            "x": 195611.63255574455,
            "y": -4757404.871143106,
            "z": 4234217.555386543
        }
    };
    viewer.camera.flyTo({
        destination: startPos.destination,
        orientation: {
            up: startPos.up,
            direction: startPos.direction
        },
        duration: startPos.duration
    });

    commandInfo.cancel = true;
});

var dailyLayer;


var imageryLayers = viewer.scene.imageryLayers;

// var layer = imageryLayers.get(0);

// layer.brightness = 1.66;
// layer.contrast = 0.98;
// layer.hue = 0;
// layer.saturation = 0.12;
// layer.gamma = 0.64;

viewer.scene.skyBox.show = false;
viewer.scene.sun.show = false;
viewer.scene.moon.show = false;
viewer.scene.skyAtmosphere.show = false;

var startPos = {
    "duration": 1,
    "direction": {
        "x": 0.24146393146600847,
        "y": 0.9513712823598792,
        "z": 0.19127951511268426
    },
    "up": {
        "x": 0.20628135068895498,
        "y": -0.24292852105730334,
        "z": 0.9478574460406468
    },
    "destination": {
        "x": 195611.63255574455,
        "y": -4757404.871143106,
        "z": 4234217.555386543
    }
};

viewer.camera.flyTo({
    destination: startPos.destination,
    orientation: {
        up: startPos.up,
        direction: startPos.direction
    },
    duration: startPos.duration
});

var rotation = false;

function updateAndRender() {
    if (rotation == true) {
        // viewer.scene.camera.rotate(Cesium.Cartesian3.UNIT_Z, -.001);
        var center = Cesium.Cartesian3.fromDegrees(-87.628326, 41.881879, 5000.0);
        var transform = Cesium.Matrix4.fromTranslation(center);
        viewer.scene.camera.rotate(Cesium.Cartesian3.UNIT_Y, -.0001, transform);
    }
    Cesium.requestAnimationFrame(updateAndRender);
}
Cesium.requestAnimationFrame(updateAndRender);

function PointOfInterestBuilderViewModel(viewer) {
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

function PointOfInterestViewModel() {
    var self = this;
    self.pointsOfInterest = ko.observableArray([
        // new ChicagoPOI_1(),
        // new ChicagoPOI_2(),
        // new ChicagoPOI_3(),
        // new ChicagoPOI_4(),
        // new ChicagoPOI_5()
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

var poiBuilderViewModel = new PointOfInterestBuilderViewModel(viewer);
ko.applyBindings(poiBuilderViewModel, document.getElementById("cameraInfo"));

var pointOfInterestViewModel = new PointOfInterestViewModel();
ko.applyBindings(pointOfInterestViewModel, document.getElementById("filmstrip"));
ko.applyBindings(pointOfInterestViewModel, document.getElementById("info"));

var cesiumContainer = document.getElementById('cesiumContainer');
cesiumContainer.onmousedown = function() {
    pointOfInterestViewModel.cancelFlight();
};
cesiumContainer.onmousewheel = function() {
    pointOfInterestViewModel.cancelFlight();
};

var filmstripCollapsed = false;

function onCollapse() {
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

setTimeout(function() {
    onCollapse()
}, 50);

function onClickInfo() {
    var infoDiv = document.getElementById("info");
    infoDiv.className = "collapsed";
}

viewer.scene.preRender.addEventListener(function(scene, time) {
    poiBuilderViewModel.update();
});

window.document.addEventListener("keydown", function(e) {
    if (e.keyCode == 13) {
        var saveCamPos = {
            direction: viewer.camera._direction,
            up: viewer.camera._up,
            position: viewer.camera._position
        }
        console.log(JSON.stringify(saveCamPos));
    }
});

// load neighborhoods
var selectColor;
var buildColor;
var picktimeselect;
var selection = true;
var currentPicked;
var oldPicked;
var oldPrimitive;
var dataSourceNeighbor = new Cesium.GeoJsonDataSource();
viewer.dataSources.add(dataSourceNeighbor);

Cesium.InfoBoxViewModel.defaultSanitizer = undefined;

var lastSelect;
window.addEventListener("message", receiveMessage, false);
function receiveMessage(event) {
    if (event.data.msg == "hoveractive") {
        try {
            lastSelect.polygon.material.color = new Cesium.ConstantProperty(buildColor);
        }
        catch (e) {}
        hoverme = event.data.neighborhood;
        var entities = viewer.dataSources.get(0).entities.values;
        for (var i = 0; i < entities.length; i++) {
            var entity = entities[i];
            if (hoverme == entities[i]._properties.PRI_NEIGH) {
                entity.polygon.material.color = new Cesium.ConstantProperty(selectColor);
                //entity.polygon.outlineColor = new Cesium.ConstantProperty(new Cesium.Color(0, 0, 0, 0));
                lastSelect = entity;
                var activeID = activeData.neighborhoodMap[entities[i]._properties.PRI_NEIGH];
                var activeMatrix = activeData.matrix[activeID];
                //break;
            }
        }
        // create the gradient here based on activeMatrix
        var scale = chroma.scale(['black', 'white']).domain(activeMatrix, 10, 'k-means');
        // get max and min values
        // normalize values between them for opacity
        for (var i = 0; i < entities.length; i++) {
            var entity = entities[i];
            if (entity._properties.PRI_NEIGH != hoverme) {
                var trips = activeMatrix[activeData.neighborhoodMap[entity._properties.PRI_NEIGH]];
                var color = scale(trips).rgb();
                entity.polygon.material.color = new Cesium.ConstantProperty(new Cesium.Color(color[0] / 255, color[1] / 255, color[2] / 255, 0.35));
            }
        }
    }
    else if (event.data.msg == "hoveroff") {
        var entities = viewer.dataSources.get(0).entities.values;
        for (var i = 0; i < entities.length; i++) {
            var entity = entities[i];
            entity.polygon.material.color = new Cesium.ConstantProperty(buildColor);
        }
    }
}

function noselectzone() {
    noselect = true;
}

function selectzone() {
    noselect = false;
}

var helpShown = true;
document.getElementsByClassName('cesium-navigationHelpButton-wrapper')[0].addEventListener('click', function() {
    document.getElementById('HelpContainerBG').style.display = '';
    document.getElementById('HelpContainer').style.display = '';
    setTimeout(function() {
        helpShown = true;
    }, 50);

}, false);

document.addEventListener('click', function(e) {
    try {
        if (helpShown == true) {
            if (e.toElement.id != 'HelpContainer' && e.toElement.id != 'helpcontent' && e.toElement.className != 'helpc') {
                document.getElementById('HelpContainerBG').style.display = 'none';
                document.getElementById('HelpContainer').style.display = 'none';
                helpShown = false;
            }
        }
    }
    catch (e1) {
        try {
            if (helpShown == true) {
                if (e.target.id != 'HelpContainer' && e.target.id != 'helpcontent') {
                    document.getElementById('HelpContainerBG').style.display = 'none';
                    document.getElementById('HelpContainer').style.display = 'none';
                    helpShown = false;
                }
            }
        }
        catch (e2) {
            console.log(e2)
        }
    }
}, false);


