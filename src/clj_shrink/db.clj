(ns clj-shrink.db
  (:require [clojure.java.jdbc :as jdbc])
  (:use clj-shrink.secret))

(defn create-links []
  (clojure.java.jdbc/create-table :cljshrink_link
                                  [:id :integer "PRIMARY KEY" "AUTO_INCREMENT"]
                                  [:sourceurl "varchar(255)"]
                                  [:link "varchar(10)"]
                                  [:created "TIMESTAMP" "DEFAULT CURRENT_TIMESTAMP"]))

(defn create_table []
  (let [db-host "localhost"
        db-port 3306
        db-name "cljshrink"
        db {:classname "com.mysql.jdbc.Driver"
            :subprotocol "mysql"
            :subname (str "//" db-host ":" db-port "/" db-name)
            :user db-user
            :password db-pass}]
    (clojure.java.jdbc/with-connection db
      (clojure.java.jdbc/transaction (create-links)))))

