(ns leiningen.north
  "Spec-like tool for BDD in Clojure"
  (:use [leiningen.deps :only [deps]])
  (:use [north.core]))

(defn north
  "Run specs using North"
  []
  (deps 'project)
  (println "Ready!")
  (run-specs)
  (println "Yeah!"))
