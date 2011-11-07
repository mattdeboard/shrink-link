(ns clj-shrink.helpers
  (:import [java.net Socket ServerSocket UnknownHostException]
           [java.io IOException DataInputStream DataOutputStream]
           [java.util.regex Pattern]
           [java.security NoSuchAlgorithmException MessageDigest]))

;; This module lifted entirely from https://github.com/shughes/clojure-memcached
(defn md5 
  "Return md5 hash string"
  [str]
  (let [alg (doto (MessageDigest/getInstance "MD5")
	      (.reset)
	      (.update (.getBytes str)))]
    (try
     (.toString (new BigInteger 1 (.digest alg)) 16)
     (catch NoSuchAlgorithmException e
       (throw (new RuntimeException e))))))
