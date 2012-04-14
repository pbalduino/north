(ns north.core)
(def ^:dynamic test-map {})

(defmacro describe [description & body]
  `(do
     (in-ns 'north.core)
     (def test-map (assoc test-map (keyword ~description) {}))
     (println test-map)
     ~body))

(defmacro context [description & body]
  `(do
     ()))