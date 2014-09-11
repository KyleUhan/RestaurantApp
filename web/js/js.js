$(function () {

    var menuOptionPics = ["images/hamburgerSized.jpg", "images/tacosSized.jpg", "images/steakSized.jpg", "images/chickenSized.jpg", "images/saladSized.jpg"];
    var menuOptionSpot = [0, 1, 2];

    function switchMenuPicture() {

        $('#pictureLeft').attr('src', menuOptionPics[menuOptionSpot[0]]);
        $('#pictureCenter').attr('src', menuOptionPics[menuOptionSpot[1]]);
        $('#pictureRight').attr('src', menuOptionPics[menuOptionSpot[2]]);
    }

    function centerImageFade() {
        $('#pictureCenter').animate({
            opacity: "0"
        }, 300).animate({
            opacity: "1"
        }, 300);
    }

    function switchMenuOptions(right) {
        if (!right) {
            if (menuOptionSpot[2] !== menuOptionPics.length) {
                for (var i = 0; i < menuOptionSpot.length; i++) {
                    menuOptionSpot[i]++;
                }
                $('#pictureRight').animate({
                    right: "11%"
                }, 100).animate({
                    right: "-10%"
                }, 100, function () {
                    switchMenuPicture();
                });
                centerImageFade();
            }

        } else {
            if (menuOptionSpot[0] !== -1) {
                for (var i = 0; i < menuOptionPics.length; i++) {
                    menuOptionSpot[i]--;
                }
                $('#pictureLeft').animate({
                    left: "11%"
                }, 100).animate({
                    left: "-10%"
                }, 100, function () {
                    switchMenuPicture();
                });
                centerImageFade();
            }

        }


        /* $('#pictureLeft').css('background', menuOptions[menuOptionSpot[0]]);
         $('#pictureCenter').css('background', menuOptions[menuOptionSpot[1]]);
         $('#pictureRight').css('background', menuOptions[menuOptionSpot[2]]);*/
    }

    $('#leftControl').click(function () {
        switchMenuOptions(true);
    });
    $('#rightControl').click(function () {
        switchMenuOptions(false);
    });
});

