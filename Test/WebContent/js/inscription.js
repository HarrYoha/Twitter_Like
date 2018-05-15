function enregistrement() {
	var prenom = document.getElementById("prenom").value;
	var nom = document.getElementById("nom").value;
	var login = document.getElementById("login").value;
	var password = document.getElementById("password").value;
	var retapez = document.getElementById("retapez").value;
	console.log("recupere le formulaire");
	var ok = verif_formulaire_enregistrement(prenom, nom, login, password,
			retapez);
	if (ok) {
		enregistre(prenom, nom, login, password);
	}
}

function verif_formulaire_enregistrement(prenom, nom, login, password, retapez) {

	if (prenom.length == 0) {
		funct_error("Veuillez ecrire votre prenom ");
		return false;
	}
	if (nom.length == 0) {
		funct_error("Veuillez ecrire votre nom");
		return false;
	}
	if (login.length == 0) {
		funct_error("Veuillez choisir un login ");
		return false;
	}

	if (password.length == 0) {
		funct_error("Veuillez choisir votre mot de passe ");
		return false;
	}

	if (password.length < 8) {
		funct_error("Votre mot de passe doit faire plus de huit caracteres");
		return false;
	}

	if (retapez.length == 0) {
		funct_error("Veuillez retapez votre mot de passe");
		return false;
	}

	if (password != retapez) {
		funct_error("Le mot de passe retapé est différent");
		return false;
	}

	if (password == login) {
		funct_error("Veuillez choisir un mot de passe different de votre login ");
		return false;
	}
	console.log("check des infos ok");
	return true;
}

function enregistre(prenom, nom, login, password) {
	console.log("Enregistrement de " + prenom);
	if (!noConnection) {
		$.ajax({
			type : "post",
			url : "CreateUser",
			data : "user=" + login + "&nom=" + nom + "&prenom=" + prenom
					+ "&mdp=" + password,
			datatype : "json",
			success : function(rep) {
				console.log("reussi va passe a la reponse");
				responseInscription(rep);
			},
			error : function(xhr, textStatus, error) {
				console.log(xhr.statusText);
				console.log(textStatus);
				console.log(error);
			}
		});
	}
}


function responseInscription(rep) {

	
	// parse necessaire
	obj = JSON.parse(rep);
	console.log("va afficher makeconnexionpanel");
	console.log("obj.user.value");
	makeConnexionPanel();

}