#use "graphes.ml";;
(Printf.printf " ~~~~~~~~~ IMPORT GRAPHES \n");;

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

let rec insertLast element theList = (
	match theList with
	| [] -> [element]
	| x::r -> x::(insertLast element r)
);;

let estimChemin etatFils coutsFils valParcours = (
	((hEtat1 etatFils) + coutsFils + valParcours)
);;

let rec ajouterFils fils file valParcours cheminActuel =
(
	match fils with
	| [] -> file
	| (o,a,c)::r -> (insertTriCout (a,(estimChemin a c valParcours), (insertLast o cheminActuel)) (ajouterFils r file valParcours cheminActuel))
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
