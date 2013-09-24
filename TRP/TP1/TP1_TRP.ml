#use "graphes.ml";;

(* ########### EXERCICE I ############ *)

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


(* ########### EXERCICE II ############ *)

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


(* ########### EXERCICE III ############ *)
(Printf.printf " ");;
(Printf.printf "########### EXERCICE III ############");;
(Printf.printf " ");;

let rec insertLast e l = 
(
 match l with
       []->[e]
       |x::r->x::(insertLast e r)
);;

let rec deepSearchV3 p v =
(
 match p with 
       []-> failwith "pas de solution"
       |x::r-> if (estBut2 x) then v
else (if (not (List.mem x v)) then (deepSearchV2 ((etatsSuivants3 x)@p) (insertLast x v))
	 else (deepSearchV3(diff p [x]) v))
);;

(deepSearchV3 ["A"] []);;