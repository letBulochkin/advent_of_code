(require '[clojure.string :as str])
(def input_vals (mapv #(str/split % #"") (str/split-lines (slurp "../input/input03.txt"))))
(def turned_vals (apply mapv list input_vals))

; In memory of my stupid attempt to recreate frequencies function
#_(def bit_count {"zeros" 0, "ones" 0})
#_(println (reduce (fn [res symb]
          (if (= symb "1")
            (assoc res "ones" (inc (get res "ones")))
            (assoc res "zeros" (inc (get res "zeros"))))) bit_count (nth turned_vals 0)))

(def rates (reduce (fn [[gamma_rate_str epsilon_rate_str] line]
                     (if (> (get (frequencies line) "1") (get (frequencies line) "0"))
                       (vector (str gamma_rate_str "1") (str epsilon_rate_str "0"))
                       (vector (str gamma_rate_str "0") (str epsilon_rate_str "1")))) ["", ""] turned_vals))

(println (apply * (mapv #(Integer/parseInt % 2) rates))) ; 3009600
