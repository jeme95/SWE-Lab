var stompClient = null;

var matchId = null;

function connect() {

	value = document.querySelectorAll('body')[0].getAttribute('data'); // Übergabe des Parameters via Attribut im HTML-Tag
	                                                                   // ist mit Thymeleaf leichter zu realisieren.
    var socket = new SockJS('/rummy-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/update_rummy', function (message) {		// Bindet den Lambda-Ausdruck an den Socket.
			if (message.body != value)									// Falls sich der Wert auf Seiten des Servers (Inhalt der Nachricht), 
            	reload_rummy();											// geändert hat, wird die Seite neu geladen!
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function reload_rummy() {											 
	disconnect();   												// Socket-Verbindung trennen und die Seite neu laden. 
	window.location.replace(window.location.href);					// Verwandelt den bisherigen Post-Request in einen Get-Request,
}																	// wodurch er auf Seiten des Servers identifiziert werden kann.


function addCard() {
  var form = document.createElement('form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', '/rummy/verdecktZiehen');
  form.style.display = 'hidden';
  document.body.appendChild(form)
  form.submit();
}


function finishTurn() {
/*
  var form = document.createElement('form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', '/rummy/karteAblegen');
  form.style.display = 'hidden';
  document.body.appendChild(form)
  form.submit();
  */
  
  var formData = new FormData();
  formData.append("indexKarteSelected",indexKarteSelected );
  var request = new XMLHttpRequest();
request.open("POST", "/rummy/karteAblegen");
request.send(formData);
}


var boden = document.getElementById("boden");
var indexCurrentPlayer = boden.getAttribute("data-indexcurrentplayer");

var handKarten = document.getElementsByClassName("handkarten")[indexCurrentPlayer];
let karten = handKarten.querySelectorAll("span");

var ablegen = document.getElementById("ablegen");
    var ablegenMoeglich = ablegen.getAttribute('data-ablegenmoeglich');
    
var indexKarteSelected  ;
karten.forEach(function (karte) {
  karte.addEventListener("click", function () {

      for (var i = 0 ; i <karten.length ; i++){
        karten[i].classList.remove("active");
      }
      karte.classList.add("active");
    for (var i = 0 ; i <karten.length ; i++){
if (karten[i].classList.contains("active"))
  indexKarteSelected = i;
    }
    console.log("Karte "+indexKarteSelected+ " selected");
    console.log("ablegenMoeglich = "+ablegenMoeglich);

    if (ablegenMoeglich == "true"){
          console.log("ablegen ist möglich");
      document.getElementById("ablegen").disabled = false;
    }
  });
});

var ablegenButton = document.getElementById("ablegen");
ablegenButton.addEventListener("click",function (){
  karten[indexKarteSelected].remove();
})

