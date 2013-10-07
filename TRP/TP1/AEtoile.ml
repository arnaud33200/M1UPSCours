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

let estimChemin etatfils estimPere = (

);;

let rec ajouterFils fils file  estimPere =
(
	match fils with
	| [] -> file
	| (o,a,c)::r -> (insertTriCout (a,c+(hEtat1 a)) (ajouterFils r file))
);;

let rec profondeurA attent =
(
	match attent with
	| [] -> failwith "etat non trouve"
	| (e,v)::r -> if (estBut1 e) then (e,v)::[]
		else (e,v)::(profondeurA (ajouterFils (opPoss1 e) r))
);;

let test = [("A",1);("B",2);("C",4);("D",7)];;

#trace profondeurA;;
profondeurA [("A",7)];;