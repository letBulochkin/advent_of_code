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

(defn common_value [way frqs]
  (if (= (get frqs "1") (get frqs "0"))
    (case way 
      "most" (str 1)
      "least" (str 0))
    (case way
      "most" (key (apply max-key val frqs))
      "least" (key (apply min-key val frqs)))))

(defn matching_indexes [column value]
  (keep-indexed (fn [i v] (if (= value v) i)) column))

(defn rating_calculator [vals iteration way]
  (let [turned_vals (apply mapv list vals)
        line (nth turned_vals iteration)
        frq (frequencies line)
        indexes (matching_indexes line (common_value way frq))]
    (if (> (count indexes) 1)
      (rating_calculator (reduce (fn [vec ind] (conj vec (nth vals ind))) [] indexes) (+ iteration 1) way)
      (nth vals (nth indexes 0)))))

(def o2_gen_rating (str/join (rating_calculator input_vals 0 "most")))
(def co2_scrub_rating (str/join (rating_calculator input_vals 0 "least")))

(println (apply * (mapv #(Integer/parseInt % 2) [o2_gen_rating co2_scrub_rating]))) ; 6940518
