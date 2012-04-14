(ns north.test.core
  (:use [north.core]))

(println
(macroexpand
  '(describe "North"
    (context "using context"
      (it "should test simple assertions"
        (should (= 1 1) be-true)
        (should (= 1 0) be-false)
        (should (+ 1 1) be-equals 2)
        (should (+ 1 1) be-not-equals 1))))))

(do
  (clojure.core/in-ns
    (quote north.core))
  (clojure.core/println
    (clojure.core/format "before %s" north.core/test-map))
  (clojure.core/println
    (clojure.core/format "description %s" "North"))
  (def north.core/test-map
    (clojure.core/assoc north.core/test-map
      (clojure.core/keyword "North") {}))
  (clojure.core/println
    (clojure.core/format "after %s" north.core/test-map))
  ((context "using context"
     (it "should test simple assertions"
       (should (= 1 1) be-true)
       (should (= 1 0) be-false)
       (should (+ 1 1) be-not-equals 1)
       (should (+ 1 1) be-equals 2)

        true
       )
     )
    )
  )