(ns instafav.web
  (:gen-class)
  (:require
    [compojure.core :refer [defroutes GET]]
    [compojure.handler :refer [api]]
    [clojure.data.json :as json]
    [hiccup.page :as page]
    [ring.middleware.defaults :refer :all]
    [instafav.insta :as insta]))

(defn login []
  ; (json/write-str { :hello "World" }))
  ; (json/write-str insta/credentials))
  (page/html5
    [:head
      [:title "Instafav"]]
    [:body
      [:div {:id "content"}
        [:h1 "InstaFav"]
        [:p "Get updates when your favorite Instagram friends post."]
        [:a {:href insta/auth-url} "Login with Instagram"]
      ]
    ]
  ))

; Will get a "code" get parameter which contains the
; token for the user who just authenticated.
(defn receive-callback [params]
  (json/write-str params))

(defroutes routes
  (GET "/" [] (login))
  (GET "/callback" {params :params} (receive-callback params)))

(def site (wrap-defaults routes api-defaults))
