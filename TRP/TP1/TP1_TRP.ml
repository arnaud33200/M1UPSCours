(Printf.printf " ");;
(Printf.printf "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");;
(Printf.printf " ~~~~~~~~~ IMPORT GRAPHES ");;
(Printf.printf "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");;
(Printf.printf " ");;
#use "graphes.ml";;

(Printf.printf " ");;
(Printf.printf "######### EXERCICE I ######## ");;
(Printf.printf " ");;

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


(Printf.printf " ");;
(Printf.printf "######### EXERCICE II ########");;
(Printf.printf " ");;

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


(Printf.printf " ");;
(Printf.printf "########### EXERCICE III ############");;
(Printf.printf " ");;

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

(deepSearchV3 [["A"]] []);;


(Printf.printf " ");;
(Printf.printf "########### EXERCICE IV ############");;
(Printf.printf " ");;

let rec getOperation info next = (
			 match info with 
			       [] -> failwith "informations vide"
			       | (o,n,c)::r -> if n = next then o
else (getOperation r next)
);;

