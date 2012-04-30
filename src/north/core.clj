(ns north.core)
(use 'clojure.pprint)

(set! *warn-on-reflection* true)

(def spec-list {})

(def test-count (let [count (ref 0)] #(dosync (alter count inc))))
(def ok-count   (let [count (ref 0)] #(dosync (alter count inc))))
(def fail-count (let [count (ref 0)] #(dosync (alter count inc))))

(defstruct suite-struct :name :context-list :before-all :before-each :after-all :after-each)
(defstruct context-struct :name :test-list :before-all :before-each :after-all :after-each)
(defstruct test-struct :name :fun)

(defmacro describe [feature & body]
  `(binding [*ns* (find-ns 'north.core)]
     (eval 
       '(def spec-list
            (struct-map suite-struct
              :name (str ~feature)
              :context-list [~@body])))))

(defmacro context [description & body]
  `(struct-map context-struct 
      :name (str ~description) 
      :test-list [~@body]))

(defmacro it [description & body]
  `(struct-map test-struct 
      :name (str ~description)
      :fun #(do ~@body)))

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

(defn run-specs-2[]
  (println "Showing specs")
  (pprint spec-list))

(defn run-specs[]
  (dorun
  (map (fn [x]
    (dorun 
      (map 
        (fn [y] 
          (println (str (:name spec-list) ": " (:name x) " " (:name y)))
          (test-count)
          (try
            (do
              ((:fun y))
              (println "OK\n")
              (ok-count))
            (catch Exception e
              (println "Error")
              (fail-count)
              (.printStackTrace e))))
        (:test-list x))))
  (:context-list spec-list)))
(println (str "Ran " (dec (test-count)) " tests with 0 assertions"))
(println (str (dec (ok-count)) " passed and " (dec (fail-count)) " failed.")))