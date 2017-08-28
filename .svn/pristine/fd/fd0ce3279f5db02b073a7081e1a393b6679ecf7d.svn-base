if (typeof(Main) === 'undefined') {
		Main = {};
}
(function($) {

		function SubNav($element) {

				var self = this;

				this.$el = $element;

				this.ThreeOneOneMenuItems = [{
								label: "Street Lights",
								color: "#FF7F3C"
						}, {
								label: "Graffiti Cleanup",
								color: "#DE00C5"
						}, {
								label: "Pot Holes",
								color: "#2BC3A8"
						}, {
								label: "Abandoned Cars",
								color: "#EDD83D"
						}, {
								label: "Tree Removal",
								color: "#3C5FEC"
						}, {
								label: "Rodent Complaint",
								color: "#FF003D"
						}

				];

				this.DivvyMenuItems = [
						{
								label: "基础地形",
								color: "#3C5FEC",
								state: true
						}, {
								label: "卫星遥感",
								color: "#DE00C5",
								state: true
						}, {
								label: "电子地图",
								color: "#FBB040",
								state: false
						},
						 {
								label: "标注",
								color: "red",
								state: true
						}
				];
				this.subnavInner = $('<div class="subnav-inner" onmouseout="selectzone()" onmouseover="noselectzone()" />');
				this.rightContainer = $('<div class="subnav-right-container" />');
				this.leftContainer = $('<div class="subnav-left-container" />');

				this.toggleList = $('<ul class="subnav-toggle-list" />');

				this.animationToggle = new Main.ToggleBtn("animation", false);

				$(this.animationToggle).on('toggleAnimation', function(e, data) {
						$(self).trigger('toggleAnimation', data);
				});

				this.rightContainer.append(this.animationToggle.$el);

				//this.animationToggle.Toggle();

				this.flowToggle = new Main.ToggleBtn("flow", false);

				$(this.flowToggle).on('toggleFlow', function(e, data) {
						if (data.state == "on") {
								selection = true;
								setTimeout(function() {
										selection = true;
								}, 50)
								if($("#collapse").is(".collapsed")){
										 $("#infobox").css("transform","translate(0px,410px)");
								}else{
										$("#infobox").css("transform","translate(0px,0px)");
								 }
						}
						else {
								var entities = viewer.dataSources.get(0).entities.values;
								for (var i = 0; i < entities.length; i++) {
										var entity = entities[i];
										entity.polygon.material.color = new Cesium.ConstantProperty(buildColor);
								}
								selection = false;
								if($("#collapse").is(".collapsed")){
										$("#infobox").css("transform","translate(410px,410px)");
								}else{
										$("#infobox").css("transform","translate(410px,0px)");
								}
						}
						$(self).trigger('toggleFlow', data);
				});

				this.flowToggle.Toggle();

				this.rightContainer.append(this.flowToggle.$el);

				this.subnavInner.append(this.leftContainer, this.rightContainer);

				this.$el.append(this.subnavInner);

				return this;
		}


		SubNav.prototype.Set = function(type) {

				var menuItems;
				var self = this;
				this.toggleList.empty();

				switch (type) {
						case "twitter":
								return;
								break;
						case "divvy":
								menuItems = this.DivvyMenuItems;
								break;
						case "311":
								menuItems = this.ThreeOneOneMenuItems;
								break;
				}



				$.each(menuItems, function(index, menuItem) {
						var legendItem = $('<div class="subnav-legend" />').css('backgroundColor', menuItem.color);
						var label = $('<div class="subnav-label" />').text(menuItem.label);
						var listItem = $('<li class="subnav-item" />');

						listItem.append(legendItem, label);

						new Button(listItem, {
								mousedown: function() {
										TweenLite.to(listItem, .35, {
												scaleX: .98,
												scaleY: .98,
												ease: "easeOutQuint"
										});
								},
								mouseup: function() {
										TweenLite.to(listItem, .35, {
												scaleX: 1,
												scaleY: 1,
												ease: "easeOutQuint"
										});
								},
								click: function() {
										self.ToggleView(listItem, menuItem);
								}
						});

						self.toggleList.append(listItem);

						if (menuItem.state == true) {

								listItem.addClass("active");
						}

				});


				this.leftContainer.append(this.toggleList);
		};

		SubNav.prototype.AnimateIn = function() {

				var tl = new TimelineLite();

				TweenLite.set($('.subnav-item'), {
						y: 10,
						alpha: 0
				})

				tl.staggerTo($('.subnav-item'), .35, {
								y: 0,
								alpha: 1,
								delay: .15,
								ease: 'easeOutQuint'
						},
						0.05);
		}

		SubNav.prototype.ToggleView = function(element, data) {
				var onOff = element.hasClass('active');
				var bg = onOff ? 'transparent' : '#f4f4f4';
				var color = onOff ? "#f4f4f4" : '#0A1523';
		
				TweenLite.to(element, .35, {
						backgroundColor: bg,
						color: color
				});
		
				$(this).trigger('toggleView', {
						view: data.label,
						state: !onOff
				});
				element.toggleClass('active');
		switch(data.label)
		{
			case "基础地形":
				if(onOff)
				{
					viewer.terrainProvider=new Cesium.EllipsoidTerrainProvider();
				}
				else
				{
					viewer.terrainProvider = terrainProvider; 
				}
			break;
			case "卫星遥感":
				if(onOff)
				{
					for(var i=0;i<viewer.imageryLayers._layers.length;i++)
					{
						if(viewer.imageryLayers._layers[i]._imageryProvider ==viewerImageLayers[0])
						{
							viewer.imageryLayers.remove(viewer.imageryLayers._layers[i]);
						}
					}
					
				}
				else
				{
					viewer.imageryLayers.addImageryProvider(viewerImageLayers[0]);
				}
			break;
			case "电子地图":
				if(onOff)
				{
					for(var i=0;i<viewer.imageryLayers._layers.length;i++)
					{
						if(viewer.imageryLayers._layers[i]._imageryProvider ==viewerImageLayers[1])
						{
							viewer.imageryLayers.remove(viewer.imageryLayers._layers[i]);
						}
					}
					
				}
				else
				{
					viewer.imageryLayers.addImageryProvider(viewerImageLayers[1]);
				}
			break;
			case "标注":
				if(onOff)
				{
					

				}
				else
				{
					
				}
			break;
		}
		
		
		
		};

		SubNav.prototype.Reset = function() {
				console.log('here reset');
				//$('.subnav-item').removeClass('active');
				TweenLite.to($('.subnav-item'), .35, {
						backgroundColor: 'transparent',
						color: "#f4f4f4"
				});

		};

		return Main.SubNav = SubNav;

})(jQuery);