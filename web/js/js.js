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
        }, 200).animate({
            opacity: "1"
        }, 200);
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
        $('#detailsPrice').text("$" + menuItemPrice[menuOptionSpot[1]]);
    }

//HANDLES SLIDER CONTROLS---------------------------------------------------
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
        var totalItemAmount;

        for (var t = 0; t < itemsOrdered.length; t++) {
            totalItemAmount = (parseFloat(menuItemPrice[t + 1]) * orderAmount[t]).toFixed(2);
            displayText = displayText + "<br>" +
                    "<div class='orderItemWrapper'>" +
                    "<div class='removeItem'></div>" +
                    "<input type='text' readonly='true' class='itemNameInUserOrder'" +
                    " value='" + itemsOrdered[t] +
                    "' name='itemName" + t +
                    "'/>" +
                    "<div class='menuPrice'>" +
                    menuItemPrice[t + 1] +
                    "</div>" +
                    "<input type='text' maxlength='2' class='qntyInput'" +
                    "name='quantity" + t + "'" +
                    "id='" + itemsOrdered[t] + "'" +
                    "value='" + orderAmount[t] + "'/>" +
                    "<input type='text' readonly='true' class='totalItemPrice'" +
                    "name='totalOwedPerItem" + t +
                    "' value='" + totalItemAmount + "'></div>" +
                    "</div>";
  
        }
        $('#orderMenuForm').append(displayText);
        $('#orderMenuForm').append("<input type='submit' id='submitMenu' value='Order'/>");
        $('#orderMenuForm').append("<input type ='hidden' name = 'amountOfItems' value ='" + (itemsOrdered.length-1) + "'/>");

    });

    //listens for remove item click
    $('#orderMenuForm').on('click', '.removeItem', function () {
        alert("TODO - remove item from list");
    });
});

