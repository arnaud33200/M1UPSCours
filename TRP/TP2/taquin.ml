
(* ############# DECLARATION DES TYPES ############# *)

type operation = Up | Left | Down | Right;;

let vectorOfOperation = function
       Up   -> (0,-1)
    |  Left -> (-1,0)
    |  Down   -> (0,1)
    |  Right  -> (1,0)  ;;

let dim = 3;;

type etat = string list list * (int * int) * operation list;;

(* ############# VARIABLES TEST ############# *)

let (e1:etat) = ([["A";"C";"D"];
["H";"F";"B"];
["G";" ";"E"]], (1,2), [Up; Left; Right] );;

let (b1:etat) = 
([["A";"B";"C"];
["H";" ";"D"];
["G";"F";"E"]],(1,1), [Up; Left; Down; Right] );;

(* Determine si une opération est autorisé *)
let isAllowedOperation newdest = (
	if (fst newdest) < 0 || (fst newdest) >= (dim-1) || 
	(snd newdest) < 0 || (snd newdest) >= (dim-1) then false else true
);;

let setAllowedOperation	p = (
	if (fst p) = 0 && (snd p) = 0 then [Down; Right]
	else if (fst p) = 0 && (snd p) >= (dim-1) then [Up; Right]
	else if (fst p) >= (dim-1) && (snd p) >= (dim-1) then [Up; Left]
	else if (fst p) >= (dim-1) && (snd p) = 0 then [Down; Left]
	else if (fst p) = 0 then [Down; Up; Right]
	else if (fst p) >= (dim-1) then [Down; Up; Left]
	else if (snd p) = 0 then [Left; Up; Right]
	else if (snd p) >= (dim-1) then [Left; Down; Right]
	else [Up; Left; Down; Right]
	
);;

(* récupère un element x y de la matrice *)
	(* - si y = 0 alors on est sur la bonne ligne *)
	(* - si x = 0 alors on est sur la bonne colonne et on retourne l'element *)
let rec getElementMatrice x y matrice = (
	match matrice with
	| [] -> "X"
	| line::otherLine -> if (y != 0) then (getElementMatrice x (y - 1) otherLine)
		else (
			match line with
			| [] -> "X"
			| column::otherColumn -> if (x != 0) 
					then (getElementMatrice (x-1) y (otherColumn::otherLine))
				else column
		)
);;

(* Pour chaque ligne de la matrice, remplace l'element à changé à la position destPos *)
	(* et fait un remplacement par la position trou à la position originPos *)
let rec treatLines originPos destPos line x y matrice = (
	let (x0,y0)=originPos and (x1,y1)=destPos in (
		match line with
		| [] -> []
		| element::rest -> if (x,y) = originPos then (getElementMatrice x1 y1 matrice)::(treatLines originPos destPos rest (x+1) y matrice)
			else (if (x,y) = destPos then (getElementMatrice x0 y0 matrice)::(treatLines originPos destPos rest (x+1) y matrice)
				else element::(treatLines originPos destPos rest (x+1) y matrice)
			)
	)
);;

(* Echange la case originPos avec la case destPos dans la matrice *)
let rec switchPosition originPos destPos x y lines matrice = (
	match lines with
	| [] -> []
	| line::otherLine -> (treatLines originPos destPos line 0 y matrice)::(switchPosition originPos destPos 0 (y+1) otherLine matrice)
);;

let rec deplacer (etat:etat) (operation:operation) = (
	let (taquin, posVide, allowedOp)=etat in (
		let destPos=((fst posVide)+(fst (vectorOfOperation operation)),
					(snd posVide)+(snd (vectorOfOperation operation))) in (
			if (not (isAllowedOperation destPos)) then failwith "Operation non autorise"
			else ((switchPosition posVide destPos 0 0 taquin taquin), destPos, setAllowedOperation destPos)
		)
	)
);;

#trace deplacer;;
(* #trace switchPosition;; *)
(* #trace treatLines;; *)
(* #trace getElementMatrice;; *)
(* (deplacer e1 Down);; *)
(deplacer b1 Up);;
(deplacer b1 Down);;

(* (treatLines (1,0) (2,0) ["A";"B";"C"] 0 0 [["A";"B";"C"];["H";" ";"D"];["G";"F";"E"]]);; *)