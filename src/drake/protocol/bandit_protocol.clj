(ns drake.protocol.bandit-protocol
  "Demonstrates an additional protocol within the drake-echostep project.

   You can use dashes in the protocol name, but note that the underlying filename
   replaces the dashes with underscores, e.g. bandit_protocol.clj.

   It may not be the wisest thing to bundle in protocols other than echostep,
   since there could theoretically be naming collisions if other plugins do the same.
   But it works fine, assuming there's never a protocol naming collision with others.")

(defn bandit-protocol
  "Formats the step hash-map and spits it to the step's output file.
   The step hash-map holds all data about the step, and that's what your protocol
   uses to decide what to do. Valuable step data includes:
     :cmds    The step's commands (a.k.a., body), as a sequence, one element per line
     :inputs  All input files specified by the step
     :outputs All output files specified by the step
     :opts    All options specified by the step, as a hash-map"
  []
  (reify Protocol
    (cmds-required? [_] false) ;; a bandit-protocol step doesn't need commands
    (run [_ step]
      (println "A bandit Protocol! Does nothing."))))