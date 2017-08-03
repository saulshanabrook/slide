# slide
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
