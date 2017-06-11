(ns open-books.handler
  (:require
    [clojure.data.json :as json]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [org.httpkit.server :refer [run-server]]
    [open-books.controllers [join :as join]
                            [users :as users]
                            [record :as record]
                            [book :as books]
                            [author :as authors]
                            [login :as login]
                            [home :as home]]
    [open-books.views :as view]
  )
)

(defroutes open-books-routes
  (context "/api" []
    (context "/join" []
      (GET  "/"     []    (join/form) )
      (POST "/"     []    (join/create) )
      (GET  "/:key" [key] (join/confirm key) )
    )

    (context "/reader/:user" [user]
      (GET    "/" [] (users/show user) )
      (PATCH  "/" [] (users/patch user) )
      (DELETE "/" [] (users/delete user) )
    )

    (context "/read" []
      (POST   "/"    []   (record/create) )
      (PATCH  "/:id" [id] (record/patch id) )
      (DELETE "/:id" [id] (record/delete id) )
    )

    (context "/book/:title" [title]
      (GET "/"    []   (books/search title) )
      (GET "/:id" [id] (books/show id) )
    )

    (context "/author/:name" [name]
      (GET "/"    []   (authors/search name) )
      (GET "/:id" [id] (authors/show id) )
    )

    (context "/login" []
      (GET  "/" [] (login/form) )
      (POST "/" [] (login/create) )
    )

    (context "/" []
      (GET  "/" [] (home/timeline) )
    )

    (route/not-found (json/write-str {:message "Requested resource not found."}))
  )
  (GET "/" [] (view/index))
  (route/resources "/")
  (route/not-found (view/e404))
)

(defn -main []
  (run-server open-books-routes {:port 8080})
)
