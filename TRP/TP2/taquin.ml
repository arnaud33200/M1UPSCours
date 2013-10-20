
(* ############# DECLARATION DES TYPES ############# *)

type operation = Up | Left | Down | Right;;

let vectorOfOperation = function
       Up   -> (1,0)
    |  Left -> (0,1)
    |  Down   -> (-1,0)
    |  Right  -> (0,-1)  ;;

let dim = 3;;

type etat = string list list * (int * int) * operation list;;

(* ############# VARIABLES TEST ############# *)

let (e1:etat) = ([["A";"C";"D"];
["H";"F";"B"];
["G";" ";"E"]], (1,2), [Up; Left; Right] );;

let (b1:etat) = ([["A";"B";"C"];
["H";" ";"D"];
["G";"F";"E"]],(1,1), [Up; Left; Down; Right] );;

let rec allowedOperation operation listOperation = (
	match listOperation with
	| [] -> false
	| o::r -> if operation = o then true else (allowedOperation operation listOperation)	
);;

(* récupère un element x y de la matrice *)
	(* - si y = 0 alors on est sur la bonne ligne *)
	(* - si x = 0 alors on est sur la bonne colonne et on retourne l'element *)
let rec getElementMatrice x y matrice = (
	match matrice with
	| [] -> failwith "element not found"
	| line::otherLine -> if (y != 0) then (getElementMatrice x (y - 1) otherLine)
		else (
			match line with
			| [] -> failwith "element not found"
			| column::otherColumn -> if (x != 0) 
					then (getElementMatrice (x-1) y (otherColumn::otherLine))
				else column
		)
);;

(* Echange la case originPos avec la case destPos dans la matrice *)
let rec switchPosition originPos destPos matrice = (
	let (x0,y0) = originPos and (x1,x2) = destPos in (
		match matrice with
		| [] -> []
		| line::otherLine -> if x0 != 
	)
);;

let rec deplacer (etat:etat) (operation:operation) = (
	let (taquin, posVide, allowedOp)=etat in (
		if (not (allowedOperation operation allowedOp)) then
			(failwith "Operation non autorisé")
		else (
			(switchPosition posVide 
				((fst posVide)+(fst (vectorOfOperation operation)),
					(snd posVide)+(snd (vectorOfOperation operation)))
				taquin)
		)
	)
);;

e1;;