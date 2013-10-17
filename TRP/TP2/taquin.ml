type operation = (0,1) | (1,0) | (0,-1) | (-1,0);;
let dim = 3;;

type etat = string list list * (int * int) * operation list;;

let (e1:etat) = ([["A";"C";"D"];
["H";"F";"B"];
["G";" ";"E"]], (1,2), [(0,1); (1,0); (-1,0)] );;

let (b1:etat) = ([["A";"B";"C"];
["H";" ";"D"];
["G";"F";"E"]],(1,1), [(0,1); (1,0); (0,-1); (-1,0)] );;

let rec allowedOperation operation listOperation = (
	match listOperation with
	| [] -> false
	| o::r -> if operation = o then true else (allowedOperation operation listOperation)	
);;

let rec switchPosition originPos destPos matrice = (

);;

let rec deplacer (etat:etat) (operation:operation) = (
	let (taquin, posVide, allowedOp)=etat in (
		if (not (allowedOperation operation allowedOp)) then
			(failwith "Operation non autorisé")
		else (
			(switchPosition posVide ((fst posVide)+(fst operation),(snd posVide)+(snd operation)))
		)
	)
);;