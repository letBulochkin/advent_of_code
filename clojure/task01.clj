(def res 0) ; result variable for task 01 part 1
(def res2 0) ; result variable for task 01 part 2
(def input_vals ; vector of input values
  (mapv read-string
        (with-open [rdr (clojure.java.io/reader "../input/input01.txt")] (reduce conj [] (line-seq rdr)))))

;; Solution: Task 01 part 1
(doseq [n (range 1 (count input_vals))]
  (def prev (nth input_vals (- n 1)))
  (def cur (nth input_vals n))
  (if (> cur prev)
    (def res (+ res 1))
    nil))
(println res) ; 1624

;; Solution: Task 01 part 2
(def prev_triple_measure (reduce + (take 3 input_vals)))
(doseq [n (range 3 (count input_vals))]
  ;;(def cur_triple_measure (reduce + (drop (- n 2) (take (+ n 1) input_vals))))
  (def cur_triple_measure (->> input_vals
                               (take (+ n 1))
                               (drop (- n 2))
                               (reduce +)))
  (if (> cur_triple_measure prev_triple_measure)
    (def res2 (+ res2 1))
    nil)
  (def prev_triple_measure cur_triple_measure))
(println res2) ; 1653
