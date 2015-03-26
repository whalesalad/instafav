(ns instafav.web
  (:gen-class)
  (:require
    [compojure.core :refer [defroutes GET]]
    [compojure.handler :refer [api]]
    [clojure.data.json :as json]
    [hiccup.core :as hiccup]
    [hiccup.page :as page]
    [ring.middleware.defaults :refer :all]
    [instafav.insta :as insta]))

(def bootstrap { :css "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"
                 :js "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" })

(defn base-page [title body]
  (let [body (hiccup/html body)]
    (page/html5
      [:head
        [:title title]
        [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"}]
        [:link {:rel "stylesheet" :href (bootstrap :css)}]
        [:sript {:src (bootstrap :js)}]]
      [:body body]
    )))

(defn login []
  (base-page "Login – InstaFav"
    [:div {:class "container"}
      [:h1 "InstaFav"]
      [:p "Get updates when your favorite Instagram friends post."]
      [:a {:class "btn btn-default" :href insta/auth-url} "Login with Instagram"]
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
