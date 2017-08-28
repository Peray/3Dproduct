var map;
var view;
var pointXList = [];
var pointYList = [];
var Xmin,Ymin,Xmax,Ymax;
function loadBaiduMap(url) {
	var mapDiv = document.getElementById("dituContents");
	var viewDiv =  document.getElementsByClassName("ol-viewport");
	if(viewDiv.length == 0){
		var projection = ol.proj.get("EPSG:3857");	
		var resolutions = [];
		for(var i=0; i<19; i++){
			resolutions[i] = Math.pow(2, 18-i);
		}
		var tilegrid  = new ol.tilegrid.TileGrid({
			origin: [0,0],
			resolutions: resolutions
		});
		
		var baidu_source = new ol.source.TileImage({
			projection: projection,
			tileGrid: tilegrid,
			tileUrlFunction: function(tileCoord, pixelRatio, proj){
				if(!tileCoord){
					return "";
				}
				var z = tileCoord[0];
				var x = tileCoord[1];
				var y = tileCoord[2];
		
				if(x<0){
					x = "M"+(-x);
				}
				if(y<0){
					y = "M"+(-y);
				}
		
				return "http://online3.map.bdimg.com/onlinelabel/?qt=tile&x="+x+"&y="+y+"&z="+z+"&styles=pl&udt=20151021&scaler=1&p=1";
			}
		});
		var baidu_layer = new ol.layer.Tile({
			source: baidu_source
		});
		view = new ol.View({
			center: [12959773,4853101],
			zoom: 4
		});
		map = new ol.Map({
			target: mapDiv,
			layers: [baidu_layer],
			controls: ol.control.defaults({
			attributionOptions: /** @type {olx.control.AttributionOptions} */ ({
				collapsible: false
			})
			}),
			view: view
		});
		loadGeoJson(url);
	}else{
		map.removeLayer(map.getLayers().getArray()[1]);
		pointXList = [];
		pointYList = [];
		Xmin = "";
		Ymin = "";
		Xmax = "";
		Ymax = "";
		loadGeoJson(url);
	}
	
	setTimeout(function () {
		var features = source.getFeatures();
		for(var i=0;i<features.length;i++){
			getXYFromFeature(features[i]);
		}
		getMaxMinfromArray();
		view.fit([Xmin,Ymin,Xmax,Ymax],{duration: 1000});
	}, 1000);
}

function loadGeoJson(url){
	source = new ol.source.Vector({
		url: url,
		format: new ol.format.GeoJSON()
	});
	var vector = new ol.layer.Vector({
		source: source
	});
	map.addLayer(vector);
}

function getXYFromFeature(feature){
	var type = feature.getGeometry().getType();
	var geometry = feature.getGeometry();
	var coordinates = geometry.getCoordinates();
	if(type == "Point"){
		pointXList.push(coordinates[0]);
		pointYList.push(coordinates[1]);
	}
	if(type == "LineString"){
		for(var i=0;i<coordinates.length;i++){
			pointXList.push(coordinates[i][0]);
			pointYList.push(coordinates[i][1]);
		}
	}
	if(type == "Polygon"){
		for(var i=0;i<coordinates[0].length;i++){
			pointXList.push(coordinates[0][i][0]);
			pointYList.push(coordinates[0][i][1]);
		}
	}
}

function getMaxMinfromArray(){
	var length = pointXList.length;
	for(var i=0;i<length;i++){
		if(Xmin == undefined||Xmin == "")
			Xmin = pointXList[i];
		else if(pointXList[i] < Xmin)
				Xmin = pointXList[i];
		if(Ymin == undefined||Ymin == "")
			Ymin = pointYList[i];
		else if(pointYList[i] < Ymin)
				Ymin = pointYList[i];
		if(Xmax == undefined||Xmax == "")
			Xmax = pointXList[i];
		else if(pointXList[i] > Xmax)
				Xmax = pointXList[i];
		if(Ymax == undefined||Ymax == "")
			Ymax = pointYList[i];
		else if(pointYList[i] > Ymax)
				Ymax = pointYList[i];
	}
}