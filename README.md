drake-echostep
==============

An example plugin for Drake. Illustrates how to extend Drake with a custom protocol.

Includes `src/drake/protoocol/echostep.clj`, an implementation of the `echostep` protocol. The `echostep` protocol pretty prints all step data into the step's output file. So you can use this plugin to easily inspect Drake's step data model. Super handy when you're implementing your own protocol and need to know how to get out specific step data.

Includes `src/drake/fs/mock.clj`, an implementation of a Drake FileSystem.

This plugin has been deployed to Clojars. To use it, include the plugin in your `plugins.edn` file in your Drake workflow dir. E.g.:
```clojure
{:plugins [[dirtyvagabond/drake-echostep "0.2.0"]]}
```

Then your Drake workflow can use the `echostep` protocol, e.g.:

```clojure
; Writes all step data to step.out.
; Assuming your workflow is in Drakefile:
; drake +=step.out
step.out <- [echostep +myopt]
  A command
  another command
  last command
```

This project also implements `bandit-protocol` as a demonstration of bundling more than one protocol implementation in a single plugin. It also demonstrates setting the `no-cmds-required` option, as well as using a dash in your protocol name. See `src/drake/bandit_protocol.clj`.

For more information about Drake plugins, please see [the Plugins wiki page](https://github.com/Factual/drake/wiki/Plugins).
