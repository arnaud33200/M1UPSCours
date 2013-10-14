#use "graphes.ml";;
(Printf.printf " ~~~~~~~~~ IMPORT GRAPHES \n");;

let rec insertTriCout e l = 
(
	match l with
			| [] -> [e]
			| (n,c)::r -> let (a,b)=e in 
			(
				if b < c then (a,b)::(n,c)::r 
				else (n,c)::(insertTriCout e r)
			)		
);;

let estimChemin etatFils coutsFils valParcours = (
	((hEtat1 etatFils) + coutsFils + valParcours)
);;

let rec ajouterFils fils file valParcours =
(
	match fils with
	| [] -> file
	| (o,a,c)::r -> (insertTriCout (a,(estimChemin a c valParcours)) (ajouterFils r file valParcours))
);;

let rec profondeurA attent =
(
	match attent with
	| [] -> failwith "etat non trouve"
	| (etat,valeur)::r -> if (estBut1 etat) then (etat,valeur)::[]
		else (etat,valeur)::(profondeurA (ajouterFils (opPoss1 etat) r (valeur - (hEtat1 etat))))
);;

let test = [("A",1);("B",2);("C",4);("D",7)];;

#trace profondeurA;;
profondeurA [("A",7)];;
