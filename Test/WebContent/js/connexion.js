var noConnection=false;
var env={};
var logg;

function funct_error(texte){
    $("#div_error").text(texte);
    $("#div_error").css( { "color":"red"});
    $("#div_error").css( { "font-size": "23px"});
}





//recupere les infos du formulaire
function connexion(formulaire){
    var login = document.getElementById("login").value;
    logg=login;
    var password = document.getElementById("password").value;
    var ok=verif(login,password);
        if(ok){

            connecte(login,password);
        }
        else{
            console.log("error connexion");
        }
}


//verifie les infos du formulaire
function verif(login,password){
    if(login.length==0){
        funct_error("erreur login");
        return false;
    }

    if(password.length==0){
        funct_error("erreur mot de passe");
        return false;
    }
    return true;
}


//connecte l'utilisateur avec le serveur
function connecte(login,password){
    if(!noConnection){

        $.ajax({type:"post",
                url:"Login",
                data:"user="+login+"&mdp="+password,
                datatype:"json",
                success:function(res){
                    env.fromLogin=login;
                    env.fromMdp=password;
                    responseConnexion(res);
                },
                error: function(xhr, status, err) {
                    funct_error(status + ": " + err+"probleme de connexion dans fonction ajax");      
                }
                      });
    }
    
}



function responseConnexion(rep){
    ob=JSON.parse(rep);
    if(ob.bienvenue==undefined){
    	funct_error(ob.message);
    }
    else{
        //parse necessaire
      makeMainPanel();
  

        }
}


function makeMainPanel(){
    $("body").load("principale.html");
	 $("#ins").attr('disabled', 'disabled');
}



function makeConnexionPanel(){
    
      $("body").load("connexion.html");
  }



function makeInscriptionPanel(){
      $("body").load("inscription.html");    
  }







function deconnecte(){
    if(!noConnection){

        $.ajax({type:"post",
                url:"Logout",
                data:"user="+logg,
                datatype:"json",
                success:function(res){
                    responseDeconnexion(res);
                },
                error: function(xhr, status, err) {
                    funct_error(status + ": " + err+" ajax");      
                }
                      });
    }
    else{
        console.log("ajax n'a pas marche");
    }
}



function responseDeconnexion(res){

    makeConnexionPanel();
}