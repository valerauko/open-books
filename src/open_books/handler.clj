(ns open-books.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.server :refer [run-server]]))

(defroutes open-books-routes
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(defn -main []
  (run-server open-books-routes {:port 8080})
)
