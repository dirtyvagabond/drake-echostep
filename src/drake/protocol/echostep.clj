(ns drake.protocol.echostep
  "A simple demonstration of a protocol implementation for a Drake plugin.
   Implements the 'echostep' protocol, which simply dumps all of a step's data
   into the output file specified in the Drake step."
  (:use [drake-interface.core :only [Protocol]]))

(defn run-echostep
  "I made this a separate function for clarity, but it could have been inline
   with the reify call below."
  [step]
  (let [outfile     (first (:outputs step))
        pretty-step (with-out-str (clojure.pprint/pprint step))]
    (spit outfile pretty-step)))

(defn echostep
  "Formats the step hash-map and spits it to the step's output file.
   The step hash-map holds all data about the step, and that's what your protocol
   uses to decide what to do. Valuable step data includes:
     :cmds    The step's commands (a.k.a., body), as a sequence, one element per line
     :inputs  All input files specified by the step
     :outputs All output files specified by the step
     :opts    All options specified by the step, as a hash-map"
  []
  (reify Protocol
    (cmds-required? [_] true) ;; an echostep step must have commands
    (run [_ step]
      (run-echostep step))))