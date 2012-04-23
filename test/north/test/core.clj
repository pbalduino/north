(ns north.test.core
  (:use [north.core]))

(use 'clojure.pprint)

(pprint
  (macroexpand
    '(describe "North"
      (context "using context"
        (it "should test simple assertions"
          (should (= 1 1) be-true)
          (should (= 1 0) be-false)
          (should (+ 1 1) be-equals 2)
          (should (+ 1 1) be-not-equals 1))))))

(println)

(pprint
  (macroexpand
    '(it "should test simple assertions"
       (should (= 1 1) be-true)
       (should (= 1 0) be-false)
       (should (+ 1 1) be-equals 2)
       (should (+ 1 1) be-not-equals 1))))

(describe "North")

(run-specs)
