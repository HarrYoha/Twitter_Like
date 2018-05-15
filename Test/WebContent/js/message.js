var lo;
var localdbPerso = [];
var localdbAmis = [];
var localdbListAmis = [];
var localdbConnected = [];

$(document).ready(function() {
	init();
});

function init() {
	var i = 0;
	$("#ins").attr("disabled", "disabled");
	lo = env.fromLogin;
	$("h2").text("Bonjour 	" + env.fromLogin);
	$("h2").css("color", "rgb(40, 157, 224)");
	i++;
	completeMessages();
	completeMessagesAmis();
	listeAmis();
}

function message(id, auteur, text, date, comments) {
	this.id = id;
	this.auteur = auteur;
	this.text = text;
	this.date = date;

	if (comments === undefined) {
		comments = [];
	}
	this.comments = comments;

}

function commentaire(id, auteur, texte, date, score) {
	this.id = id;
	this.auteur = auteur;
	this.texte = texte;
	this.date = date;
	this.score = score;
}

commentaire.prototype.getHTML = function() {
	var s = "Commentaire ID: " + this.id + " ";
	s += this.texte;
	s += "<br><br>";
	s += this.score;
	s += "From " + this.auteur + " the " + this.date;
	s += "<br>";
	s += "<br>";
	return s;
};

function SetVirtualDB() {
	/*
	 * var localdb = [];
	 * 
	 * var m = new message(1, "yoyo", "premier message", new Date()); var com =
	 * new commentaire(3, "yoto", "test un commentaire", new Date()); var test =
	 * new message(2, "yohan", "premier essai", new Date(), com); localdb[0] =
	 * m; localdb[1] = com; localdb[2] = test;
	 */
}

function message(id, auteur, text, date, comments) {
	this.id = id;
	this.auteur = auteur;
	this.text = text;
	this.date = date;

	if (comments === undefined) {
		comments = [];
	}

	this.comments = comments;

}

message.prototype.getHTML = function() {

	var s = "Message ID: " + this.id + " ";
	s += "<fieldset>" + this.text;
	s += "<legend> From " + this.auteur + " the " + this.date + " </legend>";
	s += "<br><br>";
	s += "</fieldset class=\"cadre\">";
	if (this.comments.texte !== undefined) {
		s += "<br>Commentaire : " + this.comments.texte;
		s += "<br>";
	}
	s += "</div>";
	s += "<br>";
	return s;
};

function commentaire(id, auteur, texte, date, score) {
	this.id = id;
	this.auteur = auteur;
	this.texte = texte;
	this.date = date;
	this.score = score;
}

commentaire.prototype.getHTML = function() {
	var s = "Commentaire ID: " + this.id + " ";
	s += this.texte;
	s += "<br><br>";
	s += this.score;
	s += "From " + this.auteur + " the " + this.date;
	s += "<br>";
	s += "</div>";
	s += "<br>";
	return s;
};

/*
 * 
 * function revival(key, value) { // Cas où on a un Message if (value.id_message !=
 * undefined) { return new message(value.id_message, value.auteur,
 * value.contenu, value.date, value.commentaires, value.nb_commentaires,
 * value.likes); } // Cas où on a un Commentaire else if (value.id_commentaire !=
 * undefined) { return new commentaire(value.id_commentaire, value.auteur,
 * value.contenu, value.date); } // Cas où on a une date //TODO: Faire ce cas //
 * Autres cas else { return value; } }
 * 
 * 
 */

function completeMessages() {

	if (!noConnection) {

		$.ajax({
			type : "GET",
			url : "ListMessage",
			data : "user=" + env.fromLogin,
			datatype : "JSON",
			success : function(rep) {

				completeMessagesReponse(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				funct_error(textStatus + "erreur dans ajax");
			},
		});
	}

}

// mettre rep en argument
function completeMessagesReponse(rep) {
	var jsonData = JSON.parse(rep);
	for (var i = 0; i < jsonData.user.liste_message.length; i++) {
		var counter = jsonData.user.liste_message[i];
		var m = new message(counter._id, jsonData.user.auteur, counter.message,
				counter.date);
		localdbPerso.push(m);

	}

	for (var x = 0; x < localdbPerso.length; x++) {
		$("#msg_connex").append(localdbPerso[x].getHTML());
	}

	console.log(localdbPerso.length);
	countMessage();
	
}

function completeMessagesAmis() {

	console.log("rentre dans completeMessageAmis");
	if (!noConnection) {
		console.log("env.fromLogin dans ajex message amis " + env.fromLogin);

		$.ajax({
			type : "GET",
			url : "ListMessagesAmis",
			data : "user=" + lo,
			datatype : "JSON",
			success : function(rep) {
				completeMessagesReponseAmis(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				funct_error(textStatus + "erreur dans ajax");
			},
		});
	}

}

function completeMessagesReponseAmis(rep) {
	console.log("rentre dans reponse de complete message amiiiiiis");
	var jsnData = JSON.parse(rep);
	for (var index = 0; index < jsnData.user.length; index++) {
		var countr = jsnData.user[index];
		console.log("datee dans amis " + countr.date);
		console.log("cense avoir le message dans amis" + countr.message);
		var ma = new message(countr._id, countr.auteur, countr.message,
				countr.date);
		localdbAmis.push(ma);
		console.log("check localdbamis" + localdbAmis[index]);

	}
	console.log("avant append de amis");
	for (var a = 0; a < localdbListAmis.length; a++) {
		$("#msg_amis").append(localdbAmis[a].getHTML());
	}
	$("body").append(
			"<style type=\"text/css\">.cadre {display: inline-block;}</style>");

	console.log(localdbAmis);
	countMessageFriend();
}

function listeAmis() {

	console.log("rentre dans liste amis");
	if (!noConnection) {

		$.ajax({
			type : "GET",
			url : "ListFriend",
			data : "user=" + lo,
			datatype : "JSON",
			success : function(rep) {
				ReponseListeAmis(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				funct_error(textStatus + "erreur dans ajax");
			},
		});
	}

}

function ReponseListeAmis(rep) {
	var jsson = JSON.parse(rep);
	for (var n = 0; n < jsson.Friends.length; n++) {
		var countr = jsson.Friends[n];
		console.log("datee dans liste  amis " + countr.dateAmis);
		console.log("cense avoir le logggin dans liste amis" + countr.login);
		localdbListAmis.push(countr.login);
		console.log("check localdbamis" + localdbListAmis[n]);

	}

	console.log("avant append de liste amis");
	for (var az = 0; az < localdbListAmis.length; az++) {
		$("#lst_amis").append("<br/>");
		$("#lst_amis").append("<div>");
		$("#lst_amis").append(localdbListAmis[az]);
		$("#lst_amis").append(
				'<button  value =\"retirer de la liste \" onClick="removeFriend(\''
						+ localdbListAmis[az] + '\')" />');
		$("</div>");
	}
	console.log(localdbListAmis.length);
	countFriend();

}

function removeFriend(friendToRemove) {
	console.log("rentre dans removeFriend");
	console.log("ajax removefriend test de user et friend" + lo + "   "
			+ friendToRemove);

	if (!noConnection) {
		$.ajax({
			type : "GET",
			url : "RemoveFriend",
			data : "user=" + lo + "&friend=" + friendToRemove,
			datatype : "JSON",
			success : function(rep) {
				ReponseRemoveFriend(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				funct_error(textStatus + "erreur dans ajax");
			},
		});
	}

}

function ReponseRemoveFriend(rep) {

	var jzon = JSON.parse(rep);
	var ma;
	for (var p = 0; p < localdbListAmis; p++) {
		if (localdbListAmis[p] == jzon.friendRemoved) {
			ma = p;
		}
	}

	localdbListAmis.splice(ma, 1);
	/*
	 * A TESTER
	 * 
	 * $("body").append("<div id=\"new_list_amis\"> </div>");
	 * $("#lst_amis").remove();
	 * 
	 * for (var az = 0; az < localdbListAmis.length; az++) {
	 * $("#new_list_amis").append("<br/>");
	 * $("#new_list_amis").append(localdbListAmis[az]);
	 * $("#new_list_amis").append( '<button value =\"retirer de la liste \"
	 * onClick="removeFriend(\'' + localdbListAmis[az] + '\')" />'); }
	 */
}

// RETESTER AVEC LE TEXTAREA

function ecrire_message() {
	var txt = $("#ecrire_message").val();
	if (txt == undefined)
		funct_error(textStatus + "erreur dans ajax");
	console.log("valeur de txt" + txt);
	if (!noConnection) {
		$.ajax({
			type : "POST",
			url : "AddMessage",
			data : "user=" + lo + "&message=" + txt,
			datatype : "JSON",
			success : function(rep) {
				ReponseEcrireMessage(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				funct_error(textStatus + "erreur dans ajax");
			},
		});
	}

}

function ReponseEcrireMessage(rep) {
	var jon = JSON.parse(rep);
	var mesa = new message(jon.user._id, jon.user.auteur, jon.user.message,
			jon.date);
	localdbPerso.push(mesa);
	$("#msg_connex").append(mesa.getHTML());

}

function isFriend(frieend) {
	for (var i = 0; i < localdbListAmis.length; i++) {
		if (localdbListAmis[i] == frieend) {
			return true;
		}
	}
	return false;
}

function ajouter_ami() {

	var txt = $("#ajouter_ami").val();
	if (txt == undefined)
		funct_error(textStatus + "erreur dans ajax");

	if (!isFriend(txt)) {
		console.log("valeur de txt dans ajouter ami" + txt);
		if (!noConnection) {
			$.ajax({
				type : "POST",
				url : "AddFriend",
				data : "user=" + lo + "&friend=" + txt,
				datatype : "JSON",
				success : function(rep) {
					ReponseAjouterAmi(rep);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					funct_error(textStatus + "erreur dans ajax");
				},
			});
		}
	}
	alert(txt + " is already your friend");
}

function ReponseAjouterAmi(rep) {
	var jon = JSON.parse(rep);
	if (jon.NewFriend == undefined)
		alert(jon.message);

	localdbListAmis.push(jon.NewFriend);
	$("#lst_amis").append("<br/>");
	$("#lst_amis").append(jon.NewFriend);
	$("#lst_amis").append(
			'<button  value =\"retirer de la liste \" onClick="removeFriend(\''
					+ jon.NewFriend + '\')" >Retirer</button>');

}

// A TESTER

// A Tester

function search_message() {
	console.log("rentre dans search message")
	var profil = $("#input_search").val();
	console.log(profil);
	if (profil == undefined)
		funct_error(textStatus + "erreur dans ajax");

	$.ajax({
		type : "POST",
		url : "ListMessage",
		data : "user=" + profil,
		datatype : "JSON",
		success : function(rep) {
			makeProfilPanel(rep);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			funct_error(textStatus + "erreur dans ajax");
		},
	});

}

// A Tester

function makeProfilPanel(rep) {

	var jsondata = JSON.parse(rep);
	var dbProfil = [];
	var pr = " ";
	var authro = jsondata.user.auteur;
	for (var i = 0; i < jsondata.user.liste_message.length; i++) {
		var counter = jsondata.user.liste_message[i];
		var m = new message(counter._id, jsondata.user.auteur, counter.message,
				counter.date);
		dbProfil.push(m);

	}

	for (var x = 0; x < dbProfil.length; x++) {
		pr += dbProfil[x].getHTML();
	}

	$("body").html(
			"<h2> Vous etes sur la page de profil " + authro + "</h2> <br/> "
					+ pr);
}

function countMessage() {
	console.log("count_mess" + localdbPerso.length)
	$("#stat_nb_mess").append(
			"Nombre de messsage postés " + localdbPerso.length
					+ "<br/> <br/> <br/>");
}

function countFriend() {
	$("#stat_nb_mess").append(
			"Nombre d'amis " + localdbListAmis.length + "<br/> <br/> <br/>");
}

function countMessageFriend() {
	$("#stat_nb_mess").append(
			"Nombre de messages postés par vos amis " + localdbAmis.length
					+ "<br/> <br/> <br/>");
}

function listConnected() {
	console.log("rentre dans listConnected");
	console.log("valeur de lo dans listconnected" + lo);
	if (!noConnection) {
		$.ajax({
			type : "POST",
			url : "ListConnected",
			data : "user=" + lo,
			datatype : "JSON",
			success : function(rep) {
				ReponseListConnected(rep);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				funct_error(textStatus + "erreur dans ajax "+jqXHR +errorThrown);
			},
		});
	}
}

function ReponseListConnected(rep) {

	alert(rep);
	console.log("rentre dans reponse listconnected");
	var jsonData = JSON.parse(rep);
	for (var i = 0; i < jsonData.connected.length; i++) {
		var counter = jsonData.connected[i].login;
		localdbConnected.push(counter);
	}

	console.log(localdbConnected.length);
	$("#list_con").hide();
	for (var x = 0; x < localdbConnected.length; x++) {
		$("#stat_nb_mess").append("<br/> <br/> <br/>");
		$("#stat_nb_mess").append(localdbConnected[x]);
	}

	console.log(localdbPerso.length);

}
