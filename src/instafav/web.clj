(ns instafav.web
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

(defn wrap-title [title]
  (str title " — InstaFav"))

(defn base-page [title body]
  (let [title (wrap-title title)]
       [body (hiccup/html body)]
    (page/html5
      [:head
        [:title title]
        [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"}]
        [:link {:rel "stylesheet" :href (bootstrap :css)}]
        [:sript {:src (bootstrap :js)}]]
      [:body body]
    )))

(defn login []
  (base-page "Login"
    [:div {:class "container"}
      [:h1 "InstaFav"]
      [:p "Get updates when your favorite Instagram friends post."]
      [:a {:class "btn btn-default" :href insta/auth-url} "Login with Instagram"]
    ]
  ))

; Will get a "code" get parameter which contains the
; token for the user who just authenticated.
(defn receive-callback [params]
  (let [code (params :code)]
    (json/write-str (insta/get-access-token code))))

(defn test-cookies [params session]
  ; { :session params }
  ; (json/write-str {:params params :session session})
  ; )
  {:headers {"Content-Type" "text/html; charset=utf-8"}
   :session (merge session params)
   :body (base-page "Cookies"
    [:div {:class "container"}
      [:h1 "Cookies"]
      [:pre (json/write-str {:params params :session session})]
    ])
  })

(defroutes routes
  (GET "/" [] (login))
  (GET "/callback" {params :params} (receive-callback params))
  (GET "/cookies" {params :params session :session} (test-cookies params session)))

(def app (wrap-defaults routes site-defaults))
