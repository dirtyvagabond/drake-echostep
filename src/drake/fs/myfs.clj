(ns drake.fs.myfs
  "A simple demonstration of an fs implementation for a Drake plugin.
   Provides a custom filesystem, myfs"
  (:use [drake-interface.core :only [FileSystem]]
        [clojure.string :only [join split]]))

(def ^:private MYFS-DATA
  {"/A" {:mod-time 108}
   "/B" {:mod-time 107}
   "/C" {:mod-time 106}
   "/D" {:mod-time 105}
   "/E" {:mod-time 104}
   "/F" {:mod-time 103}
   "/G" {:mod-time 102}
   "/H" {:mod-time 101}
   "/I" {:mod-time 100}
   "/K" {:mod-time 99}
   "/X" {:mod-time 207}
   "/Y" {:mod-time 208 :directory true}
   "/Y/A" {:mod-time 210}
   "/Y/B" {:mod-time 212}
   "/Y/C" {:mod-time 224}})

(defn file-info-impl [fs path]
  {:path path
   :mod-time (.mod-time fs path)
   :directory (.directory? fs path)})

(defn file-info-seq-impl [fs path]
  (map #(.file-info fs %) (.file-seq fs path)))

(defn data-in?-impl [fs path]
  (not (empty? (.file-info-seq fs path))))

(defn remove-extra-slashes
  "Removes duplicate and trailing slashes from the filename."
  [filename]
  (let [spl (split filename #"/" -1)]
    (str (if (empty? (first spl)) "/" "")
         (join "/" (filter (complement empty?) spl)))))

(defn myfs
  []
  (reify FileSystem
    (exists? [_ path]
      (contains? MYFS-DATA path))
    (directory? [_ path]
      (get-in MYFS-DATA path :directory))
    (mod-time [this path]
      (if-not (.exists? this path)
        (throw (Exception. (str "file not found: " path)))
        (condp = (:mod-time (MYFS-DATA path))
          :pre (Long/MIN_VALUE)
          :now (System/currentTimeMillis)
          (:mod-time (MYFS-DATA path)))))
    (file-seq [_ path]
      (keys (filter (fn [[name opts]]
                      ;; skip directories
                      (and (not (opts :directory))
                           (.startsWith name path)))
                    MYFS-DATA)))
    (file-info [this path]
      (file-info-impl this path))
    (file-info-seq [this path]
      (file-info-seq-impl this path))
    (data-in? [this path]
      (data-in?-impl this path))
    (normalized-filename [_ path]
      (remove-extra-slashes path))
    (rm [_ _]
      (throw (Exception. (str "rm is not implemented on mock filesystem"))))
    (mv [_ _ _]
      (throw (Exception. (str "mv is not implemented on mock filesystem"))))))
