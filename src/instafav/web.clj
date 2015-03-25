(ns instafav.web
  (:gen-class)
  (:require
    [compojure.core :refer [defroutes GET]]
    [clojure.data.json :as json]
    [instafav.insta :as insta]))

(defn hello-world []
  ; (json/write-str { :hello "World" }))
  (json/write-str insta/credentials))

(defn receive-callback []
  (json/write-str { :receive "callback?" }))

(defroutes routes
  (GET "/" [] (hello-world))
  (GET "/callback" [] (receive-callback)))
