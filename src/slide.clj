(ns slide
  (:require [[clojure.future :refer :all]
             [clojure.spec.alpha :as s]]))


;; NEW IDEA
;; describe nested structure in specs as well, like this:

(s/def ::generation
  (s/keys ::req [:generation/mean-errors
                 :individuals]))

(s/def ::individuals)

(s/def :generation/mean-errors-input (s/keys [:stats/xs]))
(s/def :generation/mean-errors (s/and :generation/mean-errors-input ::xs))

(s/fdef  ->generation-mean-errors
  :args (s/cat :individuals ::individuals)
         ;; look up if it's possible to do this `just-key` thing...
         ;; or like partial fulfil spec
         ;; or maybe have to add more metaata then just spec defintiation
         ;; here, which is fine, because i am probably already overlaoding it
         ;; too much
  :ret :generation/mean-errors-input)
(defn ->generation-mean-errors [individuals]
  {:stats/xs (map :mean-error individuals)})


;; anyway, basically describe structure in specs
;; functions still provide links in graph
;; links can go between levesl and down levels
;; then just have a few inputs that are not filled....
;; for things like subcalls and mappings and such

;; columnar data?? look into this in java could be helpful to store repetitions
;; instead of in nested structres
;; also look at tensorflow fold here paper maybe it has some answers
;;


;; to make new individuals, really have a function that makes a sequence of
;; partial graphs...
;; that beocmes sequence, like this:

(s/def ::individual-input (s/keys [:individual/genome :individual/parent]))
(s/def ::indivual (s/and ::indivual-inpiut (s/keys [:individual/whatever])))
(f/fdef ->individuals
  :ret (s/seq ::individual-input))


;; to do optional, like reproduction, return or

(s/def ::crossover (s/keys [:crossover/input-1 :crossover/input-2 :child-genom]));l]))
(s/def ::child-input (s/or :crossover ::crossover :mutation ::mutation))
(s/def ::child (s/and ::child-input (s/keys ::indivual ...)))
(s/fdef ->children
  :ret (s/seq ::child-input))


;; for reporting, just conditionally get different options and have reporting
;; be out of frameowrk. like look up things in config and have those be top
;; level outputs if we do want to report them, and feed the whole output computed;
;; graph to functions to record..


;; how to do partial? like per generatiobn?
;;;;





(function (fn [a b] [(inc a) (* a b)])
          :input [::hi ::there]
          :output [::three ::four])

(call :input {:something/hi ::hi}
      :output {:something/there ::there})

(map :list ::individuals
     :input :some/ind
     :output {::local/means :some/means})
(sum :input :some/means :output :)

;; thoughts
; what about passing state as just data? because clojure is supposed to be
;; all about data?
;;


;; specs should be used to verify what we want about graphs... its the solution
;; in clojure. dont rebuild it. so we should be able to say when we want to, what
;; we want.

;; how should we spec this data structure? what can i say about this that
;; i know must be true?

;; use clojure spec for random data??

;; what about doing something like instrument for graph?
;; replace function with funciton that gets values from graph?

;;; some more examples...

;; more ideas
;; only call subgraphs throught input, output functions. returns new graph
;; with keys assoced that were computed

(s/def ::edge-fn fn?)
(s/def ::input (s/coll-of keyword?))
(s/def ::output (s/coll-of keyword?))
(s/def ::edge (s/keys :req [::edge-fn ::input ::output]))
(s/def ::dynamic-edge-fn (s/fn :ret ::edge))
(s/def ::dynamic-edge (s/keys :req [::dynamic-edge-fn ::input]))
(s/def ::graph (s/coll-of (s/or ::edge ::dynamic-edge)))

(s/def ::state
  (s/and
   (s/map-of keyword? any?)
   #(s/valid? (eval `(s/keys :req [~@(keys %)]))
              %)))

(s/def ::outputs (s/coll-of keyword?))

(s/fdef run
  :args (s/cat :graph ::graph
               :state ::state
               :outputs ::outputs)
  :ret ::state
  :fn (every? (partial contains? (:ret %)) (-> % :args :outputs)))

;; why do we need dynamic graphs?
;; just for logging inputs?
;; also for remote logging outputs? is it just logging?
;; what about for push generation?


;; how to do run-push with this?
;; profile first
(defn run [graph state outputs])


(fspec [a ::crossover-parent-a b ::crossover-parent-b])
(defn crossover [parent-a parent-b])



(defn children-scopes [...]
  (let [[f n-parents] (get genetic-operator)
        parents (repeatedly n-parents (get parent-genome))
        child-genomes (apply f parents)]
    ()(set child-genomes)))

;; reprdoduction should be reducer, that takes in individuals and
(defn children [...]
  (eduction
    (conj
      cat
      (take population-size))
    (repeatedly (get children-scopes))))



;; transducer from list of parents to lists of children
(defn reproduce [->genetic-operator]
  (fn [rf]
    (fn
      ([] (rf))
      ([result] (rf result))
      ([result input]
       (reduce)))))

;; iterate reduce! https://github.com/clojure/clojure/blob/f572a60262852af68cdb561784a517143a5847cf/src/jvm/clojure/lang/Iterate.java#L63
(defn)
