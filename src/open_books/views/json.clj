(ns open-books.views.json
  (:require [clojure.data.json :as json])
  (:import java.util.Date)
)

(defn render
  "Return JSON response"
  [body sess]
  {:body body :session (if (contains? sess :user)
    (assoc sess :date (Date.))
    nil )}
)
