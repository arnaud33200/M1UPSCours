(Printf.printf "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");;
(Printf.printf " ~~~~~~~~~ IMPORT GRAPHES \n");;
(Printf.printf "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");;
#use "graphes.ml";;

(Printf.printf "######### EXERCICE I ######## \n\n");;

let rec deepSearch p =
match p with 
[]-> failwith "pas de solution"
|x::r-> if (estBut1 x) then x
else (deepSearch ((etatsSuivants1 x)@r));;

(deepSearch ["A"]);;    (* - : string = "H" *)

let rec deepSearch p =
match p with 
[]-> failwith "pas de solution"
|x::r-> if (estBut1bis x) then x
else (deepSearch ((etatsSuivants1 x)@r));;

(deepSearch ["A"]);;    (* - : string = "I" *)

let rec deepSearch p =
match p with 
[]-> failwith "pas de solution"
|x::r-> if (estBut1ter x) then x
else (deepSearch ((etatsSuivants1 x)@r));;

(* (deepSearch ["A"]);;    (* Exception: Failure "pas de solution". *) *)


(Printf.printf "######### EXERCICE II ########\n");;

let addUnique e l =
if (List.mem e l) then e::l else l;;

let rec diff l d =
match l with
[]-> []
|x::r-> if (List.mem x d) then (diff r d)
else x::(diff r d);; 

let rec deepSearchV2 p v =
match p with 
[]-> failwith "pas de solution"
|x::r-> if (estBut2 x) then x
else (if (not (List.mem x v)) then (deepSearchV2 ((etatsSuivants2 x)@p) (x::v))
	 else (deepSearchV2 (diff p [x]) (x::v)));;

(deepSearchV2 ["A"] []);;


(Printf.printf "########### EXERCICE III ############\n");;

let rec insertLast e l = (
			  match l with
				[]->[e]
				|x::r->x::(insertLast e r)
);;

let rec deepSearchV3 lc v = ( 
match lc with 
       [] -> failwith "aucune solution"
| c::rc -> ( 
	    match c with
		  [] -> [];
		  | x::r -> if (estBut2 x) then [x]
else (if (not (List.mem x v)) 
then (insertLast x (deepSearchV3 (((etatsSuivants2 x)@r)::rc) (insertLast x v)))
	 else (deepSearchV3 (r::rc) v)) )
);;

(deepSearchV3 [["C"]] []);;

(Printf.printf "########### EXERCICE IV ############\n");;

let rec getNextInfo info next = (
			 match info with 
			       [] -> failwith "informations vide"
			       | (o,n,c)::r -> if n = next then (o,n,c)
			   						else (getNextInfo r next)
);;

let rec coutPacouru chemin = (
	match chemin with
	| [] -> 0
	| a::b::r -> let (o,n,c)=(getNextInfo (opPoss1 a) b) in c + (coutPacouru (b::r))
	| a::r -> 0
);;

(getNextInfo (opPoss1 "A") "B");;
(coutPacouru ["A";"B";"E";"I"]);;

