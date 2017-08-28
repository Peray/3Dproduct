if (typeof(Main) === 'undefined') {
    Main = {
	};
}
(function($) {
    function ToggleBtn(type, off) {
        var self = this;
        this.isOff = true;
        this.$el = $('<div class="caf-toggle-' + type + '"  onmouseout="selectzone()" onmouseover="noselectzone()"/>');
        this.container = $('<div class="caf-toggle-container" />');
        this.btn = $('<div class="caf-toggle-btn" />');
        this.btnOff = $('<div class="caf-toggle-btn-off" />');
        this.label = $('<div class="caf-toggle-label" />'); //.html('Buildings On');        	
        this.btn.append(this.btnOff);
        this.container.append(this.label, this.btn);
        this.$el.append(this.container);
        new Button(this.$el, {
            click: function() {
                self.Toggle();
            }
        });

        switch (type) {
            case "animation":
                this.btn.addClass('animation');
                var state = this.isOff ? "off" : "on";
                this.label.html('图层信息 <div class="caf-toggle-state" >' + state + ' </div>');
				Main.s=this;
                break;
            case "flow":
                this.btn.addClass('flow');
                var state = this.isOff ? "off" : "on";
                this.label.html('鹰眼图&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<div class="caf-toggle-state" >' + state + ' </div>');
                break;
        }
		
        return this;

    }
    ToggleBtn.prototype.Toggle = function() {
        if (!this.isOff) {
            TweenLite.fromTo(this.btnOff, .25, {
                scaleX: 1.1,
                scaleY: 1.1,
                autoAlpha: 0
            }, {
                scaleX: 1,
                scaleY: 1,
                autoAlpha: 1,
                ease: "easeOutBack"
            });

            var stateLbl = this.label.find('.caf-toggle-state'); //.text('off');

            TweenLite.to(stateLbl, .25, {
                y: -10,
                alpha: 0,
                ease: "easeInQuint",

                onComplete: function() {
                    stateLbl.text('off');
                    TweenLite.set(stateLbl, {
                        y: 10
                    });
                    TweenLite.to(stateLbl, .25, {
                        y: 0,
                        alpha: 1
                    });
                }
            });

            this.isOff = true;

        }
        else {
			
            TweenLite.to(this.btnOff, .25, {
                scaleX: 1.1,
                scaleY: 1.1,
                autoAlpha: 0,
                ease: "easeInBack"
            });
            var stateLbl = this.label.find('.caf-toggle-state'); //.text('off');

            TweenLite.to(stateLbl, .25, {
                y: 10,
                alpha: 0,
                ease: "easeInQuint",

                onComplete: function() {
                    stateLbl.text('on');
                    TweenLite.set(stateLbl, {
                        y: -10
                    });
                    TweenLite.to(stateLbl, .25, {
                        y: 0,
                        alpha: 1
                    });
                }
            });


            this.isOff = false;
        }

        $(this).trigger('toggleAnimation', {
            state: this.isOff ? "off" : "on"
        });

        $(this).trigger('toggleFlow', {
            state: this.isOff ? "off" : "on"
        });
		if(this.label.text().indexOf('图层信息')>=0)
		{
			if(this.isOff)
			{
				$('#infoboxce').show();
				$('#infoboxinfo').hide();
			}
			else
			{
				$('#infoboxce').hide();
				$('#infoboxinfo').show();
			}
			Main.s=this;
		}
		
    }
	ToggleBtn.prototype.Toggles = function(o_n) {
		debugger
		if(Main.s.isOff!=o_n)
			return;
        if (!o_n) {
            TweenLite.fromTo(Main.s.btnOff, .25, {
                scaleX: 1.1,
                scaleY: 1.1,
                autoAlpha: 0
            }, {
                scaleX: 1,
                scaleY: 1,
                autoAlpha: 1,
                ease: "easeOutBack"
            });

            var stateLbl = Main.s.label.find('.caf-toggle-state'); //.text('off');

            TweenLite.to(stateLbl, .25, {
                y: -10,
                alpha: 0,
                ease: "easeInQuint",

                onComplete: function() {
                    stateLbl.text('off');
                    TweenLite.set(stateLbl, {
                        y: 10
                    });
                    TweenLite.to(stateLbl, .25, {
                        y: 0,
                        alpha: 1
                    });
                }
            });

            Main.s.isOff = true;

        }
        else {
			
            TweenLite.to(Main.s.btnOff, .25, {
                scaleX: 1.1,
                scaleY: 1.1,
                autoAlpha: 0,
                ease: "easeInBack"
            });
            var stateLbl = Main.s.label.find('.caf-toggle-state'); //.text('off');

            TweenLite.to(stateLbl, .25, {
                y: 10,
                alpha: 0,
                ease: "easeInQuint",

                onComplete: function() {
                    stateLbl.text('on');
                    TweenLite.set(stateLbl, {
                        y: -10
                    });
                    TweenLite.to(stateLbl, .25, {
                        y: 0,
                        alpha: 1
                    });
                }
            });


            Main.s.isOff = false;
        }

        $(Main.s).trigger('toggleAnimation', {
            state: Main.s.isOff ? "off" : "on"
        });

        $(Main.s).trigger('toggleFlow', {
            state: Main.s.isOff ? "off" : "on"
        });
		
    }
    return Main.ToggleBtn = ToggleBtn;

})(jQuery);