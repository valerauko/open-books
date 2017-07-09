(ns open-books.controllers.home
  (:require [open-books.views.json :as json]
            [clj-orient2.core :as odb])
)

(defn timeline
  "Show timeline"
  [s]
  (println "Session: " s)
  (odb/with-orient (odb/get-graph)
    (odb/make-vertex g "Author" {:name "John Doe" :died 1930})
    (doseq [v (odb/get-vertices-of-class g "Author")]
      (println "Vertex name:" (odb/vertex-to-map g v))
      (odb/remove-vertex g v)
      (odb/commit g)
    )
  )

  (json/render "Hello bae" s)
)
