(ns north.test.result)

(use 'clojure.pprint)

(def run-specs #())
(def ^:dynamic test-list {})

(defn run-specs []
	(binding [test-list (assoc test-list "Feature name" {})]
		(println test-list)))

(defstruct suite-struct :name :context-list :before-all :before-each :after-all :after-each)
(defstruct context-struct :name :test-list :before-all :before-each :after-all :after-each)
(defstruct test-struct :name :fun)

(def tests
	(struct-map suite-struct 
		:name "Feature name" 
		:context-list [(struct-map context-struct 
			:name "In some strange context" 
			:test-list [(struct-map test-struct 
							:name "it should do this" 
							:fun #(println "--> Uhu!"))
						(struct-map test-struct 
							:name "and it should do that" 
							:fun #(println "--> Another test"))])]))

(def test-count (let [count (ref 0)] #(dosync (alter count inc))))
(def ok-count   (let [count (ref 0)] #(dosync (alter count inc))))
(def fail-count (let [count (ref 0)] #(dosync (alter count inc))))

(pprint tests)

(println "Running tests")
(dorun
	(map (fn [x]
		(dorun 
			(map 
				(fn [y] 
					(println (str (:name tests) ": " (:name x) " " (:name y)))
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
	(:context-list tests)))
(println (str "Ran " (dec (test-count)) " tests with 0 assertions"))
(println (str (dec (ok-count)) " passed and " (dec (fail-count)) " failed."))