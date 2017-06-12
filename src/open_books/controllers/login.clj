(ns open-books.controllers.login
  ; (:require [open-books.views.login :as view])
)

(defn form
  "Show login form"
  []
  ; TODO
)

(defn create
  "Login"
  [params session]
  (if (:open-books session)
    {:body (str "Session variable set to " (:open-books session))}
    {:body "Session variable not yet set. Setting it now."
     :session (assoc session :open-books (java.util.Date.))}
  )
)

(defn destroy
  "Logout"
  [session]
  (if (:open-books session)
    {:body (str "Session variable set to " (:open-books session) ". Destroying.")
     :session nil}
    {:body "Session variable not yet set. All is well."}
  )
)
