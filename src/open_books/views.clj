(ns open-books.views
  (:require [clojure.string])
)

(defn index
  "Return base index.html"
  []
  (slurp "resources/templates/index.html")
)

(defn e404
  "Render 404 error"
  []
  (clojure.string/replace
    (clojure.string/replace
      (slurp "resources/templates/index.html")
      #"(<title>).*(</title>)"
      (str "$1" "Open Books: Not found" "$2")
    )
    #"(<body>).*(</body>)"
    (str "$1" (slurp "resources/templates/404.html") "$2")
  )
)
