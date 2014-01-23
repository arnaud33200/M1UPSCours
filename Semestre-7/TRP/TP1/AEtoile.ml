#use "graphes.ml";;
(Printf.printf " ~~~~~~~~~ IMPORT GRAPHES \n");;

(* insertion trié sur la valeur de l'element e dans la liste *)
let rec insertTriCout e l = 
(
	match l with
			| [] -> [e]
			| (etatfile,valeurfile,cheminfile)::r -> let (etatnouveau,valeurnouveau, cheminnouveau)=e in 
			(
				if valeurnouveau < valeurfile then (etatnouveau,valeurnouveau, cheminnouveau)::(etatfile,valeurfile,cheminfile)::r 
				else (etatfile,valeurfile,cheminfile)::(insertTriCout e r)
			)		
);;

(* insertion d'un element à la fin de la liste *)
let rec insertLast element theList = (
	match theList with
	| [] -> [element]
	| x::r -> x::(insertLast element r)
);;


let estimChemin etatFils coutsFils valParcours = (
	((hEtat1 etatFils) + coutsFils + valParcours)
);;

(* ajout les fils du noeud actuellement développé *)
(* dans la file des noeuds à développer *)
(* #### valParcours : valeur du chemin parcours par le noeud père *)
(* #### cheminActuel : liste des opérations effectués sur les noeufs père *)
let rec ajouterFils fils file valParcours cheminActuel =
(
	match fils with
	| [] -> file
	| (o,a,c)::r -> (insertTriCout (a,(estimChemin a c valParcours), (insertLast o cheminActuel)) 
		(ajouterFils r file valParcours cheminActuel))
);;


(* ( Etat, coup, chemin) *)
let rec profondeurA attent=
(
	match attent with
	| [] -> failwith "etat non trouve"
	| (etat,valeur, chemin)::r -> if (estBut1 etat) then (chemin,valeur)
		else (profondeurA (ajouterFils (opPoss1 etat) r (valeur - (hEtat1 etat)) chemin))
);;

let test = [("A",1);("B",2);("C",4);("D",7)];;

#trace profondeurA;;
profondeurA [("A",7,[])];;
