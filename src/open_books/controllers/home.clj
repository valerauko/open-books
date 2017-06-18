(ns open-books.controllers.home
  (:require [open-books.views.json :as json])
    ;[clj-orient2.core :as odb])
)

(defn timeline
  "Show timeline"
  [s]
  (println "Session: " s)

  (json/render "Hello bae" s)
)
