(ns clj-shrink.models.link
  (:require [clj-time.core :as ctime]
            [clj-time.format :as tform]
            [clj-time.coerce :as coerce]
            [clojure.string :as string])
  (:use korma.db
        korma.core))

(def time-format (tform/formatter "H:mm:ss" (ctime/default-time-zone)))
(def date-format (tform/formatter "MM/dd/yy" (ctime/default-time-zone)))

(defn timestamp []
  (let [now (ctime/now)]
    (str (tform/unparse date-format now) " " (tform/unparse time-format now))))

(defdb prod (mysql {:db "cljshrink"
                    :user "root"
                    :password "lingo23"}))

(defentity ltable
  (database prod)
  (table :cljshrink_link))

(defn new-link [{:keys [sourceurl link]}]
  (insert ltable
          (values {:sourceurl sourceurl
                   :link link
                   :created (timestamp)})))

(defn bulk-links [coll]
  (insert link (values coll)))
