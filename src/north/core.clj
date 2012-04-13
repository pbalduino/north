(ns north.core)

(def ^:dynamic test-map {})

(defmacro describe [description & body]
  `(do
    (let [description-key (keyword ~description)]
      (def test-map (assoc test-map description-key {}))
      (println test-map)
      ~body)))