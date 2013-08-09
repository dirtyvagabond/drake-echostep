(ns drake.bandit-protocol
  "Demonstrates an additional protocol within the drake-echostep project.

   You can use dashes in the protocol name, but note that the underlying filename
   replaces the dashes with underscores, e.g. bnadit_protocol.clj.

   It may not be the wisest thing to bundle in protocols other than drake.echostep,
   since there could theoretically be naming collisions if others do the same thing.
   But it works fine, assuming there's never a protocol naming collision with others.")

(defn ^:no-cmds-required bandit-protocol
  "The :no-cmds-required metadata tells Drake that any step using the bandit-protocol
   need not supply commands in the step body."
  [step]
  (println "A bandit Protocol! Does nothing."))