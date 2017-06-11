(defproject open-books "0.1.0-SNAPSHOT"
  :description "Quick tool to track what I've read"
  :url "https://valerauko.net/open-books"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [compojure "1.5.1"]
                 [http-kit "2.1.16"]]
  :main open-books.handler/-main
)
