(ns clj-shrink.views.common
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpartial layout [& content]
            (html5
              [:head
               [:title "clj-shrink"]
               (include-css "/css/reset.css")]
              [:body
               [:div#wrapper
                content]]))
