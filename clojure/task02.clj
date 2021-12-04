(def input_vals (with-open [rdr (clojure.java.io/reader "../input/input02.txt")] (reduce conj [] (line-seq rdr))))

(def horizontal 0)
(def depth 0)
(doseq [n (range 0 (count input_vals))]
  (def cur (clojure.string/split (nth input_vals n) #"\s"))
  (case (nth cur 0)
    "forward" (def horizontal (+ horizontal (read-string (nth cur 1))))
    "down" (def depth (+ depth (read-string (nth cur 1))))
    "up" (def depth (- depth (read-string (nth cur 1))))
    (println "Oops!")))
(println horizontal)
(println depth)
(println (* horizontal depth)) ; 1670340

(def horizontal 0)
(def depth 0)
(def aim 0)
(doseq [k (range 0 (count input_vals))]
  (def cur (clojure.string/split (nth input_vals k) #"\s"))
  (case (nth cur 0)
    "forward" (do 
                (def horizontal (+ horizontal (read-string (nth cur 1))))
                (def depth (+ depth (* aim (read-string (nth cur 1))))))
    "down" (def aim (+ aim (read-string (nth cur 1))))
    "up" (def aim (- aim (read-string (nth cur 1))))
    (println "Oops!")))
(println (* horizontal depth)) ; 1954293920
