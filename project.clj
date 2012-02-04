(defproject clj-shrink "0.1.0-SNAPSHOT"
            :description "A URL shortener written in Clojure with Noir."
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [mysql/mysql-connector-java "5.1.6"]
                           [noir "1.2.1"]
                           [clj-time "0.3.0"]
                           [korma "0.2.1"]]
            :main clj-shrink.server)

