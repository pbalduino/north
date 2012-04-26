(ns north.test.result)

(use 'clojure.pprint)

(def run-specs #())
(def ^:dynamic test-list {})

(defn run-specs []
	(binding [test-list (assoc test-list "Feature name" {})]
		(println test-list)))

(defstruct suite-struct :name :context-list)
(defstruct context-struct :name :test-list)
(defstruct test-struct :name :fun)

(pprint
	(struct-map suite-struct "Feature name" 
		[(struct-map context-struct "Default" 
			[(struct-map test-struct "Sample test" #{println "Uhu!"})
			 (struct-map test-struct "Another test" #{println "Another test"})
		  ]
	  )]
  )
)

(pprint suite-struct)