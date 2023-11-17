(ns wow-helix.util)

(defn calc-large [large small]
  (- (- (* large 10)
        small)
     (* (* (/ large 100)
           5)
        10)))

(defn calc-small [large small]
  (- (- small
        (* large 10))
     (* (/ small 100)
        5)))
