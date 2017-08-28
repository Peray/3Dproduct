function mapMax()
{
	
}
mapMax.prototype.initMap = function(divid)
{
	debugger;
	this.viewer= new Cesium.Viewer("cesiumContainer", {
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
	this.viewer.scene.globe.enableLighting = true;//开启灯光
	//加载地形数据
	var terrainProvider = new Cesium.CesiumTerrainProvider({
    url: '//assets02.agi.com/stk-terrain/world',
    credit: '',
    requestWaterMask: true
	});
	this.viewer.terrainProvider = terrainProvider;
	this.viewer.scene.globe.depthTestAgainstTerrain = false;
	this.viewer.scene.frameState.creditDisplay._imageContainer.style.display = 'none';
	this.viewer.scene.frameState.creditDisplay._textContainer.style.display = 'none';
	this.viewer.homeButton.viewModel.command.beforeExecute.addEventListener(function(commandInfo) {
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
	this.viewer.scene.skyBox.show = false;
	this.viewer.scene.sun.show = false;
	this.viewer.scene.moon.show = false;
	this.viewer.scene.skyAtmosphere.show = false;
	//初始位置设置
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
	this.viewer.camera.flyTo({
		destination: startPos.destination,
		orientation: {
			up: startPos.up,
			direction: startPos.direction
		},
		duration: startPos.duration
	});
}
mapMax.prototype.PointOfInterestBuilderViewModel= function(viewer){
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

var pt = new mapMax();
pt.initMap();