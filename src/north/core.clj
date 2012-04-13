(ns north.core)

(def ^:dynamic test-map {})

(defmacro describe [description & body]
  `(do
    (def test-map (assoc test-map (keyword ~description) {}))
    (println test-map)
    ~body))

(defmacro context [description & body]
  `(do
     ()))