(ns slide
  (:require [[clojure.future :refer :all]
             [clojure.spec.alpha :as s]]))


(s/def ::count int?)
(s/def ::mean number?)
(s/def ::mean-square number?)
(s/def ::variance number?)

(defn ->count [xs]
  (count xs))

(defn ->mean [xs n]
  (/ (sum identity xs) n))

(defn ->mean-square [xs n]
  (/ (sum #(* % %) xs) n))

(defn ->varience [m m2]
  (- m2 (* m m)))

(+ 1 1)
