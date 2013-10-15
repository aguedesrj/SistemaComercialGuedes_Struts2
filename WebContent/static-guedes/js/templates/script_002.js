/*
* File Name: Script Js
* Author Name: arrowthemes
* Author URL: http://themeforest.net/user/arrowthemes/portfolio/?ref=arrowthemes
* Version: 1.0 */

(function($){

	$(document).on('ready', function() {

		$('.vmenu li:not(:first-child),.vmenu li li li:first-child,ul.vmenu>li>ul').each(function () {
		 $(this).append('<div class="vmenu-separator"> </div><div class="vmenu-separator-bg"> </div>'); 
		});


		//open on click
		function setOpenSubmenuWithNoReload(vMenuInfo) {
		    $("ul." + vMenuInfo.vMenuClass + " li").each(function () {
		        var item = $(this);
		        item.children("a").bind("click", function(e) {
		            var link = $(this);
		            var simple = link.siblings("ul").length === 0;
		            link.parent().siblings().children("ul." + vMenuInfo.activeClass).slideUp(function() {
		                $(this).find("li, a, ul").removeClass(vMenuInfo.activeClass);
		                $(this).removeClass(vMenuInfo.activeClass).siblings("a").removeClass(vMenuInfo.activeClass);
		                $(this).css("display", "");
		            });
		            link.parent().siblings().children("a." + vMenuInfo.activeClass).removeClass(vMenuInfo.activeClass);
		            link.parent().siblings().removeClass(vMenuInfo.activeClass);
		            if (simple && !link.hasClass(vMenuInfo.activeClass)) {
		                link.addClass(vMenuInfo.activeClass).parent().addClass(vMenuInfo.activeClass);
		            }
		            if (!simple) {
		                if (link.hasClass(vMenuInfo.activeClass)) {
		                    link.siblings("ul").slideUp("slow", "easeOutExpo", function() {
		                        $(this).removeClass(vMenuInfo.activeClass).siblings("a").removeClass(vMenuInfo.activeClass).parent().removeClass(vMenuInfo.activeClass);
		                        $(this).css("display", "");
		                    });
		                } else {
		                    link.siblings("ul").slideDown("slow" ,"easeOutExpo", function() {
		                        $(this).addClass(vMenuInfo.activeClass).siblings("a").addClass(vMenuInfo.activeClass).parent().addClass(vMenuInfo.activeClass);
		                        $(this).css("display", "");
		                    });
		                }
		                
		                e.preventDefault();
		                return false;
		            }
		        });
		    });
		}

		function showVMenuSubmenu(link, activeClass, hoveredClass, simple) {
		    if (simple) {
		        if (!link.hasClass(activeClass)) link.addClass(activeClass);
		        return;
		    }
		    if (!link.hasClass(activeClass)) {
		        link.siblings("ul").slideDown("slow", function() {
		            $(this).siblings("a").removeClass(hoveredClass);
		            $(this).addClass(activeClass).siblings("a").addClass(activeClass);
		            $(this).css("display", "");
		        });
		    }
		}

		function hideVMenuSubmenus(link, activeClass) {
		    link.parent().siblings().children("ul." + activeClass).slideUp(function() {
		        $(this).find("a, ul").removeClass(activeClass);
		        $(this).removeClass(activeClass).siblings("a").removeClass(activeClass);
		        $(this).css("display", "");
		    });
		    link.parent().siblings().children("a." + activeClass).removeClass(activeClass);
		}


		setOpenSubmenuWithNoReload({vMenuClass: "vmenu", activeClass: "active", hoveredClass: "hovered"});

		/* Menu Items */
		 $(".vmenu>li>a").append("<span class=\"border-top\"></span><span class=\"border-bottom\"></span>"); 

		    $.each($(".vmenu"), function () {
		        var width = $(this).innerWidth();
		        $.each($(this).children("li"), function() {
		            var a = $(this).children("a");
		            var pl = a.css("padding-left").replace("px", "");
		            var pr = a.css("padding-right").replace("px", "");
		            var bl = a.css("border-left-width").replace("px", "");
		            var br = a.css("border-right-width").replace("px", "");
		            // a.css("width", width - pl - pr - bl - br);
		        });
		    });
		    $(".vmenu>li>a.active").parent().addClass("active");


		/* Sub menu item */
			$(".vmenu li li a").append("<span class=\"border-top\"></span><span class=\"border-bottom\"></span>"); 
			
		    $.each($(".vmenu"), function () {
		        var width = $(this).innerWidth();
		        $.each($(this).find("li li"), function() {
		            var a = $(this).children("a");
		            var pl = a.css("padding-left").replace("px", "");
		            var pr = a.css("padding-right").replace("px", "");
		            var bl = a.css("border-left-width").replace("px", "");
		            var br = a.css("border-right-width").replace("px", "");
		            // a.css("width", width - pl - pr - bl - br);
		        });
		    });
		    $(".vmenu li li a.active").parent().addClass("active");

		 //image preloader
    	// JavaScript Document
		$.fn.preloader = function(options){
			
			var defaults = {
				             delay:200,
							 preload_parent:"a",
							 check_timer:300,
							 ondone:function(){ },
							 oneachload:function(image){  },
							 fadein:500 
							};
			
			// variables declaration and precaching images and parent container
			 var options = $.extend(defaults, options),
			 root = $(this) , images = root.find("img.pic3d, a.h-effect img").css({"visibility":"hidden",opacity:0}) ,  timer ,  counter = 0, i=0 , checkFlag = [] , delaySum = options.delay ,
			 
			 init = function(){
				
				timer = setInterval(function(){
					
					if(counter>=checkFlag.length)
					{
					clearInterval(timer);
					options.ondone();
					return;
					}
				
					for(i=0;i<images.length;i++)
					{
						if(images[i].complete==true)
						{
							if(checkFlag[i]==false)
							{
								checkFlag[i] = true;
								options.oneachload(images[i]);
								counter++;
								
								delaySum = delaySum + options.delay;
							}
							
							$(images[i]).css("visibility","visible").delay(delaySum).animate({opacity:1},options.fadein,
							function(){ $(this).parent().removeClass("preloader");   });						 
						}
					}
				
					},options.check_timer) 
				 
				 } ;
			
			images.each(function(){
				
				if($(this).parent(options.preload_parent).length==0)
				$(this).wrap("<a class='preloader' />");
				else
				$(this).parent().addClass("preloader");
				
				checkFlag[i++] = false;
				
				
				}); 
			images = $.makeArray(images); 
			
			
			var icon = jQuery("<img />",{
				
				id : 'loadingicon' ,
				src : 'templates/fontaine_j25/images/preloader.gif'
				
				}).hide().appendTo("body");
			
			
			
			timer = setInterval(function(){
				
				if(icon[0].complete==true)
				{
					clearInterval(timer);
					init();
					 icon.remove();
					return;
				}
				
				},100);
			
			}

		$("body").preloader();


		//social links hover effect 
		$('.top-links a').hover(function() {
			$(this).stop(true,true).animate({opacity: 0.5});
			},function() {
			$(this).stop(true,true).animate({opacity: 1});
		});

		//back to top
		if ($("#totop-scroller").length) {
			$().UItoTop({scrollSpeed: 500, easingType: 'easeOutExpo' });
		}

		//li span
		$('#maininner ul.style li').each(function(i, elem){
		    var html = $(elem).html();
		    var final = '<span>' + html + '</span>';
		    $(this).html(final );
		});

		// remove empty p tags
		$('p').each(function() {
		    var $this = $(this);
		    if($this.html().replace(/\s|&nbsp;/g, '').length === 0)
		        $this.remove();
		});

		$('img').addClass('size-auto');
		$('.mejs-poster img, .slide-image, img.no-size-auto, img.kstats-bar, img.pic-border.caption').removeClass('size-auto');
		
		// apply reveal sizes
		$('.module.size-medium').each(function() {
		    $(this).closest('.reveal-modal').addClass('medium');
		});			
		$('.module.size-small').each(function() {
		    $(this).closest('.reveal-modal').addClass('small');
		});	
		$('.module.size-large').each(function() {
		    $(this).closest('.reveal-modal').addClass('large');
		});	
		$('.module.size-xlarge').each(function() {
		    $(this).closest('.reveal-modal').addClass('xlarge');
		});	

		// style the zoo blog buttons
		$('p.links').each(function() {
		    var $this = $(this);
		    $this.children().eq(0).addClass("button-color");
		    $this.children().eq(1).addClass("button");
		});		

		// style the zoo teaser image
		$('.element-image img').each(function() {
		    var $this = $(this);
		    $this.addClass("pic3d");
		});


		// zoo blog tags
		$('.element-itemtag a, .zoo-tagcloud a').each(function(i, elem) {
		    var $this = $(this);
		    var html = $(elem).html();
		    var final = '<span class="tag">' + html + '</span>';
		    $(this).html(final );
		    $this.addClass("tag-body yellow");	   
		});


		//block-number
		$('.block-number').each(function(i, elem){
		    var html = $(elem).html();
		    var final = '<span class="digit">' + html + '</span><span class="bottom"></span>';
		    $(this).html(final );
		});

		//event date/time block
		$('.event-time').each(function(i, elem){
		    var html = $(elem).html();
		    var dt =  html.split(":");
		    var final = '<span class="date">' + dt[0] + '</span><span class="month">' + dt[1] + '</span>';
		    $(this).html(final );
		    $(this).parent().addClass('event');
		});

		// badges
		$('.badge.badge-new').parent().addClass('badge-new');
		$('.badge.badge-hot').parent().addClass('badge-hot');
		$('.badge.badge-free').parent().addClass('badge-free');
		$('.badge.badge-top').parent().addClass('badge-top');

		// toggles
		if ($('.showhide li').length > 0) {
			var showhide = $('.showhide li');
				showhide.each(function () {
				var q = $(this);

				if (q.children('section').css('display') === 'block') {
				q.children('h4').addClass('collapse');
				} else if (q.children('section').css('display') === 'none') {
				q.children('h4').addClass('expanded');
				}

				q.children('h4').click(function () {

				q.children('section').slideToggle('normal', function () {
				if (q.children('section').css('display') === 'block') {
				q.children('h4').addClass('collapse');
				q.children('h4').removeClass('expanded');

				} else if (q.children('section').css('display') === 'none') {
				q.children('h4').addClass('expanded');
				q.children('h4').removeClass('collapse');
				}

				});
			});
			});
		}


		//tooltips
		$(".top-links a").tooltip({ effect: 'slide', opacity: 1, position: 'bottom center', offset: [15, 0] });
		$("a#toTop").tooltip({ effect: 'slide', opacity: 1, position: 'top center', offset: [0, 0] });
		$("abbr").tooltip({ effect: 'slide', opacity: 1, position: 'top center', offset: [0, 0] });
	});


})(jQuery);


// css helper
(function($) {
    var data = [
        {str:navigator.userAgent,sub:'Chrome',ver:'Chrome',name:'chrome'},
        {str:navigator.vendor,sub:'Apple',ver:'Version',name:'safari'},
        {prop:window.opera,ver:'Opera',name:'opera'},
        {str:navigator.userAgent,sub:'Firefox',ver:'Firefox',name:'firefox'},
        {str:navigator.userAgent,sub:'MSIE',ver:'MSIE',name:'ie'}];
    for (var n=0;n<data.length;n++)	{
        if ((data[n].str && (data[n].str.indexOf(data[n].sub) != -1)) || data[n].prop) {
            var v = function(s){var i=s.indexOf(data[n].ver);return (i!=-1)?parseInt(s.substring(i+data[n].ver.length+1)):'';};
            $('html').addClass(data[n].name+' '+data[n].name+v(navigator.userAgent) || v(navigator.appVersion)); break;			
        }
    }
})(jQuery);

/*!
 * jQuery Tools v1.2.6 - The missing UI library for the Web
 * 
 * tooltip/tooltip.js
 * tooltip/tooltip.slide.js
 * 
 * NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.
 * 
 * http://flowplayer.org/tools/
 * 
 */
(function(a){a.tools=a.tools||{version:"v1.2.6"},a.tools.tooltip={conf:{effect:"toggle",fadeOutSpeed:"fast",predelay:0,delay:30,opacity:1,tip:0,fadeIE:!1,position:["top","center"],offset:[0,0],relative:!1,cancelDefault:!0,events:{def:"mouseenter,mouseleave",input:"focus,blur",widget:"focus mouseenter,blur mouseleave",tooltip:"mouseenter,mouseleave"},layout:"<div/>",tipClass:"tooltip"},addEffect:function(a,c,d){b[a]=[c,d]}};var b={toggle:[function(a){var b=this.getConf(),c=this.getTip(),d=b.opacity;d<1&&c.css({opacity:d}),c.show(),a.call()},function(a){this.getTip().hide(),a.call()}],fade:[function(b){var c=this.getConf();!a.browser.msie||c.fadeIE?this.getTip().fadeTo(c.fadeInSpeed,c.opacity,b):(this.getTip().show(),b())},function(b){var c=this.getConf();!a.browser.msie||c.fadeIE?this.getTip().fadeOut(c.fadeOutSpeed,b):(this.getTip().hide(),b())}]};function c(b,c,d){var e=d.relative?b.position().top:b.offset().top,f=d.relative?b.position().left:b.offset().left,g=d.position[0];e-=c.outerHeight()-d.offset[0],f+=b.outerWidth()+d.offset[1],/iPad/i.test(navigator.userAgent)&&(e-=a(window).scrollTop());var h=c.outerHeight()+b.outerHeight();g=="center"&&(e+=h/2),g=="bottom"&&(e+=h),g=d.position[1];var i=c.outerWidth()+b.outerWidth();g=="center"&&(f-=i/2),g=="left"&&(f-=i);return{top:e,left:f}}function d(d,e){var f=this,g=d.add(f),h,i=0,j=0,k=d.attr("title"),l=d.attr("data-tooltip"),m=b[e.effect],n,o=d.is(":input"),p=o&&d.is(":checkbox, :radio, select, :button, :submit"),q=d.attr("type"),r=e.events[q]||e.events[o?p?"widget":"input":"def"];if(!m)throw"Nonexistent effect \""+e.effect+"\"";r=r.split(/,\s*/);if(r.length!=2)throw"Tooltip: bad events configuration for "+q;d.bind(r[0],function(a){clearTimeout(i),e.predelay?j=setTimeout(function(){f.show(a)},e.predelay):f.show(a)}).bind(r[1],function(a){clearTimeout(j),e.delay?i=setTimeout(function(){f.hide(a)},e.delay):f.hide(a)}),k&&e.cancelDefault&&(d.removeAttr("title"),d.data("title",k)),a.extend(f,{show:function(b){if(!h){l?h=a(l):e.tip?h=a(e.tip).eq(0):k?h=a(e.layout).addClass(e.tipClass).appendTo(document.body).hide().append(k):(h=d.next(),h.length||(h=d.parent().next()));if(!h.length)throw"Cannot find tooltip for "+d}if(f.isShown())return f;h.stop(!0,!0);var o=c(d,h,e);e.tip&&h.html(d.data("title")),b=a.Event(),b.type="onBeforeShow",g.trigger(b,[o]);if(b.isDefaultPrevented())return f;o=c(d,h,e),h.css({position:"absolute",top:o.top,left:o.left}),n=!0,m[0].call(f,function(){b.type="onShow",n="full",g.trigger(b)});var p=e.events.tooltip.split(/,\s*/);h.data("__set")||(h.unbind(p[0]).bind(p[0],function(){clearTimeout(i),clearTimeout(j)}),p[1]&&!d.is("input:not(:checkbox, :radio), textarea")&&h.unbind(p[1]).bind(p[1],function(a){a.relatedTarget!=d[0]&&d.trigger(r[1].split(" ")[0])}),e.tip||h.data("__set",!0));return f},hide:function(c){if(!h||!f.isShown())return f;c=a.Event(),c.type="onBeforeHide",g.trigger(c);if(!c.isDefaultPrevented()){n=!1,b[e.effect][1].call(f,function(){c.type="onHide",g.trigger(c)});return f}},isShown:function(a){return a?n=="full":n},getConf:function(){return e},getTip:function(){return h},getTrigger:function(){return d}}),a.each("onHide,onBeforeShow,onShow,onBeforeHide".split(","),function(b,c){a.isFunction(e[c])&&a(f).bind(c,e[c]),f[c]=function(b){b&&a(f).bind(c,b);return f}})}a.fn.tooltip=function(b){var c=this.data("tooltip");if(c)return c;b=a.extend(!0,{},a.tools.tooltip.conf,b),typeof b.position=="string"&&(b.position=b.position.split(/,?\s/)),this.each(function(){c=new d(a(this),b),a(this).data("tooltip",c)});return b.api?c:this}})(jQuery);
(function(a){var b=a.tools.tooltip;a.extend(b.conf,{direction:"up",bounce:!1,slideOffset:10,slideInSpeed:200,slideOutSpeed:200,slideFade:!a.browser.msie});var c={up:["-","top"],down:["+","top"],left:["-","left"],right:["+","left"]};b.addEffect("slide",function(a){var b=this.getConf(),d=this.getTip(),e=b.slideFade?{opacity:b.opacity}:{},f=c[b.direction]||c.up;e[f[1]]=f[0]+"="+b.slideOffset,b.slideFade&&d.css({opacity:0}),d.show().animate(e,b.slideInSpeed,a)},function(b){var d=this.getConf(),e=d.slideOffset,f=d.slideFade?{opacity:0}:{},g=c[d.direction]||c.up,h=""+g[0];d.bounce&&(h=h=="+"?"-":"+"),f[g[1]]=h+"="+e,this.getTip().animate(f,d.slideOutSpeed,function(){a(this).hide(),b.call()})})})(jQuery);


/*UITop jquery*/
(function(a){a.fn.UItoTop=function(b){var c={text:"To Top",min:200,inDelay:600,outDelay:400,containerID:"toTop",containerHoverID:"toTopHover",scrollSpeed:1e3,easingType:"linear"};var d=a.extend(c,b);var e="#"+d.containerID;var f="#"+d.containerHoverID;a("body").append('<a href="#" title="" id="'+d.containerID+'">'+d.text+"</a>");a(e).hide().click(function(){a("html, body").animate({scrollTop:0},d.scrollSpeed,d.easingType);a("#"+d.containerHoverID,this).stop().animate({opacity:0},d.inDelay,d.easingType);return false}).prepend('<span id="'+d.containerHoverID+'"></span>').hover(function(){a(f,this).stop().animate({opacity:1},600,"linear")},function(){a(f,this).stop().animate({opacity:0},700,"linear")});a(window).scroll(function(){var b=a(window).scrollTop();if(typeof document.body.style.maxHeight==="undefined"){a(e).css({position:"absolute",top:a(window).scrollTop()+a(window).height()-50})}if(b>d.min)a(e).fadeIn(d.inDelay);else a(e).fadeOut(d.Outdelay)})}})(jQuery);

/*
 * jQuery Reveal Plugin 1.0
 * www.ZURB.com
 * Copyright 2010, ZURB
 * Free to use under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
*/
/*globals jQuery */

(function ($) {
  $('a[data-reveal-id]').live('click', function (event) {
    event.preventDefault();
    var modalLocation = $(this).attr('data-reveal-id');
    $('#' + modalLocation).reveal($(this).data());
  });

  $.fn.reveal = function (options) {
    var defaults = {
      animation: 'fadeAndPop',                // fade, fadeAndPop, none
      animationSpeed: 300,                    // how fast animtions are
      closeOnBackgroundClick: true,           // if you click background will modal close?
      dismissModalClass: 'close-reveal-modal', // the class of a button or element that will close an open modal
      open: $.noop,
      opened: $.noop,
      close: $.noop,
      closed: $.noop
    };
    options = $.extend({}, defaults, options);

    return this.each(function () {
      var modal    = $(this),
        topMeasure = parseInt(modal.css('top'), 10),
        topOffset  = modal.height() + topMeasure,
        locked     = false,
        modalBg    = $('.reveal-modal-bg'),
        closeButton;

      if (modalBg.length === 0) {
        modalBg = $('<div class="reveal-modal-bg" />').insertAfter(modal);
        modalBg.fadeTo('fast', 0.8);
      }
      
      function unlockModal() {
        locked = false;
      }

      function lockModal() {
        locked = true;
      }

      function openAnimation() {
        if (!locked) {
          lockModal();
          if (options.animation === "fadeAndPop") {
            modal.css({'top': $(document).scrollTop() - topOffset, 'opacity': 0, 'visibility': 'visible'});
            modalBg.fadeIn(options.animationSpeed / 2);
            modal.delay(options.animationSpeed / 2).animate({
              "top": $(document).scrollTop() + topMeasure + 'px',
              "opacity": 1
            }, options.animationSpeed, function () {
              modal.trigger('reveal:opened');
            });

          }
          if (options.animation === "fade") {
            modal.css({'opacity': 0, 'visibility': 'visible', 'top': $(document).scrollTop() + topMeasure});
            modalBg.fadeIn(options.animationSpeed / 2);
            modal.delay(options.animationSpeed / 2).animate({
              "opacity": 1
            }, options.animationSpeed, function () {
              modal.trigger('reveal:opened');
            });

          }
          if (options.animation === "none") {
            modal.css({'visibility': 'visible', 'top': $(document).scrollTop() + topMeasure});
            modalBg.css({"display": "block"});
            modal.trigger('reveal:opened');
          }
        }
      }
      modal.bind('reveal:open.reveal', openAnimation);

      function closeAnimation() {
        if (!locked) {
          lockModal();
          if (options.animation === "fadeAndPop") {
            modal.animate({
              "top":  $(document).scrollTop() - topOffset + 'px',
              "opacity": 0
            }, options.animationSpeed / 2, function () {
              modal.css({'top': topMeasure, 'opacity': 1, 'visibility': 'hidden'});
            });
            modalBg.delay(options.animationSpeed).fadeOut(options.animationSpeed, function () {
              modal.trigger('reveal:closed');
            });
          }
          if (options.animation === "fade") {
            modal.animate({
              "opacity" : 0
            }, options.animationSpeed, function () {
              modal.css({'opacity': 1, 'visibility': 'hidden', 'top': topMeasure});
            });
            modalBg.delay(options.animationSpeed).fadeOut(options.animationSpeed, function () {
              modal.trigger('reveal:closed');
            });
          }
          if (options.animation === "none") {
            modal.css({'visibility': 'hidden', 'top': topMeasure});
            modalBg.css({'display': 'none'});
            modal.trigger('reveal:closed');
          }
        }
      }

      function destroy() {
        modal.unbind('.reveal');
        modalBg.unbind('.reveal');
        $('.' + options.dismissModalClass).unbind('.reveal');
        $('body').unbind('.reveal');
      }

      modal.bind('reveal:close.reveal', closeAnimation);
      modal.bind('reveal:opened.reveal reveal:closed.reveal', unlockModal);
      modal.bind('reveal:closed.reveal', destroy);
      
      modal.bind('reveal:open.reveal', options.open);
      modal.bind('reveal:opened.reveal', options.opened);
      modal.bind('reveal:close.reveal', options.close);
      modal.bind('reveal:closed.reveal', options.closed);
      
      modal.trigger('reveal:open');

      closeButton = $('.' + options.dismissModalClass).bind('click.reveal', function () {
        modal.trigger('reveal:close');
      });

      if (options.closeOnBackgroundClick) {
        modalBg.css({"cursor": "pointer"});
        modalBg.bind('click.reveal', function () {
          modal.trigger('reveal:close');
        });
      }

      $('body').bind('keyup.reveal', function (event) {
        if (event.which === 27) { // 27 is the keycode for the Escape key
          modal.trigger('reveal:close');
        }
      });
    });
  };
} (jQuery));