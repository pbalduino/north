(ns north.core)
(set! *warn-on-reflection* true)

(def ^:dynamic *features* [])
(def ^:dynamic *contexts* [])

(defmacro describe [feature & body]
  `(binding [*features* (conj *features* '~feature)]
     ~@body))


(defmacro context [description & body]
  `(binding [*contexts* (conj *contexts* '~description)]
     ~@body))

(defmacro it [description & body]
  `(defn ~(symbol description) []
     ~@body))

(defn should
  ([result matcher]
    (matcher result))
  ([result matcher expectation]
    (matcher result expectation)))

(defn- verify [result expectation operation description]
  (assert 
    (operation result expectation)
    (format "Assertion failed. %s was expected to be %s %s" 
            result 
            description 
            expectation)))

(defn be-not-equals [result expectation]
  (verify result expectation not= "different than"))

(defn be-equals [result expectation]
  (assert 
    (= result expectation) 
    (format "Assertion failed. %s was expected to be %s" 
            result 
            expectation)))

(defn be-true [result]
  (be-equals result true))

(defn be-false [result]
  (be-equals result false))
