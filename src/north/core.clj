(ns north.core)
(use 'clojure.pprint)

(set! *warn-on-reflection* true)

(def north.core/run-specs #())

(defmacro describe [feature & body]
  `(binding [*ns* (find-ns 'north.core)]
     (println *ns*)
     (eval 
       '(defn run-specs []
          (println "running")
          (~@body)))))

(defmacro context [description & body]
  `(println "Ending context"))

(defmacro it [description & body]
  `(println "Ending it"))

(defn should
  ([result matcher]
    (matcher result))
  ([result matcher expectation]
    (matcher result expectation)))

(defn- verify [result expectation operation description]
;  (assert 
;    (operation result expectation)
;    (format "Assertion failed. %s was expected to be %s %s" 
;            result 
;            description 
;            expectation)))
            )

(defn be-not-equals [result expectation]
  (verify result expectation not= "different than"))

(defn be-equals [result expectation]
;  (assert 
;    (= result expectation) 
;    (format "Assertion failed. %s was expected to be %s" 
;            result 
;            expectation))
  )

(defn be-true [result]
  (be-equals result true))

(defn be-false [result]
  (be-equals result false))
