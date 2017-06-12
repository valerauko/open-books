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
    [open-books.views.static :as view]
    [ring.middleware.session :as session]
  )
)

(defroutes open-books-routes
  (context "/api" {s :session p :params}
    (context "/join" []
      (GET  "/"     []    (join/form s) )
      (POST "/"     []    (join/create p s) )
      (GET  "/:key" [key] (join/confirm key s) )
    )

    (context "/reader/:user" [user]
      (GET    "/" [] (users/show user s) )
      (PATCH  "/" [] (users/patch user p s) )
      (DELETE "/" [] (users/delete user s) )
    )

    (context "/read" []
      (POST   "/"    []   (record/create p s) )
      (PATCH  "/:id" [id] (record/patch id p s) )
      (DELETE "/:id" [id] (record/delete id p s) )
    )

    (context "/book/:title" [title]
      (GET "/"    []   (books/search title s) )
      (GET "/:id" [id] (books/show id s) )
    )

    (context "/author/:name" [name]
      (GET "/"    []   (authors/search name s) )
      (GET "/:id" [id] (authors/show id s) )
    )

    (POST   "/login"  [] (login/create p s) )
    (DELETE "/login"  [] (login/destroy s) ) ; delete login = logout. herpa derpa.
    (GET    "/"       [] (home/timeline s) )

    (route/not-found (json/write-str {:message "Requested resource not found."}))
  )
  (GET "/" [] (view/index))
  (route/resources "/")
  (route/not-found (view/e404))
)

(defn -main []
  (run-server (-> open-books-routes session/wrap-session) {:port 8080})
)
