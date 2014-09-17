$(function () {
    //The following 3 lines are needed to clear up 1st and last spot in array - convert to function later
    if (menuOptionPics !== null) {
        var menuLen = menuOptionPics.length - 1;
        menuOptionPics[0] = menuOptionPics[0].substring(1);
        menuOptionPics[menuLen] = menuOptionPics[menuLen].substring(1, (menuOptionPics[menuLen].length - 1));
    }

    //Holds the spots of the items displayed in the slider
    var menuOptionSpot = [0, 1, 2];

//HANDLES SLIDER---------------------------------------------------
    function switchMenuPicture() {
        $('#pictureLeft').attr('src', menuOptionPics[menuOptionSpot[0]]);
        $('#pictureCenter').attr('src', menuOptionPics[menuOptionSpot[1]]);
        $('#pictureRight').attr('src', menuOptionPics[menuOptionSpot[2]]);
    }
    switchMenuPicture();

    function centerImageFade() {
        $('#pictureCenter').animate({
            opacity: "0"
        }, 300).animate({
            opacity: "1"
        }, 300);
    }

    function switchMenuOptions(right) {
        if (!right) {
            if (menuOptionSpot[2] !== menuOptionPics.length - 1) {
                for (var i = 0; i < menuOptionSpot.length; i++) {
                    menuOptionSpot[i]++;
                }
                $('#pictureRight').animate({right: "11%"}, 100).animate({right: "-10%"}, 100, function () {
                    switchMenuPicture();
                });
                centerImageFade();
            }
        } else {
            if (menuOptionSpot[0] !== 0) {
                for (var i = 0; i < menuOptionPics.length; i++) {
                    menuOptionSpot[i]--;
                }
                $('#pictureLeft').animate({left: "11%"}, 100).animate({left: "-10%"}, 100, function () {
                    switchMenuPicture();
                });
                centerImageFade();
            }
        }
    }

    function switchDetails() {
        $('#detailsTitle').text(menuItemName[menuOptionSpot[1]]);
        $('#detailsDescription').text(menuItemDescription[menuOptionSpot[1]]);
    }

//HANDLES SLIDER Controls---------------------------------------------------
    $('#leftControl').click(function () {
        switchMenuOptions(true);
        switchDetails();
    });
    $('#rightControl').click(function () {
        switchMenuOptions(false);
        switchDetails();
    });

//HANDLES ADD TO ORDER MENU---------------------------------------------------
    var itemsOrdered = [];
    var orderAmount = [1, 1, 1, 1, 1];
    $('#addToListButton').click(function () {
        var item = menuItemName[menuOptionSpot[1]];
        var repeatItem = false;
        for (var i = 0; i < itemsOrdered.length; i++) {
            if (itemsOrdered[i] === item) {
                orderAmount[i]++;
                repeatItem = true;
            }
        }
        if (!repeatItem) {
            itemsOrdered.push(item);
        }
        $('#orderMenuForm').html("");
        var displayText = "";
        for (var t = 0; t < itemsOrdered.length; t++) {
            displayText = displayText + "<br><div class='removeItem'></div><div class='itemNameInUserOrder'>" + itemsOrdered[t] +
                    "</div><input type='text' maxlength='2' class='qntyInput' \n\
                    name='" + itemsOrdered[t] + "' \n\
                    id='" + itemsOrdered[t] + "'\n\
                    value='" + orderAmount[t] + "'/>";

        }
        $('#orderMenuForm').append(displayText);
        $('#orderMenuForm').append("<input type='submit' id='submitMenu' value='Order'/>");
    });

    //listens for remove item click
    $('#orderMenuForm').on('click', '.removeItem', function () {
        alert("TODO - remove item from list");
    });
});

