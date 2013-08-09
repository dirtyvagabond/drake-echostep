drake-echostep
==============

A simple, illustrative plugin for Drake.

Includes `src/drake/echostep.clj`, an implementation of the `echostep` protocol, which simply dumps all the step data into the output file. So you can use this plugin to inspect the step data model.

To use, include the plugin in our `plugins.edn` file in your Drake workflow dir. E.g.:
```clojure
{:plugins [[dirtyvagabond/drake-echostep "0.1.0"]]}
```

Then your Drake workflow can use the `echostep` protocol, e.g.:

```clojure
; Writes all step data to step.out. Assuming your workflow is in Drakefile:
; drake +=step.out
step.out <- [echostep +myopt]
  A command
  another command
  last command
```

This project also implements `bandit-protocol` as a demonstration of bundling more than one protocol implementations in a single plugin. It also demonstrates setting the `no-cmds-required` option, as well as using a dash in your protocol name. See `src/drake/bandit_protocol.clj`.