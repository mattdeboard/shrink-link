(ns clj-shrink.views.main
  (:require [clj-shrink.models.link :as slink]
            [clj-shrink.views.common :as common]
            [clj-shrink.helpers :as mc]
            [noir.content.getting-started :as pages])
  (:use noir.core
        noir.response
        hiccup.form-helpers
        hiccup.core
        hiccup.page-helpers))

(def base-url "http://127.0.0.1:8080/")

(defn hash-url [s]
  "Take the first 5 chars of the MD5 hash of the URL as the shortened link."
  (let [h (apply str (take 5 (mc/md5 s)))]
    (if (not (re-find #"http://.*" s))
      (slink/new-link {:sourceurl (str "http://" s) :link h})
      (slink/new-link {:sourceurl s :link h}))
    h))

(defpartial build-head []
  [:head
   [:title "Clj-Shrink URL Shortener"]])

(defpartial link-form [& [{:keys [sourceurl]}]]
  (text-field {:placeholder "Link to Shrink"} "mainInput" sourceurl))

(defpage "/" {}
  (common/layout
   (form-to [:post "/"]
            (link-form)
            (submit-button "Shrink"))))

(defpage [:post "/"] {:as req}
  (let [l (str base-url (hash-url (:mainInput req)))]
    (common/layout
     [:div {:id "newLink"}
      [:p "Shrunk to: " (link-to l l)]]
     (form-to [:post "/"]
              (link-form req)
              (submit-button "Shrink")))))

(defpage "/:s" {:keys [s]}
  (if-let [sl (first (slink/link-sel s))]
    (redirect (:sourceurl sl))))
             

