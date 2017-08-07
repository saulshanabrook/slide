(ns slide.examples.stats
  (:require [[clojure.future :refer :all]
             [clojure.spec.alpha :as s]

             [slide :refer [->graph]]]))


(s/def ::items (s/coll-of number?))
(s/def ::count int?)
(s/def ::mean number?)
(s/def ::mean-square number?)
(s/def ::variance number?)


(defn ->count [xs]
  (count xs))

(s/fdef ->count
  :args (s/cat :xs ::items)
  :ret ::count)

(defn-spec ->count [xs :- ::items] :- ::count
  (count xs))

(defn ->mean [xs n]
  (/ (sum identity xs) n))

(s/fdef ->mean
  :args (s/cat :xs ::items :n ::count)
  :ret ::mean)


(defn ->mean-square [xs n]
  (/ (sum (fn [x] (* x x) xs) n)))

(s/fdef ->mean-square
  :args (s/cat :xs ::items :n ::count)
  :ret ::mean-square)


(defn ->varience [m m2]
  (- m2 (* m m)))

(s/fdef ->varience
  :args (s/cat :m ::mean :m2 ::mean-square)
  :ret ::varience)


(ns program)


(s/def ::errors (s/coll-of number?))

(s/def ::errors-mean :slide.examples.stats/mean)

(defn errors-mean [errors items->mean]
  (items->mean errors))


(s/fdef errors-mean
  :args (s/cat :errors ::errors
               :items->means (s/fspec :args (s/cat :xs :slide.examples.stats/items)
                                      :ret :slide.examples.stats/mean))
  :ret ::errors-mean)
