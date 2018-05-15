function commentaire(id, auteur, texte, date, score){
	this.id=id;
	this.auteur=auteur;
	this.texte=texte;
	this.date=date;
	this.score=score;
}

function SetVirtualDB()
{
	localdb=[];
	var a1={"id":1,"login":"sly"};
	var a2={"id:":2,"login":"fab"};
	var a3={"id":4, "login":"joe"};
	var c1=new Commentaire(1,"user3","hum",new Date(),0);
	localdb[1]=new Message(42,"yoyo","premier message",new Date());
	localdb[2]=new Message(43,"3408749","Hello",new Date());
	localdb[3]=new Message(44,"3408750","Buenos Dias", new Date(),c1);
}


commentaire.prototype.getHTML=function(){
	var s="Commentaire ID: "+this.id+" ";
	s+=this.texte;
	s+="<br><br>";
	s+=this.score;
	s+="From "+this.auteur+" the "+this.date;
	s+="<br>";
	s+="</div>";
	s+="<br>";
	return s;
}


var c=new commentaire(1,"yoyo"," first comment",new Date(),3);
document.write(c.getHTML());