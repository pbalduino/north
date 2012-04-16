(ns north.test.core
  (:use [north.core]))

(use 'clojure.pprint)

(println "Describe")
(pprint
(macroexpand-1
  '(describe "North"
    (context "using context"
      (it "should test simple assertions"
        (should (= 1 1) be-true)
        (should (= 1 0) be-false)
        (should (+ 1 1) be-equals 2)
        (should (+ 1 1) be-not-equals 1))))))

(println)

(println "Context")
(pprint
(macroexpand
  '(context "using context"
    (it "should test simple assertions"
      (should (= 1 1) be-true)
      (should (= 1 0) be-false)
      (should (+ 1 1) be-equals 2)
      (should (+ 1 1) be-not-equals 1)))))

(println)

(println "It")
(pprint
(macroexpand
  '(it "should test simple assertions"
    (should (= 1 1) be-true)
    (should (= 1 0) be-false)
    (should (+ 1 1) be-equals 2)
    (should (+ 1  1) be-not-equals 1))))

(describe "North"
    (context "using context"
      (it "should test simple assertions"
        (should (= 1 1) be-true)
        (should (= 1 0) be-false)
        (should (+ 1 1) be-equals 2)
        (should (+ 1 1) be-not-equals 1))))
