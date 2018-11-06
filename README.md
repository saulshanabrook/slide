# slide
**NOTE: If anyone comes accross this looking for continuation of this work, I now believe that category theory has the answers that we were looking for here. See this talk/paper ["Compiling to Categories"](http://conal.net/papers/compiling-to-categories/). We are using some of this theory in compiling array expression in Python: https://github.com/quansight-labs/uarray/. At some point this work could come back to Clojure.**

Graph computation library for Clojure. Computation happens on nodes of the graph and data flows (or *slides* 😉) accross it's edges and is stored globally. 


Rip off of [Plumatic's Graph library](https://github.com/plumatic/plumbing/#graph-the-functional-swiss-army-knife) that supports:

- [ ] `clojure.spec` instead of PLumatic schema, in order to integrate better into the larger Clojure community and make it easier for new devevlopers to understand.
- [ ] [Iterative calculations](https://github.com/plumatic/plumbing/issues/41), so that your whole program can be a graph.
- [ ] Dynamic inputs, so that a component can determine it's input from other data in the graph/
- [ ] Modular configuration, so that you can easily modify existing graphs without rewriting their code.
- [ ] Sensible model for side affects and cleanup.

It also should continue to support the great things from Plumatic's graph library like:

- [ ] Succinct syntax that puts functions front and center.
- [ ] Nested graphs, each with their own scope.
- [ ] [Visualizations of the graph](https://github.com/plumatic/plumbing/issues/5#issuecomment-236776605).

How is it going to do all of this magic? Well I have a few ideas so far:

* Use `spec`ed functions are graph nodes. The namespace keywords of it's input arguments and ouputs correspond to specs as well as label the data. So if one functions outputs `::name` and another function has a `::name` input and they are in the same graph, then the data is fed between them.
* Iteration is recursion with a constant stack size, so that that the new scope should have the same variables as the old one. We can support iteration with self referential nodes, that take in the same keyword as they produce. Then they just have to be told when to stop, so each node should also support a conidtional data input. Jack Dennis (the dataflow OG) called these "control links" as opposed to "data links". 


Inspiration:

* ["First version of a data flow procedure language" Jack B. Dennis 1975](http://publications.csail.mit.edu/lcs/pubs/pdf/MIT-LCS-TM-061.pdf)
* ["Concepts, techniques, and models of computer programming" Peter Van Roy, Seif Haridi 2004](https://github.com/avikalpg/POPL/blob/master/Peter%20Van%20Roy%2C%20Seif%20Haridi-Concepts%2C%20techniques%2C%20and%20models%20of%20computer%20programming-MIT%20Press%20(2004).pdf)


Things I should take inspiration from, but haven't figured out how yet:

* ["Taming the Parallel Effect Zoo: Extensible Deterministic Parallelism with LVish" 2014](http://www.ccs.neu.edu/home/samth/effectzoo-pldi14.pdf)
* ["A computational model for TensorFlow" 2017](http://dl.acm.org/citation.cfm?id=3088527)
* [Differential Dataflow in Rust by Frank McSherry](https://github.com/frankmcsherry/differential-dataflow)
* ["A Formal Definition of Data Flow Graph Models" 1986](https://www.dropbox.com/s/pivkrz0u2inur28/A%20Formal%20Definition%20of%20Data%20Flow%20Graph%20Models%20-%20IEEE%20Transactions%20on%20Computers.pdf?dl=0)
* ["DFGR: An intermediate graph representation for macro-dataflow programs" 2014](https://wiki.rice.edu/confluence/download/attachments/4425835/ASbirlea-DFGR-DFM14.pdf?version=1&modificationDate=1418108610830&api=v2)
* ["Polyhedral Optimizations for a Data-Flow Graph Language" 2016](https://wiki.rice.edu/confluence/download/attachments/4425835/sbirlea_lcpc2015.pdf?version=1&modificationDate=1441326591633&api=v2)
